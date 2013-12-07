package Calculations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class excel extends valueReader{

	
	public excel(String chartName, double y){
		super(chartName, y);
	}
	
	//reading values from excel spreadsheet
	public void readValues(){
		
		BufferedReader in;
		try {
			
		in = new BufferedReader(new FileReader(chartName));

			
		  String line= null;
		  while ((line = in.readLine()) != null) {

		   Scanner scanner = new Scanner(line);
		   
		   while (scanner.hasNext()) {
			   line= scanner.next();
			   lookUp = line.split(",");
			
			  
			   //lookUp[1]= L, lookUp[2]=M, lookUp[3]=S
			   if(Double.parseDouble(lookUp[0])==y){
				   L= Double.parseDouble(lookUp[1]);
				   M= Double.parseDouble(lookUp[2]);
				   S= Double.parseDouble(lookUp[3]);
				   SD3neg= Double.parseDouble(lookUp[4]);
				   SD2neg= Double.parseDouble(lookUp[5]);
				   SD1neg= Double.parseDouble(lookUp[6]);
				   SD0= Double.parseDouble(lookUp[7]);
				   SD3pos= Double.parseDouble(lookUp[10]);
			   }
		    
		   }
		   
		   scanner.close();
		  }
		  
		  in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
