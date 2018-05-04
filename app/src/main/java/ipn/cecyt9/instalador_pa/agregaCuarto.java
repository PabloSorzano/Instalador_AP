package ipn.cecyt9.instalador_pa;

import java.util.*;

public class agregaCuarto {
    validacionesJT vd = new validacionesJT();

    int idCuarto = 0, idCuartoDisp = 0, idCasa, numero_Piso, tipo_disp;
    String nombreCuarto;
    public ArrayList<String> cuartos = new ArrayList<String>();
    public ArrayList<String> cuartos_dispositivos = new ArrayList<String>();
    String conjuntoCuarto[], conjuntoDisp[];
    String datosCuartos = "", datosDisp = "";

    public int getIdCuartoDisp() {
        return idCuartoDisp;
    }

    public void setIdCuartoDisp(int idCuartoDisp) {
        this.idCuartoDisp = idCuartoDisp;
    }

    public int getTipoDesp() {
        return tipo_disp;
    }

    public boolean setTipoDisp(int tipo_disp) {
        this.tipo_disp = tipo_disp;
        return vd.soloNumeros(String.valueOf(getTipoDesp()));
    }

    public int getIdCuarto() {
        return idCuarto;
    }

    public void setIdCuarto(int idCuarto) {
        this.idCuarto = idCuarto;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public int getNumero_Piso() {
        return numero_Piso;
    }

    public boolean setNumero_Piso(int numero_Piso) {
        this.numero_Piso = numero_Piso;
        return vd.soloNumeros(String.valueOf(getNumero_Piso()));
    }

    public String getNombreCuarto() {
        return nombreCuarto;
    }

    public boolean setNombreCuarto(String nombreCuarto) {
        this.nombreCuarto = nombreCuarto;
        return vd.soloLetras(getNombreCuarto());
    }


    public String agregaCu(){

        conjuntoCuarto =  new String[idCuarto+1];
        conjuntoDisp = new String[idCuartoDisp+1];
        this.idCuarto = idCuarto +1;
        this.idCuartoDisp = idCuartoDisp +1;

        conjuntoCuarto[conjuntoCuarto.length-1] = "Cuarto------------ \n IdCuarto: "+getIdCuarto()+", \nIdCasa: "+getIdCasa()+", \nNombreCuarto: "+getNombreCuarto()+", \nNumeroPiso: "+getNumero_Piso()+"-";
        conjuntoDisp[conjuntoDisp.length-1] = "Dispositivos_Cuarto----------- \n IdCuartoDispositivo: "+getIdCuartoDisp()+", \nIdCuarto: "+getIdCuarto()+", \nTipoDispositivo: "+getTipoDesp()+"-";

        cuartos.add(conjuntoCuarto[conjuntoCuarto.length-1]);
        cuartos_dispositivos.add(conjuntoDisp[conjuntoDisp.length-1]);

        for(int i=0; i<cuartos.size(); i++){
            datosCuartos += cuartos.get(i);
        }

        for(int i=0; i<cuartos_dispositivos.size(); i++){
            datosCuartos += cuartos_dispositivos.get(i);
        }
        cuartos.clear();
        cuartos_dispositivos.clear();
        return datosCuartos +"\n"+ datosCuartos;
    }
}
