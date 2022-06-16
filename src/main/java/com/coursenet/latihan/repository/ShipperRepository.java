package com.coursenet.latihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coursenet.latihan.entity.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long>{

}
