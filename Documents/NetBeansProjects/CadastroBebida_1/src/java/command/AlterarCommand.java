/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.EntidadeDominio;
import vh.Resultado;

/**
 *
 * @author renato
 */
public class AlterarCommand extends AbstractCommand{

    @Override
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.alterar(entidade);
    }
    
}
