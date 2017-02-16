package ec.medval.hackatoniee;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Supertel on 11/3/15.
 */
public class DB_Helper extends SQLiteOpenHelper{

    private static final String dbname = "yoalerto.db";
    private static final int dbversion = 1;
    private SQLiteDatabase db;
    private Context ctx;

    public DB_Helper(Context context) {
        super(context, dbname, null, dbversion);
        ctx = context;
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String line="";
        try {


            //CREATE DATABASE
            db.execSQL("CREATE TABLE UPC (UPC_ID LONG PRIMARY KEY, NOMBRE STRING, " +
                    "LATITUD NUMBER, LONGITUD NUMBER, TELEFONO STRING, TELEFONO_MOVIL STRING, DIRECCION STRING, SECTOR STRING);");
            db.execSQL("CREATE TABLE STAT (STAT_ID LONG PRIMARY KEY, CIUDAD STRING, SECTOR STRING, SUBSECTOR STRING, " +
                    "ASALTOS_2014 NUMBER, HOMICIDIOS_2014 NUMBER, ACCIDENTES_2014 NUMBER," +
                    "ASALTOS_2013 NUMBER, HOMICIDIOS_2013 NUMBER, ACCIDENTES_2013 NUMBER," +
                    "ASALTOS_2012 NUMBER, HOMICIDIOS_2012 NUMBER, ACCIDENTES_2012 NUMBER," +
                    "ASALTOS_2011 NUMBER, HOMICIDIOS_2011 NUMBER, ACCIDENTES_2011 NUMBER);");
            Log.i("SQLITE_DB", "DB CREATED");
        }
        catch (SQLiteException e)
        {
            Log.e("SQLITE_DB", "DB FAILED TO CREATE");
        }

        try {
            InputStream is = (InputStream) ctx.getAssets().open("dbinsert.txt");
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null) {
                try {
                    db.execSQL(line);
                } catch (SQLiteException e)
                {
                    e.printStackTrace();
                }
            }
            br.close();
            isr.close();
            is.close();

            Log.i("SQLITE_DB", "DB POPULATED");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<DB_Upc> getAllUpcs() throws  SQLiteException{
        ArrayList<DB_Upc> upeces = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM UPC", null);
        if (c!=null && c.getCount()>0)
        {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                try {
                    DB_Upc upc = cursorToUpc(c);
                    upeces.add(upc);
                    c.moveToNext();
                } catch (Exception e){}
            }
        }
        c.close();
        return upeces;
    }

    private DB_Upc cursorToUpc(Cursor c)
    {
        DB_Upc upc = new DB_Upc();
        upc.setId(c.getLong(0));
        upc.setNombre(c.getString(1));
        upc.setLatitude(c.getDouble(2));
        upc.setLongitude(c.getDouble(3));
        /*
        upc.setTelefono(c.getString(5));
        upc.setTelefono_movil(c.getString(6));
        upc.setDireccion(c.getString(7));
        upc.setSector(c.getString(8));
        */
        return upc;
    }

    public ArrayList<DB_Stat> getAllStats() throws  SQLiteException{
        ArrayList<DB_Stat> stats = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM STAT", null);
        if (c!=null && c.getCount()>0)
        {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                try {
                    DB_Stat stat = cursorToStat(c);
                    stats.add(stat);
                    c.moveToNext();
                } catch (Exception e){}
            }
        }
        c.close();
        return stats;
    }

    public DB_Stat getStatBySector(String city, String sector) throws  SQLiteException{
        db = getReadableDatabase();
        DB_Stat stat = null;
        Cursor c = db.rawQuery("SELECT * FROM STAT WHERE CIUDAD='"+city+"' AND SECTOR='"+sector+"'",null);
        if (c!=null && c.getCount()>0)
        {
            c.moveToFirst();
            try {
                 stat = cursorToStat(c);
            } catch (Exception e){}

        }
        c.close();
        return stat;
    }

    private DB_Stat cursorToStat(Cursor c) throws Exception{
        DB_Stat stat = new DB_Stat();
        stat.setId(c.getLong(0));
        stat.setCiudad(c.getString(1));
        stat.setSector(c.getString(2));
        stat.setSubsector(c.getString(3));
        stat.setAsaltos2014(c.getInt(4));
        stat.setHomicidios2014(c.getInt(5));
        stat.setAccidentes2014(c.getInt(6));
        stat.setAsaltos2013(c.getInt(7));
        stat.setHomicidios2013(c.getInt(8));
        stat.setAccidentes2013(c.getInt(9));
        stat.setAsaltos2012(c.getInt(10));
        stat.setHomicidios2012(c.getInt(11));
        stat.setAccidentes2012(c.getInt(12));
        stat.setAsaltos2011(c.getInt(13));
        stat.setHomicidios2011(c.getInt(14));
        stat.setAccidentes2011(c.getInt(15));
        return stat;

    }

    public void close()
    {
        if (db.isOpen()) {
            db.close();
        }
    }
}
