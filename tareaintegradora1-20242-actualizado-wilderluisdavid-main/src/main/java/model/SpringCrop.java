package model;

public class SpringCrop extends Crop {
    private SpringType springType;

    public SpringCrop(String cropName,String cropType,int cropGrowDays, SpringType springType) {
        super(cropName,cropType,cropGrowDays); 
        this.springType = springType;
    }

    public SpringType getSpringType() {
        return springType;
    }

    public void setSpringType(SpringType springType){
        this.springType = springType;
    }
    
    public String toString() {
        return "Spring Crop: "+ super.toString() + "\nSpring Type: " + springType;
    }
}