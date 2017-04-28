package lwtech.itad230.assignment3;

/**
 * @Author Mamatha Kelu
 * TimerActivity function demonstrates the implicit intent for timer
 */

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class TimerActivity extends AppCompatActivity {

    EditText durationText;
    EditText messageText;
    public static final String MAINACTIVITY = "MAINACTIVITY";
    private static final String KEY_EDITTEXTDURATION = "editTextDuration";
    private static final String KEY_EDITTEXTMESSAGE = "editTextMessage";
    private static final int TIMER_REQUEST = 1;

    /**
     * onCreate - called when activity is created
     * @param savedInstanceState - saves instance values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        durationText = (EditText)findViewById(R.id.durationEdit);
        messageText = (EditText)findViewById(R.id.messageEdit);

        /*Restore the values when activity is recreated*/
        String strEditDuration = "";
        String strEditMessage = "";

        if (savedInstanceState != null) {
            strEditDuration = savedInstanceState.getString(KEY_EDITTEXTDURATION, "<default string>");
            durationText.setText(strEditDuration);
            strEditMessage = savedInstanceState.getString(KEY_EDITTEXTMESSAGE, "<default string>");
            messageText.setText(strEditMessage);
        }

    }

    /**
     * onSaveInstanceState function saves the values for activity recreation
     * @param savedInstanceState values save din the bundle
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the string data from the EditText widget
        String strEditDuration = durationText.getText().toString();
        String strEditMessage = messageText.getText().toString();
        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTDURATION, strEditDuration);
        savedInstanceState.putString(KEY_EDITTEXTMESSAGE, strEditMessage);
    }
    /**
     * onSetTimerButtonClick handler for SET TIMER button
     * @param view
     */
    protected void onSetTimerButtonClick(View view)
    {
        int duration; /* Timer seconds information*/
        String message;/* Message displayed when timer is up*/

        /*Get duration and message from text box*/
        duration = Integer.parseInt(durationText.getText() + "");
        message = messageText.getText().toString();

        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);

        // We can add extra information to the intent with "extra's". The
        // putExtra method is overloaded so the value has a number of possible
        // types
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
        intent.putExtra(AlarmClock.EXTRA_LENGTH, duration);

        // intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        //Check any one of the android apps implement the implicit intent ACTION_SET_TIMER
        if (intent.resolveActivity(getPackageManager()) != null) {

            // pass the intent to android in the startActivityForResult call
           startActivityForResult(intent, TIMER_REQUEST);
           // startActivity(intent);
        }
    }
    /**
     * onCancelTimerButtonClick handler for CANCEL button
     * @param view
     */
    protected void onCancelTimerButtonClick(View view)
    {
        finish();
    }

    /**
     * onActivityResult called when another activity sends result
     * @param requestCode - request code tom be respond to
     * @param resultCode - result code successful or cancel
     * @param data result
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == TIMER_REQUEST) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Log.d(MAINACTIVITY, "..RESULT_OK..");
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Log.d(MAINACTIVITY, "..RESULT_CANCELED..");
            }
        }
    }
}
