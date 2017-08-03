# Spatial4j Example Project

Small example to demonstrate an issue I've run into with converting dateline-crossing JTS geometries into Spatial4J shapes. Specifically the conversion seems to only include the dateline adjustments the first time it's run.

### Run the Example

```
mvn exec:java -Dexec.mainClass="com.worace.spatial4jexample.Main"
```

**Outputs**

```
*** Shape 1 Bounding Box Width (should be 2): ***
2.0
*** Shape 2 Bounding Box Width (also expect 2): ***
358.0
*** Shape 3 (Cloned) Bounding Box Width (also expect 2): ***
358.0
```

### Example at a Glance

```java
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
```
