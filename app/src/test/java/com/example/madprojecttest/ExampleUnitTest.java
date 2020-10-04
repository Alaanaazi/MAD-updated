package com.example.madprojecttest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Civilian civilian;
    private Hotline hotline;
    private Alert alert;
    private PoliceStation policeStation;
    private Criminal criminal;

    @Before
    public void setUp() {

        civilian = new Civilian();
        hotline = new Hotline();
        alert = new Alert();
        policeStation = new PoliceStation();
        criminal = new Criminal();
    }

    //IT19056630
    @Test
    public void NICisCorrect() {
        boolean value = civilian.isNICValid("991910239V");
        assertEquals(true,value);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //IT19125794
    @Test
    public void NoisCorrect() {
        boolean value = hotline.isNovalid(112441122);
        assertEquals(true,value);
    }

    //IT19129822
    @Test
    public void TitleisCorrect(){
        boolean value = alert.TitleLengthvalid("Searching the criminal");
        assertEquals(true,value);
    }

    //IT19056630
    @Test
    public void IdisCorrect(){
        boolean value = policeStation.isIdValid("LKP01");
        assertEquals(true,value);
    }

    //IT19058788
    @Test
    public void AreaisCorrect(){
        boolean value = criminal.ArealengthisValid("Wellawatte East");
        assertEquals(true,value);
    }
}