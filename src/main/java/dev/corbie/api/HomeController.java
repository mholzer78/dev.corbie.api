package dev.corbie.api;

import java.io.File;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    Logger logger = Logger.getLogger(getClass().getName());
    //logger.info("example");
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("headline", "welcome to my api landingpage")
        .addAttribute("features", Arrays.asList(
            "Currently there is only one service which provides placeholder (lorem-) images ready to put on your website.",
            "But more services are about to come in the future.",
            "Usage for the LoremIpsum service. The given values are only examples, so feel free to choose what you need:"
        ));
        ArrayList<Links> links = new ArrayList<>();
        links.add(new Links("/loremimage","","an default image"));
        links.add(new Links("/loremimage/300","","an image with 300px x 300px"));
        links.add(new Links("/loremimage/400/200","","an image with 400px x 200px"));
        links.add(new Links("/loremimage/300/150/0000ff","","an image with 300px x 150px with blue background"));

        model.addAttribute("links", links);
        return "boilerplate";
    }
    /*
        public String loremimage(Model model) {
        model.addAttribute("headline", "loremimage description")
        .addAttribute("features", Arrays.asList(
            "ABC",
            "CDE"
        ));
        ArrayList<Links> links = new ArrayList<>();
        links.add(new Links("/loremimage/300","","an image with 300px x 300px"));
        links.add(new Links("/loremimage/400/200","","an image with 400px x 200px"));
        links.add(new Links("/loremimage/300/150/0000ff","","an image with 300px x 150px with blue background"));
        model.addAttribute("links", links);
        return "boilerplate";
    }
     */
}