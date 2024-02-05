package com.example.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Member;
import com.example.form.MemberForm;
import com.example.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(path = "/new")
	public String registerform(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "/member/form";
	}
	
	@PostMapping(path = "/new")
	public String submit(@Valid MemberForm memberForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/member/form";
		}
		
		try {
			Member member = memberForm.toMember(passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			bindingResult.rejectValue("email", null, e.getMessage());
			return "/member/form";
		}
		return "redirect:/";
	}

	@GetMapping(path = "/login")
	public String loginform() {
		return "/member/loginform";
	}
	
	@GetMapping(path = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMessage", "이메일 또는 비밀번호를 올바르지 않습니다.");
		return "/member/loginform";
	}
}
