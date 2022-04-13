package com.example.myapplication;
import static java.lang.Boolean.parseBoolean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements ToDoInterface{

    Button addItem;
    ArrayList<ToDoModel> item_list= new ArrayList<>();
    RecyclerView recyclerView;
    ToDoAdapter itemAdapter;
    AlertDialog.Builder builder;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = mDatabase.getReference();
    ToDoModel item;
    ToDoInterface toDoInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItem = findViewById(R.id.btnAddToList);
        recyclerView = findViewById(R.id.recycler_view);
        builder = new AlertDialog.Builder(this);
        Log.d("1","....");
        itemAdapter = new ToDoAdapter(MainActivity.this, item_list, this) {

        };
        recyclerView.setAdapter(itemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.d("2","....");
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Enter Task");
                builder.setMessage("Add Task Item");
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Add to list",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String uid = ref.push().getKey();
                        item = new ToDoModel(uid,input.getText().toString(),false);
                        item_list.add(item);
                        ref.child(uid).setValue(item);
                        itemAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        ValueEventListener postListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Log.d("OndataChange","After sending snapshot");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this, "Item not added", Toast.LENGTH_SHORT).show();
                            }
                        };
                        ref.addValueEventListener(postListener);
                    }
                });
                builder.show();
                Log.d("Click","End");
            }
        });

    }

    @Override
    public void onDeleteButtonClick(ToDoModel toDoModel) {
        Toast.makeText(MainActivity.this,"Task deleted",Toast.LENGTH_LONG).show();
        ref.child(toDoModel.getId()).removeValue();
        item_list.remove(toDoModel);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckboxClicked(ToDoModel toDoModel) {
        Toast.makeText(MainActivity.this,"Check Box Clicked",Toast.LENGTH_LONG).show();
        ref.child(toDoModel.getId()).child("done").setValue(!toDoModel.getDone());
        toDoModel.setDone(!toDoModel.getDone());
        itemAdapter.notifyDataSetChanged();
    }
}