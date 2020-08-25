package hr.rma.db.assecoforecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

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
