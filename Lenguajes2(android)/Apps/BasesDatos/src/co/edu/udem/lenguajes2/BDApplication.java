package co.edu.udem.lenguajes2;

import android.app.Application;
import android.content.Intent;
import android.util.Log;


public class BDApplication extends Application {
	
		ApplicationDataContext appDataContext;
		
		@Override
		public void onCreate() {
			super.onCreate();
		}
	
		public ApplicationDataContext getApplicationDataContext(){//Clase Singleton que obtiene un objeto global de bases de datos
			
			if (appDataContext == null) {
	            try {
	                appDataContext = new ApplicationDataContext(this);
	            } catch (Exception e) {
	                Log.e("Eaglesync", "Error inicializando ApplicationDataContext: " + e.getMessage());
	            }
	        }
	        return appDataContext;
		}

}
