package expert.android.quoccuong.contentprovider;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "Cuong";

    private static final int PERMISSION_REQUEST_READ_WHITE_CONTACTS = 1;

    private Button mBtnLoadData, mAddContact, mRemoveContact, mUpdateContact;
    private TextView mTextViewQueryResult;
    private EditText mEdtContactName;

    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};

    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?"; // 'abc'

    private String[] mSelectionArguments = new String[]{"abc"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

    private boolean firstTimeLoaded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        mTextViewQueryResult = findViewById(R.id.textViewQueryResult);
        mBtnLoadData = findViewById(R.id.btn_loadData);
        mAddContact = findViewById(R.id.btn_addContact);
        mRemoveContact = findViewById(R.id.btn_removeContact);
        mUpdateContact = findViewById(R.id.btn_updateContact);
        mEdtContactName = findViewById(R.id.edt_contact_name);

        mBtnLoadData.setOnClickListener(this);
        mAddContact.setOnClickListener(this);
        mRemoveContact.setOnClickListener(this);
        mUpdateContact.setOnClickListener(this);

    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_WHITE_CONTACTS);
        } else {
            getContactNames();
        }
    }

    private void getContactNames() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                mOrderBy);

        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                sb.append(cursor.getString(0) + ", " + cursor.getString(1) +
                        ", " + cursor.getString(2) + "\n");
            }
            mTextViewQueryResult.setText(sb.toString());
        } else {
            mTextViewQueryResult.setText("No contacts in device");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loadData:
//                showContacts();
                // 1: identifier of loader
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, PERMISSION_REQUEST_READ_WHITE_CONTACTS);
                } else {
                    if (firstTimeLoaded) {
                        getLoaderManager().initLoader(1, null, this);
                        firstTimeLoaded = false;
                    } else { // in first time, cursor is at the end of table so reload to get new cursor
                        getLoaderManager().restartLoader(1, null, this);
                    }
                }
                break;
            case R.id.btn_addContact:
                addContact();
                break;
            case R.id.btn_removeContact:
                removeContact();
                break;
            case R.id.btn_updateContact:
                updateContact();
                break;
            default:
                break;
        }
    }

    private void addContact() {
    }

    private void removeContact() {
        String whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = '" + mEdtContactName.getText().toString() + "'";
        Log.d(TAG, whereClause);
        getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, null);
    }

    private void updateContact() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, mColumnProjection, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                sb.append(cursor.getString(0) + ", " + cursor.getString(1) +
                        ", " + cursor.getString(2) + "\n");
            }
            mTextViewQueryResult.setText(sb.toString());
        } else {
            mTextViewQueryResult.setText("No contacts in device");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
