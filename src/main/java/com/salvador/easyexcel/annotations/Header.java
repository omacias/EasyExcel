/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.annotations;

import com.salvador.easyexcel.enums.Colors;
import com.salvador.easyexcel.enums.TextStyle;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Header annotation is used to adjust preferences for the excel headers
 * @author Oscar Salvador Macias Tiscareño
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Header {
    
    /**
     * backgroundColor of the header background cell
     * default Colors.WHITE
     * @return backgroundColor
     */
    public Colors backgroundColor() default Colors.WHITE;
    
    /**
     * color of the header text cell
     * default Colors.BLACK
     * @return color
     */
    public Colors color() default Colors.BLACK;
    
    /**
     * style of the header text cell
     * default TextStyle.NORMAL
     * @return style
     */
    public TextStyle style() default TextStyle.NORMAL;
    
    /**
     * title of the header cell
     * default Column
     * @return title
     */
    public String title() default "Column";
    
    /**
     * autoSizeColumn with content
     * WARNING: Set this preference as TRUE could result in a performance decrease for a big amount of rows
     * default false
     * @return autoSizeColumn
     */
    public boolean autoSizeColumn() default false;    
}
