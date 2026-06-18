

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;        // JUnit 5, nu org.junit.Test
import org.junit.jupiter.api.BeforeEach;

public class DonatieTest {
	
	private Donator donator;
	
	@BeforeEach
	public void setUp()
	{
		donator = new Donator(2, "Cismaru Adrian", "cismaru@email.com", "ghencea", TipDonator.PERSOANA_FIZICA);
	}
	
	
	@Test
	public void testeProcesareDonatie()
	{
		Donatie don1 = new Donatie(10, 225, "PaySafeCard");
		Donatie don2 = new Donatie(11, 3.4, "PayPal");
		Donatie don3 = new Donatie(12, -8, "");
		Donatie don4 = new Donatie(13, 0, null);
		Donatie don5 = new Donatie(14, -9, "PayPal");
		Donatie don6 = new Donatie(15, 6, "");
	
		
		assertEquals(true, don1.procesareDonatie(), "Donatie 1 valida");
		assertEquals(true, don2.procesareDonatie(), "Donatie 2 valida");
		assertEquals(false, don3.procesareDonatie(), "Donatie 3 valida");
		assertEquals(false, don4.procesareDonatie(), "Donatie 4 valida");
		assertEquals(false, don5.procesareDonatie(), "Donatie 5 valida");
		assertEquals(false, don6.procesareDonatie(), "Donatie 6 valida");
		
	}
	
	@Test
	public void testeAduagareDonatii()
	{
		// suma este 0, metoda este nula => lista ramane goala
		donator.realizeazaDonatie(0, null);
		assertEquals(0, donator.getIstoricDonatii().size(), "Donatia n-a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// suma este un numar pozitiv, metoda nu este goala => lista se actualizeaza cu un element
		donator.realizeazaDonatie(225, "PaySafeCard");
		assertEquals(1, donator.getIstoricDonatii().size(), "Donatia a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// suma este un numar negativ, metoda este ok => lista ramane neschimbata
		donator.realizeazaDonatie(-9, "PayPal");
		assertEquals(1, donator.getIstoricDonatii().size(), "Donatia n-a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// suma este un numar valid, metoda este un text gol => lista ramane neschimbata
		donator.realizeazaDonatie(5.6, "");
		assertEquals(1, donator.getIstoricDonatii().size(), "Donatia n-a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// suma este un numar pozitiv, metoda este un text valid => lista se actualizeaza
		donator.realizeazaDonatie(5.6, "Card");
		assertEquals(2, donator.getIstoricDonatii().size(), "Donatia a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// suma este 0, metoda este un text valid => lista nu se actualizeaza
		donator.realizeazaDonatie(0, "Card");
		assertEquals(2, donator.getIstoricDonatii().size(), "Donatia n-a fost adaugata.");
		System.out.println(donator.getIstoricDonatii().size());
		
		// curatam istoricul inainte de a trece la alte Teste
		donator.getIstoricDonatii().clear();
	}
	
	@Test
	public void testAdaugareMultiplaSiBucla()
	{
		for (int i=0;i<5;i++)
		{
			donator.realizeazaDonatie(i+1, "PayPal");
		}
		assertEquals(5, donator.getIstoricDonatii().size(), "Donatiile au fost adaugate.");
		
		
		for (int i=0;i<5;i++)
		{
			donator.realizeazaDonatie(i-2, "PayPal");
		}
		assertEquals(7, donator.getIstoricDonatii().size(), "Doar 2 donatii au fost valide si adaugate");
		
		// curatam istoricul inainte de a trece la alte Teste
		donator.getIstoricDonatii().clear();
	}

}
