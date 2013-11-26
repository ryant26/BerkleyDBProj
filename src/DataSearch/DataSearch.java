package DataSearch;
import com.sleepycat.db.*;
import Utilities.Search;

/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:14 PM
 */
public class DataSearch extends Search {

    private Cursor cursor;

    public DataSearch(Database db){
        super(db);
    }

    public void searchFor(String search){
        openCursor();
        DatabaseEntry key = new DatabaseEntry();
        DatabaseEntry data = new DatabaseEntry();
        data.setSize(search.length());
        int i = 0;

            long initTime = System.currentTimeMillis();
        try{
            while (cursor.getNext(key, data, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS){
                String keyString = new String(key.getData());
                String dataString = new String(data.getData());

                if (dataString.equalsIgnoreCase(search)){
                    cursor.close();
                    _queryTime = System.currentTimeMillis() - initTime;
                    addToPrintBuffer(keyString, entryConverter(data));
                    printResults();
                    return;
                }
                key = new DatabaseEntry();
                data = new DatabaseEntry();
            }
            System.out.println("Entry not in Database");
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
