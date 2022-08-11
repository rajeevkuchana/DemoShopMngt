package com.scry.poc.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.scry.poc.model.AddStatus;
import com.scry.poc.model.Shop;
import com.scry.poc.service.ShopService;

import io.swagger.annotations.ApiParam;

/**
 * This Class is used as a Controller for the functionality for Managing Shops.
 * 
 * @author Rajeev Kuchana
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-08-09T11:03:31.628Z")

@Controller
public class ShopsApiController implements ShopsApi {

	private static final Logger log = LoggerFactory.getLogger(ShopsApiController.class);

	@Autowired
	private ShopService shopService;

	/**
	 * This Method is used to save Shop Details
	 *
	 */
	public ResponseEntity<AddStatus> addShop(
			@ApiParam(value = "Shop object that needs to be added to the shops", required = true) @Valid @RequestBody Shop body) {
		AddStatus addStatus = new AddStatus();
		try {
			String addStatusStr = shopService.postShop(body);
			addStatus.setStatus(addStatusStr);
		} catch (Exception e) {
			log.error("Error Occurred while adding Shop Details", e);
			return new ResponseEntity<AddStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AddStatus>(addStatus, HttpStatus.OK);
	}

	/**
	 * This Method is used find the nearest Shop for a given Latitude and Longitude
	 *
	 */
	public ResponseEntity<Shop> findShops(String longitude, String lattitude) {
		Shop shop = null;

		try {
			shop = shopService.getShop(Double.valueOf(lattitude), Double.valueOf(longitude));
		} catch (Exception e) {
			log.error("Error Occurred while fetching Shop Details", e);
			return new ResponseEntity<Shop>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Shop>(shop, HttpStatus.OK);
	}

}
