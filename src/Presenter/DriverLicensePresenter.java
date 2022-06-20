
package Presenter;

import Model.DriverLicense;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;


public class DriverLicensePresenter {
    private ObservableList<DriverLicense> licenses;
    PersisterDriverLicense persister;


    public DriverLicensePresenter() {
        licenses = FXCollections.observableArrayList();
        persister = new PersisterDriverLicense();
    }


    public void registerDriverLicense(DriverLicense license) {
        persister.registerDriverLicense(license);
    }
    
    //selection method
    public ObservableList<DriverLicense> selectDriverLicense() {
        licenses = persister.readDriverLicense();
        return licenses;
    }

    //deletion method
    public void deleteDriverLicense(String license) {
        persister.deleteDriverLicense(license);
    }

    //update method
    public void updateDriverLicense(DriverLicense license) {
        persister.updateDriverLicense(license);
    }
    
    
}
