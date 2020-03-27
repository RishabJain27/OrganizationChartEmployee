package Equinix.OrgChartEmployee;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.*;

public class App 
{ 
		//puts data from List into objects
	private List<Personal> personalRow;
	private List<Organization> organizationRow ;
	private List<Team> TeamRow;
		
	//constructor
	public App(String pathPersonal,String pathOrganization,String pathTeam) throws Exception
	{
		//put all the data from file into a List
		List<String[]> allrowsPersonal = readcsvFile(pathPersonal);
		List<String[]> allrowsOrganization = readcsvFile(pathOrganization);
		List<String[]> allrowsTeam = readcsvFile(pathTeam);
		
		
		//puts data from List into objects
		personalRow = parsePersonalCsv(allrowsPersonal);
		organizationRow = parseOrganizationCsv(allrowsOrganization);
		TeamRow = parseTeamCsv(allrowsTeam);
		
		//set up organization info for each person 
		for (Iterator<Personal> iterPersonal = personalRow.iterator(); iterPersonal.hasNext();) {
			Personal elementPersonal = iterPersonal.next();
			//looks in Organization object for matching id with Personal Object
			for (Iterator<Organization> iterOrg = organizationRow.iterator(); iterOrg.hasNext();) {
				Organization elementOrg = iterOrg.next();
				if(elementOrg.getId().equals(elementPersonal.getId())) {
					elementPersonal.setTitle(elementOrg.getTitle() + " ");
					elementPersonal.setOrganization(elementOrg.getOrganization() + " ");
					break;
				}
			}	
		}
		
	}
	
	//reads csv file and returns contents in a List
    private List<String[]> readcsvFile(String pathName) throws Exception{
	    //Build reader instance
		Reader reader = Files.newBufferedReader(Paths.get(pathName));
	    CSVReader csvReader = new CSVReader(reader);
	    //skip header
	    csvReader.readNextSilently();
	         
	    //Read all rows at once
	    List<String[]> allRows = csvReader.readAll();
	    reader.close();
	    csvReader.close();
	    return allRows;
			
	}
    
    //Set contents in Personal file into our java object Personal
  	private List<Personal> parsePersonalCsv(List<String[]> allrowsPersonal) throws Exception{
  		List<Personal> personalRow = new ArrayList<Personal>();
  		for(String[] row : allrowsPersonal){
  			Personal person = new Personal();
  		    person.setFirst(row[0]);
  		    person.setLast(row[1]);
  		    person.setPhone(row[2]);
  		    person.setAddress(row[3]);
  		    person.setId(row[4]);
  		    personalRow.add(person);
  		}
  		return personalRow;
  	}
  	
	//Set contents in Organization file into our java object Organization
	private List<Organization> parseOrganizationCsv(List<String[]> allrowsOrganization) throws Exception{
		List<Organization> organizationRow = new ArrayList<Organization>();
		for(String[] row : allrowsOrganization){
        	Organization org = new Organization();
        	org.setTitle(row[0]);
        	org.setOrganization(row[1]);
        	org.setId(row[2]);
        	organizationRow.add(org);
        }
		return organizationRow;
		
	}
	
	//Set contents in Team file into our java object Organization
	private List<Team> parseTeamCsv(List<String[]> allrowsTeam) throws Exception{
		List<Team> teamRow = new ArrayList<Team>();
        for(String[] row : allrowsTeam){
        	Team team = new Team();
        	team.setManager(row[0]);
        	team.setOrganization(row[1]);
        	teamRow.add(team);
        }
        return teamRow;
	}
	
	/*Recursive functions that searches all 3 objects(Personal, Organization, and Team)
	  Looks for similar id's in each object and calls itself if id passed in is a manager
	  function will stop at a certain level depending on the user input
	*/
	private void recursiveLookUpID(String id,int level, boolean isLevel) {
		if(level < 0 && isLevel) {
			return;
		}
		for (Iterator<Personal> iterPersonal =personalRow.iterator(); iterPersonal.hasNext();) {
			Personal elementPersonal = iterPersonal.next();
			if(elementPersonal.getId().equals(id)) {
				System.out.println(elementPersonal);
				//reduce level before looking 
				--level;
				for (Iterator<Team> iterTeam = TeamRow.iterator(); iterTeam.hasNext();) {
					Team elementTeam = iterTeam.next();
					if(elementTeam.getManager().equals(id)) {
						String newId = elementTeam.getTeamMember();
						recursiveLookUpID(newId,level,isLevel);
					}
					
				}
			}
		}
	}
		
