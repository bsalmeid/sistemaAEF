package Utilitarios;

import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Corretores {

    public static String ConverterParaSQL(String Data) {
        if (Data != null) {
            return Data.substring(6, 10) + "-" + Data.substring(3, 5) + "-" + Data.substring(0, 2);
        } else {
            return null;
        }
    }

    public static String ConverterParaCPF(String numero) {
        String CPF = "";
        try {
            if (numero.length() == 11) {
                CPF = numero.substring(0, 3) + "." + numero.substring(3, 6) + "." + numero.substring(6, 9) + "-" + numero.substring(9, 11);
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar CPF, verifique o campo CPF!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return null;
        }
        return CPF;
    }

    public static String ConverterParaCNPJ(String numero) {
        String CNPJ = "";
        try {
            // 03.717.080/0001-99
            if (numero.length() == 14) {
                CNPJ = numero.substring(0, 2) + "." + numero.substring(2, 5) + "." + numero.substring(5, 8) + "/" + numero.substring(8, 12) + "-" + numero.substring(12, 14);
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar CPF, verifique o campo CPF!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return null;
        }
        return CNPJ;
    }

    public static String ConverterParaJava(String Data) {
        try {
            if (Data != null) {
                return Data.substring(8, 10) + "/" + Data.substring(5, 7) + "/" + Data.substring(0, 4);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Double ConverterReaisDouble(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            return Double.parseDouble(Valor.replace("R$", "").replace(".", "").replace(",", "."));
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Double ConverterPorcentagemDouble(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            return Double.parseDouble(Valor.replace("%", "").replace(".", "").replace(",", "."));
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal ConverterReaisBigDecimal(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }

        try {
            DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
            dff.setParseBigDecimal(true);
            //String string = (Valor.replace("R$", "").replace(".", "").replace(",", "."));
            return (BigDecimal) dff.parse(Valor);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double ConverQuilosDouble(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        double vf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]]", "").replaceAll(",", "."));
        return vf;
    }

    public static Double ConverterStringDouble(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        double vf = 0.00;
        try {
            vf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]&&[^.]]", "").replace(".", "").replaceAll(",", "."));
        } catch (Exception e) {
        }
        return vf;
    }

    public static String ConverterDoubleQuilos(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }

        try {
            double valorf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]]", "").replaceAll(",", "."));
            return new DecimalFormat("#,##0.00 kg").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterDecimalReais(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            double valorf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]]", "").replace(".", "").replace(",", "."));
            return new DecimalFormat("R$ #,##0.00").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterDecimalReais(String Valor, String Sifra) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            double valorf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]]", "").replace(".", "").replace(",", "."));
            return new DecimalFormat(Sifra + " #,##0.00").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }
    
    public static String ConverterDecimalPorcentagem(String Valor) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            double valorf = Double.parseDouble(Valor.replace("%", "").replace(".", "").replace(",", "."));
            return new DecimalFormat("#,##0.00%").format(valorf);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e+"Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterParaDecimal(String Valor, String Unidade) {
        if (Valor.equals("")) {
            Valor = "0";
        }
        try {
            double valorf = Double.parseDouble(Valor.replaceAll("[^0-9&&[^,]]", "").replace(".", "").replace(",", "."));
            return new DecimalFormat("#,###,##0.00 " + Unidade).format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterParaDecimal(Double Valor, String Unidade) {
        try {
            return new DecimalFormat("##,##0.00 " + Unidade).format(Valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterDoubleDecimal(Double Valor) {
        try {
            return new DecimalFormat("#,##0.00").format(Valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return "0,00";
        }
    }

    public static String ConverterDoubleReais(Double Valor) {
        if (Valor == null) {
            Valor = 0.00;
        }

        try {
            Double valorf = (Valor);
            return new DecimalFormat("R$ #,##0.00").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }
    
    public static String ConverterDoublePorcentagem(Double Valor) {
        if (Valor == null) {
            Valor = 0.0;
        }

        try {
            Double valorf = (Valor);
            return new DecimalFormat("#00.00%").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e+"Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static String ConverterDoubleReais(Double Valor, String Sifra) {
        if (Valor == null) {
            Valor = 0.00;
        }
        try {
            Double valorf = (Valor);
            return new DecimalFormat(Sifra + " #,##0.00").format(valorf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de valor, verifique o campo digitado", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
    }

    public static Date PrimeiroDiaMes() {
        Calendar dataAtual = Calendar.getInstance();
        Calendar primeiroDia = Calendar.getInstance();
        primeiroDia.add(Calendar.DAY_OF_MONTH, -dataAtual.get(Calendar.DAY_OF_MONTH));
        primeiroDia.add(Calendar.DAY_OF_YEAR, 1);
        return primeiroDia.getTime();
    }

    public static Date UltimoDiaMes() {
        Calendar dataAtual = Calendar.getInstance();
        Calendar ultimoDia = Calendar.getInstance();
        ultimoDia.add(Calendar.MONTH, 1);
        ultimoDia.add(Calendar.DAY_OF_MONTH, -dataAtual.get(Calendar.DAY_OF_MONTH));
        return ultimoDia.getTime();
    }

    public static Date somarDias(Date data, Integer dias) {
        Calendar dataBase = Calendar.getInstance();
        dataBase.setTime(data);
        dataBase.add(Calendar.DAY_OF_MONTH, dias);
        return dataBase.getTime();
    }

    public static Boolean verificarData(Date data) {
        boolean resposta;
        String dataSelecionada = new SimpleDateFormat("dd/MM/yyyy").format(data);
        if (dataSelecionada.equals(dataAtual)) {
            resposta = true;
        } else {
            int question = JOptionPane.showConfirmDialog(null, "A data Selecionada é diferente de hoje, Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (question == JOptionPane.YES_OPTION) {
                resposta = true;
            } else {
                resposta = false;
            }
        }
        return resposta;
    }

    public static Boolean verificaDataMensal(Date data) {
        boolean resposta;
        if (data.before(PrimeiroDiaMes()) || data.after(UltimoDiaMes())) {
            int question = JOptionPane.showConfirmDialog(null, "A data Selecionada este fora do mês corrente, Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (question == JOptionPane.YES_OPTION) {
                resposta = true;
            } else {
                resposta = false;
            }
        } else {
            resposta = true;
        }
        return resposta;
    }

    public static Date ConverterStringDateDDMMAAAA(String dt) {
        if (dt != null && !dt.equals("")) {
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(dt);
            } catch (ParseException ex) {
                Logger.getLogger(Corretores.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

    public static Date ConverterStringDateDDMMAA(String dt) {
        if (dt != null && !dt.equals("")) {
            try {
                return new SimpleDateFormat("dd/MM/yy").parse(dt);
            } catch (ParseException ex) {
                Logger.getLogger(Corretores.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

    public static String ConverterDateStringDDMMAAA(Date dt) {
        String s = null;
        try {
            s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        } catch (Exception e) {
        }
        return s;
    }

    public static String ConverterDateAAAA_MM_DD(Date dt) {
        String s = null;
        try {
            s = new SimpleDateFormat("yyyy-MM-dd").format(dt);
        } catch (Exception e) {
        }
        return s;
    }

    public static File ValidarArquivoModelo(String caminhoPlanilha) {
        File caminhoCanonical = null;
        FileInputStream fileStream = null;
        try {
            caminhoCanonical = new File((new File("").getCanonicalPath() + caminhoPlanilha));
            fileStream = new FileInputStream(caminhoCanonical);
        } catch (IOException e) {
            try {
                caminhoCanonical = new File((new File(".").getCanonicalPath() + caminhoPlanilha));
                fileStream = new FileInputStream(caminhoCanonical);
            } catch (IOException e1) {
                try {
                    caminhoCanonical = new File((new File("..").getCanonicalPath() + caminhoPlanilha));
                    fileStream = new FileInputStream(caminhoCanonical);
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, "<html> Erro ao localizar caminho: <br> " + caminhoCanonical + "</html>", "Caminho", 1, FormatarICO.ICObtnSair());
                }
            }
        }
        return caminhoCanonical;
    }

    public static void main(String args[]) {

    }

}
