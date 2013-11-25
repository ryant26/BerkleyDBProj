package KeySearch;

import java.util.Scanner;
import Create.*;
/**
 * User: Ryan
 * Date: 2013-11-24
 * Time: 5:58 PM
 */
public class KeySearchInterface {
    private Create create;
    private KeySearch ks;

    public KeySearchInterface(Create cobj){
        ks = new KeySearch(cobj.my_table);
        create = cobj;
    }

    public void getKey(){
        Scanner user_input = new Scanner( System.in );
        System.out.println("If you would like to specify a key to search for," +
                            " please enter it. Otherwise hit 'Enter' to search " +
                            " for a random key");

        String searchKey = user_input.nextLine();
        if (searchKey.length() > 0){
            ks.searchFor(searchKey);
        }else ks.searchFor(create.randKey);

        System.out.println(ks);

    }
}
