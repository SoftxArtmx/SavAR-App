/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo: ConexionARCamara
 * | Clase/modulo a codificar: MO.AR.ConexionARCamara
 * | Descripción general: Manejo centralizado de los productos escaneados, aplicando el patrón Singleton
 * |                     para almacenar y gestionar la lista de productos en el carrito de compras.
 * | Funciones especificas:
 * |   - Singleton para la instancia única de la clase.
 * |   - Gestión de productos escaneados en el carrito de compras.
 * |   - Agregar producto al carrito.
 * |   - Obtener lista actual de productos en el carrito.
 * |   - Vaciar el carrito (para casos de reinicio de sesión o limpieza de datos).
 * |
 * | Desarrollador encargado: Leonardo Zavala González
 * | Aprobado por: Marcos Emmanuel Juarez Navarro
*  |
 * | CAMBIOS REALIZADOS DESDE LA ULTIMA VERSION
 * | Nombre:        Fecha:               Cambios Realizados:
 * | LZG            12/11/24             Creacion de la clase e inicio de la implementacion
 * | LZG            13/11/24             Finalización de implementación la clase.
 * |
 * |
 * |
 * */


package mo.ar;

import android.util.Log;
import java.util.ArrayList;

public class ConexionARCamara {

    private static ConexionARCamara instance;
    private ArrayList<String> productosEnCarrito;
    private static final String TAG = "ConexionARCamara";

    private ConexionARCamara() {
        productosEnCarrito = new ArrayList<>();
    }

    public static ConexionARCamara getInstance() {
        if (instance == null) {
            instance = new ConexionARCamara();
        }
        return instance;
    }

    public void agregarProducto(String producto) {
        productosEnCarrito.add(0, producto); // Agregar al inicio de la lista
        Log.d(TAG, "Producto agregado al carrito: " + producto + ". Total productos: " + productosEnCarrito.size());
    }

    public ArrayList<String> getProductosEnCarrito() {
        Log.d(TAG, "Obteniendo productos escaneados: " + productosEnCarrito.size() + " productos");
        return productosEnCarrito;
    }
}
