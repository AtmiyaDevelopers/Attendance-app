package com.example.jigar.excels.Data_Classes;
import android.app.ProgressDialog;
import android.widget.Toast;
import com.example.jigar.excels.MainActivity;
import org.apache.poi.POIXMLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by JIGAR on 9/24/2017.
 */
public class Read_Excel {
    MainActivity mainActivity;
    public Read_Excel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public long readExcel(String path, String columnname[]){
        Row row;
        Cell cell;
        List<Columninfo> col=new ArrayList<>();
        List<StudentData> studentData=new ArrayList<>();
        ProgressDialog progressdialog=new ProgressDialog(mainActivity);
        progressdialog.setMessage("Please Wait...");
        progressdialog.setTitle("Reading Excel");
        try {
            InputStream inputStream=new FileInputStream(path);
            XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
            if(xssfWorkbook==null){
                Toast.makeText(mainActivity, "Please select valid excel file ", Toast.LENGTH_SHORT).show();
                return 0;
            }
            XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(0);
            progressdialog.show();
            for(int i=0;i<xssfSheet.getPhysicalNumberOfRows();i++){
                String fname="";
                String lname="";
                String enroll="";
                String phone="";
                row=xssfSheet.getRow(i);
                cell=row.getCell(0);
                if(cell!=null){
                    if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                        col.add(new Columninfo(columnname[0], String.valueOf(cell.getNumericCellValue())));
                        fname=String.valueOf(cell.getNumericCellValue());
                    }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                        col.add(new Columninfo(columnname[0],cell.getStringCellValue()));
                        fname=cell.getStringCellValue();
                    }
                }
                cell=row.getCell(1);
                if(cell!=null){
                    if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                        col.add(new Columninfo(columnname[1], String.valueOf(cell.getNumericCellValue())));
                        lname=String.valueOf(cell.getNumericCellValue());
                    }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                        col.add(new Columninfo(columnname[1],cell.getStringCellValue()));
                        lname=cell.getStringCellValue();
                    }
                }
                cell=row.getCell(2);
                if(cell!=null){
                    if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                        enroll=String.valueOf(cell.getNumericCellValue());
                        col.add(new Columninfo(columnname[2], String.valueOf(cell.getNumericCellValue())));
                    }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                        enroll=cell.getStringCellValue();
                        col.add(new Columninfo(columnname[2],cell.getStringCellValue()));
                    }
                }
                cell=row.getCell(3);
                if(cell!=null){
                    if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                        phone=String.valueOf(cell.getNumericCellValue());
                        col.add(new Columninfo(columnname[3], String.valueOf(cell.getNumericCellValue())));
                    }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                        phone=cell.getStringCellValue();
                        col.add(new Columninfo(columnname[3],cell.getStringCellValue()));
                    }
                }
                if(!mainActivity.excelcontrol.inserValues(mainActivity.tablename,col)){
                    Toast.makeText(mainActivity, String.valueOf(i), Toast.LENGTH_SHORT).show();
                    break;
                }
                col.clear();
            }
            progressdialog.dismiss();
            Toast.makeText(mainActivity, "ok done", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(mainActivity, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (POIXMLException e){
            Toast.makeText(mainActivity,"Plz Select Valid Excel", Toast.LENGTH_SHORT).show();
        }
        return 0;
    }
}
