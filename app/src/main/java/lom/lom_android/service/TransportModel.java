package lom.lom_android.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Formatter;

public class TransportModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("tonn")
    @Expose
    private String tonn;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTonn() {
        return tonn;
    }

    public void setTonn(String tonn) {
        this.tonn = tonn;
    }

    public String toString() {
        Formatter f = new Formatter();

        return f.format("%s (до %s тонн) - %s руб/км", name, tonn.substring(0, tonn.length() - 3),
                price.substring(0, tonn.length() - 3)).toString();
    }

}