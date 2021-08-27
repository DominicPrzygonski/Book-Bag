package DominicPrzygonski.BookBag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class BookListView extends AppCompatActivity {

    DatabaseSQL db;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayAdapter adapter;
    ListView ListViewActivity;
    Button ListBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Initialising
        db = new DatabaseSQL(this);
        ListViewActivity = findViewById(R.id.ListViewActivity);
        ListBackButton = findViewById(R.id.ListBackButton);
        storeData();

        //Onclick for buttons
        ListBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookListView.this, DominicPrzygonski.BookBag.Menu.class);
                startActivity(intent);
            }
        });
    }

    //Storing data to listView
    void storeData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){

                nameList.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
            ListViewActivity.setAdapter(adapter);
        }
    }

    //SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_search, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<String> userList = new ArrayList<>();
                for(String user : nameList){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        userList.add(user);
                    }
                }

                ArrayAdapter<String> adapter2  = new ArrayAdapter<String>(BookListView.this, android.R.layout.simple_list_item_1, userList);
                ListViewActivity.setAdapter(adapter2);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}