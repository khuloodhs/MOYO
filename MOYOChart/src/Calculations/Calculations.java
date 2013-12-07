package Calculations;

import java.util.Scanner;

import MOYO.*;
import Settings.settings;
import Calculations.valueReader;

public class Calculations {
	
	private static Double L=0.0;
	private static Double M=0.0;
	private static Double S=0.0;
	private static Double zScore=0.0;
	private static Double SD3neg= 0.0;
	private static Double SD2neg= 0.0;
	private static Double SD1neg= 0.0;
	private static Double SD3pos= 0.0;
	private static Double SD0=0.0;
	private static boolean isValid;
	private static Double targetWeight=0.0;
	private static String zScoreType= "";
	private static Double medianWeight=0.0;
	private static Child Child;
	private static Scanner inputValues= new Scanner(System.in);
	
	
	public Calculations(Child Child){
		Calculations.Child= Child;
	}
	
	// retrieving data from excel.java file 
	public static void readValues(String chartName, double y){
		String extension="";
		
		int dotIndex = chartName.indexOf(".");
		if (dotIndex != -1)
		{
			extension = chartName.substring(dotIndex, chartName.length());
		}
		
		if(extension.equals(".csv")){
		valueReader excel= new excel(chartName, y);
		excel.readValues();

				   L= excel.L;
				   M= excel.M;
				   S= excel.S;
				   SD3neg= excel.SD3neg;
				   SD2neg= excel.SD2neg;
				   SD1neg= excel.SD1neg;
				   SD0= excel.SD0;
				   SD3pos= excel.SD3pos;
		}else if(extension.equals(".json")){
			valueReader json= new json(chartName, y);
		}else if(extension.equals(".html")){
			valueReader html= new html(chartName, y);
		}
	}
		
		    
	
//if the input values for the double data entry differ by a value of greater 0.7, value is set to 0. Else, mean value is taken
	public static double doubleDataEntryAverage(Double input, Double input2) {
		double value=0.0;
		if (Math.abs(input-input2)<=0.7) {
			value=(input+input2)/2;
			value = Math.round(value * 2) / 2;
		} else {
			value=0.0;
		}
		return value;
	}
	
// rounding to the nearest .5 
	public static double roundToNearest05(double input){
		double diff= 0.0;
		diff= (double)(Math.round((input- Math.floor(input))*10));
		diff= diff/10;
		if(diff>=0.8){
			input= Math.ceil(input);
		}else if(diff>0.5 & diff<0.8){
			input= Math.ceil(input) - 0.5;
		}else if(diff<=0.5 & diff>=0.3){
			input= Math.floor(input) + 0.5;
		}else if(diff<0.3 ){
			input= Math.floor(input);
		}
	return input;
	}
	
	
	//if the setTargetWeight is on, then, get the input from the user and set the value 
		public void setTargetWeight(String targetSD){

			if(targetSD.equals("-1")){
				targetWeight= SD1neg;
			}else if(targetSD.equals("-2")){
				targetWeight= SD2neg;
			}else if(targetSD.equals("-3")){
				targetWeight= SD3neg;
			}else{
				targetWeight= Math.pow((Double.parseDouble(targetSD)*S*L)+1,(1/L))*M;
			}
		
		targetWeight= (double)(Math.round(targetWeight*100));
		targetWeight= targetWeight/100;
	}

		
	//return the targetWeight
	public static double getTargetWeight(){
	
		return targetWeight;
	}
	

	public void setMedianHeight(){
		medianWeight= SD0;
	}
	
	public static double getMedianHeight(){
		return medianWeight;
	}

