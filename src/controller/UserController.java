package controller;

import java.util.ArrayList;
import java.util.List;

import models.Transaction;
import models.TransactionModel;
import models.User;
import models.UsersModel;
import views.AuthViews;
import views.HomeView;
import views.UserViews;


public class UserController {

	private UserViews vista;
	private List<User> usuarios = new ArrayList<>();

	public UserController() {

		this.vista = new UserViews();
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
		
		TransactionModel tm = new TransactionModel();
		List<Transaction> misTransacciones = tm.getRentalsByUser(id);
		List<Transaction> misVentas = tm.getSimplePurchasesByUser(id);


		vista.DetallesCliente(myUser, (List<Transaction>) misTransacciones, (List<Transaction>) misVentas);

	}
	
	public void update3(int id) {
		UsersModel um = new UsersModel();
		User myUser = um.get(id);
		vista.InformacionCliente(myUser);
	}

}
