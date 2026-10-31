package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * The department mini-sites under {@code /department/<dept>/...} (replaces ten near-identical controllers).
 *
 * <p>The URL patterns list exactly the departments and pages that existed before, so unknown pages still answer
 * 404 and about/notice stay GET-only (405 otherwise), while the static pages and faculty shortcuts accept any
 * method, as before. Views are {@code department/<dept>/<page>.jsp}. The course page is in CourseFileController.
 */
@Controller
public class DepartmentController {

    private static final String ALL_DEPTS =
            "cse|civil|mechanical|ece|eee|ecevlsi|ash|fp|manufacturing|mechandsmartmanufacturing";
    private static final String MECH_DEPTS = "mechanical|manufacturing|mechandsmartmanufacturing";
    private static final String OTHER_DEPTS = "cse|civil|ece|eee|ecevlsi|ash|fp";
    private static final String PAGES = "vision|students|labs|question|awards|programs|weekly|attendance";
    private static final String MECH_PAGES = PAGES + "|faculty|staff";

    private static final String CSE_FP_FACULTY = "subodh|dky|shweta|snehakumari|sjk|mta|pks|cmm|snjk|shk";
    private static final String CIVIL_FACULTY = "rm|rps|akg|abhi|sss|ar|kkk";
    private static final String MECH_FACULTY = "srk|jk|mk|ajk|smnk|ap|kks";
    private static final String ECE_FACULTY = "principal|rk|aks|ak|aa|sa|ss|tr|rdr|nk";
    private static final String ASH_FACULTY = "pk|vk|bnm|snk";

    /** Faculty shortcut URL {@code <dept>/<page>} -> user name of the faculty member. */
    private static final Map<String, String> FACULTY_USERNAMES = Map.ofEntries(
            Map.entry("cse/subodh", "subodhkumar"), Map.entry("cse/dky", "kumar.dharmveer"),
            Map.entry("cse/shweta", "shwetakumari"), Map.entry("cse/snehakumari", "snehakumari"),
            Map.entry("cse/sjk", "sujeet.cse"), Map.entry("cse/mta", "Talib"), Map.entry("cse/pks", "pradeep"),
            Map.entry("cse/cmm", "chintamani"), Map.entry("cse/snjk", "sinjan.kumar"), Map.entry("cse/shk", "sritosh"),
            Map.entry("fp/subodh", "subodhkumar"), Map.entry("fp/dky", "kumar.dharmveer"),
            Map.entry("fp/shweta", "shwetakumari"), Map.entry("fp/snehakumari", "snehakumari"),
            Map.entry("fp/sjk", "sujeet.fp"), Map.entry("fp/mta", "Talib"), Map.entry("fp/pks", "pradeep"),
            Map.entry("fp/cmm", "chintamani"), Map.entry("fp/snjk", "sinjan.kumar"), Map.entry("fp/shk", "sritosh"),
            Map.entry("civil/rm", "talktorashid"), Map.entry("civil/rps", "rpsingh"), Map.entry("civil/akg", "anilanu"),
            Map.entry("civil/abhi", "abhishek"), Map.entry("civil/sss", "shashank"), Map.entry("civil/ar", "aale"),
            Map.entry("civil/kkk", "krishna"),
            Map.entry("mech/srk", "suryakumar"), Map.entry("mech/jk", "jayant906"), Map.entry("mech/mk", "mukeshme24"),
            Map.entry("mech/ajk", "kumarajay"), Map.entry("mech/smnk", "sumankumar"), Map.entry("mech/ap", "arbind"),
            Map.entry("mech/kks", "kanchan"),
            Map.entry("ece/principal", "principal"), Map.entry("ece/rk", "ram"), Map.entry("ece/aks", "amit"),
            Map.entry("ece/ak", "abhijeet.kumar"), Map.entry("ece/aa", "alauddinansari0432"),
            Map.entry("ece/sa", "sajjad.ahmad"), Map.entry("ece/ss", "saketsaurabh"), Map.entry("ece/tr", "tarique"),
            Map.entry("ece/rdr", "rajeev"), Map.entry("ece/nk", "navin"),
            Map.entry("ash/pk", "pramod"), Map.entry("ash/vk", "vipin"), Map.entry("ash/bnm", "bnmahto"),
            Map.entry("ash/snk", "sunil"));

