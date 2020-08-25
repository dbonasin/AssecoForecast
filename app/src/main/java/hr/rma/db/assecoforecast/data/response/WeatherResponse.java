package hr.rma.db.assecoforecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

