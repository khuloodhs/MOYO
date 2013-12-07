package MOYO;

import java.util.Scanner;

import Calculations.Calculations;
//import MOYO.Child;
import Settings.*;
//import guiMoyo.*;

public class MOYOchart {

	public static void main(String[] args) {
		
		
		String targetSD= "";
	//Settings.main(args);
		
		Scanner c= new Scanner(System.in);

		Child Child = new Child();
		
		Child.setAge();
		Child.setGender();
		Child.setWeight();		
		
//if the MUAC setting is on, call the MUAC and OEDEMA set methods	
		if(settings.getMUACSetting()){
			Child.setMUAC();
			Child.setOedema();
		}
		
		//String LH="";
		
//if the LH setting is on, then ask specifically for the length or the height
		if(settings.getLHSetting()){
			Child.setLH();
		/*System.out.println("Length(L) or Height(H)?");
		LH= c.next();
		}
		
		if(LH.equals("H") | LH.equals("")){
			Child.setHeight();
		}else{
			Child.setLength();*/
		}
		else if(settings.getLHSetting()==false){
			Child.setHeight();
		}

		
		


		if(settings.getTargetWeightSetting()){
			System.out.println("Enter target weight:");
			targetSD= c.next();
		
		}
		
		//Call the set Z score methods 	
		Child.setWHZscore();
		Child.setWAZscore();
		Child.setHAZscore();
		
		Calculations Calculations = new Calculations(Child);
		
			// if the targetweight setting is on, then ask for the target weight	
		if(settings.getTargetWeightSetting()){
			Calculations.setTargetWeight(targetSD);
		}	

		if(settings.getMedianWeightSetting()){
			Calculations.setMedianHeight();
		}

		c.close();
		//an arraylist would be used here to add the current Child object data to a database for research purposes
		//Summary.main(args);
}
	

}
