public class Comida {

    private Nutriente nutriente;
    private Alimento alimento;
    private double aporte;    
    
    public Comida(Nutriente nutriente, Alimento alimento, Double aporte) {

        this.nutriente = nutriente;
        this.alimento = alimento;
        this.aporte = aporte;
        
    }

    public void setNutriente(Nutriente nutriente) {
        this.nutriente = nutriente;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public void setAporte(Double aporte) {
        this.aporte = aporte;
    }

    public double getAporte() {
        return aporte;
    }
    

    @Override
    public String toString() {
        return nutriente.getNombre()+" "+alimento.getNombre()+ " "+aporte; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
