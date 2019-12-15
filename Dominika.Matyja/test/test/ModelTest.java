/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the Model class methods.
 * 
 * @author Dominika Matyja
 * @version 2.1
 */
public class ModelTest {
    /**
     * Model object to test
     */
    Model model;
    
    /**
     * Method to create new Model before starting every test
     */
    @Before
    public void createModelObject()
    {
        model = new Model();
    }
    
    /**
     * Test for changing the Method from b2d to d2b
     */
    @Test
    public void testChangingMethodsB2D()
    {
        try
        {
            boolean otp = true;
            model.changeSide();
            assertNotEquals("Method is not changed!", model.getSide(), otp);
        }
        catch(Exception ex)
        {
            fail("ChangingMethodB2D fail!");
        }
    }
    
    /**
     * Test for changing the Method from d2b to b2d
     */
    @Test
    public void testChangingMethodsD2B()
    {
        try
        {
            model.sideB2D = false;
            boolean otp = model.getSide();
            model.changeSide();
            assertNotEquals("Method is not changed!", model.getSide(), otp);
        }
        catch(Exception ex)
        {
            fail("ChangingMethodD2B fail!");
        }
    }
    
    /**
     * Test for different decimal values
     */
    
    @Test
    public void testValuesDecimal()
    {
        try
        {
            model.changeSide();
            boolean otp = model.getSide();
            assertEquals("Method is different!", otp, model.sideB2D);
        }
        catch(Exception ex)
        {
            fail("Method haven't changed into d2b!");
        }
        
        try
        {
            model.number = "";
            model.convert(false, model.number);
            fail("Converting empty string fails! Exception!");
        }
        catch(Exception ex)
        { }
        
        try
        {
            model.number = null;
            model.convert(false, model.number);
            fail("Converting null value fails! Exception!");
        }
        catch (Exception ex)
        { }
        
        try
        {
            model.number = "2";
            model.convert(false, model.number);
            assertEquals("This conversion should return 10",
                    model.getResult(), "10");
        }
        catch (Exception ex)
        {
            fail("Converting dec 2 to bin 10 fails! " + ex);
        }      
        
        
        try
        {
            model.number = "999";
            model.convert(false, model.number);
            assertEquals("This conversion should return 11 1110 0111",
                    model.getResult(), "1111100111");
        }
        catch (Exception ex)
        {
            fail("Converting dec 999 to bin 11 1110 0111 fails! " + ex);
        }
    }
    
    /**
     * Test for different binary values
     */
    @Test
    public void testValuesBinary()
    {
        try
        {
            model.sideB2D = true;
            boolean otp = model.getSide();
            assertEquals("Method is different!", otp, model.sideB2D);
        }
        catch(Exception ex)
        {
            fail("Method haven't changed into b2d! " + ex);
        }
                
        try
        {
            model.number = null;
            model.convert(true, model.number);
            fail("Converting null value fails! NotBinary exception");
        }
        catch (Exception ex)
        { }
        
        try
        {
            model.number = "10";
            model.convert(true, model.number);
            assertEquals("This conversion should return 2",
                    model.getResult(), "2");
        }
        catch (Exception ex)
        {
            fail("Converting bin 10 to dec 2 fails! " + ex);
        }     
                
        try
        {
            model.number = "1111100111";
            model.convert(true, model.number);
            assertEquals("This conversion should return 999",
                    model.getResult(), "999");
        }
        catch (Exception ex)
        {
            fail("Converting bin 11 1110 0111 to dec 999 fails! " + ex);
        }
    }
}
