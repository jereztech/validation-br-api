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
package com.jereztech.validation.br.test;

import javax.validation.Validation;
import javax.validation.Validator;

import junit.framework.TestCase;

/**
 * Defines the logic for testing cases.
 * 
 * <p>
 * LIMITATION OF LIABILITY: some data from this TestCase were obtained from the
 * site https://www.4devs.com.br; The merit of this data is exclusive to this
 * provider. The misuse of any data present in this TestCase is the sole
 * responsibility of the user.
 * 
 * @author Joel Jerez
 *
 */
public class ValidatorBrTestCase extends TestCase {

	private Validator validator;
	private DadosBrVO dadosBrVO;

	@Override
	public void setUp() throws Exception {
		dadosBrVO = new DadosBrVO();
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public void testCPF() {// CPF null
		assertTrue(validator.validateProperty(dadosBrVO, "cpf").isEmpty());
		dadosBrVO.setCpf("67339011889");// CPF without mask
		assertTrue(validator.validateProperty(dadosBrVO, "cpf").isEmpty());
		dadosBrVO.setCpf("980.267.200-91");// CPF with mask
		assertTrue(validator.validateProperty(dadosBrVO, "cpf").isEmpty());
		dadosBrVO.setCpf("980.267.200-00");// CheckDigit invalid
		assertFalse(validator.validateProperty(dadosBrVO, "cpf").isEmpty());
		dadosBrVO.setCpf("00000000000");// CPF invalid
		assertFalse(validator.validateProperty(dadosBrVO, "cpf").isEmpty());
	}

	public void testCNPJ() {// CNPJ null
		assertTrue(validator.validateProperty(dadosBrVO, "cnpj").isEmpty());
		dadosBrVO.setCnpj("15631162000103");// CNPJ without mask
		assertTrue(validator.validateProperty(dadosBrVO, "cnpj").isEmpty());
		dadosBrVO.setCnpj("81.904.522/0001-10");// CNPJ with mask
		assertTrue(validator.validateProperty(dadosBrVO, "cnpj").isEmpty());
		dadosBrVO.setCnpj("81.904.522/0001-00");// CheckDigit invalid
		assertFalse(validator.validateProperty(dadosBrVO, "cnpj").isEmpty());
		dadosBrVO.setCnpj("00000000000000");// CNPJ invalid
		assertFalse(validator.validateProperty(dadosBrVO, "cnpj").isEmpty());
	}

	public void testCPFCNPJ() {// CPFCNPJ null
		assertTrue(validator.validateProperty(dadosBrVO, "cpfCnpj").isEmpty());
		dadosBrVO.setCpfCnpj("67897941825");// CPF without mask
		assertTrue(validator.validateProperty(dadosBrVO, "cpfCnpj").isEmpty());
		dadosBrVO.setCpfCnpj("19.166.973/0001-23");// CNPJ with mask
		assertTrue(validator.validateProperty(dadosBrVO, "cpfCnpj").isEmpty());
	}

	public void testPassaporte() {// Passaporte null
		assertTrue(validator.validateProperty(dadosBrVO, "passaporte").isEmpty());
		dadosBrVO.setPassaporte("AA234567");// Passaporte OK
		assertTrue(validator.validateProperty(dadosBrVO, "passaporte").isEmpty());
		dadosBrVO.setPassaporte("AA000000");// Passaporte invalid
		assertFalse(validator.validateProperty(dadosBrVO, "passaporte").isEmpty());
	}

	public void testTituloEleitoral() {// TituloEleitoral null
		assertTrue(validator.validateProperty(dadosBrVO, "tituloEleitoral").isEmpty());
		dadosBrVO.setTituloEleitoral("661656510191");// TituloEleitoral OK
		assertTrue(validator.validateProperty(dadosBrVO, "tituloEleitoral").isEmpty());
		dadosBrVO.setTituloEleitoral("000000000000");// TituloEleitoral invalid
		assertFalse(validator.validateProperty(dadosBrVO, "tituloEleitoral").isEmpty());
	}

	public void testCelular() {// Celular null
		assertTrue(validator.validateProperty(dadosBrVO, "celular").isEmpty());
		dadosBrVO.setCelular("11981034919");// Celular without mask
		assertTrue(validator.validateProperty(dadosBrVO, "celular").isEmpty());
		dadosBrVO.setCelular("(11) 98617-8305");// Celular with mask
		assertTrue(validator.validateProperty(dadosBrVO, "celular").isEmpty());
		dadosBrVO.setCelular("00000000000");// Celular invalid
		assertFalse(validator.validateProperty(dadosBrVO, "celular").isEmpty());
	}

	public void testTelefoneFixo() {// TelefoneFixo null
		assertTrue(validator.validateProperty(dadosBrVO, "telefoneFixo").isEmpty());
		dadosBrVO.setTelefoneFixo("1136809060");// TelefoneFixo without mask
		assertTrue(validator.validateProperty(dadosBrVO, "telefoneFixo").isEmpty());
		dadosBrVO.setTelefoneFixo("(61) 2719-7057");// TelefoneFixo with mask
		assertTrue(validator.validateProperty(dadosBrVO, "telefoneFixo").isEmpty());
		dadosBrVO.setTelefoneFixo("0000000000");// TelefoneFixo invalid
		assertFalse(validator.validateProperty(dadosBrVO, "telefoneFixo").isEmpty());
	}

	public void testTelefone() {// Telefone null
		assertTrue(validator.validateProperty(dadosBrVO, "telefone").isEmpty());
		dadosBrVO.setTelefone("9236018004");// TelefoneFixo without mask
		assertTrue(validator.validateProperty(dadosBrVO, "telefone").isEmpty());
		dadosBrVO.setTelefone("(84) 98287-8247");// Celular with mask
		assertTrue(validator.validateProperty(dadosBrVO, "telefone").isEmpty());
		dadosBrVO.setTelefone("0000000000");// Telefone invalid
		assertFalse(validator.validateProperty(dadosBrVO, "telefone").isEmpty());
	}

	public void testAgenciaBancaria() {// Agencia null
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancaria").isEmpty());
		dadosBrVO.setAgenciaBancaria("2254");// Agencia without CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancaria").isEmpty());
		dadosBrVO.setAgenciaBancaria("1230-0");// Agencia with CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancaria").isEmpty());
		dadosBrVO.setAgenciaBancaria("1230-9");// Agencia with invalid CheckDigit
		assertFalse(validator.validateProperty(dadosBrVO, "agenciaBancaria").isEmpty());
		dadosBrVO.setAgenciaBancaria("0000");// Agencia invalid
		assertFalse(validator.validateProperty(dadosBrVO, "agenciaBancaria").isEmpty());
	}

	public void testAgenciaBancariaX() {// Agencia null
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancariaX").isEmpty());
		dadosBrVO.setAgenciaBancariaX("0322");// Agencia without CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancariaX").isEmpty());
		dadosBrVO.setAgenciaBancariaX("1230-X");// Agencia with CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "agenciaBancariaX").isEmpty());
		dadosBrVO.setAgenciaBancariaX("1230-Z");// Agencia with invalid CheckDigit
		assertFalse(validator.validateProperty(dadosBrVO, "agenciaBancariaX").isEmpty());
		dadosBrVO.setAgenciaBancariaX("0000");// Agencia invalid
		assertFalse(validator.validateProperty(dadosBrVO, "agenciaBancariaX").isEmpty());
	}

	public void testContaCorrente() {// ContaCorrente null
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrente").isEmpty());
		dadosBrVO.setContaCorrente("02226371203");// ContaCorrente without CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrente").isEmpty());
		dadosBrVO.setContaCorrente("15521799-2");// ContaCorrente with CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrente").isEmpty());
		dadosBrVO.setContaCorrente("15521799-9");// ContaCorrente with invalid CheckDigit
		assertFalse(validator.validateProperty(dadosBrVO, "contaCorrente").isEmpty());
		dadosBrVO.setContaCorrente("00000000");// ContaCorrente invalid
		assertFalse(validator.validateProperty(dadosBrVO, "contaCorrente").isEmpty());
	}

	public void testContaCorrenteX() {// ContaCorrente null
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrenteX").isEmpty());
		dadosBrVO.setContaCorrenteX("1266304");// ContaCorrente without CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrenteX").isEmpty());
		dadosBrVO.setContaCorrenteX("1028141-X");// ContaCorrente with CheckDigit
		assertTrue(validator.validateProperty(dadosBrVO, "contaCorrenteX").isEmpty());
		dadosBrVO.setContaCorrenteX("1028141-Z");// ContaCorrente with invalid CheckDigit
		assertFalse(validator.validateProperty(dadosBrVO, "contaCorrenteX").isEmpty());
		dadosBrVO.setContaCorrenteX("00000000");// ContaCorrente invalid
		assertFalse(validator.validateProperty(dadosBrVO, "contaCorrenteX").isEmpty());
	}

	public void testNISPISPASEP() {// NISPISPASEP null
		assertTrue(validator.validateProperty(dadosBrVO, "nispispasep").isEmpty());
		dadosBrVO.setNispispasep("54074854304");// NISPISPASEP without mask
		assertTrue(validator.validateProperty(dadosBrVO, "nispispasep").isEmpty());
		dadosBrVO.setNispispasep("407.72078.61-0");// NISPISPASEP with mask
		assertTrue(validator.validateProperty(dadosBrVO, "nispispasep").isEmpty());
		dadosBrVO.setNispispasep("407.72078.61-9");// NISPISPASEP with invalid CheckDigit
		assertFalse(validator.validateProperty(dadosBrVO, "nispispasep").isEmpty());
		dadosBrVO.setNispispasep("00000000000");// NISPISPASEP invalid
		assertFalse(validator.validateProperty(dadosBrVO, "nispispasep").isEmpty());
	}

	public void testCTPS() {// CTPS null
		assertTrue(validator.validateProperty(dadosBrVO, "ctps").isEmpty());
		dadosBrVO.setCtps("0658123");// CTPS ok
		assertTrue(validator.validateProperty(dadosBrVO, "ctps").isEmpty());
		dadosBrVO.setCtps("0000000");// CTPS invalid
		assertFalse(validator.validateProperty(dadosBrVO, "ctps").isEmpty());
	}

}
