package controller;

import java.util.ArrayList;
import java.util.List;

import models.Promotions;
import models.PromotionsModel;
import models.Transaction;
import models.TransactionModel;
import views.PromotionsView;


// Controlador que maneja la interacción del usuario con las operaciones relacionadas con promociones.
public class PromotionsController {

	private List<Promotions> promocion = new ArrayList<>();

	PromotionsView vista;
	
	public PromotionsController() {
		this.vista = new PromotionsView();
	}
	
	// Muestra promociones automáticas.
	public void showAutomaticPromotions() {		
		PromotionsModel pm = new PromotionsModel();
		promocion = pm.getAllPromotions();
		
		vista.PromocionAutomatica(promocion);
		
	}
	// Muestra promociones para editar.
	public void showEditablePromotions() {
		PromotionsModel pm = new PromotionsModel();
		promocion = pm.getAllPromotions();
		
		vista.EditarPromociones(promocion);
		
	}
	
	// Muestra todas las devoluciones pendientes.
	public void showPendingReturns() {
		TransactionModel tm = new TransactionModel();
		List<Transaction> transaciones = tm.getAllRentals();
		
		vista.DevolucionesPendientes(transaciones);
	}
	
}
