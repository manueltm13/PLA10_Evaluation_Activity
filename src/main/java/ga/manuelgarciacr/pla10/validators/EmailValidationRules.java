package ga.manuelgarciacr.pla10.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ga.manuelgarciacr.pla10.model.User;
import ga.manuelgarciacr.pla10.model.dao.IUserDAO;
import ga.manuelgarciacr.pla10.model.dao.UserDAO;
import ga.manuelgarciacr.pla10.model.services.UserService;

public class EmailValidationRules implements ConstraintValidator<EmailValidation, String> {

	@Override
	public void initialize(EmailValidation email) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		// General Email Regex (RFC 5322 Official Standard)
		// https://emailregex.com/
		if(email == null)
			return false;
		final Pattern PAT = Pattern.compile(
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" + 
			"[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" + 
			"@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" + 
			"|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]" + 
			":(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])|");
		Matcher mat = PAT.matcher(email.toLowerCase());
		if (!mat.matches())
			return false;
		return true;
	}

}
