package hr.rma.db.assecoforecast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("timezone_offset")
    @Expose
    public Double timezoneOffset;
    @SerializedName("current")
    @Expose
    public Current current;
    @SerializedName("hourly")
    @Expose
    public List<Hourly> hourly = null;
    @SerializedName("daily")
    @Expose
    public List<Daily> daily = null;

}
class Current {

    @SerializedName("dt")
    @Expose
    public Double dt;
    @SerializedName("sunrise")
    @Expose
    public Double sunrise;
    @SerializedName("sunset")
    @Expose
    public Double sunset;
    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("feels_like")
    @Expose
    public Double feelsLike;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("dew_point")
    @Expose
    public Double dewPoint;
    @SerializedName("uvi")
    @Expose
    public Double uvi;
    @SerializedName("clouds")
    @Expose
    public Double clouds;
    @SerializedName("visibility")
    @Expose
    public Double visibility;
    @SerializedName("wind_speed")
    @Expose
    public Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    public Double windDeg;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("rain")
    @Expose
    public Rain rain;

}

class Daily {

    @SerializedName("dt")
    @Expose
    public Double dt;
    @SerializedName("sunrise")
    @Expose
    public Double sunrise;
    @SerializedName("sunset")
    @Expose
    public Double sunset;
    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("feels_like")
    @Expose
    public FeelsLike feelsLike;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("dew_point")
    @Expose
    public Double dewPoint;
    @SerializedName("wind_speed")
    @Expose
    public Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    public Double windDeg;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Double clouds;
    @SerializedName("pop")
    @Expose
    public Double pop;
    @SerializedName("rain")
    @Expose
    public Double rain;
    @SerializedName("uvi")
    @Expose
    public Double uvi;

}

class FeelsLike {

    @SerializedName("day")
    @Expose
    public Double day;
    @SerializedName("night")
    @Expose
    public Double night;
    @SerializedName("eve")
    @Expose
    public Double eve;
    @SerializedName("morn")
    @Expose
    public Double morn;

}

class Hourly {

    @SerializedName("dt")
    @Expose
    public Double dt;
    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("feels_like")
    @Expose
    public Double feelsLike;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("dew_point")
    @Expose
    public Double dewPoint;
    @SerializedName("clouds")
    @Expose
    public Double clouds;
    @SerializedName("visibility")
    @Expose
    public Double visibility;
    @SerializedName("wind_speed")
    @Expose
    public Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    public Double windDeg;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("pop")
    @Expose
    public Double pop;
    @SerializedName("rain")
    @Expose
    public Rain rain;

}

class Rain {

    @SerializedName("1h")
    @Expose
    public Double _1h;

}

class Temp {

    @SerializedName("day")
    @Expose
    public Double day;
    @SerializedName("min")
    @Expose
    public Double min;
    @SerializedName("max")
    @Expose
    public Double max;
    @SerializedName("night")
    @Expose
    public Double night;
    @SerializedName("eve")
    @Expose
    public Double eve;
    @SerializedName("morn")
    @Expose
    public Double morn;

}

class Weather {

    @SerializedName("id")
    @Expose
    public Double id;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;

}

