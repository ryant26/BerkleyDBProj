import Create.Create;
import KeySearch.KeySearchInterface;
import com.sleepycat.db.*;
import DataSearch.*;


import java.io.*;
import java.util.*;

public class mainInterface {


	    /*
	     *  the main function
	     */
	    public static void main(String[] args) throws FileNotFoundException, DatabaseException {

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
                    /*
		          //c.my_table.get(null, , data, lockMode)

		    		Cursor cursor = null;

		    		    // Database and environment open omitted for brevity


		    		    // Open the cursor.
		    		    cursor = c.my_table.openCursor(null, null);

		    		    // Cursors need a pair of DatabaseEntry objects to operate. These hold
		    		    // the key and data found at any given position in the database.
		    		    DatabaseEntry foundKey = new DatabaseEntry();
		    		    DatabaseEntry foundData = new DatabaseEntry();

		    		    // To iterate, just call getNext() until the last database record has been
		    		    // read. All cursor operations return an OperationStatus, so just read
		    		    // until we no longer see OperationStatus.SUCCESS
		    		    while (cursor.getNext(foundKey, foundData, LockMode.DEFAULT) ==
		    		        OperationStatus.SUCCESS) {
		    		        // getData() on the DatabaseEntry objects returns the byte array
		    		        // held by that object. We use this to get a String value. If the
		    		        // DatabaseEntry held a byte array representation of some other data
		    		        // type (such as a complex object) then this operation would look
		    		        // considerably different.
		    		        String keyString = new String(foundKey.getData());
		    		        String dataString = new String(foundData.getData());
		    		        System.out.println("Key | Data : " + keyString + " | " +
		    		                       dataString + "");
		    		        System.out.println(c.my_table.get(null, foundKey, foundData, null).toString());
		    	        }
		    		    System.out.println("Enter key:");
                        */

                    KeySearchInterface ksi = new KeySearchInterface(c);
                    ksi.getKey();

		    	}
		    	else if (type.equals("3")) {
                    DataSearchInterface dsi = new DataSearchInterface(c);
                    dsi.getKey();
                    System.out.println("data was:" +c.randData);
		    	}
		    	else if (type.equals("4")) {

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





