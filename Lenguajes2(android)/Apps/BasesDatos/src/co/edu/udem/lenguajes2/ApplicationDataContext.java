package co.edu.udem.lenguajes2;

import co.edu.udem.lenguajes2.model.Food;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;

import android.content.Context;


public class ApplicationDataContext extends ObjectContext {//Objeto manejador de Bases de datos

	public ObjectSet<Food> foodDao;

	public ApplicationDataContext(Context pContext) throws Exception  {
		super(pContext);
		this.foodDao = new ObjectSet<Food>(Food.class, this);//Declarar acá todos los modelos de las tablas
	}
}
