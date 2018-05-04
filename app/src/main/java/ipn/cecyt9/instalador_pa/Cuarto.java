package ipn.cecyt9.instalador_pa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class Cuarto extends AppCompatActivity {
    agregaCuarto agCu = new agregaCuarto();
    agregaCasa agCa = new agregaCasa();
    agregaUsuario agUsr = new agregaUsuario();

    int idUsr , idCasa ;
    EditText numeroPiso, nombreCuarto;
    ImageButton foco, puerta, camara, clima;
    Button cerrar;

    boolean conD = true, namaC, numPi, tipoD;
    String nn, np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);

        idUsr = getIntent().getExtras().getInt("idUsr");
        idCasa = getIntent().getExtras().getInt("idCasa");

        agCu.setIdCasa(idCasa);

        numeroPiso = (EditText)findViewById(R.id.numeroPiso);
        nombreCuarto = (EditText)findViewById(R.id.nombreCuarto);

        foco = (ImageButton)findViewById(R.id.foco);
        puerta = (ImageButton)findViewById(R.id.puerta);
        camara = (ImageButton)findViewById(R.id.camara);
        clima = (ImageButton)findViewById(R.id.clima);

        cerrar = (Button)findViewById(R.id.cerrar);

    }

    public void agregaFoco(View view){
        np = numeroPiso.getText().toString().trim();
        if( np.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Numero vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));
        }
        nn = nombreCuarto.getText().toString().trim();
        if( nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Nombre vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            namaC = agCu.setNombreCuarto(nn);
        }


        tipoD = agCu.setTipoDisp(1);

        if(!namaC){
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombreCuarto.setText("");
            conD = false;
        } else if(!numPi){
            Toast.makeText(getApplicationContext(), "Numero incorrecto", Toast.LENGTH_SHORT).show();
            numeroPiso.setText("");
            conD = false;
        } else if (!tipoD){
            Toast.makeText(getApplicationContext(), "Tipo incorrecto", Toast.LENGTH_SHORT).show();
            conD = false;
        } else{
            conD = true;
            if(conD){
                Toast.makeText(getApplicationContext(), "Cuarto dado de alta", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void agregaPuerta(View view){
        np = numeroPiso.getText().toString().trim();
        if( np.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Numero vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));
        }
        nn = nombreCuarto.getText().toString().trim();
        if( nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Nombre vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            namaC = agCu.setNombreCuarto(nn);
        }

        tipoD = agCu.setTipoDisp(2);

        if(!namaC){
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombreCuarto.setText("");
            conD = false;
        } else if(!numPi){
            Toast.makeText(getApplicationContext(), "Numero incorrecto", Toast.LENGTH_SHORT).show();
            numeroPiso.setText("");
            conD = false;
        } else if (!tipoD){
            Toast.makeText(getApplicationContext(), "Tipo incorrecto", Toast.LENGTH_SHORT).show();
            conD = false;
        } else{
            conD = true;
            if(conD){
                Toast.makeText(getApplicationContext(), "Cuarto dado de alta", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void agregaCamara(View view){
        np = numeroPiso.getText().toString().trim();
        if( np.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Numero vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));
        }
        nn = nombreCuarto.getText().toString().trim();
        if( nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Nombre vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            namaC = agCu.setNombreCuarto(nn);
        }

        tipoD = agCu.setTipoDisp(3);

        if(!namaC){
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombreCuarto.setText("");
            conD = false;
        } else if(!numPi){
            Toast.makeText(getApplicationContext(), "Numero incorrecto", Toast.LENGTH_SHORT).show();
            numeroPiso.setText("");
            conD = false;
        } else if (!tipoD){
            Toast.makeText(getApplicationContext(), "Tipo incorrecto", Toast.LENGTH_SHORT).show();
            conD = false;
        } else{
            conD = true;
            if(conD){
                Toast.makeText(getApplicationContext(), "Cuarto dado de alta", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void agregaClima(View view){
        np = numeroPiso.getText().toString().trim();
        if( np.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Numero vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            numPi = agCu.setNumero_Piso(Integer.parseInt(np));
        }
        nn = nombreCuarto.getText().toString().trim();
        if( nn.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Nombre vacío", Toast.LENGTH_SHORT).show();
            conD = false;
        }else{
            namaC = agCu.setNombreCuarto(nn);
        }

        tipoD = agCu.setTipoDisp(4);

        if(!namaC){
            Toast.makeText(getApplicationContext(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
            nombreCuarto.setText("");
            conD = false;
        } else if(!numPi){
            Toast.makeText(getApplicationContext(), "Numero incorrecto", Toast.LENGTH_SHORT).show();
            numeroPiso.setText("");
            conD = false;
        } else if (!tipoD){
            Toast.makeText(getApplicationContext(), "Tipo incorrecto", Toast.LENGTH_SHORT).show();
            conD = false;
        } else{
            conD = true;
            if(conD){
                Toast.makeText(getApplicationContext(), agCu.agregaCu(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void cerrar(View view){
        finish();
    }
}