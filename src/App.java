import model.Catalogo;
import model.Cliente;
import model.ServicioLimon;

public class App {

    public static void main(String[] args) {
        System.out.println("=== LAVANDERIA EL LIMONCITO ===");
        Cliente cliente1 = new Cliente("Juan Pérez", "3001234567");
        ServicioLimon servicio1 = new ServicioLimon(cliente1);
        
        Catalogo.mostrarCatalogo();
        
        System.out.println("\nAgregando ítems a la orden...");
        servicio1.agregarItem(1, 5);
        servicio1.agregarItem(2, 6);
       
        
        servicio1.confirmarOrden();

        
        System.out.println(servicio1.mostrarResumen());
    }
}