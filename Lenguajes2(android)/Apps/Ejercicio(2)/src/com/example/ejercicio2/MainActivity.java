package com.example.ejercicio2;

import android.R.integer;
import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button btn;
	Spinner spn;
	TextView txtSalida;
	EditText txtEntrada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn=(Button)findViewById(R.id.btnEjecutar);
		btn.setOnClickListener(MainActivity.this);
		
		txtEntrada=(EditText)findViewById(R.id.edtEntrada);
		txtSalida=(TextView)findViewById(R.id.txtSalida);
		
		spn=(Spinner)findViewById(R.id.spn);
		String[] opciones= new String[]{"Binario","Rotar Vector","Capicua","Vector aleatorio","Quiz"};
		ArrayAdapter<String>adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
		spn.setAdapter(adaptador);
		
	}

	@Override
	public void onClick(View v) {
		try {
			int num=Integer.parseInt(txtEntrada.getText().toString());
			switch (spn.getSelectedItemPosition()) {
			case 0:
                conversor(num);
				break;
	        case 1:
				
				break;
	        case 2:
	        	capicua(num);
	        	break;

			default:
				break;
			}
			
		} catch (Exception e) {
			Toast.makeText(this, ".:El valor no es válido:.", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void conversor(int num){
		String acum = "";
		
		while(num/2 >= 1){
			acum = String.valueOf(num%2) + acum;
			num = num/2;
		}
		acum = String.valueOf(num) + acum;
		txtSalida.setText(acum);
	}

	public void capicua(int num){
		String text= String.valueOf(num);
		if(capicuaB(num,text)){
			txtSalida.setText("Es capicua: "+text);
		}else{
			String inverso="";
			for (int i = text.length(); i >=1 ; i--) {
				inverso=inverso+text.substring(i-1, i);
			}
			int inver=Integer.parseInt(inverso);
			capicua(num+inver);
		}
		
	}
	public boolean capicuaB(int num, String text){
		int cont = text.length();
		for (int i = 0; i < text.length()/2; i++) {
			if(text.substring(i,i+1).equals(text.substring(cont-1,cont)))
				cont--;
			else
				return false;
		}
		return true;
	}
	

}
