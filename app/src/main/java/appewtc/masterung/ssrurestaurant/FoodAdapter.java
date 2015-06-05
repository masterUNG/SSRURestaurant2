package appewtc.masterung.ssrurestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by masterUNG on 6/5/15 AD.
 */
public class FoodAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] foodStrings, priceStrings;
    private int[] imageFoodInts;

    public FoodAdapter(Context context, String[] foodStrings, String[] priceStrings, int[] imageFoodInts) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
        this.imageFoodInts = imageFoodInts;
    }   // Constructor

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView1 = objLayoutInflater.inflate(R.layout.listview_rom, viewGroup, false);

        //Show Food
        TextView listFoodTextView = (TextView) myView1.findViewById(R.id.txtShowFood);
        listFoodTextView.setText(foodStrings[i]);

        //Show Price
        TextView listPriceTextView = (TextView) myView1.findViewById(R.id.txtShowPrice);
        listPriceTextView.setText(priceStrings[i]);

        //Show Image
        ImageView listImageFoodImageView = (ImageView) myView1.findViewById(R.id.imvFood);
        listImageFoodImageView.setBackgroundResource(imageFoodInts[i]);

        return myView1;
    }   // getView

}   // Main Class
