
package Presenter;

import Model.DriverLicense;
import javafx.collections.ObservableList;


interface IPersistDriverLicense {
   
    public void registerDriverLicense(DriverLicense vehicle);
    public void updateDriverLicense(DriverLicense vehicle);
    public void deleteDriverLicense(String licenseNumber);
    public ObservableList<DriverLicense> readDriverLicense();
        
}
