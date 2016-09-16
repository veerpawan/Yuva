package yuvajobopps.niit.org.yuva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by Deepak Upadhyay on 19-Jul-16.
 */
public class JobOpportunitiesDetails extends AppCompatActivity{
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3,floatingActionButton4,floatingActionButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_opportunities_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent ii = getIntent();
        String news_name = ii.getStringExtra("news_name");
        String news_desc = ii.getStringExtra("news_desc");
        String news_person = ii.getStringExtra("news_person");
        String news_no_of_vacancy = ii.getStringExtra("news_no_of_vacancy");
        String news_date_interview = ii.getStringExtra("news_date_interview");

        TextView txt_news_name = (TextView)findViewById(R.id.textView_Name);
        TextView txt_news_desc = (TextView)findViewById(R.id.textView_Desc);
        TextView txt_news_person = (TextView)findViewById(R.id.textView_Contact_Person);

        TextView txt_news_nov = (TextView)findViewById(R.id.textView_No_Of_Vacancy);

        TextView txt_news_doi = (TextView)findViewById(R.id.textView_date_interview);

        txt_news_name.setText(news_name);
        txt_news_desc.setText(news_desc);
        txt_news_person.setText(news_person);
        txt_news_nov.setText(news_no_of_vacancy);
        txt_news_doi.setText(news_date_interview);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item5);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent ii = new Intent(getApplicationContext(),Home.class);
                startActivity(ii);

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent ii = new Intent(getApplicationContext(),MiddleLayerOpp.class);
                startActivity(ii);
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
    }
