package controller;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import views.AuthViews;
import views.HomeView;
import views.UserViews;

public class UserController {

	private UserViews vista;
	private List<User> usuarios = new ArrayList<>();

	public UserController() {

		vista = new UserViews();
	}

	public void index() {

		UsersModel um = new UsersModel();

		usuarios = um.getAll();

		vista.RegistroClientes(usuarios);

	}

	public void update(int id) {

		UsersModel um = new UsersModel();

		User myUser = um.get(id);

		vista.EditarCliente(myUser);

	}

	public void update2(int id) {

		UsersModel um = new UsersModel();

		User myUser = um.get(id);

		vista.DetallesCliente(myUser);

	}
	
	public void update3(int id) {
		
		UsersModel um = new UsersModel();

		User myUser = um.get(id);

		vista.InformacionCliente(myUser);

		
	}

}
