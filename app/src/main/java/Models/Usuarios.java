package Models;

import java.util.Date;

public class Usuarios {

    public String Usuario;
    public String Nombres;
    public String Apellidos;
    public String Password;
    public String Cargo;
    public int IdRol;
    public String CreatedOn;
    public String ModifiedOn;
    public String DeletedOn;
    public boolean Inactivo;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        IdRol = idRol;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        ModifiedOn = modifiedOn;
    }

    public String getDeletedOn() {
        return DeletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        DeletedOn = deletedOn;
    }

    public boolean isInactivo() {
        return Inactivo;
    }

    public void setInactivo(boolean inactivo) {
        Inactivo = inactivo;
    }
}
