/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author renato
 */
public class Livro extends EntidadeDominio{
    
    private int ano;
    private String titulo;
    private List<Categoria> categorias;
    private List<SubCategoria> subCategorias;
    private Editora editora;
    private int edicao;
    private int isbn;
    private int numeroPaginas;
    private String sinopse;
    private float altura;
    private float largura;
    private float peso;
    private float profundidade;
    private Autor autor;
    private GrupoPrecificacao grupoPrecificacao;
    
    public Livro(int id, Date dtCadastro,int ano,String titulo, List<Categoria> categoria,List<SubCategoria> subCategoria,Editora editora,int edicao,int isbn,int numeroPaginas,
            String sinopse,float altura,float largura,float peso,float profundidade,Autor autor,GrupoPrecificacao grupoPrecificacao) {
        super(id, dtCadastro);
       this.ano = ano;
       this.titulo = titulo;
       this.categorias = categoria;
       this.subCategorias = subCategoria;
       this.editora = editora;
       this.edicao = edicao;
       this.isbn = isbn;
       this.numeroPaginas = numeroPaginas;
       this.sinopse = sinopse;
       this.altura = altura;
       this.largura = largura;
       this.peso = peso;
       this.profundidade = profundidade;
       this.autor = autor;
       this.grupoPrecificacao = grupoPrecificacao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<SubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(float profundidade) {
        this.profundidade = profundidade;
    }

    
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public GrupoPrecificacao getGrupoPrecificacao() {
        return grupoPrecificacao;
    }

    public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
        this.grupoPrecificacao = grupoPrecificacao;
    }
    
    
    
    
}
