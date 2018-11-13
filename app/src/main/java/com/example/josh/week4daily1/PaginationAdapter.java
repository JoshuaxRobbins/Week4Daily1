package com.example.josh.week4daily1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josh.week4daily1.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.PersonVH>{
    List<Person> personList;

    public PaginationAdapter() {
       personList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PersonVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_list_item,viewGroup,false);
        return new PersonVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonVH personVH, int i) {
        Person person = personList.get(i);
        personVH.tvName.setText(person.getName());
        personVH.tvLocation.setText(person.getLocation());
        personVH.tvEmail.setText(person.getEmail());
        personVH.tvAge.setText(person.getAge());
        personVH.tvNumber.setText(person.getPhoneNumber());
        personVH.tvGender.setText(person.getGender());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void add(List<Person> listToAdd){


        personList.addAll(listToAdd);
    }



    public class PersonVH extends RecyclerView.ViewHolder{


        private final TextView tvName;
        private final TextView tvLocation;
        private final TextView tvEmail;
        private final TextView tvAge;
        private final TextView tvNumber;
        private final TextView tvGender;

        public PersonVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }

}
