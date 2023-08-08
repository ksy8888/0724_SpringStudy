package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;


@Controller
public class RecipeController {
	
	@GetMapping("recipe/list.do")
	public String recipe_list() {
		return "recipe/list";
	}
}
