package MAVENModule.GSONPractice;


class EtsyRecordEssential {
	private String title;
	private String purchase_date; 
	private String shop_name;

	private double price;
	private double shipping_cost;

	public double getPrice() {
		return price;
	}
	public double getShipping_cost() {
		return shipping_cost;
	}

	public EtsyRecordEssential(
			String title,
			String purchase_date, 
			String shop_name,
			double price,
			double shipping_cost) {

		this.title = title;
		this.purchase_date = purchase_date; 
		this.shop_name = shop_name;
		this.price = price;
		this.shipping_cost = shipping_cost;
	}
}