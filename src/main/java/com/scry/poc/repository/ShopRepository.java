package com.scry.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scry.poc.dto.ShopMngt;

/**
 * This Class is used as Repository for ShopMngt Table
 * 
 * @author Rajeev Kuchana
 *
 */
public interface ShopRepository extends JpaRepository<ShopMngt, Long> {

	
	ShopMngt findByShopName(String shopName);

	List<ShopMngt> findAll();

	
	@Query(value = "SELECT "
			+ "SM.*, "
			+ "("
			+ "   3959 *"
			+ "   acos(cos(radians(:lat)) * "
			+ "   cos(radians(SM.LATITUDE)) * "
			+ "   cos(radians(SM.LONGITUDE) - "
			+ "   radians(:lng)) + "
			+ "   sin(radians(:lat)) *"
			+ "   sin(radians(SM.LATITUDE)))"
			+ ") AS distance "
			+ " FROM SHOP_MNGT as SM "
			+ " ORDER BY distance LIMIT 0, 1", nativeQuery = true)
	ShopMngt findByLatLog(@Param("lat") double lat, @Param("lng") double lng);





}
