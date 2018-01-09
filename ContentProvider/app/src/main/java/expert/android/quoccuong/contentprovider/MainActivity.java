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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Cuong";

    private static final int PERMISSION_REQUEST_READ_CONTACTS = 1;

    private Button mBtnLoadData;
    private TextView mTextViewQueryResult;

    private String[] mColumnProjection = new String[] {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER };

    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?"; // 'abc'

    private String[] mSelectionArguments = new String[] {"abc"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        mTextViewQueryResult = findViewById(R.id.textViewQueryResult);
        mBtnLoadData = findViewById(R.id.btn_loadData);

        mBtnLoadData.setOnClickListener(this);

    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);
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
                showContacts();
                break;
            default:
                break;
        }
    }
}
