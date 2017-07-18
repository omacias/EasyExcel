/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.easyexcel.filegenerator;

/**
 * Builder interface
 * @author Oscar Salvador Macias Tiscareño
 * @param <T>
 */
interface Builder<T> {
    
    /**
     * build
     * @return T
     */
    T build();
}
