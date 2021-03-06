/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Usuario;

/**
 *
 * @author Juan
 */
public class UsuarioDAO {

    /**
     * Envía la sentencia SQL para obtener la lista de todos los usuarios y
     * estructura la respuesta en una estructura de datos
     *
     * @return un arraylist con los usuarios cargados
     */
    public ArrayList<Usuario> consultarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT cod_u, name_u, user_u, pass_u, email_u, rol_u "
                + "FROM users ";
        ConexionBD con = new ConexionBD();
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("cod_u"));
                u.setNombrecompleto(rs.getString("name_u"));
                u.setNombreusuario(rs.getString("user_u"));
                u.setContrasena(rs.getString("pass_u"));
                u.setCorreo(rs.getString("email_u"));
                u.setRol(rs.getString("rol_u"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            con.desconectar();
            return null;
        }
        con.desconectar();
        return lista;
    }

    /**
     * Envía la sentencia SQL para obtener la información de 1 usuario
     * específico y estructura la respuesta en un objeto de tipo Usuario
     *
     * @param idAConsultar
     * @return un objeto de tipo Usuario o null si no hay dato
     */
    public Usuario consultarUsuario(int idAConsultar) {
        Usuario u = null;
        String sql = "SELECT cod_u, name_u, user_u, pass_u, email_u, rol_u "
                + "FROM users "
                + "WHERE cod_u = " + idAConsultar + " ";
        ConexionBD con = new ConexionBD();
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("cod_u"));
                u.setNombrecompleto(rs.getString("name_u"));
                u.setNombreusuario(rs.getString("user_u"));
                u.setContrasena(rs.getString("pass_u"));
                u.setCorreo(rs.getString("email_u"));
                u.setRol(rs.getString("rol_u"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            con.desconectar();
            return null;
        }
        con.desconectar();
        return u;
    }

    /**
     * Envía la sentencia SQL para obtener la información de ciertos usuarios
     * mediante filtro y estructura la respuesta en una lista de tipo Usuario
     *
     * @param filtro
     * @return Un arraylist con los datos de Usuario según el filtro
     */
    public ArrayList<Usuario> consultarUsuariosPorFiltro(String filtro) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT cod_u, name_u, user_u, pass_u, email_u, rol_u "
                + "FROM users "
                + "WHERE name_u LIKE '%" + filtro + "%' "
                + "OR user_u LIKE '%" + filtro + "%' "
                + "OR email_u LIKE '%" + filtro + "%' ";
        ConexionBD con = new ConexionBD();
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("cod_u"));
                u.setNombrecompleto(rs.getString("name_u"));
                u.setNombreusuario(rs.getString("user_u"));
                u.setContrasena(rs.getString("pass_u"));
                u.setCorreo(rs.getString("email_u"));
                u.setRol(rs.getString("rol_u"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            con.desconectar();
            return null;
        }
        con.desconectar();
        return lista;
    }

    /**
     * Envía la sentencia SQL para almacenar el dato de un usuario
     *
     * @param u el objeto a guardar
     * @return un int con el id del usuario guardado
     */
    public int guardarNuevoUsuario(Usuario u) {
        int id = 0;
        String sql = "INSERT INTO users (cod_u, name_u, user_u, pass_u, email_u, rol_u) "
                + "VALUES ('" + u.getNombrecompleto() + "', '" + u.getNombreusuario()+ "', '" + u.getContrasena()+ "', '" + u.getCorreo()+ "', '" + u.getRol() + "') ";
        ConexionBD con = new ConexionBD();
        ResultSet rs = con.ejecutarInsert(sql);
        try {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return id;
    }

    /**
     * Actualiza la información de un usuario existente
     *
     * @param u el objeto Usuario
     * @return la cantidad de filas afectadas
     */
    public int guardarUsuarioExistente(Usuario u) {
        int filas = 0;
        String sql = "UPDATE users "
                + "SET name_u = '" + u.getNombrecompleto() + "', user_u = '" + u.getNombreusuario()+ "', pass_u = '" + u.getContrasena()+ "', email_u = '" + u.getCorreo()+ "', rol_u = '" + u.getRol() + "' "
                + "WHERE id = " + u.getId() + " ";
        ConexionBD con = new ConexionBD();
        filas = con.ejecutarUpdate(sql);
        return filas;
    }
}
