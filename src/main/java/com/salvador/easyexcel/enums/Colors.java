/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.enums;

import org.apache.poi.hssf.util.HSSFColor;


/**
 * Colors is used to define colors
 * @author Oscar Salvador Macias Tiscareño
 */
public enum Colors {
    BLACK(HSSFColor.BLACK.index), WHITE(HSSFColor.WHITE.index), RED(HSSFColor.RED.index), BRIGHT_GREEN(HSSFColor.BRIGHT_GREEN.index), BLUE(HSSFColor.BLUE.index), 
    YELLOW(HSSFColor.YELLOW.index), PINK(HSSFColor.PINK.index), TURQUOISE(HSSFColor.TURQUOISE.index), DARK_RED(HSSFColor.DARK_RED.index), GREEN(HSSFColor.GREEN.index), 
    DARK_BLUE(HSSFColor.DARK_BLUE.index), DARK_YELLOW(HSSFColor.DARK_YELLOW.index), VIOLET(HSSFColor.VIOLET.index), TEAL(HSSFColor.TEAL.index), 
    GREY_25_PERCENT(HSSFColor.GREY_25_PERCENT.index), GREY_50_PERCENT(HSSFColor.GREY_50_PERCENT.index), CORNFLOWER_BLUE(HSSFColor.CORNFLOWER_BLUE.index), 
    MAROON(HSSFColor.MAROON.index), LEMON_CHIFFON(HSSFColor.LEMON_CHIFFON.index), ORCHID(HSSFColor.ORCHID.index), CORAL(HSSFColor.CORAL.index), 
    ROYAL_BLUE(HSSFColor.ROYAL_BLUE.index), LIGHT_CORNFLOWER_BLUE(HSSFColor.LIGHT_CORNFLOWER_BLUE.index), SKY_BLUE(HSSFColor.SKY_BLUE.index), 
    LIGHT_TURQUOISE(HSSFColor.LIGHT_TURQUOISE.index), LIGHT_GREEN(HSSFColor.LIGHT_GREEN.index), LIGHT_YELLOW(HSSFColor.LIGHT_YELLOW.index), 
    PALE_BLUE(HSSFColor.PALE_BLUE.index), ROSE(HSSFColor.ROSE.index), LAVENDER(HSSFColor.LAVENDER.index), TAN(HSSFColor.TAN.index), LIGHT_BLUE(HSSFColor.LIGHT_BLUE.index), 
    AQUA(HSSFColor.AQUA.index), LIME(HSSFColor.LIME.index), GOLD(HSSFColor.GOLD.index), LIGHT_ORANGE(HSSFColor.LIGHT_ORANGE.index), ORANGE(HSSFColor.ORANGE.index), 
    BLUE_GREY(HSSFColor.BLUE_GREY.index), GREY_40_PERCENT(HSSFColor.GREY_40_PERCENT.index), DARK_TEAL(HSSFColor.DARK_TEAL.index), SEA_GREEN(HSSFColor.SEA_GREEN.index), 
    DARK_GREEN(HSSFColor.DARK_GREEN.index), OLIVE_GREEN(HSSFColor.OLIVE_GREEN.index), BROWN(HSSFColor.BROWN.index), PLUM(HSSFColor.PLUM.index), 
    INDIGO(HSSFColor.INDIGO.index), GREY_80_PERCENT(HSSFColor.GREY_80_PERCENT.index), AUTOMATIC(HSSFColor.AUTOMATIC.index);
    
    private final short indexColor;
    
    private Colors(short indexColor) {
        this.indexColor = indexColor;        
    }
    
    public short getColorCode() {
        return this.indexColor;
    }
}
