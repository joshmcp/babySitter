package babySitter;


public final class BabySitterCalculationUtils {
	
    protected static int computeRemainingWage(int startTime, int bedTime, int endTime) {
    	
    	int remainingWage = 0;
    	int remainingHours = 0;
    	int rate = 12;
    	//don't count any work time after midnight in this calculation
    	int endHour = endTime > 12 ? 12 : endTime;
    	
    	if (startTime < 12) {
    		if (bedTime != 0) {
	    		//don't count any work after bedtime in this calculation
	    		if (startTime < bedTime) {
	    			if (bedTime <= endHour) {
	    				remainingHours = bedTime - startTime;
	    			} else {
	    				remainingHours = endHour - startTime;
	    			}
	    		}
    		} else {
    			//bedtime was not supplied
    			remainingHours = endHour - startTime;
    		}

    		remainingWage = remainingHours * rate;
    	}
    	
    	return remainingWage;
    }
    
    protected static int computePostMidnightWage(int startTime, int endTime) {
    	int totalAfterMidnightWage = 0;
    	int hoursAfterMidnight = 0;
    	int rate = 16;
    	
    	//do not count any post midnight time that is before start time
    	int startHour = startTime > 12 ? startTime : 12;
    	
    	if (endTime > 12) {
    		hoursAfterMidnight = endTime - startHour;
    		totalAfterMidnightWage = hoursAfterMidnight * rate;
    	}
    	
    	return totalAfterMidnightWage;
    }
    
    protected static int computeBedTimeWage(int startTime, int bedTime, int endTime) {
    	
    	int bedTimeWage = 0;
    	int bedTimeHours = 0;
    	int rate = 8;
    	int midnight = 12;
    	int endHour;
   
		//NOTE: there are NO bedtime wages if:
		//		bedtime not provided
		//	OR  bedtime > endtime
		//  OR  bedtime > midnight
		
		if (bedTime != 0 && bedTime < midnight ) {
			
			//if endtime > midnight, only count bedtime wage until midnight
			endHour = (endTime > midnight) ? midnight : endTime;
			if (bedTime < endHour) {
				if (bedTime <= startTime) {
					bedTimeHours = endHour - startTime;
				} else {
					bedTimeHours = endHour - bedTime;
				}
				
				bedTimeWage = bedTimeHours * rate;
			}
		}
    	
    	return bedTimeWage;
    }
}