package py.una.pol.moviles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.moviles.model.Movil;

@Stateless
public class MovilDAO {
 
	
    @Inject
    private Logger log;
    
	/**
	 * 
	 * @param condiciones 
	 * @return
	 */
	public List<Movil> seleccionar() {
		String query = "SELECT identificador, tipo FROM movil ORDER BY identificador";
		
		List<Movil> lista = new ArrayList<Movil>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Movil m = new Movil();
        		m.setIdentificador(rs.getString(1));
        		m.setTipo(rs.getString(2));
        		
        		lista.add(m);
        	}
        	
        } catch (SQLException ex) {
            log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;
	}
	
	public Movil seleccionarPorIdentificador(String identificador) {
		String SQL = "SELECT identificador, tipo FROM movil WHERE identificador = ? ";
		
		Movil m = null;
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, identificador);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		m = new Movil();
        		m.setIdentificador(rs.getString(1));
        		m.setTipo(rs.getString(2));
        	}
        	
        } catch (SQLException ex) {
        	log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return m;

	}
	
	
    public String insertar(Movil m) throws SQLException {

        String SQL = "INSERT INTO movil(identificador, tipo) "
                + "VALUES(?,?)";
 
        String id = null;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, m.getIdentificador());
            pstmt.setString(2, m.getTipo());
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString(1);
                    }
                } catch (SQLException ex) {
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        	
        return id;	
    }
	

    public String actualizar(Movil m) throws SQLException {

        String SQL = "UPDATE movil SET tipo = ? WHERE identificador = ?";
 
        String id = null;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, m.getTipo());
            pstmt.setString(2, m.getIdentificador());
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
        	log.severe("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    
    public String borrar(String identificador) throws SQLException {

        String SQL = "DELETE FROM persona WHERE identificador = ? ";
 
        String id = null;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, identificador);
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getString(1);
                    }
                } catch (SQLException ex) {
                	log.severe("Error en la eliminación: " + ex.getMessage());
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	log.severe("Error en la eliminación: " + ex.getMessage());
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        		throw ef;
        	}
        }
        return id;
    }
    

}
