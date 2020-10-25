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

import py.una.pol.moviles.model.Ubicacion;

@Stateless
public class UbicacionDAO {
 
	
    @Inject
    private Logger log;
    
	/**
	 * 
	 * @param condiciones 
	 * @return
	 */
	public List<Ubicacion> seleccionar() {
		String query = "SELECT id, movil, latitud, longitud, fecha_hora FROM ubicacion ORDER BY movil, fecha_hora";
		
		List<Ubicacion> lista = new ArrayList<Ubicacion>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Ubicacion u = new Ubicacion();
        		u.setId(rs.getLong(1));
        		u.setMovil(rs.getString(2));
        		u.setLatitud(rs.getFloat(3));
        		u.setLongitud(rs.getFloat(4));
        		u.setFechaHora(rs.getTimestamp(5));
        		lista.add(u);
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
	
	public Ubicacion seleccionarPorId(long id) {
		String SQL = "SELECT id, movil, latitud, longitud, fecha_hora FROM ubicacion WHERE id = ? ";
		
		Ubicacion u = null;
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setLong(1, id);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		u = new Ubicacion();
        		u.setId(rs.getLong(1));
        		u.setMovil(rs.getString(2));
        		u.setLatitud(rs.getFloat(3));
        		u.setLongitud(rs.getFloat(4));
        		u.setFechaHora(rs.getTimestamp(5));
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
		return u;

	}
	
	
    public long insertar(Ubicacion u) throws SQLException {

        String SQL = "INSERT INTO ubicacion(movil,latitud,longitud,fecha_hora) "
                + "VALUES(?,?,?,?)";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.getMovil());
            pstmt.setFloat(2, u.getLatitud());
            pstmt.setFloat(3, u.getLongitud());
            pstmt.setTimestamp(4, u.getFechaHora());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                    pstmt.setString(1, u.getMovil());
                    pstmt.setFloat(2, u.getLatitud());
                    pstmt.setFloat(3, u.getLongitud());
                    pstmt.setTimestamp(4, u.getFechaHora());
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
	

    public long actualizar(Ubicacion u) throws SQLException {

        String SQL = "UPDATE ubicacion SET movil = ? , latitud = ?, longitud = ?, fecha_hora = ? WHERE id = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(2, u.getMovil());
            pstmt.setFloat(3, u.getLatitud());
            pstmt.setFloat(4, u.getLongitud());
            pstmt.setTimestamp(5, u.getFechaHora());
            pstmt.setLong(5, u.getId());
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
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
    
    public long borrar(long id) throws SQLException {

        String SQL = "DELETE FROM ubicacion WHERE id = ? ";
 
        long id_af = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, id);
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id_af = rs.getLong(1);
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
        return id_af;
    }
    

}
