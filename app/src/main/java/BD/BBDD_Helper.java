package BD;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDD_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "CALORIEBITE.db";

    public BBDD_Helper (Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(EstructuraBD.SQL_CREATE_ENTRIES);
        db.execSQL(EstructuraBD.SQL_CREATE_ENTRIES_DF);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion ){
        //db.execSQL(EstructuraBD.SQL_DELETE_ENTRIES);
        //db.execSQL(EstructuraBD.SQL_DELETE_ENTRIES_DF);
        onCreate(db);
    }

}
