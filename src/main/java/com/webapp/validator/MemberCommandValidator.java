package com.webapp.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webapp.command.MemberCommand;

public class MemberCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MemberCommand member = (MemberCommand) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "required");
		
		/*
		 * Email Length Check 64 이하
		 */
		if (member.getEmail().matches(".{65,}")) {
			errors.rejectValue("email", "length", new Object[] {64}, null);
		} 
		
//		String pattern = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+\\.[A-Za-z0-9]{2,3}$";
		String pattern = "^[A-Za-z0-9_\\-]+@([A-Za-z0-9]+\\.){1,2}[A-Za-z]{2,3}$";
		
		// ([A-Za-z0-9]+\\.){1,2} => ([A-Za-z0-9]+\\.) 이 그룹이 1번 혹은 2번 반복되는 패턴을 허용
		
		/*
		 * Email Pattern Check xxx@yyy.co.kr
		 */
		if (!member.getEmail().matches(pattern)) {
			errors.rejectValue("email", "pattern");
		}
		
		/*
		 * Password Length Check 8 이하
		 */
		if (!member.getPassword().matches("^.{8,64}$")) {
			errors.rejectValue("password", "length", new Object[] {8, 64}, null);
		}
		
		/*
		 * Password Pattern Check
		 *
		 * qw12er34 => valid
		 * qwer => invalid
		 * qwer1234 => invalid
		 * 1234 => invalid
		 *
		 * 1. 영문 & 숫자
		 * 2. 영문 연속 3자리 이상 x
		 * 3. 숫자 연속 3자리 이상 x
		 *
		 */
		if (!Pattern.compile("[A-Za-z]")
				   .matcher(member.getPassword())
				   .find())
			errors.rejectValue("password", "pattern");
		
		if (!Pattern.compile("[0-9]")
				   .matcher(member.getPassword())
				   .find())
			errors.rejectValue("password", "pattern");
		
		if (Pattern.compile("[A-Za-z]{3,}")
				   .matcher(member.getPassword())
				   .find() && !errors.hasFieldErrors("password"))
			errors.rejectValue("password", "pattern");
		
		if (Pattern.compile("[0-9]{3,}")
				   .matcher(member.getPassword())
				   .find() && !errors.hasFieldErrors("password"))
			errors.rejectValue("password", "pattern");
		
		
		/*
		 * Name Length Check 12 이하
		 */
		if (member.getName().matches("^.{13,}$")) {
			errors.rejectValue("name", "length", new Object[] {12}, null);
		}
		
		/*
		 * Comment Length Check 255 이하
		 */
		if (member.getComment().length() > 255) {
			errors.rejectValue("comment", "length", new Object[] {255}, null);
		}

		
		
	}

}
