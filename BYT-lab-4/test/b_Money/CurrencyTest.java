package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	 // Test, który sprawdza, czy metoda getName() zwraca poprawn¹ nazwê waluty.
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	  // Test, który sprawdza, czy metoda getRate() zwraca poprawny kurs waluty.
	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0);
		assertEquals(0.20, DKK.getRate(), 0);
		assertEquals(1.5, EUR.getRate(), 0);
	}
	
	// Test, który sprawdza, czy metoda setRate() poprawnie aktualizuje kurs waluty.
	@Test
	public void testSetRate() {
	    SEK.setRate(1.0);
	    assertEquals(1, SEK.getRate(), 0);
	}
	
	// Test, który sprawdza, czy metoda universalValue() zwraca poprawn¹ wartoœæ uniwersaln¹ waluty.
	@Test
	public void testGlobalValue() {
		 assertEquals(Integer.valueOf(1500), SEK.universalValue(10000));
    }
    
	
	// Test, który sprawdza, czy metoda valueInThisCurrency() poprawnie konwertuje wartoœæ z jednej waluty na inn¹.
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(15000), SEK.valueInThisCurrency(1500, EUR));
		assertEquals(1000, SEK.valueInThisCurrency(100, EUR),0);
	}

}
