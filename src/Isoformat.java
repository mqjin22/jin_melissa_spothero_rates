
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/*
 * This class helps handle strings in isoformat. The timezone is in UTC.
 */
public class Isoformat {
	
	/*
	 * Description: given a string in isoformat, return the abbreviated format of the 
	 * 			day of the week corresponding to the date specified by the string.
	 * Inputs: dateTime - string in isoformat
	 * Returns: String specifying the day of the week (eg. Mon, Tues, Wed, Thurs, Fri, 
	 * 			Sat, or Sun)
	 */
	public static String getDayOfWeek(String dateTime){
		DateTime dt = new DateTime(dateTime);
		DateTime.Property DoWStart = dt.dayOfWeek();
		return DoWStart.getAsShortText();
	}
	
	/*
	 * Description: given a string in isoformat, return hour corresponding to the 
	 * 			time specified by the string in UTC timezone.
	 * Inputs: dateTime - string in isoformat
	 * Returns: int specifying the hour (eg. 0-23)
	 */
	public static int getHour(String dateTime){
		DateTime dt = new DateTime(dateTime, DateTimeZone.UTC);
		return dt.getHourOfDay();
	}
	
	/*
	 * Description: given a string in isoformat, return minute corresponding to the 
	 * 			time specified by the string in UTC timezone.
	 * Inputs: dateTime - string in isoformat
	 * Returns: int specifying the minute (eg. 0-59)
	 */
	public static int getMin(String dateTime){
		DateTime dt = new DateTime(dateTime, DateTimeZone.UTC);
		return dt.getMinuteOfHour();
	}
	
	/*
	 * For testing
	 */
	public static void main(String args[]){
		
		System.out.println(getHour("2015-07-01T08:00:00Z"));
		System.out.println(getMin("2015-07-01T08:00:00Z"));
	}
}
