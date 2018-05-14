package models;

import java.util.ArrayList;
import java.util.List;

public class ReferenceBook {

    private List<FamilyPlants> listFamilyPlants = new ArrayList<>();

    public List<FamilyPlants> getList() {
        return listFamilyPlants;
    }

    public void setList(List<FamilyPlants> list) {
        listFamilyPlants = list;
    }
}
