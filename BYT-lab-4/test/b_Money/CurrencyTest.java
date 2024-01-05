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

	 // Test, kt�ry sprawdza, czy metoda getName() zwraca poprawn� nazw� waluty.
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	  // Test, kt�ry sprawdza, czy metoda getRate() zwraca poprawny kurs waluty.
	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0);
		assertEquals(0.20, DKK.getRate(), 0);
		assertEquals(1.5, EUR.getRate(), 0);
	}
	
	// Test, kt�ry sprawdza, czy metoda setRate() poprawnie aktualizuje kurs waluty.
	@Test
	public void testSetRate() {
	    SEK.setRate(1.0);
	    assertEquals(1, SEK.getRate(), 0);
	}
	
	// Test, kt�ry sprawdza, czy metoda universalValue() zwraca poprawn� warto�� uniwersaln� waluty.
	@Test
	public void testGlobalValue() {
		 assertEquals(Integer.valueOf(1500), SEK.universalValue(10000));
    }
    
	
	// Test, kt�ry sprawdza, czy metoda valueInThisCurrency() poprawnie konwertuje warto�� z jednej waluty na inn�.
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(15000), SEK.valueInThisCurrency(1500, EUR));
		assertEquals(1000, SEK.valueInThisCurrency(100, EUR),0);
	}

}
