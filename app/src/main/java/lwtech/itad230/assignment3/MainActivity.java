package lwtech.itad230.assignment3;

/**
 * @Author Mamatha Kelu
 * MainActivity function demonstrates the timer, setting alarm and inserting contact details on android device
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate - called when activity is created
     * @param savedInstanceState - saves instance values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * onTimerButtonClick handler for TIMER button
     * @param view
     */
    protected void onTimerButtonClick(View view)
    {
        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent timerIntent = new Intent(this, TimerActivity.class);
        // pass the intent to android in the startActivity call
        startActivity(timerIntent);
    }
    /**
     * onAlarmButtonClick handler for ALARM button
     * @param view
     */
    protected void onAlarmButtonClick(View view)
    {
        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent alarmIntent = new Intent(this, AlarmActivity.class);
        // pass the intent to android in the startActivity call
        startActivity(alarmIntent);
    }
    /**
     * onContactButtonClick handler for CONTACT button
     * @param view
     */
    protected void onContactButtonClick(View view)
    {
        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent contactIntent = new Intent(this, ContactActivity.class);
        // pass the intent to android in the startActivity call
        startActivity(contactIntent);
    }

    /**
     * onWebButtonClick handler for CONTACT button
     * @param view
     */
    protected void onWebButtonClick(View view)
    {
        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent webIntent = new Intent(this, WebActivity.class);
        // pass the intent to android in the startActivity call
        startActivity(webIntent);
    }
}

