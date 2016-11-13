package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListaCzesciManager {
	public class ZamowienieManager {
		private Connection conn;
		private Statement stmt;
		
		private PreparedStatement DodajListCzesc;
		private PreparedStatement UsunListCzesc;
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
				DodajListCzesc = conn.prepareStatement("INSERT INTO ListaCzesci(idCzesc, idZamowienie) VALUES (?,?)");
				UsunListCzesc = conn.prepareStatement("DELETE FROM ListaCzesci WHERE idZamowienie = ? AND idCzesc = ?");
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
		
		}
}
