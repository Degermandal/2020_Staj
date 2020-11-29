
package com.example.internlogin.modelOfResponse.GetCustomerPortfolio;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortfolioBankDTO implements Serializable
{

    @SerializedName("toplamBakiye")
    @Expose
    private Double toplamBakiye;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("degerliMadenler")
    @Expose
    private List<DegerliMadenler> degerliMadenler = null;
    @SerializedName("nakit")
    @Expose
    private List<Nakit> nakit = null;
    @SerializedName("doviz")
    @Expose
    private List<Doviz> doviz = null;
    @SerializedName("hisse")
    @Expose
    private List<Hisse> hisse = null;
    private final static long serialVersionUID = -2542565401281979202L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PortfolioBankDTO() {
    }

    /**
     * 
     * @param toplamBakiye
     * @param degerliMadenler
     * @param nakit
     * @param doviz
     * @param bankName
     * @param hisse
     */
    public PortfolioBankDTO(Double toplamBakiye, String bankName, List<DegerliMadenler> degerliMadenler, List<Nakit> nakit, List<Doviz> doviz, List<Hisse> hisse) {
        super();
        this.toplamBakiye = toplamBakiye;
        this.bankName = bankName;
        this.degerliMadenler = degerliMadenler;
        this.nakit = nakit;
        this.doviz = doviz;
        this.hisse = hisse;
    }

    public Double getToplamBakiye() {
        return toplamBakiye;
    }

    public void setToplamBakiye(Double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<DegerliMadenler> getDegerliMadenler() {
        return degerliMadenler;
    }

    public void setDegerliMadenler(List<DegerliMadenler> degerliMadenler) {
        this.degerliMadenler = degerliMadenler;
    }

    public List<Nakit> getNakit() {
        return nakit;
    }

    public void setNakit(List<Nakit> nakit) {
        this.nakit = nakit;
    }

    public List<Doviz> getDoviz() {
        return doviz;
    }

    public void setDoviz(List<Doviz> doviz) {
        this.doviz = doviz;
    }

    public List<Hisse> getHisse() {
        return hisse;
    }

    public void setHisse(List<Hisse> hisse) {
        this.hisse = hisse;
    }

}
