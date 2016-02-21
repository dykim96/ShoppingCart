package Assignment3;

public class Item 
{
	//Declare variables for this class. Think about its type: public, protected or private?
	protected String name;
	protected double price;
	protected long quantity;
	protected long weight;

// You will need a constructor (Why?). Create it here.
	Item(){
		name = "";
		price = 0.0;
		quantity = 0;
		weight = 0;
	}
	
	Item(String name, double price, long quantity, long weight){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	String getName(){
		return this.name;
	}
	
	long getQuantity(){
		return this.quantity;
	}
	
	void setQuantity(long newQuantity){
		this.quantity = newQuantity;
	}
	
	double getPrice(){
		return this.price;
	}
	
	long getWeight(){
		return this.weight;
	}
	
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		final_price = (20.0 * (double)weight + price)*((double)quantity);
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
		System.out.println("\nName : " + this.name);
		System.out.println("Weight : " + this.weight + "lbs");
		System.out.printf("Price : $%.2f\n", this.price);
		System.out.println("Quantity : " + this.quantity);
		System.out.printf("Total Price : $%.2f\n", calculatePrice());
	}

}
