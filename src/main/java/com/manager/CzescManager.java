package com.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Czesc;
import com.model.Zamowienie;

public class CzescManager {
	private Connection conn;
	private Statement stmt;

	private PreparedStatement DodajCzesc;
	private PreparedStatement UsunCzesc;
	private PreparedStatement UsunWszystkieCzesc;
	private PreparedStatement GetCzesc;
	private PreparedStatement GetCzescid;
	private PreparedStatement listaCzesc;
	private PreparedStatement UpdateCzesc;
	private PreparedStatement UpdateZamowienie;
	private PreparedStatement UpdatesetZamowienie;
	private PreparedStatement GetZamowienieCzesc;
	

	public CzescManager() {
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			stmt = conn.createStatement();
			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Czesc".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)

				stmt.executeUpdate("CREATE TABLE Czesc(id bigint GENERATED BY DEFAULT AS IDENTITY, numerczesci varchar(15) unique, nazwa varchar(50), cena double, zamowienie_id int, FOREIGN KEY(zamowienie_id) REFERENCES ZAMOWIENIE(id)  ON DELETE CASCADE ON UPDATE CASCADE)");
			DodajCzesc = conn.prepareStatement("INSERT INTO Czesc (numerczesci, nazwa, cena, zamowienie_id ) VALUES (?,?,?,?)");
			UsunCzesc = conn.prepareStatement("DELETE FROM Czesc WHERE numerczesci = ?");
			UsunWszystkieCzesc = conn.prepareStatement("DELETE FROM Czesc");
			GetCzesc = conn.prepareStatement("SELECT * FROM Czesc WHERE numerczesci = ?");
			GetCzescid = conn.prepareStatement("SELECT * FROM Czesc WHERE id = ?");
			listaCzesc = conn.prepareStatement("SELECT id, numerczesci, nazwa, cena, zamowienie_id FROM Czesc");
			UpdateCzesc = conn.prepareStatement("Update Czesc SET numerczesci = ?, nazwa = ?, cena = ?, zamowienie_id = ? WHERE numerczesci = ?");
			UpdateZamowienie = conn.prepareStatement("Update Czesc SET zamowienie_id=? where numerczesci=?");
			UpdatesetZamowienie = conn .prepareStatement("Update Czesc SET zamowienie_id=(SELECT id FROM Zamowienie WHERE numerzamowienie=?) WHERE numerczesci=?");
			GetZamowienieCzesc = conn.prepareStatement("SELECT id, numerczesci, nazwa, cena, zamowienie_id FROM Czesc WHERE zamowienie_id = (SELECT id FROM Zamowienie WHERE numerczesci=?);");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public Connection getConnection() {
		return conn;
	}

	public void ZmienZamowieniedlaCzesc(Czesc czesc, String numerzamowienie){
		try {
			UpdatesetZamowienie.setString(1, numerzamowienie);
			UpdatesetZamowienie.setString(2, czesc.getNumerCzesci());
			System.out.println(UpdatesetZamowienie.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean DodajCzesc(Czesc czesc) {
		int licznik = 0;
		try {
			DodajCzesc.setString(1, czesc.getNumerCzesci());
			DodajCzesc.setString(2, czesc.getNazwa());
			DodajCzesc.setDouble(3, czesc.getCena());
			DodajCzesc.setInt(4, czesc.getZamowienie_id());
			licznik = DodajCzesc.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}
	
	//dodawnie listy
	public boolean DodajkilkaCzesc(List<Czesc> czesci ) {
		
		try {
			conn.setAutoCommit(false);
			for(Czesc czesc: czesci){
			DodajCzesc.setString(1, czesc.getNumerCzesci());
			DodajCzesc.setString(2, czesc.getNazwa());
			DodajCzesc.setDouble(3, czesc.getCena());
			DodajCzesc.setInt(4, czesc.getZamowienie_id());
			DodajCzesc.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try{
				conn.rollback();
				conn.setAutoCommit(true);
			}catch (SQLException ee) {
			ee.printStackTrace();
		}
		}
		 return false;
	}

	
	public boolean UsunCzesc(String numerczesci) {
		int licznik = 0;
		try {
			
			UsunCzesc.setString(1, numerczesci);
			licznik = UsunCzesc.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}

	
	public void UsunWszystkieCzesc() {
		
		try {
			
			UsunWszystkieCzesc.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public int UpdateCzesc(String czescstara, Czesc czescnowa) {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			UpdateCzesc.setString(1, czescnowa.getNumerCzesci());
			UpdateCzesc.setString(2, czescnowa.getNazwa());
			UpdateCzesc.setDouble(3, czescnowa.getCena());
			UpdateCzesc.setInt(4,czescnowa.getZamowienie_id());
			UpdateCzesc.setString(5, czescstara);
			licznik = UpdateCzesc.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return licznik;
	}

	
	public List<Czesc> getAll() {
		List<Czesc> czesci = new ArrayList<Czesc>();
		try {
			ResultSet rs = listaCzesc.executeQuery();
			while (rs.next()) {
				Czesc cz = new Czesc();
				cz.setIdCzesc(rs.getInt("id"));
				cz.setNumerCzesci(rs.getString("numerczesci"));
				cz.setNazwa(rs.getString("nazwa"));
				cz.setCena(rs.getDouble("cena"));
				cz.setZamowienie_id(rs.getInt("zamowienie_id"));
				czesci.add(cz);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return czesci;
	}

	
	public Czesc getNumerCzesc(String numerczesci) {
		Czesc czesci = new Czesc();
		try {
			GetCzesc.setString(1, numerczesci);
			ResultSet rs = GetCzesc.executeQuery();
			while (rs.next()) {
				czesci.setIdCzesc(rs.getInt("id"));
				czesci.setNumerCzesci(rs.getString("numerczesci"));
				czesci.setNazwa(rs.getString("nazwa"));
				czesci.setCena(rs.getDouble("cena"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return czesci;
	}

	
	public List<Czesc> getallforZamowinie(String numerzamowienia) {
		List<Czesc> czesci = new ArrayList<Czesc>();
		try {
			GetZamowienieCzesc.setString(1, numerzamowienia);
			ResultSet rs = GetZamowienieCzesc.executeQuery();
			while (rs.next()) {
				Czesc cz = new Czesc();
				cz.setNumerCzesci(rs.getString("numerczesci"));
				cz.setNazwa(rs.getString("nazwa"));
				cz.setCena(rs.getDouble("cena"));
				cz.setZamowienie_id(rs.getInt("zamowienie_id"));
				czesci.add(cz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return czesci;
	}
	
	public boolean ChangeCzesctoZamowienie(Czesc czesc,Zamowienie zamowienie){
		int licznik = 0;
		ZamowienieManager zm = new ZamowienieManager();
		try{
			int zamowienie_id = zm.GetidCzesc(zamowienie.getNumerZamowienie());
			UpdateZamowienie.setInt(1, zamowienie_id);
			UpdateZamowienie.setString(2, czesc.getNumerCzesci());
			licznik= UpdateZamowienie.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}
}