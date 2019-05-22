package test8_unitTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_BeforeTestAndBeforeMethod {

    @BeforeTest
    public void beforeTest()
    {
        System.out.println("***beforeTest executes once before all tests***");
    }

    @AfterTest
    public void afterTest()
    {
        System.out.println("\n***afterTest executes once after all tests***");
    }
    
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("\nbeforeMethod");
    }
    
    @AfterMethod
    public void afterMethod()
    {
        System.out.println("afterMethod");
    }


    @Test
    public void firstTest()
    {
        System.out.println("firstTest");
    }

    @Test
    public void secondTest()
    {
        System.out.println("secondTest");
    }

    @Test
    public void thirdTest()
    {
        System.out.println("thirdTest");
    }
}

//OUTPUT
//***beforeTest executes once before all tests***
//
//beforeMethod
//firstTest
//afterMethod
//
//beforeMethod
//secondTest
//afterMethod
//
//beforeMethod
//thirdTest
//afterMethod
//
//***afterTest executes once after all tests***
//PASSED: firstTest
//PASSED: secondTest
//PASSED: thirdTest
//
//===============================================
//    Default test
//    Tests run: 3, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 3, Failures: 0, Skips: 0
//===============================================