package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.Viewholder> {

    private Context context;
    private ArrayList<ToDoModel> list;
    private ToDoInterface toDoInterface;
    // Constructor
//    public ToDoAdapter(Context context, ArrayList<ToDoModel> courseModelArrayList) {
//        this.context = context;
//        this.list = courseModelArrayList;
//    }
    public ToDoAdapter(Context context, ArrayList<ToDoModel> courseModelArrayList, ToDoInterface toDoInterface) {
        this.context = context;
        this.list = courseModelArrayList;
        this.toDoInterface = toDoInterface;
    }

//    public void ToDoList(ArrayList<ToDoModel> arr) {
//        this.list.clear();
//        this.list.addAll(arr);
//    }

    @NonNull
    @Override
    public ToDoAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
//        Viewholder viewholder = new Viewholder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ticketInterface.onCardClicked(monumentInfoArrayList.get(viewholder.getAdapterPosition()));
//            }
//        });
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        ToDoModel model = list.get(position);
        try {
            Log.d("Success:", "bytes.toString()");
            String name = model.getItemDataText();
            holder.name.setText(name);
            boolean isChecked = model.getDone();
            holder.checkBox.setChecked(isChecked);

        } catch (Exception e) {
            e.printStackTrace();
        }


//        holder.monumentname.setText(model.getMonumentName());
//        holder.monumentimg.setImageResource(model.getMonumentImage());
    }

    //    public static void updateMonuments(ArrayList<monumentInfo> new_arrayList){
//        Log.d("In updatemonument","....");
//        Log.d("new_arraylist",""+new_arrayList.size());
//        monumentInfoArrayList.clear();
//        Log.d("arraylist",""+monumentInfoArrayList.size());
//        for(int i=0;i<new_arrayList.size();i++){
//            monumentInfoArrayList.add(new_arrayList.get(i));
//        }
//        //monumentInfoArrayList.addAll(new_arrayList);
//        Log.d("arrayList",""+monumentInfoArrayList.size());
//        monumentAdapter obj = new monumentAdapter(new_arrayList);
//        obj.notifyDataSetChanged();
//
//    }
    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return list.size();
    }


    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView name;
        private Button delete;
        private CheckBox checkBox;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvname);
            delete = itemView.findViewById(R.id.btnDelete);
            checkBox = itemView.findViewById(R.id.checkbox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toDoInterface.onCheckboxClicked(list.get(getAdapterPosition()));
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDoInterface.onDeleteButtonClick(list.get(getAdapterPosition()));
                }
            });
        }

    }
}