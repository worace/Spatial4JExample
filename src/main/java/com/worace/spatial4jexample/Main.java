package com.worace.spatial4jexample;

import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.geom.Geometry;
import org.locationtech.spatial4j.context.jts.DatelineRule;
import org.locationtech.spatial4j.context.jts.JtsSpatialContext;
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory;
import org.locationtech.spatial4j.shape.jts.JtsGeometry;

/**
 * Created by horace on 8/3/17.
 */
public class Main {
  public static void main(String[] args) throws Exception {
    String wkt = "POLYGON((179 0, 179 1, -179 1, -179 0, 179 0))";
    Geometry geom = new WKTReader().read(wkt);

    JtsSpatialContextFactory factory = new JtsSpatialContextFactory();
    factory.datelineRule = DatelineRule.width180;
    JtsSpatialContext ctx = factory.newSpatialContext();

    JtsGeometry shp = ctx.makeShape(geom);
    System.out.println("*** Shape 1 Bounding Box Width (should be 2): ***");
    System.out.println(shp.getBoundingBox().getWidth());

    JtsGeometry shp2 = ctx.makeShape(geom);
    System.out.println("*** Shape 2 Bounding Box Width (also expect 2): ***");
    System.out.println(shp2.getBoundingBox().getWidth());


    JtsGeometry shp3 = ctx.makeShape((Geometry) geom.clone());
    System.out.println("*** Shape 3 (Cloned) Bounding Box Width (also expect 2): ***");
    System.out.println(shp3.getBoundingBox().getWidth());


  }
}
