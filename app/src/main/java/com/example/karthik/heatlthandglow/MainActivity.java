package com.example.karthik.heatlthandglow;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.karthik.heatlthandglow.Adapters.RecyclerViewAdapter;
import com.example.karthik.heatlthandglow.Adapters.RecyclerViewAdapterFlavours;
import com.example.karthik.heatlthandglow.Adapters.RecyclerViewAdapterImages;
import com.example.karthik.heatlthandglow.Adapters.RecyclerViewAdapterQuestions;
import com.example.karthik.heatlthandglow.Adapters.ViewPagerAdapter;
import com.example.karthik.heatlthandglow.Adapters.ViewPagerAdapterSimilar;
import com.example.karthik.heatlthandglow.Helpers.CommonUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import static android.R.attr.width;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    JSONObject data,jsonPost,JsonContent,skuItem,productVaraintList,sku1,sku2;
    JSONArray jsonarray,skuImageUrls,productVariantListJsonArray,shades_array,flavours_array,volumes_array,productFaceList,questionList,answerList,productComboList,simialrItemsList;
    ArrayList<HashMap<String, String>> arraylist,imageurls,varaiantlist,shade_array,flavours_arraylist,volumes_arraylist,productFaceArraylist,questionArrayList,answerArraylist,productComboArrayList,similiarItemsArrayList;
    ImageView main;
    TextView name,type,price,dis_price,discount,reviews;
    ViewPager similar_items,product_combos;
    RatingBar rating;

    RecyclerViewAdapter shade_adapter,volume_adapter;
    RecyclerViewAdapterQuestions question_adapter;
    RecyclerViewAdapterFlavours flavour_adapter;
    RecyclerViewAdapterImages img;
    RecyclerView shades,flavours,volumes,questions,image_gallery;
    TabHost tab;
    ViewPagerAdapter combo;
    ViewPagerAdapterSimilar similar;
    Inflater inflater;
    TabHost host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        main = (ImageView) findViewById(R.id.main_image);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.actual_price);
        dis_price = (TextView) findViewById(R.id.discount_price);
        discount = (TextView) findViewById(R.id.discount);
        rating = (RatingBar) findViewById(R.id.rating);
        reviews = (TextView) findViewById(R.id.reviews);
        shades = (RecyclerView) findViewById(R.id.shades);
        flavours = (RecyclerView) findViewById(R.id.flavours);
        volumes = (RecyclerView) findViewById(R.id.volumes);
        questions = (RecyclerView) findViewById(R.id.questions);
        image_gallery = (RecyclerView) findViewById(R.id.image_gallery);
        image_gallery.setLayoutManager(layoutManager4);
       // tab = (TabHost) findViewById(R.id.tab);
        shades.setLayoutManager(layoutManager);
        volumes.setLayoutManager(layoutManager1);
        flavours.setLayoutManager(layoutManager2);
        questions.setLayoutManager(layoutManager3);


        similar_items = (ViewPager) findViewById(R.id.similar_items);
        product_combos = (ViewPager) findViewById(R.id.product_combos);

        // Json array initialization

//        skuImageUrls = new JSONArray();
//        productVariantList=new JSONArray();
//        sahdes=new JSONArray();
//        flavours_array=new JSONArray();
//        volumes_array=new JSONArray();
//        productFaceList = new JSONArray();
//        questionList=new JSONArray();
//        answerList=new JSONArray();
//        productComboList=new JSONArray();
//        simialrItemsList=new JSONArray();

        // JSon objects initialization
        //data,jsonPost,JsonContent,skuItem,productVaraintList,sku1,sku2

        imageurls=new ArrayList<HashMap<String, String>>();
        varaiantlist=new ArrayList<HashMap<String, String>>();
        shade_array=new ArrayList<HashMap<String, String>>();
        flavours_arraylist=new ArrayList<HashMap<String, String>>();
        volumes_arraylist=new ArrayList<HashMap<String, String>>();
        productFaceArraylist=new ArrayList<HashMap<String, String>>();
        questionArrayList=new ArrayList<HashMap<String, String>>();
        answerArraylist=new ArrayList<HashMap<String, String>>();
        productComboArrayList=new ArrayList<HashMap<String, String>>();
        similiarItemsArrayList=new ArrayList<HashMap<String, String>>();
        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("Ingredients");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("Tab One");
