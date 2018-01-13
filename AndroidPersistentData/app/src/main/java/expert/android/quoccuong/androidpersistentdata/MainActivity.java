package expert.android.quoccuong.androidpersistentdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtMessage;
    private Button btnRead, btnWrite;
    private TextView txtMessage;

    private static final String FILENAME = "sample.txt";

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
        super.onResume();
        populateTheReadText();
    }

    private void populateTheReadText() {
        try {
            txtMessage.setText(readFromFile(FILENAME));
            txtMessage.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            e.printStackTrace();
            txtMessage.setVisibility(View.GONE);
        }
    }

    private String readFromFile(String fileName) throws IOException {
        String readString = "";

        FileInputStream fileInputStream = openFileInput(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        if ((readString = bufferedReader.readLine()) != null) {
            stringBuilder.append(readString);
        }
        inputStreamReader.close();
        return stringBuilder.toString();
    }

    private void writeContentToFile() {
        String text = edtMessage.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            try {
                writeToFile(FILENAME, text, MODE_PRIVATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeToFile(String fileName, String sourceText, int mode)
                    throws IOException {

        FileOutputStream fileOutputStream = openFileOutput(fileName, mode);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(sourceText);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }
}
