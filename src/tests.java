

public class tests {

	public static void main(String args[]){
		String workingDir = System.getProperty("user.dir");
		String s = Rates.getRates(workingDir+"/rates.json","2015-07-01T08:00:00Z", "2015-07-01T12:00:00Z");
		String s2 = Rates.getRates(workingDir+"/rates2.json","2015-07-01T07:00:00Z","2015-07-01T12:00:00Z");
		String s3 = Rates.getRates(workingDir+"/rates2.json","2015-07-04T07:00:00Z","2015-07-04T12:00:00Z");
		String s4 = Rates.getRates(workingDir+"/rates3.json","2015-07-04T07:00:00Z","2015-07-04T20:00:00Z");
		String s5 = Rates.getRates(workingDir+"/rates4.json","2015-07-04T07:00:00Z","2015-07-04T20:00:00Z");
		String s6 = Rates.getRates(workingDir+"/rates.json","2015-07-04T07:00:00Z","2015-07-04T00:00:00Z");
		String s7 = Rates.getRates(workingDir+"/rates.json","2016-03-03T07:00:00Z","2015-07-04T07:00:00Z");
		String s8 = Rates.getRates(workingDir+"/rates.json","2016-03-03T09:01:00Z","2015-07-04T09:01:00Z");
		String s9 = Rates.getRates(workingDir+"/rates.json","2016-03-03T09:01:00Z","2015-07-04T21:00:00Z");
		String s10 = Rates.getRates(workingDir+"/rates.json","2016-03-03T09:00:00Z","2015-07-04T20:00:00Z");
		String s11 = Rates.getRates(workingDir+"/rates.json","","2015-07-04T20:00:00Z");
		String s12 = Rates.getRates("","2016-03-03T09:00:00Z","2015-07-04T20:00:00Z");
		System.out.println("Result: "+s+ " Expected: 1750");
		System.out.println("Result: "+s2+ " Expected: 1500");
		System.out.println("Result: "+s3+ " Expected: 2000");
		System.out.println("Result: "+s4+ " Expected: Unavailable");
		System.out.println("Result: "+s5+ " Expected: Unavailable");
		System.out.println("Result: "+s6+ " Expected: Unavailable");
		System.out.println("Result: "+s7+ " Expected: Unavailable");
		System.out.println("Result: "+s8+ " Expected: 1500");
		System.out.println("Result: "+s9+ " Expected: Unavailable");
		System.out.println("Result: "+s10+ " Expected: Unavailable");
		System.out.println("Result: "+s11+ " Expected: Unavailable");
		System.out.println("Result: "+s12+ " Expected: Unavailable");
	}
}
