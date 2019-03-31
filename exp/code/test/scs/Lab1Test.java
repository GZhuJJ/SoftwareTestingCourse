package scs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//@RunWith(Parameterized.class)
public class Lab1Test {
    private Lab1 lab1;
    private int input;
    private boolean expected;

    
    public Lab1Test(int input,boolean expected) {
    	this.input=input;
    	this.expected=expected;

    }
    @Before
    public void setUp() {
    	lab1=new Lab1();
    }
    
    @Parameters
    public static Collection<Object[]>getData(){
    	return Arrays.asList(new Object[][]{
    		{3,true},
    		{11,true},
    		{28,true},
    		{55,true},
    		{77,true}
    	});
    }
	@Test
	public void test() {
		lab1=new Lab1();
		//assertEquals(expected,lab1.fo(input));
		assertEquals(true,lab1.fo(2));
		assertEquals(true,lab1.fo(5));
	}


}
