package expert.android.quoccuong.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Cuong";

    private TextView mTextViewQueryResult;

    private String[] mColumnProjection = new String[] {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER };

    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = 'Anil'";

    private String[] mSelectionArguments = new String[] {"Anil"};

    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewQueryResult = findViewById(R.id.textViewQueryResult);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                mSelectionClause,
                null,
                mOrderBy);

        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                sb.append(cursor.getString(0) + ", " + cursor.getString(1) +
                                ", " + cursor.getString(2));
            }
            mTextViewQueryResult.setText(sb.toString());
        } else {
            mTextViewQueryResult.setText("No contacts in device");
        }
    }
}
