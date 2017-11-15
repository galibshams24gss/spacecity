import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SpaceCity
{
    private Ship [ ] ships;

    private Scanner kb;

    private final int DISPLAY = 1;
    private final int START_TASK = 2;
    private final int END_TASK = 3;
    private final int CHANGE_SHIP_STATUS = 4;
    private final int ADD_SHIP = 5;
    private final int ADD_CREW = 6;
    private final int SAVE = 7;
    private final int EXIT = 8;

    private final int DISPLAY_ALL = 1;
    private final int DISPLAY_SHIPS = 2;
    private final int DISPLAY_CREW = 3;
    private final int DISPLAY_SINGLE_SHIP = 4;
    private final int DISPLAY_SINGLE_CREW = 5;
    private final int DISPLAY_EXIT = 6;

    private final int SIZE = 100;
    private int shipCounter;

    public static void main( String [ ] args ) throws IOException
    {
         SpaceCity sc = new SpaceCity( );
         sc.load( );
         sc.run( );
         System.out.println( "That's all folks" );
    }

    public SpaceCity( )
    {
         kb = new Scanner( System.in );
         ships = new Ship[ SIZE ];
         shipCounter = 0;
    }
    
    public void load( ) throws IOException
    {
    	System.out.println( "Enter file name>" );
    	String filename = kb.nextLine();
    	
    	File f = new File(filename);
    	Scanner fin= new Scanner(f);
    	
    	boolean found = false;
    	
    	if(f.length()==0)
    	{
    		System.out.println( "no file" );
    	}
    	
    	while(fin.hasNextLine() && !false)
    	{
    		String line = fin.nextLine();
    		String line1 = fin.nextLine();
    		String line2 = fin.nextLine();
    		ships[shipCounter] = new Ship(line,line1,line2);
    		int num = fin.nextInt();
    		fin.nextLine();
    		
    		for(int i=0; i<shipCounter; i ++)
    		{
    			String name = fin.nextLine();
    			String id = fin.nextLine();
    			String classification = fin.nextLine();
    			boolean status = fin.nextBoolean();
    			int exppoints = fin.nextInt();
    			fin.nextLine();
    			
    			ships[shipCounter].addCrew(name,id,classification,status,exppoints);
    		}
    		shipCounter++;
    	}
    		fin.close();
    }
    
    public void run( ) throws IOException
    {
         int choice = -1;

         while( choice != EXIT )
         {
              displayMainMenu( );
              System.out.print( "Enter choice >> " );
              choice = kb.nextInt( );
              kb.nextLine( );
              process( choice );
         }
    }

    private void process( int choice ) throws IOException
    {
         switch( choice )
         {
              case DISPLAY:
                   runDisplay( );
                 break;

              case START_TASK:
                   addTask( );
                 break;

              case END_TASK:
                   endTask( );
                 break;

              case CHANGE_SHIP_STATUS:
                   shipStatus( );
                 break;

              case ADD_SHIP:
                   addShip( );
                 break;

              case ADD_CREW:
                   addCrew( );
                 break;

              case SAVE:
                   save( );
                 break;

              case EXIT:
                   // just trap this choice
                 break;

              default:
                   System.out.println("That is not a valid choice");
                 break;
         }
    }
    
    private void displayMainMenu( )
    {
    	System.out.println( "\nSpaceCity Main Menu" );
        System.out.println( "\t" + DISPLAY + ". Display Menu" );
        System.out.println( "\t" + START_TASK + ". Add Task" );
        System.out.println( "\t" + END_TASK + ". End Task" );
        System.out.println( "\t" + CHANGE_SHIP_STATUS
                                 + ". Change Ship Status" );
        System.out.println( "\t" + ADD_SHIP + ". Add Ship" );
        System.out.println( "\t" + ADD_CREW + ". Add Crew" );
        System.out.println( "\t" + SAVE + ". Save" );
        System.out.println( "\t" + EXIT + ". Exit" );
    }
    
    /*
     * This method shows all the information about all the Ships
     * and Crews in the array.
     *
     */
    public void showAll( )
    {
    	if(shipCounter == 0)
    	{
    		System.out.println( "No ship found" );
    	}
    	else
    	{
    		for(int i = 0; i <shipCounter; i ++)
    		{
    			System.out.println(ships[i].toString());
    		}
    	}
    }
    
    public void runDisplay( ) throws IOException
    {
        int choice = -1;

        while( choice != DISPLAY_EXIT )
        {
             displayDisplayMenu( );
             System.out.print( "Enter display choice >> " );
             choice = kb.nextInt( );
             kb.nextLine( );
             processDisplay( choice );
        }

   }

   public void processDisplay( int choice ) throws IOException
   {
        switch( choice )
        {
             case DISPLAY_ALL:
                  showAll( );
                break;

             case DISPLAY_SHIPS:
                  showShips( );
                break;

             case DISPLAY_CREW:
                  showCrew( );
                break;

             case DISPLAY_SINGLE_SHIP:
                  showSingleShip( );
                break;

             case DISPLAY_SINGLE_CREW:
                  showSingleCrew( );
                break;

             case DISPLAY_EXIT:
                  // just trap this choice
                break;

             default:
                  System.out.println(choice + " is not a valid add " +
                                     "choice" );
                break;
        }
   }
   
   /*
    * This is now based on the Ship id
    *
    * This method really should check that the Ship has a Crew
    * If a Ship doesn't have a Crew then it can still be available,
    * but its total points will be 0, so it can't be assigned a
    * Task.
    *
    */
   public void addTask( )
   {
	   if(shipCounter == 0)
	   {
		   System.out.println("No ships found");
	   }
	   else if(shipCounter > 0)
	   {
		   System.out.print("Enter ship id>");
		   String sid = kb.nextLine();
		   if(searchShip(sid) != null)
		   {
			   if(ships[shipCounter].getShipStatus().equalsIgnoreCase("available"))
			   {
				   System.out.print("Enter points>");
				   int pts = kb.nextInt();
				   kb.nextLine();
				   if(ships[shipCounter].getTotalExpPoints() >= pts)
				   {
						   ships[shipCounter].setShipStatus("on task");
		
				   }
				   else
				   {
					   System.out.println("Not enough points");
				   }
				   
			   }
			   else
			   {
				   System.out.println("Ship status not available");
			   }
		   }
		   else
		   {
			   System.out.println("Ship id not found");
		   }
	   }
	   else
	   {
		   System.out.println("No ship found");
	   }
   }
   
   /*
    * Now we need to ask for the Ship id.
    * Only if this Ship id is found AND that Ship
    * is "on task", then we can end the Task.
    *
    * Every crew member gets 10 experience points for finishing
    * a Task, then we have to deal with bonus points.
    *
    * Just divide these bonus points equally
    * between the Crew, discarding any left over points.
    *
    * For extra marks, a better method that does not discard any
    * bonus points can be coded, not required here in the basic
    * assignment.
    *
    */
   public void endTask( )
   {
	   if(shipCounter == 0)
	   	{
	   		System.out.println( "No ship found" );
	   	}
	   	else
	   	{
	   		System.out.print("Enter ship id>");
			String sid = kb.nextLine();
			if(searchShip(sid) != null)
			{
				if(ships[shipCounter].getShipStatus().equalsIgnoreCase("on task"))
				{
					System.out.print("Damaged?> y/n");
					char ch = kb.nextLine().toLowerCase().charAt(0);
					if(ch=='y')
					{
						ships[shipCounter].setShipStatus("damaged");
						for(int i=0; i< shipCounter; i++)
						{
							Crew[] cw = ships[i].getCrew();
							for(int j=0; j<cw.length; j++)
							{
								cw[j].getCrewStatus();
							}
						}
					}
					else
					{
						ships[shipCounter].setShipStatus("available");
						System.out.println( "Want to add bonus?" );
						char b = kb.nextLine().toLowerCase().charAt(0);
						if(b=='y')
						{
							System.out.println( "How much?" );
							int num = kb.nextInt();
							for(int k=0; k<shipCounter; k++)
							{
								ships[k].addBonusPoints(num);
							}
						}
						else
						{
							System.out.println( "No bonus" );
						}
					}
						
				}
			}
			else
            {
               System.out.println("The Ship ID that you have entered is not recognized");
            }
	   	}

 }
   
   /*
    * This method is largely the same as it was in AssignC, the
    * difference being that now we need to look for the Ship
    * with the requested id in the ships array
    *
    */
   public void shipStatus( )
   {
	   if(shipCounter == 0)
	   	{
	   		System.out.println( "No ship found" );
	   	}
	   	else
	   	{
	   		System.out.print("Enter ship id>");
			String sid = kb.nextLine();
			if(searchShip(sid) != null)
			{
				System.out.print("Enter new ship status>");
				String newsid = kb.nextLine();
				ships[shipCounter].setShipStatus(newsid);
			}
			else
			{
				System.out.println("no ship id found>");
			}
	   	}
   }
   
   /*
    * This method displays information about the Ships in the
    * array, no Crew information
    *
    */
   public void showShips( )
   {
	   if(shipCounter == 0)
   	{
   		System.out.println( "No ship found" );
   	}
   	else
   	{
   		for(int i=0; i <shipCounter; i++)
   		{
   		System.out.println(ships[i].getShipInfo());
   		}
   	}
   }
    
   
   /*
    * This method shows all the information about all the Crew
    * in the Ships, just the Crew information, nothing about the
    * Ships.
    *
    * You can call the getCrew method from the Ship class and
    * return a, no privacy leak, of the Crew array for a Ship,
    * then display that array. Then move on to the next Ship in the
    * array and repeat.
    *
    * You could write a method in the Ship class that returns
    * a String that contained the information for the Crew in that
    * Ship, or you could, sort of, use lots of get methods
    * (not recommended)
    *
    * Of course, any methods from Ship class should only be called
    * if there is a least one Ship object in the array.
    *
    */
   public void showCrew( )
   {
	   if(shipCounter == 0)
	   	{
	   		System.out.println( "No ship found" );
	   	}
	   	else
	   	{
	   		for(int i=0; i <shipCounter; i++)
	   		{
	   		System.out.println(ships[i].getCrewInfo());
	   		}
	   	}
   }
   
   /*
    * This method displays information about a single Ship, the
    * user has to enter the id of the desired Ship
    *
    * If the Ship is found, then all the information about this Ship,
    * including any Crew in this Ship, is displayed to the Screen.
    *
    * As always, if there are no Ships in the array, then the user
    * is not asked to information that cannot possibly lead to a
    * positive result.
    *
    */
   public void showSingleShip( )
   {
	   if(shipCounter == 0)
	   	{
	   		System.out.println( "No ship found" );
	   	}
	   else
	   {
		   System.out.print("Enter ship id>");
		   String sid = kb.nextLine();
		   if(searchShip(sid) != null)
		   {
				   System.out.println( ships[shipCounter].getShipInfo());
		   }
		   else
		   {
			   System.out.println( "No ship id found" );
		   }
	   }
   }
   
   /*
    * This method displays information about a single Crew member, nothing
    * is displayed about the Ship the Crew is in. This method is slightly
    * more complex, although not much more, than displaying the
    * information about all the Crew.
    *
    * Go through the Ship array and get a (non privacy leak) copy
    * of each Crew array, stop when we find the particular Crew, whose
    * id was entered by the user. As always, if there are no Ships in
    * the array, the user should not be asked for useless information.
    *
    */
   public void showSingleCrew( )
   {
	   if(shipCounter == 0)
	   	{
	   		System.out.println( "No ship found" );
	   	}
	   else
	   {
		   System.out.print("Enter crew id>");
		   String cid = kb.nextLine();
		   boolean found = false;
		   for(int i=0; i<shipCounter && !found; i++)
		   {
			   Crew[] cr = ships[i].getCrew();
			   for(int j=0; j<cr.length; j++)
			   {
				   if(cid.equals(cr[j].getCrewId()))
				   {
					   found = true;
					   System.out.println(cr[j]);
				   }
				   else
				   {
					   System.out.println( "No crew id found" );
				   }
			   }
		   }
		   if(found)
		   {
			   System.out.println( "crew id not found" );
		   }
	   }
   }
   
   /*
    * This method adds a Ship to the array from the keyboard,
    * NO CREW INFORMATION, just the Ship.
    *
    * The id for the Ship has to be unique and this method must
    * check that the id is in fact unique, so make that the first
    * thing that is asked from the user.
    *
    * If the id entered by the user is already in use, then this
    * method can ask the user for another id, or return to the
    * main menu, either way fine.
    *
    * Provided that the Ship id is unique, then all the rest of the
    * Ship information is requested from the user and the Ship
    * object is placed in the next free space of the array.
    *
    * Recall that from the keyboard, only the id and the purpose
    * of the Ship is requested, the status is automatically
    * "available" as the Ship cannot be assigned a task before it
    * created.
    *
    * The other thing that needs to be checked first is that there
    * is actually a free space in the Ship array. If there isn't
    * then the user is informed of this and no information is
    * requested from the user.
    *
    */
   public void addShip( )
   {
	   System.out.print("Enter ship id>");
	   String sid = kb.nextLine();
	   if(searchShip(sid) == null)
	   {
		   System.out.print("Enter ship status>");
		   String sstatus = kb.nextLine();
		   System.out.print("Enter ship purpose>");
		   String spurpose = kb.nextLine();
		   
		   ships[shipCounter++] = new Ship(sid,sstatus,spurpose);		   
	   }
	   else
	   {
		   System.out.println( "A ship with that id already exists" );
	   }
   }
   
   /*
    * If there are no Ships in the array, then we can't enter any
    * Crew.
    *
    * To add a Crew, first we have to find the Ship id, provided that the
    * Ship id is found, then we have to check that there is space in
    * the Crew array of that Ship.
    *
    * Then we can get the user to enter the Crew id, which has to be
    * checked against all the Crew id's in every Ship, to make sure
    * that it is unique.
    *
    * So long as these checks are passed, then we get the user to
    * enter the rest of the Crew information for keyboard entry.
    *
    * To make this a bit easier, let the addCrew( ) method of the
    * Ship class deal with trying to add a Crew member to an already
    * full array. Really should be dealt with in this method but
    * time is getting short.
    *
    */
   public void addCrew( )
   {	   
	   	   System.out.print("Enter ship id>");
		   String sid = kb.nextLine();
		   if(searchShip(sid) != null)
		   {
			   System.out.print("Enter crew id>");
			   String cid = kb.nextLine();
			   
			   boolean found = false;
			   
			   for(int i=0; i<shipCounter && !found; i++)
			   {
				   Crew[] crw = ships[i].getCrew();
				   for(int j=0; j<crw.length; j++)
				   {
					   if(cid.equals(crw[i].getCrewId()))
					   {
						   found = true;
						   break;
					   }   
				   }
			   }
			   if(found)
			   {
				   System.out.print("Enter crew name>");
				   String crname = kb.nextLine();
				   System.out.print("Enter crew id>");
				   String crid = kb.nextLine();
				   System.out.print("Enter exppoints>");
				   int crexppoints = kb.nextInt();
				   kb.nextLine();
				   
				   ships[shipCounter].addCrew(crname,crid,crexppoints);
			   }
			   else
			   {
				   System.out.println("crew id found>");
			   }
		   }
		   else
		   {
			   System.out.println("ship id not found>");
		   }
	   }

   
   public void displayDisplayMenu( )
   {
        System.out.println( "\nSpaceCity Display Menu" );
        System.out.println( "\t" + DISPLAY_ALL + ". Display All" );
        System.out.println( "\t" + DISPLAY_SHIPS + ". Display Ships" +
                            " only" );
        System.out.println( "\t" + DISPLAY_CREW + ". Display All Crew" +
                            " only"  );
        System.out.println( "\t" + DISPLAY_SINGLE_SHIP
                                 + ". Display Single Ship" );
        System.out.println( "\t" + DISPLAY_SINGLE_CREW + ". Display" +
                            " Single Crew" );
        System.out.println( "\t" + DISPLAY_EXIT + ". Return to main " +
                            "menu" );

   }

   /*
    * If you have written writeToFile methods in Crew and Ship classes,
    * it is just a matter of calling those methods to write the
    * array back to a text file.
    *
    * Otherwise you could have Crew and Ship return Strings
    * with the desired information in the correct format and then
    * just write those Strings to the output file.
    *
    * Crew would return its String to Ship and Ship would return
    * both the Ship and Crew information to this method.
    *
    * You could try using lots of get method calls, but that is
    * definitely NOT recommended.
    *
    * You CANNOT use the toString( ) methods of Ship and Crew to
    * do this job. All you want to write back to the file is the
    * value of the attributes (and the number of Crew in a Ship),
    * see page 10 for the format.
    *
    * The toString( ) methods are for screen output as they
    * include text that is cannot be included in the file output.
    *
    * The toString( ) methods should give you the basic idea however.
    *
    */
  public void save( ) throws IOException
  {
	  System.out.println("Please enter File name: ");
      String filename = kb.nextLine();
      File f = new File (filename);
      PrintWriter fout = new PrintWriter(f);
      
      for(int i = 0; i < shipCounter; i++)
      {
         ships[i].writeToFile(fout);
      }
      fout.close();
  }
  
  public Ship searchShip(String shipid)
  {  
	  for (int i = 0; i <shipCounter; i ++)
	  {
		  if(ships[i].getShipID().equals(shipid))
		  {
			  return ships[i];
		  }
	  }
	  return null;
  }
}
    