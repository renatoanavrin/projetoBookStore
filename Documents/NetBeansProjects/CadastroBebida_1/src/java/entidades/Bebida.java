
package entidades;

import java.util.Date;

/**
 *
 * @author renato
 */
public class Bebida extends EntidadeDominio{
    
    private String nome;
    private  Fabricante fabricante;
    private Date dataFabricacao;
    private Date dataValidade;
    private double teorAlcoolico;
    private Categoria categoria;
    private Fornecedor fornecedor;
    private double preco;
    private double precoInicial;
    private double precoFinal;
    private int estoqueminimo;
    private int estoquemaximo;

    public Bebida(String nome, Fabricante fabricante, Date dataFabricacao, Date dataValidade, double teorAlcoolico, Categoria categoria, Fornecedor fornecedor, int id, Date dtCadastro, double preco,double precoInicial,double precoFinal,int estoqueminimo,int estoquemaximo) {
        super(id, dtCadastro);
        this.nome = nome;
        this.fabricante = fabricante;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.teorAlcoolico = teorAlcoolico;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.precoInicial = precoInicial;
        this.precoFinal = precoFinal;
        this.estoqueminimo = estoqueminimo;
        this.estoquemaximo = estoquemaximo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getTeorAlcoolico() {
        return teorAlcoolico;
    }

    public void setTeorAlcoolico(double teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getEstoqueminimo() {
        return estoqueminimo;
    }

    public void setEstoqueminimo(int estoqueminimo) {
        this.estoqueminimo = estoqueminimo;
    }

    public int getEstoquemaximo() {
        return estoquemaximo;
    }

    public void setEstoquemaximo(int estoquemaximo) {
        this.estoquemaximo = estoquemaximo;
    }
    
    
    
}
