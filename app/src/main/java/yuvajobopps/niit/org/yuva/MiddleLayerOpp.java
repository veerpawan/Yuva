package yuvajobopps.niit.org.yuva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yuvajobopps.db.Constants;
import yuvajobopps.niit.org.yuva.parser.JSONParser;

public class MiddleLayerOpp extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    //String student_id,news_name,news_desc,news_no_of_vacancy,news_person,news_date_interview,news_counter;
    String status, studentIdSession, news_counter, news_name2, news_desc2, news_no_of_vacancy2, news_date_interview2, news_person2;
    SharedPreferences sharedPreferences;
    String op_news_name, op_news_desc, op_news_no_of_vacancy, op_news_date_interview, op_news_person;

    JSONParser jsonParser = new JSONParser();
    private static final String News_Refresh_URL = Constants.NIIT+"/Yuva/AppChangesDetect";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static final String keyCode = "keyCode";

    private static final String student_id = "student_id";

    private static final String student_roll_no = "student_roll_no";

    private static final String student_email = "student_email";

    private static final String student_mobile = "student_mobile";

    private static final String student_first_name = "student_first_name";

    private static final String student_last_name= "student_last_name";

    private static final String institute_name="institute_name";

    private static final String institute_address = "institute_address";

    private static final String institute_website = "institute_website";

    private static final String institute_contactno1 = "institute_contactno1";

    private static final String institute_contactno2 ="institute_contactno2";

    private static final String institute_primary_person_name = "institute_primary_person_name";

    private static final String news_name = "news_name";

    private static final String news_desc = "news_desc";

    private static final String news_person ="news_person";

    private static final String news_no_of_vacancy = "news_no_of_vacancy";

    private static final String news_date_interview ="news_date_interview";


    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_layer_opportunities);

        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        new AttemptRefresh().execute();

    }
    public class AttemptRefresh extends AsyncTask<String, String, String> {
        boolean failure = false;
        ProgressDialog pDialog;
        String username,mobile;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MiddleLayerOpp.this);
            pDialog.setMessage("Opportunities List Updating ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // here Check for success tag
            Log.e("Inside do post", "do In backround");
            int success;
            try {
                String student_id = sharedPreferences.getString("student_id",null);
                Log.e("Refresh student_id", student_id);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("student_id", student_id));

                /*ContextCompat.checkSelfPermission(LoginForm2.this, Manifest.permission.READ_PHONE_STATE);
                ContextCompat.checkSelfPermission(LoginForm2.this, Manifest.permission.INTERNET);
                ContextCompat.checkSelfPermission(LoginForm2.this, Manifest.permission.BIND_TELECOM_CONNECTION_SERVICE);



                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
               new SimCardInfoBean(tm.getSimSerialNumber(),tm.getSimOperatorName(),tm.getDeviceId());


                params.add(new BasicNameValuePair("simId",tm.getSimSerialNumber()));
                Log.d("simID", tm.getSimSerialNumber());

                params.add(new BasicNameValuePair("simCompanyName", tm.getSimOperatorName()));
                Log.d("simCompanyName", tm.getSimOperatorName());
                params.add(new BasicNameValuePair("phoneEMINo", tm.getDeviceId()));
                Log.d("simPhoneEMINo", tm.getDeviceId());

                */
                params.add(new BasicNameValuePair("simId","1234567890"));
                //Log.d("simID", tm.getSimSerialNumber());

                params.add(new BasicNameValuePair("simCompanyName", "0123456789"));
                //Log.d("simCompanyName", tm.getSimOperatorName());
                params.add(new BasicNameValuePair("phoneEMINo", "Aircel"));
                //Log.d("simPhoneEMINo", tm.getDeviceId());

                JSONObject json = jsonParser.makeHttpRequest(News_Refresh_URL, "POST", params);
                Log.e("Inside News_Refresh_URL","After Refresh_URL");

                JSONArray PROFILE_INFO = json.getJSONArray("NEWS_INFO");
                // checking log for json response
                Log.d("Refresh attempt", json.toString());
                // success tag for json
                JSONObject q = PROFILE_INFO.getJSONObject(0);
                success = q.getInt(TAG_SUCCESS);

                if (success == 1) {
                    status = "1";

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int json_keyCode=q.getInt(keyCode);
                    Log.e("keyCode:",json_keyCode+"");

                    String json_student_id = q.getString("student_id");
                    Log.e("student_id:",json_student_id+"");
                    String json_student_roll_no = q.getString(student_roll_no);
                    String json_student_email = q.getString(student_email);
                    Log.e("json_student_email:",json_student_email);
                    String json_student_mobile = q.getString(student_mobile);
                    Log.e("json_student_mobile:",json_student_mobile);
                    String json_student_first_name = q.getString(student_first_name);
                    Log.d("json_student_first_name:",json_student_first_name);
                    String json_student_last_name = q.getString(student_last_name);
                    Log.e("json_student_last_name:",json_student_last_name);

                    String json_institute_name = q.getString(institute_name);
                    Log.e("json_institute_name:",json_institute_name);

                    String json_institute_address = q.getString(institute_address);
                    Log.e("json_institute_address:",json_institute_address);

                    String json_institute_primary_person_name = q.getString(institute_primary_person_name);
                    Log.e("json_institute_primary_person_name:",json_institute_primary_person_name);

                    String json_institute_contactno1 = q.getString(institute_contactno1);
                    Log.e("json_institute_contactno1:",json_institute_contactno1);

                    String json_institute_contactno2 = q.getString(institute_contactno2);
                    Log.e("json_institute_contactno2:",json_institute_contactno2);

                    String json_institute_website = q.getString(institute_website);
                    Log.e("json_institute_website:",json_institute_website);

                    int json_news_counter = q.getInt("news_counter");
                    editor.putInt("news_counter",json_news_counter);
                    for(int i=0;i<json_news_counter;i++) {
                        String json_news_name = q.getString(news_name + i);
                        Log.e("json_news_name:" + i, json_news_name);

                        String json_news_desc = q.getString(news_desc + i);
                        Log.e("json_news_desc:" + i, json_news_desc);

                        String json_news_date_interview = q.getString(news_date_interview + i);
                        Log.e("json_news_date_interview:" + i, json_news_date_interview);

                        String json_news_no_of_vacancy = q.getString(news_no_of_vacancy + i);
                        Log.e("json_news_no_of_vacancy:" + i, json_news_no_of_vacancy);

                        String json_news_person = q.getString(news_person + i);
                        Log.e("json_news_person:" + i, json_news_person);

                        editor.putString("news_name" + i, json_news_name);
                        editor.putString("news_desc" + i, json_news_desc);
                        editor.putString("news_date_interview" + i, json_news_date_interview);
                        editor.putString("news_no_of_vacancy" + i, json_news_no_of_vacancy);
                        editor.putString("news_person" + i, json_news_person);


                    }
                    //Store into Shared Preferences
                    editor.putString("student_id", json_student_id);
                    editor.putString("student_roll_no", json_student_roll_no);
                    editor.putString("student_email", json_student_email);
                    editor.putString("student_mobile_no", json_student_mobile);
                    editor.putString("student_first_name", json_student_first_name);
                    editor.putString("student_last_name", json_student_last_name);

                    editor.putString("institute_name", json_institute_name);
                    editor.putString("institute_address", json_institute_address);
                    editor.putString("institute_primary_person_name", json_institute_primary_person_name);
                    editor.putString("institute_contactno1", json_institute_contactno1);
                    editor.putString("institute_contactno2", json_institute_contactno2);
                    editor.putString("institute_website", json_institute_website);

                    editor.commit();
                    Intent intent = new Intent(MiddleLayerOpp.this, OpportunitiesList.class);
                    intent.putExtra("message","1");
                    finish();
                    startActivity(intent);


                    return status;
                } else {
                    status="0";
                    return status;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Once the background process is done we need to Dismiss the progress dialog asap * *
         */
        protected void onPostExecute(String message) {
//            if (message.equals("1")) {
          //            }
/*
            else{
                //    Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
                Snackbar snackbar =   Snackbar.make(findViewById(android.R.id.content), "  Unable to Load Opportunities List  ", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundc));
                textView.setTextColor(Color.RED);
                snackbar.show();
            }
*/
        }

    }


}  //Main Class