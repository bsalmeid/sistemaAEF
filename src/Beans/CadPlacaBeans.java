package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "cad_placabeans")
public class CadPlacaBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadora", nullable = false)
    private TransportadorasBeans transportadora;

    @Column(name = "placa", unique = true, nullable = false)
    private String placa;

    @Column(name = "motorista")
    private String motorista;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cam_proprio", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean camProprio;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano")
    private String ano;

    @Column(name = "carroceria")
    private String carroceria;

    @Column(name = "veiculo")
    private String veiculo;

    @Column(name = "capacidade_carga")
    private Double capacidadeCarga;

    public CadPlacaBeans() {
    }

    public CadPlacaBeans(String placa, String carroceria, String veiculo) {
        this.placa = placa;
        this.carroceria = carroceria;
        this.veiculo = veiculo;
    }
    
    //Construtor está na ordem do TableModel Consulta de Transportadora(PLACA)
    public CadPlacaBeans(TransportadorasBeans transportadora, String placa, String veiculo, String carroceria,Boolean camProprio, String marca, String modelo, String ano, Double capacidadeCarga, Boolean status) {
      //Está na ordem do TableModel
        this.transportadora = transportadora;
        this.placa = placa;
        this.veiculo = veiculo;
        this.carroceria = carroceria;
        this.camProprio = camProprio;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.capacidadeCarga = capacidadeCarga;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getCpf() {
        return cpf;
    }

    public TransportadorasBeans getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadorasBeans transportadora) {
        this.transportadora = transportadora;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getCamProprio() {
        return camProprio;
    }

    public void setCamProprio(Boolean camProprio) {
        this.camProprio = camProprio;
    }

    public Double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(Double capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

}
