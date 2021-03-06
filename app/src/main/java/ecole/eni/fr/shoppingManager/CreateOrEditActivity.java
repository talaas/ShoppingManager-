package ecole.eni.fr.shoppingManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;
import ecole.eni.fr.shoppingManager.dal.ArticleRefDAL;


public class CreateOrEditActivity extends AppCompatActivity {

    private int articleId;
    private ArticleRef nouvelArticle;
    private ArticleRefDAL articleDAL;
    private TextView editName;
    private TextView date;

    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editName = (TextView) findViewById(R.id.form_Create_Edit_Label);
        date = (TextView) findViewById(R.id.dateEdit);

        articleDAL = new ArticleRefDAL(CreateOrEditActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        articleId = intent.getIntExtra("articleId", -1);
        if (articleId != -1)
        {
            ChargeArticle chargeArticle = new ChargeArticle();
            chargeArticle.execute(articleId);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    /**
     *  désactive tous les widgets lors des Tâches asynchrone
     */
    public void disableAll()
    {
        editName.setEnabled(false);
        date.setEnabled(false);

        progress.setVisibility(View.VISIBLE);
    }

    public void enableAll(String s)
    {
        this.enableAll();
        Toast.makeText(CreateOrEditActivity.this, s, Toast.LENGTH_LONG).show();
        CreateOrEditActivity.this.finish();
    }

    private void enableAll(){
        editName.setEnabled(true);
        date.setEnabled(true);

        progress.setVisibility(View.GONE);
    }

    public void chargeArticle(ArticleRef article){
//        editName.setText(article.getName());
//        editDescription.setText(article.getDescription());
//        rbNote.setRating(article.getRate());
//        editPrice.setText(article.getPrice().toString());
//        editUrl.setText(article.getUrl());

        this.enableAll();

    }

    public void onClickButtonAjouter(View view) {
// TODO
        String name = editName.getText().toString();
        String[] dateSaisie = date.getText().toString().split("/|-");
        Date dateVal = new Date(
                Integer.valueOf(dateSaisie[2]),
                Integer.valueOf(dateSaisie[1]),
                Integer.valueOf(dateSaisie[0]),
                        12,
                        00);

        ListeDeCourse listeSave = new ListeDeCourse(null, dateVal, name);
        // TODO base
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

    // Class interne AsyncTask permettant de lancer une tâche lourde pouvant bloquer l'UI
    private class InsertOrUpdate extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            if (articleId == -1) {


                articleDAL.addArticle(nouvelArticle);
            }
            else
            {
                articleDAL.setArticle(articleId, nouvelArticle);
            }
            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAll(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * Chargement de l'objet article depuis
     */
    private class ChargeArticle extends AsyncTask<Integer, Integer, Void>{

        private ArticleRef article;

        @Override
        protected Void doInBackground(Integer... params) {
            //this.article = articleDAL.getArticle(params[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //chargeArticle(this.article);
        }
    }

}
