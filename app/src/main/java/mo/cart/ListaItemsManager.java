/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo: ListaItemsManager
 * | Clase/modulo a codificar: MO.AR.ListaItemsManager
 * | Descripción general: Gestión y persistencia de los elementos de la lista de compras del usuario,
 * |                      asegurando que los elementos se mantengan al cambiar de fragmento.
 * | Funciones especificas:
 * |   - Almacenamiento y actualización de elementos en la lista del usuario.
 * |   - Recuperación de los elementos almacenados de la lista de compras.
 * |   - Integración con otros componentes para mantener la consistencia de la lista.
 * |   - Sincronización de la lista de compras con cambios en el carrito de productos.
 * |
 * | Desarrollador encargado: Leonardo Zavala González
 * | Aprobado por: Marcos Emmanuel Juarez Navarro
 * |
 * | CAMBIOS REALIZADOS DESDE LA ULTIMA VERSION
 * | Nombre:        Fecha:               Cambios Realizados:
 * | LZG            14/11/24             Creacion de la clase y finalización de la implementación de esta conexion.
 * |
 * |
 * */

package mo.cart;
import java.util.ArrayList;

public class ListaItemsManager {
    private static ListaItemsManager instance;
    private ArrayList<String> listaItems;

    private ListaItemsManager() {
        listaItems = new ArrayList<>();
    }

    public static synchronized ListaItemsManager getInstance() {
        if (instance == null) {
            instance = new ListaItemsManager();
        }
        return instance;
    }

    public ArrayList<String> getListaItems() {
        return listaItems;
    }

    public void agregarItem(String item) {
        listaItems.add(item);
    }
}
