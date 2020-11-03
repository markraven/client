package hu.mark.client;

public class Messages {

    public Messages(String felado, String cimzett, String message, String targy) {
        this.felado = felado;
        this.cimzett = cimzett;
        this.message = message;
        this.targy = targy;
    }

    public int getId() { return id; }
    public String getFelado() {
        return felado;
    }

    public void setFelado(String felado) {
        this.felado = felado;
    }

    public String getCimzett() {
        return cimzett;
    }

    public void setCimzett(String cimzett) {
        this.cimzett = cimzett;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    private String felado;
    private String cimzett;
    private String message;
    private String targy;
    int id;
}
