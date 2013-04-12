import java.util.Scanner;
import java.util.HashMap;
import java.math.BigDecimal; 

// 
// Homework 2, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class calculates dollars and cents for a given amount of money. (HW2, Problem 2)
 */
public class ChangeCalculator {
	private final double startingMoneyAmount;
	private double moneyAmount;
	private HashMap<Double, Integer> unitDictionary;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter an amount of money. > $");

		ChangeCalculator me = new ChangeCalculator(scan.nextDouble());

		System.out.println();
		System.out.println(me.twenties() + " twenty dollar bills");
		System.out.println(me.tens() + " ten dollar bills");
		System.out.println(me.fives() + " five dollar bills");
		System.out.println(me.ones() + " one dollar bills");
		System.out.println(me.quarters() + " quarters");
		System.out.println(me.dimes() + " dimes");
		System.out.println(me.nickels() + " nickels");
		System.out.println(me.pennies() + " pennies");
	} // end main method

	public ChangeCalculator(double moneyAmount) { // initializes a change calulator result
		this.startingMoneyAmount = moneyAmount;
		this.unitDictionary = new HashMap<Double, Integer>();
		this.setMoneyAmount(moneyAmount);
	} // end initializer method

	public double moneyAmount() {
		return this.moneyAmount;
	}

	public void findAmountForUnit(double unit) {
		int numberOfUnits = (int)(this.moneyAmount / unit);
		double remainder = this.moneyAmount - (numberOfUnits * unit);

		BigDecimal bd = new BigDecimal(remainder).setScale(2, BigDecimal.ROUND_HALF_EVEN );
		remainder = bd.doubleValue();

		this.moneyAmount = remainder;
		Double key = new Double(unit);
		Integer value = new Integer(numberOfUnits);
		unitDictionary.put(key, numberOfUnits);
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
		this.findAmountForUnit(20.00);
		this.findAmountForUnit(10.00);
		this.findAmountForUnit(5.00);
		this.findAmountForUnit(1.00);
		this.findAmountForUnit(0.25);
		this.findAmountForUnit(0.10);
		this.findAmountForUnit(0.05);
		this.findAmountForUnit(0.01);
	}

	public int twenties() {
		return unitDictionary.get(20.00);
	}

	public int tens() {
		return unitDictionary.get(10.00);
	}

	public int fives() {
		return unitDictionary.get(5.00);
	}

	public int ones() {
		return unitDictionary.get(1.00);
	}

	public int quarters() {
		return unitDictionary.get(0.25);
	}

	public int nickels() {
		return unitDictionary.get(0.05);
	}

	public int dimes() {
		return unitDictionary.get(0.10);
	}

	public int pennies() {
		return unitDictionary.get(0.01);
	}

} // end class ChangeCalculator