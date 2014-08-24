package co.edu.udem.lenguajes2;

import com.mobandme.ada.Entity;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import co.edu.udem.lenguajes2.adapter.OptimizedFoodAdapter;
import co.edu.udem.lenguajes2.model.Food;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

public class MainActivity extends Activity {

	ApplicationDataContext appDataCtx;
	ObjectSet<Food> foodTable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BDApplication app = (BDApplication)getApplication();
		
		appDataCtx = app.getApplicationDataContext();
		foodTable = appDataCtx.foodDao; //Referencia al objeto Tabla
		
		fillFoodList();
		
		try {
		foodTable.fill();//Select * from Food
		
		//Select * from Food where price >= 5000 order by price
		/*
		String[] args = new String[] {"5000"};
		foodTable.fill("price>=?", args, "price");*/
		
		} catch (AdaFrameworkException e) {
			e.printStackTrace();
		}
		
		OptimizedFoodAdapter foodAdapter = new OptimizedFoodAdapter(this,  0 , foodTable);//Pasamos la tabla como fuente de datos
		
		ListView lvFood = (ListView)findViewById(R.id.lvFood);
		lvFood.setAdapter(foodAdapter);		
	}
	
	

	private void fillFoodList() {
		
		Food food = new Food();		
		food.setTitle("Hamburguesa");
		food.setDescription("Hamburguesa de res");
		food.setPrice(15000);
		food.setDrawableId(R.drawable.ic_launcher); //Cambiar por una imagen propia
		food.setStatus(Entity.STATUS_NEW);//Configurar el estado del registro, New para insertar
		
		try {
			foodTable.save(food); //Guardar el registro
		} catch (AdaFrameworkException e) {
			e.printStackTrace();
		}
		

		
		food = new Food();	
		food.setTitle("Chocolate Donut");
		food.setDescription("Rosquilla de Chocolate");
		food.setPrice(3000);
		food.setDrawableId(R.drawable.ic_launcher);//Cambiar por una imagen propia
		
		food.setStatus(Entity.STATUS_NEW);
		
		try {
			foodTable.save(food);
		} catch (AdaFrameworkException e) {
			e.printStackTrace();
		}
		

		
		food = new Food();	
		food.setTitle("Cherry pie");
		food.setDescription("Porción de Tarta de Cereza");
		food.setPrice(5000);
		food.setDrawableId(R.drawable.ic_launcher);//Cambiar por una imagen propia
		
		food.setStatus(Entity.STATUS_NEW);
		
		try {
			foodTable.save(food);
		} catch (AdaFrameworkException e) {
			e.printStackTrace();
		}

	}

}
