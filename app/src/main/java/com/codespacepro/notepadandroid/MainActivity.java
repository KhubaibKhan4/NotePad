package com.codespacepro.notepadandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    List<Notepad> notepadList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notepadList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child("Users").getChildren()) {
                    if (dataSnapshot.hasChild("username") && dataSnapshot.hasChild("fullname") && dataSnapshot.hasChild("email")) {

                        String username = dataSnapshot.child("username").getValue(String.class);
                        String fullname = dataSnapshot.child("fullname").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);

                        Notepad notepad = new Notepad(username, fullname, email);
                        notepadList.add(notepad);
                    }
                    recyclerView.setAdapter(new NotepadAdapter(MainActivity.this, notepadList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}