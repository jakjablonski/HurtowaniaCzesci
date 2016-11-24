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
				stmt.executeUpdate("Create table Czesc (idCzesc int GENERATED BY DEFAULT AS IDENTITY, numerczesci varchar(15), nazwa varchar(50), cena double, marka varchar(20),PRIMARY KEY(idCzesc) )");
			DodajCzesc = conn.prepareStatement("INSERT INTO Czesc(numerczesci, nazwa, cena ,marka) VALUES (?,?,?,?)");
			UsunCzesc = conn.prepareStatement("DELETE FROM Czesc WHERE idCzesc = ?");
			UsunWszystkieCzesc = conn.prepareStatement("DELETE FROM Czesc");
			GetCzesc = conn.prepareStatement("SELECT * FROM Czesc WHERE numerczesci = ?");
			GetCzescid = conn.prepareStatement("SELECT * FROM Czesc WHERE idCzesc = ?");
			listaCzesc = conn.prepareStatement("SELECT * FROM Czesc");
			UpdateCzesc = conn.prepareStatement("Update Czesc SET numerczesci = ?, nazwa = ?, cena = ?, marka = ? WHERE numerczesci = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public Connection getConnection() {
		return conn;
	}

	
	public boolean DodajCzesc(Czesc czesc) {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			DodajCzesc.setString(1, czesc.getNumerCzesci());
			DodajCzesc.setString(2, czesc.getNazwa());
			DodajCzesc.setDouble(3, czesc.getCena());
			DodajCzesc.setString(4, czesc.getMarka());
			licznik = DodajCzesc.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(licznik == 1) return true;
		else return false;
	}

	
	public int UsunCzesc(Czesc czesc) {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			UsunCzesc.setInt(1, czesc.getIdCzesc());
			licznik = UsunCzesc.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return licznik;
	}

	
	public int UsunWszystkieCzesc() {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			licznik = UsunWszystkieCzesc.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return licznik;
	}

	
	public int UpdateCzesc(Czesc czescstara, Czesc czescnowa) {
		int licznik = 0;
		try {
			conn.setAutoCommit(false);
			UpdateCzesc.setString(1, czescnowa.getNumerCzesci());
			UpdateCzesc.setString(2, czescnowa.getNazwa());
			UpdateCzesc.setDouble(3, czescnowa.getCena());
			UpdateCzesc.setString(4, czescnowa.getMarka());
			UpdateCzesc.setString(5, czescstara.getNumerCzesci());
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
				cz.setIdCzesc(rs.getInt("idCzesc"));
				cz.setNumerCzesci(rs.getString("numerczesci"));
				cz.setNazwa(rs.getString("nazwa"));
				cz.setCena(rs.getDouble("cena"));
				cz.setMarka(rs.getString("marka"));
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
				czesci.setIdCzesc(rs.getInt("idCzesc"));
				czesci.setNumerCzesci(rs.getString("numerczesci"));
				czesci.setNazwa(rs.getString("nazwa"));
				czesci.setCena(rs.getDouble("cena"));
				czesci.setMarka(rs.getString("marka"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return czesci;
	}

	
	public Czesc getidCzesc(String idczesc) {
		Czesc czesci = new Czesc();
		try {
			GetCzescid.setString(1, idczesc);
			ResultSet rs = GetCzescid.executeQuery();
			while (rs.next()) {
				czesci.setIdCzesc(rs.getInt("idCzesc"));
				czesci.setNumerCzesci(rs.getString("numerczesci"));
				czesci.setNazwa(rs.getString("nazwa"));
				czesci.setCena(rs.getDouble("cena"));
				czesci.setMarka(rs.getString("marka"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return czesci;
	}
}