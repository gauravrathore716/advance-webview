package com.example.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ErrorActivty extends AppCompatActivity {

    TextView tvError;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_activty);
        if (getIntent().getExtras().getString("MSG") != null)

        {
            tvError = (TextView) findViewById(R.id.tvError);
            tvError.setText(getIntent().getExtras().getString("MSG"));

        }
    }

    public void btnRetry_Click(View view)

    {
        Redirect();

    }

    public void btnExit_Click(View view) {
        finish();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Redirect();

    }
    private void Redirect() {

        if (Common.connectionAvailable(ErrorActivty.this)) ;

        {
            Intent home = new Intent(ErrorActivty.this, MainActivity.class);
            startActivity(home);
            finish();

        }
        //else

        makeText(this, R.string.checkinternet, LENGTH_SHORT).show();
    }
}
