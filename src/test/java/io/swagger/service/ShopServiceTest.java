package io.swagger.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.scry.poc.dto.ShopMngt;
import com.scry.poc.model.Shop;
import com.scry.poc.model.ShopAddress;
import com.scry.poc.repository.ShopRepository;
import com.scry.poc.service.ShopService;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

	@Mock
	private ShopRepository shopRepository;

	@InjectMocks
	private ShopService shopService;

	private Shop shop;

	private ShopMngt shopMnt;

	@BeforeEach
	public void setup() {
		shop = new Shop();
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber(new BigDecimal(111));
		shopAddress.setPostCode("506002");

		shop.setShopName("SHOPWARANGAL");
		shop.setShopAddress(shopAddress);

		shopMnt = new ShopMngt();
		shopMnt.setId(Long.valueOf(1));
		shopMnt.setShopName("SHOPName");
		shopMnt.setShopNumber(BigDecimal.valueOf(123));
		shopMnt.setShopPostcode("506002");
		shopMnt.setLatitude(16.395110);
		shopMnt.setLongitude(78.317024);
	}

	@Test
	public void getShopTest1() {
		
		given(shopRepository.findByLatLog(16.395110, 78.317024)).willReturn(shopMnt);

		Shop shop = shopService.getShop(16.395110, 78.317024);
		
		assertNotEquals(null, shop);
	}

	@Test
	public void getShopTest2() {
		
		given(shopRepository.findByLatLog(16.395110, 78.317024)).willReturn(null);

		Shop shop = shopService.getShop(16.0000, 78.000);
		
		assertEquals(null, shop);
	}
	
	@Test
	public void postShopTest1() {

		given(shopRepository.findByShopName(shop.getShopName())).willReturn(shopMnt);
		//given(shopRepository.saveAndFlush(shopMnt)).willReturn(shopMnt);

		String postStatuStr = shopService.postShop(shop);

		
		assertThat(postStatuStr).isIn("Updated");
	}
	
	@Test
	public void postShopTest2() {

		given(shopRepository.findByShopName(shop.getShopName())).willReturn(null);
		//given(shopRepository.saveAndFlush(shopMnt)).willReturn(shopMnt);

		String postStatuStr = shopService.postShop(shop);

		
		assertThat(postStatuStr).isIn("Added");
	}
}
