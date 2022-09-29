import java.util.Scanner;
import javax.xml.namespace.QName;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]
    private static final int BIN_SIZE = (int)((UPPER_BOUND-LOWER_BOUND)/NUM_BINS);           // size of each bin
   
 
    public static String getHeaderAsString( String me ) {

    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
	return (name+" ").split(" ")[0]; 
    }

 
    public static void main(String[] args) {  

        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	String heading = getHeaderAsString( user );

        System.out.println( heading ); 
        
        // Calls the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );

	// Calls the method to create the array histogram
	    int[] histogram = calculateHistogram( numbers );

	// Print the historgram
        System.out.println( toString( histogram ) );
    }

    // Creates and returns the histogram
    public static int [] calculateHistogram( double [] numbers ) {
        int[] histogram = new int[NUM_BINS];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = BIN_SIZE; j <= UPPER_BOUND; j = j+BIN_SIZE) {
                if (numbers[i] <= j) {
                    histogram[(j/BIN_SIZE)-1]++;
                    break;
                }
            }
        }
        return histogram;
    }

    // returns the bin that num belongs in
    public static int findBin( double num ) {
        for (int i = BIN_SIZE; i <= UPPER_BOUND; i = i+BIN_SIZE) {
            if (num <= i) {
                return (int)(i/BIN_SIZE) - 1;
            }
        }
        return -1;
    }
    
    public static String toString( int [] histogram ) {
        
        StringBuilder sb = new StringBuilder();

        //Starts with the first set of bounds without loop
        sb.append("[" + (int)LOWER_BOUND + ".." + (int)(LOWER_BOUND + BIN_SIZE) + "]:");
        for (int j =0; j<histogram[0]; j++) {
            sb.append("*");
        }
        sb.append( System.getProperty("line.separator") );

        // loop for showing the rest of the bounds
        for (int i = (int)BIN_SIZE; i< UPPER_BOUND; i = i + BIN_SIZE) {
            sb.append("(" + i + ".." + (i + BIN_SIZE) + "]:");
            //loop for showing number of items in bounds
            for (int j =0; j<histogram[i/BIN_SIZE]; j++) {
                sb.append("*");
            }
            // line separator before continuing to next set of bounds
            sb.append( System.getProperty("line.separator") );
        }

        return sb.toString();
    }

    // determines if the integer is in the range of valid inputs
    public static boolean validInput( double num ) {
        if(num >= LOWER_BOUND && num <= UPPER_BOUND) {
            return true;
        } else {
            return false;
        }
    }
    
    // returns array of doubles input by user
    public static double[] inputNumbers( Scanner scan ) {
        // create array with max length
        double[] input = new double[MAX_NUMBERS];
        int count = 0;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            double num = scan.nextDouble();
            if (num != SENTINAL) {
                count++;
                if (validInput(num)) {
                    input[i] = num;
                } else {
                    throw new IllegalArgumentException("Number is out of the valid input range.");
                }
            
            } else {
                break;
            }
             
        }
        // creates new array with length equal to number of inputs
        double[] input2 = new double[count];
        for (int i = 0; i < input2.length; i++) {
            if (input[i] != SENTINAL){
                input2[i] = input[i];
            }
        }
        return input2;
    }



} // end of class
