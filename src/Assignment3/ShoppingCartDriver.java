package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class ShoppingCartDriver 
	{

	  static ArrayList<String> states;
	  public static void main(String[] args) 
	  {
		  states = new ArrayList<String>(Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN","IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "UT", "VT", "VA", "WA", "WV","WI", "WY"));
		  ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		  if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
			try 
			{
				FileReader freader = new FileReader(args[0]);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{
					if(checkFormat(s))
					{
						processInput(s, shoppingCart);
					}

				}
				reader.close();
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
	  }
	  
	  
	  
	  
	  
	  
	  
	  public static void processInput(String line, ArrayList<Item> shoppingList)
	  {
		  ArrayList<String> inst = new ArrayList<String>(Arrays.asList("print","insert","update","search", "delete"));
		  ArrayList<String> states = new ArrayList<String>(Arrays.asList("TX", "NM","VA", "AZ", "AK"));
		  ArrayList<String> category = new ArrayList<String>(Arrays.asList("electronics", "groceries", "clothing"));
		  Iterator<Item> i = shoppingList.iterator();
		  String instructions[] = line.toLowerCase().split(" +");
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
			  long weight;
			  long quantity;
			  boolean tax;
			  boolean shipping;
			  int categ = category.indexOf(instructions[1].toLowerCase());
			  if(categ == 0)								// item is electronics
			  {
				 name = instructions[2];
				 price = Double.parseDouble(instructions[2]);
				 quantity = Long.parseLong(instructions[3]);
				 weight = Long.parseLong(instructions[4]);
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
					 weight = Long.parseLong(instructions[4]);
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
					 weight = Long.parseLong(instructions[4]);
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
								  token[6] = token[6].toLowerCase();
								  token[7] = token[7].toUpperCase();
								  if(token[6].equals("f") || token[6].equals("nf")){
									  if(!states.contains(token[7])){
										  System.out.println("Such state doesn't exist");
										  return false;
									  }
								  }
								  else{
									  System.out.println("Must indicate if it is F(fragile) or NF(non-fragile)");
									  return false;
								  }
							  }
							  else{
								  if(token.length > 8)
									  System.out.println("Invalid transaction : too much tokens");
								  else
									  System.out.println("Invalid transaction : not enough tokens");
								  return false;
							  }
						  }
						  else if(token[1].equals("groceries")){
							  if(token.length == 7){
								  token[6] = token[6].toLowerCase();
								  if(token[6].equals("p") || token[6].equals("np")){
									  
								  }
								  else{
									  System.out.println("Must indicate if it is P(perishable) or NP(nonperishable)");
									  return false;
								  }
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