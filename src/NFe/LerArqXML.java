package NFe;


import Utilitarios.Corretores;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class LerArqXML {

    List<NFeProdutosBeans> listProdutos;
    String Caminho;
    NFeBeans nfeB;

    public LerArqXML(String Caminho) {
        this.Caminho = Caminho;
        nfeB = new NFeBeans();
    }

    public NFeBeans lerXML() {
        listProdutos = new ArrayList();
        System.out.println(Caminho);
        File f = new File(Caminho);
        SAXBuilder sb = new SAXBuilder();
        Document d;
        try {
            d = (Document) sb.build(f);
            Element nfe = d.getRootElement();   //Recuperamos o elemento root    
            List elements = nfe.getChildren(); //Recuperamos os elementos filhos (children)  
            Iterator i = elements.iterator();

            while (i.hasNext()) {
                Element element = (Element) i.next();
                //System.out.println("Nome Element " + element.getName());
                trataElement(element, nfeB);
            }
            nfeB.setListProdutos(listProdutos);
            //listProdutos.forEach((l) -> {
            //    System.out.println(l);
            //});
            //System.out.println(nfeB.getChNFe());

        } catch (JDOMException ex) {
            Logger.getLogger(LerArqXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LerArqXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nfeB;
    }

    private void trataElement(Element element, NFeBeans nfeB) {
        List elements = element.getChildren();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element el = (Element) it.next();
            switch (el.getName()) {
                case "chNFe":
                    nfeB.setChNFe(el.getText());
                    break;
                case "cUF":
                    nfeB.setcUF(new Integer(el.getText()));
                    break;
                case "cNF":
                    nfeB.setcNF(new Integer(el.getText()));
                    break;
                case "natOp":
                    nfeB.setNatOp(el.getText());
                    break;
                case "nNF":
                    nfeB.setnNF(new Integer(el.getText()));
                    break;
                case "dhEmi":
                    Date dtEmi = new Date();
                    try {
                        dtEmi = new SimpleDateFormat("yyyy-MM-dd").parse(el.getText());
                    } catch (ParseException ex) {
                        Logger.getLogger(LerArqXML.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    nfeB.setDhEmi(Corretores.ConverterDateAAAA_MM_DD(dtEmi));
                    break;
                case "cMunFG":
                    nfeB.setcMunFG(new Integer(el.getText()));
                    break;
                case "CNPJ":
                    if (element.getName().equals("emit")) {
                        nfeB.setCNPJ(el.getText());
                    }
                    break;
                case "xNome":
                    if (element.getName().equals("emit")) {
                        nfeB.setxNome(el.getText());
                    }
                    break;
                case "xFant":
                    nfeB.setxFant(el.getText());
                    break;
                case "xLgr":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setxLgr(el.getText());
                    }
                    break;
                case "nro":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setNro(el.getText());
                    }
                    break;
                case "xBairro":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setxBairro(el.getText());
                    }
                    break;
                case "cMun":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setcMun(new Integer(el.getText()));
                    }
                    break;
                case "xMun":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setxMun(el.getText());
                    }
                    break;
                case "UF":
                    if (element.getName().equals("enderEmit")) {
                        nfeB.setUF(el.getText());
                    }
                    break;
                case "IE":
                    if (element.getName().equals("dest")) {
                        nfeB.setIEDest(el.getText());
                        System.out.println(nfeB.getIEDest());
                    }
                    break;
                case "vNF":
                    nfeB.setvNF(new Double(el.getText()));
                    break;
                case "nDup":
                    nfeB.setnDup(el.getText());
                    break;
                case "dVenc":
                    //System.out.println(el.getText());
                    nfeB.setdVenc(el.getText());
                    break;
                case "vDup":
                    nfeB.setvDup(new Double(el.getText()));
                    break;
                case "det":
                    //System.out.println("teste nó " + el.getName());
                    addProduto(el);
                    break;
                default:
                    break;
            }
            trataElement(el, nfeB);
        }
    }

    private void addProduto(Element el) {
        NFeProdutosBeans prodB = new NFeProdutosBeans();
        List atrib = el.getAttributes();
        Iterator it_atrib = atrib.iterator();
        while (it_atrib.hasNext()) {
            Attribute atribib = (Attribute) it_atrib.next();
            //System.out.println("atributo " + atrib.getValue());
            //prodB = new NFeProdutosBeans();
        }

        List produtos = el.getChildren();
        Iterator itProd = produtos.iterator();

        while (itProd.hasNext()) {
            Element elProd = (Element) itProd.next();
            //System.out.println("teste nó " + elProd.getName());
            if (elProd.getName().equals("prod")) {
                List produtos2 = elProd.getChildren();
                Iterator it2 = produtos2.iterator();
                while (it2.hasNext()) {
                    Element elProd2 = (Element) it2.next();
                    //System.out.println("teste nó " + elProd2.getText());
                    switch (elProd2.getName()) {
                        case "cProd":
                            prodB.setcProd(elProd2.getText());
                            break;
                        case "xProd":
                            prodB.setxProd(elProd2.getText());
                            break;
                        case "NCM":
                            prodB.setNCM(elProd2.getText());
                            break;
                        case "CEST":
                            prodB.setCEST(elProd2.getText());
                            break;
                        case "uCom":
                            prodB.setuCom(elProd2.getText());
                            break;
                        case "qCom":
                            prodB.setqCom(new Double(elProd2.getText()));
                            break;
                        case "vUnCom":
                            prodB.setvUnCom(new Double(elProd2.getText()));
                            break;
                        case "vProd":
                            prodB.setvProd(new Double(elProd2.getText()));
                            if (prodB.getcProd() != null) {
                                listProdutos.add(prodB);
                                addProduto(elProd2);
                            }
                            break;
                        case "vDesc":
                            prodB.setvDesc(new Double(elProd2.getText()));
                            prodB.setValorUnitFinal();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //String Caminho = "C:\\Users\\agroa\\Desktop\\teste.xml";
        //LerArqXML lexml = new LerArqXML(Caminho);

        //lexml.lerXML(Caminho, Nfe);
    }
}
