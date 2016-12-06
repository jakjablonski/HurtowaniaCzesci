package com;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manager.ZamowienieManager;
import com.model.Czesc;
import com.model.Zamowienie;

public class ZamowienieManagerTest {

	ZamowienieManager zamowienieManager = new ZamowienieManager();
	
	@Before
	public void czysc_baze(){
		zamowienieManager.UsunWszystkieZamowienie();
	}
	@Test
	public void test_conection(){
		assertNotNull(zamowienieManager.getConnection());
	}
	
	@Test
	public void test_dodaj_zamowienie(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		assertEquals(true,zamowienieManager.DodajZamowienie(zamowienie));
		List<Zamowienie> zamowienia = zamowienieManager.getAll();
		Zamowienie zwroczam = zamowienia.get(0);
		assertEquals("sda5563665",zwroczam.getNumerZamowienie());
		assertEquals("2000-10-11",zwroczam.getDataZamowienie());
		assertEquals("Kowalski",zwroczam.getKontrahent());

	}
	//tu popraw
	@Test
	public void test_usun(){
		zamowienieManager.UsunWszystkieZamowienie();
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Zamowienie zamowienie2 = new Zamowienie("sda5563666","2011-10-11","Kowalski2");
		zamowienieManager.DodajZamowienie(zamowienie2);
		List<Zamowienie> z = new ArrayList<Zamowienie>();
		
		assertEquals(2,zamowienieManager.getAll().size());
		zamowienieManager.UsunZamowienie("sda5563665");
		assertEquals(1,zamowienieManager.getAll().size());
		
	}
	@Test
	public void test_dodajkilkazam(){
		List<Zamowienie> z = new ArrayList<Zamowienie>();
		Zamowienie z1 = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie z2 = new Zamowienie("RMA9875","2220-10-11","xxxkk");
		Zamowienie z3 = new Zamowienie("ZDF8954","2003-10-11","TEst");
		Zamowienie z4 = new Zamowienie ("ZAE8982","2090-10-11","Nowak");
		z.add(z1);
		z.add(z2);
		z.add(z3);
		z.add(z4);
		
		zamowienieManager.UsunWszystkieZamowienie();
		zamowienieManager.DodajkilkaZamowien(z);

		List<Zamowienie> zm = zamowienieManager.getAll();
		assertEquals(4, zm.size());
	}
	@Test
	public void test_getzamowienie(){
		List<Zamowienie> z = new ArrayList<Zamowienie>();
		Zamowienie z1 = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie z2 = new Zamowienie("RMA9875","2220-10-11","xxxkk");
		Zamowienie z3 = new Zamowienie("ZDF8954","2003-10-11","TEst");
		Zamowienie z4 = new Zamowienie ("ZAE8982","2090-10-11","Nowak");
		z.add(z1);
		z.add(z2);
		z.add(z3);
		z.add(z4);
		
		zamowienieManager.UsunWszystkieZamowienie();
		zamowienieManager.DodajkilkaZamowien(z);
				
		Zamowienie zamowienie = zamowienieManager.getnumerZamowienie("ZAE8982");
		
		assertEquals(z4.getNumerZamowienie(),zamowienie.getNumerZamowienie());
		zamowienieManager.UsunWszystkieZamowienie();
	}
	@Test
	public void update_test(){
		zamowienieManager.UsunWszystkieZamowienie();
		Zamowienie zamowieniestare = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","NOOOOWWAAK");
		zamowienieManager.DodajZamowienie(zamowieniestare);
		assertEquals(1, zamowienieManager.UpdateZamowienie(zamowieniestare, zamowienie));
	}
	
}
