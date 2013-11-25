/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:15 PM
 */
import com.sleepycat.db.*;

import java.io.PrintWriter;

public abstract class Search {

    protected long _queryTime;
    protected Database _db;
    protected OperationStatus _operStatus;
    protected String _printBuffer = "";

    public Search(Database db){
        _db = db;
    }

    protected String entryConverter(DatabaseEntry entry){
        return new String(entry.getData());
    }

    public abstract void searchFor(String search);

    protected void addToPrintBuffer(String key, String data){
        _printBuffer += key + "\n" + data + "\n\n";
    }

    protected void printResults(){
        try{
            PrintWriter pw = new PrintWriter("Answers.txt", "UTF-8");
            pw.print(_printBuffer);
            pw.close();
            System.out.print("Print Buffer: \n");
            System.out.println(_printBuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printOpStatus(){
        System.out.println(_operStatus);
    }

    @Override
    public String toString(){
        String result = "Query Time: " + _queryTime + "\n"
                        + "Results: \n" + _printBuffer;
        return result;
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
