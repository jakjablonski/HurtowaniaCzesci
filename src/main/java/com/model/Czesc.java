package com.model;

public class Czesc {

	private int id;
	private String numerCzesci;
	private String nazwa;
	private double cena;
	private int zamowienie_id;

	public Czesc() {

	}

	public Czesc(String numerCzesci, String nazwa, double cena) {
		super();
		this.numerCzesci = numerCzesci;
		this.nazwa = nazwa;
		this.cena = cena;
		this.zamowienie_id = zamowienie_id;
	}

	public Czesc(String numerCzesci, String nazwa, double cena,int zamowienie_id) {
		super();
		
		this.numerCzesci = numerCzesci;
		this.nazwa = nazwa;
		this.cena = cena;
		this.zamowienie_id = zamowienie_id;
	}

	public int getIdCzesc() {
		return id;
	}

	public void setIdCzesc(int idCzesc) {
		this.id = idCzesc;
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

	public String getNumerCzesci() {
		return numerCzesci;
	}

	public void setNumerCzesci(String numerCzesci) {
		this.numerCzesci = numerCzesci;
	}

	public int getZamowienie_id() {
		return zamowienie_id;
	}

	public void setZamowienie_id(int zamowienie_id) {
		this.zamowienie_id = zamowienie_id;
	}
	
}
