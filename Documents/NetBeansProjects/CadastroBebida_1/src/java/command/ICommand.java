/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import vh.Resultado;
import entidades.EntidadeDominio;

/**
 *
 * @author renato
 */
public interface ICommand {

    public Resultado execute(EntidadeDominio entidade);
}
