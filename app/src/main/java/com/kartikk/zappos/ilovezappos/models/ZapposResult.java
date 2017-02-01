package com.kartikk.zappos.ilovezappos.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kartikk on 1/31/2017.
 */

public class ZapposResult implements Parcelable {

    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("thumbnailImageUrl")
    @Expose
    private String thumbnailImageUrl;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("originalPrice")
    @Expose
    private String originalPrice;
    @SerializedName("styleId")
    @Expose
    private String styleId;
    @SerializedName("colorId")
    @Expose
    private String colorId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("percentOff")
    @Expose
    private String percentOff;
    @SerializedName("productUrl")
    @Expose
    private String productUrl;
    @SerializedName("productName")
    @Expose
    private String productName;
    public final static Parcelable.Creator<ZapposResult> CREATOR = new Creator<ZapposResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ZapposResult createFromParcel(Parcel in) {
            ZapposResult instance = new ZapposResult();
            instance.brandName = ((String) in.readValue((String.class.getClassLoader())));
            instance.thumbnailImageUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.productId = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalPrice = ((String) in.readValue((String.class.getClassLoader())));
            instance.styleId = ((String) in.readValue((String.class.getClassLoader())));
            instance.colorId = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            instance.percentOff = ((String) in.readValue((String.class.getClassLoader())));
            instance.productUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.productName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ZapposResult[] newArray(int size) {
            return (new ZapposResult[size]);
        }

    };

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(brandName);
        dest.writeValue(thumbnailImageUrl);
        dest.writeValue(productId);
        dest.writeValue(originalPrice);
        dest.writeValue(styleId);
        dest.writeValue(colorId);
        dest.writeValue(price);
        dest.writeValue(percentOff);
        dest.writeValue(productUrl);
        dest.writeValue(productName);
    }

    public int describeContents() {
        return 0;
    }

}
