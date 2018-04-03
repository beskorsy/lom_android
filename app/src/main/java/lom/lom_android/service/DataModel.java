package lom.lom_android.service;

import java.util.Comparator;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("loader")
    @Expose
    private Boolean loader;
    @SerializedName("cutter")
    @Expose
    private Boolean cutter;
    @SerializedName("calculatedInPlace")
    @Expose
    private Boolean calculatedInPlace;
    @SerializedName("transports")
    @Expose
    private List<TransportModel> transports = null;
    @SerializedName("scrapyards")
    @Expose
    private List<ScrapyardModel> scrapyards = null;
    @SerializedName("localitys")
    @Expose
    private List<LocalityModel> localitys = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLoader() {
        return loader;
    }

    public void setLoader(Boolean loader) {
        this.loader = loader;
    }

    public Boolean getCutter() {
        return cutter;
    }

    public void setCutter(Boolean cutter) {
        this.cutter = cutter;
    }

    public Boolean getCalculatedInPlace() {
        return calculatedInPlace;
    }

    public void setCalculatedInPlace(Boolean calculatedInPlace) {
        this.calculatedInPlace = calculatedInPlace;
    }

    public List<TransportModel> getTransports() {
        return transports;
    }

    public void setTransports(List<TransportModel> transports) {
        this.transports = transports;
    }

    public List<ScrapyardModel> getScrapyards() {
        return scrapyards;
    }

    public void setScrapyards(List<ScrapyardModel> scrapyards) {
        this.scrapyards = scrapyards;
    }

    public List<LocalityModel> getLocalitys() {
        Comparator<LocalityModel> cmp = new Comparator<LocalityModel>() {
            @Override
            public int compare(LocalityModel o1, LocalityModel o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        java.util.Collections.sort(localitys, cmp);
        return localitys;
    }

    public void setLocalitys(List<LocalityModel> localitys) {
        this.localitys = localitys;
    }

}

