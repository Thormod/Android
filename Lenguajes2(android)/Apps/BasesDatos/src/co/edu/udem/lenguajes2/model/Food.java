package co.edu.udem.lenguajes2.model;


import com.mobandme.ada.annotations.TableField;
import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;

@Table(name = "Food")//Le decimos al framework que esta clase es una tabla
public class Food  extends Entity{//Clase de tipo Entity adiciona métodos para manipular la clase como un registro de la tabla

	@TableField(name = "drawableID", datatype = DATATYPE_INTEGER, required = true)//Nombre del campo, tipo de datos(Entero), campo requerido
	private int drawableId;
	@TableField(name = "title", datatype = DATATYPE_TEXT, required = true)
	private String title;
	@TableField(name = "description", datatype = DATATYPE_TEXT, required = true)
	private String description;
	@TableField(name = "price", datatype = DATATYPE_INTEGER, required = true)
	private int price;
	
	public int getDrawableId() {
		return drawableId;
	}
	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
