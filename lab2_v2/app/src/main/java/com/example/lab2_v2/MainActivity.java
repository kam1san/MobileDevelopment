package com.example.lab2_v2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction t=fm.beginTransaction();
        FirstFragment f1=new FirstFragment();
        t.add(R.id.frameLayout3,f1);
        t.commit();
}

public void get_pass(String s1){
Bundle bundle = new Bundle();
bundle.putString("password", s1);
SecondFragment sf = new SecondFragment();
sf.setArguments(bundle);
getSupportFragmentManager().beginTransaction().replace(R.id.fragment10,sf).commit();
}


}
