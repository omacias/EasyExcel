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
 * Row annotation is used to adjust preferences for the excel rows
 * @author Oscar Salvador Macias Tiscareño
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Row {
    
    /**
     * backgroundColor of the row background cell
     * default Colors.WHITE
     * @return backgroundColor
     */
    public Colors backgroundColor() default Colors.WHITE;
    
    /**
     * color of the row text cell
     * default Colors.BLACK
     * @return color
     */
    public Colors color() default Colors.BLACK;
    
    /**
     * style of the row text cell
     * default TextStyle.NORMAL
     * @return style
     */
    public TextStyle style() default TextStyle.NORMAL;
    
    /**
     * define is null values should be treated as empty cells
     * default false
     * @return nullAsEmpty
     */
    public boolean nullAsEmpty() default false;
}
