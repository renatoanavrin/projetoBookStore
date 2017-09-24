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
public abstract class EntidadeDominio {
    private int id;
    private Date dtCadastro;

    public EntidadeDominio(int id, Date dtCadastro) {
        this.id = id;
        this.dtCadastro = dtCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
    
    
}
