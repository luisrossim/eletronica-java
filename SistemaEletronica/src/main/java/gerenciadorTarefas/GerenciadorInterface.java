package gerenciadorTarefas;

import com.formdev.flatlaf.FlatDarkLaf;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Marca;
import dominio.TipoEletronico;
import interfaces.DlgCadCidade;
import interfaces.DlgCadCliente;
import interfaces.DlgCadEletronicoRef;
import interfaces.DlgCadMarca;
import interfaces.DlgCadServico;
import interfaces.DlgLogin;
import interfaces.DlgPesqCliente;
import interfaces.DlgPesqEletronico;
import interfaces.DlgPesqServico;
import interfaces.DlgPesqVenda;
import interfaces.DlgVendaEletronicoRef;
import interfaces.FrmPrincipal;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GerenciadorInterface {
    
    private DlgLogin janLogin = null;
    private FrmPrincipal janPrincipal = null;
    private DlgCadCliente janCadCliente = null;
    private DlgCadCidade janCadCidade = null;
    private DlgCadServico janCadServico = null;
    private DlgCadEletronicoRef janCadEletronicoRef = null;
    private DlgCadMarca janCadMarca = null;
    private DlgVendaEletronicoRef janVendaEletronicoRef = null;
    private DlgPesqCliente janProcurarCliente = null;
    private DlgPesqServico janProcurarServico = null;
    private DlgPesqEletronico janProcurarEletronico = null;
    private DlgPesqVenda janProcurarVenda = null;
    
    
    
    GerenciadorDominio gerDominio;
    
    public GerenciadorInterface() {
        try {
            gerDominio = new GerenciadorDominio();
        } catch (ClassNotFoundException | SQLException  ex) {
            JOptionPane.showMessageDialog(janPrincipal, "Erro de conexão com o banco. " + ex.getMessage() );
                System.exit(-1);
        } 
    }
    
    public GerenciadorDominio getGerDominio() {
        return gerDominio;
    }

    
    
    
    private JDialog abrirJanela(java.awt.Frame parent, JDialog dlg, Class classe) {
        if (dlg == null){     
            try {
                dlg = (JDialog) classe.getConstructor(Frame.class, boolean.class, GerenciadorInterface.class ).newInstance(parent,true,this);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                JOptionPane.showMessageDialog(parent, "Erro ao abrir a janela " + classe.getName() );
            }
        }        
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true); 
        return dlg;
    }
    
    
    
    public void janelaLogin() {
        janLogin = (DlgLogin) abrirJanela(null, janLogin, DlgLogin.class);
    }
   
    public void janelaPrincipal(){
        janPrincipal = new FrmPrincipal(this);
        janPrincipal.setVisible(true);
    }
    
    public void janelaCadCliente() {
        janCadCliente = (DlgCadCliente) abrirJanela(janPrincipal, janCadCliente, DlgCadCliente.class);
    }
    
    public void janelaCadServico(){
        janCadServico = (DlgCadServico) abrirJanela(janPrincipal, janCadServico, DlgCadServico.class);
    }
    
    public void janelaCadEletronicoRef(){
        janCadEletronicoRef = (DlgCadEletronicoRef) abrirJanela(janPrincipal, janCadEletronicoRef, DlgCadEletronicoRef.class);
    }
    
    
    public void janelaCadMarca(){
        janCadMarca = (DlgCadMarca) abrirJanela(janPrincipal, janCadMarca, DlgCadMarca.class);
    }
    
    public void janelaVendaEletronicoRef(){
        janVendaEletronicoRef = (DlgVendaEletronicoRef) abrirJanela(janPrincipal, janVendaEletronicoRef, DlgVendaEletronicoRef.class);
    }
    
    public Cliente janelaProcurarCliente(){
        janProcurarCliente = (DlgPesqCliente) abrirJanela(janPrincipal, janProcurarCliente, DlgPesqCliente.class);
        return janProcurarCliente.getCliente();
    }
    
    public void janelaProcurarServico(){
        janProcurarServico = (DlgPesqServico) abrirJanela(janPrincipal, janProcurarServico, DlgPesqServico.class);
    }
    
    public void janelaProcurarEletronico(){
        janProcurarEletronico = (DlgPesqEletronico) abrirJanela(janPrincipal, janProcurarEletronico, DlgPesqEletronico.class);
    }
    
    public void janelaProcurarVenda(){
        janProcurarVenda = (DlgPesqVenda) abrirJanela(janPrincipal, janProcurarVenda, DlgPesqVenda.class);
    }
    
    public void janelaCadCidade(){
        janCadCidade = (DlgCadCidade) abrirJanela(janPrincipal, janCadCidade, DlgCadCidade.class);
    }
    
   
    
    
    public void carregarComboCidades(JComboBox combo) {
        try {
            List<Cidade> lista = gerDominio.listarCidades();
            combo.setModel( new DefaultComboBoxModel( lista.toArray() )  );
                                   
        } catch (ClassNotFoundException | SQLException  ex) {
            JOptionPane.showMessageDialog(janPrincipal, "Erro ao carregar cidades. " + ex.getMessage() );          
        } 
    }
    
    
    public void carregarComboTipoEletronicos(JComboBox combo) {
        try {
            List<TipoEletronico> lista = gerDominio.listarTipoEletronicos();
            combo.setModel( new DefaultComboBoxModel( lista.toArray() )  );
                                   
        } catch (ClassNotFoundException | SQLException  ex) {
            JOptionPane.showMessageDialog(janPrincipal, "Erro ao carregar tipos de eletronicos. " + ex.getMessage() );          
        }
    }
  
    
    public void carregarComboMarcas(JComboBox combo) {
        try {
            List<Marca> lista = gerDominio.listarMarcas();
            combo.setModel( new DefaultComboBoxModel( lista.toArray() )  );
                                   
        } catch (ClassNotFoundException | SQLException  ex) {
            JOptionPane.showMessageDialog(janPrincipal, "Erro ao carregar marcas. " + ex.getMessage() );          
        } 
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        /* Set the FlatLighLaf and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        //Setando um tema de um repositório externo
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Falha ao iniciar tema FlatDarkLaf" );
        }catch (Exception ex) {
            System.err.println( "Falha ao definir tema" );
        } 
        //</editor-fold>

        GerenciadorInterface gerIG = new GerenciadorInterface();
        gerIG.janelaLogin();
    }    
}

