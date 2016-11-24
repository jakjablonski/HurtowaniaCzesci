package com.model;

import java.sql.Date;

public class Zamowienie {
	private int idZamowienie;
	private String numerZamowienie;
	private String dataZamowienie;
	private String kontrahent;
	private String numerTel;
	private String adres;

	public Zamowienie() {

	}

	public Zamowienie(String numerZamowienie, String dataZamowienie, String kontrahent, String numerTel, String adres) {
		super();
		this.numerZamowienie = numerZamowienie;
		this.dataZamowienie = dataZamowienie;
		this.kontrahent = kontrahent;
		this.numerTel = numerTel;
		this.adres = adres;
	}

	public Zamowienie(int idZamowienie, String numerZamowienie, String dataZamowienie, String kontrahent, String numerTel,
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

	public String getNumerZamowienie() {
		return numerZamowienie;
	}

	public void setNumerZamowienie(String numerZamowienie) {
		this.numerZamowienie = numerZamowienie;
	}

	public String getDataZamowienie() {
		return dataZamowienie;
	}

	public void setDataZamowienie(String dataZamowienie) {
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
