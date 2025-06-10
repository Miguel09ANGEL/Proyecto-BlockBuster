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
	private UsersModel modelo;
	private List<User> usuarios = new ArrayList<>();

	public UserController() {

		this.vista = new UserViews();
		this.modelo = new UsersModel();
	}

	// se muestran todos los usuarios
	public void index() {
		usuarios = modelo.getAll();
		
		vista.RegistroClientes(usuarios);
	}

	// aqui se edita el cliente
	public void editClient(int id) {
		User myUser = modelo.get(id);
		
		vista.EditarCliente(myUser);
	}

	// se muestra los detalles del cliente
	public void clientDetails(int id) {		
		User myUser = modelo.get(id);
		
		TransactionModel tm = new TransactionModel();
		List<Transaction> misTransacciones = tm.getRentalsByUser(id);
		List<Transaction> misVentas = tm.getSimplePurchasesByUser(id);

		vista.DetallesCliente(myUser, (List<Transaction>) misTransacciones, (List<Transaction>) misVentas);
	}
	
	// se muestra la informacion del cliente
	public void clientInformation(int id) {
		User myUser = modelo.get(id);
		
		vista.InformacionCliente(myUser);
	}

}
