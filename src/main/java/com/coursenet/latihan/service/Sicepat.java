package com.coursenet.latihan.service;

import org.springframework.stereotype.Service;

@Service
public class Sicepat extends Shipper implements ShipperInterface{
	public Sicepat() {
		namaShipper = "Sicepat";
	}
	
	private static Sicepat sicepat;
	public static Sicepat getInstance() {
		if (sicepat == null) {
			sicepat = new Sicepat();
		}
		
		return sicepat;
	}
}
