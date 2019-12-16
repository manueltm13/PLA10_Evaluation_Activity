package ga.manuelgarciacr.pla10.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateValidationRules.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValidation {
	String message() default "Invalid date";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
