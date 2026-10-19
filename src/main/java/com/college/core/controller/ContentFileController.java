package com.college.core.controller;

import com.college.core.entity.ContentFile;
import com.college.core.tags.FileTypes;
import com.college.service.PageBlockService;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Images and documents of page blocks: {@code /content-file/{id}} or {@code /content-file/{id}/{name}} (the name is
 * only for readable links). Files of hidden blocks are only shown to the editors of the page.
 */
@Controller
public class ContentFileController {
    private final PageBlockService pageBlockService;

    public ContentFileController(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }

    @GetMapping({"/content-file/{id}", "/content-file/{id}/{name}"})
    public ResponseEntity<byte[]> file(@PathVariable("id") Long id, Authentication authentication) {
        ContentFile file = pageBlockService.servableFile(authentication, id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean inline = FileTypes.inline(file.getContentType());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDisposition((inline ? ContentDisposition.inline() : ContentDisposition.attachment())
                .filename(file.getFileName(), StandardCharsets.UTF_8).build());
        headers.set("X-Content-Type-Options", "nosniff");
        // a new upload always gets a new id, so a file never changes: let browsers keep it for a day
        headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate());
        return new ResponseEntity<>(file.getData(), headers, HttpStatus.OK);
    }
}
