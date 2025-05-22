package controller;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;
import views.HomeView;

public class VideogamesController {

	private HomeView vista;

	private List<VideoGames> videogames = new ArrayList<>();

	public VideogamesController() {

		vista = new HomeView();

	}

	public void indexVideoGames() {

		VideoGamesModel um = new VideoGamesModel();

		videogames = um.getAllVideogames();

		vista.RegistroJuegos(videogames);
	}

	public void updateVideogames(int id) {

		VideoGamesModel vm = new VideoGamesModel();

		VideoGames myVideogame = vm.getVideojuegos(id);

		vista.DetallesJuego(myVideogame);

	}

	public void updateVideogames2(int id) {

		VideoGamesModel vm = new VideoGamesModel();

		VideoGames myVideogame = vm.getVideojuegos(id);

		vista.EditarJuego(myVideogame);

	}
}
