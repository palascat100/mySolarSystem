package test;

 
import java.util.ArrayList; 




public class InfoEx
{
	 static ArrayList<Double> list= new ArrayList<Double>();
	 static ArrayList<Double> listloc= new ArrayList<Double>();
	 static ArrayList<Double> line= new ArrayList<Double>();
	 static ArrayList<Double> numline= new ArrayList<Double>();
	 static ArrayList<Double> mustline= new ArrayList<Double>();
	 int numcharge=0,keeptrack=0,numberline,numtrack,track,track2;
	 double[] location=new double[2];
	 double q;
	 int h=1;
	 ElectricField elec;

	
	public InfoEx(ElectricField el)
	{
		numline.clear();
		mustline.clear();
		track2=0;
		elec=el;
	}
	
	public void getCRN(double[] crn)
	{
	    list.add(crn[0]);
	    list.add(crn[1]);
		numline.add(0.0);
		mustline.add(Math.abs(crn[1])*numberline);
		elec.calcmax();

	}
	
	public void remove(double[] remove)
	{
		list.remove((int)(2*(remove[2]-1)));
		list.remove((int)(2*(remove[2]-1)));
		mustline.remove((int)(remove[2]-1));
		elec.calcmax();
	}
	
	public void modify(double[]mod)
	{
		
		list.set((int)(2*(mod[2]-1)), (mod[0]));
		list.set((int)((2*(mod[2]-1))+1),mod[1]);
		mustline.set((int)(mod[2]-1),Math.abs(mod[0])*numberline);
		h--;
		elec.calcmax();
	
	}
	
	public void getLoc(double[] crn)
	{
		listloc.add(crn[0]/24);
	    listloc.add(crn[1]/24);
	}
	
	public void removeLoc(double[] remove)
	{
		listloc.remove((int)(2*(remove[2]-1)));
		listloc.remove((int)(2*(remove[2]-1)));
	}
	
	public void modifyloc(double[]mod)
	{
		
		listloc.set((int)(2*(mod[2]-1)), (mod[0])/24);
		listloc.set((int)((2*(mod[2]-1))+1),mod[1]/24);

	}
	
	public double[] getForce(double distance, double charge1,double charge2, double[] pos)
	{
		double[] forcetemp= new double[2];
		forcetemp[0]=((9.0*charge1*charge2/(Math.pow(distance,3)))*pos[0])/10.0;
		forcetemp[1]=((9.0*charge1*charge2/(Math.pow(distance,3)))*pos[1])/10.0;
		if(forcetemp[0]<.000000001 && forcetemp[1]<.000000001 && forcetemp[0]>-.000000001 && forcetemp[1]>-.000000001) 
		{
			keeptrack++;
		}
		return forcetemp;
	}
	
	public double getRadius(double[]pos, double[] poscharge)
	{
		
		double radius= Math.sqrt((Math.pow((pos[0]-poscharge[0]),2))+(Math.pow((pos[1]-poscharge[1]),2)));
		return radius;
	}
	
	public void getNum(int num) 
	{
		numcharge=num;
		
	}
	
