package appewtc.masterung.ssrurestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 6/3/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    private static final String DATABASE_NAME = "ssru.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (_id integer primary key, User text, Password text, Name text);";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
