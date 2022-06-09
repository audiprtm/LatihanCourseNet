package com.coursenet.latihan.service;

import org.springframework.stereotype.Service;

@Service
public class Gojek extends Shipper implements ShipperInterface {
	public Gojek() {
		namaShipper = "Gojek";
	}
	
	
	//lagi ngoding fitur
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
