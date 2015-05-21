package utilities;
import java.text.NumberFormat;
/**
 * Essentially, the <code>Utilities</code> class is a collection of static methods that
 * perform various computations relevant to computing common and accrued interest.
 * @author UMD CS Department
 *
 */
public class Utilities {
	/**
	 * Implements a simple interest computation, viz. \f[
	 * SI = principle times \dfrac{percentage}{100} \times years + principle
	 * \f]
	 * where \f$SI\f$ is simple interest, and the other variables are self-explanatory.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return simple interest
	 */
	public static double simpleInterest(double principal, double ratePercentage, double years) {
		
		return (principal + principal*(ratePercentage/100)*years);
	}
	/**
	 * Pretty print (displays) the Simple Interest --note this method 
	 * use  <code>formattedCurrency</code>, defined within your class.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return formatted simple interest
	 */
	public static String formattedSimpleInterest(double principal, double ratePercentage, double years) {
		
		return formattedCurrency(simpleInterest(principal, ratePercentage, years));
	}
	/**
	 * To compute compound interest, use the following formula:
	 * \f[
	 * CI = principle times left(frac{1+percentage}{100}\right)^{years}
	 * \f]
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return compound interest
	 */
	public static double compoundInterest(double principal, double ratePercentage, double years) {
		return (principal*Math.pow(1+ratePercentage/100, years)); 
	}
	/**
	 * Pretty print the computed compound interest --note, this method should use
	 * the <code>formattedCurrency</code> method.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return formatted compound interest
	 */
	public static String formattedCompoundInterest(double principal, double ratePercentage, double years) {
		return formattedCurrency(compoundInterest(principal, ratePercentage, years));
	}

	/**
	 * Use Java's <code>NumberFormat</code> to format the currency values to US (which will be
	 * the default <em>locale</em>.
	 * @param value
	 * @return formatted currency (US)
	 */
	private static String formattedCurrency(double value) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(value);
	}
	/**
	 * Return a delimited <code>String</code> (that is, a string with carriage returns, etc.) suitable 
	 * for display in the GUI.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return simple interest table
	 */
	public static String simpleInterestTable(double principal, double ratePercentage, int years) {
		String toReturn = "Principal: "+ formattedCurrency(principal)+ ", Rate: "+ratePercentage;
		toReturn+= "\n Year, Simple Interest Amount";
		for(int rows=1; rows<= years; rows++){
			toReturn  = toReturn +"\n"+rows+"-->"+formattedSimpleInterest(principal, ratePercentage, rows);
		}
		return toReturn;
	}
	/**
	 * Return a <code>String</code> containing necessary information formatted to suit the 
	 * GUI.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return compound interest table
	 */
	public static String compoundInterestTable(double principal, double ratePercentage, int years) {
		String toReturn = "Principal: "+ formattedCurrency(principal)+ ", Rate: "+ratePercentage;
		toReturn+= "\n Year, Compound Interest Amount";
		for(int rows=1; rows<= years; rows++){
			toReturn  = toReturn +"\n"+rows+"-->"+formattedCompoundInterest(principal, ratePercentage, rows);
		}
		return toReturn;
	}
	/**
	 * Return a <code>String</code> embodying all of the relevant information for these interest
	 * computations. Note, the string that this method creates should be suitable for display
	 * in the GUI.
	 * @param principal
	 * @param ratePercentage
	 * @param years
	 * @return both interest table
	 */
	public static String bothInterestsTable(double principal, double ratePercentage, int years) {
		String toReturn = "Principal: "+ formattedCurrency(principal)+ ", Rate: "+ratePercentage;
		toReturn+= "\n Year, Simple Interest Amount, Compound Interest Amount";
		for(int rows=1; rows<= years; rows++){
			toReturn  += "\n"+rows+"-->"+
						formattedSimpleInterest(principal, ratePercentage, rows)+
						"-->"+formattedCompoundInterest(principal, ratePercentage, rows);
		}
		return toReturn;
	}
	
	public static void main(String[] args) {
		double principal = 1000, ratePercentage = 5, years = 15;
		for (int i=1; i<=years; i++)
			System.out.println(formattedSimpleInterest(principal, ratePercentage, i));
	}
}