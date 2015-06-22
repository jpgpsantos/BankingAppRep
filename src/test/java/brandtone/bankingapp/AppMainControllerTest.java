/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brandtone.bankingapp;

import junit.framework.TestCase;

/**
 *
 * @author XKEP007
 */
public class AppMainControllerTest extends TestCase {
    
    public AppMainControllerTest(String testName) {
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
     * Test of run method, of class AppMainController.
     */
    public void testRun() {
        System.out.println("run");
        AppMainController instance = new AppMainController();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printInstructions method, of class AppMainController.
     */
    public void testPrintInstructions() {
        System.out.println("printInstructions");
        AppMainController instance = new AppMainController();
        instance.printInstructions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
