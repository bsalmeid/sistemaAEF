

package Utilitarios;


public class ThreadLoadProgressBar implements Runnable {

   public FrmProgressBar dialog;

    public ThreadLoadProgressBar() {
        dialog = new FrmProgressBar(null, false, this);
}   

    public void iniciaLoad() {
        Thread thread = new Thread(this);
        thread.start();
   
        //System.out.println("Teste iniciaLOAD");
    }

    @Override
    public void run() {
        try {
        System.out.println("Teste RUN");
        
        } catch (Exception ex) {
            //new StackTrace(ex, "", "ThreadLoad").setVisible(true);
        }
    }

}
