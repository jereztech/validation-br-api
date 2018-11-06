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

import javax.validation.Payload;

/**
 * The annotated element must be a single character, that can be applied to the
 * check digit when the result in the Mod11 algorithm is greater than or equal
 * to 10.
 * 
 * @author Joel Jerez
 * 
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface Mod11Alias {

	String message() default "{com.jereztech.validation.br.constraints.DVMod11Alias.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Gets the alias, for digit substitution, applied to the check digit when the
	 * result in the Mod11 algorithm is greater than or equal to 10.
	 * 
	 * @return the alias for digits substitution
	 */
	char alias() default '0';

	/**
	 * Gets the regular expression specified for {@link alias} matching.
	 * 
	 * @return the regular expression
	 */
	String regexp();

	/**
	 * Defines several {@link Mod11Alias} annotations on the same element.
	 *
	 * @see Mod11Alias
	 */
	@Documented
	@Retention(RUNTIME)
	@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
	@interface List {

		Mod11Alias[] value();
	}

}
