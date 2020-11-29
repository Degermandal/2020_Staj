package com.example.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Asset {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assetdescription_id" , referencedColumnName = "id")
    private AssetDescription assetDescription; // varlığın tipi

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetDescription getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(AssetDescription assetDescription) {
        this.assetDescription = assetDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(assetDescription, asset.assetDescription) &&
                Objects.equals(id, asset.id) &&
                Objects.equals(name, asset.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetDescription, id, name);
    }
}
