package vh;

import entidades.Bebida;
import entidades.EntidadeDominio;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author renato
 */
public class GridBebidas {

    public static String getGridBebidas(List<EntidadeDominio> bebidas) {
        StringBuilder tabela = new StringBuilder();

        tabela.append("<table class='table table-striped'><thead>");
        tabela.append("<tr><th>Ação</th><th>Código</th><th>Nome</th><th>Fornecedor</th><th>Categoria</th><th>Preço</th></tr></thead><tbody>");

        Bebida bebida;
        String preco ;
        DecimalFormat df = new DecimalFormat("0.##");

        for (EntidadeDominio b : bebidas) {
            bebida = (Bebida) b;
            tabela.append("<tr>");
            tabela.append("<td><button type='button' class='btn btn-default pull-left' onclick='editarBebida(");
            tabela.append(bebida.getId());
            tabela.append(")'>Editar <span class='glyphicon glyphicon-edit' aria-hidden='true'></span></button></td>");

            tabela.append("<td>");
            tabela.append(bebida.getId());
            tabela.append("</td>");

            tabela.append("<td>");
            tabela.append(bebida.getNome());
            tabela.append("</td>");

            tabela.append("<td>");
            tabela.append(bebida.getFornecedor().getNome());
            tabela.append("</td>");

            tabela.append("<td>");
            tabela.append(bebida.getCategoria().getNome());
            tabela.append("</td>");

            tabela.append("<td>");
             preco = df.format(bebida.getPreco());
            tabela.append(preco);
            tabela.append("</td>");
            tabela.append("</tr>");

        }

        tabela.append("</tbody></table></div>");
        return tabela.toString();
    }

}
