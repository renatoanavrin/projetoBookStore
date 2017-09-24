/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author renato
 */
public class Fabricante extends EntidadeDominio{
 
    private String nome;
    
    public Fabricante(int id, Date dtCadastro,String nome) {
        super(id, dtCadastro);
        this.nome = nome;
    }

    
}
