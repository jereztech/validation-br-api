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
package com.jereztech.validation.br.util;

import com.jereztech.validation.br.constraints.Mod11Alias;

/**
 * Defines the logic for error detection on identification numbers, verifying
 * check digits according to the routine of Mod11 algorithm.
 * 
 * @author Joel Jerez
 *
 */
public class CheckDigit {

	private static final int RADIX = 10;

	/**
	 * Gets the check digit from the purported parameter using Mod11 algorithm.
	 * 
	 * @return the check digit
	 */
	public static char getCheckDigit(String purported, int maxSequence, Mod11Alias[] mod11Aliases) {
		int checkDigit = -1, sum = 0;
		for (int i = purported.length() - 1, sequence = 2; i >= 0; i--, sequence++) {
			sum += sequence * Character.getNumericValue(purported.charAt(i));
			if (sequence == maxSequence) {
				sequence = 1; // restarts the sequence
			}
		}
		checkDigit = 11 - (sum % 11);
		return checkDigit >= 10 ? getCheckDigitAlias(purported, mod11Aliases) : Character.forDigit(checkDigit, RADIX);
	}

	private static char getCheckDigitAlias(String purported, Mod11Alias[] mod11Aliases) {
		char mod11Alias = '0';
		if (mod11Aliases != null && mod11Aliases.length > 0) {
			for (Mod11Alias item : mod11Aliases) {
				if (item.regexp() != null && !item.regexp().isEmpty() && purported.matches(item.regexp())) {
					mod11Alias = item.alias();
					break;
				}
			}
		}
		return mod11Alias;
	}

}
