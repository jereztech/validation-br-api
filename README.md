<!-- Copyright (C) 2018 Joel Jerez This program is free software: you can 
	redistribute it and/or modify it under the terms of the GNU General Public 
	License as published by the Free Software Foundation, either version 3 of 
	the License, or (at your option) any later version. This program is distributed 
	in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even 
	the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
	See the GNU General Public License for more details. You should have received 
	a copy of the GNU General Public License along with this program. If not, 
	see <http://www.gnu.org/licenses/>. -->
# validation-br-api
Brazilian Personal Data Validation API compatible with the Java Bean Validation API.

[![Maven Central](https://img.shields.io/maven-central/v/com.jereztech/validation-br-api.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.jereztech%22%20AND%20a:%22validation-br-api%22)
[![License-GPLv3](https://img.shields.io/badge/License-GPLv3-blue.svg?style=flat)](https://www.gnu.org/licenses/gpl.html)
![build-passing](https://img.shields.io/badge/build-passing-brightgreen.svg?style=flat)
![contributions-welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)
[![Donate-PayPal](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=joel%2ejerez%40jereztech%2ecom&lc=BR&item_name=Joel%20Jerez&item_number=DONATION&currency_code=BRL&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

## Overview
Some identification numbers in Brazil use check digits to guarantee the document authenticity. This API helps you to validate Brazilian Personal Data according to official regulations. Most institutions use standardized masks in these documents to improve the user experience, to facilitate programming, it's possible to validate the data with or without masks. The code is based on annotations and can be used easily.

## Example
```
  @CPF
  private String cpf;
  
  @CNPJ
  private String cnpj;
  
  @CPFCNPJ // CPF or CNPJ in the same field
  private String cpfCnpj;
```

## Annotation Types Summary
### AgenciaBancaria
The annotated element must contain 4 numeric characters (with leading zeros as needed) plus 1 check digit (not required); Allowed formats are '9999', '9999A' or '9999-A'.
### Celular	
The annotated element must be an 11-digit number, the first 2 digits being the area code (DDD), and the remaining 9 digits referring to the subscriber number; Accepts the following formats '99900000000' or '(99) 90000-0000'. Only the area codes of the Brazilian Numbering Plan defined by ANATEL are allowed.
### CNPJ	
The annotated element must be a 14-digit number, with the following formats '99999999999999' or '99.999.999/9999-99', being 8 base digits, 4 digits for the serial number and 2 digits for the check digits.
### ContaCorrente	
The annotated element must contain up to 20 numeric characters plus 1 check digit (not required); The allowed formats are '99999999999999999999' or '99999999999999999999-A'. In the presence of the check digit, the use of the hyphen character (‐) is mandatory.
### CPF	
The annotated element must be an 11-digit number, with the following formats '99999999999' or '999.999.999-99', being 9 base digits and 2 digits for the check digits.
### CPFCNPJ	
The annotated element must match [CPF](https://github.com/jereztech/validation-br-api#cpf) or [CNPJ](https://github.com/jereztech/validation-br-api#cnpj).
### CTPS	
The annotated element must contain 7 numeric characters (with leading zeros as needed).
### NISPISPASEP	
The annotated element must be an 11-digit number, 10 base digits, and 1 check digit with the following formats '99999999999' or '999.99999.99-9'.
### Passaporte	
The annotated element must be an alphanumeric code in the following format 'AA999999'.
### Telefone	
The annotated element must match [Celular](https://github.com/jereztech/validation-br-api#celular) or [TelefoneFixo](https://github.com/jereztech/validation-br-api#telefonefixo).
### TelefoneFixo	
The annotated element must be a 10-digit number, the first 2 digits being the area code (DDD), and the remaining 8 digits referring to the subscriber number; Accepts the following formats '9930000000' or '(99) 3000-0000'. Only the area codes of the Brazilian Numbering Plan defined by ANATEL are allowed.
### TituloEleitoral	
The annotated element must be a 12-digit number, being 8 base digits, 2 digits for the Federal State and 2 digits for the check digits.

## The ContaCorrente Case
Because it's standard by the Central Bank of Brazil, the ContaCorrente field can contain up to 20 numeric characters. To validate specific cases, it's necessary to configure some parameters of the annotation. For example, to validate a Banco do Brasil ContaCorrente's field, is mandatory the following configuration:
```
  @ContaCorrente(mod11Aliases = { @Mod11Alias(alias = 'X', regexp = "[0-9]{4,11}") })
  private String contaCorrente;
```
Why? Because Banco do Brasil use an X character as part of the ContaCorrente checksum, and the max length for this field is 11.
This logic is applied to AgenciaBancaria field in the same way.

## Maven
```xml
  <dependency>
      <groupId>com.jereztech</groupId>
      <artifactId>validation-br-api</artifactId>
      <version>1.3</version>
  </dependency>
```

## Gradle
```
  compile 'com.jereztech:validation-br-api:1.3'
```

## FAQ
### How can I validate my Value Object coming from the front-end?
The fastest way is using the @javax.validation.Valid annotation with your Value Object. The @javax.validation.Valid annotation marks a method parameter for validation cascading (applied recursively). Constraints defined on the object and its properties are be validated when the method parameter is validated. If the validation does not succeed, an HTTP 400 Bad Request error is generated. For example:
```
  @PutMapping(path = "/saveDataBr")
  public ResponseEntity<DataBrVO> saveDataBr(@RequestBody @Valid DataBrVO dataBrVO) {}
```
### How can I implement my Value Object to use these annotations?
An excellent choice is JAXB, which allows the most used formats such as XML and JSON. For example:
```
  @XmlRootElement
  @XmlAccessorType(XmlAccessType.FIELD)
  public class DataBrVO {
  
    @CPF
    @NotNull //you can use another annotations from the javax.validation.constraints package
    @XmlAttribute
    private String cpf;
    
    @Valid //validation applied recursively
    @XmlElement
    private OtherDataBrVO otherDataBrVO;
    
  }
```

## Bugs Report
If you think you have found a bug, please file an issue in the [validation-br-api Issue Tracker](https://github.com/jereztech/validation-br-api/issues)

## License
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
