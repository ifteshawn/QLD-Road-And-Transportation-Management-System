
package Presenter;

import Model.Vehicle;
import javafx.collections.ObservableList;


interface IPersistVehicle {
    
    public void registerVehicle(Vehicle vehicle);
    public void updateVehicle(Vehicle vehicle);
    public void deleteVehicle(String licenseNumber);
    public ObservableList<Vehicle> readVehicle();
}