//        host.addTab(spec);
//
//        //Tab 2
//        spec = host.newTabSpec("How to use");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("Tab");
//        host.addTab(spec);
//
//        //Tab 3
//        spec = host.newTabSpec("Description");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("Tab Three");
//        host.addTab(spec);



        jsonPost = new JSONObject();
        try {


            jsonPost.accumulate("appType","android");
            jsonPost.accumulate("appVersion","1.20");
            jsonPost.accumulate("pinCode","560097");
            jsonPost.accumulate("skuId","536161");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        data = new JSONObject();

        new GetHomePage().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    private class GetHomePage extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {


            arraylist = new ArrayList<HashMap<String, String>>();


            JsonContent = JsonFunctions
                    .getJSONfromURL( "http://119.81.82.197:9090/hngeCommerceWebservice/rest/product/detail/", jsonPost);
            try {
                data = JsonContent.getJSONObject("data");

                skuItem = data.getJSONObject(CommonUtils.skuItemJsonObject);
                skuImageUrls = skuItem.getJSONArray(CommonUtils.skuImahesJsonArray);
                productVaraintList = data.getJSONObject(CommonUtils.skuProcuctVariantListJsonOBject);
                System.out.println("flavours are" + "flavours_array");

                shades_array = productVaraintList.getJSONArray(CommonUtils.skuShadesJsonArray);
                flavours_array =productVaraintList.getJSONArray(CommonUtils.skuFlavoursJsonArray);
                //volumes_array = productVaraintList.getJSONArray(CommonUtils.skuVolumesJsonArray);
                productFaceList = data.getJSONArray(CommonUtils.productFaceJsonArray);
                questionList = data.getJSONArray(CommonUtils.questionListJsonArray);
                productComboList = data.getJSONArray(CommonUtils.productListComboJsonArray);
                simialrItemsList = data.getJSONArray(CommonUtils.similarItemsListJsonArray);
                System.out.println("flavours are" + flavours_array);





            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
            for(int i=0; i<flavours_array.length();i++){
                HashMap<String, String> map = new HashMap<String, String>();

                    JSONObject temp = flavours_array.getJSONObject(i);
                System.out.println("flavours json is " + temp);

                map.put(CommonUtils.skuname,temp.getString(CommonUtils.skuname));
                map.put(CommonUtils.skuCode,temp.getString(CommonUtils.skuCode));
                map.put(CommonUtils.skuIsSelected,temp.getString(CommonUtils.skuIsSelected));
                map.put(CommonUtils.skuIsAvaliable,temp.getString(CommonUtils.skuIsAvaliable));
                map.put(CommonUtils.skuId,temp.getString(CommonUtils.skuId));
                map.put(CommonUtils.skuVolumesJsonArray,temp.getJSONArray(CommonUtils.skuVolumesJsonArray).toString());
                flavours_arraylist.add(map);
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try{
                for(int k = 0;k<questionList.length();k++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject temp = questionList.getJSONObject(k);

                    map.put(CommonUtils.userID,temp.getString(CommonUtils.userID));
                    map.put(CommonUtils.userName,temp.getString(CommonUtils.userName));
                    map.put(CommonUtils.question,temp.getString(CommonUtils.question));
                    map.put(CommonUtils.questionId,temp.getString(CommonUtils.questionId));
                    map.put(CommonUtils.answerListJsonArray,temp.getString(CommonUtils.answerListJsonArray).toString());
                    map.put(CommonUtils.defaultAnswer,temp.getString(CommonUtils.defaultAnswer));
                    map.put(CommonUtils.isMore,temp.getString(CommonUtils.isMore));
                    questionArrayList.add(map);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }


            try{
                for(int p = 0;p<productComboList.length();p++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject temp = productComboList.getJSONObject(p);

                    JSONObject sku1 = temp.getJSONObject(CommonUtils.sku1JsonObject);
                    JSONObject sku2 = temp.getJSONObject(CommonUtils.sku2JsonObject);
                    map.put(CommonUtils.sku1Price,sku1.getString(CommonUtils.sku1Price));
                    map.put(CommonUtils.sku1Name,sku1.getString(CommonUtils.sku1Name));
                    map.put(CommonUtils.sku1Id,sku1.getString(CommonUtils.sku1Id));
                    map.put(CommonUtils.sku1OfferPrice,sku1.getString(CommonUtils.skuOfferPrice));
                    map.put(CommonUtils.sku1BrandName,sku1.getString(CommonUtils.sku1BrandName));
                    map.put(CommonUtils.sku1promoFlagOnImage,sku1.getString(CommonUtils.sku1promoFlagOnImage));
                    map.put(CommonUtils.sku1AverageRating,sku1.getString(CommonUtils.sku1AverageRating));
                    map.put(CommonUtils.sku1ImageUrl,sku1.getString(CommonUtils.sku1ImageUrl));

                    map.put(CommonUtils.sku2Price,sku2.getString(CommonUtils.sku1Price));
                    map.put(CommonUtils.sku2Name,sku2.getString(CommonUtils.sku1Name));
                    map.put(CommonUtils.sku2Id,sku2.getString(CommonUtils.sku1Id));
                    map.put(CommonUtils.sku2OfferPrice,sku2.getString(CommonUtils.skuOfferPrice));
                    map.put(CommonUtils.sku2BrandName,sku2.getString(CommonUtils.sku1BrandName));
                    map.put(CommonUtils.sku2promoFlagOnImage,sku2.getString(CommonUtils.sku1promoFlagOnImage));
                    map.put(CommonUtils.sku2AverageRating,sku2.getString(CommonUtils.sku1AverageRating));
                    map.put(CommonUtils.sku2ImageUrl,sku2.getString(CommonUtils.sku1ImageUrl));
                    map.put(CommonUtils.totalPrice,temp.getString(CommonUtils.totalPrice));
                    map.put(CommonUtils.finalPrice,temp.getString(CommonUtils.finalPrice));
                    productComboArrayList.add(map);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }


            try{
                for(int s = 0;s<simialrItemsList.length();s++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject temp = simialrItemsList.getJSONObject(s);

                    map.put(CommonUtils.sku2Price,temp.getString(CommonUtils.sku1Price));
                    map.put(CommonUtils.sku2Name,temp.getString(CommonUtils.sku1Name));
                    map.put(CommonUtils.sku2Id,temp.getString(CommonUtils.sku1Id));
                    map.put(CommonUtils.sku2OfferPrice,temp.getString(CommonUtils.skuOfferPrice));
                    map.put(CommonUtils.sku2BrandName,temp.getString(CommonUtils.sku1BrandName));
                    map.put(CommonUtils.sku2promoFlagOnImage,temp.getString(CommonUtils.sku1promoFlagOnImage));
                    map.put(CommonUtils.sku2AverageRating,temp.getString(CommonUtils.sku1AverageRating));
                    map.put(CommonUtils.sku2ImageUrl,temp.getString(CommonUtils.sku1ImageUrl));

                    similiarItemsArrayList.add(map);

                    System.out.println("similar items are" + similiarItemsArrayList);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }




            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                name.setText(skuItem.getString(CommonUtils.skuName));
                price.setText("Rs"+" "+skuItem.getString(CommonUtils.skuPrice));
                dis_price.setText("Rs"+" "+skuItem.getString(CommonUtils.skuOfferPrice));
                reviews.setText(skuItem.getString(CommonUtils.skuReviewListCount)+" "+"User reviews");
                rating.setNumStars(3);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            img = new RecyclerViewAdapterImages(MainActivity.this,main,skuImageUrls);
            image_gallery.setAdapter(img);
//            shade_adapter = new RecyclerViewAdapter(MainActivity.this);
//
//            shades.setAdapter(shade_adapter);
            flavour_adapter = new RecyclerViewAdapterFlavours(MainActivity.this,flavours_arraylist,volumes);

            flavours.setAdapter(flavour_adapter);

           // volume_adapter = new RecyclerViewAdapter(MainActivity.this);
            question_adapter = new RecyclerViewAdapterQuestions(MainActivity.this,questionArrayList);

            LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for(int i=0; i<productFaceList.length(); i++ )
            {
                try {
                    JSONObject temp = productFaceList.getJSONObject(i);
                    final TabHost.TabSpec x=host.newTabSpec(temp.getString(CommonUtils.title));
                    final View row = inflater.inflate(R.layout.indicator,null);
                    row.setId(i);
//                    ViewGroup.LayoutParams params = host.getTabWidget().getChildAt(0).getLayoutParams();
//                    params.width = 170;
//                    params.height = 120;
//                    host.getTabWidget().getChildAt(0).setLayoutParams(params );
                    //host.getTabWidget().getChildAt(0).getLayoutParams().width = 50;
                   // host.getTabWidget().getChildAt(i).getLayoutParams().width=50;

                    final TextView indicator1 =(TextView) row.findViewById(R.id.tabs);
                    indicator1.setText(temp.getString(CommonUtils.content));
                    // indicator1.setShadowLayer(1, 0, 1, 0xFF013201);

                    x.setIndicator(temp.getString(CommonUtils.title));
                    indicator1.setTextSize(25);

                    // x.setContent(row.getId());
                    x.setContent(new TabHost.TabContentFactory() {
                        @Override
                        public View createTabContent(String tag) {
                            return row;
                        }
                    });
//            x.setContent(new TabHost.TabContentFactory() {
//                public View createTabContent(String arg) {
//                    return gallery2;
//                }
//            });

                    host.addTab(x);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            if(shade_array.size() == 0){
                TextView shade_text = (TextView) findViewById(R.id.shade_text);
                shade_text.setVisibility(View.GONE);
            }

            questions.setAdapter(question_adapter);
            combo = new ViewPagerAdapter(MainActivity.this,productComboArrayList);
            product_combos.setAdapter(combo);
            similar = new ViewPagerAdapterSimilar(MainActivity.this,similiarItemsArrayList);
            similar_items.setAdapter(similar);
        }
    }
}
