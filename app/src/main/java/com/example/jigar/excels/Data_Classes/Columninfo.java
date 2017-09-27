package com.example.jigar.excels.Data_Classes;

/**
 * Created by JIGAR on 9/24/2017.
 */

public class Columninfo {
    private String columnName;
    private String columnDataType;
    public Columninfo(String columnName, String columnDataType) {
        this.columnName = columnName;
        this.columnDataType = columnDataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }
}
