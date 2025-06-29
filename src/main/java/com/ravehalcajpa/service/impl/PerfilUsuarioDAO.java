package com.ravehalcajpa.service.impl;

import com.ravehalcajpa.connection.conexion;
import com.ravehalcajpa.model.PerfilUsuario;
import com.ravehalcajpa.model.Usuario;
import com.ravehalcajpa.service.DAOedit;
import jakarta.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilUsuarioDAO extends conexion implements DAOedit<PerfilUsuario> {

    @Transactional
    @Override
    public PerfilUsuario getById(Long id) {
        String sql = "SELECT * FROM PerfilUsuario WHERE id = ?";
        try {
            conectar();
            PreparedStatement st = this.getCn().prepareStatement(sql);

            st.setLong(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                PerfilUsuario perfilusuario = new PerfilUsuario();
                perfilusuario.setId(rs.getInt("id"));
                perfilusuario.setNombre(rs.getString("nombre"));
                perfilusuario.setApellido(rs.getString("apellido"));
                perfilusuario.setDni(rs.getInt("dni"));
                perfilusuario.setNacionalidad(rs.getString("nacionalidad"));
                perfilusuario.setDistrito(rs.getString("distrito"));
                perfilusuario.setDireccion(rs.getString("direccion"));

                Usuario usu = new Usuario();
                usu.setId(rs.getInt("usuario_id"));
                perfilusuario.setUsuario(usu);

                return perfilusuario;
            }
        } finally {
            try {
                cerrar();
            } catch (Exception ex) {
                Logger.getLogger(PerfilUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }

    @Override
    @Transactional
    public List<PerfilUsuario> getAll() {
        String sql = "SELECT u.id, u.nombre, u.apellido, u.dni, u.nacionalidad, u.distrito, u.direccion, t.password FROM usuario t"
                + " LEFT JOIN PerfilUsuario u ON t.id = u.usuario_id;";
        List<PerfilUsuario> lista = new ArrayList<>();
        try {
            conectar();
            PreparedStatement st = this.getCn().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PerfilUsuario perfilusuario = new PerfilUsuario();
                perfilusuario.setId(rs.getInt("id"));
                perfilusuario.setNombre(rs.getString("nombre"));
                perfilusuario.setApellido(rs.getString("apellido"));
                perfilusuario.setDni(rs.getInt("dni"));
                perfilusuario.setNacionalidad(rs.getString("nacionalidad"));
                perfilusuario.setDistrito(rs.getString("distrito"));
                perfilusuario.setDireccion(rs.getString("direccion"));

                Usuario usu = new Usuario();
                usu.setPassword(rs.getString("password"));
                perfilusuario.setUsuario(usu);
                lista.add(perfilusuario);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PerfilUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PerfilUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cerrar();
            } catch (Exception ex) {
                Logger.getLogger(PerfilUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

     @Transactional
    public List<PerfilUsuario> getFilteredAttributes() throws SQLException, Exception {
        String sql = "SELECT id, nombre, apellido, dni, nacionalidad, distrito, direccion FROM perfilusuario";
        List<PerfilUsuario> lista = new ArrayList<>();
        try {
            conectar();
            PreparedStatement st = this.getCn().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PerfilUsuario p = new PerfilUsuario();
                p.setId(rs.getLong("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getInt("dni"));
                p.setNacionalidad(rs.getString("nacionalidad"));
                p.setDistrito(rs.getString("distrito"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }

            return lista;
        } finally {
            cerrar();
        }
    }
    
    
    @Override
    public PerfilUsuario update(PerfilUsuario entity) {
        return null;
    }

}
