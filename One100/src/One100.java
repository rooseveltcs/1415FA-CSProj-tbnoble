/**
 * One100.java
 * @version 09/09/14
 * @author Thomas Noble
*/
import java.util.*;
public class One100 {
	public static final int MAX/*Biggest that works: 9999. Biggest allowed: 2147483647*/= 100;
	public static void main(String[] args) {
		int[] num = {1, 0};
		Scanner console = new Scanner(System.in);
		while (num[0] != 0) {
		//makes the code run until you quit
			num = askNum(console);
			//num = 0 if response was "quit" or "Quit"
			if (num[0] != 0) {
				ArrayList<Integer> factors = getFactors(num);
				toText(factors);
			}
		}
		console.close();
	}
   
	public static int[] askNum(Scanner console) {
	//asks and stores 1 or 2 number(s) between 1 and MAX
		int[] num = {0, 0};
		//creates the 2 numbers (if 2nd remains 0, it is unused)
		boolean good = true;
		//good is used to check that the input is a number between 1 and MAX or quit
		boolean quit = false;
		//quit is to tell if the input was quit
		int spaces = 0;
		//used to tell if there are too many numbers
		boolean pNums = false;
		//used in determining if there are multiple numbers
		int marker = 0;
		//tells where the ' ' is
		System.out.println("Please input an integer(s) from 1 to " + MAX + ".");
		String text = "";
		try {
			text = console.nextLine();
		} catch (java.util.NoSuchElementException e) {
			text = "";
		}
		if (text.equals("quit") || text.equals("Quit")) {
		//checks if the input was quit
			quit = true;
		} else {
		//if the input wasn't quit, it figures out the number
			for (int i = 0; i < text.length(); i++) {
				if ((text.charAt(i) - 48 > 9 || text.charAt(i) - 48 < 0) && text.charAt(i) != ' ') {
				//checks if the character is a number or a space
					good = false;
					//if the character isn't a number, the input isn't "good"
				} else if (text.charAt(i) == ' ') {
				//checks if the character is a space
					spaces++;
					pNums = true;
					marker = i;
				} else if (spaces <= 1){
					num[spaces] += ((text.charAt(i) - 48) * ((int) Math.pow(10, text.length() - 1 - i)));
					//This adds the integer to the right number, in the correct decimal place
				}
			}
		}
		if (pNums) {
		//num[0] will be too large if there is a second number (because of how I converted the string to a number
		//this fixes that by dividing by a power of 10
			num[0] /= (int) Math.pow(10, text.length() - marker);
		}
		boolean okay1 = true;
		if (num[0] < 1 || num[0] > MAX) {
		//checks that the 1st number is good
			okay1 = false;
		}
		boolean okay2 = true;
		if (pNums) {
		//makes sure that the 2nd number is good
			if (num[1] < 1 || num[1] > MAX) {
				okay2 = false;
			}
		}
		if (spaces > 1) {
		//no more than 1 space allowed
			good = false;
		}
		if ((!good || !okay1 || !okay2) && !quit) {
		//gives an error message for a bad entry and asks again
			System.out.println("ERROR! invalid entry");
			num = askNum(console);
		}
		if (quit) {
		//sets the number as 0 if the user wants to quit
			num[0] = 0;
		}
		return num;
	}
   
	public static ArrayList<Integer> getFactors(int[] num) {
	//this calculates and returns the factors or greatest common factor
		ArrayList<Integer> factors1 = new ArrayList<Integer>();
		ArrayList<Integer> reverse1 = new ArrayList<Integer>();
		ArrayList<Integer> factors2 = new ArrayList<Integer>();
		ArrayList<Integer> reverse2 = new ArrayList<Integer>();
		//these are for the factors of both numbers.
		//the "factors" ArrayLists are for the first half of the factors and the "reverse"'s for the second
		for (int pFact = 1; pFact <= Math.sqrt(num[0]); pFact++) {
		//checks all ints between 1 and sqrt of the number for factors
		//pFact = possible Factor
			if (num[0] % pFact == 0) {
			//checks that pFact is a factor
				factors1.add(pFact);
				if (num[0] / pFact != pFact) {
				//adds the complementary factor to reverse if pFact ISN'T the sqrt
					reverse1.add(num[0] / pFact);
				}
			}	
		}
		for (int pFact = 1; pFact <= Math.sqrt(num[1]); pFact++) {
		//checks all ints between 1 and sqrt of the number for factors
		//pFact = possible Factor
			if (num[1] % pFact == 0) {
			//checks that pFact is a factor
				factors2.add(pFact);
				if (num[1] / pFact != pFact) {
				//adds the complementary factor to reverse if pFact ISN'T the sqrt
					reverse2.add(num[1] / pFact);
				}
			}
		}
		for (int opFact = reverse1.size() - 1; opFact >= 0; opFact--) {
		//reverses reverse and adds the factors to factors
			factors1.add(reverse1.get(opFact));
		}
		for (int opFact = reverse2.size() - 1; opFact >= 0; opFact--) {
		//reverses reverse and adds the factors to factors
			factors2.add(reverse2.get(opFact));
		}
		factors1.add(num[0]);
		factors2.add(num[1]);
		//for some reason, reverse doesn't include the original number
		int gCF = 0;
		//greatest Common Factor
		if (num[1] > 0) {
		//this goes through the ArrayLists to check what the greatest common factor is (if there are 2 numbers)
			for (int num1 = 0; num1 < factors1.size() - 1; num1++) {
				for (int num2 = 0; num2 < factors2.size() - 1; num2++) {
					if((int) factors1.get(num1) == (int) factors2.get(num2)) {
						gCF = (int) factors1.get(num1);
					}
				}
			}
		}
		if (gCF > 0) {
		//if there is a greatest common factor, it is the only necessary number
			factors1.clear();
			factors1.add(gCF);
		}
		if (num[1] > 0) {
		//if there is a greatest common factor, this lets later methods know
			factors1.add(0);
		} else {
			factors1.add(num[0]);
		}
		return factors1;
	}
   
	public static void toText(ArrayList<Integer> factors) {
		if (factors.get(1) == 0) {
		//the print statement for a greatest common factor
			System.out.print("Greatest common factor: " + factors.get(0));
		} else {
		//the print statement for a group of factors
			System.out.print("Factors: ");
			System.out.print("" + factors.get(0));
			for (int current = 1; current < factors.size() - 2; current++) {
			//simple to text for loop
				System.out.print(", " + factors.get(current));
			}
		}
		System.out.println();
		//prepares for next input
	}
}