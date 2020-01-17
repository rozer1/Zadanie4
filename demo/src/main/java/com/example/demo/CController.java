package com.example.demo;

import ezvcard.VCard;
import ezvcard.property.StructuredName;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@CrossOrigin
public class CController {
    List<Employee> users;
    private java.net.URL url;
    @Autowired
    private URLs urLs;

    @GetMapping("")
    public String index() {
        ModelAndView mv = new ModelAndView("book/form");
        mv.addObject("work", new Employee());
        return "index";
    }

    @GetMapping(value = "/search/name", params = "name")
    public String search(@RequestParam String name, Model model) {
        try {
            try {
                url = new URL("https://adm.edu.p.lodz.pl/user/users.php?search=" + name);
                users = urLs.generate(url);
                model.addAttribute("workersList", users);
                model.addAttribute("name", name);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return "search";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

}
