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
	CzescManager czescimanager = new CzescManager();
	
	
	
	@Before
	public void czysc_baze(){
		czescimanager.UsunWszystkieCzesc();
	}
	@Test
	public void test_conection(){
		assertNotNull(czescimanager.getConnection());
	}
	@Test
	public void test_dodaj_czesc(){
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,"Fiat");
		assertEquals(true,czescimanager.DodajCzesc(czesc));
		List<Czesc> czesci = czescimanager.getAll();
		Czesc zwrocczesc = czesci.get(0);
		assertEquals("AR7854",zwrocczesc.getNumerCzesci() );
		assertEquals("Tuleja",zwrocczesc.getNazwa());
		assertEquals("19.7",String.valueOf(zwrocczesc.getCena()));
		assertEquals("Fiat",zwrocczesc.getMarka());
		
	}
	@Test
	public void test_usun(){
		czescimanager.UsunWszystkieCzesc();
		Czesc czesc = new Czesc("AR7854","Tuleja",19.7,"Fiat");
		czescimanager.DodajCzesc(czesc);
		List<Czesc> cz= czescimanager.getAll();
		
		assertEquals(1,cz.size());	
		czescimanager.UsunCzesc(czesc);
		List<Czesc> cze = czescimanager.getAll();
		//System.out.println(cze.size());
		assertEquals(0,cze.size());		
	}
	
	@Test
	public void test_dodajkilka(){
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7,"Fiat");
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7,"Audi");
		Czesc cz3 = new Czesc ("CD892","świeca",45,"Skoda");
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7,"Wszyskie");
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		czescimanager.UsunWszystkieCzesc();
		czescimanager.DodajkilkaCzesc(cz);

		List<Czesc> czesci = czescimanager.getAll();
		assertEquals(4, czesci.size());
	}
	@Test
	public void update_test(){
		czescimanager.UsunWszystkieCzesc();
		Czesc czescstara = new Czesc("AR7854","Tuleja",19.7,"Fiat");
		Czesc czescnowa = new Czesc("AR7854","cos",19.7,"Skoda");
		czescimanager.DodajCzesc(czescstara);	
		assertEquals(1, czescimanager.UpdateCzesc(czescstara.getNumerCzesci(), czescnowa));
	}
	@Test
	public void test_getczesc(){
		List<Czesc> cz = new ArrayList<Czesc>();
		Czesc cz1 = new Czesc("AR7854","Tuleja",19.7,"Fiat");
		Czesc cz2 = new Czesc("BR007854","amortyzator",192.7,"Audi");
		Czesc cz3 = new Czesc ("CD892","świeca",45.0,"Skoda");
		Czesc cz4 = new Czesc ("RDZ875","zarówka",10.7,"Wszyskie");
		cz.add(cz1);
		cz.add(cz2);
		cz.add(cz3);
		cz.add(cz4);
		
		czescimanager.UsunWszystkieCzesc();
		czescimanager.DodajkilkaCzesc(cz);		
		Czesc czesc = czescimanager.getNumerCzesc("BR007854");
		
		assertEquals(cz2.getNazwa(),czesc.getNazwa());
	}

	

}
