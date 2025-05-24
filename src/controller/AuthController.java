package controller;

import java.util.ArrayList;
import java.util.List;

import models.Admins;
import models.AdminsModel;
import models.AuthModel;
import views.AuthViews;
import views.HomeView;

public class AuthController {
	
	private AuthViews vista;
	private List<Admins> administrador = new ArrayList<>();


	private AdminsController vista2;

	public AuthController() { 
		
		vista = new AuthViews();
		vista2 = new AdminsController();
	}
	
	public void login() {

		AuthModel am = new AuthModel();

		administrador = am.getAll();

		vista.login(administrador);;

	}
	
	public void indexAdmins() {
		
		vista2.indexAdmins();;
	}
	
	public void framePrueba() {

	}
	
	

}
