package com.example.triviathor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.Facebook.DialogListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static String APP_ID = "330955550366348";

	// Instance of Facebook Class
	private Facebook facebook = new Facebook(APP_ID);
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	
	// Buttons and Android widgets
	Button btn_logIn;
	Button btn_exit;
	Button btn_singlePlayer;
	Button btn_multiPlayer;
	Button btn_options;
	Button btn_topScores;
	TextView txv_welcome;
	TextView txv_connect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Facebook
		mAsyncRunner = new AsyncFacebookRunner(facebook);
	
		//Buttons
		btn_logIn=(Button)findViewById(R.id.btn_login);
		btn_exit=(Button)findViewById(R.id.btn_logOut);
		btn_singlePlayer=(Button)findViewById(R.id.btn_singlePlayer);
		btn_multiPlayer=(Button)findViewById(R.id.btn_multiplayer);
		btn_options=(Button)findViewById(R.id.btn_options);
		btn_topScores=(Button)findViewById(R.id.btn_topScores);
		txv_welcome=(TextView)findViewById(R.id.txv_welcome);
		txv_connect=(TextView)findViewById(R.id.textView1);
		
		btn_logIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Image Button", "button Clicked");
				loginToFacebook();
			}
		});
		btn_exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Image Button", "button Clicked");
				logoutFacebook();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void loginToFacebook() {

		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
			
			getProfileInformation();
			btn_logIn.setVisibility(View.INVISIBLE);
			txv_connect.setVisibility(View.INVISIBLE);
			btn_exit.setVisibility(View.VISIBLE);
			btn_multiPlayer.setVisibility(View.VISIBLE);
			btn_options.setVisibility(View.VISIBLE);
			btn_singlePlayer.setVisibility(View.VISIBLE);
			btn_topScores.setVisibility(View.VISIBLE);
			txv_welcome.setVisibility(View.VISIBLE);
			
			

			Log.d("FB Sessions", "" + facebook.isSessionValid());
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();

							btn_logIn.setVisibility(View.INVISIBLE);
							txv_connect.setVisibility(View.INVISIBLE);
							btn_exit.setVisibility(View.VISIBLE);
							btn_multiPlayer.setVisibility(View.VISIBLE);
							btn_options.setVisibility(View.VISIBLE);
							btn_singlePlayer.setVisibility(View.VISIBLE);
							btn_topScores.setVisibility(View.VISIBLE);
							txv_welcome.setVisibility(View.VISIBLE);
							getProfileInformation();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}
	public void getProfileInformation() {
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					
					JSONObject profile = new JSONObject(json);
					// Facebook profile information
					final String name = profile.getString("name");
					final String email = profile.getString("email");
					final String ps = profile.toString();
					
					
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							txv_welcome.setText("Welcome, "+ name);
						}

					});

					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}
	public void logoutFacebook(){
		new Thread(){
            public void run() {
                try {
                    facebook.logout(MainActivity.this);
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
            }.start();
            
           this.finish();
    		
	}
		
}
