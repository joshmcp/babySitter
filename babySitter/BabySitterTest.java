package babySitter;

import java.util.ArrayList;
import java.util.Collection;

public class BabySitterTest { 
  
	 private static final int NOT_SUPPLIED  = 0;
	 private static final int FIVEPM 		= 5;
	 private static final int SIXPM			= 6;
	 private static final int SEVENPM 		= 7;
	 private static final int EIGHTPM 		= 8;
	 private static final int NINEPM 		= 9;
	 private static final int TENPM 		= 10;
	 private static final int ELEVENPM 		= 11;
	 private static final int TWELVEPM 		= 12;
	 private static final int ONEAM	 		= 13;
	 private static final int TWOAM	 		= 14;
	 private static final int THREEAM 		= 15;
	 private static final int FOURAM 		= 16;
	 
    public static void main(String[] args) throws Exception 
    { 
        //testValidateTimes();
    	BabySitterTest test = new BabySitterTest();
    	try {
    		Collection<String> calcUtilsMessages = test.testBabySitterCalculationUtils();
    		if (calcUtilsMessages.size() > 0) {
    			for (String message : calcUtilsMessages) {
    				System.out.println(message);
    			}
    		} else {
    			System.out.println("All tests passed. Congrats. Looks like code still works...   for now.");
    		}
    	} catch (Exception ex) {
    		System.out.println("HUGE Error in hours computation test - debug that trash!");
    		
    	}
    } 
    
    private Collection<String> testBabySitterCalculationUtils () {
    	
    	Collection<String> calcUtilsMessages = new ArrayList<String>();
    	
    	Collection<String> testAfterMidnightWageMessages = testComputePostMidnightWage();
    	Collection<String> testComputeRemainingWageMessages = testComputeRemainingWage(); 
    	Collection<String> testComputeBedTimeWage = testComputeBedTimeWage();
    	
    	calcUtilsMessages.addAll(testAfterMidnightWageMessages);
    	calcUtilsMessages.addAll(testComputeRemainingWageMessages);
    	calcUtilsMessages.addAll(testComputeBedTimeWage);
    	
    	return calcUtilsMessages;
    }
    
    private Collection<String> testComputePostMidnightWage() {
    	
    	Collection<String> calcUtilsMessages = new ArrayList<String>();
    	
    	
    	int testNumber = 1;
    	int expectedWage = 0;
    	int resultWage = 0;
    	
    	//no post midnight wage
    	resultWage = BabySitterCalculationUtils.computePostMidnightWage(FIVEPM, TWELVEPM );
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputePostMidnightWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//one hour worth of post midnight wage
    	testNumber = 2;
    	expectedWage = 16;
    	resultWage = BabySitterCalculationUtils.computePostMidnightWage(FIVEPM, ONEAM );
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputePostMidnightWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//four hours of post midnight wage
    	testNumber = 3;
    	expectedWage = 64;
    	resultWage = BabySitterCalculationUtils.computePostMidnightWage(FIVEPM, FOURAM );
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputePostMidnightWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	return calcUtilsMessages;
    }
    
    private Collection<String> testComputeRemainingWage() {
    	
    	Collection<String> calcUtilsMessages = new ArrayList<String>();
    	
    	int testNumber = 1;
    	int expectedWage = 0;
    	int resultWage = 0;
    	
    	//no remaining wage because of bedtime
    	resultWage = BabySitterCalculationUtils.computeRemainingWage(FIVEPM, FIVEPM, SEVENPM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeRemainingWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//remaining wage not affected by bedtime or midnight
    	testNumber = 2;
    	expectedWage = 24;
    	resultWage = BabySitterCalculationUtils.computeRemainingWage(FIVEPM, SEVENPM, SEVENPM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeRemainingWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//no remaining wage after midnight
    	testNumber = 3;
    	expectedWage = 0;
    	resultWage = BabySitterCalculationUtils.computeRemainingWage(TWELVEPM, SEVENPM, THREEAM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeRemainingWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	return calcUtilsMessages;
    }
    
    private Collection<String> testComputeBedTimeWage() {
    	
    	Collection<String> calcUtilsMessages = new ArrayList<String>();

    	int testNumber = 1;
    	int expectedWage = 0;
    	int resultWage = 0;
    	
    	//no bed time, so no bedtime wage
    	resultWage = BabySitterCalculationUtils.computeBedTimeWage(FIVEPM, NOT_SUPPLIED, TENPM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeBedTimeWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//all hours worked are bed time wage
    	testNumber = 2;
    	expectedWage = 32;
    	resultWage = BabySitterCalculationUtils.computeBedTimeWage(SIXPM, FIVEPM, TENPM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeBedTimeWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	//no hours worked are bed time wage
    	testNumber = 3;
    	expectedWage = 0;
    	resultWage = BabySitterCalculationUtils.computeBedTimeWage(SIXPM, ONEAM, TENPM);
    	if (expectedWage != resultWage) {
    		calcUtilsMessages.add("testComputeBedTimeWage failed for testNumber: " + testNumber + " expected $" + expectedWage + " and received $" + resultWage);
    	}
    	
    	return calcUtilsMessages;
    }
} 