package model;
public class Czesc {
	
	private int idCzesc;
	private String numerCzesci;
	private String nazwa;	
	private double cena;
	private String marka;
	
	public Czesc()	{
		
	}
	
	public Czesc(int idCzesc, String numerCzesci, String nazwa, double cena, String marka) {
		super();
		this.idCzesc = idCzesc;
		this.numerCzesci = numerCzesci;
		this.nazwa = nazwa;
		this.cena = cena;
		this.marka = marka;
	}

	public int getIdCzesc() {
		return idCzesc;
	}
	
	public void setIdCzesc(int idCzesc) {
		this.idCzesc = idCzesc;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public double getCena() {
		return cena;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public String getMarka() {
		return marka;
	}
	
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getNumerCzesci() {
		return numerCzesci;
	}
	public void setNumerCzesci(String numerCzesci) {
		this.numerCzesci = numerCzesci;
	}

}
