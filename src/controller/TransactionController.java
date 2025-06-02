package controller;

import java.util.ArrayList;
import java.util.List;

import models.VideoGames;
import models.VideoGamesModel;
import views.TransactionView;

public class TransactionController {

	private TransactionView vista;

	private List<VideoGames> TransactionView = new ArrayList<>();

	public TransactionController() {

		vista = new TransactionView();

	}
	
	public void updateVideogames(int id) {
		
		TransactionView tv = new TransactionView();


		vista.AdministradorRentaCompra();

	}
	
	public void indexRenta() {
	    VideoGamesModel um = new VideoGamesModel();
	    List<VideoGames> videoGamesList = um.getAllVideogames();
	    vista.Renta(videoGamesList);
	}
	
	public void indexCompra() {
	    VideoGamesModel um = new VideoGamesModel();
	    List<VideoGames> videoGamesList = um.getAllVideogames();
	    vista.Compra(videoGamesList);
	}
	
	public void indexDetallesCompra(int id) {
		VideoGamesModel um = new VideoGamesModel();

		VideoGames myVideogame = um.getVideojuegos(id);

		vista.DetallesCompra(myVideogame);
	}
	
	public void indexDetallesRenta(int id) {
		VideoGamesModel um = new VideoGamesModel();

		VideoGames myVideogame = um.getVideojuegos(id);

		vista.DetallesRenta(myVideogame);
	}


	public void framePrueba(int id) {

		TransactionView tv = new TransactionView();
		VideoGamesModel vm = new VideoGamesModel();
		
		VideoGames myVideogame = vm.getVideojuegos(id);

		tv.OperacionRentar(myVideogame);
	}

}
