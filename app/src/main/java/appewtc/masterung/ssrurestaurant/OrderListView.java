package appewtc.masterung.ssrurestaurant;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OrderListView extends AppCompatActivity {

    //Explicit
    private TextView nameTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String nameString;
    private FoodTABLE objFoodTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_view);

        objFoodTABLE = new FoodTABLE(this);

        //Bind Widget
        bindWidget();

        //Show Name
        showName();

        //Create Spinner Desk
        createSpinnerDesk();

        //Syn JSON To SQLite
        synJSONtoSQLite();

        //Create ListView
        createListView();

    }   // onCreate

    private void createListView() {

         String[] strFood = objFoodTABLE.readAllFood();
         String[] strPrice = objFoodTABLE.readAllPrice();
        int intImageFood[] = new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7,
                R.drawable.food8, R.drawable.food9, R.drawable.food10, R.drawable.food11,
                R.drawable.food12, R.drawable.food13, R.drawable.food14, R.drawable.food15,
                R.drawable.food16, R.drawable.food17, R.drawable.food18, R.drawable.food19,
                R.drawable.food20, R.drawable.food21, R.drawable.food22, R.drawable.food23,
                R.drawable.food24, R.drawable.food25, R.drawable.food26, R.drawable.food27,
                R.drawable.food28, R.drawable.food29, R.drawable.food30, R.drawable.food31,
                R.drawable.food32, R.drawable.food33, R.drawable.food34, R.drawable.food35,
                R.drawable.food36, R.drawable.food37, R.drawable.food38, R.drawable.food39,
                R.drawable.food40, R.drawable.food41, R.drawable.food42, R.drawable.food43,
                R.drawable.food44, R.drawable.food45, R.drawable.food46, R.drawable.food47,
                R.drawable.food48, R.drawable.food49, R.drawable.food50};
        FoodAdapter objFoodAdapter = new FoodAdapter(OrderListView.this, strFood, strPrice, intImageFood);
        foodListView.setAdapter(objFoodAdapter);
    }   //createListView

    private void synJSONtoSQLite() {

        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }


        InputStream objInputStream = null;
        String strJSON = "";

        //1. Create InputStream
        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/ssru3/php_get_data_food.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("ssru", "InputStream ==> " + e.toString());
        }


        //2. Create strJSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null ) {
                objStringBuilder.append(strLine);
            }
            objInputStream.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {
            Log.d("ssru", "strJSON ==> " + e.toString());
        }


        //3. Update SQLite
        try {

            final JSONArray objJsonArray = new JSONArray(strJSON);

            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject objJSONObject = objJsonArray.getJSONObject(i);

                String strFood = objJSONObject.getString("Food");
                String strPrice = objJSONObject.getString("Price");

                objFoodTABLE.addFood(strFood, strPrice);


            }   // for

        } catch (Exception e) {
            Log.d("ssru", "Update ==> " + e.toString());
        }




    }   //synJSONtoSQLite

    private void createSpinnerDesk() {

        String showAllDesk[] = getResources().getStringArray(R.array.desk);
        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, showAllDesk);
        deskSpinner.setAdapter(deskAdapter);

    }   // createSpinnerDesk

    private void showName() {
        nameString = getIntent().getExtras().getString("Name");
        nameTextView.setText(nameString);
    }

    private void bindWidget() {
        nameTextView = (TextView) findViewById(R.id.txtShowName);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
