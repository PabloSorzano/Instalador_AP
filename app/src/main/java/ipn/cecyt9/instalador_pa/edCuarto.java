package ipn.cecyt9.instalador_pa;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
    LinearLayout linear;

    String nomCuarto, numPiso, obser;
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    int idCuartoDisp, idTipoDisp;

    int idCasa, idUsr, minLat=0, minLong=0, idCuarto;
    int cuartos=0;
    float LAT, LOG;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, aPat, aMat, telefono, email, pass;
    String namas="", rooms[];

    String whereClause, whereArgs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_cuarto);
        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();

        linear = (LinearLayout)findViewById(R.id.linear);

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

        //LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main);//main.xml
        //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View itemBox = inflater.inflate(R.layout.item_box);//activity a agregar
        //mainLayout.addView(itemBox);

    }
    int i=0;
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
                namas += nomCuarto+ ",";
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
            rooms = namas.split(",");
            for(int j=0;j<cuartos;j++) {
                LinearLayout childLayout = new LinearLayout(edCuarto.this);
                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                childLayout.setLayoutParams(linearParams);
                childLayout.setGravity(Gravity.CENTER_HORIZONTAL);

                //TextView mType = new TextView(edCuarto.this);
                Button btn = new Button(edCuarto.this);
                //TextView mValue = new TextView(edCuarto.this);

                btn.setLayoutParams(new TableLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                //mType.setLayoutParams(new TableLayout.LayoutParams(
                //        LinearLayout.LayoutParams.WRAP_CONTENT,
                //        LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                //mValue.setLayoutParams(new TableLayout.LayoutParams(
                //        LinearLayout.LayoutParams.WRAP_CONTENT,
                //        LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

                //mType.setTextSize(17);
                //mType.setPadding(5, 3, 0, 3);
                //mType.setTypeface(Typeface.DEFAULT_BOLD);
                //mType.setGravity(Gravity.LEFT | Gravity.CENTER);

                //mValue.setTextSize(16);
                //mValue.setPadding(5, 3, 0, 3);
                //mValue.setTypeface(null, Typeface.ITALIC);
                //mValue.setGravity(Gravity.LEFT | Gravity.CENTER);

                //mType.setText(rooms[j]);
                //mValue.setText("111");
                btn.setText(rooms[j]);
                btn.setTypeface(Typeface.MONOSPACE);
                btn.setGravity(Gravity.CENTER);
                final int finalJ = j;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(edCuarto.this, "Proceso edit de: "+rooms[finalJ], Toast.LENGTH_SHORT).show();
                    }
                });
                childLayout.addView(btn, 0);
                //childLayout.addView(mValue, 0);
                //childLayout.addView(mType, 0);

                linear.addView(childLayout);
            }
        }else{
            Toast.makeText(getApplicationContext(), "No tiene cuartos", Toast.LENGTH_SHORT).show();
            edUsr = new Intent(getApplicationContext(), Inicio.class);
            finish();
            startActivity(edUsr);
        }
        jj.close();
    }
}
