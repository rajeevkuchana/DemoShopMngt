package com.scry.poc.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scry.poc.dto.ShopMngt;
import com.scry.poc.model.Shop;
import com.scry.poc.model.ShopAddress;
import com.scry.poc.model.geo.Result;
import com.scry.poc.model.geo.Root;
import com.scry.poc.repository.ShopRepository;

/**
 * This Class is used as service layer for Shop Management functionality.
 * 
 * @author Rajeev Kuchana
 *
 */

@Service
public class ShopService {

	private static final Logger log = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${googleMap.API}")
	private String googleMapAPI;
	
	@Value("${googleMap.API.key}")
	private String googleMapAPIKey;
	
	/**
	 * This Method is used to find the Nearest Shop for a given Latitude and
	 * Longitude
	 *
	 * input parameter : Latitude and Longitude return type : Shop
	 */
	public Shop getShop(double latitude, double Longitude) {
		Shop shop = null;
		try {
			ShopMngt nearestShop = shopRepository.findByLatLog(latitude, Longitude);

			if (null != nearestShop) {
				shop = new Shop();
				shop.setShopName(nearestShop.getShopName());
				ShopAddress shopAddress = new ShopAddress();
				shopAddress.setNumber(nearestShop.getShopNumber());
				shopAddress.setPostCode(nearestShop.getShopPostcode());
				shop.setShopAddress(shopAddress);
			}
		} catch (Exception e) {
			log.error("Error Occurred while fetchig nearesr Shop Details");
		}

		return shop;
	}

	/**
	 * This Method is used to Save the Shop details along with Latitude and
	 * Longitude and a status of the operation Added/Updated
	 *
	 * input parameter : Shop return type : String
	 */
	public String postShop(Shop shop) {
		String addSatuString = "Added";
		ShopMngt existingShop = shopRepository.findByShopName(shop.getShopName());
		Root geoLocation = null;
		if (null == existingShop) {
			existingShop = new ShopMngt();
			existingShop.setShopName(shop.getShopName());
		} else {
			addSatuString = "Updated";
		}
		existingShop.setShopPostcode(shop.getShopAddress().getPostCode());
		existingShop.setShopNumber(shop.getShopAddress().getNumber());

		try {
			geoLocation = getGeoCoordinates(shop.getShopAddress().getPostCode());
			List<Result> geoResultList = geoLocation.getResults();
			Result geoResult = geoResultList.get(0);
			if (null != geoResult) {
				existingShop.setLatitude(geoResult.getGeometry().getLocation().getLat());
				existingShop.setLongitude(geoResult.getGeometry().getLocation().getLng());
			} else {
				existingShop.setLatitude(null);
				existingShop.setLongitude(null);
			}
			ShopMngt shopMngt = shopRepository.saveAndFlush(existingShop);
			if (null != shopMngt) {
				addSatuString = "Error";
			}
		} catch (URISyntaxException e) {
			log.error("Error Occurred while parsing the input");
		} catch (Exception e) {
			log.error("Error Occurred while saving the Shop Details");
		}
		return addSatuString;
	}

	/**
	 * This Method is used to fine the Latitude and Longitude of a given PostCode
	 *
	 * input parameter : PostCode return type : Root
	 */
	private Root getGeoCoordinates(String postCode) throws URISyntaxException {
		Root geoLocation = null;
		final String baseUrl = googleMapAPI + postCode + googleMapAPIKey;
		URI uri = new URI(baseUrl);

		ResponseEntity<Root> geoLocationEntity = restTemplate.getForEntity(uri, Root.class);
		if (null != geoLocationEntity.getBody()) {
			geoLocation = geoLocationEntity.getBody();

		}
		return geoLocation;
	}
}
