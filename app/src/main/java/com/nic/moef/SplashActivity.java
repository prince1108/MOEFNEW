package com.nic.moef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread lTimer = new Thread() {
            public void run() {
                try {
                    int lTimer1 = 0;
                    while (lTimer1 < 5000) {
                        sleep(100);
                        lTimer1 = lTimer1 + 100;
                    }
                    Intent intent=new Intent(SplashActivity.this,WalkThrouActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        lTimer.start();
    }
}
