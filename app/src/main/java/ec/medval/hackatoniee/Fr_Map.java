package ec.medval.hackatoniee;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Fr_Map extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener, View.OnClickListener {

    private static final int UPC_ALL = 0;
    private static final int UPC_FIVE_KM = 1;
    private static final int UPC_TEN_KM = 2;
    private static final int TRESHOLD_5KM = 5000;
    private static final int TRESHOLD_10KM = 10000;
    private MapFragment map;
    private GoogleMap mapa;
    private ArrayList<DB_Upc> upcs;
    private ArrayList<Float> distances;
    private Location currentLocation;
    private float minDistance = 1000000000;
    private DB_Upc nearestUpc;
    private int cont=0;
    private Location lastLocation;
    private Button btn_ver;
    private TextView tv_closests;
    private boolean isMapPopulated=false;
    private TextView btn_todos;
    private TextView btn_cinco;
    private TextView btn_diez;
    private TextView btn_cerca;
    private TextView map_address;
    private DB_Stat currentStat;
    private TextView stat_accidentes;
    private TextView stat_homicidios;
    private TextView stat_asaltos;
    private LinearLayout stat_layout;
    private Button btn_stat;
    private boolean isStatLayoutDisplayed=false;
    private LinearLayout address_layout;
    private LinearLayout closest_layout;

    public Fr_Map(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_map, container, false);
        if (view!=null)
        {
            map =  (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
            btn_todos = (TextView) view.findViewById(R.id.map_btn_todos);
            btn_cinco = (TextView) view.findViewById(R.id.map_btn_cinco);
            btn_diez = (TextView) view.findViewById(R.id.map_btn_diez);
            btn_cerca = (TextView) view.findViewById(R.id.map_btn_cerca);
            btn_ver = (Button) view.findViewById(R.id.map_btn_ver);
            btn_stat = (Button) view.findViewById(R.id.map_btn_stat);
            map_address = (TextView) view.findViewById(R.id.map_address);
            stat_homicidios = (TextView) view.findViewById(R.id.map_stat_homicidios);
            stat_asaltos = (TextView) view.findViewById(R.id.map_stat_asaltos);
            stat_accidentes = (TextView) view.findViewById(R.id.map_stat_accidentes);
            stat_layout = (LinearLayout) view.findViewById(R.id.map_stat_layout);
            address_layout = (LinearLayout) view.findViewById(R.id.map_address_layout);
            closest_layout = (LinearLayout) view.findViewById(R.id.map_closest_layout);
            map.getMapAsync(this);
            btn_ver.setOnClickListener(this);
            btn_todos.setOnClickListener(this);
            btn_cerca.setOnClickListener(this);
            btn_cinco.setOnClickListener(this);
            btn_diez.setOnClickListener(this);
            btn_stat.setOnClickListener(this);

            tv_closests = (TextView) view.findViewById(R.id.map_closest);
        }
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lastLocation = new Location("Initial");
        lastLocation.setLatitude(0);
        lastLocation.setLongitude(0);
        btn_stat.setEnabled(false);
        btn_ver.setEnabled(false);
        btn_cinco.setSelected(true);
        showMessage("Obteniendo ubicación");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationChangeListener(this);
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setZoomGesturesEnabled(true);
        populateMapWithUpcs();
    }

    private void populateMapWithUpcs(){

        Task_GetUpcs task_upcs = new Task_GetUpcs();
        task_upcs.execute(getActivity());

    }

    @Override
    public void onClick(View v) {
        if (v==btn_ver || v==btn_cerca)
        {
            setSelectedButton(btn_cerca);
            closest_layout.setVisibility(View.VISIBLE);
            clearMap();
            addSingleMarker(nearestUpc);
        }
        else if (v==btn_cinco)
        {
            //if (isMapPopulated) {
            closest_layout.setVisibility(View.GONE);
            setSelectedButton(btn_cinco);
                showUpcs(UPC_FIVE_KM);
            //}
        }
        else if (v==btn_diez)
        {
            //if (isMapPopulated) {
            setSelectedButton(btn_diez);
            closest_layout.setVisibility(View.GONE);
                showUpcs(UPC_TEN_KM);
            //}
        }
        else if (v==btn_todos)
        {
           // if (isMapPopulated) {
            setSelectedButton(btn_todos);
            closest_layout.setVisibility(View.GONE);
                showUpcs(UPC_ALL);
           // }
        }
        else if (v==btn_stat)
        {
            if (!isStatLayoutDisplayed) {
                showStats();
            } else {
                hideStats();
            }
        }
    }

    private void setSelectedButton(TextView btn) {
        this.btn_cinco.setSelected(false);
        this.btn_diez.setSelected(false);
        this.btn_todos.setSelected(false);
        this.btn_cerca.setSelected(false);
        btn.setSelected(true);
    }

    private void showStats() {
        isStatLayoutDisplayed = true;
        stat_layout.setVisibility(View.VISIBLE);
    }

    private void hideStats() {
        isStatLayoutDisplayed = false;
        stat_layout.setVisibility(View.INVISIBLE);
    }

    private void clearMap() {
        if (mapa!=null && isMapPopulated) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mapa.clear();
                }
            });
        }
    }

    private void showUpcs(int upcKm) {
        Task_ShowUpcs task = new Task_ShowUpcs();
        task.execute(upcKm);
    }

    private void addSingleMarker(DB_Upc upc) {
        if (upc!=null) {
            addMarker(upc);
            centerMapIn(upc.getLocation());
        }
    }

    private class Task_GetUpcs extends AsyncTask<Context, Void, ArrayList<DB_Upc>>{


        @Override
        protected ArrayList<DB_Upc> doInBackground(Context... params) {
            ArrayList<DB_Upc> upeces = new ArrayList<>();
            Context ctx = params[0];
            try {
                DB_Helper dbHelper = new DB_Helper(ctx);
                upeces = dbHelper.getAllUpcs();
            } catch (SQLiteException e){}
            return upeces;
        }

        @Override
        protected void onPostExecute(ArrayList<DB_Upc> db_upcs) {
            super.onPostExecute(db_upcs);

            if (db_upcs!=null && db_upcs.size()>0)
            {
                upcs = db_upcs;

                calculateDistances task = new calculateDistances();
                task.execute();
            }
        }
    }

    private class Task_ShowUpcs extends AsyncTask<Integer, Void, Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            int mode = params[0];
            if (upcs!=null && upcs.size()>0) {
                clearMap();
                switch (mode){
                    case UPC_ALL:
                    {
                        for (DB_Upc upc : upcs) {
                            addMarker(upc);
                            isMapPopulated=true;
                        }

                        break;
                    }
                    case UPC_FIVE_KM:
                    case UPC_TEN_KM:
                    {
                        if (distances!=null && distances.size()>0) {
                            for (int i = 0; i < distances.size(); i++) {
                                float distance = distances.get(i);
                                int treshold = ((mode == UPC_FIVE_KM) ? TRESHOLD_5KM : TRESHOLD_10KM);
                                if (distance <= treshold) {
                                    DB_Upc upc = upcs.get(i);
                                    if (upc != null) {
                                        addMarker(upc);
                                        isMapPopulated = true;
                                    }

                                }
                            }
                        }
                        break;
                    }
                }
            }
            return null;
        }
    }

    private void addMarker(final DB_Upc upc) {
        if (upc!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mapa.addMarker(new MarkerOptions()
                            .title(upc.getNombre())
                            .position(new LatLng(upc.getLatitude(), upc.getLongitude())));
                }
            });
        }

    }

    private void showMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    private class calculateDistances extends AsyncTask<Location, Void, Void>{

        @Override
        protected Void doInBackground(Location... params) {
            try {
                float minDistance = 1000000000;
                currentLocation = params[0];

                if (currentLocation!=null && upcs != null && upcs.size() > 0) {
                    if (cont==0) {
                        centerMapIn(currentLocation);
                        cont++;
                    }
                    distances = new ArrayList<>();
                    for (DB_Upc upc : upcs) {
                        Location loc = new Location(LocationManager.GPS_PROVIDER);
                        loc.setLatitude(upc.getLatitude());
                        loc.setLongitude(upc.getLongitude());
                        float dist = currentLocation.distanceTo(loc);
                        distances.add(dist);
                        //minDistance = (dist<minDistance) ? dist : minDistance;
                    }

                }
            } catch (Exception e)
            {
                Log.e("LOCATION ERROR", "Error: "+e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!isMapPopulated){
                Task_ShowUpcs task = new Task_ShowUpcs();
                task.execute(TRESHOLD_5KM);
            }

            calculateNearest task = new calculateNearest();
            task.execute();
        }
    }


    private class calculateNearest extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (distances!=null) {
                showMessage("Calculando el UPC más cercano");
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            int pos = 0;
            if (distances!=null && distances.size()>0)
            {
                for (float d:distances)
                {
                    if (d<minDistance)
                    {
                        minDistance = d;
                        pos = distances.indexOf(d);
                    }

                }

                nearestUpc = upcs.get(pos);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (nearestUpc!=null) {
                setNearestText("UPC más cercano: " + nearestUpc.getNombre());
            }
        }
    }

    private void setNearestText(String text) {
        tv_closests.setText(text);
    }

    @Override
    public void onMyLocationChange(Location location) {
        if (lastLocation.getLatitude()==0 && lastLocation.getLongitude()==0 && location!=null)
        {
            centerMapIn(location);
        }

        if (lastLocation.distanceTo(location)>50) {
            lastLocation = location;
            calculateDistances task = new calculateDistances();
            task.execute(location);
        }

        if (lastLocation.distanceTo(location)>=10)
        {
            doReverseGeocoding(location);
        }

        if (getActivity()!=null) {
            ((Act_Main) getActivity()).setCurrentLocation(location);
        }
    }

    private void doReverseGeocoding(Location location) {
        Task_ReverseGeocoding task = new Task_ReverseGeocoding();
        task.execute(location);
    }

    private class Task_ReverseGeocoding extends AsyncTask<Location, Void, DB_GeocodingResponse>
    {

        @Override
        protected DB_GeocodingResponse doInBackground(Location... params) {
            DB_GeocodingResponse response = null;
            Address address = null;
            Location location = params[0];
            try {
                Geocoder gc = new Geocoder(getActivity());
                List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses!=null && addresses.size()>0)
                {
                    address = addresses.get(0);
                    if (getActivity()!=null) {
                        ((Act_Main) getActivity()).setCurrentAddress(address);
                    }
                    response = new DB_GeocodingResponse();
                    response.setAddress(address);
                    String locality = address.getLocality();
                    String sublocality = address.getSubLocality();

                    StringBuilder sb = new StringBuilder();
                    if (locality!=null && (locality.equalsIgnoreCase("QUITO") || locality.equalsIgnoreCase("DISTRITO METROPOLITANO DE QUITO")) && sublocality!=null)
                    {
                        //CIUDAD=QUITO
                        String zona = Helper.getZonaAdministrativa(sublocality);
                        //String zona = Helper.getZonaAdministrativa("GUAYLLABAMBA");
                        if (zona!=null)
                        {
                            DB_Helper dbHelper = new DB_Helper(getActivity());
                            ArrayList<DB_Stat> statistics = dbHelper.getAllStats();
                            DB_Stat stat = dbHelper.getStatBySector("QUITO",zona);
                            dbHelper.close();
                            if (stat!=null)
                            {
                                response.setStat(stat);
                            }
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(DB_GeocodingResponse response) {
            super.onPostExecute(response);
            if (response!=null)
            {
                try{
                    Address address = response.getAddress();
                    DB_Stat stat = response.getStat();
                    StringBuilder direccion = new StringBuilder();

                    if (address!=null) {
                        String thor = address.getThoroughfare();
                        String locality = address.getLocality();
                        String sublocality = address.getSubLocality();
                        String feature = address.getFeatureName();

                        if (thor != null) {
                            //CALLE
                            direccion.append("t:" + thor.toUpperCase()).append("-");
                        }
                        if (sublocality != null) {
                            //BARRIO O SECTOR
                            direccion.append("sl:" + sublocality.toUpperCase()).append("-");
                        }
                        if (feature != null) {
                            //ZONA O DIRECCION
                            direccion.append("f:" + feature.toUpperCase()).append("-");
                        }
                        if (locality != null) {
                            //CIUDAD
                            direccion.append("l:" + locality.toUpperCase()).append("-");
                        }
                    }

                    if (stat!=null)
                    {
                        currentStat = stat;
                        address_layout.setVisibility(View.VISIBLE);
                        btn_stat.setEnabled(true);
                        populateStatsAndAddress();
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                btn_stat.setEnabled(false);
                map_address.setText("Zona: No Disponible");
            }
        }
    }

    private void populateStatsAndAddress() {
        if (currentStat!=null)
        {
            map_address.setText("Zona: " +currentStat.getSector());

            stat_accidentes.setText("Muertes Accidentales: " + currentStat.getAccidentes2014());
            stat_accidentes.setBackgroundColor(Helper.getColorForStat(DB_Stat.ACCIDENTE, currentStat.getAccidentes2014()));
            stat_homicidios.setText("Homicidios: " +currentStat.getHomicidios2014());
            stat_homicidios.setBackgroundColor(Helper.getColorForStat(DB_Stat.HOMICIDIO, currentStat.getHomicidios2014()));
            stat_asaltos.setText("Asaltos/Robos: " +currentStat.getAsaltos2014());
            stat_asaltos.setBackgroundColor(Helper.getColorForStat(DB_Stat.ASALTO_ROBO, currentStat.getAsaltos2014()));

        }

    }

    private void centerMapIn(final Location loc)
    {
        if (loc!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 14));
                }
            });
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapa!=null) {
            mapa.setOnMyLocationChangeListener(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapa!=null)
        {
            mapa.setOnMyLocationChangeListener(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment fr = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        if (fr != null) {
            getActivity().getFragmentManager().beginTransaction().remove(fr).commit();
        }
    }
}
