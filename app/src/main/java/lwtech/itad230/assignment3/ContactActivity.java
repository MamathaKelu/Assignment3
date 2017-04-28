package lwtech.itad230.assignment3;

/**
 * @Author Mamatha Kelu
 * ContactActivity function demonstrates the implicit intent for inserting contact
 */
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {
    public static final String MAINACTIVITY = "MAINACTIVITY";
    private static final String KEY_EDITTEXTNAME = "editTextName";
    private static final String KEY_EDITTEXTEMAIL = "editTextEmail";
    private static final String KEY_EDITTEXTPHONE = "editTextPhone";
    private static final int CONTACT_REQUEST = 1;


    EditText editName;
    EditText editEmail;
    EditText editPhone;

    /**
     * onCreate - called when activity is created
     * @param savedInstanceState - saves instance values
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPhone = (EditText)findViewById(R.id.editPhone);

         /*Restore the values when activity is recreated*/
        String strEditName = "";
        String strEditEmail = "";
        String strEditPhone = "";

        if (savedInstanceState != null) {
            strEditName = savedInstanceState.getString(KEY_EDITTEXTNAME, "<default string>");
            editName.setText(strEditName);
            strEditEmail = savedInstanceState.getString(KEY_EDITTEXTEMAIL, "<default string>");
            editEmail.setText(strEditEmail);
            strEditPhone = savedInstanceState.getString(KEY_EDITTEXTPHONE, "<default string>");
            editPhone.setText(strEditPhone);
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
        String strEditName = editName.getText().toString();
        String strEditEmail = editEmail.getText().toString();
        String strEditPhone = editPhone.getText().toString();
        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTNAME, strEditName);
        savedInstanceState.putString(KEY_EDITTEXTEMAIL, strEditEmail);
        savedInstanceState.putString(KEY_EDITTEXTPHONE, strEditPhone);
    }
    /**
     * onInsertContactButtonClick handler for SET ALARM button
     * @param view
     */
    protected void onInsertContactButtonClick(View view)
    {
        String name, email, phone;

        /*Get name, email and phonenumber from text box*/
        name = editName.getText().toString();
        email = editEmail.getText().toString();
        phone = editPhone.getText().toString();

        // When we want an activity to start a second activity, use an intent.
        // An intent is an "intent to do something". It's a message that we
        // send to Android, stating that we want another activity started.
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        // We can add extra information to the intent with "extra's". The
        // putExtra method is overloaded so the value has a number of possible
        // types
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);

        //Check any one of the android apps implement the implicit intent ACTION_INSERT
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, CONTACT_REQUEST);
        }

    }
    /**
     * onCancelContactButtonClick handler for CANCEL button
     * @param view
     */
    protected void onCancelContactButtonClick(View view)
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
        if (requestCode == CONTACT_REQUEST) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Log.d(MAINACTIVITY, "..RESULT_OK..");

            } else if (resultCode == RESULT_CANCELED) {

                Log.d(MAINACTIVITY, "..RESULT_CANCELED..");

            }
        }
    }
}
