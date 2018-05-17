import java.util.List;

import Models.Productos;

public class Listas {
    private static final Listas ourInstance = new Listas();

    public static Listas getInstance() {
        return ourInstance;
    }

    private static List<Productos> listaProductos;

    public static List<Productos> getListaProductos() {
        return listaProductos;
    }

    public static void setListaProductos(List<Productos> listaProductos) {
        Listas.listaProductos = listaProductos;
    }

    private Listas() {
    }
}
