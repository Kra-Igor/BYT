package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	String accountId, accountId2;

	Money money;
	
	@Before
	public void setUp() throws Exception {
		accountId = "1";
		accountId2 = "2";
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
		//opened account id: "1", "2"
		SweBank.openAccount(accountId);
		SweBank.openAccount(accountId2);
	}

	@Test
	public void testGetName() {
		 assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		/*Otwarcie konta w tym banku. Rzuca wyj¹tek AccountExistsException, jeœli konto ju¿ istnieje */
		assertThrows(AccountExistsException.class, ()-> SweBank.openAccount(accountId));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		/* Wp³aæ pieni¹dze na konto. Zg³asza wyj¹tek „Konto nie istnieje”, jeœli konto nie istnieje*/
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.deposit("accountId", money)); //assertion Error: unexpected exception type thrown; expected:<b_Money.AccountDoesNotExistException> but was:<java.lang.NullPointerException>

		//w³aœciwy sposób: wp³acanie pieniêdzy na istniej¹ce konto
		//SweBank.deposit(accountId, money);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		/* Wyp³ata pieniêdzy z konta. Zg³asza wyj¹tek „Konto nie istnieje” Jeœli konto nie istnieje */
		assertThrows(AccountDoesNotExistException.class, ()-> SweBank.withdraw("accountId", money));

	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		/* Uzyskaj saldo konta. Zg³asza wyj¹tek „Konto nie istnieje” Jeœli konto nie istnieje */
		assertThrows(AccountDoesNotExistException.class, ()-> SweBank.getBalance("accountId"));

		//poprawny sposób: uzyskaj saldo istniej¹cego konta
		SweBank.getBalance(accountId);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//java.lang.AssertionError: oczekiwano zg³oszenia wyj¹tku b_Money.AccountDoesNotExistException, ale nic nie zosta³o zg³oszone
		assertThrows(AccountDoesNotExistException.class, ()->SweBank.transfer(accountId, "accountId2", money)); 

		//poprawny sposób: oba konta istniej¹
		//SweBank.transfer(accountId, accountId2, money);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		assertThrows(AccountDoesNotExistException.class, () -> SweBank.addTimedPayment("accountId", "1", 1, 1, money, SweBank, accountId));
		SweBank.addTimedPayment(accountId, "1", 1, 1, money, SweBank, accountId);

		assertThrows(AccountDoesNotExistException.class, () -> SweBank.removeTimedPayment("accountId", "1"));
		SweBank.removeTimedPayment(accountId, "1");
	}
}
