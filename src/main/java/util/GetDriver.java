package util;

public class GetDriver {

	private static String firefoxDriver = "/browserDrivers/geckodriver_linux";
	
	public static String fireFox(){
		return System.getProperty("user.dir") + firefoxDriver;
	}
}