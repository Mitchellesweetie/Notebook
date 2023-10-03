package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // TextView text;
    ListView listView;
    DBHelper mydb;
    ArrayList<MessageInfo>arrayList;
    MessageAdapter messageAdapter;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=findViewById(R.id.image2);
        listView=findViewById(R.id.listview);
       // text=findViewById(R.id.text);
        mydb=new DBHelper(this);

        MessageAdapter messageAdapter;

        showData();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MessageAdapter.class);
                startActivity(intent);
                View v=getLayoutInflater().inflate(R.layout.activity_message_adapter,null);

                final EditText text=v.findViewById(R.id.message);

                String message=text.getText().toString();
                boolean res= mydb.insertMessage(message);
                if (res==true)
                {showData();
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();}
                else
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        arrayList=mydb.getStudentInfo();
        messageAdapter=new MessageAdapter(this,arrayList);
        listView.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
    }
}