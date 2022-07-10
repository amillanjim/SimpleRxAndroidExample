package com.etheur.rxproject.models;

/**
 * Inmutabilidad:
 * La inmutabilidad de una clase es importante para proteger nuestros datos, en la medida de lo
 * posible debemos de crear nuestros objetos inmutables.
 * Cuando se hace una clase final se indica que la clase no puede ser extendida, por lo cual no
 * puede haber herencia.
 */
public final class Worker {

    private final int id;
    private final String name;
    private final Direction direction;

    public Worker(int id, String name, Direction direction){
        this.id = id;
        this.name = name;
        this.direction = direction;
    }

    public int getId() {
        return new Worker(id, name, direction).id;
    }

    public String getName() {
        return new Worker(id, name, direction).name;
    }

    public Direction getDirection() {
        return new Direction(direction.getStreet(), direction.getNumber(), direction.getCity());
    }

     /**
     * Ejemplo de inmutabilidad, se esta protegiendo el dato de ser sobre escrito creando una
     * instancia nueva del objeto y mandadole los valores ya existentes y solo cambiando la
     * variable correspondiente.
     * En lugar de modificar las variables de instancia se devuelve una nueva instancia con la
     * modificación.
     * @param newName
     * @return Worker: A new Worker instance
     */
     public Worker changeName(String newName){
        return new Worker(id, newName, direction);
    }
}
