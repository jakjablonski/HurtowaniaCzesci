package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.Czesc;
import model.Zamowienie;

public class ListaCzesciManager {
	public class ZamowienieManager {
		private Connection conn;
		private Statement stmt;
		
		private PreparedStatement DodajCzescdoZamowienia;
		private PreparedStatement UsunCzesczZamowienia;
		private PreparedStatement UsunWszystkieListCzescid;
		private PreparedStatement UsunWszystkieListCzesc;
		private PreparedStatement GetListaCzesc;
		private PreparedStatement GetListCzescid;
		
		
		public ZamowienieManager(){
			try{
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
				if (!tableExists) stmt.executeUpdate("Create table ListaCzesci (idListaCzesci int UNIQUE GENERATED BY DEFAULT AS IDENTITY,idCzesc int,idZamownienie int )");
				DodajCzescdoZamowienia = conn.prepareStatement("INSERT INTO ListaCzesci(idCzesc, idZamowienie) VALUES (?,?)");
				UsunCzesczZamowienia = conn.prepareStatement("DELETE FROM ListaCzesci WHERE idZamowienie = ? AND idCzesc = ?");
				UsunWszystkieListCzescid = conn.prepareStatement("DELETE FROM ListaCzesci WHERE idZamowienia = ?");
				UsunWszystkieListCzesc = conn.prepareStatement("DELETE FROM ListaCzesci");
				GetListaCzesc = conn.prepareStatement("SELECT * FROM Czesc");
				GetListCzescid = conn.prepareStatement("SELECT * FROM Czesc WHERE idZamowienie = ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			return conn;
		}
		
		public int DodajCzescdoZamowienia(Zamowienie zamowienie, Czesc czesc) {
			int licznik = 0;
			try {
				conn.setAutoCommit(false);
				DodajCzescdoZamowienia.setInt(1, czesc.getIdCzesc());
				DodajCzescdoZamowienia.setInt(2, zamowienie.getIdZamowienie());
				licznik = DodajCzescdoZamowienia.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return licznik;
		}
		
		public int UsunCzesczZamowienia(Zamowienie zamowienie, Czesc czesc) {
			int licznik = 0;
			try {
				conn.setAutoCommit(false);
				UsunCzesczZamowienia.setInt(1, zamowienie.getIdZamowienie());
				UsunCzesczZamowienia.setInt(2, czesc.getIdCzesc());
				licznik = UsunCzesczZamowienia.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return licznik;
		}
		
		public int UsunWszystkieListCzescid(Zamowienie zamowienie) {
			int licznik = 0;
			try {
				conn.setAutoCommit(false);
				UsunWszystkieListCzescid.setInt(1, zamowienie.getIdZamowienie());
				licznik = UsunWszystkieListCzescid.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return licznik;
		}
		
		public int UsunWszystkieListCzesc(Zamowienie zamowienie) {
			int licznik = 0;
			try {
				conn.setAutoCommit(false);				
				licznik = UsunWszystkieListCzesc.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return licznik;
		}
		//wyswietalnie select�w
		
		}
}
