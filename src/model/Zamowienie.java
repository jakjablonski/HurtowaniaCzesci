package model;
import java.sql.Date;

public class Zamowienie {
	private int idZamowienie;	
	private Date dataZamowienia;
	private String kontrahent;
	
	
	public Zamowienie(Date dataZamowienia, String kontrahent) {
		super();
		this.dataZamowienia = dataZamowienia;
		this.kontrahent = kontrahent;
	}


	public int getIdZamowienie() {
		return idZamowienie;
	}


	public void setIdZamowienie(int idZamowienie) {
		this.idZamowienie = idZamowienie;
	}


	public Date getDataZamowienia() {
		return dataZamowienia;
	}


	public void setDataZamowienia(Date dataZamowienia) {
		this.dataZamowienia = dataZamowienia;
	}


	public String getKontrahent() {
		return kontrahent;
	}


	public void setKontrahent(String kontrahent) {
		this.kontrahent = kontrahent;
	}

	
}
