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
        "hisseYuzeysel",
        "stc_5_3",
        "rsI14",
        "mov10",
        "ccI14"
})
public class MarketDTO {

    @JsonProperty("hisseYuzeysel")
    private HisseYuzeysel hisseYuzeysel;
    @JsonProperty("stc_5_3")
    private Object stc53;
    @JsonProperty("rsI14")
    private Object rsI14;
    @JsonProperty("mov10")
    private Object mov10;
    @JsonProperty("ccI14")
    private Object ccI14;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hisseYuzeysel")
    public HisseYuzeysel getHisseYuzeysel() {
        return hisseYuzeysel;
    }

    @JsonProperty("hisseYuzeysel")
    public void setHisseYuzeysel(HisseYuzeysel hisseYuzeysel) {
        this.hisseYuzeysel = hisseYuzeysel;
    }

    @JsonProperty("stc_5_3")
    public Object getStc53() {
        return stc53;
    }

    @JsonProperty("stc_5_3")
    public void setStc53(Object stc53) {
        this.stc53 = stc53;
    }

    @JsonProperty("rsI14")
    public Object getRsI14() {
        return rsI14;
    }

    @JsonProperty("rsI14")
    public void setRsI14(Object rsI14) {
        this.rsI14 = rsI14;
    }

    @JsonProperty("mov10")
    public Object getMov10() {
        return mov10;
    }

    @JsonProperty("mov10")
    public void setMov10(Object mov10) {
        this.mov10 = mov10;
    }

    @JsonProperty("ccI14")
    public Object getCcI14() {
        return ccI14;
    }

    @JsonProperty("ccI14")
    public void setCcI14(Object ccI14) {
        this.ccI14 = ccI14;
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
