import Create.Create;
import KeySearch.KeySearchInterface;

import com.sleepycat.db.*;

import DataSearch.*;
import RangeSearch.*;
import Utilities.SearchInterface;

import java.io.*;
import java.util.*;

public class mainInterface {

	    /*
	     *  the main function
	     */
	    public static void main(String[] args) throws FileNotFoundException, DatabaseException {

	    	File yourFile = new File("Answers.txt");
	    	yourFile.delete();
	    	File yourNewFile = new File("Answers.txt");
	    	try {
				yourNewFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	    	Scanner user_input = new Scanner( System.in );
		    boolean option = false;
		    Create c = null;
		    while (option != true) {

		    	System.out.println("Options:");
		    	System.out.println("(1) Create and Populate a Database");
		    	System.out.println("(2) Retrieve a Record with a given Key Value");
		    	System.out.println("(3) Retrieve a Record with a given Data Value");
		    	System.out.println("(4) Retrieve a list of Records with a given range of Key Values");
		    	System.out.println("(5) Destroy the Database");
		    	System.out.println("(6) Exit");

		    	String type = user_input.next();

		    	if (type.equals("1")) {
		    		c = new Create(args[0]);
		    		c.createDatabase();
		    	}
		    	else if (type.equals("2")) {

                    KeySearchInterface ksi = new KeySearchInterface(c, c.randKey);
                    ksi.getKey();

		    	}
		    	else if (type.equals("3")) {
		    		SearchInterface dsi;
		    		if (c.type.equals("indexfile")) {
		    			dsi = new KeySearchInterface(c, c.randData);
		    		}
		    		else {
                    dsi = new DataSearchInterface(c, c.randData);
		    		}
                    dsi.getKey();
                    System.out.println("data was:" + c.randData);
		    	}
		    	else if (type.equals("4")) {
                    SearchInterface rsi = new RangeSearchInterface(c, c.randKey + " "  + c.randKey2);
                    rsi.getKey();

		    	}
		    	else if (type.equals("5")) {
		    		if (c.type.equals("btree")) {
		    		Database.remove(Create.BTREE_TABLE,null,null);
		    		System.out.println("BTree database was destroyed");
		    		}
		    		else if (c.type.equals("hash")) {
		    		Database.remove(Create.HASH_TABLE,null,null);
		    		System.out.println("Hash database was destroyed");
		    		}
		    		else if (c.type.equals("indexfile"))
		    		{
		    			Database.remove(Create.INDEX_TABLE,null,null);

		    			System.out.println("Indexfile database was destroyed");
		    		}
		    		else {
		    			System.out.println("No database to destroy");
		    		}
		    	}
		    	else if (type.equals("6")) {
		    		option = true;
		    		/* close the database and the db environment */
				    //my_table.close();
				    user_input.close();
		    	}
		    	else {
		    		System.out.println("Incorrect option. Please try again");
		    	}
		    }





		    /* to remove the table */
		    //Database.remove(SAMPLE_TABLE,null,null);

	    }
}





