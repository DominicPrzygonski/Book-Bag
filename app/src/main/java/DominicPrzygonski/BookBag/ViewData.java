package DominicPrzygonski.BookBag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity{

    RecyclerView recyclerView;

     DatabaseSQL db;
     ArrayList<String> ID, Name, Author, Length, Prequels, Sequels, Genre, Completion;
     Adapter adapter;
     private Button ViewBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);


        //Initialising
        recyclerView = findViewById(R.id.recycleView);
        ViewBackButton = (Button) findViewById(R.id.ViewBackButton);
        db = new DatabaseSQL(ViewData.this);
        ID = new ArrayList<>();
        Name = new ArrayList<>();
        Author = new ArrayList<>();
        Length = new ArrayList<>();
        Prequels = new ArrayList<>();
        Sequels = new ArrayList<>();
        Genre = new ArrayList<>();
        Completion = new ArrayList<>();

        storeData();
        adapter = new Adapter(ViewData.this, ViewData.this, ID, Name, Author, Length, Prequels, Sequels, Genre, Completion);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewData.this));

        //Onclick for buttons
        ViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewData.this, Menu.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                ID.add(cursor.getString(0));
                Name.add(cursor.getString(1));
                Author.add(cursor.getString(2));
                Length.add(cursor.getString(3));
                Prequels.add(cursor.getString(4));
                Sequels.add(cursor.getString(5));
                Genre.add(cursor.getString(6));
                Completion.add(cursor.getString(7));
            }
        }
    }


    //Delete All
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseSQL db = new DatabaseSQL(ViewData.this);
                db.deleteAllData();
                Intent intent = new Intent(ViewData.this, ViewData.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}