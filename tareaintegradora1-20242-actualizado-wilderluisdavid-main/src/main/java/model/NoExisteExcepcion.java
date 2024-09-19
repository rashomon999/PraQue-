package model;
public class NoExisteExcepcion extends Exception{

    public NoExisteExcepcion(String nameCrop){
        super("El código: " + nameCrop + " no existe para ningún paciente");
    }

}