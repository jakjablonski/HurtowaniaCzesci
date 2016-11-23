package model;

public class ListaCzesci {
	private int idListaCzesci;
	private Czesc czesc;
	private Zamowienie zamowienie;
	
	public ListaCzesci(){
		
	}
	
	

	public int getIdListaCzesci() {
		return idListaCzesci;
	}

	public void setIdListaCzesci(int idListaCzesci) {
		this.idListaCzesci = idListaCzesci;
	}

	public Czesc getCzesc() {
		return czesc;
	}

	public void setCzesc(Czesc czesc) {
		this.czesc = czesc;
	}

	public Zamowienie getZamowienie() {
		return zamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}
	
}
