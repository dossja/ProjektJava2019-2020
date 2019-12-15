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
 * @version 2.1
 */
public class EnumBinaryTest {

    /** Constructor for tests*/
    public EnumBinaryTest() {
    }
    
    /** Set up for class with addnotation*/
    @BeforeClass
    public static void setUpClass() {
    }
    
    /** Tear down for class*/
    @AfterClass
    public static void tearDownClass() {
    }
    
    /** Set up before tests*/
    @Before
    public void setUp() {
    }
    
    /** Tear down after tests*/
    @After
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
     * Test of number method, of class EnumBinary.
     */
    @Test
    public void testNumber() {
        System.out.println("Test of method \"number\" of EnumBinary");

        String expResult = "0";
        String result = EnumBinary.zero.number();
        assertEquals(expResult, result);
        
        expResult = "1";
        result = EnumBinary.one.number();
        assertEquals(expResult, result);
    }
    
}