	public double[] rungeKutta(double [] loc,double charge)
	{
		double[] force= new double[2];
		double[] velocity=new double[2];
		track=0;
		velocity[0]=0.0;
		velocity[1]=0.0;
		force[0]=0.0;
		force[1]=0.0;
		double rmag;
		double[] tempposloc= new double[2];
		double[] postemp= new double[2];
		double[] linetemp=new double[2];
		double[] linetempfinal=new double[2];
		double[] k2=new double[2];
		double[] k3=new double[2];
		double[] k4=new double[2];
		linetemp=loc;
		double r;
		int i,d,e,f;
		linetempfinal=loc;
		
		for (i=0;i<numcharge;i++)
		{
			tempposloc[0]=listloc.get(i*2);
			tempposloc[1]=listloc.get(i*2+1);
			rmag=getRadius(loc,tempposloc)+.000000000000001;
			r=list.get(1+i*2)/24.0;
			if(rmag>=r)
			{
				postemp[0]=loc[0]-tempposloc[0];
				postemp[1]=loc[1]-tempposloc[1];
				force[0]+=getForce(rmag,list.get(2*(i)),charge,postemp)[0];
				force[1]+=force[1]+getForce(rmag,list.get(2*(i)),charge,postemp)[1];
			}
			else
			{
				keeptrack++;
				if(track==0)
				{
					numline.set(i,numline.get(i)+1);
					track++;
				}
			}
		}
		if(keeptrack==0)
		{
			double[] k1=velocity;
			linetemp[0]=linetemp[0]+.03*.5*velocity[0];
			linetemp[1]=linetemp[1]+.03*.5*velocity[1];
			velocity[0]=velocity[0]+.03*.5*force[0];
			velocity[1]=velocity[1]+.03*.5*force[1];
			force[0]=0.0;
			force[1]=0.0;
		
			for (d=0;d<numcharge;d++)
			{
		
				tempposloc[0]=listloc.get(d*2);
				tempposloc[1]=listloc.get(d*2+1);
				rmag=getRadius(linetemp,tempposloc)+.000000000000001;
				r=list.get(1+d*2)/24.0;
				if(rmag>=r)
				{
					postemp[0]=linetemp[0]-tempposloc[0];
					postemp[1]=linetemp[1]-tempposloc[1];
					force[0]+=getForce(rmag,list.get(2*(d)),charge,postemp)[0];
					force[1]+=getForce(rmag,list.get(2*(d)),charge,postemp)[1];
					
				}
				else
				{
					keeptrack++;
					if(track==0)
					{
						numline.set(d,numline.get(d)+1);
						track++;
					}
				}
			}
			if(keeptrack==0)
			{
				k2[0]=velocity[0];
				k2[1]=velocity[1];
				linetemp[0]=linetemp[0]+.03*.5*velocity[0];
				linetemp[1]=linetemp[1]+.03*.5*velocity[1];
				velocity[0]=velocity[0]+.03*.5*force[0];
				velocity[1]=velocity[1]+.03*.5*force[1];
				force[0]=0.0;
				force[1]=0.0;
			
				for (e=0;e<numcharge;e++)
				{
					tempposloc[0]=listloc.get(e*2);
					tempposloc[1]=listloc.get(e*2+1);
					rmag=getRadius(linetemp,tempposloc)+.000000000000001;
					r=list.get(1+e*2)/24.0;
					if(rmag>=r)
					{
						postemp[0]=linetemp[0]-tempposloc[0];
						postemp[1]=linetemp[1]-tempposloc[1];
						force[0]+=getForce(rmag,list.get(2*(e)),charge,postemp)[0];
						force[1]+=getForce(rmag,list.get(2*(e)),charge,postemp)[1];
					}
					else
					{
						keeptrack++;
						if(track==0)
						{
							numline.set(e,numline.get(e)+1);
							track++;
						}
					}
				}
				if(keeptrack==0)
				{
					k3[0]=velocity[0];
					k3[1]=velocity[1];
					linetemp[0]=linetemp[0]+.03*velocity[0];
					linetemp[1]=linetemp[1]+.03*velocity[1];
					velocity[0]=velocity[0]+.03*force[0];
					velocity[1]=velocity[1]+.03*force[1];
					force[0]=0.0;
					force[1]=0.0;
					for (f=0;f<numcharge;f++)
						{
							tempposloc[0]=listloc.get(f*2);
							tempposloc[1]=listloc.get(f*2+1);
							rmag=getRadius(linetemp,tempposloc)+.000000000000001;
							r=list.get(1+f*2)/24.0;
							if(rmag>=r)
							{
								postemp[0]=linetemp[0]-tempposloc[0];
								postemp[1]=linetemp[1]-tempposloc[1];
								force[0]+=getForce(rmag,list.get(2*(f)),charge,postemp)[0];
								force[1]+=getForce(rmag,list.get(2*(f)),charge,postemp)[1];
							}
							else
							{
								keeptrack++;
								if(track==0)
								{
									numline.set(f,numline.get(f)+1);
									track++;
								}
							}
						}
						
						if(keeptrack==0)
						{
							k4[0]=velocity[0];
							k4[1]=velocity[1];
							linetempfinal[0]=loc[0]+.03*(1/6)*(k1[0]+2*k2[0]+2*k3[0]+k4[0]);
							linetempfinal[1]=loc[1]+.03*(1/6)*(k1[1]+2*k2[1]+2*k3[1]+k4[1]);
						}
				}
			}
		}
		
		return linetempfinal;
	}
	
	public ArrayList<Double> getLines(double[] loc, double charge, int num)
	{
		track2=0;
		int i;
		numtrack=num;
		line.clear();
		location=loc;
		q=charge;
		keeptrack=0;
		line.add(location[0]);
		line.add(location[1]);
		while(location[0]>=0 && location[1]>=0 && location[0]<=489.0/24.0 && location[1]<=462.0/24.0 && keeptrack==0)
		{
			
			location=rungeKutta(location,q);
			line.add(location[0]);
			line.add(location[1]);
		}
		for(i=0;i<numcharge;i++)
		{
			if(numline.get(i)>mustline.get(i))
			{
				track2++;
				numline.set(i,numline.get(i)-1);	
			}
		}
		if(track2!=0)
		{ 
			line.clear();	
		}
		else
		{
			if(numline.get(numtrack)<mustline.get(numtrack))
			{
				numline.set(numtrack, numline.get(numtrack)+1);
			}
		}

		
		return line;
	}
	

	public void getNumLines(int num)
	{
		int i;
		numberline=num;
		if(mustline.size()!=0)
		{
			
			for (i=0;i<mustline.size();i++)
			{
				mustline.set((int)(i),Math.abs(list.get(2*(i)))*numberline);
			}
			}
	}
	
	public ArrayList<Double> getInfo()
	{
		return mustline;
	}
	
	public ArrayList<Double> getInfo2()
	{
		return list;
	}
	
	public ArrayList<Double> getInfo3()
	{
		return listloc;
	}
	
	public ArrayList<Double> getInfo4()
	{
		return numline;
	}
	
	public void resetlines()
	{
		int i;
		for(i=0;i<numline.size();i++)
		{
			numline.set(i, (double) 0.0);
		}
	}
	
	public double returnmax()
	{
		int i;
		double maxcharge=0;
		for(i=0;i<list.size()/2;i++)
		{
			if(maxcharge<Math.abs(list.get(i*2)))
			{
				maxcharge=Math.abs(list.get(i*2));
			}
		}
		return maxcharge;
	}
	

}
