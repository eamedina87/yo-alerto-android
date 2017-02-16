package ec.medval.hackatoniee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Act_Login extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ImageView btn_fb = (ImageView)findViewById(R.id.login_fb);
		btn_fb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  loginWithFb();
			}
		});
		
		if (!getUserName().equals("default")){
			openMainACtivity();
		}
	}

	protected void loginWithFb() {
		// TODO Auto-generated method stub
	/*	Session.openActiveSession(this, true, new Session.StatusCallback() {

		    // callback when session changes state
		    @Override
		    public void call(Session session, SessionState state, Exception exception) {
		    	if (session.isOpened()) {
		    		Request.newMeRequest(session, new Request.GraphUserCallback() {

		    			  // callback after Graph API response with user object
		    			  @Override
		    			  public void onCompleted(GraphUser user, Response response) {
		    				  
		    				  if (user != null) {
		    					  setUserName(user.getName());
		    					  openMainACtivity();
		    					}

						
		    			  }
		    			}).executeAsync();
		    	}
		    }
		  });
	*/
	}
	
	private void openMainACtivity() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, Fr_Configuration.class));
		this.finish();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
}
