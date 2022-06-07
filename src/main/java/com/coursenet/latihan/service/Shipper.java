package com.coursenet.latihan.service;

import com.coursenet.latihan.domain.Paket;

public class Shipper {
	//Ngoding hari ke 3
	
	private int flagKirim;
	protected String namaShipper;
	
	public void sendPackage(Paket paket) {
		System.out.println(namaShipper+ " sedang mengirim paket " + paket.getNamaBarang());
		flagKirim=1;
	}
	
	public void checkPackage(Paket paket) {
		if(flagKirim ==1) {
			System.out.println(namaShipper+  " sudah mengirim paket "+ paket.getNamaBarang());
		}else {
			System.out.println(namaShipper+ " belum mengirim " + paket.getNamaBarang());
		}
		
	}
}
