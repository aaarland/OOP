import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Locale;
/**
 * RetirementSimulator takes values and calculates a yearly return investment like a 401k.
 * It consists of 7 methods including main.
 * <p style="color:red">BUGS: "<b>Histogram is weird when 1 year is chosen, should be 100 '#' is 51, 98 when year is 50</b>"
 * " </p>
 * 
 * @author Adrian Aarland
 * @version 1.1.0
 * 
 */
public class RetirementSimulator {

	// Attribute declaration, used in multiple methods
	private static float initialInv; // Initial investment, used in runSimulation(), printResult(), userInput()
	private static float initialSalary; // Initial salary, , used in runSimulation(), printResult(), userInput()
	private static float salaryUpBound; // Upper percentage saving in one year, used in runSimulation(), printResult(),
	// userInput()
	private static float salaryLowBound;// Lower percentage saving in one year, used in runSimulation(), printResult(),
	// userInput()
	private static float yearlyRetUpBound;// Yearly return upper limit in percentage, used in runSimulation(),
	// printResult(), userInput()
	private static float yearlyRetLowBound; // Yearly return lower limit in percentage, used in runSimulation(),
	// printResult(), userInput()
	private static float raiseUpBound; // Expected raise upper limit in percentage, used in runSimulation(),
	// printResult(), userInput()
	private static float raiseLowBound; // Expected raise lower limit in percentage, used in runSimulation(),
	// printResult(), userInput()
	private static int retirement; // Years until retirement, used in runSimulation(), printResult(), userInput().

	// Declaration of ArrayLists
	private static ArrayList<Float> invAccount; // Takes the account balance of every year, used in runSimulation()
	private static ArrayList<Float> totalSalary; // Takes all the random salary ranges, used in runSimulation()
	private static ArrayList<Float> totalYearlyRet; // Takes all the random yearly return ranges, used in
	// runSimulation()
	private static ArrayList<Float> totalRaise; // Takes all the random raise values, used in runSimulation()

	private static Random rand = new Random(); // Random generator declaration
	private static Scanner input; // Scanner declaration

	/**
	 * Main method, used to call userInput() and runSimulator(). input is
	 * instantiated. Default format is set to US
	 * 
	 * @param args takes command lines
	 */
	public static void main(final String[] args) {
		input = new Scanner(System.in); // instance of input, used to take user input.
		Locale.setDefault(Locale.US); // Set language to US, String.format("%.2f") prints ',' not '.' if not set.

		userInput(); //Method for user input
		runSimulator(); // Method for the simulation.
		printResults(); // Method for printing result out.

		// close the scanner
		input.close();
	}

