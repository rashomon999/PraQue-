package model;

public class FallCrop extends Crop{

    private FallType fallType;

    public FallCrop(String cropName,String cropType,int cropGrowDays,FallType fallType){
        super(cropName, cropType, cropGrowDays);
        this.fallType = fallType;
    }

    public FallType getFallType(){
        return fallType;
    }

    public void setFallType(FallType fallType){
        this.fallType = fallType;
    }

    public String toString(){
        return "Fall Crop: "+super.toString() + "Fall Type: "+ fallType;
    }

}
