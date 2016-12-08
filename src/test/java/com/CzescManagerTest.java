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







public class CzescManagerTest {
	ZamowienieManager zamowienieManager = new ZamowienieManager();
	CzescManager CzesciManager = new CzescManager();
	
	@Before
	public void clear(){
		zamowienieManager.UsunWszystkieZamowienie();
		CzesciManager.UsunWszystkieCzesc();
	}
	
	
	@Test
	public void testConection(){
		assertNotNull(zamowienieManager.getConnection());
		assertNotNull(CzesciManager.getConnection());
	}
	
	
	@Test
	public void testDodajCzesc(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,zamowienieManager.GetidCzesc("sda5563665"));
		assertEquals(true,CzesciManager.DodajCzesc(czesc));
		
		List<Czesc>czesci = CzesciManager.getAll();
		Czesc cz = czesci.get(0);
		assertEquals("AR7854",cz.getNumerCzesci());
	}
	@Test
	public void testDodajKilka(){
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
	public void testZmienZamowienie(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Zamowienie zamowienie2 = new Zamowienie("czczxc532","2020-10-11","Nowak");
		zamowienieManager.DodajZamowienie(zamowienie2);
		int id1 = zamowienieManager.GetidCzesc("sda5563665");
		int id2 = zamowienieManager.GetidCzesc("czczxc532");
		
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,id1);
		CzesciManager.DodajCzesc(czesc);
		assertEquals(true,CzesciManager.ChangeCzesctoZamowienie(czesc, zamowienie2));
		List<Czesc> czesci = CzesciManager.getAll();
		Czesc returnczesc = czesci.get(0);
		assertEquals(id2,returnczesc.getZamowienie_id());
		
	}
	@Test	
	public void testGetCzesc(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		
		int zamowienie_id = zamowienieManager.GetidCzesc("sda5563665");
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7,zamowienie_id);
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7,zamowienie_id);
		Czesc cz3 = new Czesc ("CD892","świeca",45,zamowienie_id);
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7,zamowienie_id);
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		CzesciManager.DodajkilkaCzesc(cz);
		
		List<Czesc> czesci =  CzesciManager.getAll();
		assertEquals(4, czesci.size());
	}
	
	@Test
	public void updateZamowienie(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		Zamowienie zamowienie2 = new Zamowienie("czczxc532","2020-10-11","Nowak");
		zamowienieManager.DodajZamowienie(zamowienie2);
		int id1 = zamowienieManager.GetidCzesc("sda5563665");
		int id2 = zamowienieManager.GetidCzesc("czczxc532");
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,id1);
		CzesciManager.DodajCzesc(czesc);
		
		Czesc poprawczesc = new Czesc("AR7854","Tuleja",19.7,id2);
		assertEquals(1, CzesciManager.UpdateCzesc(czesc.getNumerCzesci(), poprawczesc));
	}
	@Test
	public void deleteTest(){
		Zamowienie zamowienie = new Zamowienie("sda5563665","2000-10-11","Kowalski");
		zamowienieManager.DodajZamowienie(zamowienie);
		int zamowienie_id = zamowienieManager.GetidCzesc("sda5563665");
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,zamowienie_id);
		Czesc czesc2 = new Czesc("abcdef","Test",11.7,zamowienie_id);
		CzesciManager.DodajCzesc(czesc);
		CzesciManager.DodajCzesc(czesc2);
		
		CzesciManager.UsunCzesc(czesc.getNumerCzesci());
		List<Czesc> czesci = CzesciManager.getAll();
		assertEquals(1, czesci.size());
		
	}
}
