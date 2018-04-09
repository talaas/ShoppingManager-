package ecole.eni.fr.shoppingManager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.dal.ArticleDAL;


public class ArticleDetailActivity extends AppCompatActivity {
    String url;
    private int articleId;
    private ArticleDAL dal;
    private ArticleRef article;

    private TextView textName ;
    private TextView textDescription ;
    private TextView textPrice ;
    private RatingBar rateBar;
    private ToggleButton boughtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(android.R.drawable.btn_star);
        dal = new ArticleDAL(ArticleDetailActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        textName = (TextView) findViewById(R.id.textArticleName);
        textDescription = (TextView) findViewById(R.id.textDescription);
        textPrice = (TextView) findViewById(R.id.textPrix);
        rateBar = (RatingBar) findViewById(R.id.bar_Note);
        boughtButton = (ToggleButton) findViewById(R.id.buttonAcheter);
        articleId = intent.getIntExtra("articleId", 0);

        ChargeArticle chargeArticle = new ChargeArticle();
        chargeArticle.execute(articleId);

        rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser == true) {
                    article.setRate(rating);
                    UpdateArticle updateThread = new UpdateArticle();
                    updateThread.execute();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    public void onClickButtonUrl(View view) {
//        Toast.makeText(ArticleDetailActivity.this, ArticleDAL.getArticle(articleId).getUrl(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(dal.getArticle(articleId).getUrl()));

        // Test si l'activité est connu
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(ArticleDetailActivity.this, "Impossible de trouver une activité", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onPause() {

        super.onPause();

    }

    public void enableAll(){
        boughtButton.setEnabled(true);
        rateBar.setEnabled(true);
    }


    public void disableAll(){
        boughtButton.setEnabled(false);
        rateBar.setEnabled(false);
    }

    public void onClickBuyingButton(View view) {
        article.setBought(((ToggleButton) view).isChecked());
        UpdateArticle updateThread = new UpdateArticle();
        updateThread.execute();
    }

    public void onClickEditButton(View view) {
        Intent intent = new Intent(ArticleDetailActivity.this, CreateOrEditActivity.class);
        intent.putExtra("articleId", articleId);
        startActivity(intent);
    }

    public void onClickDeleteButton(View view) {
        Toast.makeText(ArticleDetailActivity.this, R.string.article_delete, Toast.LENGTH_LONG).show();
        DeleteArticle deleteThread = new DeleteArticle();
        deleteThread.execute();
        ArticleDetailActivity.this.finish();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_mail:
                if (id == R.id.action_mail) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:0228223003"));

                    String text = "Pour me faire un cadeau, tu peux m'acheter ça : " + dal.getArticle(articleId).getName()
                            +". Cela ne coute que "+ dal.getArticle(articleId).getPrice() + " euros et cela me fera vraiment plaisir :) Merci !";
                    intent.putExtra("sms_body", text);
                    startActivity(intent);
                    return true;
                }
            break;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private class UpdateArticle extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {


            dal.setArticle(articleId, article);

            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAll();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

    }


    private class DeleteArticle extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            dal.deleteArticle(articleId);

            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAll();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

    }

    /**
     * Chargement de l'objet article depuis
     */
    private class ChargeArticle extends AsyncTask<Integer, Integer, Void>{

        private ArticleRef article;

        @Override
        protected Void doInBackground(Integer... params) {

            this.article = dal.getArticle(params[0]);

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
            chargeArticle(this.article);
        }
    }

    private void chargeArticle(ArticleRef art){

        this.article = art;

        textName.setText(article.getName());

        textDescription.setText(article.getDescription());
        textPrice.setText(String.valueOf(article.getPrice()));
        rateBar.setRating(article.getRate());
        boughtButton.setChecked(article.getBought());
        url = article.getUrl();

    }
}
