package net.globalapp.login;
import java.io.IOException;

import net.globalapp.common.BaseTest;

public class LoginMailAndCodeTest extends  BaseTest {

	private static final String PASSWORD = "/html/body/section/header/section/form/div/div/input[1]";
	private static final String EMAIL = "/html/body/section/header/section/form/div/div/input[1]";
	private static final String BOTON_DE_OK = "//*[@id='loginForm']/div/div/input[2]";

	public void executeTest() throws IOException {
		escribirEnElCampo(EMAIL, "ecalderon@globalappssolutions.com");
		esperarEstosSegundos(5);
		tomarCapturaDePantallaYGuardelaComo("paginaDeLogin");
		clickAl(BOTON_DE_OK);
		escribirEnElCampo(PASSWORD, "1234");
		tomarCapturaDePantallaYGuardelaComo("paginaDeInfoDeUsuarioParaLogin");
		clickAl(BOTON_DE_OK);
		revisarSiElTagTieneElValor("body", "Welcome");
		tomarCapturaDePantallaYGuardelaComo("usuarioYaLogueado");
		
	}

	@Override
	public String getInitialPath() {
		return "/globalapp/";
	}

}
