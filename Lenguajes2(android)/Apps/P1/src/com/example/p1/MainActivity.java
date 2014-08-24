package com.example.p1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import java.util.StringTokenizer;

public class MainActivity extends Activity implements OnClickListener, OnItemLongClickListener{

	ListView lstV;
	Button btnAgregar,btnCalcular,btnLimpiar;
	EditText edTxtNota, edTxtPorcentaje;
	TextView txtSalida,txtSalida2;
	double totalPorcentaje,totalNota;
	
	
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAgregar=(Button)findViewById(R.id.btnAgregar);
		btnAgregar.setOnClickListener(this);
		btnCalcular=(Button)findViewById(R.id.btnCalcular);
		btnCalcular.setOnClickListener(this);
		btnLimpiar=(Button)findViewById(R.id.btnLimpiar);
		btnLimpiar.setOnClickListener(this);
		
		txtSalida=(TextView)findViewById(R.id.txtSalida);
		txtSalida2=(TextView)findViewById(R.id.txtSalida2);
		
		lstV=(ListView)findViewById(R.id.lstV);
	    edTxtNota=(EditText)findViewById(R.id.edTxtNota);
	    edTxtPorcentaje=(EditText)findViewById(R.id.edTxPorcentaje);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);	
		lstV.setOnItemLongClickListener(this);
		edTxtNota.setText("");
		edTxtPorcentaje.setText("");
		totalPorcentaje=0;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==btnAgregar.getId()){
			if(!edTxtNota.getText().toString().equals("")){
				if(!edTxtPorcentaje.getText().toString().equals("")){	
					adapter.add("Nota: "+edTxtNota.getText().toString()+" Porcentaje: "+edTxtPorcentaje.getText().toString()+" %");
					lstV.setAdapter(adapter);
					edTxtNota.setText("");
					edTxtPorcentaje.setText("");
				}
				else{
					Toast.makeText(this, "Falta llenar algún campo de texto", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(this, "Falta llenar algún campo de texto", Toast.LENGTH_SHORT).show();
			}
			
		}
		if(v.getId()==btnCalcular.getId()){
			try {
				if(!adapter.isEmpty()){
					totalNota= 0;
					totalPorcentaje=0;
					for (int i = 0; i <adapter.getCount(); i++) {
						totalNota = totalNota + calcNota(adapter.getItem(i));
					}
					txtSalida.setText("Nota actual: "+totalNota+" Sobre el "+totalPorcentaje+"%");
					
				}else{
					Toast.makeText(this, "Lista vacía", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				Toast.makeText(this, "Posee algún valor incorrecto", Toast.LENGTH_SHORT).show();
			}
		}
		if(v.getId()==btnLimpiar.getId()){
			adapter.clear();
			lstV.setAdapter(adapter);
			txtSalida.setText("");
			txtSalida2.setText("");
		}
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
			adapter.remove(parent.getItemAtPosition(position).toString());
			lstV.setAdapter(adapter);
			txtSalida.setText("");
			txtSalida2.setText("");
			
		return false;
	}

	public double calcNota(String text){
		int cont=0;
		double nota=0, porcentaje=0;
		StringTokenizer token=new StringTokenizer(text);
		while(token.hasMoreTokens()){
			cont++;
			if(cont==2){
				nota=Double.parseDouble(token.nextToken());
			}
			if(cont==3){
				porcentaje=Double.parseDouble(token.nextToken());
				totalPorcentaje=totalPorcentaje+porcentaje;
				porcentaje=porcentaje/100;
			}
			token.nextToken();
			
		}
		if((nota*porcentaje)<3){
			txtSalida2.setText("Falta: "+(3-(nota*porcentaje))+" para ganar la materia");
		}else{
			txtSalida2.setText("");
		}
		return (nota*porcentaje);
	}
	
}
