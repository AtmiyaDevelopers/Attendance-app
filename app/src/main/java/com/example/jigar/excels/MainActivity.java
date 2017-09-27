package com.example.jigar.excels;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jigar.excels.Data_Base_Package.Excel_DataBase;
import com.example.jigar.excels.Data_Base_Package.Excel_data_control_class;
import com.example.jigar.excels.Data_Classes.Columninfo;
import com.example.jigar.excels.Data_Classes.Read_Excel;
import com.example.jigar.excels.Data_Classes.StudentData;
import com.google.gson.Gson;

import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public EditText crname,crnumber;
    public Spinner departmentspinner,semesterspinner;
    public ArrayAdapter departmentadapter,semesteradapter;
    public String department[],semester[];
    public Excel_data_control_class excelcontrol;
    public String tablename="";
    public Read_Excel read_excel;
    public Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crname=(EditText)findViewById(R.id.crname_edt);
        crnumber=(EditText)findViewById(R.id.crnumber_edt);
        departmentspinner=(Spinner)findViewById(R.id.department_spinner);
        semesterspinner=(Spinner)findViewById(R.id.semester_spinner);
        department=getResources().getStringArray(R.array.departmentname);
        semester=getResources().getStringArray(R.array.semester);
        departmentadapter=ArrayAdapter.createFromResource(this,R.array.departmentname,android.R.layout.simple_spinner_item);
        semesteradapter=ArrayAdapter.createFromResource(this,R.array.semester,android.R.layout.simple_spinner_item);
        departmentspinner.setAdapter(departmentadapter);
        semesterspinner.setAdapter(semesteradapter);
        excelcontrol=new Excel_data_control_class(this);
        read_excel=new Read_Excel(this);
        gson=new Gson();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<StudentData> studentData=new ArrayList<>();
                studentData.add(new StudentData("jigar","chhatrala","161240107005","9408103800"));
                studentData.add(new StudentData("jigar","chhatrala","161240107005","9408103800"));
                studentData.add(new StudentData("jigar","chhatrala","161240107005","9408103800"));
                studentData.add(new StudentData("jigar","chhatrala","161240107005","9408103800"));
                studentData.add(new StudentData("jigar","chhatrala","161240107005","9408103800"));
                String d=gson.toJson(studentData);
                Toast.makeText(MainActivity.this, d, Toast.LENGTH_SHORT).show();
                /*tablename=department[(int) departmentspinner.getSelectedItemId()]+"_"+semester[(int) semesterspinner.getSelectedItemId()];
                if(!excelcontrol.tableExist(tablename)){
                    List<Columninfo> column=new ArrayList<>();
                    column.add(new Columninfo("firstname","text"));
                    column.add(new Columninfo("phonenumber","text"));
                    column.add(new Columninfo("address","text"));
                    excelcontrol.createTable(tablename,column);
                }
                selectExcel();*/
            }
        });
    }
    private void selectExcel() {
        Intent intent=new Intent();
        intent.setType("xlsx/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    public void readsheet(String s) {
        read_excel.readExcel(s,new String[]{"firstname","phonenumber","address"});
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        Uri uri=data.getData();
        String s=uri.toString();
        s=s.replace("file:///","/");
        readsheet(s);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
