
public class Nutriente {
    
    private String nombre;
    private double cantRequerida;

    public Nutriente(String nombre) {
        this.nombre = nombre;
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantRequerida() {
        return cantRequerida;
    }

    public void setCantRequerida(double cantRequerida) {
        this.cantRequerida = cantRequerida;
    }
    
    
    
}
