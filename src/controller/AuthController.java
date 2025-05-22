package controller;

import views.HomeView;

public class AuthController {
	
	private HomeView vista;
	private AdminsController vista2;
	VideogamesController lt = new VideogamesController();

	public AuthController() { 
		
		vista = new HomeView();
		vista2 = new AdminsController();
	}
	
	public void login() {
		
		vista2.indexAdmins();;
	}
	
	public void framePrueba() {
//		vista.InformacionCliente();

//		lt.indexVideoGames();
	}
	
	

}
