package fr.clubomnisportsulis.mycarviewapp;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private List<AppsModels> appsList;
    private AppsAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Recycler View Widgets
        recyclerView = findViewById(R.id.myRecyclerView);

        appsList = new ArrayList<>();
        adapter = new AppsAdapter(this, appsList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Itme decoration


        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridItemSpacingDecoration(2, dpToPx(10), true));

        // preparing the cards
        InserDataIntoCards();



    }

    private void InserDataIntoCards() {

        // Add the cards Data and Display them

        int[] appCovers = new int[]{
                R.drawable.ic_amandes,// appCovers[0]
                R.drawable.ic_pommes,
                R.drawable.ic_potato,
                R.drawable.ic_arbre,
                R.drawable.ic_bouquet,
                R.drawable.ic_feuilleverte,
                R.drawable.ic_fruitsbizarres,
                R.drawable.ic_oiseauvert,
                R.drawable.ic_painepice,
                R.drawable.ic_rose
        };

        AppsModels amandes = new AppsModels("Master Android App", 000,appCovers[0]);
        appsList.add(amandes);

        AppsModels pommes = new AppsModels("Matser Android Pro",100, appCovers[1] );
        appsList.add(pommes);

        AppsModels potato = new AppsModels("Master Android Kotlin", 200, appCovers[2]);
        appsList.add(potato);

        AppsModels arbre = new AppsModels("Master Android arbre", 300,appCovers[3]);
        appsList.add(arbre);

        AppsModels bouquet = new AppsModels("Master Android arbre", 400,appCovers[4]);
        appsList.add(bouquet);

        AppsModels feuilleverte = new AppsModels("Master Android arbre", 500,appCovers[5]);
        appsList.add(feuilleverte);

        AppsModels fruitsbizarres = new AppsModels("Master Android arbre", 600,appCovers[6]);
        appsList.add(fruitsbizarres);

        AppsModels oiseauvert = new AppsModels("Master Android arbre", 700,appCovers[7]);
        appsList.add(oiseauvert);

        AppsModels painepice = new AppsModels("Master Android arbre", 800,appCovers[8]);
        appsList.add(painepice);

        AppsModels rose = new AppsModels("Master Android arbre", 900,appCovers[9]);
        appsList.add(rose);



        // Don't forget to notify the data change
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GridItemSpacingDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridItemSpacingDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
           // super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeEdge){

                outRect.left = spacing - column * spacing / spanCount ;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom

            } else {

                outRect.left = column * spacing / spanCount; // column * ..
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
              //  outRect.top = spacing; //TODO est-ce la ligne manquante ?

            }

        }


    }

    private int dpToPx(int dp){
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
}