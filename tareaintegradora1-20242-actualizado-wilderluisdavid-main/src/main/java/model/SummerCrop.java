package model;

public class SummerCrop extends Crop {

    private SummerType summerType;

    public SummerCrop(String cropName, String cropType, int cropGrowDays, SummerType summerType) {
        super(cropName, cropType, cropGrowDays);
        this.summerType = summerType;
    }

    public SummerType getSummerType() {
        return summerType;
    }

    public void setSummerType(SummerType summerType) {
        this.summerType = summerType;
    }

    public String toString() {
        return "Summer Crop: " + super.toString() + "\nSummer Type: " + summerType;
    }
}