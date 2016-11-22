package model;
import java.sql.Date;

public class Zamowienie {
	private int idZamowienie;
	private String numerZamowienie;
	private Date dataZamowienie;
	private String kontrahent;
	private String numerTel;
	private String adres;
	
	
	public Zamowienie() {
		
	}

	
	public Zamowienie(int idZamowienie, String numerZamowienie, Date dataZamowienie, String kontrahent, String numerTel,
			String adres) {
		super();
		this.idZamowienie = idZamowienie;
		this.numerZamowienie = numerZamowienie;
		this.dataZamowienie = dataZamowienie;
		this.kontrahent = kontrahent;
		this.numerTel = numerTel;
		this.adres = adres;
	}


	public int getIdZamowienie() {
		return idZamowienie;
	}


	public void setIdZamowienie(int idZamowienie) {
		this.idZamowienie = idZamowienie;
	}
	

	public String getNumerZamowienia() {
		return numerZamowienie;
	}


	public void setNumerZamowienia(String numerZamowienie) {
		this.numerZamowienie = numerZamowienie;
	}


	public Date getDataZamowienie() {
		return dataZamowienie;
	}


	public void setDataZamowienie(Date dataZamowienie) {
		this.dataZamowienie = dataZamowienie;
	}


	public String getKontrahent() {
		return kontrahent;
	}


	public void setKontrahent(String kontrahent) {
		this.kontrahent = kontrahent;
	}


	public String getNumerTel() {
		return numerTel;
	}


	public void setNumerTel(String numerTel) {
		this.numerTel = numerTel;
	}


	public String getAdres() {
		return adres;
	}


	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	
	
}
