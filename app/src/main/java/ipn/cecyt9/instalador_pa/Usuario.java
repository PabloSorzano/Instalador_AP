package ipn.cecyt9.instalador_pa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Usuario extends AppCompatActivity {
    EditText nombre, aPat, aMat, cel, email, password;
    Button agrega;

    agregaUsuario agUsr = new agregaUsuario();
    agregaCasa agCasa = new agregaCasa();
    boolean nama, pata, mata, celu, mai, pass, conD = true, agregarUsr;
    String namDef = "Anote el nombre",
            patDef = "Anote el apellido paterno",
            matDef = "Anote el apellido materno",
            celDef = "Anote el número móvil (10 dígitos)",
            mailDef = "Anote el correo electrónico",
            passDef = "Anote la contraseña",
            xnombre, xaPat, xaMat, xcel, xmail, xpass;
    int idUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Agregar usuario", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        nombre = (EditText) findViewById(R.id.nombre);
        aPat = (EditText) findViewById(R.id.aPat);
        aMat = (EditText) findViewById(R.id.aMat);
        cel = (EditText) findViewById(R.id.cel);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);

        agrega = (Button) findViewById(R.id.agreg);
    }

    public void agregaUsuario(View view) {
        nama = agUsr.setXnombre(nombre.getText().toString().trim());
        pata = agUsr.setXaPat(aPat.getText().toString().trim());
        mata = agUsr.setXaMat(aMat.getText().toString().trim());
        celu = agUsr.setXcel(cel.getText().toString().trim());
        mai = agUsr.setXmail(email.getText().toString().trim());
        pass = agUsr.setXpass(password.getText().toString().trim());

        xnombre = agUsr.getXnombre();
        xaPat = agUsr.getXaPat();
        xaMat = agUsr.getXaMat();
        xcel = agUsr.getXcel();
        xmail = agUsr.getXmail();
        xpass = agUsr.getXpass();

        if (xnombre.equals(namDef) || nama == false) {
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            conD = false;
        } else if (xaPat.equals(patDef) || pata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Paterno incorrecto", Toast.LENGTH_SHORT).show();
            aPat.setText("");
            conD = false;
        } else if (xaMat.equals(matDef) || mata == false) {
            Toast.makeText(getApplicationContext(), "Apellido Materno incorrecto", Toast.LENGTH_SHORT).show();
            aMat.setText("");
            conD = false;
        } else if (xcel.equals(celDef) || xcel.length() != 10 || celu == false) {
            Toast.makeText(getApplicationContext(), "Celular incorrecto", Toast.LENGTH_SHORT).show();
            cel.setText("");
            conD = false;
        } else if (xmail.equals(mailDef) || mai == false) {
            Toast.makeText(getApplicationContext(), "Correo incorrecto", Toast.LENGTH_SHORT).show();
            email.setText("");
            conD = false;
        } else if (xpass.equals(passDef) || pass == false) {
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            password.setText("");
            conD = false;
        } else{
            conD = true;
            if (conD) {
                Toast.makeText(getApplicationContext(), "Usuario dada de alta", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), agUsr.agregaUsuario(), Toast.LENGTH_LONG).show();
                idUsr = agUsr.getIdUsr();
                System.out.println("Usuario: "+idUsr);


                Intent casa = new Intent(getApplicationContext(), Casa.class);
                casa.putExtra("idUsr", idUsr);
                finish();
                startActivity(casa);
            }
        }

    }

    private void limpiar(){
        nombre.setText("");
        aPat.setText("");
        aMat.setText("");
        cel.setText("");
        email.setText("");
        password.setText("");
    }

}
