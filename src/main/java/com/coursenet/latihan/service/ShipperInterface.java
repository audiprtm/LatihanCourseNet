package com.coursenet.latihan.service;

import com.coursenet.latihan.domain.Paket;

public interface ShipperInterface {
	public void sendPackage(Paket paket);
	public void checkPackage(Paket paket);
}
