import com.lindo.Lingd14;

public class Lingo {

    Dieta dieta;
    Object pnLngEnv;
    double solucionValores[] = new double[5];
    int nLastIterationCount;
    Lingd14 lng = new Lingd14();

    static {
        System.loadLibrary("Lingj64_14");
    }

    public Lingo(Dieta dieta) {

        this.dieta = dieta;
        nLastIterationCount = -1;

    }

    private static int MySolverCallback(Object pnLng, int iLoc, Object jobj) {

        Lingo g = (Lingo) jobj;

        int nIterations[] = new int[1];
        Lingd14.LSgetCallbackInfoIntLng(pnLng, Lingd14.LS_IINFO_ITERATIONS_LNG, nIterations);
        if (nIterations[0] != g.nLastIterationCount) {
            g.nLastIterationCount = nIterations[0];
            System.out.println("In Java callback function...iterations = " + g.nLastIterationCount);
        }
        return (0);
    }

    private static int MyErrorCallback(Object pnLng, int nErrorCode, String jsErrMessage, Object jobj) {
        System.out.println("Lingo error code: " + nErrorCode);
        System.out.println("Lingo error message:\n\n " + jsErrMessage);
        return (0);
    }

    public void solve() {

        int nErr;
        double dStatus[] = new double[1];
        int[] nPointersNow = new int[1];
        //STATUS
        nErr = lng.LSsetPointerLng(pnLngEnv, dStatus, nPointersNow);
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LSsetPointerLng() error***: " + nErr);
            return;
        }

        // pass Lingo the name of the solver callback function...
        nErr = lng.LSsetCallbackSolverLng(pnLngEnv, "MySolverCallback", this);
        // ...and the error callback function
        nErr = lng.LSsetCallbackErrorLng(pnLngEnv, "MyErrorCallback", this);
        // construct the script
        // echo input to log file
        String sScript = "SET ECHOIN 1" + "\n";
        // load the model from disk
        sScript = sScript + "TAKE DIETAJAVA.LNG" + "\n";
        // view the formulation
        sScript = sScript + "LOOK ALL" + "\n";
        // solve
        sScript = sScript + "GO" + "\n";
        // exit script processor
        sScript = sScript + "QUIT" + "\n";

        // run the script
        dStatus[0] = -1;
        nLastIterationCount = -1;
        nErr = lng.LSexecuteScriptLng(pnLngEnv, sScript);
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LSexecuteScriptLng error***: " + nErr);
            return;
        }

        // clear the pointers to force update of local arrays
        // ***NOTE*** solution won't get passed to local arrays until
        // LSclearPointersLng is called!!!
        nErr = lng.LSclearPointersLng(pnLngEnv);
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LSclearPointerLng() error***: " + nErr);
            return;
        }
        // check the solution status
        if (dStatus[0] != lng.LS_STATUS_GLOBAL_LNG) {
            System.out.println("***Unable to Solve*** dStatus:" + dStatus[0]);
        }

        //System.out.println("free Java memory: " + Runtime.getRuntime().freeMemory());
    }

    public void setPointer(double[] valores, boolean solucion) {

        int[] nPointersNow = new int[1];
        int nErr;
        if (!solucion) {
            nErr = lng.LSsetPointerLng(pnLngEnv, valores, nPointersNow);
            if (nErr != lng.LSERR_NO_ERROR_LNG) {
                System.out.println("***LSsetPointerLng() error***: " + nErr);
                return;
            }
        } else {
            solucionValores = valores;
            nErr = lng.LSsetPointerLng(pnLngEnv, solucionValores, nPointersNow);
            if (nErr != lng.LSERR_NO_ERROR_LNG) {
                System.out.println("***LSsetPointerLng() error***: " + nErr);
                return;
            }
        }

    }

    public void setPointer(Buffer valores) {
        int[] nPointersNow = new int[1];
        int nErr;
        StringBuffer valoresBuffer = valores.getStringBuffer();

        nErr = lng.LSsetPointerStringLng(pnLngEnv, valoresBuffer, nPointersNow);
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LSsetPointerStringLng() error***: " + nErr);
            return;
        }

    }

    public void openEnvironment() {
        pnLngEnv = lng.LScreateEnvLng();
        int nErr;
        if (pnLngEnv == null) {
            System.out.println("***Unable to create Lingo environment***");
            return;
        }
        // open a log file       
        nErr = lng.LSopenLogFileLng(pnLngEnv, "dietajava.log");
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LSopenLogFileLng() error***: " + nErr);
            return;
        }
        System.out.println("cargado");

    }

    public void closeEnvironment() {
        int nErr;
        // close Lingo's log file
        nErr = lng.LScloseLogFileLng(pnLngEnv);
        if (nErr != lng.LSERR_NO_ERROR_LNG) {
            System.out.println("***LScloseLogFileLng() error***: " + nErr);
            return;
        }       
        // delete the Lingo environment
        lng.LSdeleteEnvLng(pnLngEnv);
        for (int i = 0; i < solucionValores.length; i++) {
            System.out.println(solucionValores[i]);
        }
        
    }
}
