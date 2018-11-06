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

import com.jereztech.validation.br.constraints.ContaCorrente;
import com.jereztech.validation.br.constraints.Mod11Alias;

/**
 * Defines the logic to validate a given constraint ContaCorrente for a given
 * object type String.
 * 
 * @author Joel Jerez
 *
 */
public class ContaCorrenteValidator implements ConstraintValidator<ContaCorrente, String> {

	public static final String CONTA_CORRENTE_REGEXP = "[0-9]{1,20}(\\-\\p{Alnum})?";
	private final String HYPHEN = "-";

	public boolean isValid(String contaCorrente, ConstraintValidatorContext context) {
		if (contaCorrente != null) {
			ConstraintValidatorContextImpl contextImpl = (ConstraintValidatorContextImpl) context;
			Map<String, Object> attributes = contextImpl.getConstraintDescriptor().getAttributes();
			int count = 1, checkDigitIndex = -1, beginIndex = 0, endIndex = -1, maxSequence = Integer.MAX_VALUE;
			boolean isValid = !contaCorrente.replaceAll("\\W", "").matches(REPEAT_REGEXP);
			return isValid && (!contaCorrente.contains(HYPHEN) || Mod11Validator.check(contaCorrente, count,
					checkDigitIndex, beginIndex, endIndex, maxSequence, (Mod11Alias[]) attributes.get("mod11Aliases")));
		}
		return true;// null elements are considered valid.
	}

}
