package models;

import java.util.List;

public class FamilyPlants {
    private int id;
	private String name;
    private FloweringTime floweringTime;
	private List<Plant> listPlants;

	public FamilyPlants(){}

	public FamilyPlants(String name, FloweringTime floweringTime, List<Plant> listPlants){
	    this.name = name;
	    this.floweringTime = floweringTime;
	    this.listPlants = listPlants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FloweringTime getFloweringTime() {
        return floweringTime;
    }

    public void setFloweringTime(FloweringTime floweringTime) {
        this.floweringTime = floweringTime;
    }

    public List<Plant> getListPlants() {
        return listPlants;
    }

    public void setListPlants(List<Plant> listPlants) {
        this.listPlants = listPlants;
    }
}
