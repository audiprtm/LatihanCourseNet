package com.coursenet.latihan.service;

import org.springframework.stereotype.Service;

@Service
public class Anteraja extends Shipper implements ShipperInterface {
	public Anteraja() {
		namaShipper = "Anteraja";
	}
	
//	private static Anteraja anteraja;
//	
//	public static Anteraja getInstance() {
//		if (anteraja == null) {
//			anteraja = new Anteraja();
//		}
//		
//		return anteraja;
//	}
}
