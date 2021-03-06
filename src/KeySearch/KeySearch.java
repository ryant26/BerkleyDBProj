package KeySearch;
import com.sleepycat.db.*;
import Utilities.Search;
/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:13 PM
 */


public class KeySearch extends Search {
    private DatabaseEntry key, data;

    public KeySearch(Database db){
        super(db);
    }

    public void searchFor(String search){
        /**
         * This is where most of the work happens to search for
         * a key in a database.
         * @param String - your search key
         */
        key = new DatabaseEntry();
        data = new DatabaseEntry();
        key.setData(search.getBytes());
        key.setSize(search.length());

        try {
            //Perform the search
             //long initTime = System.currentTimeMillis();
            long initTime = System.nanoTime();
            _operStatus = _db.get(null, key, data, LockMode.DEFAULT);
            _queryTime = (System.nanoTime() - initTime)/1000;
        }catch (DatabaseException edb){
            edb.printStackTrace();
        }
        if (_operStatus == OperationStatus.SUCCESS){
            addToPrintBuffer(search, entryConverter(data));
            printResults();
        }else System.out.println("No Results match your query");

    }

    public void rangeSearch(String searchKey){
        //ignore
    }

    public DatabaseEntry getKey() {
        return key;
    }

    public void setKey(DatabaseEntry key) {
        this.key = key;
    }

    public DatabaseEntry getData() {
        return data;
    }

    public void setData(DatabaseEntry data) {
        this.data = data;
    }
}
