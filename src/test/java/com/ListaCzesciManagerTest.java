package com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.manager.ListaCzesciManager;

public class ListaCzesciManagerTest {
	ListaCzesciManager listaczescimanager = new ListaCzesciManager();
	
	@Before
	public void czysc_baze(){
		listaczescimanager.UsunWszystkieListCzesc();
	}
	@Test
	public void test_conection(){
		assertNotNull(listaczescimanager.getConnection());
	}

}
