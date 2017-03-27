package com.example.vidya.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    String data;
    private String file = "contactManager.txt";
    List<Map<String, String>> contactList = new ArrayList<Map<String,String>>();
    List<String> contacts = new ArrayList<>();
    public String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/ContactManager";
    //File file = new File(sdcard,"contactManager.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(MainActivity.this, NewActivity.class));
            }
        });

        initList();//Initialize the list view

         ListView lv = (ListView) findViewById(R.id.listView);
        SimpleAdapter simpleAapt = new SimpleAdapter(this, contactList, android.R.layout.simple_list_item_1, new String[] {"person"}, new int[] {android.R.id.text1});
        lv.setAdapter(simpleAapt);
        //lv.setOnItemClickListener();
        //lv.setOnItemClickListener(new OnItemClickListenerListViewItem());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = parent.getAdapter().getItem(position).toString();

                String[] recordValue = selectedFromList.split("\t");
                Toast.makeText(MainActivity.this, "you clicked "+ recordValue[1], Toast.LENGTH_LONG).show();
            }
        });



    }

    private void initList() {
        save();

        read();


    }
    public void read()
    {


        try {
            FileInputStream fin = openFileInput(file);

            InputStreamReader isr = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String contents;
            while ((contents = bufferedReader.readLine()) != null) {
                sb.append(contents);
                contacts.add(contents);
            }
            for (int i = 0; i < contacts.size(); i++) {
                String[] recordValue = contacts.get(i).split("\t");
                contactList.add(createContact("person", recordValue[0]+"\t"+recordValue[1]+"\t"+recordValue[2]));
            }

        }
        catch(Exception e){
        }
    }
    public void save()
    {

        data="Vidya\tMani\t4692799257\tsm.vidya@gmail.com\t11/25/1992\rShruti\tBidada\t1231231223\tshrti.bidada@gmail.com\t12/12/1992\n";
        try {
            FileOutputStream fOut = openFileOutput(file,Context.MODE_PRIVATE);
            fOut.write(data.getBytes());
            fOut.close();
            Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> createContact(String key, String name) {
        HashMap<String, String> contact = new HashMap<String, String>();
        contact.put(key, name);

        return contact;
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