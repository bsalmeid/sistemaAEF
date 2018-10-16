package Icones;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FormatarICO {

    public static Icon ICObtnSair() {
        Icon Icone = new ImageIcon(Class.class.getResource("/Icones/btn_sair.png"));
        //System.out.println(Icone);
        return Icone;
    }

    public static Icon ICObtnOk() {
        Icon Icone = new ImageIcon(Class.class.getResource("/Icones/ok.png"));
        //System.out.println(Icone);
        return Icone;
    }

    public static ImageIcon logoTipo() {
        ImageIcon logo = new ImageIcon(Class.class.getResource("/Icones/iconeAEF.jpg"));
        return logo;
    }

    public static ImageIcon IconeTreTableAnaliseMercadoria() {
        ImageIcon logo = new ImageIcon(Class.class.getResource("/Icones/gear_ico.ico"));
        return logo;
    }

}
