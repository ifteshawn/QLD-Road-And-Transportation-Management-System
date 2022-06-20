
package Presenter;

import Model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehiclePresenter {
    
//    variable declaration
    private ObservableList<Vehicle> vehicles;
    PersisterVehicle persister;

    //constructor
    public VehiclePresenter() {
        vehicles = FXCollections.observableArrayList();
        persister = new PersisterVehicle();
    }

    public void registerVehicle(Vehicle vehicle){
        persister.registerVehicle(vehicle);
    }
    
    //selection method
    public ObservableList<Vehicle> selectVehicle() {
        vehicles = persister.readVehicle();
        return vehicles;
    }

    //deletion method
    public void deleteVehicle(String licenseNumber) {
        persister.deleteVehicle(licenseNumber);
    }

    //update method
    public void updateVehicle(Vehicle vehicle) {
        persister.updateVehicle(vehicle);
    }
}
