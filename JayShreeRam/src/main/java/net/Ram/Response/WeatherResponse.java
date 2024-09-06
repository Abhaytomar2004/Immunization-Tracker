package net.Ram.Response;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WeatherResponse {
	private Weather weather ; 
	
	public class Clouds{
	    private int all;
	}

	public class Coord{
	    private double lon;
	    private double lat;
	}
	@Getter
	@Setter
	public class Main{
	    private double temp;
	    private double feels_like;
	    private double temp_min;
	    private double temp_max;
	    private int pressure;
	    private int humidity;
	    private int sea_level;
	    private int grnd_level;
	}

	public class Root{
	    private Coord coord;
	    private ArrayList<Weather> weather;
	    private String base;
	    private Main main;
	    private int visibility;
	    private Wind wind;
	    private Clouds clouds;
	    private int dt;
	    private Sys sys;
	    private int timezone;
	    private int id;
	    private String name;
	    private int cod;
	}

	public class Sys{
	    private int type;
	    private int id;
	    private String country;
	    private int sunrise;
	    private int sunset;
	}
	@Getter
	@Setter
	public class Weather{
	    private int id;
	    private String main;
	    private String description;
	    private String icon;
	}

	public class Wind{
	    private double speed;
	    private int deg;
	}



}
