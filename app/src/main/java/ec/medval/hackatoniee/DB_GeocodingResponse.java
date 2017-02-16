package ec.medval.hackatoniee;

import android.location.Address;

import java.util.Locale;

/**
 * Created by Supertel on 18/3/15.
 */
public class DB_GeocodingResponse {

    private Address address;
    private DB_Stat stat;
    private DB_Upc upc;

    public DB_GeocodingResponse(){
        this.address = new Address(new Locale("SPANISH"));
        this.stat = new DB_Stat();
        this.upc = new DB_Upc();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DB_Stat getStat() {
        return stat;
    }

    public void setStat(DB_Stat stat) {
        this.stat = stat;
    }

    public DB_Upc getUpc() {
        return upc;
    }

    public void setUpc(DB_Upc upc) {
        this.upc = upc;
    }
}
