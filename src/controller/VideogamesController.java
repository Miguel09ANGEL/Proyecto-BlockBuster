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

	private VideogamesView view;
    private VideoGamesModel model;
	private List<VideoGames> videogames = new ArrayList<>();

	public VideogamesController() {
		this.view = new VideogamesView();
        this.model = new VideoGamesModel();
	}

	//	Carga y muestra la lista de videojuegos.
	public void indexVideoGames() {
		videogames = model.getAllVideogames();
		
		view.RegistroJuegos(videogames);
	}

	// carga todos los detalles de lo juegos de la bd con el id 
	public void updateVideogames(int id) {
		VideoGames myVideogame = model.getVideogames(id);
		
		view.DetallesJuego(myVideogame);
	}

	// se encarga de editar los videojuegos de la bd con el id	
	public void editVideoGame(int id) {
		VideoGames myVideogame = model.getVideogames(id);
		
		view.EditarJuego(myVideogame);
	}
}
