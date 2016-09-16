package yuvajobopps.niit.org.yuva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class Home extends AppCompatActivity {
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3,floatingActionButton4,floatingActionButton5;
    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView studentName = (TextView)findViewById(R.id.sName);
        TextView studentRollNo = (TextView)findViewById(R.id.sRollNo);
        TextView studentEmailId = (TextView)findViewById(R.id.sEmailId);
        TextView studentMobileNo = (TextView)findViewById(R.id.sMobileNo);
        TextView companyName = (TextView)findViewById(R.id.cName);
        TextView companyAddress = (TextView)findViewById(R.id.cAddress);
        TextView companyPrimaryPersonName = (TextView)findViewById(R.id.pName);
        TextView companyPrimaryPersonContacrNo1 = (TextView)findViewById(R.id.pContactNo);
        TextView companyPrimaryPersonContactNo2 = (TextView)findViewById(R.id.pContactNo2);
        TextView companyWebsite = (TextView)findViewById(R.id.pWebsite);

        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        studentName.setText(sharedPreferences.getString("student_first_name", null) + " " + sharedPreferences.getString("student_last_name", null));
        studentRollNo.setText(sharedPreferences.getString("student_roll_no", null));
        studentEmailId.setText(sharedPreferences.getString("student_email", null));
        studentMobileNo.setText(sharedPreferences.getString("student_mobile_no", null));
        companyName.setText(sharedPreferences.getString("institute_name", null));
        companyAddress.setText(sharedPreferences.getString("institute_address", null));
        companyPrimaryPersonName.setText(sharedPreferences.getString("institute_primary_person_name", null));
        companyPrimaryPersonContacrNo1.setText(sharedPreferences.getString("institute_contactno1", null));
        companyPrimaryPersonContactNo2.setText(sharedPreferences.getString("institute_contactno2", null));
        companyWebsite.setText(sharedPreferences.getString("institute_website", null));

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item5);

            floatingActionButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu first item clicked

                }
            });
            floatingActionButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu second item clicked
                    if(isInternetOn()==true) {
                        Intent ii = new Intent(getApplicationContext(),MiddleLayerOpp.class);
                        startActivity(ii);
                    }
                    else{
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
                    Intent ii = new Intent(getApplicationContext(),SubmitQuery.class);
                    startActivity(ii);
                }
            });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

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


    }
    public boolean isInternetOn() {
        ConnectivityManager connectivitymanager = (ConnectivityManager)getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null)
        {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null)
            {
                for (int i = 0; i < anetworkinfo.length; i++)
                {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }

            }
        }
        return false;
    }


}

