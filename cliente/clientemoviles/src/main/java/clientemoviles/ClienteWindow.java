package clientemoviles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import org.apache.http.client.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.swing.JTextPane;
public class ClienteWindow extends JFrame {
	private JTextField movil_identificador;
	private JTextField ubicacion_movil;
	private JTextField ubicacion_latitud;
	private JTextField ubicacion_longitud;
	private JTextField consulta_latitud;
	private JTextField consulta_longitud;
	private JTextField consulta_distancia;
	private JComboBox movil_tipo;
	private JTextArea display;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteWindow frame = new ClienteWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteWindow() {
		setTitle("Servidor moviles - ubicacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 562);
		getContentPane().setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Registrar movil");
		internalFrame.setBounds(15, 12, 416, 151);
		getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setBounds(28, 12, 107, 15);
		internalFrame.getContentPane().add(lblIdentificador);
		
		movil_identificador = new JTextField();
		movil_identificador.setBounds(140, 13, 114, 19);
		internalFrame.getContentPane().add(movil_identificador);
		movil_identificador.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(28, 39, 70, 15);
		internalFrame.getContentPane().add(lblTipo);
		
		JButton btnRegistrarMovil = new JButton("Registrar");
		btnRegistrarMovil.setAction(action);
		btnRegistrarMovil.setBounds(153, 88, 117, 25);
		internalFrame.getContentPane().add(btnRegistrarMovil);
		
		movil_tipo = new JComboBox();
		movil_tipo.setModel(new DefaultComboBoxModel(new String[] {"auto", "avion", "embarcacion"}));
		movil_tipo.setEditable(true);
		movil_tipo.setBounds(76, 37, 154, 24);
		internalFrame.getContentPane().add(movil_tipo);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("Registrar ubicacion");
		internalFrame_1.setBounds(15, 170, 416, 163);
		getContentPane().add(internalFrame_1);
		internalFrame_1.getContentPane().setLayout(null);
		
		JLabel lblMovil = new JLabel("Movil:");
		lblMovil.setBounds(37, 12, 70, 15);
		internalFrame_1.getContentPane().add(lblMovil);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setBounds(37, 39, 70, 15);
		internalFrame_1.getContentPane().add(lblLatitud);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(37, 69, 70, 15);
		internalFrame_1.getContentPane().add(lblLongitud);
		
		JButton btnRegistrarUbicacion = new JButton("Registrar");
		btnRegistrarUbicacion.setAction(action_1);
		btnRegistrarUbicacion.setBounds(153, 100, 117, 25);
		internalFrame_1.getContentPane().add(btnRegistrarUbicacion);
		
		ubicacion_movil = new JTextField();
		ubicacion_movil.setBounds(88, 10, 114, 19);
		internalFrame_1.getContentPane().add(ubicacion_movil);
		ubicacion_movil.setColumns(10);
		
		ubicacion_latitud = new JTextField();
		ubicacion_latitud.setBounds(105, 37, 114, 19);
		internalFrame_1.getContentPane().add(ubicacion_latitud);
		ubicacion_latitud.setColumns(10);
		
		ubicacion_longitud = new JTextField();
		ubicacion_longitud.setBounds(120, 68, 114, 19);
		internalFrame_1.getContentPane().add(ubicacion_longitud);
		ubicacion_longitud.setColumns(10);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("Consultar ubicaciones a x metros");
		internalFrame_2.setBounds(15, 340, 416, 174);
		getContentPane().add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		
		JLabel lblLatitud_1 = new JLabel("Latitud:");
		lblLatitud_1.setBounds(37, 10, 70, 15);
		internalFrame_2.getContentPane().add(lblLatitud_1);
		
		JLabel lblLongitud_1 = new JLabel("Longitud:");
		lblLongitud_1.setBounds(37, 37, 70, 15);
		internalFrame_2.getContentPane().add(lblLongitud_1);
		
		JLabel lblDistanciametros = new JLabel("Distancia (metros):");
		lblDistanciametros.setBounds(37, 65, 157, 15);
		internalFrame_2.getContentPane().add(lblDistanciametros);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setAction(action_2);
		btnConsultar.setBounds(153, 110, 117, 25);
		internalFrame_2.getContentPane().add(btnConsultar);
		
		consulta_latitud = new JTextField();
		consulta_latitud.setBounds(101, 10, 114, 19);
		internalFrame_2.getContentPane().add(consulta_latitud);
		consulta_latitud.setColumns(10);
		
		consulta_longitud = new JTextField();
		consulta_longitud.setBounds(120, 35, 114, 19);
		internalFrame_2.getContentPane().add(consulta_longitud);
		consulta_longitud.setColumns(10);
		
		consulta_distancia = new JTextField();
		consulta_distancia.setBounds(176, 63, 114, 19);
		internalFrame_2.getContentPane().add(consulta_distancia);
		consulta_distancia.setColumns(10);
		
		JInternalFrame internalFrame_3 = new JInternalFrame("Respuesta del servidor");
		internalFrame_3.setBounds(443, 12, 369, 502);
		getContentPane().add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		
		display = new JTextArea();
		display.setWrapStyleWord(true);
		display.setEditable(false);
		display.setBounds(0, 0, 370, 458);
		internalFrame_3.getContentPane().add(display);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(443, 31, 369, 465);
		textArea.setWrapStyleWord(true);
		getContentPane().add(textArea);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Registrar");
			putValue(SHORT_DESCRIPTION, "Registra un nuevo movil");
		}
		public void actionPerformed(ActionEvent e) {
			crearMovil(movil_identificador.getText(), (String)movil_tipo.getSelectedItem());
		}
		
	}
	
