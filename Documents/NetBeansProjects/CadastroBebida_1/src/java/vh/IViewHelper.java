package vh;

import entidades.EntidadeDominio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renato
 */
public interface IViewHelper {
    public EntidadeDominio getEntidade(HttpServletRequest request);
    
    public void setView(Resultado resultado,HttpServletRequest request,HttpServletResponse response) 
            throws IOException,ServletException;
}
