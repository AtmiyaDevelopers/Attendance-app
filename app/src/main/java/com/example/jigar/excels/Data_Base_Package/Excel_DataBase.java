package com.example.jigar.excels.Data_Base_Package;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by JIGAR on 9/24/2017.
 */

public class Excel_DataBase extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="exceldata";
    public final static String TABLE_NAME="DEPARTMENT";
    final static String ID="ID";
    public final static String DEPT_NAME="DEPT_NAME";
    public final static String CRNAME="CR_NAME";
    public final static String CRNUMBER="CR_NUMBER";
    final static String QUERY="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER AUTO_INCREMENT,"+DEPT_NAME+" VARCHAR(30) UNIQUE,"+CRNAME+" TEXT,"+CRNUMBER+" TEXT);";
    Context c;
    public Excel_DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        c=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY);
        Toast.makeText(c, "create table "+TABLE_NAME, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
