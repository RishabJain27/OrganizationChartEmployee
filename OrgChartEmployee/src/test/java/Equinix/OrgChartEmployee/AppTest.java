package Equinix.OrgChartEmployee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testIsString()
    {
        boolean result = App.isInteger("123");
        assertEquals(true, result);
        
        boolean resultFalse = App.isInteger("text");
        assertEquals(false, resultFalse);
    }
    
}
