package hr.rma.db.assecoforecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {

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
