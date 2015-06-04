package appewtc.masterung.ssrurestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 6/3/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    public MyOpenHelper() {
        super(null, null, null, 0);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
