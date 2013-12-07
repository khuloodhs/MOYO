package MOYO;
//commit


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Calculations.Calculations;
import Calculations.Classification;
import Settings.settings;


public class Child {
	private static String Gender;
	private static Double age=0.0;
	private static double height=0.0;
	private static double length=0.0;
	private static Double weight;
	private static boolean OEDEMA; 
	private static double MUAC;
	private static String chartName;
	private static double WHZScore;
	private static String zScoreType;
	private static Double WAZScore;
	private static Double HAZScore;
	private static String wastingMalnutritionClassification;
	private static String stuntingClassficiation;
	private static String underweightClassification;
	private static String LH= "";

	private static Scanner c= new Scanner(System.in);
	
	public Child(){
		
	}
	
	/*AGE
	 * setAge method asks the user to input the age
	 * okAge checks to see if the age entered is appropriate
	 * try and catch methods check for Number format exception in the input
	 * getAge method returns the age in double
	 */
	
	public void setAge(){
		boolean okAge=false;
		
		while(!okAge){
		try{
			okAge=true;
		
		//while loop to ask for input again if not in range	
		while(age==0.0){
			System.out.println("Please input the child's DOB in the following format");
			System.out.println("dd-mm-yyyy");
			String dob= c.nextLine();
				
		//Entered DOB parsed to date format	
			Date birthDate = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
	        //System.out.println(birthDate);
	        
	      //current date 
	        Date now = new Date();
	        //System.out.println(now);
	        
	        Calendar calDOB = new GregorianCalendar();
	        calDOB.setTime(birthDate);
	        Calendar calToday = new GregorianCalendar();
	        calToday.setTime(now);
	        
	      //Calculating the number of days alive
	        long diff = calToday.getTimeInMillis() -  calDOB.getTimeInMillis();
	        double dayCount = diff/ (24 * 60 * 60 * 1000);
	        System.out.println(dayCount);
	       
	      //making sure the input falls within the range of 0-1856  
	        if(dayCount>=0 &&dayCount<1856){
				age=dayCount;
				System.out.println(age);
			}
			else if(dayCount>1856){
				age=0.0;
				System.out.println("The Range is 0-1856 days. Please re-enter");	 
			}
			else if(dayCount<0){
				age=0.0;
				System.out.println("The Range is 0-1856 days. Please re-enter");
			}
		}

		}
		catch(NumberFormatException e){
			okAge=false;
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(okAge){
		}else{
		
		System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
		}
		}
	}
	
	
	
	public static double getAge(){
		return age;
	}
	
	
	
	//setGender method asks the user for gender and sets the gender
	public void setGender(){
		boolean okGender=false;
		while(!okGender){
		System.out.println("Please input gender (F/M):");
		Gender= (c.nextLine());
		
		if(Gender.equals("F") | Gender.equals("M")){
		okGender=true;	
		
		}else{
		okGender=false;
		System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
		}
		}
	}

//getGender returns the Gender as string	
	public static String getGender(){
		return Gender;
	}
	
//asks for Length or Height if the setting is on 	
	public void setLH(){
		System.out.println("Length(L) or Height(H)?");
		LH= c.next();
		
		
		if(LH.equals("H") | LH.equals("")){
			setHeight();
		}else{
			setLength();
	}
	}

//returns LH 
	public String getLH(){
		return LH;
	}

//setting the height	
	public void setHeight(){
		Double input=0.0;
		Double input2=0.0;
		
		while(height==0.0){
			
			if (settings.getDoubleDataEntrySetting()) {
			do{
				boolean okHeight=false;
				while(!okHeight){
				try{
					System.out.println("Please input height");
					input= Double.parseDouble(c.next());
			
				System.out.println("Second height");
				input2= Double.parseDouble(c.next());
				
				
				okHeight=true;
				
				/*If height is measured for a child less than 2 years, add 0.7 to account for 
				spinal compression*/
				
				if(settings.getLHSetting()&&LH.equals("H")){
					if(age<730){
						input+=0.7;
						input2+=0.7;
						System.out.println(input);
						System.out.println(input2);
					}
					else if(age>730){
					}
				}
			
				
				}catch(NumberFormatException e){
					okHeight=false;
				}
				if(okHeight){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
				
			}while (Calculations.doubleDataEntryAverage(input, input2)==0.0);
			
				
			input=Calculations.doubleDataEntryAverage(input, input2);
			
			
			}else{
				boolean okHeight=false;
				while(!okHeight){
				try{
				System.out.println("Please input height");
				input= Double.parseDouble(c.next());
				if(settings.getLHSetting()&&LH.equals("H")){
					if(age<730){
						input+=0.7;
						System.out.println(input);
					}
					else if(age>730){
					}
					
					System.out.println(input);
				}
				okHeight= true;
				}catch(NumberFormatException e){
					okHeight=false;
				}
				if(okHeight){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
			}
				
			if (Gender.equals("F") && (input<45.0  | input>110.0 )){
				System.out.println("Range between 45 and 110");
			}else if(Gender.equals("M") && (input<65.0  | input>120.0 )){
				System.out.println("Range between 65 and 120");
			}else{
			height= input;
			height = Calculations.roundToNearest05(height);
			}
			}	
	}
	
	
//returns the height	
	public static Double getheight(){
		return height;
	}
	

//sets the length	
	public void setLength(){
		Double input= 0.0;
		Double input2=0.0;
	
		while(length==0.0){
			//checks to see if the double data entry setting is on
			if (settings.getDoubleDataEntrySetting()) {
			do{
				boolean okLength=false;
				while(!okLength){
				try{
					System.out.println("Please input length");
					input= Double.parseDouble(c.next());
					
					//Ask for the second length when the double data entry is on
					System.out.println("Second length");
					input2= Double.parseDouble(c.next());
					
					if(settings.getLHSetting()&&LH.equals("L")){
						//if they are more than 2 years, account for spinal compression by -0.7
						if(age>730){
							input-=0.7;
							input2-=0.7;
							System.out.println(input);
							System.out.println(input2);
						}
						else if(age>730){
						}
					}
					
					okLength= true;
				}catch(NumberFormatException e){
					okLength=false;
				}
				if(okLength){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
				}while (Calculations.doubleDataEntryAverage(input, input2)==0.0);
			
				input=Calculations.doubleDataEntryAverage(input, input2);
				
				
			}
			
			//case wherein the DoubleDataEntry is switched off
			else{
				boolean okLength=false;
				while(!okLength){
				try{
				System.out.println("Please input length");
				input= Double.parseDouble(c.next());
				if(settings.getLHSetting()&&LH.equals("L")){
					if(age>730){
						input-=0.7;
					}
					else if(age<730){
					}
					
					System.out.println(input);
				}
				okLength=true;
				}catch(NumberFormatException e){
					okLength=false;
				}
				if(okLength){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
			}
			
			//checking to see if the input value falls in the correct range
			if (Gender.equals("F") && (input<45.0 | input>110.0)){
				System.out.println("Range between 45 and 110");
			}else if(Gender.equals("M") && (input<45.0 | input>110.0)){
				System.out.println("Range between 45 and 110");
			}else{
			length= input;
			length= Calculations.roundToNearest05(length);
			}
	}
	}
	
	
	//returns the length
	public static Double getlength(){
		return length;
	}
	
	
	//setting the weight from the user input
	public void setWeight(){
		boolean okWeight=false;
		while(!okWeight){
		try{
		System.out.println("Please input weight");
		weight= Double.parseDouble(c.next());
		okWeight=true;
		}catch(NumberFormatException e){
			okWeight=false;
		}
		if(okWeight){
		}else{
		
		System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
		}
		}
	}
	
	
	//returns the weight
	public static Double getweight(){
		return weight;
	}
	
	//sets the OEDEMA as a boolean value
	public void setOedema(){
		String oedema="";
		boolean okOedema=false;
		
		while(!okOedema){
		try{
			System.out.println("OEDEMA? Y/N");
			oedema=c.next();
			okOedema=true;
		}catch(NumberFormatException e){
			okOedema=false;
		}
		if(okOedema){
		}else{
		
		System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
		}
		}
			if (oedema.equals("Y")) {
				OEDEMA=true; 
			}
			else {
				OEDEMA=false;
			
		}
	}
	
	
	//returns the OEDEMA value
	public static boolean getOedema(){
		return OEDEMA;
	}
	
	
	//setting the MUAC values
	public void setMUAC(){
			double MUAC2= 0.0;
			
			//checks to see if the double data entry is on. If it is, then ask for two values
			if (settings.getDoubleDataEntrySetting()) {
			do{
				boolean okMUAC=false;
				
				while(!okMUAC){
				try{
				System.out.println("Enter MUAC");
				MUAC= Double.parseDouble(c.next());

				System.out.println("Second MUAC");
				MUAC2= c.nextDouble();
				okMUAC=true;
				}catch(NumberFormatException e){
					okMUAC=false;
				}
				if(okMUAC){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
			}while (Calculations.doubleDataEntryAverage(MUAC, MUAC2)==0.0);
			
			//if double data entry is on, take the average for the values
			MUAC=Calculations.doubleDataEntryAverage(MUAC, MUAC2);
			}else{
				boolean okMUAC=false;
				
				while(!okMUAC){
				try{
				System.out.println("Enter MUAC");
				MUAC= Double.parseDouble(c.next());
				okMUAC= true;
				}catch(NumberFormatException e){
					okMUAC=false;
				}
				if(okMUAC){
				}else{
				
				System.err.println("\n You've inputted an unacceptable value, please re-enter \n");
				}
				}
			}	
	}
	
	//getting the MUAC value
	public static double getMUAC(){
		return MUAC;
	}
	
	
	//the method assigns the zScoreType and the chartName  
	public void setWHZscore(){
		zScoreType= "WHZ";
		
		
		//if the length is 0, then assigns the height charts by gender
		if(getlength()==0.0){
			if(getGender().equals("F")){			
				chartName="data/WHZgirls.csv";
			}else{
				chartName="data/WHZboys.csv";
			}
				WHZScore= Calculations.zScoreCalculator(chartName, getheight(), getweight(), zScoreType);
			}
		else{
				if(getGender().equals("F")){			
					chartName="data/WLZgirls.csv";
				}else{
					chartName="data/WLZboys.csv";
				}
				WHZScore= Calculations.zScoreCalculator(chartName, getlength(), getweight(),zScoreType);
			}
	}
	
	
//return the double WHZ value	
	public static Double getWHZscore(){
		return WHZScore;
	}
	
	
	
//Setting the zScoreType and the chartName for the WAZ scores	
	public void setWAZscore(){
		zScoreType= "WAZ";
		
		if(getGender().equals("F")){			
				chartName="data/WAZgirls.csv";
			}
		
		 if(getGender().equals("M")){
				chartName="data/WAZboys.csv";
			}
		
				
		WAZScore= Calculations.zScoreCalculator(chartName, getAge(), getweight(), zScoreType);
				
		}	
	
	public static double getWAZscore(){
		return WAZScore;
	}
	
	
	
	
//Setting the zScoreType and the chartName for the HAZscore	
	public void setHAZscore(){
			zScoreType= "HAZ";
	
			if(getGender().equals("F")){			
				chartName="data/HAZgirls.csv";
			}
			else if(getGender().equals("M")){
				chartName="data/HAZboys.csv";
			}
			double input= getheight()==0.0? getlength(): getheight();
			
				HAZScore= Calculations.zScoreCalculator(chartName, getAge(), input, zScoreType);	
		
		}	
	
	public static double getHAZScore(){
		return HAZScore;
	}

	public void setMalnutrinitionClassification(){
		wastingMalnutritionClassification= Classification.wastingMalnutritionClassification();
	}
	
	public static String getMalnutrinitionClassification(){
		return wastingMalnutritionClassification;
	}
	
	public void setStuntingClassficiation(){
		stuntingClassficiation= Classification.classifyHAZ();
	}
	
	public static String getStuntingClassficiation(){
		return stuntingClassficiation;
	}
	
	public void setUnderweightClassification(){
		underweightClassification= Classification.classifyWAZ();
	}
	
	public static String getUnderweightClassification(){
		return underweightClassification;
	}
	
	}


