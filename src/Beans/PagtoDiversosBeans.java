/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class PagtoDiversosBeans {
   
    private Integer ID;
    private Integer IDContaOrigem;
    private String DtPrevista;
    private String DtRealizado;
    private Integer nPedido;
    private Integer nCompra;
    private String Moeda;
    private Double ValorEmMoeda;
    private Double TaxaConverte;
    private Double ValorPagamento;
    private Double ValorCompra;  //Previsão
    private String FormaPagto;
    private String nDocumento;
    private String Banco;
    private String agencia;
    private String conta;
    private String titular;
    private String cpf;
    private String CPF_CNPJ;
    private Integer status;

    // Tela de Resumo
    
    private String DtInicialRES;
    private String DtFinalRES;
    private String TipoDeDataRES;
    private String ContaDeOrigemRES;
    private String BancoDestinoRES;
    private String formaPagtoRES;
    private String nDocRES;
    private String FavorecidoRES;
    private String statusRES;
    private String TipoOrdenacao;

    // Bloco de NF's
    private Integer idPagtoNF;
    private String dtEmissaoNF;
    private String TipoEmissorNF;
    private String CPF_NF;
    private String TipoDocumentoNF;
    private int nDocNF;
    private int idEmissorNF;
    private String EmissorNF;
    private String FazendaNF;
    private BigDecimal VlrNF;

    

}
