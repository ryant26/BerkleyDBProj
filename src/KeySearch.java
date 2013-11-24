/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:13 PM
 */
import com.sleepycat.db.*;

public class KeySearch extends Search{
    private DatabaseEntry key, data;

    public void searchFor(String search){
        key.setData(search.getBytes());
        key.setSize(search.length());

        try {
            //Perform the search
             long initTime = System.currentTimeMillis();
            _operStatus = _db.get(null, key, data, LockMode.DEFAULT);
            _queryTime = System.currentTimeMillis() - initTime;
        }catch (DatabaseException edb){
            edb.printStackTrace();
        }

        _result = new String(data.getData());

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
