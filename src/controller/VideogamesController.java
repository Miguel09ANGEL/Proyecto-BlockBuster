package controller;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;
import views.HomeView;
import views.VideogamesView;

public class VideogamesController {

	private VideogamesView vista;
	private List<VideoGames> videogames = new ArrayList<>();

	public VideogamesController() {
		this.vista = new VideogamesView();
	}

	public void indexVideoGames() {
		VideoGamesModel um = new VideoGamesModel();
		videogames = um.getAllVideogames();
		vista.RegistroJuegos(videogames);
	}

	public void updateVideogames(int id) {
		VideoGamesModel vm = new VideoGamesModel();
		VideoGames myVideogame = vm.getVideogames(id);
		vista.DetallesJuego(myVideogame);
	}

	public void updateVideogames2(int id) {
		VideoGamesModel vm = new VideoGamesModel();
		VideoGames myVideogame = vm.getVideogames(id);
		vista.EditarJuego(myVideogame);
	}
}
