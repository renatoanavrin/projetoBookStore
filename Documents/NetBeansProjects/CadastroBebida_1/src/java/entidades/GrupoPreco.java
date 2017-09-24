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
public class GrupoPreco extends EntidadeDominio{
    
    float margem;
    String descricao;
    
    public GrupoPreco(int id, Date dtCadastro) {
        super(id, dtCadastro);
    }
   
    
}
