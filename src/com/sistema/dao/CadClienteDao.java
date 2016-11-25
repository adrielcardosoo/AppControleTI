package com.sistema.dao;

import com.sistema.controle.Conexao;
import com.sistema.controle.Exceptions;
import com.sistema.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadClienteDao {

    Conexao conn = new Conexao();
    Cliente cliMod = new Cliente();

    public void delete(String codigo) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "delete from CLIENTES where CODIGO = ?";
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

    public void insert(Cliente cliente) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "insert into CLIENTES (CODIGO, NOME, ENDERECO, BAIRRO, CIDADE, ESTADO) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getCodigo());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getBairro());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getEstado());
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

    public void update(Cliente cliente) throws Exceptions {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "update CLIENTES set NOME = ?, ENDERECO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ? where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getBairro());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getEstado());
            ps.setInt(6, cliente.getCodigo());
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
    
    public boolean select(Cliente cliente) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select NOME, ENDERECO, BAIRRO, CIDADE, ESTADO from CLIENTES where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(cliente.getCodigo()));
            ResultSet rs = ps.executeQuery();
            rs.next();
           
            cliente.setNome(rs.getString(1));
            cliente.setEndereco(rs.getString(2));
            cliente.setBairro(rs.getString(3));
            cliente.setCidade(rs.getString(4));
            cliente.setEstado(rs.getString(5));
            
            condicao = true;

        } catch (SQLException e) {
            condicao = false;
        } 
        return condicao;

    }
    
    public boolean exists(Cliente cliente) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select NOME from CLIENTES where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getCodigo());
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
