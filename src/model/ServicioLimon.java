package model;

import java.util.ArrayList;
import java.util.List;

public class ServicioLimon {
    private Cliente cliente;
    private List<ItemOrden> items;
    private boolean servicioExpres;
    private boolean ordenConfirmada;
    private double totalBruto;
    private double recargoExpres;
    private double descuento;
    private double totalFinal;

    public ServicioLimon(Cliente cliente) {
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.servicioExpres = false;
        this.ordenConfirmada = false;
    }

    public void agregarItem(int tipoItem, int cantidad) {
        if (ordenConfirmada) {
            throw new IllegalStateException("No se puede editar una orden confirmada");
        }
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        String nombreItem = obtenerNombreItem(tipoItem);
        double subtotal = Catalogo.extraerCatalogo(tipoItem, cantidad);
        double precioUnitario = subtotal / cantidad;
        
        ItemOrden item = new ItemOrden(tipoItem, cantidad, subtotal, nombreItem, precioUnitario);
        items.add(item);
    }

    public void setServicioExpres(boolean expres) {
        if (ordenConfirmada) {
            throw new IllegalStateException("No se puede editar una orden confirmada");
        }
        this.servicioExpres = expres;
    }

    public double calcularTotalServicio() {
        if (items.isEmpty()) {
            throw new IllegalStateException("La orden debe tener al menos un ítem");
        }

        totalBruto = 0;
        for (ItemOrden item : items) {
            totalBruto += item.getSubtotal();
        }

        recargoExpres = 0;
        if (servicioExpres) {
            recargoExpres = totalBruto * 0.10;
        }

        double totalConRecargo = totalBruto + recargoExpres;
        descuento = 0;
        if (totalConRecargo > 60000) {
            descuento = totalConRecargo * 0.05;
        }

        totalFinal = totalConRecargo - descuento;
        if (totalFinal < 0) {
            throw new IllegalStateException("El total no puede ser negativo");
        }

        return totalFinal;
    }

    public void confirmarOrden() {
        if (ordenConfirmada) {
            throw new IllegalStateException("La orden ya está confirmada");
        }
        
        calcularTotalServicio();
        ordenConfirmada = true;
    }

    public String mostrarResumen() {
        if (!ordenConfirmada) {
            throw new IllegalStateException("La orden debe estar confirmada para mostrar el resumen");
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("========================================\n");
        resumen.append("    LAVANDERIA EL LIMONCITO\n");
        resumen.append("========================================\n\n");
        
        resumen.append("CLIENTE:\n");
        resumen.append("  Nombre: ").append(cliente.getNombre()).append("\n");
        resumen.append("  Teléfono: ").append(cliente.getTelefono()).append("\n\n");
        
        resumen.append("DETALLE DE LA ORDEN:\n");
        resumen.append("----------------------------------------\n");
        for (ItemOrden item : items) {
            resumen.append(String.format("  %s: %d unidad(es) x $%,.0f = $%,.0f\n", 
                item.getNombreItem(), 
                item.getCantidad(), 
                item.getPrecioUnitario(), 
                item.getSubtotal()));
        }
        resumen.append("----------------------------------------\n\n");
        
        resumen.append(String.format("Total Bruto: $%,.0f\n", totalBruto));
        
        if (servicioExpres) {
            resumen.append(String.format("Recargo Exprés (10%%): $%,.0f\n", recargoExpres));
        }
        
        if (descuento > 0) {
            resumen.append(String.format("Descuento (5%%): -$%,.0f\n", descuento));
        }
        
        resumen.append("----------------------------------------\n");
        resumen.append(String.format("TOTAL FINAL: $%,.0f\n", totalFinal));
        resumen.append("========================================\n");
        
        return resumen.toString();
    }

    private String obtenerNombreItem(int tipoItem) {
        switch (tipoItem) {
            case 1:
                return "Camisa";
            case 2:
                return "Pantalón";
            case 3:
                return "Chaqueta";
            default:
                return "Desconocido";
        }
    }

    // Getters para pruebas
    public boolean isOrdenConfirmada() {
        return ordenConfirmada;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public double getRecargoExpres() {
        return recargoExpres;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public List<ItemOrden> getItems() {
        return new ArrayList<>(items); 
    }
}