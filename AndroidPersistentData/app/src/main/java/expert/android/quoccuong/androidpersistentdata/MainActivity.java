package expert.android.quoccuong.androidpersistentdata;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private EditText edtMessage;
    private Button btnRead, btnWrite;
    private TextView txtMessage;

    private static final String FILENAME = "sample.txt";

    private SharedPreferences sharedPreferences;

    private UserAction recentUserAction;

    enum UserAction {
        READ, WRITE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMessage = findViewById(R.id.edt_message);
        txtMessage = findViewById(R.id.textviewFromFile);
        btnRead = findViewById(R.id.btn_read_from_file);
        btnWrite = findViewById(R.id.btn_write_to_file);

        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);

        Log.d("Cuong", "OnCreate");

//        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE); context
        sharedPreferences = getPreferences(MODE_PRIVATE); // activity
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_read_from_file:
                populateTheReadText();
                break;
            case R.id.btn_write_to_file:
                writeContentToFile();
                break;
        }
    }

    @Override
    protected void onResume() {
        Log.d("Cuong", "onResume");
        super.onResume();
        populateTheReadText();
    }

    private void populateTheReadText() {
        recentUserAction = UserAction.READ;
        txtMessage.setText(sharedPreferences.getString("sample_key", "String not found"));
        /*try {
            if (arePermissionGranted(EXTERNAL_STORAGE_READ_WRITE_PERMISSION)) {
                txtMessage.setText(readTextFromExternalStorage(FILENAME));
                txtMessage.setVisibility(View.VISIBLE);
            } else {
                requestRuntimePermission(this, EXTERNAL_STORAGE_READ_WRITE_PERMISSION, EXTERNAL_STORAGE_PERMISSION,
                        "to READ/WRITE to external storage");
            }
        } catch (IOException e) {
            e.printStackTrace();
            txtMessage.setVisibility(View.GONE);
        }*/
    }

    private void writeContentToFile() {
        recentUserAction = UserAction.WRITE;
        String text = edtMessage.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("sample_key", text);
            editor.commit();
            /*try {
                if (arePermissionGranted(EXTERNAL_STORAGE_READ_WRITE_PERMISSION)) {
                    writeToExternalStorageFile(FILENAME, text);
                } else {
                    requestRuntimePermission(this, EXTERNAL_STORAGE_READ_WRITE_PERMISSION, EXTERNAL_STORAGE_PERMISSION,
                                    "to READ/WRITE to external storage");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (arePermissionGranted(permissions) && requestCode == EXTERNAL_STORAGE_PERMISSION) {
            if (recentUserAction == UserAction.WRITE) {
                writeContentToFile();
            } else if (recentUserAction == UserAction.READ) {
                populateTheReadText();
            }
        }
    }
}
