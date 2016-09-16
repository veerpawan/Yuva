package yuvajobopps.niit.org.yuva;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class OpportunitiesList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    //String student_id,news_name,news_desc,news_no_of_vacancy,news_person,news_date_interview,news_counter;
    String status, studentIdSession, news_counter, news_name2, news_desc2, news_no_of_vacancy2, news_date_interview2, news_person2;
    SharedPreferences sharedPreferences;
    String op_news_name, op_news_desc, op_news_no_of_vacancy, op_news_date_interview, op_news_person;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunities_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item5);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent ii = new Intent(getApplicationContext(), Home.class);
                startActivity(ii);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                if (isInternetOn()) {
                    Intent ii = new Intent(getApplicationContext(), MiddleLayerOpp.class);
                    startActivity(ii);

                } else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No internet connection !!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    snackbar.setActionTextColor(Color.RED);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                }


            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent ii = new Intent(getApplicationContext(), SubmitQuery.class);
                startActivity(ii);
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

                if (isInternetOn()) {
                    Intent ii = new Intent(getApplicationContext(), MiddleLayerOpp.class);
                    startActivity(ii);

                } else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No internet connection !!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    snackbar.setActionTextColor(Color.RED);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                }

            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        sharedPreferences = getSharedPreferences("MyPrefs", 0);
//        studentIdSession= sharedPreferences.getString("student_id", null);
//        Log.e("studentIdSession",studentIdSession);

        // new AttemptRefresh().execute();

/*
        op_news_name= sharedPreferences.getString("news_name", null);
        op_news_desc= sharedPreferences.getString("news_desc", null);
        op_news_no_of_vacancy = sharedPreferences.getString("news_no_of_vacancy",null);
        op_news_date_interview = sharedPreferences.getString("date_interview",null);
        op_news_person = sharedPreferences.getString("news_person",null);
*/


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        int news_counter = sharedPreferences.getInt("news_counter", 0);
        Log.e("news_counter", news_counter + "");
        ArrayList results = new ArrayList<DataObject>();
        for (int i = 0; i < news_counter; i++) {
            //for (int i = 0; i < 5; i++) {

            Log.e("op_news_name" + i, sharedPreferences.getString("news_name" + i, null));
            Log.e("op_news_desc" + i, sharedPreferences.getString("news_desc" + i, null));
            Log.e("op_news_no_of_vacancy" + i, sharedPreferences.getString("news_no_of_vacancy" + i, null));
            Log.e("op_news_date_interview" + i, sharedPreferences.getString("news_date_interview" + i, null));
            Log.e("op_news_person" + i, sharedPreferences.getString("news_person" + i, null));


            DataObject obj = new DataObject(sharedPreferences.getString("news_name" + i, null),
                    sharedPreferences.getString("news_desc" + i, null),
                    sharedPreferences.getString("news_no_of_vacancy" + i, null),
                    sharedPreferences.getString("news_date_interview" + i, null),
                    sharedPreferences.getString("news_person" + i, null));

            /*DataObject obj = new DataObject("1"+i,
                    "2"+i,"3"+i,
                    "4"+i,
                    "5"+i);
*/
            results.add(i, obj);
            mAdapter = new MyRecyclerViewAdapter(results);
            mRecyclerView.setAdapter(mAdapter);

        }
        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (isInternetOn() == true) {
                    Log.i(LOG_TAG, " Clicked on Item " + position);
                    news_name2 = sharedPreferences.getString("news_name" + position, null);
                    news_desc2 = sharedPreferences.getString("news_desc" + position, null);
                    news_no_of_vacancy2 = sharedPreferences.getString("news_no_of_vacancy" + position, null);
                    news_person2 = sharedPreferences.getString("news_person" + position, null);
                    news_date_interview2 = sharedPreferences.getString("news_date_interview" + position, null);

                    Intent ii = new Intent(getApplicationContext(), JobOpportunitiesDetails.class);
                    ii.putExtra("news_name", news_name2);
                    ii.putExtra("news_desc", news_desc2);
                    ii.putExtra("news_no_of_vacancy", news_no_of_vacancy2);
                    ii.putExtra("news_person", news_person2);
                    ii.putExtra("news_date_interview", news_date_interview2);
                    //finish();
                    startActivity(ii);
                } else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No internet connection !!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    snackbar.setActionTextColor(Color.RED);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                }

            }
        });
    }

    private ArrayList<DataObject> getDataSet(int news_counter) {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < news_counter; index++) {
            DataObject obj = new DataObject(news_name2, news_desc2, news_no_of_vacancy2, news_date_interview2, news_person2);
            results.add(index, obj);
        }
        return results;
    }


    /*
                private ArrayList<DataObject> getDataSet() {
                    ArrayList results = new ArrayList<DataObject>();
                    for (int index = 0; index < 20; index++) {
                        DataObject obj = new DataObject("Some Primary Text " + index,
                                "Secondary " + index);
                        results.add(index, obj);
                    }
                    return results;
                }
    */
    public boolean isInternetOn() {
        boolean status;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_SHORT).show();
            status = true;


        } else {
            status = false;

        }
        return status;
    }


}
