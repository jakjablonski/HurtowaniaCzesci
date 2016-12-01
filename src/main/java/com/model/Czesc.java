package com.model;

public class Czesc {

	private int idCzesc;
	private String numerCzesci;
	private String nazwa;
	private double cena;

	public Czesc() {

	}

	public Czesc(String numerCzesci, String nazwa, double cena) {
		super();
		
		this.numerCzesci = numerCzesci;
		this.nazwa = nazwa;
		this.cena = cena;
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

	public String getNumerCzesci() {
		return numerCzesci;
	}

	public void setNumerCzesci(String numerCzesci) {
		this.numerCzesci = numerCzesci;
	}

}
