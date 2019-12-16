package ga.manuelgarciacr.pla10.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EmailValidationRules.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
	String message() default "Invalid email";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
