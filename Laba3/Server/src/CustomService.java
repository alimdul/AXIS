import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.FamilyPlants;
import models.Plant;
import models.ReferenceBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomService {
    private final static String FILE_NAME = "E:\\aipos_laba2_thrift\\fileWithData\\data.json";
    private ReferenceBook referenceBook = new ReferenceBook();

    public List<FamilyPlants> getListFamilyPlants(){
        referenceBook.getList().clear();

        Gson gson = new Gson();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            ReferenceBook rf = gson.fromJson(reader, ReferenceBook.class);

            if(rf != null){
                int i = 0;
                for(FamilyPlants familyPlants: rf.getList()){
                    familyPlants.setId(++i);
                    referenceBook.getList().add(familyPlants);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return referenceBook.getList();
    }

    public void addFamilyPlants(FamilyPlants familyPlants){
        familyPlants.setListPlants(new ArrayList<>());
        referenceBook.getList().add(familyPlants);
        saveChanging();
    }

    public void deleteFamilyPlants(int index){
        referenceBook.getList().remove(index);
        saveChanging();
    }

    public void changeFamilyPlants(FamilyPlants familyPlants){
        int index = familyPlants.getId();
        List<Plant> listThemes = referenceBook.getList().get(index).getListPlants();
        referenceBook.getList().remove(index);
        familyPlants.setListPlants(listThemes);
        referenceBook.getList().add(index, familyPlants);
        saveChanging();
    }

    public List<Plant> getListPlants(int index){
        for(int i = 0; i < referenceBook.getList().get(index).getListPlants().size(); i++){
            referenceBook.getList().get(index).getListPlants().get(i).setId(i+1);
        }
        return referenceBook.getList().get(index).getListPlants();
    }

    public void addPlant(int indexFamily, Plant plant){
        referenceBook.getList().get(indexFamily).getListPlants().add(plant);
        saveChanging();
    }

    public void deletePlant(int indexFamily, int index){
        referenceBook.getList().get(indexFamily).getListPlants().remove(index);
        saveChanging();
    }

    public void changePlant(int indexFamily, Plant plant){
        int index = plant.getId();
        referenceBook.getList().get(indexFamily).getListPlants().remove(index);
        referenceBook.getList().get(indexFamily).getListPlants().add(index, plant);
        saveChanging();
    }

    public void saveChanging(){

        ReferenceBook rb = new ReferenceBook();
        rb.setList(referenceBook.getList());

        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        String json = gs.toJson(rb);

        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_NAME);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
