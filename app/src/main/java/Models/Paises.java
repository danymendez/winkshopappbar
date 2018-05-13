package Models;

public class Paises {
    public int IdPais;
    public String NombrePais;

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int idPais) {
        IdPais = idPais;
    }

    public String getNombrePais() {
        return NombrePais;
    }

    public void setNombrePais(String nombrePais) {
        NombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return NombrePais.toString();
    }
}
