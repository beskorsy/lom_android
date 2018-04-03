package lom.lom_android.service;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalityModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distanceBelogorsk")
    @Expose
    private Integer distanceBelogorsk;
    @SerializedName("distanceSkovorodino")
    @Expose
    private Integer distanceSkovorodino;
    @SerializedName("distanceTygda")
    @Expose
    private Integer distanceTygda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistanceBelogorsk() {
        return distanceBelogorsk;
    }

    public void setDistanceBelogorsk(Integer distanceBelogorsk) {
        this.distanceBelogorsk = distanceBelogorsk;
    }

    public Integer getDistanceSkovorodino() {
        return distanceSkovorodino;
    }

    public void setDistanceSkovorodino(Integer distanceSkovorodino) {
        this.distanceSkovorodino = distanceSkovorodino;
    }

    public Integer getDistanceTygda() {
        return distanceTygda;
    }

    public void setDistanceTygda(Integer distanceTygda) {
        this.distanceTygda = distanceTygda;
    }

    public String toString() {
        return name;
    }

}