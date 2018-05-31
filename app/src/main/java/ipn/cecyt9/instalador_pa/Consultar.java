package ipn.cecyt9.instalador_pa;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.*;
import android.widget.Toast;

import ipn.cecyt9.instalador_pa.data.SmartConstract;
import ipn.cecyt9.instalador_pa.data.SmartHouseDBHelper;

public class Consultar extends AppCompatActivity {
    TextView text;
    Button cons;
    EditText mai;

    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(),"Ingrese un correo para buscar al usuario", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        cons = (Button)findViewById(R.id.cons);
        text = (TextView)findViewById(R.id.text);
        mai = (EditText)findViewById(R.id.mai) ;

        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mai.getText().toString().trim().isEmpty()){
                    consult();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingrese un correo a buscar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();





    }
    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, email, pass, aPat, aMat, telefono;
    String nomCuarto, numPiso, obser;
    int idCasa, idUsr, idCuarto, idCuartoDisp, idTipoDisp;
    int foco, puerta, camara, clima;
    public void consult(){
        table = SmartConstract.UsrEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.UsrEntry.ID_USUARIO,
                SmartConstract.UsrEntry.NAME_USUARIO,
                SmartConstract.UsrEntry.APELLIDO_PATERNO,
                SmartConstract.UsrEntry.APELLIDO_MATERNO,
                SmartConstract.UsrEntry.TELEFONO_MOVIL,
                SmartConstract.UsrEntry.EMAIL,
                SmartConstract.UsrEntry.CONTRASEÑA};
        selection = SmartConstract.UsrEntry.EMAIL+" = ?";
        selectionArgs = new String[]{mai.getText().toString().trim()};
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
                idUsr = Integer.parseInt(cursor.getString(0));
                nombre = cursor.getString(1);
                aPat = cursor.getString(2);
                aMat = cursor.getString(3);
                telefono = cursor.getString(4);
                email = cursor.getString(5);
                pass = cursor.getString(6);
                msj = "----"+nombre+" "+aPat+" "+aMat+"----\n"+
                        //"ID_Usuario:\n  "+idUsr+"\n" +
                        //"Nombre:\n  "+nombre+" "+aPat+" "+aMat+"\n" +
                        "Teléfono Móvil:\n  "+telefono+"\n" +
                        "E-mail:\n  "+email+"\n" +
                        "Nombre de usuario:\n  "+nombre+"\n"  +
                        "Contraseña:\n  "+pass+"\n";
                text.setText("");
                text.append(msj);
            } while(cursor.moveToNext());

            table = SmartConstract.CasaEntry.TABLE_NAME;
            columns = new String[]{SmartConstract.CasaEntry.ID_CASA,
                    SmartConstract.CasaEntry.ID_USUARIO,
                    SmartConstract.CasaEntry.COORDENADAS,
                    SmartConstract.CasaEntry.ESTADO,
                    SmartConstract.CasaEntry.MUNICIPIO,
                    SmartConstract.CasaEntry.CODIGO_POSTAL,
                    SmartConstract.CasaEntry.COLONIA,
                    SmartConstract.CasaEntry.CALLE,
                    SmartConstract.CasaEntry.NUMERO_INTERIOR};
            selection = SmartConstract.CasaEntry.ID_USUARIO+"=?";
            selectionArgs = new String[]{String.valueOf(idUsr)};
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
                    idCasa = Integer.parseInt(cursor.getString(0));
                    coorde = cursor.getString(2);
                    estado = cursor.getString(3);
                    muni = cursor.getString(4);
                    codP = cursor.getString(5);
                    col = cursor.getString(6);
                    call = cursor.getString(7);
                    numInt = cursor.getString(8);
                    msj = "-------------CASA-------------\n"+
                            //"ID_Casa:\n  "+idCasa+"\n" +
                            //"ID_Usuario:\n  "+cursor.getString(1)+"\n" +
                            "Coordenadas:\n  "+coorde+"\n" +
                            "Estado:\n  "+estado+"\n" +
                            "Municipio:\n  "+muni+"\n" +
                            "Codigo Postal:\n  "+codP+"\n" +
                            "Colonia:\n  "+col+"\n" +
                            "Calle:\n  "+call+"\n" +
                            "Numero Interior:\n  "+numInt+ "\n";

                    text.append(msj);
                } while(cursor.moveToNext());
            }else{
            }
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
                    msj = "\n-------"+nomCuarto+"-------\n"+
                            //"ID_Cuarto:\n  "+idCuarto+"\n" +
                            //"ID_Casa:\n  "+cursor.getString(1)+"\n" +
                            //"Nombre de Cuarto:\n  "+nomCuarto+"\n" +
                            "Piso ubicado:\n  "+numPiso+"\n" ;
                    foco=0; puerta=0; camara=0; clima=0;
                    text.append(msj);
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

                            if(idTipoDisp==1){
                                foco++;
                            }else if(idTipoDisp==2){
                                puerta++;
                            }else if(idTipoDisp==3){
                                camara++;
                            }else if(idTipoDisp==4){
                                clima++;
                            }else{

                            }
                            String general = "Foco(s): "+foco+"\n" +
                                    "Puerta(s): "+puerta+"\n" +
                                    "Cámara(s): "+camara+"\n" +
                                    "Clima(s): "+clima;
                            msj = ""+general+"\n";
                            //"ID_CuartoDisp:\n  "+idCuartoDisp+"\n" +
                            //"ID_Cuarto:\n  "+cursor1.getString(1)+"\n" +
                            //"ID_TipoDisp:\n  "+idTipoDisp+"\n" ;

                        } while(cursor1.moveToNext());
                    }else{
                    }

                    text.append(msj);

                } while(cursor.moveToNext());
            }else{
            }
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros de usuarios", Toast.LENGTH_SHORT).show();
        }




    }
}
