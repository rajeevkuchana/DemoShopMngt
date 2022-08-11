package com.scry.poc.model.geo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bounds{
    public Northeast northeast;
    public Southwest southwest;
}
