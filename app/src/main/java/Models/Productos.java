package Models;

import java.util.Date;

public class Productos {

    public int IdProducto;

    public String NombreProducto;

    public String TipoMedida;

    public byte IdCategoria;

    public int IdProveedor;

    public String UrlImagen;

    public String Descripcion;

    public String TipoProducto;

    public String Marca;

    public double Precio;

    public int Cantidad;

    public Date FechaVenc;

    public Date CreatedOn;

    public Date ModifiedOn;

    public Date DeletedOn;

    public Boolean Inactivo;

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getTipoMedida() {
        return TipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        TipoMedida = tipoMedida;
    }

    public byte getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(byte idCategoria) {
        IdCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        IdProveedor = idProveedor;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        TipoProducto = tipoProducto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Date getFechaVenc() {
        return FechaVenc;
    }

    public void setFechaVenc(Date fechaVenc) {
        FechaVenc = fechaVenc;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }

    public Date getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        ModifiedOn = modifiedOn;
    }

    public Date getDeletedOn() {
        return DeletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        DeletedOn = deletedOn;
    }

    public Boolean getInactivo() {
        return Inactivo;
    }

    public void setInactivo(Boolean inactivo) {
        Inactivo = inactivo;
    }
}
