package ui;

import java.util.Scanner;
import control.ChestSystemController;
import model.ChestLabel;

public class ChestSystem {

    private ChestSystemController controller;
    public static Scanner scanner = new Scanner(System.in);

    public ChestSystem() {
        createController();
    }

    public static void main(String[] args) {

        ChestSystem objMain = new ChestSystem();
        boolean menu = true;

        while (menu) {
            System.out.println("1) Mostrar cultivos disponibles");
            System.out.println("2) Crear cofre");
            System.out.println("3) Agregar cultivo a cofre");
            System.out.println("4) Ordenar cultivos en cofre");
            System.out.println("5) Consultar un cultivo en un cofre");
            System.out.println("6) Consultar todos los cultivos en un cofre");
            System.out.println("99) Salir");

            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (option) {
                case 1:
                    objMain.showCrops();
                    break;

                case 2:
                    objMain.createChest();
                    break;

                case 3:
                    objMain.addCropToChest();
                    break;

                case 4:
                    objMain.sortCropsInChest();
                    break;

                case 5:
                    objMain.searchCropsInChest();
                    break;

                case 6:
                    objMain.checkCropsInChest();
                    break;

                case 99:
                    menu = false;
                    objMain.controller.SaveChests("chests.json");  // Guardar el estado de los cofres antes de salir
                    break;

                default:
                    System.out.println("Opción incorrecta, por favor intente de nuevo.");
                    break;
            }
        }
    }

    public void createController() {
        boolean menu = true;
        while (menu) {
            System.out.println("1) Crear inventario nuevo");
            System.out.println("2) Cargar inventario existente");

            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            switch (option) {
                case 1:
                    controller = new ChestSystemController();
                    menu = false;
                    break;
                case 2:
                    controller = new ChestSystemController();
                    controller.LoadChests("chests.json");  // Cargar el estado anterior de los cofres
                    menu = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public void showCrops() {
        controller.displayChests();  // Mostrar los cultivos de todos los cofres
    }

    public void createChest() {
        System.out.println("Seleccione el tipo de cofre a crear:");
        System.out.println("1) Cofre de Primavera");
        System.out.println("2) Cofre de Verano");
        System.out.println("3) Cofre de Otoño");
        System.out.println("4) Cofre de Invierno");
        System.out.println("5) Cofre Normal");

        int option = scanner.nextInt();
        scanner.nextLine();

        ChestLabel chestLabel;
        switch (option) {
            case 1:
                chestLabel = ChestLabel.SPRING;
                break;
            case 2:
                chestLabel = ChestLabel.SUMMER;
                break;
            case 3:
                chestLabel = ChestLabel.FALL;
                break;
            case 4:
                chestLabel = ChestLabel.WINTER;
                break;
            case 5:
                chestLabel = ChestLabel.OTHER;
                break;
            default:
                System.out.println("Opción incorrecta.");
                return;
        }

        System.out.println("Ingrese la ubicación del cofre (número):");
        int chestLocation = scanner.nextInt();
        scanner.nextLine();

        controller.addChest(chestLocation, chestLabel);
        System.out.println("Cofre creado exitosamente.");
    }

    private  void addCropToChest() {
        System.out.print("Ingrese la ubicación del cofre al que desea agregar el cultivo: ");
        int chestLocation = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el nombre del cultivo que desea agregar: ");
        String cropName = scanner.nextLine();


        System.out.println("Ingrese el tipo del cultivo:");
        System.out.println("1. SPRING");
        System.out.println("2. SUMMER");
        System.out.println("3. FALL");
        System.out.println("4. WINTER");
        System.out.println("5. OTHER");
        int cropType = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese los días de crecimiento del cultivo: ");
        int growthDays = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese la cantidad de cultivo a agregar: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Llamar al método del controlador para añadir el cultivo al cofre
        String message = controller.addCropToChest(chestLocation, cropName, cropType, growthDays, quantity);
        System.out.println(message);
    }


    public void sortCropsInChest() {
        System.out.println("Ingrese la ubicación del cofre que desea ordenar:");
        int chestLocation = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Elige la opción de ordenamiento de los cultivos:");
        System.out.println("1) Por nombre");
        System.out.println("2) Por tipo");
        System.out.println("3) Por días de crecimiento");
        int option = scanner.nextInt();
        scanner.nextLine();

        controller.sortCropsInChest(chestLocation, option);
        System.out.println("Cultivos ordenados exitosamente.");
    }

    public void searchCropsInChest() {
        System.out.println("Ingrese la ubicación del cofre que desea consultar:");
        int chestLocation = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del cultivo que desea buscar:");
        String cropName = scanner.nextLine();

        controller.searchCropsInChest(chestLocation, cropName);
    }

    public void checkCropsInChest() {
        System.out.println("Ingrese la ubicación del cofre que desea consultar:");
        int chestLocation = scanner.nextInt();
        scanner.nextLine();

        controller.checkCropsInChest(chestLocation);
    }
}



