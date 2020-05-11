package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordStorageActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_storage);
        list = findViewById(R.id.list);
        File file = new File(getFilesDir() + "/" + FILE_NAME);
        if(file.length()==0)
            list.setText("No data in storage");
        else
            list.setText(load(new View(this)));
        Button update = findViewById(R.id.update);
        configureBackButton();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                File file = new File(getFilesDir() + "/" + FILE_NAME);
                if(file.length()==0)
                    list.setText("No data in storage");
                else
                    list.setText(load(view));
        }
        });
    }

    private void configureBackButton(){
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    public String load(View v) {
        FileInputStream f = null;
        StringBuilder sb = new StringBuilder();
        try {
            f = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(f);
            BufferedReader br= new BufferedReader(isr);
            String text;

            while((text=br.readLine())!=null){
                sb.append(text).append("\n");
            }
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
        return sb.toString();
    }
}
