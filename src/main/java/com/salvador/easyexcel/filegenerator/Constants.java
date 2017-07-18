/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.filegenerator;

import com.salvador.easyexcel.enums.Colors;
import com.salvador.easyexcel.enums.TextStyle;

/**
 * Constants class
 * @author Oscar Salvador Macias Tiscareño
 */
class Constants {
    
    static final String DEFAULT_COLUMN_NAME = "Column";
    
    static final int DEFAULT_BUFFER_ROWS = 100;
    
    static final boolean DEFAULT_KEEP_WRITING_SHEET = false;
    
    static final String DEFAULT_SHEET_NAME = "Sheet";
    
    static final String EMPTY_STRING = "";
    
    static final String NULL_STRING = "null";
    
    static final String DEFAULT_FILE_PATH = System.getProperty("java.io.tmpdir");
    
    static final String EXCEL_EXT = ".xlsx";
    
    static final Colors BACKGROUND_DEFAULT = Colors.WHITE;
    
    static final Colors FONT_DEFAULT_COLOR = Colors.BLACK;
    
    static final TextStyle FONT_DEFAULT_STYLE = TextStyle.NORMAL;
    
    static final int EXCEL_MAX_SHEET_ROWS = 1048575;
    
    static final int MIN_BUFFER_ROWS = 100;
    
    static final int MAX_BUFFER_ROWS = 10_000;
    
    static final String BUFFER_ROWS_INVALID = "Invalid value for bufferRows, it should be between " + MIN_BUFFER_ROWS + " and " + MAX_BUFFER_ROWS;
    
    static final String SHEET_NAME_INVALID = "Sheet name cannot be null or empty";
    
    static final String PATH_INVALID = "Path cannot be null or empty";
}
