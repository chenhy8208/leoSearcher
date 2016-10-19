package com.hongru;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.http.client.utils.DateUtils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Unit test for simple AppInit.
 */
public class AppInitTest
    extends TestCase
{
    
    public void testI() {
        int i = 0;
        i += ++i;
        System.out.println("i = " + i);
    }

    public void testOutDate() {
        DateFormat gmtSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        gmtSdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String now = gmtSdf.format(new Date());
        System.out.println("now = " + now);
    }

    public void testDateFormat() {
        String dateString = "Fri, 14 Oct 2016 13:05:40 GMT";
        DateFormat gmtSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        gmtSdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date now = null;
            if(!dateString.endsWith("GMT"))
            {
                String tail = dateString.substring(dateString.indexOf("GMT"));
                TimeZone oldTimeZone = gmtSdf.getTimeZone();
                gmtSdf.setTimeZone(TimeZone.getTimeZone("GMT" + tail));
            }
            //now = gmtSdf.parse(dateString);
            now = DateUtils.parseDate(dateString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
