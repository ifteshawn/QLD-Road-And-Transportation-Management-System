
package Model;


public class DriverLicense {
   
    private String fName,mName,lName,gender,contactNo,dob,address,licenseType,expiryDate,licenseNumber;

    public DriverLicense() {}

    public DriverLicense(String fName, String mName, String lName, String gender, String contactNo, String dob, String address, String licenseType, String expiryDate, String licenseNumber) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.gender = gender;
        this.contactNo = contactNo;
        this.dob = dob;
        this.address = address;
        this.licenseType = licenseType;
        this.expiryDate = expiryDate;
        this.licenseNumber = licenseNumber;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "DriverLicense{" + "fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", gender=" + gender + ", contactNo=" + contactNo + ", dob=" + dob + ", address=" + address + ", licenseType=" + licenseType + ", expiryDate=" + expiryDate + ", licenseNumber=" + licenseNumber + '}';
    }
    
    
}
