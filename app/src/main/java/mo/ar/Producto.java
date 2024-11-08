package mo.ar;

public class Producto {
    private String id;
    private String nombre;
    private String marca;
    private String tipo;
    private String qtyunit;
    private int tipUnit;
    private String enlace_nube;
    private String enlace_imagen;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQtyunit() {
        return qtyunit;
    }

    public void setQtyunit(String qtyunit) {
        this.qtyunit = qtyunit;
    }

    public int getTipUnit() {
        return tipUnit;
    }

    public void setTipUnit(int tipUnit) {
        this.tipUnit = tipUnit;
    }

    public String getEnlace_nube() {
        return enlace_nube;
    }

    public void setEnlace_nube(String enlace_nube) {
        this.enlace_nube = enlace_nube;
    }

    public String getEnlace_imagen() {
        return enlace_imagen;
    }

    public void setEnlace_imagen(String enlace_imagen) {
        this.enlace_imagen = enlace_imagen;
    }
}
