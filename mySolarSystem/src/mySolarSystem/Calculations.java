package mySolarSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calculations 
//class that does all calculations for orbiting objects
{
	double tau=0.02;
	static ArrayList<Double> position= new ArrayList<Double>();
	static ArrayList<Double> currentpos= new ArrayList<Double>();
	static ArrayList<Double> mass= new ArrayList<Double>();
	static ArrayList<Double> velocity= new ArrayList<Double>();
	static ArrayList<Double> acceleration= new ArrayList<Double>();
	int bodiesNum=2;
	Main Main;
	SolarPanel Panel;
	double newaccel[];
	
	public Calculations(Main main, SolarPanel panel)
	{
		newaccel=new double[4];
		mass.add(500.0);
		mass.add(0.1);
		position.add(0.0);
		position.add(0.0);
		position.add(142.0);
		position.add(0.0);
		position.add(0.0);
		position.add(0.0);
		position.add(142.0);
		position.add(0.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(50.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(50.0);
		acceleration.add(0.0);
		acceleration.add(0.0);
		acceleration.add(0.0);
		acceleration.add(0.0);
		Main=main;
		Panel=panel;
	}
	private void CalcAccel(int i, int f)
	{
		//takes in mass with units 10^22 kg and km
		double m=mass.get(f);
		double radius=radius(i,f);
		acceleration.set((i*2),acceleration.get(i*2)+(667.4*(m)/(radius*radius*radius)*(-position.get(4+2*i)+position.get(4+2*f))));
		acceleration.set((i*2+1),acceleration.get(1+i*2)+(667.4*(m)/(radius*radius*radius)*(-position.get(5+2*i)+position.get(5+2*f))));
		
	}
	
	private void CalcNewAccel(int i, int f)
	{
		//takes in mass with units 10^22 kg and km
		double m=mass.get(f);
		double radius=radius(i,f);
		newaccel[i*2]=newaccel[i*2]+(667.4*(m)/(radius*radius*radius)*(-position.get(4+2*i)+position.get(4+2*f)));
		newaccel[i*2+1]=newaccel[i*2+1]+(667.4*(m)/(radius*radius*radius)*(-position.get(5+2*i)+position.get(5+2*f)));
		
	}
	
	private void Verlet(int i, int f)
	{
		position.set((i*2)+4, position.get((i*2)+4)+velocity.get(i*2+4)*tau+0.5*acceleration.get(i*2)*Math.pow(tau,2));
		position.set((i*2)+5, position.get((i*2)+5)+velocity.get(i*2+5)*tau+0.5*acceleration.get(i*2+1)*Math.pow(tau,2));
		newaccel[i*2]=acceleration.get(i*2);
		newaccel[i*2+1]=acceleration.get(1+i*2);
		int j;
		for(j=0;j<bodiesNum; j++) //circles through calculating summation of acceleration as result of all different forces
		{
			if(i!=j)
			{
				CalcNewAccel(i,j);
			}
		}
		velocity.set((i*2)+4, velocity.get((i*2)+4)+0.5*(acceleration.get(i*2)+newaccel[i*2])*tau);
		velocity.set((i*2)+5, velocity.get((i*2)+5)+0.5*(acceleration.get(i*2+1)+newaccel[i*2+1])*tau);	
	}
	
	
	private double radius(int i, int f)
	{
		double radius= Math.sqrt(Math.pow(position.get(4+2*i)-position.get(4+2*f),2)+Math.pow(position.get(5+2*i)-position.get(5+2*f),2));
		return radius;
	}
	
	public void calc()
	{
		acceleration.clear();
		acceleration.add(0.0);
		acceleration.add(0.0);
		acceleration.add(0.0);
		acceleration.add(0.0);
		bodiesNum=Main.getNumBodies();
		int i,f;
		for (i=0; i<bodiesNum; i++) //circles through number of bodies 
		{
			for(f=0;f<bodiesNum; f++) //circles through calculating summation of acceleration as result of all different forces
			{
				if(i!=f)
				{
					CalcAccel(i,f);
				}
			}
			Verlet(i,f);
		}
	}
	
	public ArrayList<Double> getPos()
	{
		currentpos.clear();
		int i;
		for(i=0;i<bodiesNum;i++)
		{
			currentpos.add(position.get((i*2)+4));
			currentpos.add(position.get((i*2)+5));
		}
		return currentpos;
	}
	
	public ArrayList<Double> getInit()
	{
		currentpos.clear();
		int i;
		for(i=0;i<bodiesNum;i++)
		{
			currentpos.add(position.get((i*2)));
			currentpos.add(position.get((i*2)+1));
		}
		return currentpos;
	}
	
}
