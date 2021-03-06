package br.senac.sp.entidade.model;

import java.sql.Date;

public class Venda {
    private int id;
    private int idItens;
    private int idCliente;
    private String nomeCliente;
    private int idVendedor;
    private double precoTotal;
    private Date dataDaVenda;
    private Carrinho carrinho;
    private String filial;

    public Venda(int id, Carrinho carrinho, int idCliente, int idVendedor, double precoTotal, Date dataDaVenda) {
        this.id = id;
        this.carrinho = carrinho;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.precoTotal = precoTotal;
        this.dataDaVenda = dataDaVenda;
    }

    public Venda(int idCliente, int idVendedor, String filial, Carrinho carrinho) {
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.carrinho = carrinho;
        this.filial = filial;
    }

    public Venda(int id, int idItens, int idCliente, int idFuncionario, String filial, double precoTotal, Date dataDaVenda) {
        this.id = id;
        this.idItens = idItens;
        this.idCliente = idCliente;
        this.idVendedor = idFuncionario;
        this.filial = filial;
        this.precoTotal = precoTotal;
        this.dataDaVenda = dataDaVenda;
    }

    public Venda(Carrinho carrinho, int idCliente, int idVendedor, double precoTotal, Date dataDaVenda) {
        this.carrinho = carrinho;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.precoTotal = precoTotal;
        this.dataDaVenda = dataDaVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getId() {
        return id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public int getIdItens() {
        return idItens;
    }

    public void setIdItens(int idItens) {
        this.idItens = idItens;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Date getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Date dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }
}
