package ec.medval.hackatoniee;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Supertel on 18/3/15.
 */
public class Helper {

    private static ArrayList<String> barrios_calderon;
    private static ArrayList<String> barrios_eloyalfaro;
    private static ArrayList<String> barrios_eugenioespejo;
    private static ArrayList<String> barrios_ladelicia;
    private static ArrayList<String> barrios_loschillos;
    private static ArrayList<String> barrios_tumbaco;
    private static ArrayList<String> barrios_manuelasaenz;
    private static ArrayList<String> barrios_quitumbe;

    public static ArrayList<String> getBarriosCalderon(){
        barrios_calderon = new ArrayList<>();
        barrios_calderon.add("CALDERÓN");
        barrios_calderon.add("CALDERON");
        barrios_calderon.add("LLANO CHICO");
        return barrios_calderon;
    }

    public static ArrayList<String> getBarriosEloyAlfaro(){
        barrios_eloyalfaro = new ArrayList<>();
        barrios_eloyalfaro.add("CHILLIBULLO");
        barrios_eloyalfaro.add("CHIMBACALLE");
        barrios_eloyalfaro.add("LA ARGELIA");
        barrios_eloyalfaro.add("ARGELIA");
        barrios_eloyalfaro.add("LA FERROVIARIA");
        barrios_eloyalfaro.add("FERROVIARIA");
        barrios_eloyalfaro.add("LA MAGDALENA");
        barrios_eloyalfaro.add("MAGDALENA");
        barrios_eloyalfaro.add("LA MAGDALENA");
        barrios_eloyalfaro.add("LA MENA");
        barrios_eloyalfaro.add("MENA");
        barrios_eloyalfaro.add("LLOA");
        barrios_eloyalfaro.add("SAN BARTOLO");
        barrios_eloyalfaro.add("SOLANDA");
        return barrios_eloyalfaro;
    }

    public static ArrayList<String> getBarriosEugenioEspejo(){
        barrios_eugenioespejo = new ArrayList<>();
        barrios_eugenioespejo.add("BELISARIO QUEVEDO");
        barrios_eugenioespejo.add("CHAVEZPAMBA");
        barrios_eugenioespejo.add("COCHAPAMBA");
        barrios_eugenioespejo.add("COMITÈ DEL PUEBLO");
        barrios_eugenioespejo.add("COMITE DEL PUEBLO");
        barrios_eugenioespejo.add("GUAYLLABAMBA");
        barrios_eugenioespejo.add("IÑAQUITO");
        barrios_eugenioespejo.add("INAQUITO");
        barrios_eugenioespejo.add("EL BATÁN");
        barrios_eugenioespejo.add("EL BATAN");
        barrios_eugenioespejo.add("BATÁN");
        barrios_eugenioespejo.add("BATAN");
        barrios_eugenioespejo.add("JIPIJAPA");
        barrios_eugenioespejo.add("KENNEDY");
        barrios_eugenioespejo.add("LA CONCEPCIÓN");
        barrios_eugenioespejo.add("LA CONCEPCION");
        barrios_eugenioespejo.add("MARISCAL SUCRE");
        barrios_eugenioespejo.add("NAYÓN");
        barrios_eugenioespejo.add("NAYON");
        barrios_eugenioespejo.add("PUÉLLARO");
        barrios_eugenioespejo.add("PUELLARO");
        barrios_eugenioespejo.add("RUMIPAMBA");
        barrios_eugenioespejo.add("SAN ISIDRO DEL INCA");
        barrios_eugenioespejo.add("SAN ISIDRO");
        barrios_eugenioespejo.add("SAN JOSÉ DE MINAS");
        barrios_eugenioespejo.add("SAN JOSE DE MINAS");
        barrios_eugenioespejo.add("SAN JOSÉ");
        barrios_eugenioespejo.add("SAN JOSE");
        barrios_eugenioespejo.add("ZÁMBIZA");
        barrios_eugenioespejo.add("ZAMBIZA");
        return barrios_eugenioespejo;
    }

