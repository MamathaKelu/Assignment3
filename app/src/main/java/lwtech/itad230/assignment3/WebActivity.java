package lwtech.itad230.assignment3;
/**
 * @Author Mamatha Kelu
 * ContactActivity function demonstrates the implicit intent for inserting contact
 */
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity {
    public static final String MAINACTIVITY = "MAINACTIVITY";
    private static final String KEY_EDITTEXTURL = "editTextUrl";
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";
    private static final int WEB_REQUEST = 1;


    EditText editUrl;

    /**
     * onCreate - called when activity is created
     * @param savedInstanceState - saves instance values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        editUrl = (EditText)findViewById(R.id.urlEdit);


         /*Restore the values when activity is recreated*/
        String strEditUrl = "";


        if (savedInstanceState != null) {
            strEditUrl = savedInstanceState.getString(KEY_EDITTEXTURL, "<default string>");
            editUrl.setText(strEditUrl);

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
        String strEditUrl = editUrl.getText().toString();
        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTURL, strEditUrl);

    }
    /**
     * onOpenWebPageButtonClick handler for SET ALARM button
     * @param view
     */
    protected void onOpenWebPageButtonClick(View view)
    {
        String url; /* url example www.google.com*/

        /*Get url from text box*/
        url = editUrl.getText().toString();

        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }
        Uri webpage = Uri.parse(url);

        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //intent.setData(Uri.parse(url));
        //intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        //Check any one of the android apps implement the implicit intent ACTION_VIEW
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, WEB_REQUEST);
        }

    }
    /**
     * onCancelOpenWebPageButtonClick handler for CANCEL button
     * @param view
     */
    protected void onCancelOpenWebPageButtonClick(View view)
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
        if (requestCode == WEB_REQUEST) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Log.d(MAINACTIVITY, "..RESULT_OK..");

            } else if (resultCode == RESULT_CANCELED) {

                Log.d(MAINACTIVITY, "..RESULT_CANCELED..");

            }
        }
    }
}
