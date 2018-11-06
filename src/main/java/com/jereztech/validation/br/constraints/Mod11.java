/*******************************************************************************
 * Copyright (C) 2018 Joel Jerez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jereztech.validation.br.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jereztech.validation.br.validator.Mod11Validator;

/**
 * The annotated element must be a 10-based numeric representation, that will be
 * used to determine check digits according to the routine of Mod11 algorithm.
 * 
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * <p>
 * Reference: Checksum formula (Doc No 54938870) - The Brazilian Federal Court
 * of Accounts.
 * 
 * @author Joel Jerez
 * 
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = Mod11Validator.class)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface Mod11 {

	String message() default "{com.jereztech.validation.br.constraints.Mod11.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return the number of check digits
	 */
	int count() default 1;

	/**
	 * @return the max sequence value for Mod11 algorithm
	 */
	int maxSequence() default Integer.MAX_VALUE;

	/**
	 * Gets aliases, for digits substitution, that can be applied to the check digit
	 * when the result in the Mod11 algorithm is greater than or equal to 10. The
	 * first alias that matches any of the regular expressions will be selected. If
	 * none {@code Pattern} matches or is {@code null} will be used 0 (zero).
	 * 
	 * @return aliases for digits substitution
	 * @see Mod11Alias
	 */
	Mod11Alias[] mod11Aliases() default {};

	/**
	 * Gets the beginning index (inclusive) of the purported.
	 * 
	 * @return the beginning index
	 */
	int beginIndex() default 0;

	/**
	 * Gets the ending index (exclusive) of the purported. When not specified or
	 * negative, it is considered {@code purported.length - count}.
	 * 
	 * @return the ending index
	 */
	int endIndex() default -1;

	/**
	 * Gets the check digit index. When not specified or negative, it is considered
	 * {@code purported.substring(checkDigitIndex, checkDigitIndex + count)}.
	 * 
	 * @return the check digit index
	 */
	int checkDigitIndex() default -1;

	/**
	 * Defines several {@link Mod11} annotations on the same element.
	 *
	 * @see Mod11
	 */
	@Documented
	@Retention(RUNTIME)
	@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
	@interface List {

		Mod11[] value();
	}

}
