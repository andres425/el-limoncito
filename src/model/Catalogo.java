package model;

public class Catalogo {

    public static void mostrarCatalogo() {
        System.out.println("1.camisa ($4.000, o $3.500 c/u si dejan 5 o más).");
        System.out.println("2.Pantalón ($6.000, o $5.000 c/u desde 5).");
        System.out.println("3. Chaqueta ($9.000, o $7.500 c/u desde 5).");
    }

    public static double extraerCatalogo(int catalogo, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor o igual 0");
        }

        switch (catalogo) {
            case 1:
                if (cantidad >= 5) {
                    return 3500 * cantidad;
                } else {
                    return 4000 * cantidad;
                }

            case 2:
                if (cantidad >= 5) {
                    return 5000 * cantidad;
                } else {
                    return 6000 * cantidad;
                }

            case 3:
                if (cantidad >= 5) {
                    return 7500 * cantidad;
                } else {
                    return 9000 * cantidad;
                }

            default:
                throw new IllegalArgumentException("Producto desconocido: ");
        }
    }

}