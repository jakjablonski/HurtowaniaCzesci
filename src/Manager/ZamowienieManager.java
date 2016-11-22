
package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Czesc;
import model.Zamowienie;

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
			if (!tableExists) stmt.executeUpdate("Create table Zamowienie (idZamowienie int UNIQUE GENERATED BY DEFAULT AS IDENTITY, numerzamowienie varchar(10), dataZamowienie date DEFAULT CURRENT_DATE, kontrahent varchar(50), numertel varchar(11), adres varchar(50)");
			DodajZamowienie = conn.prepareStatement("INSERT INTO Zamowienie(numerzamowienia,kontrahent, suma ,dataZamowienie) VALUES (?,?,?)");
			UsunZamowienie = conn.prepareStatement("DELETE FROM Zamowienie WHERE numerzamowienie = ?");
			UsunWszystkieZamowienie = conn.prepareStatement("DELETE FROM Zamowienie");
			GetZamowienie = conn.prepareStatement("SELECT * FROM Zamowienie WHERE numerzamowienie = ?");
			GetZamowienieid = conn.prepareStatement("SELECT * FROM Zamowienie WHERE idZamowienie = ?");
			listaZamowienie = conn.prepareStatement("SELECT * FROM Zamowienie");
			updateZamowienie = conn.prepareStatement("Update zamowienie set numerzamowienie = ?, dataZamowienie = ?, kontrahent = ?, numertel = ?, adres = ? WHERE numerzamowienie = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public int DodajZamowienie(Zamowienie zamowienie){
		int licznik = 0;
			try {
				conn.setAutoCommit(false);
				DodajZamowienie.setString(1, zamowienie.getNumerZamowienia());
				DodajZamowienie.setDate(2, zamowienie.getDataZamowienie());
				DodajZamowienie.setString(3, zamowienie.getKontrahent());
				DodajZamowienie.setString(4, zamowienie.getNumerTel());
				DodajZamowienie.setString(5, zamowienie.getAdres());
				licznik = DodajZamowienie.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return licznik;
		}
	
	public int UsunZamowienie(Zamowienie zamowienie){
		int licznik = 0;
			try {
				conn.setAutoCommit(false);
				UsunZamowienie.setString(1, zamowienie.getNumerZamowienia());
				licznik = UsunZamowienie.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return licznik;
		}
	
	public int UsunWszystkieZamowienie(Zamowienie zamowienie){
		int licznik = 0;
			try {
				conn.setAutoCommit(false);
				licznik = UsunWszystkieZamowienie.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return licznik;
		}
	
	public List<Zamowienie> getAll(){
		List<Zamowienie> zamowienie = new ArrayList<Zamowienie>();
		try{
			ResultSet rs = listaZamowienie.executeQuery();
			while(rs.next()){
				Zamowienie zamowienia = new Zamowienie();
				zamowienia.setIdZamowienie(rs.getInt("idZamowienie"));
				zamowienia.setNumerZamowienia(rs.getString("numerzamowienie"));
				zamowienia.setDataZamowienie(rs.getDate("dataZamowienie"));
				zamowienia.setKontrahent(rs.getString("kontrahent"));
				zamowienia.setNumerTel(rs.getString("numertel"));
				zamowienia.setAdres(rs.getString("adres"));
				((List<Zamowienie>) zamowienia).add(zamowienia);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamowienie;
	}
	
	public Zamowienie getnumerZamowienie(String numerzamowienie) {
		Zamowienie zamowienia = new Zamowienie();
		try{
			GetZamowienie.setString(1, numerzamowienie);
			ResultSet rs = GetZamowienie.executeQuery();
			while(rs.next()){
				zamowienia.setIdZamowienie(rs.getInt("idZamowienie"));
				zamowienia.setNumerZamowienia(rs.getString("numerzamowienie"));
				zamowienia.setDataZamowienie(rs.getDate("dataZamowienie"));
				zamowienia.setKontrahent(rs.getString("kontrahent"));
				zamowienia.setNumerTel(rs.getString("numertel"));
				zamowienia.setAdres(rs.getString("adres"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamowienia;
	}
	
	public Zamowienie getnumerZamowienieid(String idZamowienie) {
		Zamowienie zamowienia = new Zamowienie();
		try{
			GetZamowienieid.setString(1, idZamowienie);
			ResultSet rs = GetZamowienieid.executeQuery();
			while(rs.next()){
				zamowienia.setIdZamowienie(rs.getInt("idZamowienie"));
				zamowienia.setNumerZamowienia(rs.getString("numerzamowienie"));
				zamowienia.setDataZamowienie(rs.getDate("dataZamowienie"));
				zamowienia.setKontrahent(rs.getString("kontrahent"));
				zamowienia.setNumerTel(rs.getString("numertel"));
				zamowienia.setAdres(rs.getString("adres"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamowienia;
	}
	public int UpdateZamowienie(Zamowienie zamowieniestare, Zamowienie zamowienienowe){
		int licznik = 0;
			try {
				conn.setAutoCommit(false);
				updateZamowienie.setString(1, zamowienienowe.getNumerZamowienia());
				updateZamowienie.setDate(2, zamowienienowe.getDataZamowienie());
				updateZamowienie.setString(3, zamowienienowe.getKontrahent());
				updateZamowienie.setString(4, zamowienienowe.getNumerTel());
				updateZamowienie.setString(5, zamowienienowe.getAdres());
				updateZamowienie.setString(6, zamowieniestare.getNumerZamowienia());
				licznik = updateZamowienie.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return licznik;
		}
	
	}
