package Models;

import java.util.Date;

/**
 * Created by Palacios on 6/5/2018.
 */

public class Clientes {
    public int IdCliente;

    public String Nombre;

    public String Apellido;

    public String RazonSocial;

    public String Direccion ;

    public int IdPais;

    public int IdDepartamento;

    public String Telefono;

    public String  Celular;

    public String Nit;

    public String Ncr;

    public Date FechaNac;

    public String TipoCliente;

    public Date CreatedOn;

    public Date ModifiedOn;

    public Date DeletedOn;

    public Boolean Inactivo;

    public int IdUsuario;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int idPais) {
        IdPais = idPais;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public String getNcr() {
        return Ncr;
    }

    public void setNcr(String ncr) {
        Ncr = ncr;
    }

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        FechaNac = fechaNac;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        TipoCliente = tipoCliente;
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

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
}
