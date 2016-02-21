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
		}
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
	
	  }
	  public static void processInput(String[] instructions, ArrayList<Item> shoppingList)
	  {
		  ArrayList<String> inst = new ArrayList<String>(Arrays.asList("print","insert","update","search", "delete"));
		  ArrayList<String> states = new ArrayList<String>(Arrays.asList("TX", "NM","VA", "AZ", "AK"));
		  ArrayList<String> category = new ArrayList<String>(Arrays.asList("electronics", "groceries", "clothing"));
		  Iterator<Item> i = shoppingList.iterator();
		  int instruction = inst.indexOf(instructions[0].toLowerCase());
		  if(instruction == 0)								// instruction is print
		  {		
			double totalPrice = 0.0;
			while (i.hasNext()) 
			{
				Item temp = i.next();
				temp.printItemAttributes();
				totalPrice+= temp.calculatePrice();
			}
			System.out.printf("Total Price is %.2f\n", totalPrice);
		  }
		  else if(instruction == 1)							// instruction is insert
		  {
			  String name;
			  double price;
			  double weight;
			  long quantity;
			  boolean tax;
			  boolean shipping;
			  int categ = category.indexOf(instructions[1].toLowerCase());
			  if(categ == 0)								// item is electronics
			  {
				 name = instructions[2];
				 price = Double.parseDouble(instructions[2]);
				 quantity = Long.parseLong(instructions[3]);
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
				 shoppingList.add(new Electronics(name, price, quantity, weight, shipping, tax));
				}
			  	else if(categ ==1)						// item is groceries
			  	{
			  		name = instructions[2];
					 price = Double.parseDouble(instructions[2]);
					 quantity = Long.parseLong(instructions[3]);
					 weight = Double.parseDouble(instructions[4]);
					 if(instructions[5].toLowerCase().equals("p")){
						 shipping = true;
					 }
					 else{
						 shipping = false;
					 }
					 shoppingList.add(new Grocery(name, price, quantity, weight, shipping));
			  	}
			  	else									//item is clothing
			  	{
			  		name = instructions[2];
					 price = Double.parseDouble(instructions[2]);
					 quantity = Long.parseLong(instructions[3]);
					 weight = Double.parseDouble(instructions[4]);
					 shoppingList.add(new Clothing(name, price, quantity, weight));
			  	}
		  }
		  else if(instruction == 2)						// operation update
		  {
			  for(Item thing: shoppingList)
			  {
				  if(thing.getName().equals(instructions[1]))
				  {
					  thing.setQuantity(Long.parseLong(instructions[2]));
					  System.out.printf("Item: %s new quantity : %l\n", thing.getName(), thing.getQuantity());
					  return;
				  }
			  }
			  System.out.printf("Item not found\n");
		  }
		  else if(instruction == 3)						// operation search
		  {
			  long total = 0;
			  for(Item thing: shoppingList)
			  {
				  if(thing.getName().equals(instructions[1]))
				  {
					  total++;
				  }
			  }
			  System.out.printf("number of object %l\n", total);
		  }
		  else if(instruction == 4)							//operation delete
		  {
			  	long deletion = 0;
				while (i.hasNext())
				{
					String name;
					Item temp = i.next();
					name = temp.getName();
					if(name.equals(instructions[1]))
					{
						i.remove();
						deletion++;
					}
				}
				System.out.printf("Item deleted %l times", deletion);
		  }
		  else
		  {
			  System.out.println("error, unknown operation");
		  }
	  }

	  public static boolean checkFormat(String line){
		  //check if line is empty
		  if(line.trim().isEmpty()){
			  //System.out.println("Line is Empty");
			  return false;
		  }
		  String token[] = line.split(" +");
		  token[0] = token[0].toLowerCase();
		  //check for operations
		  if(token[0].equals("insert")){
			  //check for categories
			  if(token.length > 1){
				  token[1] = token[1].toLowerCase();
				  if(token[1].equals("groceries") || token[1].equals("electronics") || token[1].equals("clothing")){
					  if(token.length >= 6){
						  //check price
						  try{
							  Double.parseDouble(token[3]);
						  }
						  catch(java.lang.NumberFormatException e){
							  System.out.println("Wrong price : price must be a double");
							  return false;
						  }
						  //check quantity
						  try{
							  Long.parseLong(token[4]);
						  }
						  catch(java.lang.NumberFormatException e){
							  System.out.println("Wrong quantity : quantity must be an integer");
							  return false;
						  }
						  //check weight
						  try{
							  Long.parseLong(token[5]);
						  }
						  catch(java.lang.NumberFormatException e){
							  System.out.println("Wrong weight : weight must be an integer");
							  return false;
						  }
						  //check optional fields
						  if(token[1].equals("electronics")){
							  if(token.length == 8){
								  
							  }
							  else{
								  if(token.length > 8)
									  System.out.println("Invalid transaction : too much tokens");
								  else
									  System.out.println("Invalid transaction : not enough tokens");
								  return false;
							  }
						  }
						  else if(token[1].equals("clothing")){
							  if(token.length == 7){
								  
							  }
							  else{
								  if(token.length > 7)
									  System.out.println("Invalid transaction : too much tokens");
								  else
									  System.out.println("Invalid transaction : not enough tokens");
								  return false;
							  }
						  }
					  }
					  else{
						  System.out.println("Invalid transaction : not enough tokens");
						  return false;
					  }
				  }
				  else{
					  System.out.println("Wrong category!");
					  return false;
				  }
			  }
			  else{
				  System.out.println("Invalid transaction : not enough tokens");
				  return false;
			  }
		  }
		  else if(token[0].equals("update")){
			  //check if it has 3 tokens
			  if(token.length == 3){
				  //check if quantity is in right format
				  try{
					  Long.parseLong(token[2]);
				  }
				  catch (java.lang.NumberFormatException e){
					  System.out.println("Wrong quantity : quantity must be an integer");
					  return false;
				  }
			  }
			  else{
				  if(token.length > 3)
					  System.out.println("Invalid transaction : too much tokens");
				  else
					  System.out.println("Invalid transaction : not enough tokens");
				  return false;
			  }
		  }
		  else if(token[0].equals("delete") || token[0].equals("search")){
			  //check if it has 2 tokens
			  if(token.length != 2){
				  if(token.length > 2)
					  System.out.println("Invalid transaction : too much tokens");
				  else
					  System.out.println("Invalid transaction : not enough tokens");
				  return false;
			  }
		  }
		  else if(token[0].equals("print")){
			  //check if it has 1 token
			  if(token.length != 1){
				  if(token.length > 1)
					  System.out.println("Invalid transaction : too much tokens");
				  else
					  System.out.println("Invalid transaction : not enough tokens");
				  return false;
			  }
		  }
		  else{
			  System.out.println("Wrong operation!");
			  return false;
		  }
		  return true;
	  }
}