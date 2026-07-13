package hn.uth.cajeroautomatico;


import hn.uth.cajeroautomatico.entities.Cliente;
import hn.uth.cajeroautomatico.entities.Movimientos;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
@Named("TransaccionesBean")
@SessionScoped
public class TransaccionesBean implements java.io.Serializable {
    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Movimientos> movimientos;
    private int numeroCuenta;
    private int pin;
    private int deposito;
    private int retiro;
    private int transaccionsec = 0;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }


    public Integer getPin() {
        return pin;
    }
    public void setPin(Integer pin) {
        try {
            this.pin = pin;
        }
        catch (Exception e) {
            this.pin = 0;
        }

    }

    public int getNumeroCuenta() {
            return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        try {
            this.numeroCuenta = numeroCuenta;
        }
        catch (Exception e) {
            this.numeroCuenta = 0;
        }
    }

    public int getDeposito() {
        return deposito;
    }

    public void setDeposito(int deposito) {
        try{
            this.deposito = deposito;
        }
        catch(Exception e){
            this.deposito = 0;
        }
    }

    public int getRetiro() {
        return retiro;
    }

    public void setRetiro(int retiro) {
        try{
            this.retiro = retiro;
        }
        catch(Exception e){
            this.retiro = 0;
        }
    }

    public TransaccionesBean() {
        this.cliente = new Cliente();
        this.clientes = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        loadData();


    }

    private void loadData() {
        String csvFilePath = this.getClass().getClassLoader().getResource("clientes.csv").getPath();
        String line;
        System.out.println(csvFilePath);
        clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                // Asegurarse de que la línea tenga el número correcto de valores
                if (values.length == 4) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(values[0].trim());
                    cliente.setCuenta(Integer.parseInt(values[1].trim()));
                    cliente.setPin(Integer.parseInt(values[2].trim()));
                    cliente.setSaldo(Double.parseDouble(values[3].trim()));
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String checklogin() {

        try{
            this.cliente  = clientes.stream()
                    .filter(c -> c.getCuenta() == numeroCuenta && c.getPin() == pin)
                    .findFirst()
                    .orElse(null);
        }   catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No. Cuenta o Pin incorrecto, Corrija!!","No. Cuenta o Pin incorrecto, Corrija!!"));
            return null;
        }

        if (this.cliente != null) {
            return "transacciones.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No. Cuenta o Pin incorrecto, Corrija!!","No. Cuenta o Pin incorrecto, Corrija!!"));
            return null;
        }

    }

    public String addMonto() {
        if(deposito > 0){
            transaccionsec ++;
            cliente.setSaldo(cliente.getSaldo() + deposito);
            movimientos.add(new Movimientos(transaccionsec,"Deposito",deposito));
            setDeposito(0);
            return "deposito_exitoso.xhtml?faces-redirect=true";
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Monto erroneo, Corrija!!","Valor erroneo3"));
            return null;
        }

    }
    public String restMonto() {
        if(retiro > 0){
            if(cliente.getSaldo() >= retiro){
                transaccionsec ++;
                cliente.setSaldo(cliente.getSaldo() - retiro);
                movimientos.add(new Movimientos(transaccionsec,"Retiro",retiro));
                setRetiro(0);
                return "retiro_exitoso.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Monto ingresado supera el saldo, Corrija!!","Valor erroneo!!"));
                return null;
            }

        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Monto erroneo, Corrija!!","Valor erroneo2"));
            return null;
        }

    }
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        // Optionally, redirect the user to a login or home page after logout
        return "index.xhtml?faces-redirect=true";
    }


}