	/**
	 * runSimulator takes the users input and creates random numbers between the
	 * ranges the user have set. The different ranges are <i>salaryRange,
	 * raiseRange, yearlyRetRange.</i> It adds a value to the investment account
	 * based on <i>salaryRange</i> , then takes the new value and adds a yearly
	 * return to the account based on <i>yearlyRetRange</i>, then creates a new
	 * salary based on <i>salaryRange</i> for the next years investment.
	 * <p>
	 * The ranges are added to an array for upper, lower, and average calculation.
	 * The <i>newInv</i> is then added to an array for printing all years.
	 * </p>
	 * <p>
	 * <i>printResult()</i> is then called with <i>salaryRange, yearlyRetRange,
	 * raiseRange, newInv</i>
	 * 
	 */
	public static void runSimulator() {

		float salaryRange = 0; // Range between upper and lower limit of expected raise
		float yearlyRetRange = 0; // Range between upper and lower limit of yearly return
		float raiseRange = 0; // Range between upper and lower limit of expected raise
		float newInv = 0; // Saves the new yearly investment account based on salaryRange and
		// yearlyRetRange
		float newSalary = 0; // Saves the new yearly salary based on salaryRange

		// Creates Float ArrayLists
		RetirementSimulator.invAccount = new ArrayList<Float>(); //Stores yearly balance in investment account
		RetirementSimulator.totalYearlyRet = new ArrayList<Float>(); //Stores randomly generated number based on yearlyRetRange
		RetirementSimulator.totalSalary = new ArrayList<Float>(); //
		RetirementSimulator.totalRaise = new ArrayList<Float>();

		newInv = initialInv; // Initiates the new investment account, used in yearly calculation
		newSalary = initialSalary; // Initiates the new salary account, used in yearly calculation

		invAccount.add(0, newInv); // adds the Initial value to the first position in the arrayList invAccount

		// yearly generated loop
		for (int index = 1; index - 1 < retirement; index++) {

			// creates a random number between 0-1 which is multiplied by the upper limit
			// minus the lower limit
			// the lower bound is then added to make a random number between the two ranges
			salaryRange = salaryLowBound + rand.nextFloat() * (salaryUpBound - salaryLowBound);
			raiseRange = raiseLowBound + rand.nextFloat() * (raiseUpBound - raiseLowBound);
			yearlyRetRange = yearlyRetLowBound + rand.nextFloat() * (yearlyRetUpBound - yearlyRetLowBound);

			newInv = newInv + (newSalary * (salaryRange / 100)); // newInv is added with the salary and multiplied by
			// the decimal number of salaryRange
			newInv = newInv + (newInv * (yearlyRetRange / 100)); // newInv is added with the newInv multiplied by the
			// decimal number of yearlyRetRange
			newSalary = newSalary + (newSalary * (raiseRange / 100)); // newSalary is assigned newSalary and added with
			// newSalary multiplied with the decimal number
			// of raiseRange

			// add every random generated number to ArrayList
			//index starts at 1, error generated 
			totalSalary.add(index - 1, salaryRange);
			totalYearlyRet.add(index - 1, yearlyRetRange);
			totalRaise.add(index - 1, raiseRange);

			invAccount.add(index, newInv); // add the current investment account to an ArrayList for safe keeping.

		} // for (int index = 1; index-1 < retirement; index++)
	}

	/**
	 * Finds the lowest value in an arrayList. Loops through the array and checks if
	 * the current value is lower then the local variable lowest.
	 * 
	 * @param array takes an array with floating points
	 * @return the value of the local variable lowest
	 */

	public static float getLowest(final ArrayList<Float> array) {

		float lowest = 100;

		for (int index = 0; index < array.size(); index++) {
			if (lowest > array.get(index)) {
				lowest = array.get(index);
			}
		}
		return lowest;
	}

	/**
	 * Finds the highest value in an arrayList. Loops through the array and checks
	 * if the current value is higher then the local variable highest.
	 * 
	 * @param array takes an array with floating points
	 * @return the value of the local variable highest
	 */
	public static float getHighest(final ArrayList<Float> array) {

		float highest = 0;

		for (int index = 0; index < array.size(); index++) {
			if (highest < array.get(index)) {
				highest = array.get(index);
			}
		}
		return highest;
	}

	/**
	 * Finds the average value in an arrayList. Loops through the array and adds
	 * them all together.
	 * 
	 * @param array takes an array with floating points
	 * @return the value of the local variable average divided by the size of the
	 *         array.
	 */
	public static float getAverage(final ArrayList<Float> array) {
		float average = 0;

		for (final Float num : array) {
			average = average + num;
		}
		return average / array.size();
	}

