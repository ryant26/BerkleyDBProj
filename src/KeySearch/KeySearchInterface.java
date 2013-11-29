package KeySearch;

import java.util.Scanner;
import Create.*;
import Utilities.SearchInterface;
import com.sleepycat.db.Database;

/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 5:58 PM
 */
public class KeySearchInterface extends SearchInterface{

    public KeySearchInterface(Create cobj, String searchString, Database db){
        super(cobj, searchString);
            search = new KeySearch(db);
    }

    @Override
    protected void printMSG() {
        System.out.println("If you would like to specify a key to search for," +
                " please enter it. Otherwise hit 'Enter' to search " +
                " for a random key");
    }

    @Override
    protected void custSearch() {
        search.searchFor(this.ranSearchKey);
    }
}
