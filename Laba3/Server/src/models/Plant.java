package models;

import java.util.List;

public class Plant {

    private int id;
    private String plant;
    private String leaf;
    private String stem;

    public Plant(){}

    public Plant(String plant, String leaf, String stem){
        this.plant = plant;
        this.leaf = leaf;
        this.stem = stem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }
}
