package controller;

import views.HomeView;

public class AuthController {
	
	
	
	public AuthController() { 
		
		HomeView = new PanelAdministrador();
	}
	
	public void login() {
		
		HomeView.PanelAdministrador();
	}
	
	

}
