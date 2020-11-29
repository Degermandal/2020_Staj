package com.example.demo.DTO.marketResponse;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "kod",
        "ad",
        "tip"
})
public class Datum {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kod")
    private String kod;
    @JsonProperty("ad")
    private String ad;
    @JsonProperty("tip")
    private String tip;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("kod")
    public String getKod() {
        return kod;
    }

    @JsonProperty("kod")
    public void setKod(String kod) {
        this.kod = kod;
    }

    @JsonProperty("ad")
    public String getAd() {
        return ad;
    }

    @JsonProperty("ad")
    public void setAd(String ad) {
        this.ad = ad;
    }

    @JsonProperty("tip")
    public String getTip() {
        return tip;
    }

    @JsonProperty("tip")
    public void setTip(String tip) {
        this.tip = tip;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
