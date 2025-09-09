package dev.corbie.api;

import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
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
}