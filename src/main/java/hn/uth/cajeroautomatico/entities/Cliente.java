package hn.uth.cajeroautomatico.entities;

public class Cliente {
    private String nombre;
    private int cuenta;
    private double saldo;
    private int pin;

    public Cliente() {

    }

    public Cliente(String nombre, int cuenta, double saldo, int pin) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.saldo = saldo;
        this.pin = pin;
    }
    public String getNombre() {
        return nombre;
    }
    public int getCuenta() {
        return cuenta;
    }
    public int getPin() {
        return pin;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
