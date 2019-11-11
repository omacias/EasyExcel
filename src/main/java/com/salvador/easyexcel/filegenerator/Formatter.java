/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.filegenerator;

import com.salvador.easyexcel.annotations.Exclude;
import com.salvador.easyexcel.annotations.Header;
import com.salvador.easyexcel.annotations.Row;
import com.salvador.easyexcel.enums.Colors;
import com.salvador.easyexcel.enums.TextStyle;
import java.lang.reflect.Field;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Formatter class, applies format and style to rows and columns
 * @author Oscar Salvador Macias Tiscareño
 */
class Formatter {
    
    private Formatter(){}
    
    static void setBackgroundHeader(Field f, CellStyle style) {
        Colors backgroundColor = Constants.BACKGROUND_DEFAULT;
        if(f.isAnnotationPresent(Header.class)) {
            Header header = f.getAnnotation(Header.class);
            backgroundColor = header.backgroundColor();
        }
        setBackground(style, backgroundColor);
    }
    
    static void setBackgroundRow(Field f, CellStyle style) {
        Colors backgroundColor = Constants.BACKGROUND_DEFAULT;
        if(f.isAnnotationPresent(com.salvador.easyexcel.annotations.Row.class)) {
            com.salvador.easyexcel.annotations.Row row = f.getAnnotation(com.salvador.easyexcel.annotations.Row.class);
            backgroundColor = row.backgroundColor();
        }
        setBackground(style, backgroundColor);
    }
    
    static void setBackground(CellStyle style, Colors color) {
        style.setFillForegroundColor(color.getColorCode());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    }
    
    static void setFontRow(Field f, Font font) {
        Colors color = Constants.FONT_DEFAULT_COLOR;
        TextStyle style = TextStyle.NORMAL;
        if(f.isAnnotationPresent(com.salvador.easyexcel.annotations.Row.class)) {
            com.salvador.easyexcel.annotations.Row row = f.getAnnotation(com.salvador.easyexcel.annotations.Row.class);
            color = row.color();
            style = row.style();
        }        
        setFont(font, color, style);
    }
    
    static void setFontHeader(Field f, Font font) {
        Colors color = Constants.FONT_DEFAULT_COLOR;
        TextStyle style = Constants.FONT_DEFAULT_STYLE;
        if(f.isAnnotationPresent(Header.class)) {
            Header header = f.getAnnotation(Header.class);
            color = header.color();
            style = header.style();
        }        
        setFont(font, color, style);
    }
    
    static void setFont(Font font, Colors color, TextStyle style) {
        font.setColor(color.getColorCode());
        switch (style) {
            case NORMAL:
                font.setItalic(false);
                font.setUnderline(HSSFFont.U_NONE);
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                break;
            case BOLD:
                font.setItalic(false);
                font.setUnderline(HSSFFont.U_NONE);
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                break;
            case ITALIC:
                font.setItalic(true);
                font.setUnderline(HSSFFont.U_NONE);
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                break;
            case UNDERLINE:
                font.setItalic(false);
                font.setUnderline(HSSFFont.U_SINGLE);
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                break;
            default:
                font.setItalic(false);
                font.setUnderline(HSSFFont.U_NONE);
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                break;
        }
    }
    
    static <T> String getValueAsString(Field f, T t) throws IllegalAccessException {
        String value = String.valueOf(FieldUtils.readField(f, t, true));
        if(replaceNullsToEmptyString(f) && Constants.NULL_STRING.equalsIgnoreCase(value)) {
            value = Constants.EMPTY_STRING;
        }
        return value;
    }
    
    static boolean replaceNullsToEmptyString(Field f) {
        boolean nullAsEmpty = false;
        if(f.isAnnotationPresent(com.salvador.easyexcel.annotations.Row.class)){
            com.salvador.easyexcel.annotations.Row row = f.getAnnotation(com.salvador.easyexcel.annotations.Row.class);
            nullAsEmpty = row.nullAsEmpty();
        }
        return nullAsEmpty;
    }
    
    static <T> void adjustSheet(Sheet sheet, T t) {
        int columnIndex = 0;
        for(Field f : t.getClass().getDeclaredFields()) {
            if(!excludeColumn(f, t)){
                if(autoSizeColumn(f, t)) {
                    sheet.autoSizeColumn(columnIndex);
                }
                columnIndex++;
            }
        }
    }
    
    static <T> boolean autoSizeColumn(Field f, T t) {
        if(f.isAnnotationPresent(Header.class)) {
            Header header = f.getAnnotation(Header.class);
            return header.autoSizeColumn();
        }
        return false;
    }
    
    static <T> boolean excludeColumn(Field f, T t) {
        return (f.isAnnotationPresent(Exclude.class) || !f.isAnnotationPresent(Header.class) || !f.isAnnotationPresent(Row.class));
    }
}
