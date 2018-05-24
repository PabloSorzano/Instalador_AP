package ipn.cecyt9.instalador_pa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Inicio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        SmartHouseDBHelper jj = new SmartHouseDBHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = jj.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 1);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Foco");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Fuente de luz con switch");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 2);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Puerta");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Lugar de acceso");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 3);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Camara");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto de vigilancia");
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 4);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Clima");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto capaz de aclimatizar");

        sqLiteDatabase.insert(
                SmartConstract.CatDispEntry.TABLE_NAME,
                null,
                values);
        jj.close();

    }

    public void add(View view){
        Intent cambio = new Intent(getApplicationContext(), Usuario.class);
        finish();
        startActivity(cambio);
    }

    public void del(View view){
        Intent cambio = new Intent(getApplicationContext(), Eliminar.class);
        finish();
        startActivity(cambio);
    }

    public void consult(View view){
        Intent cambio = new Intent(getApplicationContext(), Consultar.class);
        finish();
        startActivity(cambio);
    }

    public void edit(View view){
        Intent cambio = new Intent(getApplicationContext(), Editar.class);
        finish();
        startActivity(cambio);
    }

}