package Create;

import java.io.PrintWriter;
import java.util.Random;

import com.sleepycat.db.*;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.Relationship;

@Entity
public class Create {

    // to specify the file name for the table
	//NOTE: I HARDCODED MY USERNAME. CHANGE AT WILL
	public static final String BTREE_TABLE = "/Users/Ryan/Desktop/tmp/btree";
	public static final String HASH_TABLE = "/Users/Ryan/Desktop/tmp/hash";
	public static final String INDEX_TABLE = "/Users/Ryan/Desktop/tmp/index1";
	public static final String INDEX_TABLE2 = "/Users/Ryan/Desktop/tmp/index2";
    private static final int NO_RECORDS = 10;
    public String type;
    public Database my_table;
    public Database my_table_sec;
    public PrintWriter file;
    public static Relationship relate;

    public String randKey;
    public String randKey2;
    private Cursor cursor;
    public String randData;
    //@SecondaryKey(relate= Relationship.ONE_TO_ONE)
   // public DatabaseEntry indexData;


    public Create(String type) {
    	this.type = type;
    	this.my_table = null;
    	this.my_table_sec = null;
    	this.file = null;
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
			    System.out.println("100000 records inserted into" + BTREE_TABLE);
	    	}
	    	else if (this.type.equals("hash")) {
	    		dbConfig.setType(DatabaseType.HASH);
			    dbConfig.setAllowCreate(true);
			    this.my_table = new Database(HASH_TABLE, null, dbConfig);
			    System.out.println(HASH_TABLE + " has been created");

			    /* populate the new database with NO_RECORDS records */
			    populateTable(my_table,NO_RECORDS);
			    System.out.println("100000 records inserted into" + HASH_TABLE);
	    	}

	    	else if (this.type.equals("indexfile")) {

			    dbConfig.setType(DatabaseType.BTREE);
			    dbConfig.setAllowCreate(true);
			    this.my_table = new Database(INDEX_TABLE, null, dbConfig);
			    System.out.println(INDEX_TABLE + " has been created");

			    /* populate the new database with NO_RECORDS records */
			    populateTable(this.my_table,NO_RECORDS);
			    System.out.println("100000 records inserted into" + INDEX_TABLE);


			   this.my_table_sec = new Database(INDEX_TABLE2, null, dbConfig);
			   System.out.println(INDEX_TABLE2 + " has been created");
	    		try{
	                cursor = this.my_table.openCursor(null, null);
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	            DatabaseEntry key = new DatabaseEntry();
	            DatabaseEntry data = new DatabaseEntry();
	            //data.setSize(search.length());
	            //entityData ed = null;
	            try{
	                while (cursor.getNext(key, data, LockMode.DEFAULT) == OperationStatus.SUCCESS){
	                	DatabaseEntry dataString = new DatabaseEntry(key.getData());
	                    DatabaseEntry keyString = new DatabaseEntry(data.getData());
	                	//ed =pi.put(new entityData(keyString, dataString));

	                    this.my_table_sec.put(null, keyString, dataString);

	                    key = new DatabaseEntry();
	                    data = new DatabaseEntry();
	                }
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	            System.out.println("100000 records inserted into" + INDEX_TABLE2);

	    	}
	    	else {
	    		System.out.println("Incorrect db_test_option (should be caught in mainInterface");
	    	}
		}catch (Exception e1) {
            System.err.println("Test failed: " + e1.toString());
        }
	}

	void populateTable(Database my_table, int nrecs ) {
        int range;
        DatabaseEntry kdbt, ddbt;
        String s;
        boolean set = false;

        /*
         *  generate a random string with the length between 64 and 127,
         *  inclusive.
         *
         *  Seed the random number once and once only.
         */
        Random random = new Random(1000000);
        //Random initials for getting random keys and data values
        Random ranKeysAndData = new Random(123456);
        int keyVal1 = ranKeysAndData.nextInt(nrecs/2);
        int keyVal2 = ranKeysAndData.nextInt(nrecs/2) + keyVal1;
        int dataVal = ranKeysAndData.nextInt(nrecs);

            try {
            for (int i = 0; i < nrecs; i++) {

            /* to generate a key string */
            range = 64 + random.nextInt( 64 );
            s = "";
            for ( int j = 0; j < range; j++ )
              s+=(new Character((char)(97+random.nextInt(26)))).toString();

            if (i == keyVal1) randKey = s;
            if (i > keyVal2 && !set){
                if(s.compareToIgnoreCase(randKey)>0){
                    randKey2 = s;
                    set = true;
                }
            }
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
            //System.out.println(s);
            if (i == dataVal) randData = s;
            //System.out.println("");

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


