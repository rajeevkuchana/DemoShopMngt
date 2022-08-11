package com.scry.poc.model.geo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry{
    public Bounds bounds;
    public Location location;
    public String location_type;
    public Viewport viewport;
}
