import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Dieta {

    Alimento[] alimento = new Alimento[5];
    Nutriente[] nutriente = new Nutriente[4];
    ArrayList<Nutriente> nutrientes = new ArrayList<>();
    ArrayList<Alimento> alimentos = new ArrayList<>();
    Comida[] comida = new Comida[nutriente.length * alimento.length];
    ArrayList<Comida> comidas = new ArrayList<>();
    Buffer bufferAlimento = new Buffer();
    Buffer bufferNutriente = new Buffer();
    Lingo lingo;
    Modelo vista;

    public Dieta(Modelo vista) {

        this.vista = vista;
        lingo = new Lingo(this);
        //crearAlimentos();
        //crearNutrientes();
    }

    public void crearAlimentos() {

        alimento[0] = new Alimento("Maiz A");
        alimento[0].setCosto(1);
        bufferAlimento.append(alimento[0]);

        alimento[1] = new Alimento("Avena");
        alimento[1].setCosto(0.5);
        bufferAlimento.append(alimento[1]);

        alimento[2] = new Alimento("Maiz B");
        alimento[2].setCosto(2);
        bufferAlimento.append(alimento[2]);

        alimento[3] = new Alimento("Salvado");
        alimento[3].setCosto(1.2);
        bufferAlimento.append(alimento[3]);

        alimento[4] = new Alimento("Linaza");
        alimento[4].setCosto(3);
        bufferAlimento.append(alimento[4]);
    }

    public void crearNutrientes() {

        nutriente[0] = new Nutriente("DN");
        bufferNutriente.append(nutriente[0]);
        nutriente[1] = new Nutriente("DP");
        bufferNutriente.append(nutriente[1]);
        nutriente[2] = new Nutriente("Ca");
        bufferNutriente.append(nutriente[2]);
        nutriente[3] = new Nutriente("Ph");
        bufferNutriente.append(nutriente[3]);

    }
    
    public void addNutriente(Nutriente nutriente) {
        nutrientes.add(nutriente);
        bufferNutriente.append(nutriente);               
    }
    
    public void addAlimento(Alimento alimento) {
        alimentos.add(alimento);
        bufferAlimento.append(alimento);
    }

    public void crearComida() {
        
        double aporte = 0;
        
        comidas.clear();
        
        for (int i = 0; i < nutrientes.size(); i++) {

            for (int j = 0; j < alimentos.size(); j++) {

                aporte = Double.valueOf((String) vista.dtm.getValueAt(i, j + 2));

                comidas.add(new Comida(nutrientes.get(i), alimentos.get(j), aporte));

            }
        }
    }

    private void getCantidadRequerida() {
            
        double val = 0;
        for (int i = 0; i < nutrientes.size(); i++) {

            val = Double.valueOf((String) vista.dtm.getValueAt(i, 1));

            nutrientes.get(i).setCantRequerida(val);
        }
    }

    public double[] getRequeridos() {

        double requeridos[] = new double[nutrientes.size()];

        for (int i = 0; i < nutrientes.size(); i++) {

            requeridos[i] = nutrientes.get(i).getCantRequerida();
        }

        return requeridos;
    }

    public double[] getCostos() {

        double costos[] = new double[alimentos.size()];

        for (int i = 0; i < alimentos.size(); i++) {

            costos[i] = alimentos.get(i).getCosto();
        }

        return costos;
    }

    public double[] getAportes() {

        int total = alimentos.size()*nutrientes.size();
        
        double aportes[] = new double[total];

        for (int i = 0; i < comidas.size(); i++) {

            aportes[i] = comidas.get(i).getAporte();
        }

        return aportes;
    }

    public double[] getCantidades() {

        double cantidades[] = new double[alimentos.size()];

        for (int i = 0; i < alimentos.size(); i++) {

            cantidades[i] = alimentos.get(i).getCantidad();
        }

        return cantidades;

    }

    public void solve() {
        
        getCantidadRequerida();
        crearComida();

        lingo.openEnvironment();
        lingo.setPointer(bufferNutriente);
        lingo.setPointer(getRequeridos(), false);
        lingo.setPointer(bufferAlimento);
        lingo.setPointer(getCostos(), false);
        lingo.setPointer(getCantidades(), true);
        lingo.setPointer(getAportes(), false);
        lingo.solve();
        System.out.println(bufferNutriente.getStringBuffer());
        System.out.println(bufferNutriente.getStringBuffer().length()+ " LONGITUD");
        System.out.println(bufferNutriente.getStringBuffer().charAt(0));
        System.out.println(bufferNutriente.getStringBuffer().delete(0, 4));
        System.out.println(bufferNutriente.getStringBuffer());
        lingo.closeEnvironment();
    }

}
