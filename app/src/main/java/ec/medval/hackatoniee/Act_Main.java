package ec.medval.hackatoniee;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class Act_Main extends BaseActivity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private static Address currentAddress;
    private Cust_MainMenuOption btn_panic;
	private Cust_MainMenuOption btn_report;
	private Cust_MainMenuOption btn_map;
	private Cust_MainMenuOption btn_config;
    private Context context;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private boolean isRequestingLocationUpdates;
    private boolean isMapFragmentActive=false;
    private static Location currentLocation;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        context = this;
		setContentView(R.layout.main);

		btn_report = (Cust_MainMenuOption)findViewById(R.id.mm_option2);
		btn_map = (Cust_MainMenuOption)findViewById(R.id.mm_option3);
		//btn_config = (Cust_MainMenuOption)findViewById(R.id.mm_option4);

        btn_report.setImageDrawable(getResources().getDrawable(R.drawable.btn_report));
        btn_report.setTitleString("Reporte");

        btn_map.setImageDrawable(getResources().getDrawable(R.drawable.btn_map));
        btn_map.setTitleString(getResources().getString(R.string.mm_option3));

        //btn_config.setImageDrawable(getResources().getDrawable(R.drawable.btn_config));
        //btn_config.setTitleString(getResources().getString(R.string.mm_option4));

		btn_report.setOnClickListener(this);
		btn_map.setOnClickListener(this);
		//btn_config.setOnClickListener(this);
		
		if (findViewById(R.id.main_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            Fr_Map fragment = new Fr_Map();
            getSupportFragmentManager().beginTransaction()
                  .add(R.id.main_container, fragment).commit();
            isMapFragmentActive = true;
            setSelectedButton(btn_map);
		}

		if (!isDBCreated())
        {
            createDatabase();
        }

       // checkPlayServices();
	}

    private void createDatabase() {
        AsyncTask<Void, Void, Void> dbcreation = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DB_Helper dbh = new DB_Helper(context);
                dbh.close();
                setDBCreated();
                return null;
            }
        }.execute();

    }

    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v==btn_report){
			showReport();
            setSelectedButton(btn_report);
		} else if (v==btn_map){
			showMap();
            setSelectedButton(btn_map);
		}
	}


    private void setSelectedButton(Cust_MainMenuOption btn) {
        btn_map.setSelected(false);
        btn_report.setSelected(false);
        btn.setSelected(true);
    }

	private void showConfig(){
		Fr_Configuration newFragment = new Fr_Configuration();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.main_container, newFragment);
	//	transaction.addToBackStack(null);
		// Commit the transaction
		transaction.commit();
	}

    private void showReport(){
        if (isMapFragmentActive) {
            Fr_Report newFragment = new Fr_Report();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, newFragment);
            transaction.commit();
            isMapFragmentActive=false;
        }
    }

    private void showMap(){
        if (!isMapFragmentActive) {
            Fr_Map newFragment = new Fr_Map();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, newFragment);
            transaction.commit();
            isMapFragmentActive=true;
        }
    }

    private void checkPlayServices()
    {
        if (isGooglePlayServicesAvailable())
        {
            buildGoogleApiClient();
        }
        else
        {
            Toast.makeText(this, "GooglePlayServices no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        createLocationRequest();

    }

    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }



    protected void startLocationUpdates() {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
        isRequestingLocationUpdates = true;
    }


    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected() && !isRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient!=null && !mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
*/
    @Override
    public void onConnected(Bundle bundle) {
        if (isMapFragmentActive && !isRequestingLocationUpdates)
        {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if (location!=null) {
            this.currentLocation = location;

        }

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    public void setCurrentLocation(Location loc){
        this.currentLocation = loc;
    }

    public static Location getCurrentLocation(){
        return currentLocation;
    }

    public void setCurrentAddress(Address ad)
    {
        this.currentAddress = ad;
    }

    public static Address getCurrentAddress(){
        return currentAddress;
    }

	
}
