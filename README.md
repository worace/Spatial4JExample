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
