package ec.medval.hackatoniee;

/**
 * Created by Supertel on 18/3/15.
 */
public class DB_Stat {

    public static final int HOMICIDIO = 0;
    public static final int ASALTO_ROBO = 1;
    public static final int ACCIDENTE = 2;
    public static final int HOMICIDIO_GREEN = 9;
    public static final int HOMICIDIO_YELLOW = 15;
    public static final int HOMICIDIO_RED = 22;
    public static final int ASALTO_GREEN = 130;
    public static final int ASALTO_YELLOW = 223;
    public static final int ASALTO_RED = 316;
    public static final int ACCIDENTE_GREEN = 23;
    public static final int ACCIDENTE_YELLOW = 34;
    public static final int ACCIDENTE_RED = 48;

    private long id;
    private String ciudad;
    private String sector;
    private String subsector;
    private int asaltos_2014;
    private int homicidios_2014;
    private int accidentes_2014;
    private int asaltos_2013;
    private int homicidios_2013;
    private int accidentes_2013;
    private int asaltos_2012;
    private int homicidios_2012;
    private int accidentes_2012;
    private int asaltos_2011;
    private int homicidios_2011;
    private int accidentes_2011;

    public DB_Stat(){
        this.id=-1;
        this.ciudad="default";
        this.sector="default";
        this.subsector="default";
        this.asaltos_2011=-1;
        this.asaltos_2012=-1;
        this.asaltos_2013=-1;
        this.asaltos_2014=-1;
        this.homicidios_2011=-1;
        this.homicidios_2012=-1;
        this.homicidios_2013=-1;
        this.homicidios_2014=-1;
        this.accidentes_2011=-1;
        this.accidentes_2012=-1;
        this.accidentes_2013=-1;
        this.accidentes_2014=-1;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSubsector() {
        return subsector;
    }

    public void setSubsector(String subsector) {
        this.subsector = subsector;
    }

    public int getAsaltos2014() {
        return asaltos_2014;
    }

    public void setAsaltos2014(int asaltos_2014) {
        this.asaltos_2014 = asaltos_2014;
    }

    public int getHomicidios2014() {
        return homicidios_2014;
    }

    public void setHomicidios2014(int homicidios_2014) {
        this.homicidios_2014 = homicidios_2014;
    }

    public int getAccidentes2014() {
        return accidentes_2014;
    }

    public void setAccidentes2014(int accidentes_2014) {
        this.accidentes_2014 = accidentes_2014;
    }

    public int getAsaltos2013() {
        return asaltos_2013;
    }

    public void setAsaltos2013(int asaltos_2013) {
        this.asaltos_2013 = asaltos_2013;
    }

    public int getHomicidios2013() {
        return homicidios_2013;
    }

    public void setHomicidios2013(int homicidios_2013) {
        this.homicidios_2013 = homicidios_2013;
    }

    public int getAccidentes2013() {
        return accidentes_2013;
    }

    public void setAccidentes2013(int accidentes_2013) {
        this.accidentes_2013 = accidentes_2013;
    }

    public int getAsaltos2012() {
        return asaltos_2012;
    }

    public void setAsaltos2012(int asaltos_2012) {
        this.asaltos_2012 = asaltos_2012;
    }

    public int getHomicidios2012() {
        return homicidios_2012;
    }

    public void setHomicidios2012(int homicidios_2012) {
        this.homicidios_2012 = homicidios_2012;
    }

    public int getAccidentes2012() {
        return accidentes_2012;
    }

    public void setAccidentes2012(int accidentes_2012) {
        this.accidentes_2012 = accidentes_2012;
    }

    public int getAsaltos2011() {
        return asaltos_2011;
    }

    public void setAsaltos2011(int asaltos_2011) {
        this.asaltos_2011 = asaltos_2011;
    }

    public int getHomicidios2011() {
        return homicidios_2011;
    }

    public void setHomicidios2011(int homicidios_2011) {
        this.homicidios_2011 = homicidios_2011;
    }

    public int getAccidentes2011() {
        return accidentes_2011;
    }

    public void setAccidentes2011(int accidentes_2011) {
        this.accidentes_2011 = accidentes_2011;
    }
}
