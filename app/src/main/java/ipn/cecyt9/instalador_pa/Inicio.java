package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Inicio extends AppCompatActivity {
    ContentValues values;
    String msj;

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();

        System.out.println(msj);

    }

    public void add(View view){
        Intent cambio = new Intent(getApplicationContext(), Usuario.class);
        startActivity(cambio);
    }

    public void del(View view){
        Intent cambio = new Intent(getApplicationContext(), Eliminar.class);
        startActivity(cambio);
    }

    public void consult(View view){
        Intent cambio = new Intent(getApplicationContext(), Consultar.class);
        startActivity(cambio);
    }

    public void edit(View view){
        Intent cambio = new Intent(getApplicationContext(), Editar.class);
        startActivity(cambio);
    }

}