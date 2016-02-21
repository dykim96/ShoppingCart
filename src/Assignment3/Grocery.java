package Assignment3;

public class Grocery extends Item {
	//variables, constructor here
	private boolean perishable;
	
	Grocery(){
		super();
		perishable = false;
	}
	
	Grocery(String name, double price, long quantity, double weight, boolean perishable){
		super(name, price, quantity, weight);
		this.perishable = perishable;
	}
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		final_price = 20.0 * weight;
		if(perishable){//premium shipping
			final_price *= 1.2;
		}
		final_price += price;
		final_price *= ((double)quantity);
		return final_price;
	}
}
