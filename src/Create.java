import java.util.Random;
import com.sleepycat.db.*;



public class Create {

    // to specify the file name for the table
	//NOTE: I HARDCODED MY USERNAME. CHANGE AT WILL
    public static final String BTREE_TABLE = "/Users/Ryan/Desktop/tmp/btree";
    public static final String HASH_TABLE = "/tmp/cbotto_db/hash_table";
    private static final int NO_RECORDS = 11;
    public String type;
    public Database my_table;
    public String randKey;

    public Create(String type) {
    	this.type = type;
    	this.my_table = null;
    }


	public void createDatabase() {
		try {
		    // Create the database object.
		    // There is no environment for this simple example.
		    DatabaseConfig dbConfig = new DatabaseConfig();
		    if (this.type.equals("btree")) {
			    dbConfig.setType(DatabaseType.BTREE);
			    dbConfig.setAllowCreate(true);
			    this.my_table = new Database(BTREE_TABLE, null, dbConfig);
			    System.out.println(BTREE_TABLE + " has been created");

			    /* populate the new database with NO_RECORDS records */
			    populateTable(my_table,NO_RECORDS);
			    System.out.println("1000 records inserted into" + BTREE_TABLE);
	    	}
	    	else if (this.type.equals("hash")) {
	    		dbConfig.setType(DatabaseType.HASH);
			    dbConfig.setAllowCreate(true);
			    this.my_table = new Database(HASH_TABLE, null, dbConfig);
			    System.out.println(HASH_TABLE + " has been created");

			    /* populate the new database with NO_RECORDS records */
			    populateTable(my_table,NO_RECORDS);
			    System.out.println("1000 records inserted into" + HASH_TABLE);
	    	}

	    	else if (this.type.equals("indexfile")) {

	    	}
	    	else {
	    		System.out.println("Incorrect db_test_option (should be caught in mainInterface");
	    	}




		}
        catch (Exception e1) {
            System.err.println("Test failed: " + e1.toString());
        }
	}

	    void populateTable(Database my_table, int nrecs ) {
	    	int range;
	            DatabaseEntry kdbt, ddbt;
	    	String s;

	    	/*
	    	 *  generate a random string with the length between 64 and 127,
	    	 *  inclusive.
	    	 *
	    	 *  Seed the random number once and once only.
	    	 */
	    	Random random = new Random(1000000);

	            try {
	                for (int i = 0; i < nrecs; i++) {

	    		/* to generate a key string */
	    		range = 64 + random.nextInt( 64 );
	    		s = "";
	    		for ( int j = 0; j < range; j++ )
	    		  s+=(new Character((char)(97+random.nextInt(26)))).toString();

                if (i == 10) randKey = s;
	    		/* to create a DBT for key */
	    		kdbt = new DatabaseEntry(s.getBytes());
	    		kdbt.setSize(s.length());
	    		//System.out.println(kdbt);

	                    // to print out the key/data pair
	                    //System.out.println(kdbt.toString());

	    		/* to generate a data string */
	    		range = 64 + random.nextInt( 64 );
	    		s = "";
	    		for ( int j = 0; j < range; j++ )
	    		  s+=(new Character((char)(97+random.nextInt(26)))).toString();
	                    // to print out the key/data pair
	                    System.out.println(s);
	                    System.out.println("");

	    		/* to create a DBT for data */
	    		ddbt = new DatabaseEntry(s.getBytes());
	    		ddbt.setSize(s.length());

	    		/* to insert the key/data pair into the database */
	                    my_table.putNoOverwrite(null, kdbt, ddbt);
	                }
	            }
	            catch (DatabaseException dbe) {
	                System.err.println("Populate the table: "+dbe.toString());
	                System.exit(1);
	            }
	        }

}
