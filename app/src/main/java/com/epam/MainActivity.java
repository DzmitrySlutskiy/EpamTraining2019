package com.epam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.epam.TestService.EXAMPLE_ACTION;
import static com.epam.TestService.EXAMPLE_ACTION_FOR_FINISH;
import static com.epam.TestService.SENT_TEXT;

public class MainActivity extends AppCompatActivity {
    private TestBroadcastReceiver testBroadcastReceiver = new TestBroadcastReceiver();
    private TextView textView;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (EXAMPLE_ACTION.equals(intent.getAction())) {
                String receivedText = intent.getStringExtra(SENT_TEXT);
                textView.setText(receivedText);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.from_service_text_view);

        findViewById(R.id.go_to_activity_1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity1.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.go_to_activity_2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.go_to_activity_3_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.start_service_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestService.class);
                startService(intent);
            }
        });

        findViewById(R.id.stop_service_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestService.class);
                stopService(intent);
            }
        });

        IntentFilter startFilter = new IntentFilter (EXAMPLE_ACTION);
        IntentFilter filterFinish = new IntentFilter(EXAMPLE_ACTION_FOR_FINISH);
        registerReceiver(testBroadcastReceiver, startFilter);
        registerReceiver(testBroadcastReceiver, filterFinish);
        registerReceiver(broadcastReceiver, startFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(testBroadcastReceiver);
        unregisterReceiver(broadcastReceiver);
    }
}
