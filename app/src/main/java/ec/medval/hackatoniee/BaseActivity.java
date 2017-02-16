package ec.medval.hackatoniee;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {


    protected boolean isDBCreated(){
        return getBooleanPref("dbcreated");
    }

    protected void setDBCreated(){
        setBooleanPref("dbcreated", true);
    }

	protected void setUserName(String user){
		setStringPref("username", user);	
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
		SharedPreferences settings = getSharedPreferences("SETTINGS",0);
		Editor editor = settings.edit();
		editor.putString(name, value);
		editor.commit();
	}
	
	protected String getStringPref(String name) {
		SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
		return settings.getString(name,"default");
	}
	
	protected void setIntPref(String name, int value) {
		SharedPreferences settings = getSharedPreferences("SETTINGS",0);
		Editor editor = settings.edit();
		editor.putInt(name, value);
		editor.commit();
	}
	
	protected int getIntPref(String name) {
		SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
		return settings.getInt(name, 0);
	}

    protected void setBooleanPref(String name, boolean value) {
        SharedPreferences settings = getSharedPreferences("SETTINGS",0);
        Editor editor = settings.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    protected boolean getBooleanPref(String name) {
        SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
        return settings.getBoolean(name, false);
    }
}
