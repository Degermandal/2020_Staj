
package com.example.internlogin.modelOfResponse.GetLogin;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUser implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("identityId")
    @Expose
    private String identityId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    private final static long serialVersionUID = 3887851577452390410L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetUser() {
    }

    /**
     * 
     * @param password
     * @param identityId
     * @param surname
     * @param name
     * @param status
     */
    public GetUser(Boolean status, String identityId, String password, String name, String surname) {
        super();
        this.status = status;
        this.identityId = identityId;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
