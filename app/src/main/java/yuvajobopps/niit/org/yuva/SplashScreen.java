package yuvajobopps.niit.org.yuva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Deepak Upadhyay on 18-Jul-16.
 */
public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    private int progressStatus = 0;
    String student_id;
    SharedPreferences sharedPreferences;
    private Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        //sharedPreferences.edit().remove("student_id").commit();
        //sharedPreferences.edit().clear().commit();
        student_id = sharedPreferences.getString("student_id", null);
        //Log.e("SplashScreen:student_id-->",student_id);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //textView.setText(progressStatus + "/" + progressBar.getMax());rgb(238,118,0)
                            progressBar.getProgressDrawable().setColorFilter(Color.rgb(0, 0, 205), PorterDuff.Mode.SRC_IN);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                // username = sharedPreferences.getString("username", null);
                //  password = sharedPreferences.getString("password", null);
                if(student_id!=null)
                {

                    Intent i = new Intent(getApplicationContext(),Home.class);
                    finish();
                    startActivity(i);

                }
                else {
                    Intent i = new Intent(getApplicationContext(), LoginForm.class);
                    finish();
                    startActivity(i);
                }
            }
        }).start();


    }
}