	public void printOrg(String name, String lastname, int level, boolean fullname, boolean isLevel) {
		//Lookup id for first person in Personal Table and call recursive function to get rest of info
		for (Iterator<Personal> iter = personalRow.iterator(); iter.hasNext();) {
			Personal element = iter.next();
			if(fullname && ( element.getFirst().equals(name)) && element.getLast().equals(lastname)) {
				String id = element.getId();
				recursiveLookUpID(id,level,isLevel);
				System.out.println(); 
			}
			else if(!fullname && ( element.getFirst().equals(name) ||element.getLast().equals(name))){
				String id = element.getId();
				recursiveLookUpID(id,level,isLevel);
				System.out.println(); 
			}
			//Continue looking for additional matches
		}	
	}
	
	//get filepath of data
	private static final String pluginPathPersonal =System.getProperty("user.dir") + "/src/main/resources/Personal.csv";
	private static final String pluginPathOrganization =System.getProperty("user.dir") + "/src/main/resources/Organization.csv";
	private static final String pluginPathTeam =System.getProperty("user.dir") + "/src/main/resources/Team.csv";
	
    public static void main( String[] args ) throws Exception
    {
    	
		int length = args.length; 
		String name = "";
		String lastName = "";
		boolean fullname = false;
		int level = -1;
		boolean isLevel = false;
	    
		// switch statement to parse command line given length of arguments passed
	    switch (length) { 
	    	case 1: 
	            if(isInteger(args[0])) {
	            	System.err.println("First argument can't be a Integer.");
	    			System.exit(0);
	            }
	            else {
	            	name = args[0];
	            }
	            break; 
	        case 2: 
	        	if(isInteger(args[0])) {
	            	System.err.println("First argument can't be a Integer.");
	    			System.exit(0);
	            }
	        	name = args[0];
	        	
	        	if(isInteger(args[1])) {
	            	level = Integer.parseInt(args[1]);
	            	isLevel = true;
	            	if(level < 1) {
	            		System.err.println("Level must be greater than 1");
		    			System.exit(0);
	            	}
	            }
	        	else {
	        		lastName = args[1];
	        		fullname = true;
	        	}
	            break; 
	        case 3: 
	        	if(isInteger(args[0])) {
	            	System.err.println("First argument can't be a Integer.");
	    			System.exit(0);
	            }
	        	name = args[0];
	        	
	        	if(isInteger(args[1])) {
	        		System.err.println("LastName can't be an integer.");
	    			System.exit(0);
	            }
	        	lastName = args[1];
	        	fullname = true;
	        	
	        	if(!isInteger(args[2])) {
	            	System.err.println("Level must be an Integer");
	    			System.exit(0);
	            }
	        	else {
	        		level = Integer.parseInt(args[2]);
	        		isLevel = true;
	            	if(level < 1) {
	            		System.err.println("Level must be greater than 1");
		    			System.exit(0);
	            	}
	        	}
	            break; 
	        default: 
	        	System.err.println("Invalid number of arguments.");
    			System.exit(0); 
	            break; 
	        }  
	    
	    //Create instance of App
    	App app = new App(pluginPathPersonal,pluginPathOrganization,pluginPathTeam);
	    app.printOrg(name, lastName, level, fullname, isLevel);
    }
	 		
	//Checks whether a string is an Integer
	public static boolean isInteger(String str) {
	    if (str == null || str.isEmpty()) {
	        return false;
	    }
	    try {
	        long value = Long.valueOf(str);
	        return value >= -2147483648 && value <= 2147483647;
	    } catch (Exception ex) {
	        return false;
	    }
	}

	
}

