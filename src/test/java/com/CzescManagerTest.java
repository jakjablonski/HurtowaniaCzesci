package com;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manager.CzescManager;

import com.model.Czesc;

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
		Czesc czesc = new Czesc("test123","test1234",19,"test12345");
		assertEquals(true,czescimanager.DodajCzesc(czesc));
		List<Czesc> czesci = czescimanager.getAll();
		Czesc zwrocczesc = czesci.get(0);
		assertEquals("test123",zwrocczesc.getNumerCzesci() );
		
	}
}