	/**
	 * printResults() main task is to print out the information to the user. First
	 * it calculated the lowest, highest, and average values of <i>totalSalary,
	 * totalYearlyRet, totalRaise</i>. This is done in this method so fewer
	 * attributes are used. It prints all the information the user entered, then the
	 * simulation results, finally it prints the investment account each year.
	 * 
	 */
	public static void printResults() {

		float simSalaryLowest; // the lowest random generated salary numbers
		float simSalaryHighest; // the highest random generated salary numbers
		float simSalaryAverage; // the average of the random generated salary numbers

		float simYearlyRetLowest; // the lowest of the random generated yearly return numbers
		float simYearlyRetHighest; // the highest of the random generated yearly return numbers
		float simYearlyRetAverage; // the average of the random generated yearly return numbers

		float simRaiseLowest; // the lowest of the random generated raise numbers
		float simRaiseHighest; // the highest of the random generated raise numbers
		float simRaiseAverage; // the average of the random generated raise numbers

		// Assign the value of getLowest, getHighest, getAverage to their matching
		// variables.
		simSalaryLowest = getLowest(totalSalary);
		simSalaryHighest = getHighest(totalSalary);
		simSalaryAverage = getAverage(totalSalary);
		simYearlyRetLowest = getLowest(totalYearlyRet);
		simYearlyRetHighest = getHighest(totalYearlyRet);
		simYearlyRetAverage = getAverage(totalYearlyRet);
		simRaiseLowest = getLowest(totalRaise);
		simRaiseHighest = getHighest(totalRaise);
		simRaiseAverage = getAverage(totalRaise);

		// Users input is printed back
		System.out.println("You Entered: ");
		System.out.println("Initial investment: " + initialInv);
		System.out.println("Initial salary: $" + initialSalary);
		System.out.println("Yearly percentage of salary saved: " + String.format("%.1f", salaryLowBound) + "% - "
				+ String.format("%.1f", salaryUpBound) + "%");
		System.out.println("Range of yearly returns: " + String.format("%.1f", yearlyRetLowBound) + "% - "
				+ String.format("%.1f", yearlyRetUpBound) + "%");
		System.out.println("Yearly salary increase range: " + String.format("%.1f", raiseLowBound) + "% - "
				+ String.format("%.1f", raiseUpBound) + "%");
		System.out.println("Number of years until retirement: " + retirement);

		System.out.println();

		System.out.println("The simulation generated the following values:");

		System.out.println("Yearly percentage of salary saved-- min: " + String.format("%.2f", simSalaryLowest)
		+ "% Max: " + String.format("%.2f", simSalaryHighest) + "% average: "
		+ String.format("%.2f", simSalaryAverage) + "%"); // print of min, max, average of salary saved

		System.out.println("Range of yearly returns-- min: " + String.format("%.2f", simYearlyRetLowest) + "% max: "
				+ String.format("%.2f", simYearlyRetHighest) + "% average: "
				+ String.format("%.2f", simYearlyRetAverage) + "%"); // print of min, max, average of yearly returns

		System.out.println("Yearly percentage of salary increase-- min: " + String.format("%.2f", simRaiseLowest)
		+ "% max: " + String.format("%.2f", simRaiseHighest) + "% average: "
		+ String.format("%.2f", simRaiseAverage) + "%"); // print of min, max, average of yearly raise

		//prints the highest number in the invAccount arrayList
		System.out.println(
				"The maximum salary used in the simulation was: $" + String.format("%,.2f", getHighest(invAccount))); 
		

		System.out.println();
		System.out.println("0: #(" + invAccount.get(0) + ")"); // prints the initial investment account
		for (int index = 1; index < invAccount.size(); index++) {
			System.out.print(index + ": "); // prints the current year
			//loop for histogram		(x*100)/y = percentage
			for (int count = 0; count < (index * 100 )/invAccount.size() && count <= 100; count++) {
				System.out.print("#"); // prints index amount of '#'
			} // for (int count = 1; count <= index; count++)
			
			System.out.println("(" + String.format("%.2f", invAccount.get(index)) + ")"); //
		} // for (int index = 1; index < invAccount.size(); index++)

	}

