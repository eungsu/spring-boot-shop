package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.MemberForm;
import com.example.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping(path = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/form";
	}
	
	@PostMapping(path = "/new")
	public String submit(@Valid MemberForm memberForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "member/form";
		}
		
		try {
			memberService.saveMember(memberForm);
		} catch (IllegalStateException e) {
			bindingResult.rejectValue("email", null, e.getMessage());
			return "member/form";
		}
		return "redirect:/";
	}
}
