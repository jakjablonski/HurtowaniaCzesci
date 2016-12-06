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

public class ZamowienieManager {
	private Connection conn;
	private Statement stmt;

	private PreparedStatement DodajZamowienie;
	private PreparedStatement UsunZamowienie;
	private PreparedStatement UsunWszystkieZamowienie;
	private PreparedStatement GetZamowienie;
	private PreparedStatement GetZamowienieid;
	private PreparedStatement listaZamowienie;
	private PreparedStatement updateZamowienie;
	private PreparedStatement GetidCzesc;

	public ZamowienieManager() {
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			stmt = conn.createStatement();
			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Zamowienie".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)

				stmt.executeUpdate("CREATE TABLE Zamowienie(id bigint GENERATED BY DEFAULT AS IDENTITY, numerzamowienie varchar(10), dataZamowienie date, kontrahent varchar(50), PRIMARY KEY (id))");
			DodajZamowienie = conn.prepareStatement("INSERT INTO Zamowienie (numerzamowienie, dataZamowienie, kontrahent) VALUES (?,?,?)");
			UsunZamowienie = conn.prepareStatement("DELETE FROM Zamowienie WHERE numerzamowienie = ?");
			UsunWszystkieZamowienie = conn.prepareStatement("DELETE FROM Zamowienie");
			GetZamowienie = conn.prepareStatement("SELECT * FROM Zamowienie WHERE numerzamowienie = ?");
			GetZamowienieid = conn.prepareStatement("SELECT * FROM Zamowienie WHERE id = ?");
			listaZamowienie = conn.prepareStatement("SELECT * FROM Zamowienie");
			updateZamowienie = conn.prepareStatement("Update zamowienie set numerzamowienie = ?, dataZamowienie = ?, kontrahent = ?  WHERE numerzamowienie = ?");
			GetidCzesc = conn.prepareStatement("SELECT id from Zamowienie WHERE numerzamowienie = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public boolean DodajZamowienie(Zamowienie zamowienie) {
		int licznik = 0;
		try {
			
			DodajZamowienie.setString(1, zamowienie.getNumerZamowienie());
			DodajZamowienie.setString(2, zamowienie.getDataZamowienie());
			DodajZamowienie.setString(3, zamowienie.getKontrahent());
			licznik = DodajZamowienie.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}
	
public boolean DodajkilkaZamowien(List<Zamowienie> zamowienia ) {
		
		try {
			conn.setAutoCommit(false);
			for(Zamowienie zamowienie:zamowienia){
			DodajZamowienie.setString(1, zamowienie.getNumerZamowienie());
			DodajZamowienie.setString(2, zamowienie.getDataZamowienie());
			DodajZamowienie.setString(3, zamowienie.getKontrahent());
			DodajZamowienie.executeUpdate();
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


	public boolean UsunZamowienie(String numerzamowienie) {
		int licznik = 0;
		try {
			UsunZamowienie.setString(1, numerzamowienie);
			licznik = UsunZamowienie.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}

	public void UsunWszystkieZamowienie() {

		try {
			UsunWszystkieZamowienie.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Zamowienie> getAll() {
		List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
		try {
			ResultSet rs = listaZamowienie.executeQuery();
			while (rs.next()) {
				Zamowienie z = new Zamowienie();
				z.setIdZamowienie(rs.getInt("id"));
				z.setNumerZamowienie(rs.getString("numerzamowienie"));
				z.setDataZamowienie(rs.getString("dataZamowienie"));
				z.setKontrahent(rs.getString("kontrahent"));

				zamowienia.add(z);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zamowienia;
	}

	public Zamowienie getnumerZamowienie(String numerzamowienie) {
		Zamowienie zamowienia = new Zamowienie();
		try {
			GetZamowienie.setString(1, numerzamowienie);
			ResultSet rs = GetZamowienie.executeQuery();
			while (rs.next()) {
				zamowienia.setIdZamowienie(rs.getInt("id"));
				zamowienia.setNumerZamowienie(rs.getString("numerzamowienie"));
				zamowienia.setDataZamowienie(rs.getString("dataZamowienie"));
				zamowienia.setKontrahent(rs.getString("kontrahent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zamowienia;
	}

	public Zamowienie getnumerZamowienieid(int idZamowienie) {
		Zamowienie zamowienia = new Zamowienie();
		try {
			GetZamowienieid.setInt(1, idZamowienie);
			ResultSet rs = GetZamowienieid.executeQuery();
			while (rs.next()) {
				zamowienia.setIdZamowienie(rs.getInt("id"));
				zamowienia.setNumerZamowienie(rs.getString("numerzamowienie"));
				zamowienia.setDataZamowienie(rs.getString("dataZamowienie"));
				zamowienia.setKontrahent(rs.getString("kontrahent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zamowienia;
	}

	public int UpdateZamowienie(Zamowienie zamowieniestare, Zamowienie zamowienienowe) {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			updateZamowienie.setString(1, zamowienienowe.getNumerZamowienie());
			updateZamowienie.setString(2, zamowienienowe.getDataZamowienie());
			updateZamowienie.setString(3, zamowienienowe.getKontrahent());
			updateZamowienie.setString(4, zamowieniestare.getNumerZamowienie());
			licznik = updateZamowienie.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return licznik;
	}
	
	public int GetidCzesc(String numerczesci){
		int licznik = -1;
		try {
			GetidCzesc.setString(1, numerczesci);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = GetidCzesc.executeQuery();
			while (rs.next()) {
				licznik = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return licznik;
	}


}