	/**
	 * Gets input from the user Inputs include, initialInv, initialSalary,
	 * salaryLowerBound, salaryUpperBound, investmentLowerBound,
	 * investmentUpperBound, raiseLowerBound, raiseUpperBound
	 */
	public static void userInput() {
		input.useLocale(Locale.US); //set language, error when '.' was used.
		// Request current account value
		System.out.println("Please enter the current amount you have in your retirement account: ");
		initialInv = input.nextFloat();

		while (initialInv < 0.0) {// Check if the value is less than 0

			// Error and retype initialInv until >= 0
			System.err.println("ERROR: The retirement account balance is 0 or less ");
			System.out.println("Please enter the current amount you have in your retirement account: ");
			initialInv = input.nextFloat();
		} // while (initialInv < 0.0)

		// Request the salary input
		System.out.println("Please enter your current salary: ");
		initialSalary = input.nextFloat();

		// Check if salary is less than 0
		while (initialSalary < 0.0) {
			// Error and retype initialInv until >= 0
			System.err.println("ERROR: Salary is less than 0");
			initialSalary = input.nextFloat();
		}

		// Request lower bound of salary as a percentage
		System.out.println(
				"As a percentage of salary, please enter the minimum amount you plan to save for retirement in any given year: ");
		salaryLowBound = input.nextFloat();
		// check that the lower bound isn't less than 0
		while (salaryLowBound < 0) {
			System.err.println("ERROR: Percentage can't be less than 0");
			System.out.print("Please re-enter: ");
			salaryLowBound = input.nextFloat();
		}

		// Request upper bound of salary as a percentage
		System.out.println(
				"As a percentage of salary, please enter the maximum amount you plan to save for retirement in any given year: ");
		salaryUpBound = input.nextFloat();
		// check that the upper bound is higher than lower
		while (salaryLowBound > salaryUpBound) {
			System.err.println("ERROR: Upper limit can't be lower than lower limit");
			System.out.println("Please re-enter upper limit");
			salaryUpBound = input.nextFloat();
		}

		// Request lower bound for the minimum yearly return
		System.out.println("As a percentage, please enter the expected minimum yearly return for your investments: ");
		yearlyRetLowBound = input.nextFloat();
		// Request upper bound for the maximum yearly return
		System.out.println("As a percentage, please enter the expected maximum yearly return for your investments: ");
		yearlyRetUpBound = input.nextFloat();

		// check if upper is lower than lower bound
		while (yearlyRetLowBound > yearlyRetUpBound) {
			System.err.println("ERROR: Upper limit can't be lower than lower limit");
			// re-enter both values
			System.out.println("Please re-enter lower limit");
			yearlyRetLowBound = input.nextFloat();
			System.out.println("Please re-enter upper limit");
			yearlyRetUpBound = input.nextFloat();
		}

		// Request lower limit for the pay raise
		System.out.println("As a percentage, the minimum pay raise you expect to receive in any given year: ");
		raiseLowBound = input.nextFloat();

		// check if the limit is less than -100
		while (raiseLowBound < -100) {
			System.err.println("ERROR: Lower limit can't be less than -100% ");
			System.out.println("Please re-enter the minimum pay raise ");
			raiseLowBound = input.nextFloat();
		}

		// Request upper limit for the pay raise
		System.out.println("As a percentage, the maximum pay raise you expect to receive in any given year:");
		raiseUpBound = input.nextFloat();

		// check that the lower limit is less than the upper limit
		while (raiseLowBound > raiseUpBound) {
			System.err.println("ERROR: Upper limit can't be lower than lower ");
			System.out.println("Please re-enter the maximum pay raise ");
			raiseUpBound = input.nextFloat();
		}

		// Request years until retirement
		System.out.println("Please enter the whole number of years until retirement: ");
		retirement = input.nextInt();
		// Check that the years aren't less than 0
		while (retirement < 0) {
			System.out.println("ERROR: Retrirement can't be less than 0");
			System.out.println("Please re-enter years until retirement");
			retirement = input.nextInt();
		}

	}
}
//Thanks for playing