package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.ContentFile;
import com.college.core.entity.ContentAreaRole;
import com.college.core.entity.PageBlock;
import com.college.core.model.AreaAccess;
import com.college.core.tags.FileTypes;
import com.college.core.tags.PageHtml;
import com.college.core.tags.PageKeys;
import com.college.repository.ContentFileRepository;
import com.college.repository.PageBlockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageBlockServiceImpl implements PageBlockService {
    private static final Logger logger = LoggerFactory.getLogger(PageBlockServiceImpl.class);
    static final String ENTITY = "page_block";
    static final long MAX_FILE_SIZE = 20L * 1024 * 1024;
    private static final Set<String> TYPES = new HashSet<>(Arrays.asList(PageBlock.HEADING, PageBlock.SUBHEADING,
            PageBlock.TEXT, PageBlock.IMAGE, PageBlock.DOCUMENT));
    private static final Set<String> IMAGE_SIZES = new HashSet<>(Arrays.asList("small", "medium", "full"));

    private final PageBlockRepository pageBlockRepository;
    private final ContentFileRepository contentFileRepository;
    private final ContentPermissionService contentPermissionService;
    private final ContentChangeLogService contentChangeLogService;

    /** Visible blocks per page; replaced after every change (changes made by SQL need a restart). */
    private volatile Map<String, List<PageBlock>> cache;

    public PageBlockServiceImpl(PageBlockRepository pageBlockRepository, ContentFileRepository contentFileRepository,
                                ContentPermissionService contentPermissionService,
                                ContentChangeLogService contentChangeLogService) {
        this.pageBlockRepository = pageBlockRepository;
        this.contentFileRepository = contentFileRepository;
        this.contentPermissionService = contentPermissionService;
        this.contentChangeLogService = contentChangeLogService;
    }

    @Override
    public List<PageBlock> visibleBlocks(String pageKey) {
        Map<String, List<PageBlock>> pages = cache;
        if (pages == null) {
            pages = load();
            cache = pages;
        }
        List<PageBlock> blocks = pages.get(pageKey);
        return blocks == null ? Collections.emptyList() : blocks;
    }

    private Map<String, List<PageBlock>> load() {
        Map<String, List<PageBlock>> pages = new HashMap<>();
        try {
            List<PageBlock> visible = pageBlockRepository.findByVisibleTrueOrderBySortOrderAscBlockIdAsc();
            addFileInfo(visible);
            for (PageBlock block : visible) {
                pages.computeIfAbsent(block.getPageKey(), k -> new ArrayList<>()).add(block);
            }
        } catch (DataAccessException e) {
            logger.warn("page_block not readable, pages show no added content: {}", e.getMostSpecificCause().getMessage());
        }
        return pages;
    }

    private void addFileInfo(List<PageBlock> blocks) {
        Set<Long> ids = new HashSet<>();
        for (PageBlock block : blocks) {
            if (block.getFileId() != null) ids.add(block.getFileId());
        }
        if (ids.isEmpty()) return;
        Map<Long, Object[]> info = new HashMap<>();
        for (Object[] row : contentFileRepository.findInfo(ids)) {
            info.put((Long) row[0], row);
        }
        for (PageBlock block : blocks) {
            Object[] row = info.get(block.getFileId());
            if (row != null) {
                block.setFileName((String) row[1]);
                block.setFileType((String) row[2]);
                block.setFileSize((Long) row[3]);
            }
        }
    }

    @Override
    public List<PageBlock> allBlocks(String pageKey) {
        List<PageBlock> blocks = pageBlockRepository.findByPageKeyOrderBySortOrderAscBlockIdAsc(pageKey);
        addFileInfo(blocks);
        return blocks;
    }

    @Override
    public String access(Authentication authentication, String pageKey) {
        return areaAccess(authentication, PageKeys.area(pageKey));
    }

    private String areaAccess(Authentication authentication, String areaCode) {
        if (areaCode == null) return null;
        for (AreaAccess access : contentPermissionService.editableAreas(authentication)) {
            if (access.getArea().getAreaCode().equals(areaCode)) {
                return access.getAccess();
            }
        }
        return null;
    }

    @Override
    public boolean canChange(Authentication authentication, PageBlock block) {
        String access = areaAccess(authentication, block.getAreaCode());
        return ContentAreaRole.EDIT.equals(access)
                || (ContentAreaRole.ADD_ONLY.equals(access) && Objects.equals(block.getCreatedBy(), ControllerUtility.getPrincipalName()));
    }

    @Override
    public PageBlock block(Long blockId) {
        return blockId == null ? null : pageBlockRepository.findById(blockId).orElse(null);
    }

    @Override
    public String save(Authentication authentication, String pageKey, Long blockId, String type, String heading,
                       String html, String display, boolean visible, String fileName, byte[] fileData) {
        PageBlock block = blockId == null ? new PageBlock() : block(blockId);
        if (block == null || (blockId != null && !block.getPageKey().equals(pageKey))) {
            return "This entry does not exist any more.";
        }
        String blockType = blockId == null ? type : block.getBlockType(); // the type of an existing block stays
        if (!TYPES.contains(blockType)) {
            return "Please choose what to add.";
        }
        boolean hasFile = fileData != null && fileData.length > 0;
        String text = heading == null ? "" : heading.trim();
        String fileType = null;
        if (text.length() > 255) {
            return "The heading / caption / title may have at most 255 characters.";
        }
        String clean = null;
        switch (blockType) {
            case PageBlock.HEADING:
            case PageBlock.SUBHEADING:
                if (text.isEmpty()) return "Please enter the heading.";
                break;
            case PageBlock.TEXT:
                clean = PageHtml.sanitize(html == null ? "" : html);
                if (clean.replaceAll("<[^>]*>", "").trim().isEmpty() && !clean.contains("<img") && !clean.contains("<iframe")) {
                    return "Please enter the text.";
                }
                break;
            case PageBlock.IMAGE:
                if (!hasFile && block.getFileId() == null) return "Please choose an image file.";
                if (hasFile && (fileType = FileTypes.image(fileData)) == null) {
                    return "Only JPG, PNG, GIF and WebP images can be uploaded.";
                }
                display = IMAGE_SIZES.contains(display) ? display : "medium";
                break;
            case PageBlock.DOCUMENT:
                if (!hasFile && block.getFileId() == null) return "Please choose a document.";
                if (hasFile && (fileType = FileTypes.document(fileData, fileName)) == null) {
                    return "Only PDF and Word/Excel/PowerPoint files (.doc, .docx, .xls, .xlsx, .ppt, .pptx) can be uploaded.";
                }
                display = "link".equals(display) ? "link" : "embed";
                break;
            default:
                return "Please choose what to add.";
        }
        if (hasFile && fileData.length > MAX_FILE_SIZE) {
            return "The file may be at most 20 MB.";
        }

        String before = blockId == null ? null : describe(block);
        String user = ControllerUtility.getPrincipalName();
        Long oldFileId = block.getFileId();
        if (hasFile) {
            ContentFile file = new ContentFile();
            file.setAreaCode(PageKeys.area(pageKey));
            file.setFileName(FileTypes.cleanName(fileName, PageBlock.IMAGE.equals(blockType) ? "image" : "document"));
            file.setContentType(fileType);
            file.setFileSize(fileData.length);
            file.setData(fileData);
            file.setUploadedBy(user);
            file.setUploadedAt(new Date());
            block.setFileId(contentFileRepository.save(file).getFileId());
        }
        if (blockId == null) {
            block.setPageKey(pageKey);
            block.setAreaCode(PageKeys.area(pageKey));
            block.setBlockType(blockType);
            block.setSortOrder(nextSortOrder(pageKey));
            block.setCreatedBy(user);
            block.setCreatedAt(new Date());
        }
        block.setHeading(text.isEmpty() ? null : text);
        block.setBodyHtml(clean);
        block.setDisplay(PageBlock.IMAGE.equals(blockType) || PageBlock.DOCUMENT.equals(blockType) ? display : null);
        block.setVisible(visible);
        block.setUpdatedBy(user);
        block.setUpdatedAt(new Date());
        PageBlock saved = pageBlockRepository.save(block);
        if (hasFile && oldFileId != null) {
            deleteFileIfUnused(oldFileId);
        }
        contentChangeLogService.record(saved.getAreaCode(), ENTITY, String.valueOf(saved.getBlockId()),
                blockId == null ? "create" : "update", before, describe(saved));
        cache = null;
        return null;
    }

    @Override
    public void delete(PageBlock block) {
        pageBlockRepository.delete(block);
        if (block.getFileId() != null) {
            deleteFileIfUnused(block.getFileId());
        }
        contentChangeLogService.record(block.getAreaCode(), ENTITY, String.valueOf(block.getBlockId()), "delete",
                describe(block), null);
        cache = null;
    }

    @Override
    public void move(PageBlock block, int direction) {
        List<PageBlock> blocks = pageBlockRepository.findByPageKeyOrderBySortOrderAscBlockIdAsc(block.getPageKey());
        int index = -1;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getBlockId().equals(block.getBlockId())) index = i;
        }
        int target = index + (direction < 0 ? -1 : 1);
        if (index < 0 || target < 0 || target >= blocks.size()) {
            return;
        }
        Collections.swap(blocks, index, target);
        for (int i = 0; i < blocks.size(); i++) {
            PageBlock b = blocks.get(i);
            if (b.getSortOrder() != (i + 1) * 10) {
                b.setSortOrder((i + 1) * 10);
                pageBlockRepository.save(b);
            }
        }
        contentChangeLogService.record(block.getAreaCode(), ENTITY, String.valueOf(block.getBlockId()), "update", null,
                "moved " + (direction < 0 ? "up" : "down"));
        cache = null;
    }

    @Override
    public ContentFile servableFile(Authentication authentication, Long fileId) {
        ContentFile file = contentFileRepository.findById(fileId).orElse(null);
        if (file == null) {
            return null;
        }
        if (pageBlockRepository.countByFileIdAndVisibleTrue(fileId) > 0 || areaAccess(authentication, file.getAreaCode()) != null) {
            return file;
        }
        return null;
    }

    @Override
    public List<String> pagesWithBlocks(Authentication authentication) {
        List<String> pages = new ArrayList<>();
        for (String key : pageBlockRepository.findPageKeys()) {
            if (access(authentication, key) != null) pages.add(key);
        }
        return pages;
    }

    private void deleteFileIfUnused(Long fileId) {
        if (pageBlockRepository.countByFileId(fileId) == 0) {
            contentFileRepository.deleteById(fileId);
        }
    }

    private int nextSortOrder(String pageKey) {
        int max = 0;
        for (PageBlock block : pageBlockRepository.findByPageKeyOrderBySortOrderAscBlockIdAsc(pageKey)) {
            max = Math.max(max, block.getSortOrder());
        }
        return max + 10;
    }

    private static String describe(PageBlock block) {
        String text = block.getBodyHtml() == null ? "" : " " + block.getBodyHtml();
        return block.getBlockType() + ": " + (block.getHeading() == null ? "" : block.getHeading())
                + (block.getFileId() == null ? "" : " [file " + block.getFileId() + "]")
                + (block.isVisible() ? "" : " (hidden)") + text;
    }
}
