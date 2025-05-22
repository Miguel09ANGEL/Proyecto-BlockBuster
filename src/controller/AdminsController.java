package controller;

import java.util.ArrayList;
import java.util.List;

import models.Admins;
import models.AdminsModel;
import models.User;
import models.UsersModel;
import views.AuthViews;
import views.HomeView;

public class AdminsController {

	private HomeView vista;
	private List<Admins> administrador = new ArrayList<>();
	public AdminsController() {

		vista = new HomeView();
	}

	public void indexAdmins() {

		AdminsModel am = new AdminsModel();

		administrador = am.getAll();

		vista.PanelAdministrador(administrador);;

	}
}
