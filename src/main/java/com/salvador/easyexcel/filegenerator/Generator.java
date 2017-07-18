/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.filegenerator;

import com.salvador.easyexcel.annotations.Header;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Generator is used to create excel files using apache poi
 * @author Oscar Salvador Macias Tiscareño
 * @param <T>
 */
public class Generator<T> {
    
    /**
     * bufferRows define the quantity of rows that keep in memory before to save into file
     */
    private int bufferRows;
    
    /**
     * keepWritingAtNextSheet defines if the process should continue writing on the next sheet. 
     * Otherwise process will stop when the file of sheet is reached
     */
    private boolean keepWritingAtNextSheet;
    
    /**
     * sheetName defines the name to set
     */
    private String sheetName;
    
    /**
     * define the path for the file.
     */
    private String path;
    
    /**
     * rowsCellStyle, reusing style for all rows
     */
    private final List<CellStyle> rowsCellStyle;
    
    private Generator() {
        this.bufferRows = Constants.DEFAULT_BUFFER_ROWS;
        this.keepWritingAtNextSheet = Constants.DEFAULT_KEEP_WRITING_SHEET;
        this.sheetName = Constants.DEFAULT_SHEET_NAME;
        this.path = Constants.DEFAULT_FILE_PATH;
        this.rowsCellStyle = new LinkedList<>();
    }
    
    /**
     * This method takes a List and saves it into an excel file
     * @param list of type List
     * @return excel File
     * @throws FileNotFoundException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    public File createExcelFile(List<T> list) throws FileNotFoundException, IllegalAccessException, IOException {
        String fileName = UUID.randomUUID().toString();
        File file = new File(this.path + "\\" + fileName + Constants.EXCEL_EXT);
        if(list != null && list.size() > 0 && list.get(0) != null) {         
            try (FileOutputStream streamOut = new FileOutputStream(file)) {
                SXSSFWorkbook streamWorkbook = new SXSSFWorkbook(this.bufferRows);
                Sheet currentSheet = streamWorkbook.createSheet(this.sheetName);
                int sheetNumber = 1;
                int indexRow = 0;
                Row headerRow = currentSheet.createRow(indexRow++);
                addHeaderRow(list.get(0), headerRow, streamWorkbook);
                for (T t : list) {
                    if(indexRow > Constants.EXCEL_MAX_SHEET_ROWS) {
                        if(this.keepWritingAtNextSheet) {
                            Formatter.adjustSheet(currentSheet, list.get(0));
                            //continue writing on the next sheet
                            currentSheet = streamWorkbook.createSheet(this.sheetName + sheetNumber++);
                            indexRow = 0;
                            headerRow = currentSheet.createRow(indexRow++);
                            addHeaderRow(list.get(0), headerRow, streamWorkbook);
                        }else{
                            break;
                        }                     
                    }                    
                    Row row = currentSheet.createRow(indexRow++);
                    fillRow(t, row, streamWorkbook);
                }
                Formatter.adjustSheet(currentSheet, list.get(0));
                streamWorkbook.write(streamOut);
            }
        }
        return file;        
    }    
    
    private void fillRow(T t, Row row, Workbook wb) throws IllegalAccessException {
        int index = 0;
        for(Field f : t.getClass().getDeclaredFields()) {
            if(!Formatter.excludeColumn(f, t)){     
                CellStyle style = getStyleFromList(index);
                if(style == null) {
                    style = wb.createCellStyle();
                    Font font = wb.createFont();
                    Formatter.setFontRow(f, font);                    
                    style.setFont(font);
                    Formatter.setBackgroundRow(f, style);
                    this.rowsCellStyle.add(index, style);
                }                
                Cell cell = row.createCell(index++);
                cell.setCellValue(Formatter.getValueAsString(f, t));
                cell.setCellStyle(style);
            }
        }
    }
    
    private CellStyle getStyleFromList(int index) {
        try {
            return this.rowsCellStyle.get(index);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    private void addHeaderRow(T t, Row row, Workbook wb) {
        int index = 0;
        for(Field f : t.getClass().getDeclaredFields()) {
            if(!Formatter.excludeColumn(f, t)){
                String title = Constants.DEFAULT_COLUMN_NAME;
                if(f.isAnnotationPresent(Header.class)) {
                    Header header = f.getAnnotation(Header.class);
                    title = header.title();
                }
                Font font = wb.createFont();
                Formatter.setFontHeader(f, font);
                CellStyle style = wb.createCellStyle();
                style.setFont(font);
                Formatter.setBackgroundHeader(f, style);
                Cell cell = row.createCell(index++);
                cell.setCellValue(title);
                cell.setCellStyle(style);
            }
        }
    }
    
    /**
     * getBufferRows()
     * @return bufferRows
     */
    public int getBufferRows() {
        return bufferRows;
    }
    
    /**
     * isKeepWritingAtNextSheet()
     * @return keepWritingAtNextSheet
     */
    public boolean isKeepWritingAtNextSheet() {
        return keepWritingAtNextSheet;
    }
    
    /**
     * getSheetName()
     * @return sheetName
     */
    public String getSheetName() {
        return sheetName;
    }
    
    /**
     * getPath()
     * @return path
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Class to build Generator instance
     * @param <K> 
     */
    public static class Build<K> implements Builder<Generator> {
        
        /**
        * bufferRows define the quantity of rows that keep in memory before to save into file
        */
        private Integer bufferRows;
        
        /**
        * keepWritingAtNextSheet defines if the process should continue writing on the next sheet. 
        * Otherwise process will stop when the file of sheet is reached
        */
        private Boolean keepWritingAtNextSheet;

        /**
        * sheetName defines the name to set
        */
        private String sheetName;
        
        /**
         * path, location to save file
         */
        private String path;

        /**
         * setBufferRows
         * @param bufferRows
         * @return Build         * 
         */
        public Build setBufferRows(int bufferRows) {
            if(bufferRows < Constants.MIN_BUFFER_ROWS || bufferRows > Constants.MAX_BUFFER_ROWS) {
                throw new IllegalArgumentException(Constants.BUFFER_ROWS_INVALID);
            }
            this.bufferRows = bufferRows;
            return this;
        }
        
        /**
         * setKeepWritingAtNextSheet
         * @param keepWritingAtNextSheet
         * @return Build
         */
        public Build setKeepWritingAtNextSheet(boolean keepWritingAtNextSheet) {
            this.keepWritingAtNextSheet = keepWritingAtNextSheet;
            return this;
        }

        /**
         * setSheetName
         * @param sheetName
         * @return Build
         */
        public Build setSheetName(String sheetName) {
            if(sheetName == null || sheetName.trim().isEmpty()) {
                throw new IllegalArgumentException(Constants.SHEET_NAME_INVALID);
            }
            this.sheetName = sheetName;
            return this;
        }

        /**
         * setPath
         * @param path
         * @return Build
         */
        public Build setPath(String path) {
            if(path == null || path.trim().isEmpty()) {
                throw new IllegalArgumentException(Constants.PATH_INVALID);
            }
            this.path = path;
            return this;
        }

        /**
         * build()
         * @return Generator instance 
         */
        @Override
        public Generator build() {
            Generator<K> generator = new Generator<>();
            if(this.bufferRows != null) {
                generator.bufferRows = this.bufferRows;
            }            
            if(this.keepWritingAtNextSheet != null) {
                generator.keepWritingAtNextSheet = this.keepWritingAtNextSheet;
            }            
            if(this.sheetName != null) {
                generator.sheetName = this.sheetName;
            }   
            if(this.path != null) {
                generator.path = this.path;
            }
            
            return generator;
        }        
    }
}
