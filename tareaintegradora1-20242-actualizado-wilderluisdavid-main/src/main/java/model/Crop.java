package model;

public abstract class Crop {

    private String cropName;
    private String cropType;
    private int cropGrowthDays;

    public Crop(String cropName, String cropType, int cropGrowthDays) {
        if (cropGrowthDays <= 28) {
            this.cropName = cropName;
            this.cropType = cropType;
            this.cropGrowthDays = cropGrowthDays;
        } else {
            throw new IllegalArgumentException("Los días de crecimiento no pueden superar los 28 días.");
        }
    }

    public String getCropName() {
        return cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public int getCropGrowthDays() {
        return cropGrowthDays;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public void setCropGrowthDays(int cropGrowthDays) {
        this.cropGrowthDays = cropGrowthDays;
    }

    public String toString(){
        return "Crop Name: " + cropName + "\nCrop Type: " + cropType + "Crop Grow Days: "+cropGrowthDays;
    }
}