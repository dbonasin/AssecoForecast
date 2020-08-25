package hr.rma.db.assecoforecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Current {

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
