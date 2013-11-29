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
        int i = 0;

        try{
            //Add the first result
            cursor.getSearchKeyRange(key, data, LockMode.DEFAULT);
            addToPrintBuffer(key.toString(), data.toString());

            //Reinitialize the key and data
            key = new DatabaseEntry();
            data = new DatabaseEntry();

            long initTime = System.currentTimeMillis();
            while (cursor.getNext(key, data, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS){
                i++;
                String keyString = entryConverter(key);
                String dataString = entryConverter(data);

                addToPrintBuffer(keyString, dataString);

                if (keyString.compareToIgnoreCase(tokens[1])>=0 || i == 200){
                    _queryTime = System.currentTimeMillis() - initTime;
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

    public void rangeSearch(String search){
        openCursor();
        DatabaseEntry key = new DatabaseEntry();
        DatabaseEntry data = new DatabaseEntry();
        String [] tokens = search.split(" ");
        int i = 0;

        try{
            long initTime = System.currentTimeMillis();
            while(cursor.getNext(key, data,LockMode.DEFAULT) == OperationStatus.SUCCESS){

                String keyString = entryConverter(key);
                String dataString = entryConverter(data);

                if (keyString.compareToIgnoreCase(tokens[0]) >= 0
                        && keyString.compareToIgnoreCase(tokens[1]) <= 0){
                    i++;
                    addToPrintBuffer(keyString, dataString);
                    if(i == 200){
                        break;
                    }
                }

                key = new DatabaseEntry();
                data = new DatabaseEntry();
            }
            _queryTime = System.currentTimeMillis() - initTime;
            printResults();
            System.out.println(i);

        }catch(Exception e){
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
