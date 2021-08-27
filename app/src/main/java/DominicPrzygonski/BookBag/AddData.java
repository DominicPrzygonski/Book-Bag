package DominicPrzygonski.BookBag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class AddData extends AppCompatActivity {

    private Button AddBook, BackButton;
    private EditText Name, Author, Length, Prequels, Sequels, Genre, Completion;

    //Animation Background
    private ConstraintLayout base_layout;
    private AnimationDrawable animDrawable;
    private Animation anim;
    private LinearLayout stripes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

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
        AddBook = (Button) findViewById(R.id.AddBook);
        BackButton = (Button) findViewById(R.id.BackAddButton);
        Name = (EditText) findViewById(R.id.Name);
        Author = (EditText) findViewById(R.id.Author);
        Length = (EditText) findViewById(R.id.Length);
        Prequels = (EditText) findViewById(R.id.Prequels);
        Sequels = (EditText) findViewById(R.id.Sequels);
        Genre = (EditText) findViewById(R.id.Genre);
        Completion = (EditText) findViewById(R.id.Completion);

        //Onclick for buttons
        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSQL db = new DatabaseSQL(AddData.this);
                db.addBook(
                        Name.getText().toString().trim(),
                        Author.getText().toString().trim(),
                        Integer.valueOf(Length.getText().toString().trim()),
                        Prequels.getText().toString().trim(),
                        Sequels.getText().toString().trim(),
                        Genre.getText().toString().trim(),
                        Completion.getText().toString().trim());

                Intent intent = new Intent(AddData.this, Menu.class);
                startActivity(intent);
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddData.this, Menu.class);
                startActivity(intent);
            }
        });

    }
}