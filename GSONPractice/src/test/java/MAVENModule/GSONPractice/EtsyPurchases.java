package MAVENModule.GSONPractice;

import java.util.List;

public class EtsyPurchases {
	private double totalShippingCost;
	private double totalPrice;
	private double totalCost;
	
	private List<EtsyRecord> purchases;
	
	public EtsyPurchases(List<EtsyRecord> purchases) {
		this.purchases = purchases;
		setTotalCosts();
	}
	
	private void setTotalCosts() {
		totalShippingCost = 0;
		totalPrice = 0;
		totalCost = 0;
		
		for(EtsyRecord er : purchases) {
			totalShippingCost += er.getShipping_cost();
			totalPrice += er.getPrice();
		}
		
		totalCost = totalShippingCost + totalPrice;
	}
}