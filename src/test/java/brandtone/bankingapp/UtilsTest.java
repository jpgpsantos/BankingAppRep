/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brandtone.bankingapp;

import java.math.BigDecimal;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author XKEP007
 */
public class UtilsTest extends TestCase {
    
    public UtilsTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of splitCommandIntoArray method, of class Utils.
     */
    public void testSplitCommandIntoArray() {
        System.out.println("splitCommandIntoArray");
        String inputString = "";
        List<String> expResult = null;
        List<String> result = Utils.splitCommandIntoArray(inputString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertStringToBigDecimal method, of class Utils.
     */
    public void testConvertStringToBigDecimal() throws Exception {
        System.out.println("convertStringToBigDecimal");
        String inputMoneyString = "";
        BigDecimal expResult = null;
        BigDecimal result = Utils.convertStringToBigDecimal(inputMoneyString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
