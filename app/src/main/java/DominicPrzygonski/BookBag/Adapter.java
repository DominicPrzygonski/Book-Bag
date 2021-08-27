package DominicPrzygonski.BookBag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private ArrayList id, name, author, length, prequels, sequels, genre, completion;
    private Activity activity2;


    Adapter(Activity activity2, Context context, ArrayList id, ArrayList name, ArrayList author, ArrayList length, ArrayList prequels, ArrayList sequels, ArrayList genre, ArrayList completion){
        this.activity2 = activity2;
        this.context = context;
        this.id = id;
        this.name = name;
        this.author = author;
        this.length = length;
        this. prequels = prequels;
        this.sequels = sequels;
        this. genre = genre;
        this.completion = completion;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.book_id.setText(String.valueOf(id.get(position)));
        holder.book_name.setText(String.valueOf(name.get(position)));
        holder.book_author.setText(String.valueOf(author.get(position)));
        holder.book_length.setText(String.valueOf(length.get(position)));
        holder.book_prequel.setText(String.valueOf(prequels.get(position)));
        holder.book_sequel.setText(String.valueOf(sequels.get(position)));
        holder.book_genre.setText(String.valueOf(genre.get(position)));
        holder.book_completion.setText(String.valueOf(completion.get(position)));
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("author", String.valueOf(author.get(position)));
                intent.putExtra("length", String.valueOf(length.get(position)));
                intent.putExtra("prequels", String.valueOf(prequels.get(position)));
                intent.putExtra("sequels", String.valueOf(sequels.get(position)));
                intent.putExtra("genre", String.valueOf(genre.get(position)));
                intent.putExtra("completion", String.valueOf(completion.get(position)));
                activity2.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id, book_name, book_author, book_length, book_prequel, book_sequel, book_genre, book_completion;
        ConstraintLayout rowLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.id_text);
            book_name = itemView.findViewById(R.id.title_text);
            book_author = itemView.findViewById(R.id.author_text);
            book_length = itemView.findViewById(R.id.length_text);
            book_prequel = itemView.findViewById(R.id.prequel_text);
            book_sequel = itemView.findViewById(R.id.sequel_text);
            book_genre = itemView.findViewById(R.id.genre_text);
            book_completion = itemView.findViewById(R.id.completion_text);
            rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    }
}
