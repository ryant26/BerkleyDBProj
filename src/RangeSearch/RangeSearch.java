package RangeSearch;

import com.sleepycat.db.*;
import Utilities.Search;

/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:14 PM
 */
public class RangeSearch extends Search {

    private Cursor cursor;

    public RangeSearch(Database db){
        super(db);
    }

    public void searchFor(String search){
        openCursor();
        DatabaseEntry key = new DatabaseEntry("".getBytes());
        DatabaseEntry data = new DatabaseEntry("".getBytes());
        boolean found = false;

        try{
            long initTime = System.currentTimeMillis();
            while (cursor.getNext(key, data, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS){

                String keyString = new String(key.getData());
                String dataString = new String(data.getData());

                if (keyString.equalsIgnoreCase(search)){
                    _queryTime = System.currentTimeMillis() - initTime;
                    addToPrintBuffer(search, entryConverter(data));
                    printResults();
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openCursor(){
        try{
            cursor = _db.openCursor(null, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
