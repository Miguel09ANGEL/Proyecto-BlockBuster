package controller;

import views.HomeView;

public class AuthController {
	
	private HomeView vista;

	public AuthController() { 
		
		vista = new HomeView();
	}
	
	public void login() {
		
		vista.PanelAdministrador();
	}
	
	

}
