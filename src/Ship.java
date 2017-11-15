import java.util.*;
import java.io.*;

public class Ship {
	private String ShipId;
	private String ShipStatus;
	private String ShipPurpose;
	private Crew[] Crewobj;
	private int CrewCounter=0;
	private final int Crew_Size = 10;
	
	public Ship(String id, String Purpose)
	{
		ShipId = id;
		ShipStatus = "Available";
		ShipPurpose = Purpose;
		
	}
	
	public Ship(String id, String Status, String Purpose)
	{
		ShipId = id;
		ShipStatus = Status;
		ShipPurpose = Purpose;
	}
	
	public String getShipID()
	   {
	      return ShipId;
	   }
	   public String getShipStatus()
	   {
	      return ShipStatus;
	   }
	   public String getShipPurpose()
	   {
	      return ShipPurpose;
	   }
	
	public String toString()
	{
		String ShipInfo = "ID:" +  ShipId + "Status:" + ShipStatus + "Purpose:" + ShipPurpose;
		String CrewInfo = "";
		for(int i = 0; i<CrewCounter; i++)
		{
			CrewInfo += Crewobj[i].toString();
		}
		return ShipInfo + CrewInfo;
	}
	
	public String getShipInfo()
	{
		return "ID:" +  ShipId + "Status:" + ShipStatus + "Purpose:" + ShipPurpose;
	}
	
	public void addCrew(String CrewName, String CrewId, int ExpPoints)
	{
		Crewobj[CrewCounter++] = new Crew(CrewName,CrewId,ExpPoints);
	} 
	
	public void addCrew(String CrewName, String CrewClassification, boolean CrewStatus, String CrewId, int ExpPoints)
	{
		Crewobj[CrewCounter++] = new Crew(CrewName,CrewClassification,CrewStatus,CrewId,ExpPoints);
	}
	
	public String getCrewInfo()
	{
		String CrewInfo = "";
		for(int i = 0; i<CrewCounter; i++)
		{
			CrewInfo += Crewobj[i].toString();
		}
		return CrewInfo;
	}
	
	public void writeToFile( PrintWriter fout) throws IOException
	   {
	      fout.println(ShipId);
	      fout.println(ShipStatus);
	      fout.println(ShipPurpose);
	      
	      fout.println(CrewCounter);
	      for(int i = 0; i < CrewCounter; ++i)
	      {
	         Crewobj[i].writeToFile(fout);
	      }
	   }
	
	public void addBonusPoints(int totalbonus)
	{
		
		int result = totalbonus/CrewCounter;
		
		for(int i =0; i<CrewCounter; i++)
		{
			Crewobj[i].addExpPoints(result);			
		}
	}
	
	public void getTotalExpPoints()
	{
		int totalPoints=0;
		
		if (ShipStatus == "Available")
		{
			for(int i=0; i<CrewCounter; i++)
			{
				totalPoints = totalPoints+Crewobj[i].getExpPoints();
			}
		}
	}
	
	public Crew[] getCrew()
	{
		Crew[] temp = new Crew[CrewCounter];
		for(int i=0; i<CrewCounter; i++)
		{
			temp[i] = new Crew(Crewobj[i]);
		}
		return temp;
	}
	
	public void setShipStatus(String newStatus)
	{
		if(ShipStatus.equalsIgnoreCase("available") && newStatus.equalsIgnoreCase("on task"))
	      {
	         ShipStatus = "on task";
	         for( int i = 0; i <  CrewCounter; ++i )
	         {
	            Crewobj[ i ].getCrewStatus();
	         }
	      }
		
		else if(ShipStatus.equalsIgnoreCase("damaged") && newStatus.equalsIgnoreCase("being repaired"))
	      {
	         ShipStatus = "being repaired";
	      }
		

	      else if(ShipStatus.equalsIgnoreCase("on task") && newStatus.equalsIgnoreCase("damaged"))
	      {
	         ShipStatus = "damaged";
	         for( int i = 0; i <  CrewCounter; ++i )
	         {
	            Crewobj[ i ].getCrewStatus( );
	         }
	      }

	      else if(ShipStatus.equalsIgnoreCase("on task") && newStatus.equalsIgnoreCase("available"))
	      {
	         ShipStatus = "available";
	         for( int i = 0; i <  CrewCounter; ++i )
	         {
	            Crewobj[ i ].getCrewStatus( );
	         }
	      }

	      else if(ShipStatus.equalsIgnoreCase("being repaired") && newStatus.equalsIgnoreCase("available"))
	      {
	         ShipStatus = "available";
	      }
	      else
	      {
	         System.out.println("You have typed wrong status");
	      }
	}

}
