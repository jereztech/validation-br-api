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

/**
 * The annotated element must be an alphanumeric code in the following format
 * 'AA999999'.
 * 
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * <p>
 * Reference: http://www.pf.gov.br/servicos-pf/passaporte - The Brazilian
 * Federal Police.
 * 
 * @author Joel Jerez
 * 
 */
@Pattern.List({ @Pattern(regexp = "[A-Z]{2}[0-9]{6}"), @Pattern(regexp = "^(?:(?![A-Z]{2}([0-9])\\1+).)*$") })
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface Passaporte {

	String message() default "{com.jereztech.validation.br.constraints.Passaporte.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
