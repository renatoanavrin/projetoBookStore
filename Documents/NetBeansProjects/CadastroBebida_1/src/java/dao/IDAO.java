/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.EntidadeDominio;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author renato
 */
public interface IDAO {

    public void salvar(EntidadeDominio entidade) throws SQLException;

    public void alterar(EntidadeDominio entidade) throws SQLException;

    public void excluir(EntidadeDominio entidade) throws SQLException;
   
   public List<EntidadeDominio> consultar(EntidadeDominio entidade, String adicional, List<String> parametros) throws SQLException ;
}
