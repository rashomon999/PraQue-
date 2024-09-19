package model;

public class OtherCrop extends Crop{

    private OtherType otherType;

    public OtherCrop(String cropName,String cropType,int cropGrowDays,OtherType otherType){
        super(cropName,cropType,cropGrowDays);
        this.otherType = otherType;
    }

    public OtherType getOtherType() {
        return otherType;
    }

    public void setOtherType(OtherType otherType) {
        this.otherType = otherType;
    }

    public String toString() {
        return "Other Crop: " + super.toString() + "\nOther Type: " + otherType;
    }


}
