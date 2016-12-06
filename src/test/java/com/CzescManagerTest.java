package com;

import static org.junit.Assert.*;

import java.awt.print.Printable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manager.CzescManager;
import com.manager.ZamowienieManager;
import com.model.Czesc;
import com.model.Zamowienie;







public class CzescManagerTest {
	ZamowienieManager zamowienieManager = new ZamowienieManager();
	CzescManager CzesciManager = new CzescManager();
	
	@Before
	public void clear(){
		zamowienieManager.UsunWszystkieZamowienie();
		CzesciManager.UsunWszystkieCzesc();
	}
	
	
	@Test
	public void test_conection(){
		assertNotNull(zamowienieManager.getConnection());
		assertNotNull(CzesciManager.getConnection());
	}
	
	
	@Test
	public void test_dodaj_czesc(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,zamowienieManager.GetidCzesc("sda5563665"));
		assertEquals(true,CzesciManager.DodajCzesc(czesc));
		
		List<Czesc>czesci = CzesciManager.getAll();
		Czesc cz = czesci.get(0);
		assertEquals("AR7854",cz.getNumerCzesci());
	}
	@Test
	public void test_dodajkilka(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		
		int zamowienie_id=zamowienieManager.GetidCzesc("sda5563665");
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7,zamowienie_id);
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7,zamowienie_id);
		Czesc cz3 = new Czesc ("CD892","świeca",45,zamowienie_id);
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7,zamowienie_id);
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		CzesciManager.UsunWszystkieCzesc();
		CzesciManager.DodajkilkaCzesc(cz);

		List<Czesc> czesci = CzesciManager.getAll();
		assertEquals(4, czesci.size());
	}
	
	@Test
	public void UpdateZamowienie(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Zamowienie zamowienie2 = new Zamowienie("czczxc532","2020-10-11","Nowak");
		zamowienieManager.DodajZamowienie(zamowienie2);
		int id1 = zamowienieManager.GetidCzesc("sda5563665");
		int id2 = zamowienieManager.GetidCzesc("czczxc532");
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,id1);
		CzesciManager.DodajCzesc(czesc);
		Czesc czesc2 = new Czesc();
	}
	/*
	@Test
	public void test_usun(){
		CzesciManager.UsunWszystkieCzesc();
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7);
		CzesciManager.DodajCzesc(czesc);
		Czesc czesc2 = new Czesc("ZD8458","wachacz",198.87);	
		CzesciManager.DodajCzesc(czesc2);
		
		CzesciManager.UsunCzesc("AR7854");
		assertEquals(1, CzesciManager.getAll().size());
	}
	
	@Test
	public void test_dodajkilka(){
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7);
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7);
		Czesc cz3 = new Czesc ("CD892","świeca",45);
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7);
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		CzesciManager.UsunWszystkieCzesc();
		CzesciManager.DodajkilkaCzesc(cz);

		List<Czesc> czesci = CzesciManager.getAll();
		assertEquals(4, czesci.size());
	}
	@Test
	public void update_test(){
		CzesciManager.UsunWszystkieCzesc();
		Czesc czescstara = new Czesc("AR7854","Tuleja",19.7);
		Czesc czescnowa = new Czesc("AR7854","cos",19.7);
		CzesciManager.DodajCzesc(czescstara);	
		assertEquals(1, CzesciManager.UpdateCzesc(czescstara.getNumerCzesci(), czescnowa));
	}
	@Test
	public void test_getczesc(){
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7);
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7);
		Czesc cz3 = new Czesc ("CD892","świeca",45.0);
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7);
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		CzesciManager.UsunWszystkieCzesc();
		CzesciManager.DodajkilkaCzesc(cz);		
		Czesc czesc = CzesciManager.getNumerCzesc("BR007854");
		
		assertEquals(cz2.getNazwa(),czesc.getNazwa());
	}

	
*/
}
