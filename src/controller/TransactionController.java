package controller;

import java.util.ArrayList;
import java.util.List;

import models.VideoGames;
import models.VideoGamesModel;
import views.TransactionView;

public class TransactionController {

	private TransactionView vista;
	private VideoGamesModel modeloVideojuego;

	private List<VideoGames> TransactionView = new ArrayList<>();

	public TransactionController() {
		this.vista = new TransactionView();
		this.modeloVideojuego = new VideoGamesModel();
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

	public void indexDetallesCompra(int id) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(id);
		vista.DetallesCompra(myVideogame);
	}

	public void indexDetallesRenta(int id) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(id);
		vista.DetallesRenta(myVideogame);
	}

	public void framePrueba(int id) {
		VideoGames myVideogame = modeloVideojuego.getVideogames(id);
		vista.OperacionRentar(myVideogame);
	}

}
