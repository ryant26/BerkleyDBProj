package Utilities;

import Create.Create;
import KeySearch.KeySearch;

import java.util.Scanner;

/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 7:30 PM
 */
public abstract class SearchInterface {
    protected Create create;
    protected Search search;
    protected String ranSearchKey;

    public void getKey(){
        Scanner user_input = new Scanner( System.in );
        printMSG();
        String searchKey = user_input.nextLine();
        if (searchKey.length() > 0){
            search.searchFor(searchKey);
        }else custSearch();
        System.out.println(search);

    }

    public SearchInterface(Create cobj, String ranSearchKey){
        create = cobj;
        this.ranSearchKey = ranSearchKey;
    }

    protected abstract void printMSG();

    protected abstract void custSearch();
}
