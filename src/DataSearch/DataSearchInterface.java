package DataSearch;

import Utilities.SearchInterface;
import Create.*;
import KeySearch.KeySearch;

import java.util.Scanner;

/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 7:30 PM
 */
public class DataSearchInterface extends SearchInterface{

    public DataSearchInterface(Create cobj, String searchString){
        super(cobj, searchString);
        if (cobj.type.equals("indexfile")) {
        	search = new KeySearch(cobj.my_table_sec);
        }
        else {
        search = new DataSearch(cobj.my_table);
        }
        }

    @Override
    protected void custSearch() {
        search.searchFor(this.ranSearchKey);
    }
    @Override
    protected void printMSG() {
        System.out.println("If you would like to specify a data value to search for," +
                " please enter it. Otherwise hit 'Enter' to search " +
                " for a random value");
    }
}

