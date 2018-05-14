package ludapivovarevich.controller;

import com.doszhan.CustomServiceStub;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private final static String END_POINT = "http://localhost:8080/axis2/services/CustomService?wsdl";

    public List<CustomServiceStub.FamilyPlants> getListFamilyPlants() throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.GetListFamilyPlants listFamilyPlants= new CustomServiceStub.GetListFamilyPlants();
        CustomServiceStub.GetListFamilyPlantsResponse listFamilyResponse = stub.getListFamilyPlants(listFamilyPlants);
        CustomServiceStub.FamilyPlants[] familyPlants = listFamilyResponse.get_return();
        if(familyPlants != null){
            return Arrays.asList(familyPlants);
        }
        return null;
    }

    public void addFamilyPlants(CustomServiceStub.FamilyPlants familyPlants)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.AddFamilyPlants addFamilyPlants = new CustomServiceStub.AddFamilyPlants();
        addFamilyPlants.setFamilyPlants(familyPlants);
        stub.addFamilyPlants(addFamilyPlants);
    }

    public void deleteFamilyPlants(int index)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.DeleteFamilyPlants deleteFamilyPlants = new CustomServiceStub.DeleteFamilyPlants();
        deleteFamilyPlants.setIndex(index);
        stub.deleteFamilyPlants(deleteFamilyPlants);
    }

    public void changeFamilyPlants(CustomServiceStub.FamilyPlants familyPlants) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.ChangeFamilyPlants changeFamilyPlants = new CustomServiceStub.ChangeFamilyPlants();
        changeFamilyPlants.setFamilyPlants(familyPlants);
        stub.changeFamilyPlants(changeFamilyPlants);
    }

    public List<CustomServiceStub.Plant> getListPlants(int index) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.GetListPlants listPlants = new CustomServiceStub.GetListPlants();
        listPlants.setIndex(index);
        CustomServiceStub.GetListPlantsResponse allThemesResponse = stub.getListPlants(listPlants);
        CustomServiceStub.Plant[] plants = allThemesResponse.get_return();
        if(plants != null){
            return Arrays.asList(plants);
        }

        return null;
    }

    public void addPlant(int index, CustomServiceStub.Plant plant)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.AddPlant addPlant = new CustomServiceStub.AddPlant();
        addPlant.setIndexFamily(index);
        addPlant.setPlant(plant);
        stub.addPlant(addPlant);
    }

    public void deletePlant(int indexFamily, int index)throws RemoteException{
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.DeletePlant deletePlant = new CustomServiceStub.DeletePlant();
        deletePlant.setIndexFamily(indexFamily);
        deletePlant.setIndex(index);
        stub.deletePlant(deletePlant);
    }

    public void changePlant(int indexFamily, CustomServiceStub.Plant plant) throws RemoteException {
        CustomServiceStub stub = new CustomServiceStub(END_POINT);
        CustomServiceStub.ChangePlant changePlant = new CustomServiceStub.ChangePlant();
        changePlant.setIndexFamily(indexFamily);
        changePlant.setPlant(plant);
        stub.changePlant(changePlant);
    }
}
