package MAVENModule.GSONPractice;

import java.util.List;

public class EtsyPurchases {
	private double totalShippingCost;
	private double totalPrice;
	private double totalCost;
	
	private List<EtsyRecordEssential> purchases;
	
	public EtsyPurchases(List<EtsyRecordEssential> purchases) {
		this.purchases = purchases;
		setTotalCosts();
	}
	
	private void setTotalCosts() {
		totalShippingCost = 0;
		totalPrice = 0;
		totalCost = 0;
		
		for(EtsyRecordEssential er : purchases) {
			totalShippingCost += er.getShipping_cost();
			totalPrice += er.getPrice();
		}
		
		totalCost = totalShippingCost + totalPrice;
	}
}