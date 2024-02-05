package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	@GetMapping(path = "/item/new")
	public String itemForm() {
		return "/admin/item/form";
	}
}
