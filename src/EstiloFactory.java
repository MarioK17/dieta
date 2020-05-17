import javax.swing.UIManager;

public class EstiloFactory {

    public static void crearEstilo(FrameEstilo estilo) {

        String tipoEstilo = null;

        switch (estilo) {

            case METAL:
                tipoEstilo = "Metal";
                break;
            case NIMBUS:
                tipoEstilo = "Nimbus";
                break;
            case MOTIF:
                tipoEstilo = "CDE/Motif";
                break;
            case WINDOWS:
                tipoEstilo = "Windows";
                break;
            case WINDOWS_CLASSIC:
                tipoEstilo = "Windows Classic";
                break;

        }

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (tipoEstilo.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EstiloFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstiloFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstiloFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstiloFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
