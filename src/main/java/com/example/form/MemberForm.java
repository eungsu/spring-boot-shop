package com.example.form;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.constant.Role;
import com.example.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

	@NotBlank(message = "이름은 필수입력값입니다.")
	private String name;
	
	@NotBlank(message = "이메일은 필수입력값입니다.")
	@Email(message = "유효한 이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "비밀번호는 필수입력값입니다.")
	private String password;
	
	@NotBlank(message = "우편번호와 주소는 필수입력값입니다.")
	private String zipcode;

	@NotBlank(message = "우편번호와 주소는 필수입력값입니다.")
	private String address1;

	@NotBlank(message = "우편번호와 주소는 필수입력값입니다.")
	private String address2;
	
	public Member toMember(PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(name);
		member.setEmail(email);
		member.setPassword(passwordEncoder.encode(password));
		member.setZipcode(zipcode);
		member.setAddress1(address1);
		member.setAddress2(address2);
		member.setRole(Role.ROLE_USER);
		member.setCreatedDate(LocalDateTime.now());
		
		return member;
	}
}
