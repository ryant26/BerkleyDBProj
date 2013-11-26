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
        String [] tokens = search.split(" ");
        DatabaseEntry key = new DatabaseEntry(tokens[0].getBytes());
        DatabaseEntry data = new DatabaseEntry();

        try{
            cursor.getSearchKey(key, data, LockMode.DEFAULT);
            long initTime = System.currentTimeMillis();
            while (cursor.getNext(key, data, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS){

                String keyString = entryConverter(key);
                String dataString = entryConverter(data);

                addToPrintBuffer(keyString, dataString);

                if (keyString.equalsIgnoreCase(tokens[1])){
                    _queryTime = System.currentTimeMillis() - initTime;
                    System.out.println("Seccessful range");
                    addToPrintBuffer(search, entryConverter(data));
                    printResults();
                    return;
                }

                key = new DatabaseEntry();
                data = new DatabaseEntry();
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

    private String[] getPieces(String input){
        return input.split(" ");
    }
}
