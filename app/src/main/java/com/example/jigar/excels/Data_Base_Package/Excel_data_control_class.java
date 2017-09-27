package com.example.jigar.excels.Data_Base_Package;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.jigar.excels.Data_Classes.Columninfo;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.TopLevelAttributeImpl;

import java.util.List;
/**
 * Created by JIGAR on 9/24/2017.
 */
public class Excel_data_control_class {
    Excel_DataBase excel_dataBase;
    Context context;
    SQLiteDatabase sqLiteWriteableDatabase;
    SQLiteDatabase sqLiteReadableDatabase;
    public Excel_data_control_class(Context context) {
        this.context=context;
        excel_dataBase=new Excel_DataBase(context);
        sqLiteWriteableDatabase=excel_dataBase.getWritableDatabase();
        sqLiteReadableDatabase=excel_dataBase.getReadableDatabase();
    }
    public boolean tableExist(String tableName){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Excel_DataBase.DEPT_NAME,tableName);
        long a=sqLiteWriteableDatabase.insert(Excel_DataBase.TABLE_NAME,null,contentValues);
        if(a==-1){
            Toast.makeText(context, "table exist", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context, "table not exist", Toast.LENGTH_SHORT).show();
        return false;
    }
    public void createTable(String tablename, List<Columninfo> columninfos){
        if(columninfos.isEmpty()){
            Toast.makeText(context, "column info is empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        String query="CREATE TABLE "+tablename+"("+columninfos.get(0).getColumnName()+" "+columninfos.get(0).getColumnDataType();
        Columninfo columninfo;
        for(int i=1;i<columninfos.size();i++){
            columninfo=columninfos.get(i);
            query=query+","+columninfo.getColumnName()+" "+columninfo.getColumnDataType();
        }
        query=query+");";
        sqLiteWriteableDatabase.execSQL(query);
        Toast.makeText(context, "create table "+tablename, Toast.LENGTH_SHORT).show();
        return ;
    }
    public int deleteDataFromTable(String tableName,Columninfo columninfo){
        return sqLiteWriteableDatabase.delete(tableName,columninfo.getColumnName()+" LIKE ?",new String[]{columninfo.getColumnDataType()});
    }
    public boolean inserValues(String tableName,List<Columninfo> columns){
        ContentValues contentvalues=new ContentValues();
        for(Columninfo col:columns){
            contentvalues.put(col.getColumnName(),col.getColumnDataType());
        }
        long a=sqLiteWriteableDatabase.insert(tableName,null,contentvalues);
        if(a==-1){
            Toast.makeText(context, "sorry faild", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void dropTable(String tableName,Columninfo columninfo){
        if(columninfo!=null){
            if(deleteDataFromTable(tableName,columninfo)>0){
                Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
            }
        }
        deleteDataFromTable(Excel_DataBase.TABLE_NAME,columninfo);
        if(tableExist(tableName)){
            sqLiteWriteableDatabase.execSQL("DROP TABLE"+tableName);
            return;
        }
        Toast.makeText(context, tableName+" not exist", Toast.LENGTH_SHORT).show();
    }
    public void clearTable(String tableName){
        if(tableExist(tableName)){
            sqLiteWriteableDatabase.execSQL("TRUNCATE "+tableName);
            return;
        }
        Toast.makeText(context, tableName+" not exist", Toast.LENGTH_SHORT).show();
    }
}
