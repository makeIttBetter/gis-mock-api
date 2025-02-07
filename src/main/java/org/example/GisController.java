package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gis")
public class GisController {

    @GetMapping("/mock-dots")
    public Map<String, Object> getMockDots() {
        // (Existing endpoint that returns point features)
        Map<String, Object> geoJson = new HashMap<>();
        geoJson.put("type", "FeatureCollection");

        List<Map<String, Object>> features = List.of(
                createFeature(-111.6706, 40.2969), // Example point in Orem, Utah
                createFeature(-111.685, 40.3047),
                createFeature(-111.66, 40.3124)
        );

        geoJson.put("features", features);
        return geoJson;
    }

    private Map<String, Object> createFeature(double longitude, double latitude) {
        Map<String, Object> feature = new HashMap<>();
        feature.put("type", "Feature");

        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type", "Point");
        geometry.put("coordinates", List.of(longitude, latitude));

        feature.put("geometry", geometry);

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Mock Point");

        feature.put("properties", properties);
        return feature;
    }

    // New endpoint that returns a polygon instead of dots
    @GetMapping("/mock-polygon")
    public Map<String, Object> getMockPolygon() {
        Map<String, Object> geoJson = new HashMap<>();
        // For a single feature, you can return a Feature object directly
        geoJson.put("type", "Feature");

        // Define a polygon geometry (a rectangle in this example)
        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type", "Polygon");
        // A polygon's coordinates must be an array of linear rings. Here we define one ring.
        List<List<List<Double>>> coordinates = List.of(
                List.of(
                        List.of(-111.70, 40.30),
                        List.of(-111.68, 40.30),
                        List.of(-111.68, 40.32),
                        List.of(-111.70, 40.32),
                        List.of(-111.70, 40.30)  // Closing the ring (first and last points are the same)
                )
        );
        geometry.put("coordinates", coordinates);
        geoJson.put("geometry", geometry);

        // Add some properties for display purposes
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Mock Polygon");
        geoJson.put("properties", properties);

        return geoJson;
    }
}

