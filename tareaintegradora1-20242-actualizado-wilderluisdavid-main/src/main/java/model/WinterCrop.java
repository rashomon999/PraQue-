package model;

public class WinterCrop extends Crop{

    private WinterType winterType;

    public WinterCrop(String cropName,String cropType,int cropGrowDays,WinterType winterType){
        super(cropName, cropType, cropGrowDays);
        this.winterType = winterType;
    }

    public WinterType getWinterType(){
        return winterType;
    }

    public void setWinterType(WinterType winterType){
        this.winterType = winterType;
    }

    public String toString(){
        return "Winter Crop: " +super.toString() + "Winter Type: "+ winterType;
    }


}
