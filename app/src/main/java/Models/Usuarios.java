package Models;

import java.util.Date;

public class Usuarios {

    public String Usuario;
    public String Nombres;
    public String Apellidos;
    public String Password;
    public String Cargo;
    public int IdRol;
    public Date CreatedOn;
    public Date ModifiedOn;
    public Date DeletedOn;
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
        if (password == null){
            password ="";
        }
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

    public boolean isInactivo() {
        return Inactivo;
    }

    public void setInactivo(boolean inactivo) {
        Inactivo = inactivo;
    }
}
