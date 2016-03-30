package BasicCalculator;
import java.util.Scanner;

/*
 * 
 * ---- @author Eoin Gralton 
 * ---- L00102669
 * ---- Bsc. Computing (Hons)
 * ---- Basic Handler for Insurance policies using a switch statement
 * ---- Assignment: Cloud/Enterprise Systems
 * ---- Lecturer: Ruth Lennon 
 *
 */
public class calculatorAddOn {



  public static void main(String[] args){
    int choice;
    int age = 0;
    int basePrice= 10; 
    
    int cardiac= 20;
    int diabetic = 10;
    int epilepsy = 10;
    int pregnant = 30;
    
    
    // add variables
    boolean check = false;
    Scanner keyboard = new Scanner(System.in); 
    
    do{
      
      System.out.println("Enter Age: ");
      //Scanner keyboard = new Scanner(system.in);
      age = keyboard.nextInt();
      
      if(age >= 90)
      {
    	  System.out.println("Sorry, Insurance not appicable");
    	  System.exit(0);
    	  
      }
      
      System.out.println("1: No Medical Issues");
      System.out.println("2: Cardiac Problems");
      System.out.println("3: Diabetic");
      System.out.println("4: Epilepsy");
      System.out.println("5: Pregnant");
      System.out.println("6: More than 1 of above");
      System.out.println("0: Quit");
      
      System.out.println("Please Select if appicable: ");
      
      choice = keyboard.nextInt();
      // No Issue, price is same as before
      if (choice == 1){
       System.out.println("Your Base Price is" +  " " + "Base Price : €"+ basePrice);
      }
      // Cardiac + 20 on price
      else if (choice == 2){
        System.out.println("Cardiac Price" + " " + " Premium" + "€ "+ cardiac + basePrice);
      }
      // Diabetic + 10 on price
      else if (choice == 3){
        System.out.println("Diabetic Price" + " " +" Premium" + " " + "€" + diabetic + basePrice);
      }
      // Epilepsy = 10 on price
      else if (choice == 4){
        System.out.println("Epileptic Price" + " " + " Premium" + " " + "€" + epilepsy + basePrice);
      }
      // Pregnant + 30 on price
      else if (choice == 5){
          System.out.println("Pregnant Price" + " "  + " Premium" + " " + "€" + pregnant + basePrice);
      }
      // More than 1
      else if (choice == 6){
          System.out.println("Multiple Ailment Price" + " " +basePrice + " Premium" + " " + "€" + 20 * 2);
      }
      
      else if (choice == 0){
        System.out.println("Exit with no policy?");
        System.exit(0);
        
      }
      
      else{
        System.out.println("Error, entered wrong number.");
      }
      
     while (!check==true);
      System.out.print("Finished.");
     
 }while (choice >= 6);
}
  
}

