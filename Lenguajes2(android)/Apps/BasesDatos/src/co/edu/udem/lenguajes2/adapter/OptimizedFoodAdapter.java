package co.edu.udem.lenguajes2.adapter;

import java.util.ArrayList;

import co.edu.udem.lenguajes2.R;
import co.edu.udem.lenguajes2.model.Food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OptimizedFoodAdapter extends ArrayAdapter<Food> {

	ArrayList<Food> foodList;
	Context context;
	

	public OptimizedFoodAdapter(Context context, int textViewResourceId,
			ArrayList<Food> foodList) {
		super(context, textViewResourceId, foodList);
		this.foodList = foodList;
		this.context = context;
	}

    public static class ViewHolder{
        public TextView lblTitle;
        public TextView lblDescription;
        public TextView lblPrice;
        public ImageView imgFood; 
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		ViewHolder holder;
		
		if(v==null){
			final LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.food_item, null);
			holder = new ViewHolder();
			holder.lblTitle = (TextView) v.findViewById(R.id.lblTitle);
			holder.lblDescription = (TextView) v.findViewById(R.id.lblDescription);
			holder.lblPrice= (TextView) v.findViewById(R.id.lblPrice);
			holder.imgFood =  (ImageView) v.findViewById(R.id.imgFood);	
			v.setTag(holder);
		}else{
			holder = (ViewHolder)v.getTag();
		}

		final Food food_item = foodList.get(position);

		if (food_item != null) {
			holder.lblTitle.setText(food_item.getTitle());
			holder.lblDescription.setText(food_item.getDescription());
			holder.lblPrice.setText(String.valueOf(food_item.getPrice()));
			holder.imgFood.setImageResource(food_item.getDrawableId());
		}
		return v;
	}

}
