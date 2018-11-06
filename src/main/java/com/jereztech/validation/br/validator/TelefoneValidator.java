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

import static com.jereztech.validation.br.validator.CelularValidator.MOBILE_REGEXP;
import static com.jereztech.validation.br.validator.TelefoneFixoValidator.FIXO_REGEXP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jereztech.validation.br.constraints.Telefone;

/**
 * Defines the logic to validate a given constraint Telefone for a given object
 * type String.
 * 
 * @author Joel Jerez
 *
 */
public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

	private final String TELEFONE_REGEXP = String.format("%s|%s", MOBILE_REGEXP, FIXO_REGEXP);

	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		return telefone == null || telefone.matches(TELEFONE_REGEXP);// null elements are considered valid.
	}

}
