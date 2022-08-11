package com.scry.poc.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Shop
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-08-09T11:03:31.628Z")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop   {
	
  @JsonProperty("shopName")
  private String shopName = null;

  @JsonProperty("shopAddress")
  private ShopAddress shopAddress = null;

  
}

