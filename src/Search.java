/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:15 PM
 */
import com.sleepycat.db.*;

public abstract class Search {
    protected long _queryTime;
    protected Database _db;
    protected OperationStatus _operStatus;
    protected String _result;

    public abstract void searchFor(String search);

    @Override
    public String toString(){
        return String.valueOf(_queryTime);
    }

    public Database get_db() {
        return _db;
    }

    public void set_db(Database _db) {
        this._db = _db;
    }

    public long get_queryTime() {
        return _queryTime;
    }

    public void set_queryTime(long _queryTime) {
        this._queryTime = _queryTime;
    }
}
