
package entidades;

import java.util.Date;

/**
 *
 * @author renato
 */
public class Fornecedor extends EntidadeDominio{
    
    private String nome;
    
    public Fornecedor(int id, Date dtCadastro,String nome) {
        super(id, dtCadastro);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    
}
