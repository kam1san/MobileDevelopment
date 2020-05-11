package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Switch s1; EditText pass; Button show; TextView ans; TextView list; Button show_saved;
    private static final String FILE_NAME = "example.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1=findViewById(R.id.pass_switch);
        pass=findViewById(R.id.pass);
        show=findViewById(R.id.show_pass);
        ans=findViewById(R.id.answer);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1.isChecked()) pass.setInputType(InputType.TYPE_CLASS_TEXT);
                else pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(""))
                    ans.setText("Please input password");
                else
                    ans.setText(pass.getText());
            }
        });

    }

    public void save (View v){
        String txt = pass.getText().toString();
        FileOutputStream f = null;
        try {
            f = openFileOutput(FILE_NAME, MODE_PRIVATE);
            f.write(txt.getBytes());
            pass.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(f!=null){
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void load(View v) {
        FileInputStream f = null;
        try {
            f = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(f);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text=br.readLine())!=null){
                sb.append(text).append("\n");
            }
            list.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(f!=null){
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
