package com.scry.poc.model.geo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root{
    public ArrayList<Result> results;
    public String status;
}
