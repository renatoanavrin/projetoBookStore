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
public class GrupoPrecificacao extends EntidadeDominio {

    String descricao;
    float margem;

    
    public GrupoPrecificacao(int id, Date dtCadastro,String descricao,float margem) {
        super(id, dtCadastro);
        this.descricao = descricao;
        this.margem = margem;
        
    }

}
