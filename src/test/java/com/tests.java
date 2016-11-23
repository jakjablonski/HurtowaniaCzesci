package com;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.manager.CzescManager;
import com.manager.ListaCzesciManager;
import com.manager.ZamowienieManager;
import com.model.Czesc;

import com.manager.*;
import com.model.*;
public class tests {

	@Test
	public void test() {
		CzescManager czescManager = new CzescManager();
		ZamowienieManager zamowienieManager = new ZamowienieManager();
		ListaCzesciManager listaCzesciManager = new ListaCzesciManager();
		
		@Test
		public void checkConnectionTest(){
			assertNotNull(czescManager.getConnection());
			assertNotNull(zamowienieManager.getConnection());
			assertNotNull(listaCzesciManager.getConnection());
					
		}
		@Test
		public void DodajCzesc(){
			Czesc czesc = new Czesc("dsad", "dsadasd", 20, "dsadas");
			czescManager.DodajCzesc(czesc);
			assertEquals(1,czescManager.DodajCzesc(czesc));
			List<Czesc> czesci = czescManager.getAll();
			assertEquals(1,czesci.size());
			assertEquals(czesc.getNumerCzesci(),czesci.get(0).getNumerCzesci());
		}
	}

}
