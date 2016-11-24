package com;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manager.CzescManager;
import com.manager.ListaCzesciManager;
import com.manager.ZamowienieManager;
import com.model.Czesc;
import com.model.Zamowienie;

public class ListaCzesciManagerTest {
	ListaCzesciManager listaczescimanager = new ListaCzesciManager();
	CzescManager czescmanager = new CzescManager();
	ZamowienieManager zamowieniemanager = new ZamowienieManager();
	
	@Before
	public void czysc_baze(){
		listaczescimanager.UsunWszystkieListCzesc();
	}
	@Test
	public void test_conection(){
		assertNotNull(listaczescimanager.getConnection());
	}
	@Test
	public void test_dodajlista(){
	czescmanager.UsunWszystkieCzesc();
	zamowieniemanager.UsunWszystkieZamowienie();
	
	Zamowienie zam = new Zamowienie("sda5563665","2000-10-11","Kowalski","12","Gdansk");
	Czesc cz1 = new Czesc("AR7854","Tuleja",19.7,"Fiat");
	Czesc cz2 = new Czesc("BR007854","amortyzator",192.7,"Audi");
	
	assertEquals(true,zamowieniemanager.DodajZamowienie(zam));
	assertEquals(true,czescmanager.DodajCzesc(cz1));
	assertEquals(true,czescmanager.DodajCzesc(cz2));
	
	zam = zamowieniemanager.getnumerZamowienie(zam.getNumerZamowienie());
	cz1 = czescmanager.getNumerCzesc(cz1.getNumerCzesci());
	cz2 = czescmanager.getNumerCzesc(cz2.getNumerCzesci());
	
	List<Czesc> czesci = listaczescimanager.
	
	}
}
