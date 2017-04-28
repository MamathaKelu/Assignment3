package lwtech.itad230.assignment3;

/**
 * @Author Mamatha Kelu
 * AlarmActivity function demonstrates the implicit intent for alarm
 */
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AlarmActivity extends AppCompatActivity {
    public static final String MAINACTIVITY = "MAINACTIVITY";
    private static final String KEY_EDITTEXTHOURS = "editTextHours";
    private static final String KEY_EDITTEXTMINUTES = "editTextMinutes";
    private static final String KEY_EDITTEXTSECONDS = "editTextSeconds";
    private static final int ALARM_REQUEST = 1;

    EditText EditHours;
    EditText EditMinutes;
    EditText EditSeconds;

    /**
     * onCreate - called when activity is created
     * @param savedInstanceState - saves instance values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        EditHours = (EditText)findViewById(R.id.hoursEdit);
        EditMinutes = (EditText)findViewById(R.id.minutesEdit);
        EditSeconds = (EditText)findViewById(R.id.secondsEdit);

         /*Restore the values when activity is recreated*/
        String strEditHours = "";
        String strEditMinutes = "";
        String strEditSeconds = "";

        if (savedInstanceState != null) {
            strEditHours = savedInstanceState.getString(KEY_EDITTEXTHOURS, "<default string>");
            EditHours.setText(strEditHours);
            strEditMinutes = savedInstanceState.getString(KEY_EDITTEXTMINUTES, "<default string>");
            EditMinutes.setText(strEditMinutes);
            strEditSeconds = savedInstanceState.getString(KEY_EDITTEXTSECONDS, "<default string>");
            EditSeconds.setText(strEditSeconds);
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
        String strEditHours = EditHours.getText().toString();
        String strEditMinutes = EditMinutes.getText().toString();
        String strEditSeconds = EditSeconds.getText().toString();
        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTHOURS, strEditHours);
        savedInstanceState.putString(KEY_EDITTEXTMINUTES, strEditMinutes);
        savedInstanceState.putString(KEY_EDITTEXTSECONDS, strEditSeconds);
    }
    /**
     * onSetAlarmButtonClick handler for SET ALARM button
     * @param view
     */
    protected void onSetAlarmButtonClick(View view)
    {
        int hours, minutes,seconds; /* hours, minutes and seconds*/

        /*Get hours, minutes and seconds from text box*/
        hours = Integer.parseInt(EditHours.getText() + "");
        minutes = Integer.parseInt(EditMinutes.getText() + "");
        seconds = Integer.parseInt(EditSeconds.getText() + "");

        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

        // We can add extra information to the intent with "extra's". The
        // putExtra method is overloaded so the value has a number of possible
        // types
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up");
        intent.putExtra(AlarmClock.EXTRA_HOUR, hours);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        //intent.putExtra(AlarmClock.EXTRA_LENGTH, seconds);

        // intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        //Check any one of the android apps implement the implicit intent ACTION_SET_ALARM
        if (intent.resolveActivity(getPackageManager()) != null) {

            // pass the intent to android in the startActivityForResult call
            startActivityForResult(intent, ALARM_REQUEST);
        }

    }
    /**
     * onCancelAlarmButtonClick handler for CANCEL button
     * @param view
     */
    protected void onCancelAlarmButtonClick(View view)
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
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == ALARM_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.d(MAINACTIVITY, "..RESULT_OK..");

            }
            else if (resultCode == RESULT_CANCELED) {
                Log.d(MAINACTIVITY, "..RESULT_CANCELED..");

            }
        }
    }
}
