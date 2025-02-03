package com.example.realestate;

import org.locationtech.proj4j.*;

public class Test {

    public static void main(String[] args) {
        // Example UTM coordinates in Zone 12N
        double easting = 447480.4519172386;
        double northing = 4437423.661806457;

        double[] latLon = convertUTMToLatLon(easting, northing);
        System.out.println("Latitude: " + latLon[0]);
        System.out.println("Longitude: " + latLon[1]);
    }

    public static double[] convertUTMToLatLon(double easting, double northing) {
        CRSFactory crsFactory = new CRSFactory();
        // Use EPSG:26912 for NAD83 / UTM zone 12N
        CoordinateReferenceSystem utmCRS = crsFactory.createFromName("EPSG:26912");
        CoordinateReferenceSystem wgs84CRS = crsFactory.createFromName("EPSG:4326");

        CoordinateTransform transform = new BasicCoordinateTransform(utmCRS, wgs84CRS);
        ProjCoordinate utmCoord = new ProjCoordinate(easting, northing);
        ProjCoordinate latLonCoord = new ProjCoordinate();

        transform.transform(utmCoord, latLonCoord);

        return new double[]{latLonCoord.y, latLonCoord.x}; // [latitude, longitude]
    }

}