    public static ArrayList<String> getBarriosLaDelicia(){
        barrios_ladelicia = new ArrayList<>();
        barrios_ladelicia.add("CALICALÍ");
        barrios_ladelicia.add("CALICALI");
        barrios_ladelicia.add("CARCELÉN");
        barrios_ladelicia.add("CARCELEN");
        barrios_ladelicia.add("COTOCOLLAO");
        barrios_ladelicia.add("EL CONDADO");
        barrios_ladelicia.add("CONDADO");
        barrios_ladelicia.add("GUALEA");
        barrios_ladelicia.add("NANEGAL");
        barrios_ladelicia.add("NANEGALITO");
        barrios_ladelicia.add("NONO");
        barrios_ladelicia.add("PACTO");
        barrios_ladelicia.add("POMASQUI");
        barrios_ladelicia.add("PONCEANO");
        barrios_ladelicia.add("SAN ANTONIO");
        return  barrios_ladelicia;
    }

    public static ArrayList<String> getBarriosLosChillos(){
        barrios_loschillos = new ArrayList<>();
        barrios_loschillos.add("ALANGASÍ");
        barrios_loschillos.add("ALANGASI");
        barrios_loschillos.add("AMAGUAÑA");
        barrios_loschillos.add("AMAGUANA");
        barrios_loschillos.add("CONOCOTO");
        barrios_loschillos.add("GUANGOPOLO");
        barrios_loschillos.add("LA MERCED");
        barrios_loschillos.add("MERCED");
        barrios_loschillos.add("PINTAG");
        return barrios_loschillos;
    }

    public static ArrayList<String> getBarriosManuelaSaenz(){
        barrios_manuelasaenz = new ArrayList<>();
        barrios_manuelasaenz.add("CENTRO HISTÓRICO");
        barrios_manuelasaenz.add("CENTRO HISTORICO");
        barrios_manuelasaenz.add("ITCHIMBÍA");
        barrios_manuelasaenz.add("ITCHIMBIA");
        barrios_manuelasaenz.add("LA LIBERTAD");
        barrios_manuelasaenz.add("LIBERTAD");
        barrios_manuelasaenz.add("PUENGASÍ");
        barrios_manuelasaenz.add("PUENGASI");
        barrios_manuelasaenz.add("SAN JUAN");
        return barrios_manuelasaenz;
    }

    public static ArrayList<String> getBarriosQuitumbe(){
        barrios_quitumbe = new ArrayList<>();
        barrios_quitumbe.add("CHILLOGALLO");
        barrios_quitumbe.add("GUAMANÍ");
        barrios_quitumbe.add("GUAMANI");
        barrios_quitumbe.add("LA ECUATORIANA");
        barrios_quitumbe.add("ECUATORIANA");
        barrios_quitumbe.add("QUITUMBE");
        barrios_quitumbe.add("TURUBAMBA");
        return barrios_quitumbe;
    }

    public static ArrayList<String> getBarriosTumbaco(){
        barrios_tumbaco = new ArrayList<>();
        barrios_tumbaco.add("CHECA");
        barrios_tumbaco.add("CUMBAYÁ");
        barrios_tumbaco.add("EL QUINCHE");
        barrios_tumbaco.add("QUINCHE");
        barrios_tumbaco.add("PIFO");
        barrios_tumbaco.add("PUEMBO");
        barrios_tumbaco.add("TABABELA");
        barrios_tumbaco.add("TUMBACO");
        barrios_tumbaco.add("YARUQUÌ");
        barrios_tumbaco.add("YARUQUI");
        return barrios_tumbaco;
    }


