package ec.medval.hackatoniee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Receiver_OutgoingCall extends BroadcastReceiver {

	public Receiver_OutgoingCall(){
		
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		if (intent!=null && intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)!=null)
		{
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
	        
			if (number.equalsIgnoreCase("444")){
				SmsManager sms = SmsManager.getDefault();
				String text = "Emergencia";
				SharedPreferences settings = context.getSharedPreferences("SETTINGS", 0);
				String telefono = settings.getString("contact_1","");
				if (telefono.length()>0){
					telefono = ((String[])telefono.split(";"))[1];
					sms.sendTextMessage(telefono, null, text, null, null);
				}
				telefono = settings.getString("contact_2","");
				if (telefono.length()>0){
					telefono = ((String[])telefono.split(";"))[1];
					sms.sendTextMessage(telefono, null, text, null, null);
				}
				telefono = settings.getString("contact_3","");
				if (telefono.length()>0){
					telefono = ((String[])telefono.split(";"))[1];
					sms.sendTextMessage(telefono, null, text, null, null);
				}
				
			} else {
				Toast.makeText(context,
                        "No Emergencia: " + number,
                        Toast.LENGTH_LONG).show();
			}
		
		}
	}
}
