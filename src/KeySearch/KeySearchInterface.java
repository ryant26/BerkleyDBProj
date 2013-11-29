package KeySearch;

import java.util.Scanner;
import Create.*;
import Utilities.SearchInterface;
/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 5:58 PM
 */
public class KeySearchInterface extends SearchInterface{

    public KeySearchInterface(Create cobj){
        super(cobj);
        search = new KeySearch(cobj.my_table);
    }

    @Override
    protected void printMSG() {
        System.out.println("If you would like to specify a key to search for," +
                " please enter it. Otherwise hit 'Enter' to search " +
                " for a random key");
    }

    @Override
    protected void custSearch() {
    	if (create.type.equals("indexfile")) {
    		search.searchFor(create.randData);
    	}
    	else {
        search.searchFor(create.randKey);
    	}
    }
}
