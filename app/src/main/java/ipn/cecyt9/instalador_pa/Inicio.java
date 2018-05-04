package ipn.cecyt9.instalador_pa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


    }

    public void start(View view){
        Intent cambio = new Intent(getApplicationContext(), Usuario.class);
        finish();
        startActivity(cambio);
    }
}