    private final NoticeBoardService noticeBoardService;
    private final FacultyHelper facultyHelper;

    public DepartmentController(NoticeBoardService noticeBoardService, FacultyHelper facultyHelper) {
        this.noticeBoardService = noticeBoardService;
        this.facultyHelper = facultyHelper;
    }

    /** Department home page with its notices (mechanical's is {@code /mech}). */
    @GetMapping({"/department/{dept:cse|civil|ece|eee|ecevlsi|ash|fp|manufacturing|mechandsmartmanufacturing}/about",
            "/department/{dept:mechanical}/mech"})
    public ModelAndView getAbout(@PathVariable("dept") String dept) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("UserName", ControllerUtility.getUserName());
        List<NoticeBoardDTO> notices = notices(dept);
        ControllerUtility.getNoticelist(notices);
        mv.addObject("noticeList", ControllerUtility.filterNotices(notices, false));
        mv.addObject("scrollingNoticeList", ControllerUtility.filterNotices(notices, true));
        if ("civil".equals(dept)) {
            mv.addObject("blinkMessage", noticeBoardService.getBlinkingMessage(PageRequest.of(0, 10)));
        }
        mv.setViewName("department/" + dept + "/about.jsp");
        return mv;
    }

    @GetMapping("/department/{dept:" + ALL_DEPTS + "}/notice")
    public ModelAndView getNotice(@PathVariable("dept") String dept) {
        ModelAndView mv = new ModelAndView();
        List<NoticeBoardDTO> notices = notices(dept);
        ControllerUtility.getNoticelist(notices);
        mv.addObject("noticeList", notices);
        // cse and fp passed the page size as a String, the others as an Integer (renders the same)
        mv.addObject("pageSize", "cse".equals(dept) || "fp".equals(dept) ? "0" : (Object) 0);
        mv.setViewName("department/" + dept + "/notice.jsp");
        return mv;
    }

    @RequestMapping({"/department/{dept:" + OTHER_DEPTS + "}/{page:" + PAGES + "}",
            "/department/{dept:" + MECH_DEPTS + "}/{page:" + MECH_PAGES + "}"})
    public ModelAndView getPage(@PathVariable("dept") String dept, @PathVariable("page") String page) {
        return new ModelAndView("department/" + dept + "/" + page + ".jsp");
    }

    /** Per-faculty shortcuts (their JSPs do not exist; kept as they were). */
    @RequestMapping({"/department/{dept:cse|fp}/{page:" + CSE_FP_FACULTY + "}",
            "/department/{dept:civil}/{page:" + CIVIL_FACULTY + "}",
            "/department/{dept:" + MECH_DEPTS + "}/{page:" + MECH_FACULTY + "}",
            "/department/{dept:ece|eee|ecevlsi}/{page:" + ECE_FACULTY + "}",
            "/department/{dept:ash}/{page:" + ASH_FACULTY + "}"})
    public ModelAndView getFacultyPage(@PathVariable("dept") String dept, @PathVariable("page") String page) {
        String group = switch (dept) {
            case "mechanical", "manufacturing", "mechandsmartmanufacturing" -> "mech";
            case "ece", "eee", "ecevlsi" -> "ece";
            default -> dept;
        };
        ModelAndView mv = facultyHelper.getFacultyDetails(FACULTY_USERNAMES.get(group + "/" + page));
        boolean oldAshPage = "ash".equals(dept) && ("bnm".equals(page) || "snk".equals(page));
        mv.setViewName("department/" + dept + (oldAshPage ? "/old/" : "/") + page + ".jsp");
        return mv;
    }

    private List<NoticeBoardDTO> notices(String dept) {
        PageRequest firstTen = PageRequest.of(0, 10);
        return switch (dept) {
            case "cse" -> noticeBoardService.getCseNotices(firstTen);
            case "civil" -> noticeBoardService.getCivilNotices(firstTen);
            case "mechanical", "manufacturing", "mechandsmartmanufacturing" -> noticeBoardService.getMechNotices(firstTen);
            case "ece", "eee", "ecevlsi" -> noticeBoardService.getEecNotices(firstTen);
            case "ash" -> noticeBoardService.getApplidScienceNotices(firstTen);
            case "fp" -> noticeBoardService.getfpNotices(firstTen);
            default -> throw new IllegalArgumentException("unknown department " + dept);
        };
    }
}
