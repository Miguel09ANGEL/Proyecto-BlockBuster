package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;
import views.TransactionView;

public class TransactionController {

	private TransactionView vista;
	private VideoGamesModel modeloVideojuego;
	private UsersModel modeloUsuario;
	private List<User> usuarios = new ArrayList<>();

	
	public TransactionController() {
		this.vista = new TransactionView();
		this.modeloVideojuego = new VideoGamesModel();
		this.modeloUsuario = new UsersModel();
	}

	public void updateVideogames(int id) {
		vista.AdministradorRentaCompra();
	}

	//	Muestra la vista con la lista de videojuegos disponibles para renta.
	public void rentalIndex() {
		List<VideoGames> videoGamesList = modeloVideojuego.getAllVideogames()
				;
		vista.Renta(videoGamesList);
	}

	// 	muestra la vista con la lista de videojuegos disponibles para venta.
	public void salesIndex() {
		List<VideoGames> videoGamesList = modeloVideojuego.getAllVideogames();
		
		vista.Compra(videoGamesList);
	}

	// 	muestra la operación de renta para un videojuego y un usuario en específico.
	public void rentalOperation(int idVideogame, int idConstumer) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		User myUser = modeloUsuario.get(idConstumer);
		
		vista.OperacionRentar( myUser,myVideogame);
	}
	
	//	muestra los detalles de la renta antes de confirmarla.
	public void rentalDetails(int idConstumer, int idVideogame, Date fechaDevolucion) {
		User myUser = modeloUsuario.get(idConstumer);
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		
		vista.DetallesRenta(myUser ,myVideogame, fechaDevolucion);
	}
	
	//	 muestra la operación de venta para un videojuego y un usuario específico.
	public void salesOperation(int idVideogame, int idConstumer) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		User myUser = modeloUsuario.get(idConstumer);
		
		vista.OperacionComprar(myVideogame,myUser);
	}
	
	// 	 muestra los detalles de la venta antes de confirmar la compra.
	public void indexDetallesCompra(int idConstumer, int idVideogame) {
		User myUser = modeloUsuario.get(idConstumer);
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		
		vista.DetallesCompra(myUser ,myVideogame);
	}
	
	// muestra la vista de selección de cliente para una renta específica.
	public void selectCustomerRent(int idVideogame) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		usuarios = modeloUsuario.getAll();
		
		vista.SeleccionClienteRenta(myVideogame, usuarios);
	}
	
	//	 Muestra la vista de selección de cliente para una venta específica.
	public void selectCustomerSale(int idVideogame) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		usuarios = modeloUsuario.getAll();
		
		vista.SeleccionClienteVenta(myVideogame, usuarios);
	}

}
