package com.scry.poc.model.geo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result{
    public ArrayList<AddressComponent> address_components;
    public String formatted_address;
    public Geometry geometry;
    public String place_id;
    public ArrayList<String> postcode_localities;
    public ArrayList<String> types;
}
