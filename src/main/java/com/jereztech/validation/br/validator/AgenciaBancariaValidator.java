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

import static com.jereztech.validation.br.validator.Mod11Validator.REPEAT_REGEXP;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import com.jereztech.validation.br.constraints.AgenciaBancaria;
import com.jereztech.validation.br.constraints.Mod11Alias;

/**
 * Defines the logic to validate a given constraint AgenciaBancaria for a given
 * object type String.
 * 
 * @author Joel Jerez
 *
 */
public class AgenciaBancariaValidator implements ConstraintValidator<AgenciaBancaria, String> {

	public static final String AGENCIA_REGEXP = "[0-9]{4}(\\-?\\p{Alnum})?";
	private final int AGENCIA_LENGTH = 4;

	public boolean isValid(String agenciaBancaria, ConstraintValidatorContext context) {
		if (agenciaBancaria != null) {
			ConstraintValidatorContextImpl contextImpl = (ConstraintValidatorContextImpl) context;
			Map<String, Object> attributes = contextImpl.getConstraintDescriptor().getAttributes();
			int count = 1, checkDigitIndex = -1, beginIndex = 0, endIndex = -1, maxSequence = Integer.MAX_VALUE;
			boolean isValid = !agenciaBancaria.replaceAll("\\W", "").matches(REPEAT_REGEXP);
			return isValid && (agenciaBancaria.length() == AGENCIA_LENGTH
					|| Mod11Validator.check(agenciaBancaria, count, checkDigitIndex, beginIndex, endIndex, maxSequence,
							(Mod11Alias[]) attributes.get("mod11Aliases")));
		}
		return true;// null elements are considered valid.
	}

}
