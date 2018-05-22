package Models;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Palacios on 21/5/2018.
 */

public class ListasProductosSing {
    private static final ListasProductosSing ourInstance = new ListasProductosSing();

    public static ListasProductosSing getInstance() {
        return ourInstance;
    }
    public static List<Productos> listaProductos;
    public static Bitmap[] bitmaps;
    public static List<Ofertas> listaOfertas;
    public static Clientes clientes;
    private ListasProductosSing() {
    }

    public static List<Productos> getListaProductos() {
        return listaProductos;
    }

    public static void setListaProductos(List<Productos> listaProductos) {
        ListasProductosSing.listaProductos = listaProductos;
    }

    public static List<Ofertas> getListaOfertas() {
        return listaOfertas;
    }

    public static void setListaOfertas(List<Ofertas> listaOfertas) {
        ListasProductosSing.listaOfertas = listaOfertas;
    }

    public static Clientes getClientes() {
        return clientes;
    }

    public static void setClientes(Clientes clientes) {
        ListasProductosSing.clientes = clientes;
    }

    public static Bitmap[] getBitmaps() {
        return bitmaps;
    }

    public static void setBitmaps(Bitmap[] bitmaps) {
        ListasProductosSing.bitmaps = bitmaps;
    }
}