    public static String getZonaAdministrativa(String sublocality) {
        sublocality = sublocality.toUpperCase();
        String zonaAdministrativa = null;
        if (getBarriosCalderon().contains(sublocality))
        {
            zonaAdministrativa = "CALDERON";
        }
        else if (getBarriosEloyAlfaro().contains(sublocality))
        {
            zonaAdministrativa = "ELOY ALFARO";
        }
        else if (getBarriosEugenioEspejo().contains(sublocality))
        {
            zonaAdministrativa = "EUGENIO ESPEJO";
        }
        else if (getBarriosLaDelicia().contains(sublocality))
        {
            zonaAdministrativa = "LA DELICIA";
        }
        else if (getBarriosLosChillos().contains(sublocality))
        {
            zonaAdministrativa = "LOS CHILLOS";
        }
        else if (getBarriosManuelaSaenz().contains(sublocality))
        {
            zonaAdministrativa = "MANUELA SAENZ";
        }
        else if (getBarriosQuitumbe().contains(sublocality))
        {
            zonaAdministrativa = "QUITUMBE";
        }
        else if (getBarriosTumbaco().contains(sublocality))
        {
            zonaAdministrativa = "TUMBACO";
        }
        return zonaAdministrativa;
    }

    public static int getColorForStat(int type, int value)
    {
        int out = -1;
        switch (type)
        {
            case DB_Stat.HOMICIDIO:
            {
                if (value<=DB_Stat.HOMICIDIO_GREEN)
                {
                    out = Color.GREEN;
                }
                else if (value<=DB_Stat.HOMICIDIO_YELLOW)
                {
                    out = Color.YELLOW;
                }
                else if (value<=DB_Stat.HOMICIDIO_RED)
                {
                    out = Color.RED;
                }
                break;
            }
            case DB_Stat.ASALTO_ROBO:
            {
                if (value<=DB_Stat.ASALTO_GREEN)
                {
                    out = Color.GREEN;
                }
                else if (value<=DB_Stat.ASALTO_YELLOW)
                {
                    out = Color.YELLOW;
                }
                else if (value<=DB_Stat.ASALTO_RED)
                {
                    out = Color.RED;
                }
                break;
            }
            case DB_Stat.ACCIDENTE:
            {
                if (value<=DB_Stat.ACCIDENTE_GREEN)
                {
                    out = Color.GREEN;
                }
                else if (value<=DB_Stat.ACCIDENTE_YELLOW)
                {
                    out = Color.YELLOW;
                }
                else if (value<=DB_Stat.ACCIDENTE_RED)
                {
                    out = Color.RED;
                }
                break;
            }

        }
        return out;
    }

    public static ArrayList<String> getEventosPolicia(){
        ArrayList<String> eventos = new ArrayList<>();
        eventos.add("ASESINATO");
        eventos.add("DELINCUENTE");
        eventos.add("DESAPARICIÓN");
        eventos.add("DISPAROS");
        eventos.add("DROGA");
        eventos.add("ESTAFA");
        eventos.add("LIBADORES");
        eventos.add("PERSONA SOSPECHOSA");
        eventos.add("RIÑA");
        eventos.add("ROBO");
        eventos.add("SECUESTRO");
        return eventos;
    }

    public static ArrayList<String> getEventosTransito(){
        ArrayList<String> eventos = new ArrayList<>();
        eventos.add("ATROPELLO");
        eventos.add("CHOQUE");
        eventos.add("VOLCAMIENTO");
        eventos.add("MAL ESTACIONADO");
        return eventos;
    }

    public static ArrayList<String> getEventosMedico(){
        ArrayList<String> eventos = new ArrayList<>();
        eventos.add("CAÍDA");
        eventos.add("CONVULSIÓN/EPILEPSIA");
        eventos.add("EMBARAZO/PARTO");
        eventos.add("ENVENENAMIENTO");
        eventos.add("HERIDO DE BALA");
        eventos.add("INTOXICACIÒN");
        eventos.add("PARO CARDIACO/RESPIRATORIO");
        eventos.add("QUEMADURA");
        eventos.add("VIOLACIÓN");
        return eventos;
    }

    public static ArrayList<String> getEventosBombero(){
        ArrayList<String> eventos = new ArrayList<>();
        eventos.add("FUGA DE GAS");
        eventos.add("INCENDIO");
        eventos.add("INCENDIO VEHICULAR");
        eventos.add("INUNDACIÓN");
        return eventos;
    }
}
