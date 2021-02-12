package pt.ubi.di.pdm.hojabola.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ubi.di.pdm.hojabola.Model.Game;
import pt.ubi.di.pdm.hojabola.R;
import pt.ubi.di.pdm.hojabola.database;

public class resultsSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Game> games = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private ResultAdapter mAdapter;
    int nmrFixtures;

    public resultsSliderAdapter(Context context, int nmrFixtures) {
        this.context = context;
        this.nmrFixtures = nmrFixtures;
    }

    @Override
    public int getCount() {
        return nmrFixtures;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_result, container, false);
        database db = new database(context);
        SQLiteDatabase oSQLiteDB = db.getWritableDatabase();
        Log.e("TAG", String.valueOf(position));
        TextView fixture = view.findViewById(R.id.fixture);
        fixture.setText("Jornada "+String.valueOf(position+1));
        RecyclerView mRecyclerView = view.findViewById(R.id.rvResult);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mAdapter = new ResultAdapter(games);
        games.clear();
        Cursor oCursor = oSQLiteDB.rawQuery("SELECT * FROM GAME WHERE gRound= ?",new String[]{String.valueOf(position+1)} );
        boolean bCarryOn = oCursor.moveToFirst();

        while (bCarryOn){
            games.add(new Game(oCursor.getString(0),oCursor.getString(1),oCursor.getString(2),oCursor.getString(3),oCursor.getString(4),oCursor.getString(5),oCursor.getString(6),oCursor.getInt(7)));
            bCarryOn = oCursor.moveToNext();
        }

        mAdapter.updateResults(games);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

}