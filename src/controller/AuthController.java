package controller;

import java.util.ArrayList;
import java.util.List;

import models.Admins;
import models.AuthModel;
import views.AuthViews;
import views.HomeView;
import views.TransactionView;

public class AuthController {
	
	private AuthViews vista;
	private List<Admins> administrador = new ArrayList<>();

	public AuthController() { 
		this.vista = new AuthViews();
	}	
	
	public void login() {	
		AuthModel am = new AuthModel();
		administrador = am.getAll();
		vista.login(administrador);;
	}
	
}
