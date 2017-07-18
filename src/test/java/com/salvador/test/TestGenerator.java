/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.test;

import com.salvador.easyexcel.filegenerator.Generator;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 * TestGenerator is define to test generator class
 * @author Oscar Salvador Macias Tiscareño
 */
public class TestGenerator {
    
    @Test
    public void testFileGen() {        
        Person person = new Person("Oscar", 26, null);    
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 40_000; i++) {
            list.add(person);
        }
        Generator<Person> gen = new Generator.Build<Person>().setPath("E:\\temp").setBufferRows(200).setSheetName("Prueba").setKeepWritingAtNextSheet(true).build();
        try {
            System.out.println("Starting excel generation...");
            File file = gen.createExcelFile(list);
            System.out.println(file.getName() + " " + (file.length() / 1024) + " Kb");
            System.out.println(gen.getBufferRows() + " " + gen.getSheetName() + " " + gen.isKeepWritingAtNextSheet() + " " + gen.getPath());
        }catch(Exception e) {
            e.printStackTrace();
        }        
        System.out.println("File created!");
    }
}
