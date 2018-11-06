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

import static com.jereztech.validation.br.validator.AgenciaBancariaValidator.AGENCIA_REGEXP;

import com.jereztech.validation.br.constraints.AgenciaBancaria;
import com.jereztech.validation.br.constraints.CNPJ;
import com.jereztech.validation.br.constraints.CPF;
import com.jereztech.validation.br.constraints.CPFCNPJ;
import com.jereztech.validation.br.constraints.CTPS;
import com.jereztech.validation.br.constraints.Celular;
import com.jereztech.validation.br.constraints.ContaCorrente;
import com.jereztech.validation.br.constraints.Mod11Alias;
import com.jereztech.validation.br.constraints.NISPISPASEP;
import com.jereztech.validation.br.constraints.Passaporte;
import com.jereztech.validation.br.constraints.Telefone;
import com.jereztech.validation.br.constraints.TelefoneFixo;
import com.jereztech.validation.br.constraints.TituloEleitoral;

public class DadosBrVO {

	@CPF
	private String cpf;
	@CNPJ
	private String cnpj;
	@CPFCNPJ // CPF or CNPJ in the same field
	private String cpfCnpj;
	@Passaporte
	private String passaporte;
	@TituloEleitoral
	private String tituloEleitoral;
	@Celular
	private String celular;
	@TelefoneFixo
	private String telefoneFixo;
	@Telefone // Celular or TelefoneFixo in the same field
	private String telefone;
	@AgenciaBancaria
	private String agenciaBancaria;
	@AgenciaBancaria(mod11Aliases = { @Mod11Alias(alias = 'X', regexp = AGENCIA_REGEXP) })
	private String agenciaBancariaX;
	@ContaCorrente
	private String contaCorrente;
	@ContaCorrente(mod11Aliases = { @Mod11Alias(alias = 'X', regexp = "[0-9]{4,11}") })
	private String contaCorrenteX;
	@NISPISPASEP
	private String nispispasep;
	@CTPS
	private String ctps;

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setAgenciaBancaria(String agenciaBancaria) {
		this.agenciaBancaria = agenciaBancaria;
	}

	public void setAgenciaBancariaX(String agenciaBancariaX) {
		this.agenciaBancariaX = agenciaBancariaX;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public void setContaCorrenteX(String contaCorrenteX) {
		this.contaCorrenteX = contaCorrenteX;
	}

	public void setNispispasep(String nispispasep) {
		this.nispispasep = nispispasep;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}
}
