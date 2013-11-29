package RangeSearch;

import Create.Create;
import Utilities.SearchInterface;

/**
 * User: Ryan
 * Date: 2013-11-29
 * Time: 12:15 PM
 */
public class RangeSearchInterface extends SearchInterface{

    public RangeSearchInterface(Create cobj, String searchKey){
        super(cobj, searchKey);
    }

    public void custSearch(){
        search.searchFor(this.ranSearchKey);
    }

    public void printMSG(){
        System.out.println("If you'd like to specify a range to search for please enter 2 bounds" +
                "sepparated by a space. Otherwise hit 'Enter' to use random values.s");
    }
}
