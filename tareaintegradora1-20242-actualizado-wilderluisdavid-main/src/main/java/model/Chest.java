package model;

import structures.CustomLinkedList;

public class Chest {

    private CustomLinkedList<Space> spaces;
    private final int maxSpaces = 50;
    private int chestLocation;
    private ChestLabel chestLabel;

    public Chest(int chestLocation, ChestLabel chestLabel) {
        spaces = new CustomLinkedList<>();
        this.chestLocation = chestLocation;
        this.chestLabel = chestLabel;
    }

    public boolean addCrop(Crop crop, int quantity) {
        for (Space space : spaces) {
            if (space.isEmpty()) {
                space.setCrop(crop, quantity);
                return true;
            } else if (space.getCrop().getCropName().equals(crop.getCropName())) {
                if (space.getQuantity() + quantity <= 25) {
                    space.incrementStackSize(quantity);
                    return true;
                } else {
                    System.out.println("Cantidad excede el límite máximo de 25 stacks por espacio.");
                    return false;
                }
            }
        }
        System.out.println("No hay espacio disponible para agregar el cultivo.");
        return false;
    }

    public int getOccupiedSpaces() {
        return spaces.getSize();
    }

    public CustomLinkedList<Space> getSpaces() {
        return spaces;
    }

    public int getChestLocation() {
        return chestLocation;
    }

    public ChestLabel getChestLabel() {
        return chestLabel;
    }


    public void setChestLocation(int chestLocation) {
        this.chestLocation = chestLocation;
    }

    public void setChestLabel(ChestLabel chestLabel) {
        this.chestLabel = chestLabel;
    }

    public boolean removeCrop(String cropName) {
        for (Space space : spaces) {
            if (space.getCrop().getCropName().equals(cropName)) {
                spaces.remove(space);
                return true;
            }
        }
        return false;
    }

    public void clearChest() {
        spaces.clear();
    }

    public boolean isFull() {
        return spaces.getSize() >= maxSpaces;
    }

    public boolean isEmpty() {
        return spaces.isEmpty();
    }

    public void displayChestContents() {
        if (spaces.isEmpty()) {
            System.out.println("El cofre está vacío.");
        } else {
            for (Space space : spaces) {
                System.out.println("Cultivo: " + space.getCrop().getCropName() +
                        " | Tipo: " + space.getCrop().getCropType() +
                        " | Días de crecimiento: " + space.getCrop().getCropGrowthDays() +
                        " | Cantidad de stacks: " + space.getQuantity());
            }
        }
    }
}
