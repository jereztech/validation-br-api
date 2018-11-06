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

import com.jereztech.validation.br.validator.TelefoneFixoValidator;

/**
 * The annotated element must be a 10-digit number, the first 2 digits being the
 * area code (DDD), and the remaining 8 digits referring to the subscriber
 * number; Accepts the following formats '9930000000' or '(99) 3000-0000'. Only
 * the area codes of the Brazilian Numbering Plan defined by ANATEL are allowed.
 * 
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * <p>
 * Reference: Resolution Act No 263/2001 - ANATEL.
 * 
 * @author Joel Jerez
 * 
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = TelefoneFixoValidator.class)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface TelefoneFixo {

	String message() default "{com.jereztech.validation.br.constraints.TelefoneFixo.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
