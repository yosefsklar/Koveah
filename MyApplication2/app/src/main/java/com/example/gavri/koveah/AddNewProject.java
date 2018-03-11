package com.example.gavri.koveah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewProject extends AppCompatActivity {

    Button create;
    String book;
    int page;
    EditText seferInput;
    EditText pageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_project);

        seferInput = (EditText) findViewById(R.id.seferInput);
        pageInput = (EditText) findViewById(R.id.pageInput);
        create = (Button) findViewById(R.id.createButton);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = seferInput.getText().toString();
                page = Integer.parseInt(pageInput.getText().toString());
                MainActivity.addProject(book, page);

                System.out.println("submit pressed");
                Intent returnToMain = new Intent(AddNewProject.this, MainActivity.class);
                startActivity(returnToMain);

            }
        });
    }
}
