package yuvajobopps.niit.org.yuva;

import android.Manifest;
import android.animation.Animator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yuvajobopps.db.Constants;
import yuvajobopps.niit.org.yuva.beans.SimCardInfoBean;
import yuvajobopps.niit.org.yuva.parser.JSONParser;

public class fLoginForm extends AppCompatActivity {

    EditText etName, etPassword;


    int PERMISSION_REQUEST_CODE = 7;
    ImageView imageView;
    ImageButton imageButton;
    LinearLayout revealView, layoutButtons;
    Animation alphaAnimation;
    float pixelDensity;
    boolean flag = true;
    Button signIn;
    CardView cv;
    int currentapiVersion;
    SharedPreferences sharedPreferences;
    String username;
    String mobile, status;
    int otpStatus = 0;
    private EditText emailId_eText, mobile_eText;
    private Button bLogin;    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = Constants.NIIT + "/Yuva/AppStudentInfo";

    private static final String TAG_SUCCESS = "success";

    private static final String keyCode = "keyCode";

    private static final String student_id = "student_id";
    private static final String institute_id = "institute_id";

    private static final String student_roll_no = "student_roll_no";

    private static final String student_email = "student_email";

    private static final String student_mobile = "student_mobile";

    private static final String student_first_name = "student_first_name";

    private static final String student_last_name = "student_last_name";

    private static final String institute_name = "institute_name";

    private static final String institute_address = "institute_address";

    private static final String institute_website = "institute_website";

    private static final String institute_contactno1 = "institute_contactno1";

    private static final String institute_contactno2 = "institute_contactno2";

    private static final String institute_primary_person_name = "institute_primary_person_name";

    private static final String news_name = "news_name";

    private static final String news_desc = "news_desc";

    private static final String news_person = "news_person";

    private static final String news_no_of_vacancy = "news_no_of_vacancy";

    private static final String news_date_interview = "news_date_interview";

