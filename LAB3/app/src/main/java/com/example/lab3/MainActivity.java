package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Switch s1; EditText pass; Button show; TextView ans; TextView list; Button save;
    private static final String FILE_NAME = "example.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1=findViewById(R.id.pass_switch);
        pass=findViewById(R.id.pass);
        show=findViewById(R.id.show_pass);
        ans=findViewById(R.id.answer);
        list=findViewById(R.id.list);
        save=findViewById(R.id.save);

        configureNextButton();

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

    }

    public void save (View v){
        FileOutputStream f = null;
        try {
            if(pass.getText().toString().equals(""))
                Toast.makeText(this,"Nothing saved", Toast.LENGTH_LONG).show();
            else{

                File file = new File(getFilesDir() + "/" + FILE_NAME);
                if(file.length()==0) {
                    f = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    f.write(pass.getText().toString().trim().getBytes());
                }
                else {
                    f = openFileOutput(FILE_NAME, MODE_APPEND);
                    String saved = "\n" + pass.getText().toString().trim() ;
                    f.write(saved.getBytes());
                }
                Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
            }
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

    private void configureNextButton(){
        Button storage = (Button) findViewById(R.id.open_storage);
        storage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, PasswordStorageActivity.class));
            }
        }
        );
    }

}
