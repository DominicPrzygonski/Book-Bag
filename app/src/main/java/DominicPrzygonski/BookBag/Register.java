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

public class Register extends AppCompatActivity {

    private Button RegisterAccount, BackAccount;
    private EditText UsernameEdit, PasswordEdit;

    //Animation Background
    private ConstraintLayout base_layout;
    private AnimationDrawable animDrawable;
    private Animation anim;
    private LinearLayout stripes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        UsernameEdit = (EditText) findViewById(R.id.UsernameEdit);
        PasswordEdit = (EditText) findViewById(R.id.PasswordEdit);

        //Button initializing
        RegisterAccount = (Button) findViewById(R.id.RegisterAccount);
        BackAccount = (Button) findViewById(R.id.BackAccount);

        //Onclick for buttons
        RegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSQL db = new DatabaseSQL(Register.this);
                db.addAccount(
                        UsernameEdit.getText().toString().trim(),
                        PasswordEdit.getText().toString().trim());

                Intent intent = new Intent(Register.this, Launcher.class);
                startActivity(intent);
            }
        });
        BackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Launcher.class);
                startActivity(intent);
            }
        });
    }
}