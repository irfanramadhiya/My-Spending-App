package com.example.myspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    Spending spending = new Spending();
    SpendingDB spendingDB = new SpendingDB(this);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText et_name, et_nominal;
        Button btn_submit;
        btn_submit = findViewById(R.id.btn_submit_add);
        et_name = findViewById(R.id.et_name_add);
        et_nominal = findViewById(R.id.et_nominal_add);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String nominal = et_nominal.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(AddItemActivity.this, "Please input the name of the spending", Toast.LENGTH_SHORT).show();
                }
                else if(nominal.isEmpty()){
                    Toast.makeText(AddItemActivity.this, "Please input the nominal of the spending", Toast.LENGTH_SHORT).show();
                }
                else {
                    spending.name = name;
                    spending.nominal = Integer.parseInt(nominal);
                    spending.date = simpleDateFormat.format(Calendar.getInstance().getTime());
                    spendingDB.insertSpending(spending);

                    Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}