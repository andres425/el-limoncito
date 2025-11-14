package model;

public class ItemOrden {
    private int tipoItem; // 1: Camisa, 2: Pantalón, 3: Chaqueta
    private int cantidad;
    private double subtotal;
    private String nombreItem;
    private double precioUnitario;

    public ItemOrden(int tipoItem, int cantidad, double subtotal, String nombreItem, double precioUnitario) {
        this.tipoItem = tipoItem;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.nombreItem = nombreItem;
        this.precioUnitario = precioUnitario;
    }

    public int getTipoItem() {
        return tipoItem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}