	public void crearMovil(String movil_identificador, String movil_tipo) {
		JSONObject json = new JSONObject();
		json.put("identificador", movil_identificador);
		json.put("tipo", movil_tipo);
		final HttpClient httpClient = new DefaultHttpClient();
		try {
			URI uri = null;
			try {
				URIBuilder builder = new URIBuilder("http://localhost:8080/moviles/rest/moviles");
				uri = builder.build();
			} catch (URISyntaxException e) {
				display.setText("Error: " + e.getMessage());
			}
			final HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(new StringEntity(json.toString()));
			HttpResponse response = null;
		    response = httpClient.execute(httpPost);
		    display.setText(EntityUtils.toString(response.getEntity()));
		} catch (IOException ex) {
		    display.setText("Error: " + ex.getMessage());
		}
	}
	
	public void crearUbicacion(String movil, Float latitud, Float longitud) {
		JSONObject json = new JSONObject();
		json.put("movil", movil);
		json.put("latitud", latitud);
		json.put("longitud", longitud);
		final HttpClient httpClient = new DefaultHttpClient();
		try {
			URI uri = null;
			try {
				URIBuilder builder = new URIBuilder("http://localhost:8080/moviles/rest/ubicaciones");
				uri = builder.build();
			} catch (URISyntaxException e) {
				display.setText("Error: " + e.getMessage());
			}
			final HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(new StringEntity(json.toString()));
			HttpResponse response = null;
		    response = httpClient.execute(httpPost);
		    display.setText(EntityUtils.toString(response.getEntity()));
		} catch (IOException ex) {
		    display.setText("Error: " + ex.getMessage());
		}
	}
	public void consultarUbicacion(Float latitud, Float longitud, Float metros) {
		JSONObject json = new JSONObject();
		json.put("latitud", latitud);
		json.put("longitud", longitud);
		json.put("metros", metros);
		final HttpClient httpClient = new DefaultHttpClient();
		try {
			URI uri = null;
			try {
				URIBuilder builder = new URIBuilder("http://localhost:8080/moviles/rest/ubicaciones");
				builder.setParameter("latitud", latitud.toString());
				builder.setParameter("longitud", longitud.toString());
				builder.setParameter("metros", metros.toString());
				uri = builder.build();
			} catch (URISyntaxException e) {
				display.setText("Error: " + e.getMessage());
			}
			final HttpGet httpGet = new HttpGet(uri);
			HttpResponse response = null;
		    response = httpClient.execute(httpGet);
		    display.setText(EntityUtils.toString(response.getEntity()).replaceAll(",", ",\n"));
		} catch (IOException ex) {
		    display.setText("Error: " + ex.getMessage());
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Registrar");
			putValue(SHORT_DESCRIPTION, "Registrar ubicacion");
		}
		public void actionPerformed(ActionEvent e) {
			crearUbicacion(ubicacion_movil.getText(),Float.parseFloat(ubicacion_latitud.getText()),Float.parseFloat(ubicacion_longitud.getText()));
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Consultar");
			putValue(SHORT_DESCRIPTION, "Consultar ubicaciones");
		}
		public void actionPerformed(ActionEvent e) {
			consultarUbicacion(Float.parseFloat(consulta_latitud.getText()),Float.parseFloat(consulta_longitud.getText()),Float.parseFloat(consulta_distancia.getText()));
		}
	}
}

