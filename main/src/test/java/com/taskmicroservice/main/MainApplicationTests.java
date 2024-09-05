package com.taskmicroservice.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmicroservice.main.Controllers.TaskController;
import com.taskmicroservice.main.DTO.TaskEntityPostBody;
import com.taskmicroservice.main.ServicesImpl.TaskServiceImpl;

import jakarta.inject.Inject;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MainApplicationTests {
	private Logger log = LoggerFactory.getLogger(getClass());

	private MockMvc controllerWeb;

	@Mock
	List<Integer> testIntegers;

	@Spy
	List<String> savingLikeList = new ArrayList<String>();

	@Captor
	ArgumentCaptor<String> captorPrueba;

	@InjectMocks
	TaskController controllerTasking;
	
	@Mock
	TaskServiceImpl taskService;

	@BeforeEach
	public void executeBefore(){
		log.info("ESTO DEBE EJECUTARSE ANTES DE CADA INICIACIÓN POR TEST");
		 MockitoAnnotations.openMocks(this);
		this.controllerWeb = MockMvcBuilders.standaloneSetup(controllerTasking).build();
	}

	/**LOS display names son para identificarlos en ejecución*/
	@Test
	@DisplayName("checkidis")
	public void assertTesting(){
		assertEquals(1, 1);
	}

	@Test
	@DisplayName("WURA WURA WURAAAAAA")
    public void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test deberia marcar error");
    }

	@Test
	@DisplayName("USING MOCKITO EXAMPLE STARTER PACK")
	public void it_should_add_12_to_array(){
		testIntegers.add(12);
		/**MOCKITO PERMITE VERIFICAR LA EJECUCIÓN CORRECTA DE LOS MÉTODOS CON VALORES PREDETERMINADOS,
		 * OSEA QUE PRODUCTO DE PROCESOS PODEMOS VERIFICAR SI UN MÉTODO SE EJECUTA CON EL INPUT DESEADO O SI AL MENOS SE EJECUT+O
		 */
		Mockito.verify(testIntegers).add(12);

		/**EL SAVING LIKE LIST PERTENECE AL ANNOTATION @Spy*/
		savingLikeList.add("STRING PRUEBA");
	//	Mockito.verify(savingLikeList).add("REMOKE");
		Mockito.verify(savingLikeList).add("STRING PRUEBA");
		assertEquals(1,savingLikeList.size());

		/**EL CAPTOR PERTENECE A LA CLASE CAPTOR,DEBIDO A QUE EL CAPTOR
		 * VIGILA TODA LA INSTANCIA DE LA VARIABLE, SE INDICA EL MOMENTO EN EL QUE DEBE SER CAPTURADO
		 */
		savingLikeList.add("OTRO BRODER");
		Mockito.verify(savingLikeList,times(2)).add(captorPrueba.capture());
		assertEquals("OTRO BRODER", captorPrueba.getValue());
	}

	@Test
	@DisplayName("TESTEAR CONTROLADORES")
	public void probar_api_añadir_task() throws Exception{
		TaskEntityPostBody postBodyTest = new TaskEntityPostBody("PRUEBOTA 2","DADSDSAA",1);

		ObjectMapper objectMapper = new ObjectMapper();
        String productoJson = objectMapper.writeValueAsString(postBodyTest);
		log.info(productoJson+" AQUI ESTA EL POSDT BODY DE LA PETICION A PROBAR");

		this.controllerWeb.perform(post("/api/v1/mx/tasks/create/new")
		.contentType(MediaType.APPLICATION_JSON)
		.content(productoJson)).andExpect(status().isOk());

		MvcResult resultado = this.controllerWeb.perform(post("/api/v1/mx/tasks/create/new")
		.contentType(MediaType.APPLICATION_JSON)
		.content(productoJson)).andReturn();

		log.info("DEBUG RESPONSE "+resultado.getResponse().getContentAsString().length());
	}
}
