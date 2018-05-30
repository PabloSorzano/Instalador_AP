package ipn.cecyt9.instalador_pa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class edCuarto extends AppCompatActivity {
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;
    agregaUsuario agUsr = new agregaUsuario();
    Intent edUsr;
    LinearLayout contenedor;

    String nomCuarto, numPiso, obser;
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    int idCuartoDisp, idTipoDisp;

    int idCasa, idUsr, minLat=0, minLong=0, idCuarto;
    int cuartos=0;
    float LAT, LOG;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, aPat, aMat, telefono, email, pass;

    String whereClause, whereArgs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_cuarto);
        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();

        //contenedor = new LinearLayout(getApplicationContext()) ;
        //contenedor.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        //contenedor.setOrientation(LinearLayout.VERTICAL);

        idCasa = getIntent().getExtras().getInt("idCasa");
        coorde = getIntent().getExtras().getString("coorde");
        estado = getIntent().getExtras().getString("estado");
        muni = getIntent().getExtras().getString("muni");
        codP = getIntent().getExtras().getString("codP");
        col = getIntent().getExtras().getString("col");
        call = getIntent().getExtras().getString("call");
        numInt = getIntent().getExtras().getString("numInt");

        idUsr = getIntent().getExtras().getInt("idUsr");
        nombre = getIntent().getExtras().getString("nombre");
        aPat = getIntent().getExtras().getString("aPat");
        aMat = getIntent().getExtras().getString("aMat");
        telefono = getIntent().getExtras().getString("telefono");
        email = getIntent().getExtras().getString("email");
        pass = getIntent().getExtras().getString("pass");

        obtenerCuartoDisp(idCasa);

    }

    public void obtenerCuartoDisp(int idCasa){
        //cuarto
        table = SmartConstract.CuartoEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.CuartoEntry.ID_CUARTO,
                SmartConstract.CuartoEntry.ID_CASA,
                SmartConstract.CuartoEntry.NOMBRE_CUARTO,
                SmartConstract.CuartoEntry.NUMERO_PISO,
                SmartConstract.CuartoEntry.OBSERVACION};
        selection = SmartConstract.CuartoEntry.ID_CASA+"=?";
        selectionArgs = new String[]{String.valueOf(idCasa)};
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
        msj="";
        //table: the name of the table you want to query
        //columns: the column names that you want returned. Don't return data that you don't need.
        //selection: the row data that you want returned from the columns (This is the WHERE clause.)
        //selectionArgs: This is substituted for the ? in the selection String above.
        //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
        //        orderBy: sort the data
        //limit: limit the number of results to return

        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                idCuarto = Integer.parseInt(cursor.getString(0));
                nomCuarto = cursor.getString(2);
                numPiso = cursor.getString(3);
                obser = cursor.getString(4);
                msj = "-------------CUARTO-------------\n" +
                        "ID_Cuarto:\n  " + idCuarto + "\n" +
                        "ID_Casa:\n  " + cursor.getString(1) + "\n" +
                        "Nombre de Cuarto:\n  " + nomCuarto + "\n" +
                        "Numero de Piso:\n  " + numPiso + "\n";
                cuartos++;
                //Crea contenedor

                //contenedor.setWeightSum(cuartos);
                //contenedor.setGravity(Gravity.CENTER);
                //Crea cuartos
                Button cuarto1 = new Button(getApplicationContext());
                //Agrega propiedades al TextView.
                //Agrega imagen al ImageView.
                cuarto1.setText(nomCuarto);
                cuarto1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(edCuarto.this, "Proceso edit de: "+nomCuarto, Toast.LENGTH_SHORT).show();
                    }
                });
                cuarto1.setTypeface(Typeface.MONOSPACE);


                //Agrega vistas al contenedor.
                //contenedor.addView(cuarto1);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, 200, Gravity.CENTER);
                //Agrega contenedor con botones.
                this.addContentView(getCurrentFocus(), params);




                //dispositivos
                table = SmartConstract.CuartoDispEntry.TABLE_NAME;
                columns = new String[]{SmartConstract.CuartoDispEntry.ID_CUARTO_DISP,
                        SmartConstract.CuartoDispEntry.ID_CUARTO,
                        SmartConstract.CuartoDispEntry.ID_TIPO_DISP};
                selection = SmartConstract.CuartoDispEntry.ID_CUARTO+"=?";
                selectionArgs = new String[]{String.valueOf(idCuarto)};
                groupBy = null;
                having = null;
                orderBy = null;
                limit = null;
                msj="";
                //table: the name of the table you want to query
                //columns: the column names that you want returned. Don't return data that you don't need.
                //selection: the row data that you want returned from the columns (This is the WHERE clause.)
                //selectionArgs: This is substituted for the ? in the selection String above.
                //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
                //        orderBy: sort the data
                //limit: limit the number of results to return
                Cursor cursor1;
                cursor1 = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                //Nos aseguramos de que existe al menos un registro
                if (cursor1.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        idCuartoDisp = Integer.parseInt(cursor1.getString(0));
                        idTipoDisp = Integer.parseInt(cursor1.getString(2));
                        msj = "---------CUARTO/DISP---------\n"+
                                "ID_CuartoDisp:\n  "+idCuartoDisp+"\n" +
                                "ID_Cuarto:\n  "+cursor1.getString(1)+"\n" +
                                "ID_TipoDisp:\n  "+idTipoDisp+"\n" ;




                    } while(cursor1.moveToNext());
                }else{
                }

            } while(cursor.moveToNext());
        }else{
            Toast.makeText(getApplicationContext(), "No tiene cuartos", Toast.LENGTH_SHORT).show();
            edUsr = new Intent(getApplicationContext(), Inicio.class);
            finish();
            startActivity(edUsr);
        }
    }
}
