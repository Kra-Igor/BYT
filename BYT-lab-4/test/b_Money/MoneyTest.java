package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	  // Test, który sprawdza czy getAmount() zwraca poprawn¹ wartoœæ.
	@Test
	public void testGetAmount() {
		assertEquals(100, SEK100.getAmount(), 0);
		assertEquals(200, SEK200.getAmount(), 0);
	}

	 // Test, który sprawdza czy getCurrency() zwraca poprawn¹ walutê.
	@Test
	public void testGetCurrency() {
		 assertEquals(SEK, SEK100.getCurrency());
	}

	  // Test, który sprawdza czy toString() zwraca oczekiwan¹ reprezentacjê tekstow¹ waluty.
	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
	}

	// Test, który sprawdza czy universalValue() zwraca poprawn¹ wartoœæ uniwersaln¹.
	@Test
	public void testGlobalValue() {
		 assertEquals(Integer.valueOf(3000), EUR20.universalValue());
	}

	// Test, który sprawdza równoœæ dwóch ró¿nych kwot pieniê¿nych.
	@Test
	public void testEqualsMoney() {
		  Money anotherSEK100 = new Money(10000, SEK);
	        assertTrue(SEK100.equals(anotherSEK100));
	}

	// Test, który sprawdza poprawne dodawanie dwóch kwot pieniê¿nych.
	@Test
	public void testAdd() {
				// 0SEK + 0SEK = 0SEK
				assertEquals(0, SEK0.add(SEK0).getAmount(), 0);
				assertEquals(SEK, SEK0.add(SEK0).getCurrency());
	}
	
	// Test, który sprawdza poprawne odejmowanie dwóch kwot pieniê¿nych.
	@Test
	public void testSub() {

		Money testSEK0 = SEK0.sub(SEK0);
		assertEquals(0, testSEK0.getAmount(),0); // 0-0=0
		assertEquals(SEK, testSEK0.getCurrency()); //0-0=0
	}

	// Test, który sprawdza czy metoda isZero() poprawnie rozpoznaje zerow¹ wartoœæ.
	@Test
	public void testIsZero() {
		  assertTrue(EUR0.isZero());
	}

	// Test, który sprawdza czy metoda negate() poprawnie zmienia wartoœæ na przeciwn¹.
	@Test
	public void testNegate() {
		assertEquals("100.0 SEK", SEKn100.negate().toString());
	}

	// Test, który sprawdza poprawne porównywanie wartoœci dwóch kwot pieniê¿nych.
	@Test
	public void testCompareTo() {
        assertTrue(EUR20.compareTo(EUR10) > 0);
        assertTrue(EUR10.compareTo(EUR20) < 0);
        assertTrue(EUR20.compareTo(EUR20) == 0);
	}
}
