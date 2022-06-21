package com.coursenet.latihan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coursenet.latihan.entity.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long>{
	
	@Query(
			value="SELECT * FROM shipper where status = 'ACTIVE'",
			nativeQuery=true
	)
	List<Shipper> getAllShipperActive();
}
