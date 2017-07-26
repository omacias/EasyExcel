/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.test;

import com.salvador.easyexcel.filegenerator.Generator;
import com.salvador.easyexcel.filegenerator.Generator.Build;
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
        System.setProperty("java.io.tmpdir", "E:\\temp");
        
        System.out.println(System.getProperty("java.io.tmpdir"));
        
        Person person = new Person("Oscar", 26, null);    
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 524_288; i++) {
            list.add(person);
        }
        Build builder = new Generator.Build().setPath("E:\\temp").setBufferRows(200).setSheetName("Prueba").setKeepWritingAtNextSheet(true);
        try(Generator<Person> gen = builder.<Person>build()) {            
            System.out.println("Starting excel generation...");
            System.out.println("Writing first group");
            gen.write(list);
            System.out.println("Writing second group");
            gen.write(list);
            System.out.println("Writing third group");
            gen.write(list);
            System.out.println("Writing fourth group");
            gen.write(list);
            File file = gen.flush();
            System.out.println(file.getName() + " " + (file.length() / 1024) + " Kb");
            System.out.println(gen.getBufferRows() + " " + gen.getSheetName() + " " + gen.isKeepWritingAtNextSheet() + " " + gen.getPath());
            
        }catch(Exception e) {
            e.printStackTrace();
        }  
        
        System.out.println("File created!");
    }
}
