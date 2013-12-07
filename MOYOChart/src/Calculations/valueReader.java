package Calculations;

public abstract class valueReader {
	protected String[] lookUp= new String[11];
	protected Double L=0.0;
	protected Double M=0.0;
	protected Double S=0.0;
	protected Double zScore=0.0;
	protected Double SD3neg= 0.0;
	protected Double SD2neg= 0.0;
	protected Double SD1neg= 0.0;
	protected Double SD3pos= 0.0;
	protected Double SD0=0.0;
	protected String chartName="";
	protected double y=0.0;
	
	public valueReader(String chartName, double y){
	this.chartName=chartName;
	this.y= y;
	}
	
	public abstract void readValues();

}
