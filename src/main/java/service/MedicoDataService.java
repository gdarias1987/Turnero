package service;

import entidades.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDataService extends DBDataService {

    public MedicoDataService() {
    }

    public List<Medico> SelectAll(boolean actives_only) {
        String sql;

        if (actives_only) {
            sql = "SELECT * FROM medicos WHERE status = 1;";
        } else {
            sql = "SELECT * FROM medicos;";
        }

        List<Medico> listadoMedicos = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // ITERANDO SOBRE EL RESULTADO
            while (rs.next()) {
                Medico aux = Medico.fromResultSet(rs);

                // AGREGO EN LA LISTA
                listadoMedicos.add(aux);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            return listadoMedicos;
        }
    }

    public Medico createMedico(final Medico medico) {
        String sql = "INSERT INTO medicos (dni,apellido,nombre,valor_consulta) values (?,?,?,?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // SETEAR VALORES DE LA QUERY "?"
            pstmt.setInt(1,medico.getDni());
            pstmt.setString(2, medico.getApellido());
            pstmt.setString(3, medico.getNombre());
            pstmt.setDouble(4, medico.getPrecio_consulta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return medico;
    }

    public Medico getMedicoByID(int id_medico){
        String sql = "SELECT * FROM medicos WHERE id_medico = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,id_medico);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                return Medico.fromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Medico getMedicoByDNI(int dni){
        String sql = "SELECT * FROM medicos WHERE dni = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,dni);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                return Medico.fromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // ALTA/BAJA LOGICA DE MEDICO
    public boolean updateMedicoStatus(final int id_medico, boolean status) {
        String sql = "UPDATE medicos SET status = ? WHERE id_medico = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, id_medico);

            // ACTUALIZAR
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean updateMedico(final Medico medico) {
        String sql = "UPDATE medicos SET nombre = ?, apellido = ?, dni =?, valor_consulta = ? WHERE id_medico = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, medico.getNombre());
            pstmt.setString(2, medico.getApellido());
            pstmt.setDouble(3, medico.getDni());
            pstmt.setDouble(4, medico.getPrecio_consulta());
            pstmt.setInt(5, medico.getId_medico());

            // ACTUALIZAR
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
