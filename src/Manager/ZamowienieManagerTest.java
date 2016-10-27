package Manager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import Object.*;
public class ZamowienieManagerTest {
	
	ZamowienieManager zamowienieManager = new ZamowienieManager();
	
	private String Name_1="zeee";
	private int YOB_1 = 1999;
	
	@Test
	public void checkConnection(){
		assertNotNull(zamowienieManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
				
	}

}
