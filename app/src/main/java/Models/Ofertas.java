package Models;

import java.util.Date;

public class Ofertas {
    public int IdOferta;
    public int IdProducto;
    public Date FechaInicio;
    public Date FechaFinal;
    public float Descuento;
    public Date CreatedOn;
    public Date ModifiedOn;
    public Date DeletedOn;
    public Boolean Inactivo;

    public int getIdOferta() {
        return IdOferta;
    }

    public void setIdOferta(int idOferta) {
        IdOferta = idOferta;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        FechaFinal = fechaFinal;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float descuento) {
        Descuento = descuento;
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
