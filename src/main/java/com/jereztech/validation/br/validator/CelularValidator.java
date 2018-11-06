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
package com.jereztech.validation.br.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jereztech.validation.br.constraints.Celular;

/**
 * Defines the logic to validate a given constraint Celular for a given object
 * type String.
 * 
 * @author Joel Jerez
 *
 */
public class CelularValidator implements ConstraintValidator<Celular, String> {

	private static final String REGION_I_III_MOBILE_REGEXP = "\\(?([189][1-9]|2[1-8&&[^356]]|3[1-8&&[^6]]|7[1-9&&[^268]])\\)?\\s?9[0-9]{4}\\-?[0-9]{4}";
	private static final String REGION_II_MOBILE_REGEXP = "\\(?([46][1-9]|5[1-5&&[^2]])\\)?\\s?9[987][0-9]{3}\\-?[0-9]{4}";
	public static final String MOBILE_REGEXP = String.format("%s|%s", REGION_I_III_MOBILE_REGEXP,
			REGION_II_MOBILE_REGEXP);

	public boolean isValid(String celular, ConstraintValidatorContext context) {
		return celular == null || celular.matches(MOBILE_REGEXP); // null elements are considered valid.
	}

}
