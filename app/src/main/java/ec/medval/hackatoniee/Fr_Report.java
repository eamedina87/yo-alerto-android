package ec.medval.hackatoniee;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fr_Report extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener, View.OnClickListener {

    private TextView btn_transito;
    private TextView btn_bombero;
    private TextView btn_medico;
    private TextView btn_policia;
    private EditText provincia;
    private EditText ciudad;
    private EditText sector;
    private EditText direccion;
    private ArrayList<String> eventos;
    private ImageView btn_next;
    private ImageView btn_previous;
    private String currentEvento;
    private TextView btn_evento;
    private Button btn_enviar;
    private GoogleMap mapa;
    private Location lastLocation;
    private MapFragment map;
    private Address currentAddress;
    private Location currentLocation;


    public Fr_Report(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_report, container, false);
        if (v!=null) {
            map =  (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.report_map);
            btn_policia = (TextView) v.findViewById(R.id.report_btn_policia);
            btn_medico = (TextView) v.findViewById(R.id.report_btn_medico);
            btn_bombero = (TextView) v.findViewById(R.id.report_btn_bombero);
            btn_transito = (TextView) v.findViewById(R.id.report_btn_transito);
            btn_evento = (TextView) v.findViewById(R.id.report_btn_evento);
            btn_next = (ImageView) v.findViewById(R.id.report_btn_next);
            btn_previous = (ImageView) v.findViewById(R.id.report_btn_previous);
            btn_enviar = (Button) v.findViewById(R.id.report_btn_enviar);
            provincia = (EditText) v.findViewById(R.id.report_provincia);
            ciudad = (EditText) v.findViewById(R.id.report_ciudad);
            sector = (EditText) v.findViewById(R.id.report_sector);
            direccion = (EditText) v.findViewById(R.id.report_direccion);
            btn_policia.setOnClickListener(this);
            btn_medico.setOnClickListener(this);
            btn_bombero.setOnClickListener(this);
            btn_transito.setOnClickListener(this);
            btn_previous.setOnClickListener(this);
            btn_next.setOnClickListener(this);
            btn_enviar.setOnClickListener(this);
            map.getMapAsync(this);
        }

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setSelectedButton(btn_policia);
        setEventList(Helper.getEventosPolicia());
        lastLocation = new Location("");
        lastLocation.setLatitude(0);
        lastLocation.setLongitude(0);
        showMessage("Obteniendo ubicación");
        if (getActivity()!=null) {
            setCurrentAddress(((Act_Main) getActivity()).getCurrentAddress());
            setCurrentLocation(((Act_Main) getActivity()).getCurrentLocation());
        }

    }

    private void setCurrentLocation(Location loc) {
        if (loc!=null)
        {
            this.currentLocation = loc;
           /* if (mapa!=null)
            {
                mapa.clear();
                mapa.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude())));
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 14));
            }
            */
        }
    }

    private void setSelectedButton(TextView btn) {
        this.btn_policia.setSelected(false);
        this.btn_transito.setSelected(false);
        this.btn_medico.setSelected(false);
        this.btn_bombero.setSelected(false);
        btn.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_policia)
        {
            setSelectedButton(btn_policia);
            setEventList(Helper.getEventosPolicia());
        }
        else if (v==btn_bombero)
        {
            setSelectedButton(btn_bombero);
            setEventList(Helper.getEventosBombero());
        }
        else if (v==btn_medico)
        {
            setSelectedButton(btn_medico);
            setEventList(Helper.getEventosMedico());
        }
        else if (v==btn_transito)
        {
            setSelectedButton(btn_transito);
            setEventList(Helper.getEventosTransito());
        }
        else if (v==btn_previous)
        {
            showPreviousEvento();
        }
        else if (v==btn_next)
        {
            showNextEvento();
        }
        else if (v==btn_enviar)
        {
            enviarReporte();
        }
    }

    private void enviarReporte() {
        if (isNotEmpty(provincia) && isNotEmpty(ciudad) && isNotEmpty(sector) && isNotEmpty(direccion))
        {
            showMessage("Información Enviada");
            clearTexts();
        }
        else
        {
            showMessage("Complete toda la información");
        }
    }

    private void clearTexts() {
        provincia.setText("");
        ciudad.setText("");
        direccion.setText("");
        sector.setText("");
    }

    private void showMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }

    private boolean isNotEmpty(EditText et)
    {
        return et.getText().toString().length()>0;
    }

    private void showPreviousEvento() {
        if (currentEvento!=null && eventos!=null && eventos.size()>0) {
            int pos = eventos.indexOf(currentEvento);
            if (pos -1 < 0)
            {
                setCurrentEvento(eventos.get(eventos.size()-1));
            }
            else
            {
                setCurrentEvento(eventos.get(pos-1));
            }
        }
    }

    private void showNextEvento() {
        if (currentEvento!=null && eventos!=null && eventos.size()>0) {
            int pos = eventos.indexOf(currentEvento);
            if (pos+1 >= eventos.size())
            {
                setCurrentEvento(eventos.get(0));
            }
            else
            {
                setCurrentEvento(eventos.get(pos+1));
            }
        }
    }

    private void setEventList(ArrayList<String> eventos) {
        this.eventos = eventos;
        setCurrentEvento(eventos.get(0));

    }

    private void setCurrentEvento(String ev) {
        if (ev!=null) {
            this.currentEvento = ev;
            btn_evento.setText(ev);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationChangeListener(this);
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.getUiSettings().setZoomGesturesEnabled(true);

    }

    @Override
    public void onMyLocationChange(Location location) {
        if (lastLocation.distanceTo(location)>=10)
        {
            doReverseGeocoding(location);
        }
    }

    private void doReverseGeocoding(Location location) {
        Task_ReverseGeocoding task = new Task_ReverseGeocoding();
        task.execute(location);
    }

    private class Task_ReverseGeocoding extends AsyncTask<Location, Void, Address>
    {

        @Override
        protected Address doInBackground(Location... params) {
            Address address = null;
            Location location = params[0];
            try {
                Geocoder gc = new Geocoder(getActivity());
                List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses!=null && addresses.size()>0)
                {
                    address = addresses.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return address;
        }

        @Override
        protected void onPostExecute(Address result) {
            super.onPostExecute(result);
            if (result!=null)
            {
                setCurrentAddress(result);

            }
        }
    }

    private void setCurrentAddress(Address result) {
        if (result!=null) {
            this.currentAddress = result;
            try {
                String admin = result.getAdminArea();
                String locality = result.getLocality();
                String sublocality = result.getSubLocality();
                String feature = result.getFeatureName();
                String thor = result.getThoroughfare();

                if (thor != null) {
                    //CALLE
                    direccion.setText(thor.toUpperCase());
                }
                if (feature != null) {
                    //ZONA O DIRECCION
                    direccion.setText(feature.toUpperCase());
                }
                if (sublocality != null) {
                    //BARRIO O SECTOR
                    sector.setText(sublocality.toUpperCase());
                }
                if (locality != null) {
                    //CIUDAD
                    ciudad.setText(locality.toUpperCase());
                }
                if (admin != null) {
                    provincia.setText(admin.toUpperCase());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onDestroyView() {
        super.onDestroyView();
        MapFragment fr = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.report_map);
        if (fr != null) {
            getActivity().getFragmentManager().beginTransaction().remove(fr).commit();
        }
    }
}
