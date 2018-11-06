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

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import com.jereztech.validation.br.constraints.Mod11Alias;
import com.jereztech.validation.br.constraints.Mod11;
import com.jereztech.validation.br.util.CheckDigit;

/**
 * Defines the logic to validate a given constraint Mod11 for a given object
 * type String.
 * 
 * @author Joel Jerez
 *
 */
public class Mod11Validator implements ConstraintValidator<Mod11, String> {

	public static final String REPEAT_REGEXP = "(\\p{Alnum})\\1+"; // 00000, 11111, 22222, AAAAA, BBBBB, etc.

	public boolean isValid(String purported, ConstraintValidatorContext context) {
		if (purported != null) {
			ConstraintValidatorContextImpl contextImpl = (ConstraintValidatorContextImpl) context;
			return isValid(purported, contextImpl.getConstraintDescriptor().getAttributes());
		}
		return true;// null elements are considered valid.
	}

	private boolean isValid(String purported, Map<String, Object> attributes) {
		int count = Integer.parseInt(attributes.get("count").toString()),
				checkDigitIndex = Integer.parseInt(attributes.get("checkDigitIndex").toString()),
				beginIndex = Integer.parseInt(attributes.get("beginIndex").toString()),
				endIndex = Integer.parseInt(attributes.get("endIndex").toString()),
				maxSequence = Integer.parseInt(attributes.get("maxSequence").toString());
		Mod11Alias[] mod11Aliases = (Mod11Alias[]) attributes.get("mod11Aliases");
		return check(purported, count, checkDigitIndex, beginIndex, endIndex, maxSequence, mod11Aliases);
	}

	public static boolean check(String purported, int count, int checkDigitIndex, int beginIndex, int endIndex,
			int maxSequence, Mod11Alias[] mod11Aliases) {
		purported = purported.replaceAll("\\W", "");// removes the mask
		if (purported.matches("\\w+") && !purported.matches(REPEAT_REGEXP)) {
			if (count < 0 || count > purported.length() - 2) {
				throw new IllegalArgumentException("the count argument does not have a valid value");
			}
			endIndex = endIndex < 0 ? purported.length() - count : endIndex;
			String checkDigit = getCheckDigit(purported.substring(beginIndex, endIndex), count, maxSequence,
					mod11Aliases);
			checkDigitIndex = checkDigitIndex < 0 ? purported.length() - count : checkDigitIndex;
			return purported.substring(checkDigitIndex, checkDigitIndex + count).equals(checkDigit);
		}
		return false;
	}

	private static String getCheckDigit(String purported, int count, int maxSequence, Mod11Alias[] mod11Aliases) {
		StringBuilder purportedBuilder = new StringBuilder(purported), checkDigitBuilder = new StringBuilder(0);
		char checkDigit = '\u0000';
		for (int i = 0; i < count; i++) {
			purported = purportedBuilder.toString();
			checkDigit = CheckDigit.getCheckDigit(purported, maxSequence, mod11Aliases);
			purportedBuilder.append(checkDigit);
			checkDigitBuilder.append(checkDigit);
		}
		return checkDigitBuilder.toString();
	}

}
