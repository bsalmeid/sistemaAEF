
package Beans;


public class EntradaGadoBeans {
    // Dados Nota Fiscal
    private Integer ID;
    private Integer CodigoROM;
    private String FazendaEnt;
    private String Motivo;
    private String Comprador;
    private String NumCompra;;
    private String DataNF;
    private String NumNf;
    private String QuantNF;
    private String ValorNF;
    private String ReaisCab;
    private String Inscricao;
    private String Produtor;
    private String CPFProdutor;
    private String FazendaOrigem;
    private String MetodoInsercao;
    
    //Dados GTA
    private String NumGTA;
    private String QuantGTA;
    private String DataGTA;
    private String FazendaOrigemGTA;
    private String MunicipioGTA;
    private String TipoTransp;
    private String DestinoGTA;
    private String Mate12;
    private String Mate24;
    private String Mate36;
    private String MateM36;
    private String MTotal;
    private String Fate12;
    private String Fate24;
    private String Fate36;
    private String FateM36;
    private String FTotal;
    
    //Dados Minuta
    private String Placa;
    private String Tara;
    private String PesoBruto;
    private String PesoLiq;
    private String PesoM;
    private String Quilometro;
    private String QuantCab;
    private Integer QuantCabOrigem;
    private String NumRefugo;
    private String DestinoFisico;
    private String Categoria;
    private String Transportadora;
    private String NumMinuta;
    private String DataEntradaFisica;
    
    //Beans de pesquisa
    
    private String pesqDataInicial;
    private String pesqDataFinal;
    private String pesqProdutor;
    private String pesqMotivo;
    private String pesqComprador;
    private String pesqFazenda;
    private String pesqNcompra;
    private String pesqFazOrigem;

    public String getPesqFazOrigem() {
        return pesqFazOrigem;
    }

    public void setPesqFazOrigem(String pesqFazOrigem) {
        this.pesqFazOrigem = pesqFazOrigem;
    }
    
    

    public String getMetodoInsercao() {
        return MetodoInsercao;
    }

    public void setMetodoInsercao(String MetodoInsercao) {
        this.MetodoInsercao = MetodoInsercao;
    }

    
    
    
    public String getPesqDataInicial() {
        return pesqDataInicial;
    }

    public void setPesqDataInicial(String pesqDataInicial) {
        this.pesqDataInicial = pesqDataInicial;
    }

    public String getPesqDataFinal() {
        return pesqDataFinal;
    }

    public void setPesqDataFinal(String pesqDataFinal) {
        this.pesqDataFinal = pesqDataFinal;
    }

    public String getPesqProdutor() {
        return pesqProdutor;
    }

    public void setPesqProdutor(String pesqProdutor) {
        this.pesqProdutor = pesqProdutor;
    }

    public String getPesqMotivo() {
        return pesqMotivo;
    }

    public void setPesqMotivo(String pesqMotivo) {
        this.pesqMotivo = pesqMotivo;
    }

    public String getPesqComprador() {
        return pesqComprador;
    }

    public void setPesqComprador(String pesqComprador) {
        this.pesqComprador = pesqComprador;
    }

    public String getPesqFazenda() {
        return pesqFazenda;
    }

    public void setPesqFazenda(String pesqFazenda) {
        this.pesqFazenda = pesqFazenda;
    }

    public String getPesqNcompra() {
        return pesqNcompra;
    }

    public void setPesqNcompra(String pesqNcompra) {
        this.pesqNcompra = pesqNcompra;
    }
    
    public Integer getID() {
        return ID;
    }

    public Integer getQuantCabOrigem() {
        return QuantCabOrigem;
    }

