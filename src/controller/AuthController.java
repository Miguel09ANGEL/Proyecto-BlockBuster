package controller;

import java.util.ArrayList;
import java.util.List;

import models.Admins;
import models.AuthModel;
import views.AuthViews;
import views.HomeView;

public class AuthController {
	
	private AuthViews vista;
	private List<Admins> administrador = new ArrayList<>();

	public AuthController() { 
		
		vista = new AuthViews();
	
	}	
	
	public void login() {

		AuthModel am = new AuthModel();

		administrador = am.getAll();

		vista.login(administrador);;

	}
	

}