	public static double zScoreCalculator(String chartName, double y, double measurement, String zScoreType1){
		
		zScoreType= zScoreType1;
		
		//calling the readValues method,which will read the necessary values from the table
		readValues(chartName, y);
		
		//Calculating the zScore
		zScore= (Math.pow((measurement/M),L)-1)/(S*L);

		
		//further calculations for the zScore depending on the range in which the initial zScore falls
		if(zScore<=3 & zScore>=-3){
			if (zScore<0.0){
			zScore=(double)(-1*Math.round(Math.abs(zScore)* 100));
			zScore= zScore/100;
			}if(zScore>0.0){
				zScore= (double)(Math.round(zScore*100));
				zScore= zScore/100;
			}
		}else if(zScore>3){
			Double SD23= M*Math.pow((1+(L*S*3)),(1/L))-M*Math.pow((1+(L*S*2)),(1/L));
			zScore= 3+((measurement-SD3pos)/SD23);
			if (zScore<0.0){
				
				zScore=(double)(-1*Math.round(Math.abs(zScore)* 100));
				zScore= zScore/100;
				}if(zScore>0.0){
					zScore= (double)(Math.round(zScore*100));
					zScore= zScore/100;
				}

		}else if(zScore<-3){
			Double SD23neg= M*(Math.pow((1+(L*S*-2)),(1/L)))-M*(Math.pow((1+(L*S*-3)),(1/L)));
			//lookUp[4]= SD3neg
			zScore= -3+((measurement-SD3neg)/SD23neg);
			if (zScore<0.0){
				
				zScore=(double)(-1*Math.round(Math.abs(zScore)* 100));
				zScore= zScore/100;
			}if(zScore>0.0){
					zScore= (double)(Math.round(zScore*100));
					zScore= zScore/100;
			}
		}
		
		//Calls the dataValidation method 
		if(isValid(zScore)){
			
		}else{
			isValid(zScore);
		}
		return zScore;
	}
	
	
//DataValidation 	
	public static boolean isValid(double zScore){

		
		isValid=true;
		
		
		Integer UpperLimit=0;
		int LowerLimit=0;
		
		//if the dataValidation setting is set on, ask for the upper limit and the lower limit for each chart type
		if(settings.getDataValidationSetting()){
			System.out.println("Enter the upper limit for "+zScoreType);
			UpperLimit=Integer.parseInt(inputValues.next());
			System.out.println("Enter the lower limit (in negative) for "+zScoreType);
			LowerLimit=Integer.parseInt(inputValues.next());
		
		if(zScore>UpperLimit||zScore<LowerLimit){
			System.out.println("Please recheck the following values:");
			System.out.println("Height: "+ MOYO.Child.getheight());
			System.out.println("Weight: "+MOYO.Child.getweight());
			System.out.println("Age:    "+MOYO.Child.getAge());
			System.out.println("Do you want to continue? Enter Y for Yes and N for No");
			String go=inputValues.next();
			//if the user chooses to continue despite the zScore range, then set isValid to true 
			if(go.equals("Y")){
				isValid=true;
			}
			
			else if(go.equals("N")){
				isValid=false;
				
				//if the user says he/she doesn't want to continue, then ask if he/she wants to re-enter the values
				System.out.println("Do you want to re-enter values?If yes, enter Y. Else, enter N");
				String reEnter= inputValues.next();
				if(reEnter.equals("Y")){
					
					//call the individual set methods to re-set the values
					Child.setWeight();
					
					Child.setHeight();
					
					Child.setAge();
					
					//depending on the chart type, call the specific zScore calculator
					
					if(zScoreType.equals("WHZ")){
						Child.setWHZscore();
					}else if(zScoreType.equals("WAZ")){
						Child.setWAZscore();
					}else if(zScoreType.equals("HAZ")){
						Child.setHAZscore();
					}
					
				}
				else if(reEnter.equals("N")){
					isValid=false;
				}
				
				else{
					System.out.println("You have entered an incorrect value");
				}
					
				
			}
			
		}
		
		//if the zScore is within a normal range, then, proceed to set isValid to true
		else if(zScore<UpperLimit||zScore>LowerLimit){
		isValid=true;
		
		}
		}
		
		
		return isValid;
		
		
	}

}
