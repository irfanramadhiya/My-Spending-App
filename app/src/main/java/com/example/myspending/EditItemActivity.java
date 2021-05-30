package com.example.myspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    Spending spending = new Spending();
    ArrayList<Spending> spendings = new ArrayList<Spending>();
    SpendingDB spendingDB = new SpendingDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        EditText et_name, et_nominal;
        Button btn_submit;
        et_name = findViewById(R.id.et_name_edit);
        et_nominal = findViewById(R.id.et_nominal_edit);
        btn_submit = findViewById(R.id.btn_submit_edit);

        spending = spendingDB.getSpending(id);

        String name = spending.name;
        String nominal = "" + spending.nominal;

        et_name.setText(name);
        et_nominal.setText(nominal);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String nominal = et_nominal.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(EditItemActivity.this, "Please input the name of the spending", Toast.LENGTH_SHORT).show();
                }
                else if(nominal.isEmpty()){
                    Toast.makeText(EditItemActivity.this, "Please input the nominal of the spending", Toast.LENGTH_SHORT).show();
                }
                else {
                    spending.name = name;
                    spending.nominal = Integer.parseInt(nominal);
                    spendingDB.updateSpending(spending, id);

                    Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}