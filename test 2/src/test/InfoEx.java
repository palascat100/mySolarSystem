package test;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class InfoEx
{
	 static ArrayList<Double> list= new ArrayList<Double>();
	 static ArrayList<Double> listloc= new ArrayList<Double>();
	
	public InfoEx()
	{
		
	}
	
	public void getCRN(double[] crn)
	{
		double[] temp= new double[4];
		temp=crn;
		int size= crn.length;
		int i;
		for (i=0;i!=size-2;i++)
		{
			list.add(temp[i]);
		}
		
	}
	
	public void remove(double[] remove)
	{
		double which;
		int i;
		which=remove[2];
		int reference= (int)(2*(which-1));
		for(i=0;i!=1;i++)
		{
			list.remove(reference);
			list.remove(reference);
		}
		
	}
	
	public void modify(double[]mod)
	{
		double[] temp= new double[4];
		temp=mod;
		list.set((int)(2*(temp[2]-1)), (temp[0]));
		list.set((int)((2*(temp[2]-1))+1),temp[1]);
		
	}
	
	public void getLoc(double[] crn)
	{
		double[] temp= new double[3];
		temp=crn;
		int size= crn.length;
		int i;
		for (i=0;i!=size-1;i++)
		{
			listloc.add(temp[i]);
		}
		System.out.println(listloc);
	}
	
	public void removeLoc(double[] remove)
	{
		double which;
		int i;
		which=remove[2];
		int reference= (int)(2*(which-1));
		for(i=0;i!=1;i++)
		{
			listloc.remove(reference);
			listloc.remove(reference);
		}
		System.out.println(listloc);
	}
	
	public void modifyloc(double[]mod)
	{
		double[] temp= new double[3];
		temp=mod;
		listloc.set((int)(2*(temp[2]-1)), (temp[0]));
		listloc.set((int)((2*(temp[2]-1))+1),temp[1]);
		System.out.println(listloc);
	}
	
	
	

}
