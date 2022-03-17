/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

import java.util.ArrayList;

/**
 *
 * @author Eduardo Santos
 */
public class DirectionsDTO {

    public class GeocodedWaypoint {

        public String geocoder_status;
        public String place_id;
        public ArrayList<String> types;
    }

    public class Northeast {

        public double lat;
        public double lng;
    }

    public class Southwest {

        public double lat;
        public double lng;
    }

    public class Bounds {

        public Northeast northeast;
        public Southwest southwest;
    }

    public class Distance {

        public String text;
        public int value;
    }

    public class Duration {

        public String text;
        public int value;
    }

    public class EndLocation {

        public double lat;
        public double lng;
    }

    public class StartLocation {

        public double lat;
        public double lng;
    }

    public class Polyline {

        public String points;
    }

    public class Step {

        public Distance distance;
        public Duration duration;
        public EndLocation end_location;
        public String html_instructions;
        public Polyline polyline;
        public StartLocation start_location;
        public String travel_mode;
        public String maneuver;
    }

    public class Leg {

        public Distance distance;
        public Duration duration;
        public String end_address;
        public EndLocation end_location;
        public String start_address;
        public StartLocation start_location;
        public ArrayList<Step> steps;
        public ArrayList<Object> traffic_speed_entry;
        public ArrayList<Object> via_waypoint;
    }

    public class OverviewPolyline {

        public String points;
    }

    public class Route {

        public Bounds bounds;
        public String copyrights;
        public ArrayList<Leg> legs;
        public OverviewPolyline overview_polyline;
        public String summary;
        public ArrayList<Object> warnings;
        public ArrayList<Object> waypoint_order;
    }

    public class Root {

        public ArrayList<GeocodedWaypoint> geocoded_waypoints;
        public ArrayList<Route> routes;
        public String status;
    }

}