    public void setQuantCabOrigem(Integer QuantCabOrigem) {
        this.QuantCabOrigem = QuantCabOrigem;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    
    public String getFazendaEnt() {
        return FazendaEnt;
    }

    public void setFazendaEnt(String FazendaEnt) {
        this.FazendaEnt = FazendaEnt;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getNumCompra() {
        return NumCompra;
    }

    public void setNumCompra(String NumCompra) {
        this.NumCompra = NumCompra;
    }

    public Integer getCodigoROM() {
        return CodigoROM;
    }

    public void setCodigoROM(Integer CodigoROM) {
        this.CodigoROM = CodigoROM;
    }

    public String getDataNF() {
        return DataNF;
    }

    public void setDataNF(String DataNF) {
        this.DataNF = DataNF;
    }

    public String getNumNf() {
        return NumNf;
    }

    public void setNumNf(String NumNf) {
        this.NumNf = NumNf;
    }

    public String getQuantNF() {
        return QuantNF;
    }

    public void setQuantNF(String QuantNF) {
        this.QuantNF = QuantNF;
    }

    public String getValorNF() {
        return ValorNF;
    }

    public void setValorNF(String ValorNF) {
        this.ValorNF = ValorNF;
    }

    public String getReaisCab() {
        return ReaisCab;
    }

    public void setReaisCab(String ReaisCab) {
        this.ReaisCab = ReaisCab;
    }

    public String getInscricao() {
        return Inscricao;
    }

    public void setInscricao(String Inscricao) {
        this.Inscricao = Inscricao;
    }

    public String getProdutor() {
        return Produtor;
    }

    public void setProdutor(String Produtor) {
        this.Produtor = Produtor;
    }

    public String getCPFProdutor() {
        return CPFProdutor;
    }

    public void setCPFProdutor(String CPFProdutor) {
        this.CPFProdutor = CPFProdutor;
    }

    public String getFazendaOrigem() {
        return FazendaOrigem;
    }

    public void setFazendaOrigem(String FazendaOrigem) {
        this.FazendaOrigem = FazendaOrigem;
    }

    public String getNumGTA() {
        return NumGTA;
    }

    public void setNumGTA(String NumGTA) {
       if (NumGTA.equals("")){
            this.NumGTA = null;
        } else {
            this.NumGTA = NumGTA;
       }
            
    }

    public String getQuantGTA() {
        return QuantGTA;
    }

    public void setQuantGTA(String QuantGTA) {
        this.QuantGTA = QuantGTA;
    }

    public String getDataGTA() {
        return DataGTA;
    }

    public void setDataGTA(String DataGTA) {
        this.DataGTA = DataGTA;
    }

    public String getFazendaOrigemGTA() {
        return FazendaOrigemGTA;
    }

    public void setFazendaOrigemGTA(String FazendaOrigemGTA) {
        this.FazendaOrigemGTA = FazendaOrigemGTA;
    }

    public String getMunicipioGTA() {
        return MunicipioGTA;
    }

    public void setMunicipioGTA(String MunicipioGTA) {
        this.MunicipioGTA = MunicipioGTA;
    }

    public String getTipoTransp() {
        return TipoTransp;
    }

    public void setTipoTransp(String TipoTransp) {
        this.TipoTransp = TipoTransp;
    }

    public String getDestinoGTA() {
        return DestinoGTA;
    }

    public void setDestinoGTA(String DestinoGTA) {
        this.DestinoGTA = DestinoGTA;
    }

    public String getMate12() {
        return Mate12;
    }

    public void setMate12(String Mate12) {
        this.Mate12 = Mate12;
    }

    public String getMate24() {
        return Mate24;
    }

    public void setMate24(String Mate24) {
        this.Mate24 = Mate24;
    }

    public String getMate36() {
        return Mate36;
    }

    public void setMate36(String Mate36) {
        this.Mate36 = Mate36;
    }

    public String getMateM36() {
        return MateM36;
    }

    public void setMateM36(String MateM36) {
        this.MateM36 = MateM36;
    }

    public String getMTotal() {
        return MTotal;
    }

    public void setMTotal(String MTotal) {
        this.MTotal = MTotal;
    }

    public String getFate12() {
        return Fate12;
    }

    public void setFate12(String Fate12) {
        this.Fate12 = Fate12;
    }

    public String getFate24() {
        return Fate24;
    }

    public void setFate24(String Fate24) {
        this.Fate24 = Fate24;
    }

    public String getFate36() {
        return Fate36;
    }

    public void setFate36(String Fate36) {
        this.Fate36 = Fate36;
    }

    public String getFateM36() {
        return FateM36;
    }

    public void setFateM36(String FateM36) {
        this.FateM36 = FateM36;
    }

    public String getFTotal() {
        return FTotal;
    }

    public void setFTotal(String FTotal) {
        this.FTotal = FTotal;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getTara() {
        return Tara;
    }

    public void setTara(String Tara) {
        this.Tara = Tara;
    }

    public String getPesoBruto() {
        return PesoBruto;
    }

    public void setPesoBruto(String PesoBruto) {
        this.PesoBruto = PesoBruto;
    }

    public String getPesoLiq() {
        return PesoLiq;
    }

    public void setPesoLiq(String PesoLiq) {
        this.PesoLiq = PesoLiq;
    }

    public String getPesoM() {
        return PesoM;
    }

    public void setPesoM(String PesoM) {
        this.PesoM = PesoM;
    }

    public String getQuilometro() {
        return Quilometro;
    }

    public void setQuilometro(String Quilometro) {
        if (Quilometro.equals("")){
            this.Quilometro = "0";
        } else {
            this.Quilometro = Quilometro; 
         }
    }

    public String getQuantCab() {
        return QuantCab;
    }

    public void setQuantCab(String QuantCab) {
        this.QuantCab = QuantCab;
    }

    public String getNumRefugo() {
        return NumRefugo;
    }

    public void setNumRefugo(String NumRefugo) {
        this.NumRefugo = NumRefugo;
    }

    public String getDestinoFisico() {
        return DestinoFisico;
    }

    public void setDestinoFisico(String DestinoFisico) {
        this.DestinoFisico = DestinoFisico;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getTransportadora() {
        return Transportadora;
    }

    public void setTransportadora(String Transportadora) {
        this.Transportadora = Transportadora;
    }

    public String getNumMinuta() {
        return NumMinuta;
    }

    public void setNumMinuta(String NumMinuta) {
        this.NumMinuta = NumMinuta;
    }

    public String getDataEntradaFisica() {
        return DataEntradaFisica;
    }

    public void setDataEntradaFisica(String DataEntradaFisica) {
        this.DataEntradaFisica = DataEntradaFisica;
    }
    
    
    
    
    
    
}
