package Assignment3;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	Clothing(){
		super();
	}
	
	Clothing(String name, double price, long quantity, double weight){
		super(name, price, quantity, weight);
	}
	
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		final_price = (20.0 * weight + price * 1.1)*((double)quantity);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.println("\nName : " + this.name);
		System.out.println("Category : Clothing");
		System.out.println("Weight : " + this.weight + "lbs");
		System.out.printf("Price : $%.2f\n", this.price);
		System.out.println("Quantity : " + this.quantity);
		System.out.println("Additional sales tax : 10%");
		System.out.printf("Total Price : $%.2f\n", calculatePrice());
	}
}
