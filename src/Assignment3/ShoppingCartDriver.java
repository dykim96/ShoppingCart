package Assignment3;

import java.util.*;

public class ShoppingCartDriver 
	{

	  public static void main(String[] args) 
	  {
		// TODO Auto-generated method stub
		
		//Open file; file name specified in args (command line)
		
		//Parse input, take appropriate actions.
		
		//Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}		
	  }
	  public static void processInput(String[] instructions, ArrayList<Item> shoppingList)
	  {
		  ArrayList<String> inst = new ArrayList<String>(Arrays.asList("print","insert","update","search", "delete"));
		  ArrayList<String> states = new ArrayList<String>(Arrays.asList("TX", "NM","VA", "AZ", "AK"));
		  ArrayList<String> category = new ArrayList<String>(Arrays.asList("electronics", "groceries", "clothing"));
		  Iterator<Item> i = shoppingList.iterator();
		  int instruction = inst.indexOf(instructions[0].toLowerCase());
		  if(instruction == 0)
		  {
			  
		  }
		  else if(instruction == 1)
		  {
			  String name;
			  double price;
			  double weight;
			  long quantity;
			  boolean tax;
			  boolean shipping;
			  int categ = category.indexOf(instructions[1].toLowerCase());
			  if(categ == 0)
			  {
				 name = instructions[2];
				 price = Double.parseDouble(instructions[2]);
				 quantity = Integer.parseInt(instructions[3]);
				 weight = Double.parseDouble(instructions[4]);
				 if(instructions[5].toLowerCase().equals("f")){
					 shipping = true;
				 }
				 else{
					 shipping = false;
				 }
				 if(states.indexOf(instructions[5].toUpperCase())== -1)
				 {
					 tax = false;
				 }
				 else
				 {
					 tax = true;
				 }
				 
				}
			  	else if(categ ==1)
			  	{
				  
			  	}
			  	else
			  	{
				  
			  	}
			  
		  }
		  else if(instruction == 2)
		  {
			  
		  }
		  else if(instruction == 3)
		  {
			  
		  }
		  else if(instruction == 4)
		  {
			  
		  }
		  else
		  {
			  System.out.println("error, unknown operation");
		  }
		  
		  
	  }

}