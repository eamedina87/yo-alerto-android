package ec.medval.hackatoniee;

import android.location.Location;

/**
 * Created by Supertel on 11/3/15.
 */
public class DB_Upc {

    private long id;
    private String nombre;
    private double latitude;
    private double longitude;
    private String telefono;
    private String telefono_movil;
    private String direccion;
    private String sector;

    public DB_Upc(){
        this.id = -1;
        this.nombre = "default";
        this.latitude = 900;
        this.longitude = 900;
        this.telefono = "0";
        this.telefono_movil = "0";
        this.direccion = "default";
        this.sector="default";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_movil() {
        return telefono_movil;
    }

    public void setTelefono_movil(String telefono_movil) {
        this.telefono_movil = telefono_movil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Location getLocation() {
        Location loc = null;
        if (latitude!=900 && longitude!=900) {
            loc = new Location("");
            loc.setLatitude(this.getLatitude());
            loc.setLongitude(this.getLongitude());
        }
        return loc;
    }
}
