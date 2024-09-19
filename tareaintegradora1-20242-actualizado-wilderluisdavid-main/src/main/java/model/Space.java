package model;

public class Space {

    private Crop crop;
    private int quantity;

    public Space() {
        this.crop = null;
        this.quantity = 0;
    }

    public Space(Crop crop, int quantity) {
        if (quantity <= 25) {
            this.crop = crop;
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Máximo 25 stacks por espacio.");
        }
    }

    public boolean isEmpty() {
        return crop == null;
    }

    public Crop getCrop() {
        return crop;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCrop(Crop crop, int quantity) {
        if (quantity <= 25) {
            this.crop = crop;
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Máximo 25 stacks por espacio.");
        }
    }

    public void incrementStackSize(int quantity) {
        if (this.quantity + quantity <= 25) {
            this.quantity += quantity;
        } else {
            throw new IllegalArgumentException("Cantidad excede el máximo permitido (25).");
        }
    }

    public void clearSpace() {
        this.crop = null;
        this.quantity = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Espacio vacío";
        } else {
            return "Cultivo: " + crop.getCropName() + " | Tipo: " + crop.getCropType() + " | Cantidad: " + quantity;
        }
    }


}
