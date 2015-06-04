package appewtc.masterung.ssrurestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 6/4/15 AD.
 */
public class UserTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public UserTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

}   // Main Class
