package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
@Controller
public class MainController {

    @GetMapping("/")
    public String main_page(Model model) {
    	
    	model.addAttribute("main_html", "main/home");
    	return "main";
    }
}
