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

	  public boolean checkFormat(String line){
		  //check if line is empty
		  if(line.trim().isEmpty()){
			  //System.out.println("Line is Empty");
			  return false;
		  }
		  String token[] = line.toLowerCase().split(" +");
		  //check for operations
		  if(token[0].equals("insert")){
			  //check for categories
			  if(token.length > 1){
				  if(token[1].equals("groceries") || token[1].equals("electronics") || token[1].equals("clothing")){
					  if(token.length > 2){
						  
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
					  System.out.println("Wrong quantity : quantity must be an integer or long");
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