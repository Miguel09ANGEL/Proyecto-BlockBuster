package controller;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import views.HomeView;


public class UserController {

	private HomeView vista;
	private List<User> usuarios = new ArrayList<>();
	
	public UserController() {
		
		vista = new HomeView();
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
}
