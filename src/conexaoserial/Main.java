/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaoserial;



public class Main extends SerialCom {

    public String peso;
    
    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
        
    }

    public void fecharCom(){
      
    }
    
    public void lerDados() {
        SerialComLeitura leitura = new SerialComLeitura("COM3", 9600, 100);
        leitura.HabilitarLeitura();
        leitura.ObterIdDaPorta();
        leitura.AbrirPorta();
        leitura.LerDados();
        peso = leitura.getPeso();
        //Controle de tempo da leitura aberta na serial
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.out.println("Erro na Thread: " + ex);
        }
        //leitura.FecharCom();
    }

    public void escreverDados() {
        SerialComLeitura serialEscrita = new SerialComLeitura("COM1", 9600, 0);
        serialEscrita.HabilitarEscrita();
        serialEscrita.ObterIdDaPorta();
        serialEscrita.AbrirPorta();
        serialEscrita.EnviarUmaString("Olá mundo via porta serial!");
        serialEscrita.FecharCom();

    }
}
