import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * Note: Variables beginning with curr- represent objects in the JSON file.
 * 		 Variables beginning with my- represent objects from the datetime range.
 */
public class Rates{

	/*
	 * Description: Parses the JSON file, looking for a rate that matches the
	 * 			day and encapsulates the times specified by the start and end inputs.
	 * 			If a rate is found, return the price corresponding to the rate.
	 * 			Return "Unavailable" otherwise.
	 * Inputs: jsonFile - a JSON file containing a JSONArray, "rates". Each element in
	 * 		   the array contains JSONObjects "days", "times", and "price".
	 * 		   start - a string in isoformat that specifies the start of the datetime range
	 * 		   end - a string in isoformat that specifies the end of the datetime range
	 * 		   (assume start and end specify same date)
	 * Returns: A string specifying the price of a specific rate if the datetime range 
	 * 			fits inside that rate. Returns "Unavailable" otherwise.
	 */
	public static String getRates(String jsonFile, String start, String end){
		try{
			// parse "start" and "end" isostrings into hours and minutes
			int myStartHour = Isoformat.getHour(start);
			int myEndHour = Isoformat.getHour(end);
			int myStartMin = Isoformat.getMin(start);
			int myEndMin = Isoformat.getMin(end);
				
			//if end is before start, return "Unavailable"
			if((myStartHour > myEndHour) || (myStartHour == myEndHour && myStartMin > myEndMin)){
				return "Unavailable";
			}
		
			//Open JSON file, return "Unavailable if invalid JSON file
			JSONParser parser = new JSONParser();
		
			Object obj = parser.parse(new FileReader(jsonFile));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray rates = (JSONArray) jsonObject.get("rates");
			Iterator<JSONObject> iterator = rates.iterator();
			
			// loop through array of rates
			while(iterator.hasNext()){
				JSONObject currRate = (JSONObject) iterator.next();
				String currDays = (String) currRate.get("days");
				String myDay = Isoformat.getDayOfWeek(start);
				// check if datetime range is same day of the week as the rate element
				if(sameDayOfWeek(currDays, myDay)){
					String currTime = (String) currRate.get("times");
					// check if start and end are contained inside currTime
					if(timeIsValid(start, end, currTime)){
						String currPrice = (String) currRate.get("price").toString();
						return currPrice;
					}
				}
			}
		} catch(Exception e){
			return "Unavailable";
		}
		return "Unavailable";
	}
	
	/*
	 * Description: checks if myDay is a substring of currDays (ignore case).
	 * Inputs: currDays - string containing days of the week separated by commas
	 * 		   myDay - string containing one day of the week abbreviation
	 * Returns: true if myDay is a substring of currDays
	 * 			false if myDay is not a substring of currDays
	 */
	public static boolean sameDayOfWeek(String currDays, String myDay){
		return currDays.toLowerCase().contains(myDay.toLowerCase());
	}
	
	/*
	 * Description: Returns true if start and end times are completely encapsulated within currTime.
	 * Inputs: start - string in isoformat that specifies the start of the datetime range
	 * 			end - string in isoformat that specifies the end of the datetime range
	 * 			currTime - string if the form "HHMM-HHMM" that specifies a time range 
	 * 			that "start" and "end" should fit in.
	 * Returns: true if currTime encapsulates "start" and "end" times, false otherwise
	 */
	public static boolean timeIsValid(String start, String end, String currTime){
		// parse "start" and "end" isostrings into hours and minutes
		int myStartHour = Isoformat.getHour(start);
		int myEndHour = Isoformat.getHour(end);
		int myStartMin = Isoformat.getMin(start);
		int myEndMin = Isoformat.getMin(end);
		
		//parse the currTime string into the start time and end time
		String[] currTime2 = parseTime(currTime);
		int currStartHour = Integer.parseInt(currTime2[0])/100; //get 2 most significant digits (hour)
		int currStartMin = Integer.parseInt(currTime2[0])%100; //get 2 least significant digits (minutes)
		int currEndHour = Integer.parseInt(currTime2[1])/100; //get 2 most significant digits (hour)
		int currEndMin = Integer.parseInt(currTime2[1])%100; //get 2 least significant digits (minutes)
		
		if(currStartHour < myStartHour){
			if(currEndHour > myEndHour){
				//System.out.println("1");
				return true;
			}
			else if(currEndHour == myEndHour){
				if(currEndMin > myEndMin){
					//System.out.println("2");
					return true;
				}
				
			}
		}
		else if(currStartHour == myStartHour && currStartMin < myStartMin){
			if(currEndHour > myEndHour){
				//System.out.println("3");
				return true;
			}
			else if(currEndHour == myEndHour){
				if(currEndMin > myEndMin){
					//System.out.println("4");
					return true;
				}
				
			}
		}
		return false;
	}
	
	/*
	 * Description: parse a string input into multiple times
	 * Inputs: currTime - string if the form "HHMM-HHMM" (H-hour, M-minute)
	 * Returns: returns an array of strings of length 2. The first element contains
	 * 			the first "HHMM" and the second element contains the second "HHMM"
	 */
	public static String[] parseTime(String currTime){
		String delims = "[-]";
		String[] times = currTime.split(delims);
		return times;
	}
	
	/*
	 * main method used for testing
	 */
	public static void main(String[] args){
		//Testcases
		//String s = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2015-07-01T08:00:00Z", "2015-07-01T12:00:00Z");
		//String s2 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates2.json","2015-07-01T07:00:00Z","2015-07-01T12:00:00Z");
		//String s3 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates2.json","2015-07-04T07:00:00Z","2015-07-04T12:00:00Z");
		//String s4 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates3.json","2015-07-04T07:00:00Z","2015-07-04T20:00:00Z");
		//String s5 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates4.json","2015-07-04T07:00:00Z","2015-07-04T20:00:00Z");
		//String s6 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2015-07-04T07:00:00Z","2015-07-04T00:00:00Z");
		//String s7 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2016-03-03T07:00:00Z","2015-07-04T07:00:00Z");
		//String s8 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2016-03-03T09:01:00Z","2015-07-04T09:01:00Z");
		//String s9 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2016-03-03T09:01:00Z","2015-07-04T21:00:00Z");
		//String s10 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","2016-03-03T09:00:00Z","2015-07-04T20:00:00Z");
		//String s11 = getRates("/Users/s_jin01/Documents/workspace/Rates/src/rates.json","","2015-07-04T20:00:00Z");
		//String s12 = getRates("","2016-03-03T09:00:00Z","2015-07-04T20:00:00Z");
		//System.out.println(s7);
	}
}
