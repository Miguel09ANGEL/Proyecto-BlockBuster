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

	public void indexRenta() {
		List<VideoGames> videoGamesList = modeloVideojuego.getAllVideogames();
		vista.Renta(videoGamesList);
	}

	public void indexCompra() {
		List<VideoGames> videoGamesList = modeloVideojuego.getAllVideogames();
		vista.Compra(videoGamesList);
	}


	public void operacionRenta(int id) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(id);
		usuarios = modeloUsuario.getAll();
		vista.OperacionRentar(myVideogame,usuarios);
	}
	
	public void detallesRenta(int idConstumer, int idVideogame, Date fechaDevolucion) {
		User myUser = modeloUsuario.get(idConstumer);
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		vista.DetallesRenta(myUser ,myVideogame, fechaDevolucion);
	}
	
	public void operacionVender(int id) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(id);
		usuarios = modeloUsuario.getAll();
		vista.OperacionComprar(myVideogame,usuarios);
	}
	
	public void indexDetallesCompra(int idConstumer, int idVideogame) {
		User myUser = modeloUsuario.get(idConstumer);
		VideoGames myVideogame = modeloVideojuego.getVideogames(idVideogame);
		vista.DetallesCompra(myUser ,myVideogame);
	}

}
