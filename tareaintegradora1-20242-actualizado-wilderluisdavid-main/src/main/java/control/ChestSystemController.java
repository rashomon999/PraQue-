package control;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Chest;
import model.ChestLabel;
import model.Crop;
import model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import structures.CustomLinkedList;
import structures.Node;

public class ChestSystemController {

    public ChestSystemController() {
        this.chests = new CustomLinkedList<>();
        //cambio
        this.crops = new CustomLinkedList<>();
        availableCrops();
    }

    private CustomLinkedList<Chest> chests;
    //cambio
    private CustomLinkedList<Crop> crops;

    // Método para agregar un nuevo cofre a la lista de cofres
    public void addChest(int chestLocation, ChestLabel chestLabel) {
        Chest newChest = new Chest(chestLocation, chestLabel);
        chests.add(newChest);
        System.out.println("Cofre añadido: " + chestLabel);
    }

    /** Description: Method to save a Crop Object to an specific Chest.
     *
     * @param chestLocation
     * @param crop
     * @param quantity
     */
    /**public void addCropToChest(int chestLocation, Crop crop, int quantity) {
        for (Chest chest : chests) {
            if (chest.getChestLocation() == chestLocation) {
                if (chest.addCrop(crop, quantity)) {
                    System.out.println(quantity + " unidades de " + crop.getCropName() + " añadidas al cofre en la ubicación: " + chestLocation);
                } else {
                    System.out.println("El cofre está lleno o el cultivo no puede ser añadido.");
                }
                return;
            }
        }
        System.out.println("Cofre no encontrado en la ubicación: " + chestLocation);
    }*/
    public String addCropToChest(int chestLocation, String cropName, int cropType, int growthDays, int quantity) {
        Crop crop = findCropByName(cropName, cropType, growthDays);  // Método que debe encontrar el cultivo según el nombre, tipo y días
        if (crop == null) {
            return "Cultivo no encontrado: " + cropName;
        }

        for (Chest chest : chestsToList()) {
            if (chest.getChestLocation() == chestLocation) {
                if (chest.addCrop(crop, quantity)) {
                    return quantity + " unidades de " + crop.getCropName() + " añadidas al cofre en la ubicación: " + chestLocation;
                } else {
                    return "El cofre está lleno o el cultivo no puede ser añadido.";
                }
            }
        }
        return "Cofre no encontrado en la ubicación: " + chestLocation;
    }

    private Crop findCropByName(String cropName, int cropType, int growthDays) {
        // implementar la lógica para encontrar el cultivo adecuado en la lista de cultivos
        // Dependiendo del tipo de cultivo, deberías buscar en la lista `crops` para encontrar el cultivo que coincida
        for (Crop crop : cropsToList()) {  // Convertir CustomLinkedList<Crop> a List<Crop>
            if (crop.getCropName().equalsIgnoreCase(cropName) &&
                    getCropType(crop).ordinal() + 1 == cropType &&  // Verifica que el tipo coincide con el número ingresado
                    crop.getCropGrowthDays() == growthDays) {
                return crop;
            }
        }
        return null;
    }

    private List<Crop> cropsToList() {
        List<Crop> list = new ArrayList<>();
        Node<Crop> current = crops.getHead();  // Método para obtener la cabeza de la lista
        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }

    private Enum<?> getCropType(Crop crop) {
        if (crop instanceof SpringCrop) {
            return ((SpringCrop) crop).getSpringType();
        } else if (crop instanceof SummerCrop) {
            return ((SummerCrop) crop).getSummerType();
        } else if (crop instanceof FallCrop) {
            return ((FallCrop) crop).getFallType();
        } else if (crop instanceof WinterCrop) {
            return ((WinterCrop) crop).getWinterType();
        } else if (crop instanceof OtherCrop) {
            return ((OtherCrop) crop).getOtherType();
        }
        return null;
    }

    //////
    ///////
    ////////
    // Mostrar todos los cofres con sus cultivos
    public void displayChests() {
        System.out.println("Lista de cofres:");
        for (Chest chest : chests) {
            System.out.println("Cofre en la ubicación " + chest.getChestLocation() + " con etiqueta " + chest.getChestLabel());
            chest.displayChestContents();  // Mostrar los cultivos en el cofre
        }
    }

    /** Description: Method to save the actual state of the chests in .json file.
     *
     * @param file : Archivo donde se guardan los datos.
     */
    public void SaveChests(String file) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(file)) {
            if (chests.isEmpty()) {
                System.out.println("La lista de cofres está vacía. Nada para serializar.");
            } else {
                gson.toJson(chests, writer);
                System.out.println("Cofres serializados correctamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error serializando la información del juego: " + e.getMessage());
        }
    }

    /** Description: A method to load the List of Chest Saved in .json file.
     *
     * @param file : Archivo donde se guardan los datos
     */
    public void LoadChests(String file) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Chest>>() {}.getType();
            chests = gson.fromJson(reader, listType);

            if (chests == null) {
                System.out.println("El archivo JSON no contiene cofres válidos o está vacío.");
            } else {
                System.out.println("Cofres cargados correctamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error cargando la información del juego: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            System.out.println("Error de sintaxis JSON al cargar los cofres: " + e.getMessage());
        }
    }


    /** CAMBIOS/
     *
     *
     */


    /**Cambios*/

    // Método auxiliar para convertir CustomLinkedList<Chest> a List<Chest>
    private List<Chest> chestsToList() {
        List<Chest> list = new ArrayList<>();
        Node<Chest> current = chests.getHead();  // Método para obtener la cabeza de la lista
        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }


    // Método para inicializar los cultivos disponibles
    // Método para inicializar los cultivos disponibles
    private void availableCrops() {
        // Cultivos de primavera
        crops.add(new SpringCrop("Ajo", "Primavera", 3, SpringType.GARLIC));
        crops.add(new SpringCrop("Allium azul", "Primavera", 7, SpringType.BLUE_ALLIUM));
        crops.add(new SpringCrop("Chirivia", "Primavera", 1, SpringType.PARSNIP));

        // Cultivos de verano
        crops.add(new SummerCrop("Amapola", "Verano", 4, SummerType.POPPY));
        crops.add(new SummerCrop("Arandano", "Verano", 6, SummerType.BLUEBERRY));
        crops.add(new SummerCrop("Carambola", "Verano", 8, SummerType.CARAMBOLA));

        // Cultivos de otoño
        crops.add(new FallCrop("Alcachofa", "Otoño", 9, FallType.ARTICHOKE));
        crops.add(new FallCrop("Amaranto", "Otoño", 13, FallType.AMARANTH));
        crops.add(new FallCrop("Berenjena", "Otoño", 14, FallType.EGGPLANT));

        // Cultivos de invierno
        crops.add(new WinterCrop("Melon de polvo", "Invierno", 18, WinterType.DUST_MELON));

        // Cultivos multiestacionales
        crops.add(new OtherCrop("Fruto Qi", "Multiestacional", 22, OtherType.QI_FRUITL));
        crops.add(new OtherCrop("Piña", "Multiestacional", 16, OtherType.MILLENIAL_FRUIT));
    }


    // Método auxiliar para encontrar un cultivo por nombre en la lista de cultivos
    private Crop findCropByName(String cropName) {
        for (Crop crop : crops) {
            if (crop.getCropName().equalsIgnoreCase(cropName)) {
                return crop;
            }
        }
        return null;
    }
}
