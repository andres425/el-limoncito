package model;

public class Cliente {
    private String nombre;
    private String telefono;

    public Cliente(String nombre, String telefono){
        this.telefono=telefono;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "el nombre del cliente es: "+nombre+ " el telefono es: "+telefono ;
    }
}