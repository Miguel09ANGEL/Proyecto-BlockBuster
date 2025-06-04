package controller;

import java.util.ArrayList;
import java.util.List;

import models.Promotions;
import models.PromotionsModel;
import models.Transaction;
import models.TransactionModel;
import views.PromotionsView;

public class PromotionsController {

	private List<Promotions> promocion = new ArrayList<>();

	PromotionsView vista;
	
	public PromotionsController() {
		vista = new PromotionsView();
	}
	
	public void indexPromocion() {
		
		PromotionsModel pm = new PromotionsModel();
		
		promocion = pm.getAllPromotions();
		
		vista.PromocionAutomatica(promocion);
		
	}
	
	public void indexPromocion2() {
		
		PromotionsModel pm = new PromotionsModel();
		
		promocion = pm.getAllPromotions();
		
		vista.EditarPromociones(promocion);
		
	}
	
	public void pending_returns() {
		TransactionModel tm = new TransactionModel();
		List<Transaction> transaciones = tm.getAllRentals();
		
		vista.DevolucionesPendientes(transaciones);
	}
	
}
