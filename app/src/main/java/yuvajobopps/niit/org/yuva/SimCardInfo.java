package yuvajobopps.niit.org.yuva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Deepak Upadhyay on 19-Jul-16.
 */
public class SimCardInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sim_card_info);
        Intent ii = getIntent();
        String news_name = ii.getStringExtra("news_name");
        String news_desc = ii.getStringExtra("news_desc");
        String news_person = ii.getStringExtra("news_person");
        String news_no_of_vacancy = ii.getStringExtra("news_no_of_vacancy");
        String news_date_interview = ii.getStringExtra("news_date_interview");

        TextView txt_news_name = (TextView) findViewById(R.id.textView3);
        TextView txt_news_desc = (TextView) findViewById(R.id.textview_Sim_No);
        TextView txt_news_person = (TextView) findViewById(R.id.textview_Sim_EMI);

        txt_news_name.setText(news_name);
        txt_news_desc.setText(news_desc);
        txt_news_person.setText(news_no_of_vacancy + ":" + news_date_interview + ":" + news_person);


    }
}
