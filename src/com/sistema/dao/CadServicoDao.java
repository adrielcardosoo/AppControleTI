package com.sistema.dao;

import com.sistema.controle.Conexao;
import com.sistema.controle.Exceptions;
import com.sistema.bean.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadServicoDao {

    Conexao conn = new Conexao();
    Servico servMod = new Servico();

    public void delete(String codigo) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "delete from SERVICOS where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.execute();

            conn.commit();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
    }

    public void insert(Servico servico) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "insert into SERVICOS (CODIGO, DESCRICAO, VALOR, TEMPO_MEDIO) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, servico.getCodigo());
            ps.setString(2, servico.getDescricao());
            ps.setDouble(3, servico.getValor());
            ps.setDouble(4, servico.getTempoMedio());
            ps.execute();

            conn.commit();

        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
    }

    public void update(Servico servico) throws Exceptions {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "update SERVICOS set DESCRICAO = ?, VALOR = ?, TEMPO_MEDIO = ? where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, servico.getDescricao());
            ps.setDouble(2, servico.getValor());
            ps.setDouble(3, servico.getTempoMedio());
            ps.setInt(4, servico.getCodigo());
            ps.execute();

            conn.commit();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
    }
    
    public boolean select(Servico servico) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select DESCRICAO, VALOR, TEMPO_MEDIO from SERVICOS where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(servico.getCodigo()));
            ResultSet rs = ps.executeQuery();
            rs.next();
           
            servico.setDescricao(rs.getString(1));
            servico.setValor(rs.getDouble(2));
            servico.setTempoMedio(rs.getDouble(3));
            
            condicao = true;

        } catch (SQLException e) {
            condicao = false;
        } 
        return condicao;

    }
    
    public boolean exists(Servico servico) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select DESCRICAO from SERVICOS where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, servico.getCodigo());
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            String nome = rs.getString(1);

            condicao = true;

        } catch (SQLException e) {
            condicao = false;
        } 
        
        return condicao;

    }

}
