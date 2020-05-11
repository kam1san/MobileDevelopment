package com.example.lab2_v2;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstFragment extends Fragment {
Switch s1; EditText pass; Button show; TextView list; Button show_saved;
    Context context = getContext();
    private static final String FILE_NAME = "example.txt";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        s1=view.findViewById(R.id.pass_switch);
        pass=view.findViewById(R.id.pass);
        show=view.findViewById(R.id.show_pass);
        list = view.findViewById(R.id.list);
        show_saved = view.findViewById(R.id.show_saved);
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
                String send=pass.getText().toString();
                MainActivity m1= (MainActivity) getActivity();
                m1.get_pass(send);
                save(view);
            }
        });

        show_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(view);
            }
        });
        }

    public void save (View v){
        String txt = pass.getText().toString();
        FileOutputStream f = null;
        try {
            f = context.openFileOutput(FILE_NAME, context.MODE_PRIVATE);
            f.write(txt.getBytes());
            pass.getText().clear();
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
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
            f = context.openFileInput(FILE_NAME);
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
