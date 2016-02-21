package Assignment3;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	private boolean fragile;
	private boolean taxExempt;
	
	Electronics(){
		super();
		fragile = false;
		taxExempt = false;
	}
	
	Electronics(String name, double price, long quantity, double weight, boolean fragile, boolean taxExempt){
		super(name, price, quantity, weight);
		this.fragile = fragile;
		this.taxExempt = taxExempt;
	}
	
	//Implement calculate price/print methods as necessary
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		final_price = 20.0 * weight;
		if(fragile){//premium shipping
			final_price *= 1.2;
		}
		if(taxExempt){
			final_price += price;
		}
		else{
			final_price += price*1.1;
		}
		final_price *= ((double)quantity);
		return final_price;
	}
}
