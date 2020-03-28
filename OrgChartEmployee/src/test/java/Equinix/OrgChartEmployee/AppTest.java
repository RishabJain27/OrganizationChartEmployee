package Equinix.OrgChartEmployee;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.PrintStream;
import org.apache.commons.io.FileUtils;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	//get filepath of data
	private static final String pluginPathPersonal =System.getProperty("user.dir") + "/src/main/resources/Personal.csv";
	private static final String pluginPathOrganization =System.getProperty("user.dir") + "/src/main/resources/Organization.csv";
	private static final String pluginPathTeam =System.getProperty("user.dir") + "/src/main/resources/Team.csv";
	
	//path to output test folder
	private static final String outputFolder =System.getProperty("user.dir") + "/src/test/java/Equinix/testOutputs";
		

    @Test
    public void testIsString()
    {
        boolean result = App.isInteger("123");
        assertEquals(true, result);
        
        boolean resultFalse = App.isInteger("text");
        assertEquals(false, resultFalse);
    }
    
    @SuppressWarnings("static-access")
	@Test
	/*Test to check for firstname and one level
	  Sends output to file where expected output is stored
	  and returns whether contents of files are equal 
	 */
    public void testAppFirstName() throws Exception{
    	String fileResult = outputFolder + "/testAppFirstNameResult.txt";
    	String fileExpected = outputFolder + "/testAppFirstNameExpected.txt";
    	
    	File file1 = new File(fileResult);
    	File file2 = new File(fileExpected);
    	
    	//redirect console output
    	PrintStream fileOut = new PrintStream(fileResult);
    	System.setOut(fileOut);
    	
    	App testFirst = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    	String args [] = {"John", "1"};
    	testFirst.main(args);
    	
    	boolean isTwoEqual = FileUtils.contentEquals(file1,file2);
    	
    	assertEquals(true,isTwoEqual);    	
    }
    
    @SuppressWarnings("static-access")
	@Test
	/*Test to check for firstname and lastname	  
	 *Sends output to file where expected output is stored
	  and returns whether contents of files are equal 
	 */
    public void testAppFullName() throws Exception{
    	String fileResult = outputFolder + "/testAppFullNameResult.txt";
    	String fileExpected = outputFolder + "/testAppFullNameExpected.txt";
    	
    	File file1 = new File(fileResult);
    	File file2 = new File(fileExpected);
    	
    	//redirect console output
    	PrintStream fileOut = new PrintStream(fileResult);
    	System.setOut(fileOut);
    	
    	App test = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    	String args [] = {"John", "Doe"};
    	test.main(args);
    	
    	boolean isTwoEqual = FileUtils.contentEquals(file1,file2);
    	
    	assertEquals(true,isTwoEqual);    	
    }
    
    @SuppressWarnings("static-access")
    @Test
    /*Test to check for firstname and no level
	  Sends output to file where expected output is stored
	  and returns whether contents of files are equal 
	 */
    public void testAppNoLevel() throws Exception{
    	String fileResult = outputFolder + "/testAppNoLevelResult.txt";
    	String fileExpected = outputFolder + "/testAppNoLevelExpected.txt";
    	
    	File file1 = new File(fileResult);
    	File file2 = new File(fileExpected);
    	
    	//redirect console output
    	PrintStream fileOut = new PrintStream(fileResult);
    	System.setOut(fileOut);
    	
    	App testNoLevel = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    	String args [] = {"Tim"};
    	testNoLevel.main(args);
    	
    	boolean isTwoEqual = FileUtils.contentEquals(file1,file2);
    	
    	assertEquals(true,isTwoEqual); 
    }
    
    @SuppressWarnings("static-access")
    @Test
    /*Test to check for firstname and one level
      where more levels exist
	  Sends output to file where expected output is stored
	  and returns whether contents of files are equal 
	 */
    public void testAppLevel() throws Exception{
    	String fileResult = outputFolder + "/testAppLevelResult.txt";
    	String fileExpected = outputFolder + "/testAppLevelExpected.txt";
    	
    	File file1 = new File(fileResult);
    	File file2 = new File(fileExpected);
    	
    	//redirect console output
    	PrintStream fileOut = new PrintStream(fileResult);
    	System.setOut(fileOut);
    	
    	App testAppLevel = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    	String args [] = {"Tim", "1"};
    	testAppLevel.main(args);
    	
    	boolean isTwoEqual = FileUtils.contentEquals(file1,file2);
    	
    	assertEquals(true,isTwoEqual);
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void testCommandArguments() {
    	try {
    			App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    			String args [] = {"100"};
    			testArg.main(args);
        	
    		   //Should not reach here
    		   assertEquals(true,false);
    		   
    		} catch (Exception e) {
    		   assertEquals(true,true);
    		}
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void testCommandArguments2() {
    	try {
    			App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
    			String args [] = {"100", "John"};
    			testArg.main(args);
        	
    		   //Should not reach here
    		   assertEquals(true,false);
    		   
    		} catch (Exception e) {
    		   assertEquals(true,true);
    		}
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments3() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"John", "0"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments4() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"0", "John", "Doe"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments5() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"John", "0", "Doe"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments6() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"John", "Doe", "text"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments7() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"John", "Doe", "0"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    @SuppressWarnings("static-access")
  	@Test
     public void testCommandArguments8() {
    	try {
    		App testArg = new App(pluginPathPersonal,pluginPathOrganization , pluginPathTeam);
      		String args [] = {"John", "Doe", "3", "test"};
      		testArg.main(args);
          	
      		 //Should not reach here
      		 assertEquals(true,false);
      		   
      	} catch (Exception e) {
      		assertEquals(true,true);
      	}
    	
    }
    
    
    
}
