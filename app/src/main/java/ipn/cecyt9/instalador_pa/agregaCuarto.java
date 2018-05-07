package ipn.cecyt9.instalador_pa;

import java.util.*;

public class agregaCuarto {
    validacionesJT vd = new validacionesJT();

    int idCuarto = 0, idCuartoDisp = 0, idCasa, numero_Piso, tipo_disp;
    String nombreCuarto;
    public ArrayList<String> cuartos = new ArrayList<String>();
    public ArrayList<String> cuartos_dispositivos = new ArrayList<String>();
    public int focos = 0, puertas = 0, camaras = 0, climas = 0;
    String conjuntoCuarto[], conjuntoDisp[];
    String datosCuartos = "", datosDisp = "";
    String despliegue = "";

    public int getFocos() {
        return focos;
    }

    public void setFocos(int focos) {
        this.focos = focos;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public int getCamaras() {
        return camaras;
    }

    public void setCamaras(int camaras) {
        this.camaras = camaras;
    }

    public int getClimas() {
        return climas;
    }

    public void setClimas(int climas) {
        this.climas = climas;
    }

    public int getIdCuartoDisp() {
        return idCuartoDisp;
    }

    public void setIdCuartoDisp(int idCuartoDisp) {
        this.idCuartoDisp = idCuartoDisp;
    }

    public int getTipoDesp() {
        return tipo_disp;
    }

    public boolean agregaDisp(int tipo_disp) {

        if(tipo_disp==1){
            focos++;
        }else if(tipo_disp==2){
            puertas++;
        } else if(tipo_disp==3){
            camaras++;
        } else if(tipo_disp==4){
            climas++;
        }
        this.tipo_disp = tipo_disp;
        return vd.soloNumeros(String.valueOf(getTipoDesp()));


    }

    public void quitaDisp(int tipo_disp){
        if(tipo_disp==1 && focos!=0){
            focos--;
        }else if(tipo_disp==2 && puertas!=0){
            puertas--;
        } else if(tipo_disp==3 && camaras!=0){
            camaras--;
        } else if(tipo_disp==4 && climas!=0){
            climas--;
        }
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


    public void agregaCu(){

        conjuntoCuarto =  new String[idCuarto+1];
        conjuntoDisp = new String[idCuartoDisp+1];
        this.idCuarto = idCuarto +1;
        this.idCuartoDisp = idCuartoDisp +1;

        conjuntoCuarto[conjuntoCuarto.length-1] = ""+getIdCuarto()+""+getIdCasa()+""+getNombreCuarto()+""+getNumero_Piso()+"";
        conjuntoDisp[conjuntoDisp.length-1] = ""+getIdCuartoDisp()+""+getIdCuarto()+""+getTipoDesp()+"";

        cuartos.add(conjuntoCuarto[conjuntoCuarto.length-1]);
        cuartos_dispositivos.add(conjuntoDisp[conjuntoDisp.length-1]);

        for(int i=0; i<cuartos.size(); i++){
            datosCuartos += cuartos.get(i);
        }

        for(int i=0; i<cuartos_dispositivos.size(); i++){
            datosCuartos += cuartos_dispositivos.get(i);
        }


    }

    public String despliegueDatos(){
        despliegue = "Numero de piso: "+getNumero_Piso()+"\n Nombre de Cuarto: "+getNombreCuarto()+"\n Focos: "+getFocos()+"\n Puertas: "+getPuertas()+"\n Cámaras: "+getCamaras()+"\n Climas: "+getClimas()+"";

        return despliegue;
    }
}
