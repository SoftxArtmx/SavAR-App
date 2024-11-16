/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo: Producto
 * | Clase/modulo a codificar: MO.AR.Producto
 * | Descripción general: Representación de los datos de un producto en el sistema,
 * |                      incluyendo su información básica y atributos específicos para visualización y gestión.
 * | Funciones especificas:
 * |   - Almacenamiento de información detallada del producto, como nombre, marca, tamaño y cantidad.
 * |   - Acceso a datos del producto para su visualización en interfaces de usuario.
 * |   - Integración con el módulo de escaneo y la lógica de carrito de compras.
 * |   - Compatibilidad con la API para obtener detalles del producto desde el backend.
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

public class Producto {
    private String nombre;
    private String marca;
    private String tipo;
    private String tamano;
    private int cantidad;

    public Producto(String nombre, String marca, String tipo, String tamano, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.tipo = tipo;
        this.tamano = tamano;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamano() {
        return tamano;
    }

    public int getCantidad() {
        return cantidad;
    }
}
