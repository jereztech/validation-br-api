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

import com.jereztech.validation.br.constraints.CPFCNPJ;

/**
 * Defines the logic to validate a given constraint CPFCNPJ for a given object
 * type String.
 * 
 * @author Joel Jerez
 *
 */
public class CPFCNPJValidator implements ConstraintValidator<CPFCNPJ, String> {

	public static final int CPFCNPJ_MAX_COUNT = 2;
	public static final int CNPJ_MAX_SEQ = 9;
	public static final String CPF_REGEXP = "[0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2}";
	public static final String CNPJ_REGEXP = "[0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2}";

	public boolean isValid(String cpfcnpj, ConstraintValidatorContext context) {
		if (cpfcnpj != null) {
			int checkDigitIndex = -1, beginIndex = 0, endIndex = -1, maxSequence = Integer.MAX_VALUE;
			boolean isValid = cpfcnpj.matches(CPF_REGEXP) && Mod11Validator.check(cpfcnpj, CPFCNPJ_MAX_COUNT,
					checkDigitIndex, beginIndex, endIndex, maxSequence, null);
			if (!isValid) {
				isValid = cpfcnpj.matches(CNPJ_REGEXP) && Mod11Validator.check(cpfcnpj, CPFCNPJ_MAX_COUNT,
						checkDigitIndex, beginIndex, endIndex, CNPJ_MAX_SEQ, null);
			}
			return isValid;
		}
		return true;// null elements are considered valid.
	}

}
