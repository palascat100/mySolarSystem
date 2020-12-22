package mySolarSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calculations 
//class that does all calculations for orbiting objects
{
	double accel;
	double tau=.01;
	static ArrayList<Double> position= new ArrayList<Double>();
	static ArrayList<Double> velocity= new ArrayList<Double>();
	static ArrayList<Double> acceleration= new ArrayList<Double>();
	public Calculations()
	{
		
	}
	private double CalcAccel(double mass, double radius)
	{
		//takes in mass with units 10^22 kg and km
		accel=667.4*(mass)/(radius*radius*radius);
		return accel;
	}
	
	private void Verlet()
	{
		
	}
	
}

