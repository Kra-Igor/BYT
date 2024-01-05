package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		Nordea = new Bank("Nordea", SEK);
		Nordea.openAccount("test");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 1,1, testAccount.getBalance(), Nordea, "test");

		testAccount.removeTimedPayment("1");
		assertThrows(NullPointerException.class, ()->testAccount.removeTimedPayment("1"));

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		assertFalse(testAccount.timedPaymentExists("1"));
		testAccount.addTimedPayment("1", 1,1, testAccount.getBalance(), Nordea, "test");
		assertTrue(testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
		//Wyp³aciæ pieni¹dze z konta
		testAccount.withdraw(testAccount.getBalance());
		//wyci¹gniêto wszystkie œrodki z konta => powinno byæ 0
		assertEquals(0, testAccount.getBalance().getAmount(), 0); 
	}
	
	@Test
	public void testGetBalance() {
		//Uzyskaj saldo konta
		assertEquals(100000.0, testAccount.getBalance().getAmount(), 0);
	}
}
