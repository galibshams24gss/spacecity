import java.util.*;
import java.io.*;

public class Crew {
	private String CrewName;
	private String CrewClassification;
	private boolean CrewStatus;
	private String CrewId;
	private int ExpPoints;
	
	public Crew(String CrewName, String CrewId, int ExpPoints){
		this.CrewName = CrewName;
		setCrewClassification();
		CrewStatus = true;
		this.CrewId = CrewId;
		this.ExpPoints = ExpPoints;
	}
	
	public Crew(String CrewName, String CrewClassification, boolean CrewStatus, String CrewId, int ExpPoints){
		this.CrewName = CrewName;
		this.CrewClassification = CrewClassification;
		this.CrewStatus = CrewStatus;
		this.CrewId = CrewId;
		this.ExpPoints = ExpPoints;
	}
	
	public Crew (Crew c)
	{
		CrewName = c.CrewName;
		CrewClassification = c.CrewClassification;
		CrewStatus = c.CrewStatus;
		CrewId = c.CrewId;
		ExpPoints = c.ExpPoints;
	}
	
	public void status()
	{
		CrewStatus = !CrewStatus; 
	}
	
	public void setCrewClassification()
	{
		if(ExpPoints >=0 && ExpPoints <=20)
	      {
			CrewClassification = "Trainee";
	      }
	      else if(ExpPoints >=21 && ExpPoints <=40)
	      {
	    	  CrewClassification = "Trained";
	      }
	      else if(ExpPoints >=41 && ExpPoints <=100)
	      {
	    	  CrewClassification = "Experienced";
	      }
	      else if(ExpPoints >100)
	      {
	    	  CrewClassification = "Specialist";
	      }
	}
	
	public void addExpPoints()
	{
		ExpPoints = ExpPoints+10;
		setCrewClassification();
	}
	
	public void addExpPoints(int exppoints)
	{
		if(exppoints >= 0)
		{
		ExpPoints = ExpPoints+exppoints;
		setCrewClassification();
		}
		else
		{
			System.out.println("Not Adding Experience Points as it's < 0");
		}
	}
	
	public String getCrewName()
	   {
	      return  CrewName;
	   }
	   public String getCrewClassification()
	   {
	      return CrewClassification;
	   }
	   public boolean getCrewStatus()
	   {
	      return CrewStatus;
	   }
	   public String getCrewId()
	   {
	      return CrewId;
	   }
	   public int getExpPoints()
	   {
	      return ExpPoints;
	   }
	   
	public String toString()
	   {
		
		return "Name: " + CrewName +  " ID: " + CrewId +  " Classification: " + CrewClassification +  " Points: "  + ExpPoints +  " Status:  "  + CrewStatus;                                                                                        assification +  " Points: "  + experiencePoints +  " Status:  "  + crewStatus;
	   }
	
	public void writeToFile( PrintWriter fout ) throws IOException
	   {
	      fout.println(CrewName);
	      fout.println(CrewId);
	      fout.println(CrewClassification);
	      fout.println(ExpPoints);
	      fout.println(CrewStatus);
	   }
}
