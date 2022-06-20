package Model;

public class Vehicle {

    private String plateNum,make,type,year,color,engineNum,licenseNumber;

    public Vehicle() {}

    public Vehicle(String plateNum, String make, String type, String year, String color, String engineNum, String licenseNumber) {
        this.plateNum = plateNum;
        this.make = make;
        this.type = type;
        this.year = year;
        this.color = color;
        this.engineNum = engineNum;
        this.licenseNumber = licenseNumber;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    @Override
    public String toString() {
        return "Vehicle{" + "plateNum=" + plateNum + ", make=" + make + ", type=" + type + ", year=" + year + ", color=" + color + ", engineNum=" + engineNum + ", licenseNumber=" + licenseNumber + '}';
    }
    
    
}
