package com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.manager.ZamowienieManager;

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

}
