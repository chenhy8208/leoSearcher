package com.hongru;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.text.NumberFormat;

/**
 * Unit test for simple AppInit.
 */
public class AppInitTest
    extends TestCase
{

    public void testNumberFormat() {
        Float fpercent = 0.22222222f;
        //转百分数
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2); //最大小数位数

        System.out.println("fpercent = " + percentFormat.format(fpercent));
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppInitTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppInitTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
