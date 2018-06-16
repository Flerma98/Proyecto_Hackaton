package com.tec.fernandoalberto.hackatonsolucionsaludable;

public class Datos {
    private float PH;
    private float CE;
    private float Sal;
    private String Fecha;
    private String Hora;

    public Datos() {
    }

    public Datos(float PH, float CE, float sal, String fecha, String hora) {
        this.PH = PH;
        this.CE = CE;
        Sal = sal;
        Fecha = fecha;
        Hora = hora;
    }

    public float getPH() {
        return PH;
    }

    public void setPH(float PH) {
        this.PH = PH;
    }

    public float getCE() {
        return CE;
    }

    public void setCE(float CE) {
        this.CE = CE;
    }

    public float getSal() {
        return Sal;
    }

    public void setSal(float sal) {
        Sal = sal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}
