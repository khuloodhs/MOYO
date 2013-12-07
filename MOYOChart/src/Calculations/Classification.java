package Calculations;

import MOYO.*;
import Settings.*;

public class Classification {
	
	public Classification(){
		
	}
	
	//classify the WHZ score as Severe Acute Malnutrition, Mild Acute Malnutrition, Normal and Overweight
	public static String classifyWHZ(){
		double zScore = Child.getWHZscore();
		String classification="";
		
		if(zScore<-3){
			classification= "SAM";
		}else if(zScore >=-3 & zScore <-2){
			classification= "MAM";
		}else if(zScore >=-2 & zScore <2){
			classification= "Normal";
		}else if(zScore >2){
			classification= "Overweight";
		}
	
		return classification;
	}
	
	
	/* if MUAC< 115, Mild Acute Malnutrition
	 * if MUAC<125 and >=115, Severe Acute Malnutrition
	 * if MUAC>=125, Normal
	 */
	public static String classifyMUAC(){
		double MUAC= Child.getMUAC();
		String classification="";
		if(MUAC<115){
			classification="MAM";
		}if(MUAC>=115 && MUAC<125){
			classification="SAM";
		}if(MUAC>= 125){
			classification="Normal";
		}
		
		return classification;
	}
	
	//if OEDEMA is present, then Severe Acute Malnutrition
	public static String classifyOedema(){
		boolean Oedema= Child.getOedema();
		String classification="";
		
		if (Oedema){
			classification="SAM";
		}else{
			classification="Normal";
		}
		
		return classification;
	}
	
	//Classifying the HAZ based on the zScores calculated
	public static String classifyHAZ(){
		double zScore= Child.getHAZScore();
		String classification="";
		
		if(zScore<-3){
			classification= "Severly Stunted";
		}else if(zScore >=-3 && zScore <-2){
			classification= "Moderately Stunted";
		}else if(zScore >=-2){
			classification= "Not Stunted";
		}
	
		return classification;
				
	}
	
	//Classifying the WAZ based on the zScores calculated
	public static String classifyWAZ(){
		double zScore= Child.getWAZscore();
		String classification="";
		
		if(zScore<-3){
			classification= "Severly Underweight";
		}else if(zScore >=-3 && zScore <-2){
			classification= "Underweight";
		}else if(zScore >=-2 && zScore<=2){
			classification= "Normal";
		}else if(zScore>2 && zScore<=3){
			classification= "Overweight";
		}else if(zScore >3){
			classification= "Obese";
		}
	
		return classification;
				
	}
	
	
	
	//classification for WHZ: aggregating the WHZ, MUAC and OEDEMA classifications to give a diagnosis.
	public static String wastingMalnutritionClassification(){
		String classification="";
		
		if(settings.getMUACSetting()){
		
		if(classifyWHZ().equals("SAM") | classifyMUAC().equals("SAM") | classifyOedema().equals("SAM")){
			classification="SEVERE ACUTE MALNUTRITION";
		}else if(classifyWHZ().equals("MAM") | classifyMUAC().equals("MAM") | classifyOedema().equals("MAM")){
			classification="MODERATE ACUTE MALNUTRITION";
		}else{
			classification="NORMAL";
		}
		
		
	}else{
		if(classifyWHZ().equals("SAM")){
			classification="SEVERELY WASTED";
		}else if(classifyWHZ().equals("MAM") | classifyMUAC().equals("MAM") | classifyOedema().equals("MAM")){
			classification="MODERATELY WASTED";
		}else{
			classification="NOT WASTED";
		}
	}
		return classification;	
	}
	
	
}
