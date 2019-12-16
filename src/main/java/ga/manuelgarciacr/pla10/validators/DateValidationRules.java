package ga.manuelgarciacr.pla10.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidationRules implements ConstraintValidator<DateValidation, String> {
	@Override
	public void initialize(DateValidation date) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext cxt) {
		if(date == null)
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		try {
			Date dateDate = sdf.parse(date);
			return true;
		
		} catch (ParseException e) {
			return false;
		}
	}
}
