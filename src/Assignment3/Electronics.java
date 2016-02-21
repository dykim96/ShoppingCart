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
	
	Electronics(String name, double price, long quantity, long weight, boolean fragile, boolean taxExempt){
		super(name, price, quantity, weight);
		this.fragile = fragile;
		this.taxExempt = taxExempt;
	}
	
	//Implement calculate price/print methods as necessary
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		final_price = 20.0 * (double)weight;
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
	
	void printItemAttributes () 
	{
		System.out.println("\nName : " + this.name);
		System.out.println("Category : Grocery");
		System.out.println("Weight : " + this.weight + "lbs");
		System.out.printf("Price : $%.2f\n", this.price);
		System.out.println("Quantity : " + this.quantity);
		if(fragile){
			System.out.print("Fragile");
		}
		else{
			System.out.print("Non-fragile");
		}
		System.out.println(" item");
		if(taxExempt){
			System.out.println("Your destination has no sales tax");
		}
		else{
			System.out.println("Additional sales tax : 10%");
		}
		System.out.printf("Total Price : $%.2f\n", calculatePrice());
	}
}
