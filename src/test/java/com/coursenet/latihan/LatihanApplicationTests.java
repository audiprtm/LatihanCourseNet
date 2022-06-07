package com.coursenet.latihan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coursenet.latihan.config.FirstComponent;
import com.coursenet.latihan.domain.Paket;
import com.coursenet.latihan.service.Anteraja;
import com.coursenet.latihan.service.ShipperInterface;

@SpringBootTest
class LatihanApplicationTests {
	@Autowired
	private FirstComponent firstComponent;
	
	@Autowired
	private ShipperInterface anteraja;
	
	@Autowired
	private ShipperInterface sicepat;
	
	
	@Test
	void contextLoads() {
		firstComponent.sayHello();
		
		Paket paket = new Paket();
		paket.setNamaBarang("Meja Belajar");
		paket.setShipperId(2);
		
		ShipperInterface shipper = null;
		shipper = mappingShipper(paket);

		shipper.checkPackage(paket);
		shipper.sendPackage(paket);
		shipper.checkPackage(paket);
	}


	private ShipperInterface mappingShipper(Paket paket) {
		if(paket.getShipperId()==1) {
			return anteraja;
		}else if(paket.getShipperId()==2) {
			return sicepat;
		}
		
		return null;
	}

}
