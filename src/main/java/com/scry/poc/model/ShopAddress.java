package com.scry.poc.model;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ShopAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-08-09T11:03:31.628Z")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddress   {
  @JsonProperty("number")
  private BigDecimal number = null;

  @JsonProperty("postCode")
  private String postCode = null;

  
}

