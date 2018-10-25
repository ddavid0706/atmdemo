package com.example.david.atm;

import android.content.Intent;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    boolean logon = false;
    private List<Function> functions;
    //String[] function = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!logon) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
//            startActivity(intent);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //RecyclerView
        setupFunctions();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // Adapter
//        FunctionAdapter adapter = new FunctionAdapter(this);
        iconAdapter adapter = new iconAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void setupFunctions() {
        functions = new ArrayList<>();
        String[] funs = getResources().getStringArray(R.array.function);
        functions.add(new Function(funs[0], R.drawable.func_transaction));
        functions.add(new Function(funs[1], R.drawable.func_balance));
        functions.add(new Function(funs[2], R.drawable.func_finance));
        functions.add(new Function(funs[3], R.drawable.func_contacts));
        functions.add(new Function(funs[4], R.drawable.func_exit));
    }

    public class iconAdapter extends RecyclerView.Adapter<iconAdapter.iconHolder> {
        @NonNull
        @Override
        public iconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_icon, parent, false);
            return new iconHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull iconHolder holder, int position) {
            final Function function = functions.get(position);
            holder.nametext.setText(function.getName());
            holder.iconImage.setImageResource(function.getIcon());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClicked(function);
                }
            });
        }

        @Override
        public int getItemCount() {
            return functions.size();
        }

        public class iconHolder extends RecyclerView.ViewHolder {
            ImageView iconImage;
            TextView nametext;

            public iconHolder(View itemView) {
                super(itemView);
                iconImage = itemView.findViewById(R.id.item_icon);
                nametext = itemView.findViewById(R.id.item_name);
            }
        }
    }

    private void itemClicked(Function function) {
        Log.d(TAG, "itemClicked: " + function.getName());
        switch (function.getIcon()) {
            case R.drawable.func_transaction:
                break;
            case R.drawable.func_balance:
                break;
            case R.drawable.func_finance:
                break;
            case R.drawable.func_contacts:
                Intent contacts = new Intent(this, ContactActivity.class);
                startActivity(contacts);
                break;
            case R.drawable.func_exit:
                finish();
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode != RESULT_OK) {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
