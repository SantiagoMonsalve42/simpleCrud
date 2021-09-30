package com.springsimplespasos.universidad.universidadbackend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class UniversidadBackendApplicationTests {

	Calculadora calculadora = new Calculadora();

	@Test
	@DisplayName("Sumna de valor a y b")
	void sumaDeValores() {
		//given
		int a = 2, b= 5;
		//when
		int resul = calculadora.sumar(a,b);
		//then
		int resultExpect = 7;
		assertThat(resul).isEqualTo(resultExpect);
	}
	@Test
	@DisplayName("TEST DESACTIVADO")
	@Disabled("TEST DESACTIVADO")
	void testDesactivado(){

	}
	class Calculadora{
		int sumar(int a,int b){
			return a+b;
		}
	}

}
