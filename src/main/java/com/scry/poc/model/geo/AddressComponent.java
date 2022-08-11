package com.scry.poc.model.geo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressComponent{
    public String long_name;
    public String short_name;
    public ArrayList<String> types;
}
