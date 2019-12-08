/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.EnumBinary;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for enum class called EnumBinary
 *
 * @author Dominika Matyja
 * @version 2.0
 */
public class EnumBinaryTest {

    /** Constructor for tests*/
    public EnumBinaryTest() {
    }
    
    @BeforeClass
    /** Set up for class with addnotation*/
    public static void setUpClass() {
    }
    
    @AfterClass
    /** Tear down for class*/
    public static void tearDownClass() {
    }
    
    @Before
    /** Set up before tests*/
    public void setUp() {
    }
    
    @After
    /** Tear down after tests*/
    public void tearDown() {
    }

    /**
     * Test of values method, of class EnumBinary.
     */
    @Test
    public void testValues() {
        System.out.println("Test of method \"values\" of EnumBinary");
        EnumBinary[] expResult = new EnumBinary[2];
        expResult[0] = EnumBinary.zero;
        expResult[1] = EnumBinary.one;
        EnumBinary[] result = EnumBinary.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class EnumBinary.
     */
    @Test
    public void testValueOf() {
        System.out.println("Test of method \"valueOf\" of EnumBinary");
        
        EnumBinary expResult = EnumBinary.zero;
        EnumBinary result = EnumBinary.valueOf("zero");
        assertEquals(expResult, result);

        expResult = EnumBinary.one;
        result = EnumBinary.valueOf("one");
        assertEquals(expResult, result);
    }

    /**
     * Test of liczba method, of class EnumBinary.
     */
    @Test
    public void testLiczba() {
        System.out.println("Test of method \"liczba\" of EnumBinary");

        String expResult = "zero";
        String result = EnumBinary.zero.liczba();
        assertEquals(expResult, result);
        
        expResult = "one";
        result = EnumBinary.one.liczba();
        assertEquals(expResult, result);
    }
    
}
