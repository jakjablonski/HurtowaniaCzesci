package com;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manager.CzescManager;
import com.manager.ZamowienieManager;
import com.model.Czesc;
import com.model.Zamowienie;

public class ZamowienieManagerTest {

	ZamowienieManager ZamowienieManager = new ZamowienieManager();
	CzescManager CzesciManager = new CzescManager();
	
	
	
	@Test
	public void testConection(){
		assertNotNull(ZamowienieManager.getConnection());
	}
	
	@Test
	public void testDodajZamowienie(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		assertEquals(true,ZamowienieManager.DodajZamowienie(zamowienie));
		List<Zamowienie> zamowienia = ZamowienieManager.getAll();
		Zamowienie zwroczam = zamowienia.get(0);
		assertEquals("sda5563665",zwroczam.getNumerZamowienie());
		assertEquals("2000-10-11",zwroczam.getDataZamowienie());
		assertEquals("Kowalski",zwroczam.getKontrahent());

	}
	@Test
	public void testUsun(){
		ZamowienieManager.UsunWszystkieZamowienie();
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		ZamowienieManager.DodajZamowienie(zamowienie);
		Zamowienie zamowienie2 = new Zamowienie("sda5563666","2011-10-11","Kowalski2");
		ZamowienieManager.DodajZamowienie(zamowienie2);
		
		assertEquals(2,ZamowienieManager.getAll().size());
		ZamowienieManager.UsunZamowienie("sda5563665");
		assertEquals(1,ZamowienieManager.getAll().size());
		
	}
	@Test
	public void testDodajKilkaZamowien(){
		List<Zamowienie> z = new ArrayList<Zamowienie>();
		Zamowienie z1 = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie z2 = new Zamowienie("RMA9875","2220-10-11","xxxkk");
		Zamowienie z3 = new Zamowienie("ZDF8954","2003-10-11","TEst");
		Zamowienie z4 = new Zamowienie ("ZAE8982","2090-10-11","Nowak");
		z.add(z1);
		z.add(z2);
		z.add(z3);
		z.add(z4);
		
		ZamowienieManager.UsunWszystkieZamowienie();
		ZamowienieManager.DodajkilkaZamowien(z);

		List<Zamowienie> zm = ZamowienieManager.getAll();
		assertEquals(4, zm.size());
	}
	@Test
	public void testGetZamowienie(){
		List<Zamowienie> z = new ArrayList<Zamowienie>();
		Zamowienie z1 = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie z2 = new Zamowienie("RMA9875","2220-10-11","xxxkk");
		Zamowienie z3 = new Zamowienie("ZDF8954","2003-10-11","TEst");
		Zamowienie z4 = new Zamowienie ("ZAE8982","2090-10-11","Nowak");
		z.add(z1);
		z.add(z2);
		z.add(z3);
		z.add(z4);
		
		ZamowienieManager.UsunWszystkieZamowienie();
		ZamowienieManager.DodajkilkaZamowien(z);
				
		Zamowienie zamowienie = ZamowienieManager.getnumerZamowienie("ZAE8982");
		
		assertEquals(z4.getNumerZamowienie(),zamowienie.getNumerZamowienie());
		ZamowienieManager.UsunWszystkieZamowienie();
	}
	@Test
	public void updateTest(){
		ZamowienieManager.UsunWszystkieZamowienie();
		Zamowienie zamowieniestare = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","NOOOOWWAAK");
		ZamowienieManager.DodajZamowienie(zamowieniestare);
		assertEquals(1, ZamowienieManager.UpdateZamowienie(zamowieniestare, zamowienie));
	}
	
}
