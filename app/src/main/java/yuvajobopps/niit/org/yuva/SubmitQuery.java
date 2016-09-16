package yuvajobopps.niit.org.yuva;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yuvajobopps.db.Constants;
import yuvajobopps.niit.org.yuva.parser.JSONParser;


public class SubmitQuery extends AppCompatActivity {
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;
    String jsonSendQuery, saf_student_id, saf_institute_id, saf_subject, saf_message;
    private ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    JSONParser jsonParser = new JSONParser();
    private static final String SUBMIT_QUERY = Constants.NIIT + "/Yuva/AppSubmitQuery";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    EditText etxtSubject2, etxtMsg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etxtSubject2 = (EditText) findViewById(R.id.etxtSubject2);
        etxtMsg2 = (EditText) findViewById(R.id.etxtMsg2);

        Button button = (Button) findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                if (isInternetOn()) {

                    sharedPreferences = getSharedPreferences("MyPrefs", 0);
                    saf_student_id = sharedPreferences.getString("student_id", null);
                    saf_institute_id = sharedPreferences.getString("institute_id", null);
                    saf_subject = etxtSubject2.getText().toString();
                    saf_message = etxtMsg2.getText().toString();

                    Log.e("subject: ", saf_subject);
                    Log.e("message", saf_message);
                    jsonSendQuery = createJSONQuery(saf_student_id, saf_institute_id, saf_subject, saf_message);
                    Log.e("createJson: ", jsonSendQuery);

                    new AttemptSubmitQuery().execute();
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


    protected String createJSONQuery(String saf_student_id, String saf_institute_id, String saf_subject, String saf_message) {

        try {

            JSONObject sendQuery = new JSONObject();
            JSONObject sendQueryWrap = new JSONObject();
            sendQuery.put("saf_student_id", saf_student_id);
            sendQuery.put("saf_institute_id", saf_institute_id);
            sendQuery.put("saf_subject", saf_subject);
            sendQuery.put("saf_message", saf_message);
            sendQueryWrap.put("FEEDBACK", sendQuery);
            jsonSendQuery = sendQueryWrap.toString();
            Log.e("SubmitQueryJSON: ", jsonSendQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonSendQuery;
    }

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

    class AttemptSubmitQuery extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SubmitQuery.this);
            pDialog.setMessage("Submitting Answers!!!!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // here Check for success tag
            int success;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("jsonSendQuery", jsonSendQuery));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(SUBMIT_QUERY, "POST", params);
                // checking log for json response
                Log.e("JSONStatus", json.toString());
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("submitted successfully", json.toString());

                    return json.getString(TAG_MESSAGE);

                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Once the background process is done we need to Dismiss the progress dialog asap * *
         */
        protected void onPostExecute(String message) {

            dismissProgressDialog();

            if (message != null) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Feedback sent successfully.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundc));
                textView.setTextColor(Color.rgb(24, 124, 255));
                snackbar.show();
                //  Toast.makeText(SubmitQuery.this, message, Toast.LENGTH_LONG).show();
                //    showMessage("Message","Feedback sent successfully.");
            } else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Feedback unable to sent successfully.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundc));
                textView.setTextColor(Color.rgb(24, 124, 255));
                snackbar.show();
                //    showMessage("Message","Feedback unable to send.");
                //userPause();
            }


        }

        private void dismissProgressDialog() {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }

    }
/*
    private void showMessage(final String title, final String msg) {
        final Dialog d = new Dialog(SubmitQuery.this);
        final TextView tv = new TextView(SubmitQuery.this);
                d.setTitle(title);
                tv.setText(msg);
                tv.setTextColor(Color.BLUE);
                tv.setTextSize(16);
                d.setContentView(tv);

        d.setCancelable(true);
        d.show();
    }
*/


}

