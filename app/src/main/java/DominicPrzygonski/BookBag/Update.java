package DominicPrzygonski.BookBag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    private EditText UpdateName, UpdateAuthor, UpdateLength, UpdatePrequels, UpdateSequels, UpdateGenre, UpdateCompletion;
    private Button UpdateButton, DeleteButton, BackButton;
    private String id, name, author, length, prequels, sequels, genre, completion;

    //Animation Background
    private ConstraintLayout base_layout;
    private AnimationDrawable animDrawable;
    private Animation anim;
    private LinearLayout stripes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Creating background animation -Gradient-
        base_layout = (ConstraintLayout) findViewById(R.id.base_layout1);
        animDrawable = (AnimationDrawable) base_layout.getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        //-Stripes-
        stripes = (LinearLayout) findViewById(R.id.stripes);
        anim = (Animation) AnimationUtils.loadAnimation(this, R.anim.strip_anim);
        stripes.startAnimation(anim);

        //Initialising
        UpdateName = findViewById(R.id.UpdateName);
        UpdateAuthor = findViewById(R.id.UpdateAuthor);
        UpdateLength = findViewById(R.id.UpdateLength);
        UpdatePrequels = findViewById(R.id.UpdatePrequels);
        UpdateSequels = findViewById(R.id.UpdateSequels);
        UpdateGenre = findViewById(R.id.UpdateGenre);
        UpdateCompletion = findViewById(R.id.UpdateCompletion);
        UpdateButton = findViewById(R.id.UpdateBook);
        DeleteButton = findViewById(R.id.DeleteBook);
        BackButton = findViewById(R.id.BackUpdateButton);
        getAndSetIntentData();

        //Setting title bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(name);

        //Onclick for buttons
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseSQL db = new DatabaseSQL(Update.this);
                name = UpdateName.getText().toString().trim();
                author = UpdateAuthor.getText().toString().trim();
                length = UpdateLength.getText().toString().trim();
                prequels = UpdatePrequels.getText().toString().trim();
                sequels = UpdateSequels.getText().toString().trim();
                genre = UpdateGenre.getText().toString().trim();
                completion = UpdateCompletion.getText().toString().trim();
                db.updateData(id, name, author, length, prequels, sequels, genre, completion);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update.this, ViewData.class);
                startActivity(intent);
            }
        });
    }

    //Methods
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
        getIntent().hasExtra("name") &&
        getIntent().hasExtra("author") &&
        getIntent().hasExtra("length") &&
        getIntent().hasExtra("prequels") &&
        getIntent().hasExtra("sequels") &&
        getIntent().hasExtra("genre") &&
        getIntent().hasExtra("completion")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            author = getIntent().getStringExtra("author");
            length = getIntent().getStringExtra("length");
            prequels = getIntent().getStringExtra("prequels");
            sequels = getIntent().getStringExtra("sequels");
            genre = getIntent().getStringExtra("genre");
            completion = getIntent().getStringExtra("completion");

            UpdateName.setText(name);
            UpdateAuthor.setText(author);
            UpdateLength.setText(length);
            UpdatePrequels.setText(prequels);
            UpdateSequels.setText(sequels);
            UpdateGenre.setText(genre);
            UpdateCompletion.setText(completion);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseSQL db = new DatabaseSQL(Update.this);
                db.deleteData(id);
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