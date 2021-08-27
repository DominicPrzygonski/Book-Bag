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
import android.widget.LinearLayout;

public class Menu extends AppCompatActivity {

    private Button ViewButton, AddDataButton, LogoutButton, ListButton;

    //Animation Background
    private ConstraintLayout base_layout;
    private AnimationDrawable animDrawable;
    private Animation anim;
    private LinearLayout stripes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        ViewButton = (Button) findViewById(R.id.ViewButton);
        AddDataButton = (Button) findViewById(R.id.AddDataButton);
        LogoutButton = (Button) findViewById(R.id.LogoutButton);
        ListButton = (Button) findViewById(R.id.ListButton);

        //Onclick for buttons
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ViewData.class);
                startActivity(intent);
            }
        });

        AddDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, AddData.class);
                startActivity(intent);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Launcher.class);
                startActivity(intent);
            }
        });
        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, BookListView.class);
                startActivity(intent);
            }
        });

    }
}