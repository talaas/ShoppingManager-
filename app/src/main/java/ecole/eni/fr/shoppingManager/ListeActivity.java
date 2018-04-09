package ecole.eni.fr.shoppingManager;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ecole.eni.fr.shoppingManager.adapter.ArticleAdapter;
import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.dal.ArticleRefDAL;

public class ListeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION_PROVIDER = 4845;
    ArticleAdapter articleAdapter;
    private ArticleRefDAL dal;
    private ListView listViewArticle;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(android.R.drawable.btn_star);

        mLayout = (View) findViewById(R.id.layout_main) ;

        /*Début des permissions*/
        if(ContextCompat.checkSelfPermission(ListeActivity.this,"articleprovider.WRITE") != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(ListeActivity.this,"articleprovider.READ")!= PackageManager.PERMISSION_GRANTED
                ){

            //Permission déjà demandée
            if(ActivityCompat
                        .shouldShowRequestPermissionRationale(this, "articleprovider.WRITE")
                    || ActivityCompat
                        .shouldShowRequestPermissionRationale(this, "articleprovider.READ") ){

                Snackbar.make(mLayout, R.string.permission_required,Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(ListeActivity.this,
                                        new String[]{"articleprovider.WRITE", "articleprovider.READ"},
                                        REQUEST_CODE_PERMISSION_PROVIDER
                                );
                            }
                        }).show();
            }
            else{
                ActivityCompat.requestPermissions(ListeActivity.this,
                        new String[]{"articleprovider.WRITE", "articleprovider.READ"},
                        REQUEST_CODE_PERMISSION_PROVIDER
                        );
            }

        }
        else {

            init();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_PERMISSION_PROVIDER : {

                if(grantResults.length > 0
                        && grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    init();
                }
                else{
                    Toast.makeText(this,"Impossible de lancer l'application",
                            Toast.LENGTH_LONG).show();
                    finish();
                }

                return;
            }
        }
    }

    /**
     *
     */
    private void init(){
        dal = new ArticleRefDAL(ListeActivity.this);
        articleAdapter = new ArticleAdapter(this, R.layout.listitem, dal.getArticles());
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, listeArticle);
        listViewArticle = (ListView) findViewById(R.id.listArticle);
        listViewArticle.setAdapter(articleAdapter);


        listViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                       ArticleRef article = (ArticleRef) parent.getItemAtPosition(position);
                                                       Intent intent = new Intent(ListeActivity.this, ArticleDetailActivity.class);
                                                       intent.putExtra("articleId", article.getId());
                                                       startActivity(intent);
                                                   }
                                               }
        );

        listViewArticle.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(articleAdapter != null) {
            articleAdapter.clear();
            articleAdapter.addAll(dal.getArticles());
            articleAdapter.notifyDataSetChanged();
        }
    }

    public void onClickAjouterButton(View view) {
        Intent intent = new Intent(ListeActivity.this, CreateOrEditActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste, menu);
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
}
