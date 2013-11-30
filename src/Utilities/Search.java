package Utilities; /**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 2:15 PM
 */
import com.sleepycat.db.*;

import java.io.FileWriter;
import java.io.PrintWriter;

public abstract class Search {

    protected long _queryTime;
    protected Database _db;
    protected OperationStatus _operStatus;
    protected String _printBuffer = "";
    protected int numResults;

    public Search(Database db){
        _db = db;
    }

    protected String entryConverter(DatabaseEntry entry){
        /**
         * Returns a string that has been converted from the bytes
         * for the Berk DB gives us.
         * @param Database entry
         * @return String
         */
        return new String(entry.getData());
    }

    public abstract void searchFor(String search);

    protected void addToPrintBuffer(String key, String data){
        /**
         * This method is used to add lines to the print buffer.
         * Which will eventually be used to print to "Answers.txt"
         * @param String - key used / returned from query
         * @parm String - data used / returned from query
         */
        _printBuffer += key + "\n" + data + "\n\n";
        numResults++;
    }

    protected void printResults(){
        /**
         * This method prints all results in the print buffer
         * into Answers.txt as per assingment description
         */
        try{
        	FileWriter pw = new FileWriter("Answers.txt", true);
            pw.write(_printBuffer);
            pw.close();
        }catch (Exception e){
            System.out.println("Could not write to txt file");
            e.printStackTrace();
        }
    }

    public void printOpStatus(){
        //Mostly for debugging
        System.out.println(_operStatus);
    }

    @Override
    public String toString(){
        /**
         * Use this to print data about each search class
         */
        String result = "Results: " + numResults + "\n"
                        + "Query Time: " + _queryTime;
        return result;
    }

    public abstract void rangeSearch(String searchKey);

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
