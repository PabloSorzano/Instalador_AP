package ipn.cecyt9.instalador_pa.SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity {

    private EditText idT;
    private EditText nombreT;
    private EditText apellidoMT;
    private EditText apellidoPT;
    private EditText movilT;
    private EditText contraseñaT;
    private EditText tipoUs;


    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);

    //idT = (EditText)findViewById(R.id.txtid);
    //nombreT = (EditText)findViewById(R.id.nom);
    //apellidoPT = (EditText)findViewById(R.id.apeP);
    //apellidoMT = (EditText)findViewById(R.id.apeM);
    //movilT = (EditText)findViewById(R.id.mov);
    //contraseñaT = (EditText)findViewById(R.id.pass);
    //tipoUs = (EditText)findViewById(R.id.tip);

    //} //

    //Metodo altas Usuarios
    public void Agregar(View v){
        //AdminSQLiteOpenHelper instalador = new AdminSQLiteOpenHelper(, "instalacion",null, 1);
        //SQLiteDatabase BaseDeDatos = instalador.getWritableDatabase();

        String idc = idT.getText().toString();
        String nombre = nombreT.getText().toString();
        String apellidoP = apellidoPT.getText().toString();
        String apellidoM = apellidoMT.getText().toString();
        String movil = movilT.getText().toString();
        String contraseña = contraseñaT.getText().toString();
        String tipo = tipoUs.getText().toString();

        if(!idc.isEmpty() && !nombre.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && !movil.isEmpty() && !contraseña.isEmpty() && !tipo.isEmpty()){

            ContentValues agregar = new ContentValues();
            agregar.put("codigo",idc);
            agregar.put("nombre",nombre);
            agregar.put("apellidopaterno",apellidoP);
            agregar.put("apellidomaterno",apellidoM);
            agregar.put("telefonomovil",movil);
            agregar.put("contraseña",contraseña);
            agregar.put("tipo",tipo);

            //BaseDeDatos.insert("Usuarios",null, agregar);
            //Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            //BaseDeDatos.close();

            idT.setText("");
            nombreT.setText("");
            apellidoPT.setText("");
            apellidoMT.setText("");
            movilT.setText("");
            contraseñaT.setText("");
            tipoUs.setText("");




        }
        else{
            //Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo consulta usuarios
    public void Buscar(View v){
        //AdminSQLiteOpenHelper instalador = new AdminSQLiteOpenHelper(this, "instalador",null, 1);
        //SQLiteDatabase BaseDeDatos = instalador.getWritableDatabase();

        String id = idT.getText().toString();


        if(!id.isEmpty()){
            //Cursor c = BaseDeDatos.rawQuery ("SELECT nombre, apellidomaterno, apellidopaterno, telefonomovil,tipo FROM Usuarios WHERE codigo =" + id , null);


            idT.setText(id);
            //if(c.moveToFirst()){
            //nombreT.setText(c.getString(0));
            //apellidoMT.setText(c.getString(1));
            //apellidoPT.setText(c.getString(2));
            //movilT.setText(c.getString(3));
            //tipoUs.setText(c.getString(4));

                //BaseDeDatos.close();
                //Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_SHORT).show();
            }
            else{
                //Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                //BaseDeDatos.close();
            }
        }
    //else{
            //Toast.makeText(this, "Debes llenar el campo id", Toast.LENGTH_SHORT).show();
    //}
    //}
    //Metodo para modificar usuarios
    public void Modificar(View v){
        //AdminSQLiteOpenHelper instalador = new AdminSQLiteOpenHelper(this, "instalador",null, 1);
        //SQLiteDatabase BaseDeDatos = instalador.getWritableDatabase();

        String idc = idT.getText().toString();
        String nombre = nombreT.getText().toString();
        String apellidoP = apellidoPT.getText().toString();
        String apellidoM = apellidoMT.getText().toString();
        String movil = movilT.getText().toString();
        String contraseña = contraseñaT.getText().toString();
        String tipo = tipoUs.getText().toString();

        if(!idc.isEmpty()&& !nombre.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && !movil.isEmpty() && !contraseña.isEmpty() && !tipo.isEmpty()){
            ContentValues añadir = new ContentValues();
            añadir.put("codigo",idc);
            añadir.put("nombre",nombre);
            añadir.put("apellidopaterno",apellidoP);
            añadir.put("apellidomaterno",apellidoM);
            añadir.put("telefonomovil",movil);
            añadir.put("contraseña",contraseña);
            añadir.put("tipo",tipo);

            //int cantidad = BaseDeDatos.update("Usuarios", añadir, "codigo = "+ idc, null);
            //BaseDeDatos.close();

            //if(cantidad == 1){
            //    Toast.makeText(this, "Modificado con exito", Toast.LENGTH_SHORT).show();
            //}
            //else{
            //    Toast.makeText(this, "No existe", Toast.LENGTH_SHORT).show();
            //}
        }
        else{
            //Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
