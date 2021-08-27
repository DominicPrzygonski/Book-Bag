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
import android.widget.Toast;

public class Launcher extends AppCompatActivity {

    private Button Login, Register;
    private ConstraintLayout con;
    private EditText LoginUsername, LoginPassword;

    //Animation Background
    private ConstraintLayout base_layout;
    private AnimationDrawable animDrawable;
    private Animation anim;
    private LinearLayout stripes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

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

        //Button initializing
        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.Register);
        LoginUsername = (EditText) findViewById(R.id.LoginUsername);
        LoginPassword = (EditText) findViewById(R.id.LoginPassword);

        //Onclick for buttons
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSQL db = new DatabaseSQL(Launcher.this);

                if(db.checkAccount(LoginUsername.getText().toString().trim(), LoginPassword.getText().toString().trim())){
                    Intent intent = new Intent(Launcher.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Launcher.this, "Incorrect Login Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launcher.this, Register.class);
                startActivity(intent);
            }
        });



    }
}