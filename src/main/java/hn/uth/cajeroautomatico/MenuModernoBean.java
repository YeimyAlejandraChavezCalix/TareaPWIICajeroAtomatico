package hn.uth.cajeroautomatico;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("MenuModernoBean")
@ViewScoped
public class MenuModernoBean implements Serializable {

    private String Seleccionar;

    public MenuModernoBean() {}


    private String feedback = "Esperando su selección...";


    public String getSeleccionar(String opcion) {
        switch (opcion) {
            case "Saldo":
                feedback = "Mostrando Consulta de Saldo...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;
            case "Movimientos":
                feedback = "Mostrando Movimientos Recientes...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;

            case "Ayuda":
                feedback = "Redirigiendo a pantalla de Ayuda...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;

            case "Retirar":
                feedback = "Preparando Retiro de efectivo. Ingrese el monto...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;

            case "Depositar":
                feedback = "Preparando Depósito. Inserte el dinero...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;

            case "Finalizar":
                feedback = "Gracias por su visita. Retirando tarjeta...";
                this.Seleccionar = "consulta-saldo?faces-redirect=true";
                return this.Seleccionar;

            default:
                feedback = "Opción: " + opcion + " seleccionada. No implementada aún.";
        }
        return null; // Quedarse en la misma vista para mostrar el feedback.
    }

    // Getters y Setters
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}