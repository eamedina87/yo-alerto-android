package ec.medval.hackatoniee;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Fr_Configuration extends Fragment implements OnClickListener {

	private static final int REQUEST_CONTACT_NUMBER = 0;
	private TextView tv_contact_1;
	private TextView tv_contact_2;
	private TextView tv_contact_3;
	private int contact_order=-1;
	
	private Activity context;
	
	public Fr_Configuration(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fr_config, container, false);
		
//		TextView tv_user = (TextView)view.findViewById(R.id.main_user);
		
//		tv_contact_1 = (TextView)view.findViewById(R.id.main_contact_1);
//		tv_contact_2 = (TextView)view.findViewById(R.id.main_contact_2);
//		tv_contact_3 = (TextView)view.findViewById(R.id.main_contact_3);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		context = getActivity();
		
//		tv_contact_1.setOnClickListener(this);
//		tv_contact_1.setId(1);
//		tv_contact_2.setOnClickListener(this);
//		tv_contact_2.setId(2);
//		tv_contact_3.setOnClickListener(this);
//		tv_contact_3.setId(3);
		
//		tv_user.setText("Bienvenido " + getUserName() + "!");
//		tv_contact_1.setText(getContactData(tv_contact_1.getId()).length()>0?getContactData(tv_contact_1.getId()):"Contacto");
//		tv_contact_2.setText(getContactData(tv_contact_2.getId()).length()>0?getContactData(tv_contact_2.getId()):"Contacto");
//		tv_contact_3.setText(getContactData(tv_contact_3.getId()).length()>0?getContactData(tv_contact_3.getId()):"Contacto");
		
		super.onActivityCreated(savedInstanceState);
	}

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void showToast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getClass()==TextView.class){
			contact_order=v.getId();
			selectContact();
		}
	}
	
	private void selectContact(){
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, REQUEST_CONTACT_NUMBER);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == Activity.RESULT_OK) {
            if(data != null && requestCode == REQUEST_CONTACT_NUMBER) {  
                Uri uriOfPhoneNumberRecord = data.getData();
                String idOfPhoneRecord = uriOfPhoneNumberRecord.getLastPathSegment();
                Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.DISPLAY_NAME}, Phone._ID + "=?", new String[]{idOfPhoneRecord}, null);
                if(cursor != null) {
                        if(cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            String number = cursor.getString( cursor.getColumnIndex(Phone.NUMBER) );
                            number = PhoneNumberUtils.formatNumber(number);
                            String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                            if (contact_order==1){
                            	tv_contact_1.setText(name+";"+number);
                            	 setContactData("contact_1", name+";"+number);
                            } else if (contact_order==2){
                            	tv_contact_2.setText(name+";"+number);
                            	setContactData("contact_2", name+";"+number);
                            } else if (contact_order==3){
                            	tv_contact_3.setText(name+";"+number);
                            	setContactData("contact_3", name+";"+number);
                            }
                        }
                        cursor.close();
                }
            }
            else {
                Log.w("TestActivity", "WARNING: Corrupted request response");
            }
        }
        
    }

	protected String getUserName(){
		return getStringPref("username");
	}
	
	protected void setContactData(String orden, String data){
		setStringPref(orden, data);	
	}
	
	protected String getContactData(int orden){
		return getStringPref(String.valueOf(orden));
	}

	protected void setStringPref(String name, String value) {
		SharedPreferences settings = context.getSharedPreferences("SETTINGS",0);
		Editor editor = settings.edit();
		editor.putString(name, value);
		editor.commit();
	}
	
	protected String getStringPref(String name) {
		SharedPreferences settings = context.getSharedPreferences("SETTINGS", 0);
		return settings.getString(name,"default");
	}
	
	
	

}
