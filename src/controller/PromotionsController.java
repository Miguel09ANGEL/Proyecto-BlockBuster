package controller;

import java.util.ArrayList;
import java.util.List;

import models.Promotions;
import models.PromotionsModel;
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
	
}
