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
import javax.validation.constraints.Pattern;

import com.jereztech.validation.br.validator.AgenciaBancariaValidator;
import static com.jereztech.validation.br.validator.AgenciaBancariaValidator.AGENCIA_REGEXP;

/**
 * The annotated element must contain 4 numeric characters (with leading zeros
 * as needed) plus 1 check digit (not required); Allowed formats are '9999',
 * '9999A' or '9999-A'.
 * 
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * <p>
 * Reference: Resolution Act No 3.454/2010 - Central Bank of Brazil.
 * 
 * @author Joel Jerez
 * 
 */
@Pattern(regexp = AGENCIA_REGEXP)
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = AgenciaBancariaValidator.class)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface AgenciaBancaria {

	String message() default "{com.jereztech.validation.br.constraints.AgenciaBancaria.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

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

}
