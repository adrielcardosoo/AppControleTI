package com.sistema.dao;

import com.sistema.controle.Conexao;
import com.sistema.controle.Exceptions;
import com.sistema.bean.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadLoginDao {

    Conexao conn = new Conexao();
    Login logMod = new Login();

    public void delete(String codigo) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "delete from LOGINS where CODIGO = ?";
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

    public void insert(Login login) throws Exception {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "insert into LOGINS (CODIGO, NOME, LOGIN, SENHA) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, login.getCodigo());
            ps.setString(2, login.getNome());
            ps.setString(3, login.getLogin());
            ps.setString(4, login.getSenha());
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

    public void update(Login login) throws Exceptions {
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = conex.getConnection();
            String sql = "update LOGINS set NOME = ?, LOGIN = ?, SENHA = ? where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, login.getNome());
            ps.setString(2, login.getLogin());
            ps.setString(3, login.getSenha());
            ps.setInt(4, login.getCodigo());
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

    public boolean exists(Login login) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select LOGIN from LOGINS where SENHA = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, login.getSenha());
            ResultSet rs = ps.executeQuery();
            rs.next();

            String loginUser = rs.getString(1);

            condicao = true;

        } catch (SQLException e) {
            condicao = false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"USUARIO OU SENHA INVALIDO");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"USUARIO OU SENHA INVALIDO");
                }
            }
        }
        return condicao;

    }
    
    public boolean select(Login login) throws Exceptions {
        boolean condicao = false;
        Conexao conex = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = conex.getConnection();
            String sql = "select NOME, LOGIN, SENHA from LOGINS where CODIGO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(login.getCodigo()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            String nome = rs.getString(1);
            String loginUser = rs.getString(2);
            String senhaUser = rs.getString(3);

            login.setNome(nome);
            login.setLogin(loginUser);
            login.setSenha(senhaUser);
            
            condicao = true;

        } catch (SQLException e) {
            condicao = false;
        } 
        return condicao;

    }

}
