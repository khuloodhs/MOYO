package Settings;

public class settings {
	
	// check to see which settings are on and return the boolean value
	
	private static boolean MUACSetting;
	private static boolean DataValidation;
	private static boolean DoubleDataEntry;
	private static boolean LH;
	private static boolean TargetWeight;
	private static boolean MedianWeight;
	
	public settings(){
		
	}
	
	public void setMUACSetting(boolean isChecked){
		MUACSetting=isChecked;
	}
	
	public static boolean getMUACSetting(){
		return MUACSetting;
	}
	
	public void setDataValidationSetting(boolean isChecked){
		DataValidation=isChecked;
	}
	
	public static boolean getDataValidationSetting(){
		return DataValidation;
	}
	
	public void setDoubleDataEntrySetting(boolean isChecked){
		DoubleDataEntry=isChecked;
	}
	
	public static boolean getDoubleDataEntrySetting(){
		return DoubleDataEntry;
	}
	
	public void setLHSetting(boolean isChecked){
		LH=isChecked;
	}
	
	public static boolean getLHSetting(){
		return LH;
	}
	
	public void setTargetWeightSetting(boolean isChecked){
		TargetWeight=isChecked;
	}
	
	public static boolean getTargetWeightSetting(){
		return TargetWeight;
	}
	
	public void setMedianWeightSetting(boolean isChecked){
		MedianWeight=isChecked;
	}
	
	public static boolean getMedianWeightSetting(){
		return MedianWeight;
	}
}