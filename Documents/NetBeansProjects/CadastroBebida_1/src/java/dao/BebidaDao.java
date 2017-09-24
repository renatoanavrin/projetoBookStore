/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Bebida;
import entidades.Categoria;
import entidades.EntidadeDominio;
import entidades.Fabricante;
import entidades.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConverteDate;

/**
 *
 * @author renato
 */
public class BebidaDao implements IDAO {

    private Connection conn;

    public BebidaDao() {
        /*try {
            conn = Conexao.abrir();

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        // StringBuilder sql = new StringBuilder();
        try {
            conn = Conexao.abrir();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        String sql;
        Bebida bebida = (Bebida) entidade;
        PreparedStatement pst = null;
        conn.setAutoCommit(false);

        sql = "INSERT INTO BEBIDA "
                + "(ID,"
                + "DTCADASTRO,"
                + "NOME,"
                + "FABRICANTEID,"
                + "DTFABRICACAO,"
                + "TEORALCOOLICO,"
                + "DATAVALIDADE,"
                + "CATEGORIAID,"
                + "FORNECEDORID,"
                + "PRECO,"
                + "ESTOQUEMINIMO,"
                + "ESTOQUEMAXIMO ) "
                + "VALUES "
                + "(?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "? )";

        pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pst.setInt(1, bebida.getId());
        Date dataAtual = ConverteDate.dataAtual();

        Timestamp dtCadastro = new Timestamp(dataAtual.getTime());
        pst.setTimestamp(2, dtCadastro);

        pst.setString(3, bebida.getNome());
        int FabricanteId = bebida.getFabricante().getId();
        pst.setInt(4, FabricanteId);
        Timestamp dtFabricacao = new Timestamp(bebida.getDataFabricacao().getTime());
        pst.setTimestamp(5, dtFabricacao);
        pst.setDouble(6, bebida.getTeorAlcoolico());
        Timestamp dtValidade = new Timestamp(bebida.getDataValidade().getTime());
        pst.setTimestamp(7, dtValidade);
        pst.setInt(8, bebida.getCategoria().getId());
        pst.setInt(9, bebida.getFornecedor().getId());
        pst.setDouble(10, bebida.getPreco());
        pst.setInt(11, bebida.getEstoqueminimo());
        pst.setInt(12, bebida.getEstoquemaximo());

        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        int id = 0;

        if (rs.next()) {
            id = rs.getInt(1);
        }

        bebida.setId(id);
        conn.commit();
        conn.close();

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        
         try {
            conn = Conexao.abrir();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Bebida bebida = (Bebida) entidade;
        String sql;
        PreparedStatement pst;

        sql = "UPDATE BEBIDA"
                + " SET "
                + " NOME = ?,"
                + " FABRICANTEID = ?,"
                + " DTFABRICACAO = ?,"
                + " TEORALCOOLICO = ?,"
                + " DATAVALIDADE = ?,"
                + " CATEGORIAID = ?,"
                + " FORNECEDORID = ?,"
                + " PRECO = ?,"
                + " ESTOQUEMINIMO = ?,"
                + " ESTOQUEMAXIMO = ? "
                + " WHERE "
                + " ID = ?";

        pst = conn.prepareStatement(sql);
        pst.setString(1, bebida.getNome());
        int FabricanteId = bebida.getFabricante().getId();
        pst.setInt(2, FabricanteId);
        Timestamp dtFabricacao = new Timestamp(bebida.getDataFabricacao().getTime());
        pst.setTimestamp(3, dtFabricacao);
        pst.setDouble(4, bebida.getTeorAlcoolico());
        Timestamp dtValidade = new Timestamp(bebida.getDataValidade().getTime());
        pst.setTimestamp(5, dtValidade);
        pst.setInt(6, bebida.getCategoria().getId());
        pst.setInt(7, bebida.getFornecedor().getId());
        pst.setDouble(8, bebida.getPreco());
        pst.setInt(9, bebida.getEstoqueminimo());
        pst.setInt(10, bebida.getEstoquemaximo());
        pst.setInt(11, bebida.getId());

        pst.executeUpdate();
        conn.close();

    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        
         try {
            conn = Conexao.abrir();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Bebida bebida = (Bebida) entidade;
        String sql;
        PreparedStatement pst;

        sql = "DELETE FROM BEBIDA WHERE ID = ? ";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, bebida.getId());

        pst.executeUpdate();
        conn.close();
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade, String adicional, List<String> parametros) throws SQLException {

         try {
            conn = Conexao.abrir();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Bebida bebida = (Bebida) entidade;
        String sql;
        PreparedStatement pst = null;

        sql = " SELECT A.*,"
                + " B.IDCATEGORIA,"
                + " B.NOME CATEGORIANOME,"
                + " B.DATACADASTRO CATDATACADASTRO,"
                + " C.FABRICANTEID,"
                + " C.RZSOCIAL FABRZSOCIAL,"
                + " C.DATACADASTRO FABDATACADASTRO,"
                + " D.FORNECEDORID,"
                + " D.RAZAOSOCIAL FORNERZSOCIAL,"
                + " A.ESTOQUEMINIMO,"
                + " A.ESTOQUEMAXIMO "
                + " FROM "
                + " BEBIDA A "
                + " JOIN CATEGORIA B ON A.CATEGORIAID = B.IDCATEGORIA "
                + " JOIN FABRICANTE C ON A.FABRICANTEID = C.FABRICANTEID "
                + " JOIN FORNECEDOR D ON A.FORNECEDORID = D.FORNECEDORID ";

        if (adicional == null) {
            if (entidade == null) {
                parametros = null;
            } else {
                if (bebida.getId() > 0) {
                    sql = sql + " WHERE A.ID = ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, bebida.getId());
                } else {
                    if (bebida.getCategoria().getNome() != null) {
                        sql = sql + "WHERE B.NOME LIKE ? ";

                        if (bebida.getPrecoInicial() > 0) {
                            sql = sql + " AND A.PRECO >= ? ";
                        }

                        if (bebida.getPrecoFinal() > 0) {
                            sql = sql + " AND A.PRECO <= ? ";
                        }

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, "%" + bebida.getCategoria().getNome() + "%");

                        int x = 1;
                        if (bebida.getPrecoInicial() > 0) {
                            x++;
                            pst.setDouble(x, bebida.getPrecoInicial());
                        }

                        if (bebida.getPrecoFinal() > 0) {
                            x++;
                            pst.setDouble(x, bebida.getPrecoFinal());
                        }

                    } else {
                        if (bebida.getFornecedor().getNome() != null) {
                            sql = sql + " WHERE D.RAZAOSOCIAL LIKE ?  ";

                            if (bebida.getPrecoInicial() > 0) {
                                sql = sql + " AND A.PRECO >= ? ";
                            }

                            if (bebida.getPrecoFinal() > 0) {
                                sql = sql + " AND A.PRECO <= ? ";
                            }

                            pst = conn.prepareStatement(sql);
                            pst.setString(1, "%" + bebida.getFornecedor().getNome() + "%");

                            int x = 1;
                            if (bebida.getPrecoInicial() > 0) {
                                x++;
                                pst.setDouble(x, bebida.getPrecoInicial());
                            }

                            if (bebida.getPrecoFinal() > 0) {
                                x++;
                                pst.setDouble(x, bebida.getPrecoFinal());
                            }
                        }
                    }

                }
            }
        } else {
            pst = conn.prepareStatement(sql);
        }

        ResultSet rs = pst.executeQuery();
        List<EntidadeDominio> bebidas = new ArrayList<EntidadeDominio>();
        while (rs.next()) {
            Fabricante f = new Fabricante(rs.getInt("FABRICANTEID"), rs.getDate("FABDATACADASTRO"), rs.getString("FABRZSOCIAL"));
            Categoria c = new Categoria(rs.getInt("IDCATEGORIA"), rs.getDate("CATDATACADASTRO"), rs.getString("CATEGORIANOME"));
            Fornecedor fo = new Fornecedor(rs.getInt("FORNECEDORID"), rs.getDate("FABDATACADASTRO"), rs.getString("FORNERZSOCIAL"));

            Bebida b = new Bebida(rs.getString("NOME"), f, rs.getDate("DTFABRICACAO"), rs.getDate("DATAVALIDADE"), rs.getFloat("TEORALCOOLICO"), c, fo, rs.getInt("ID"), rs.getDate("DTCADASTRO"), rs.getFloat("PRECO"), 0, 0,rs.getInt("ESTOQUEMINIMO"),rs.getInt("ESTOQUEMAXIMO"));

            bebidas.add(b);
        }
        conn.close();
        return bebidas;
        
    }

}
