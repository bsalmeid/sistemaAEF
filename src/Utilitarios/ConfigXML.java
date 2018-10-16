/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class ConfigXML {

    public ConfigXML() {

    }
    
    public static void Editar(String tag, String newValue) {
        File f = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/config.xml");
        SAXBuilder sb = new SAXBuilder();
        Document d;
        try {
            d = (Document) sb.build(f);
            Element nfe = d.getRootElement();   //Recuperamos o elemento root    
            List elements = nfe.getChildren(); //Recuperamos os elementos filhos (children)  
            Iterator i = elements.iterator();
            
            while (i.hasNext()) {
                Element element = (Element) i.next();
                if (element.getName().equals(tag)){
                    element.setText(newValue);
                }
                //System.out.println("Nome Element: " + element.getName() + " Elemente Value: " + element.getValue());
            }

            XMLOutputter xout = new XMLOutputter();
            FileWriter arquivo = new FileWriter(new File(f.getAbsoluteFile().toString()));
            xout.output(d, arquivo);

        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ConfigXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String LerDadosTag(String tag){
        File f = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/config.xml");
        SAXBuilder sb = new SAXBuilder();
        Document d;
        try {
            d = (Document) sb.build(f);
            Element nfe = d.getRootElement();   //Recuperamos o elemento root    
            List elements = nfe.getChildren(); //Recuperamos os elementos filhos (children)  
            Iterator i = elements.iterator();

            while (i.hasNext()) {
                Element element = (Element) i.next();
                if (element.getName().equals(tag)){
                    return element.getValue();
                }
                //System.out.println("Nome Element: " + element.getName() + "Elemente Value: " + element.getValue());
            }

        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ConfigXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void main(String[] args) {
      
    }

}
