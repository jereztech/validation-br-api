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
 * The annotated element must be a 12-digit number, being 8 base digits, 2
 * digits for the Federal State and 2 digits for the check digits.
 * 
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * <p>
 * Reference: Resolution Act No 19.875/1997 - The Brazilian Superior Electoral
 * Court. Checksum formula (Doc No 54938870) - The Brazilian Federal Court of
 * Accounts.
 * 
 * @author Joel Jerez
 * 
 */
@Pattern(regexp = "[0-9]{8}(0[1-9]|1[0-9]|2[0-8])[0-9]{2}") // Check format and Federal State [01 <= UF <= 28]
@Mod11.List({ @Mod11(endIndex = 8, checkDigitIndex = 10), @Mod11(beginIndex = 8, endIndex = 11, mod11Aliases = {
		@Mod11Alias(alias = 1, regexp = "[0-9]{8}0(1|2)[0-9]{2}") }) })
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE })
public @interface TituloEleitoral {

	String message() default "{com.jereztech.validation.br.constraints.TituloEleitoral.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
