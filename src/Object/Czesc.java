package Object;
public class Czesc {
	
	private int idCzesc;	
	private String nazwa;	
	private double cena;
	private String marka;
	
	public Czesc()	{
		
	}
	public Czesc(String nazwa, String marka, double cena)	{
		super();
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

}
