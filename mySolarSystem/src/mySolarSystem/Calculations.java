package mySolarSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calculations 
//class that does all calculations for orbiting objects
{
	double tau=0.05; //fastest: .05 slowest:.02
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
		//Initial conditions for 2 bodies
		mass.add(200.0);
		mass.add(10.0);
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
		velocity.add(30.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(0.0);
		velocity.add(30.0);
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
		acceleration.set((i*2),acceleration.get(i*2)+(667.4*(m)/(radius*radius*radius)*(-position.get(bodiesNum*2+2*i)+position.get(bodiesNum*2+2*f))));
		acceleration.set((i*2+1),acceleration.get(1+i*2)+(667.4*(m)/(radius*radius*radius)*(-position.get(bodiesNum*2+1+2*i)+position.get(bodiesNum*2+1+2*f))));
		
	}
	
	private void CalcNewAccel(int i, int f)
	{
		//takes in mass with units 10^22 kg and km
		double m=mass.get(f);
		double radius=radius(i,f);
		newaccel[i*2]=newaccel[i*2]+(667.4*(m)/(radius*radius*radius)*(-position.get(bodiesNum*2+2*i)+position.get(bodiesNum*2+2*f)));
		newaccel[i*2+1]=newaccel[i*2+1]+(667.4*(m)/(radius*radius*radius)*(-position.get(bodiesNum*2+1+2*i)+position.get(bodiesNum*2+1+2*f)));
		
	}
	
	private void Verlet(int i, int f)
	{
		//timestep Verlet 
		//calculates position using x+vt+(1/2)at^2
		position.set((i*2)+4, position.get((i*2)+bodiesNum*2)+velocity.get(i*2+bodiesNum*2)*tau+0.5*acceleration.get(i*2)*Math.pow(tau,2));
		position.set((i*2)+5, position.get((i*2)+bodiesNum*2+1)+velocity.get(i*2+bodiesNum*2+1)*tau+0.5*acceleration.get(i*2+1)*Math.pow(tau,2));
		int j;
		for(j=0;j<bodiesNum; j++) //circles through calculating summation of acceleration as result of all different forces
		{
			if(i!=j)
			{
				CalcNewAccel(i,j);
			}
		}
		//calculates velocity using v+(1/2)(a+a1)*t
		velocity.set((i*2)+bodiesNum*2, velocity.get((i*2)+bodiesNum*2)+0.5*(acceleration.get(i*2)+newaccel[i*2])*tau);
		velocity.set((i*2)+bodiesNum*2+1, velocity.get((i*2)+bodiesNum*2+1)+0.5*(acceleration.get(i*2+1)+newaccel[i*2+1])*tau);	
	}
	
	
	private double radius(int i, int f)
	{
		//distance formula
		double radius= Math.sqrt(Math.pow(position.get(4+2*i)-position.get(4+2*f),2)+Math.pow(position.get(5+2*i)-position.get(5+2*f),2));
		return radius;
	}
	
	public void calc()
	{
		//c;ears acceleration arrays and array lists
		newaccel[0]=0;
		newaccel[1]=0;
		newaccel[2]=0;
		newaccel[3]=0;
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
				if(i!=f) //only performs acceleration of bodies on each other and not on themselves
				{
					CalcAccel(i,f);
				}
			}
			Verlet(i,f); //after acceleration, Verlet is performed to find each body's velocity and new position
		}
	}
	
	public ArrayList<Double> getPos()
	{
		currentpos.clear();
		int i;
		for(i=0;i<bodiesNum;i++)
		{
			currentpos.add(position.get((i*2)+bodiesNum*2));
			currentpos.add(position.get((i*2)+bodiesNum*2+1));
		}
		return currentpos; //allows panel to get coordinates of where it should draw lines
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
		return currentpos; //returns initial conditions to panel so they can be saved if user decides to reset
	}
	
}

