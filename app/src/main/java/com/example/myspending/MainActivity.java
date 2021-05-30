package com.example.myspending;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SpendingAdapter.OnSpendingListener {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    SpendingDB spendingDB = new SpendingDB(this);
    ArrayList<Spending> spendings = new ArrayList<Spending>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spendings = spendingDB.getAll();

        RecyclerView rv_spending = findViewById(R.id.rv_spending);
        SpendingAdapter spendingAdapter = new SpendingAdapter();
        rv_spending.addItemDecoration(new DividerItemDecoration(rv_spending.getContext(), DividerItemDecoration.VERTICAL));
        spendingAdapter.setData(spendings, this);
        rv_spending.setAdapter(spendingAdapter);
        rv_spending.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void OnSpendingClick(int position) {
        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
        int id = spendings.get(position).id;
        intent.putExtra("id", id);
        startActivity(intent);

    }
}