    private SimCardInfoBean simCardInfoBean;

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log_in);
        cv = (CardView) findViewById(R.id.cv);
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        // sharedPreferences = getSharedPreferences("MyPrefs", 0);

        if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        pixelDensity = getResources().getDisplayMetrics().density;

        signIn = (Button) findViewById(R.id.signIn);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation);
        revealView = (LinearLayout) findViewById(R.id.linearView);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);

        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);

        YoYo.with(Techniques.FadeInRight)
                .duration(4000)
                .playOn(cv);

        if (Build.VERSION.SDK_INT >= 23) {
            //   requestPermission();
        }

    }

    public boolean isInternetOn() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public void launchLogin(View v) {
        /*
         MARGIN_RIGHT = 16;
         FAB_BUTTON_RADIUS = 28;
         */

        if ((Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)) {
            int x = imageView.getRight();
            int y = imageView.getBottom();
            x -= ((28 * pixelDensity) + (16 * pixelDensity));

            int hypotenuse = (int) Math.hypot(imageView.getWidth(), imageView.getHeight());

            if (flag) {

                imageButton.setBackgroundResource(R.drawable.rounded_cancel_button);
                imageButton.setImageResource(R.mipmap.image_cancel);

                FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                        revealView.getLayoutParams();
                parameters.height = imageView.getHeight();
                revealView.setLayoutParams(parameters);

                Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, 0, hypotenuse);
                anim.setDuration(700);

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        layoutButtons.setVisibility(View.VISIBLE);
                        layoutButtons.startAnimation(alphaAnimation);
                        signIn.setVisibility(View.VISIBLE);

                        //Requesting permissions during the runtime for marshmallow


                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                revealView.setVisibility(View.VISIBLE);
                anim.start();

                flag = false;
            } else {

                imageButton.setBackgroundResource(R.drawable.rounded_button);
                imageButton.setImageResource(R.mipmap.login);

                Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse, 0);
                anim.setDuration(400);

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {


                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        revealView.setVisibility(View.GONE);
                        layoutButtons.setVisibility(View.GONE);
                        signIn.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                anim.start();
                flag = true;
            }
        } else {

            if (flag) {

                imageButton.setBackgroundResource(R.drawable.rounded_cancel_button);
                imageButton.setImageResource(R.mipmap.image_cancel);

                FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                        revealView.getLayoutParams();
                parameters.height = imageView.getHeight();
                revealView.setLayoutParams(parameters);
                revealView.setVisibility(View.VISIBLE);
                layoutButtons.setVisibility(View.VISIBLE);
                signIn.setVisibility(View.VISIBLE);
                flag = false;
            } else {
                imageButton.setBackgroundResource(R.drawable.rounded_button);
                imageButton.setImageResource(R.mipmap.login);
                revealView.setVisibility(View.GONE);
                layoutButtons.setVisibility(View.GONE);
                signIn.setVisibility(View.INVISIBLE);
                flag = true;
            }


        }
    }

    public void onClickLogin(View v) {
        if (isInternetOn() == true) {
            new AttemptLogin().execute();
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
        /*    Intent i = new Intent(LoginForm.this, OTP_Screen.class);
            startActivity(i);
*/

    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    private void requestPermission() {

        ActivityCompat.requestPermissions(LoginForm.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 7:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    for (int i = 0; i < grantResults.length; i++) {
                        int j = grantResults[i];
                        if (j == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

                        }
                    }


                } else {

                    this.finishAffinity();
                }
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////


    public class AttemptLogin extends AsyncTask<String, String, String> {
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginForm.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // here Check for success tag
            Log.e("Inside do post", "do In backround");
            int success;
            try {
                sharedPreferences = getSharedPreferences("MyPrefs", 0);

                username = etName.getText().toString();
                mobile = etPassword.getText().toString();

                Log.e("Inside username", username);
                Log.e("Inside mobile", mobile);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("mobileNo", mobile));

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
                params.add(new BasicNameValuePair("simId", "1234567890"));
                //Log.d("simID", tm.getSimSerialNumber());

                params.add(new BasicNameValuePair("simCompanyName", "0123456789"));
                //Log.d("simCompanyName", tm.getSimOperatorName());
                params.add(new BasicNameValuePair("phoneEMINo", "Aircel"));
                //Log.d("simPhoneEMINo", tm.getDeviceId());

                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
                Log.e("Inside username", "After Login_URL");

                JSONArray PROFILE_INFO = json.getJSONArray("STUDENT_INFO");
                Log.e("Inside username", "profile_info");
                // checking log for json response
                Log.d("Login attempt", json.toString());
                // success tag for json
                JSONObject q = PROFILE_INFO.getJSONObject(0);
                success = q.getInt(TAG_SUCCESS);

                if (success == 1) {
                    status = "1";
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    int json_keyCode = q.getInt(keyCode);
                    Log.e("keyCode:", json_keyCode + "");

                    if (json_keyCode == 11) {
                        String json_student_id = q.getString(student_id);
                        Log.e("student_id:", json_student_id);
                        String json_institute_id = q.getString(institute_id);
                        Log.e("institute_id:", json_institute_id);
                        String json_student_roll_no = q.getString(student_roll_no);
                        Log.e("student_id:", json_student_id);
                        String json_student_email = q.getString(student_email);
                        Log.e("json_student_email:", json_student_email);
                        String json_student_mobile = q.getString(student_mobile);
                        Log.e("json_student_mobile:", json_student_mobile);
                        String json_student_first_name = q.getString(student_first_name);
                        Log.d("json_student_first_name:", json_student_first_name);
                        String json_student_last_name = q.getString(student_last_name);
                        Log.e("json_student_last_name:", json_student_last_name);

                        String json_institute_name = q.getString(institute_name);
                        Log.e("json_institute_name:", json_institute_name);

                        String json_institute_address = q.getString(institute_address);
                        Log.e("json_institute_address:", json_institute_address);

                        String json_institute_primary_person_name = q.getString(institute_primary_person_name);
                        Log.e("json_institute_primary_person_name:", json_institute_primary_person_name);

                        String json_institute_contactno1 = q.getString(institute_contactno1);
                        Log.e("json_institute_contactno1:", json_institute_contactno1);

                        String json_institute_contactno2 = q.getString(institute_contactno2);
                        Log.e("json_institute_contactno2:", json_institute_contactno2);

                        String json_institute_website = q.getString(institute_website);
                        Log.e("json_institute_website:", json_student_id);

                        int json_news_counter = q.getInt("news_counter");
                        editor.putString("news_counter", json_news_counter + "");
                        for (int i = 0; i < json_news_counter; i++) {
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
                        editor.putString("institute_id", json_institute_id);
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

                        Intent ii = new Intent(getApplicationContext(), OpportunitiesList.class);//subject.class
                        ii.putExtra("student_roll_no", json_student_roll_no);
                        ii.putExtra("student_email", json_student_email);
                        ii.putExtra("student_mobile_no", json_student_mobile);
                        ii.putExtra("student_first_name", json_student_first_name);
                        ii.putExtra("student_last_name", json_student_last_name);
                        ii.putExtra("institute_name", json_institute_name);
                        //finish();
                        startActivity(ii);
                    } else if (json_keyCode == 12) {
                        String json_OTP = q.getString("OTP");
                        Log.e("json_OTP", json_OTP);
                        Intent ii = new Intent(getApplicationContext(), OTP_Screen.class);//subject.class
                        ii.putExtra("OTP_Key", json_OTP);
                        String json_student_id = q.getString(student_id);
                        Log.e("student_id:", json_student_id);
                        editor.putString("student_id", json_student_id);
                        editor.commit();
                        //finish();
                        startActivity(ii);

                    }
                    return status;
                } else {
                    status = "0";
                    return status;
                }
            } catch (JSONException e) {
                status = "0";
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Once the background process is done we need to Dismiss the progress dialog asap * *
         */
        protected void onPostExecute(String message) {
            if (message.equals("1")) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Welcome to NIIT Yuva Jyoti Career Opportunites App", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundc));
                textView.setTextColor(Color.rgb(24, 124, 255));
                snackbar.show();
            } else {
                //    Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please enter correct Username & Mobile  ", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundc));
                textView.setTextColor(Color.RED);
                snackbar.show();
            }
        }

    }


}