package view.RESERVA;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import model.dataTime.DateTimePicker;
import model.alerta;
import model.entity.*;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class controllerReserva implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
		
	@FXML
	private ComboBox<String> cbxResponsavel,cbxdestinatario,cbxTipoRepeticao,cbxStatus;
		
	@FXML
	private TableView<reserva> tbGrid;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private VBox vboxReserva; //Para usar o bot�o dinamico de data e hora
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	DateTimePicker dataTimeAutor = new DateTimePicker();
	DateTimePicker dataTimeDestinatario = new DateTimePicker();
	//Variavel local
	private TableColumn<reserva, Integer> tbColum1 = new TableColumn<reserva, Integer>(); 		
	private TableColumn<reserva, Integer> tbColum2 = new TableColumn<reserva, Integer>(); 
	private TableColumn<reserva, Integer> tbColum3 = new TableColumn<reserva, Integer>(); 
	private TableColumn<reserva, String> tbColum4  = new TableColumn<reserva, String>(); 
	private TableColumn<reserva, String> tbColum5  = new TableColumn<reserva, String>(); 
	private TableColumn<reserva, String> tbColum6  = new TableColumn<reserva, String>(); 
	
	
	private TableColumn id = new TableColumn("Id");
	private TableColumn autor = new TableColumn("Autor");
	private TableColumn funcao = new TableColumn("Fu��o");
	private TableColumn destinatario = new TableColumn("Destinat�rio");
	private TableColumn confirmacao = new TableColumn("Confirma��o");
	private TableColumn status = new TableColumn("Status");
	
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
    
    int vTipoComboBox = 0;
    
    
    public void dataTimePicker() {                                  //insere o bot�o dinamico de data e hora
    	vboxReserva.setSpacing(25);
    	
    	vboxReserva.getChildren().add(dataTimeAutor);
    	vboxReserva.getChildren().add(dataTimeDestinatario);
    	
    	System.out.println(dataTimeAutor.getTextField().getText());
    }
    
    
    @SuppressWarnings("unchecked")
	public void inserirTabela(){
    	    	
    	try {
    		tbColum1.setText("ID");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("RESPONSAVEL");
        	tbColum3.setText("DESTINATARIO");
        	tbColum4.setText("DATA INICIO");
        	tbColum5.setText("DATA FIM");
        	tbColum6.setText("STATUS");
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id"));/*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id_responsavel"));//PENSAR COMO MOSTRAR O NOME DO USUARIO
        	tbColum3.setCellValueFactory(new PropertyValueFactory<reserva, Integer>("id_destinatario"));
        	tbColum4.setCellValueFactory(new PropertyValueFactory<reserva, String>("data_hora_reserva"));
        	tbColum5.setCellValueFactory(new PropertyValueFactory<reserva, String>("data_hora_final"));
        	tbColum6.setCellValueFactory(new PropertyValueFactory<reserva, String>("status"));
        	        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3,tbColum4,tbColum5,tbColum6);
        	
        	ObservableList<reserva> vLista = FXCollections.observableArrayList(vCtrl.ListaReserva());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Fun��o Inserir na Tabela: \n Erro: "+e.getMessage());
		}		
		    	
    }
    
    //fun�ao para copiar o ID que esta na comboBox
   // String nome = "Vin�cius Mendon�a";
   // String primeiroNome = nome.substring(0, nome.indexOf(" "));
    
//    public void inserirTabela(){
//    	////// Terminar tabela
//    	id.setCellValueFactory(new PropertyValueFactory<usuario, String>("id"));
//    	autor.setCellValueFactory(new PropertyValueFactory<usuario, String>("matricula"));
//    	funcao.setCellValueFactory(new PropertyValueFactory<usuario, String>("senha"));
//    	destinatario.setCellValueFactory(new PropertyValueFactory<usuario, String>("email"));
//    	confirmacao.setCellValueFactory(new PropertyValueFactory<usuario, String>("telefone"));
//    	status.setCellValueFactory(new PropertyValueFactory<usuario, String>("status"));
//    	
//    	tbGrid.getItems().setAll(id, autor, funcao,  destinatario, confirmacao, status);
//    	    }
    
    public void inserirReserva(){
    	reserva vReserva = new reserva();
    	String vResponsavel = cbxResponsavel.getValue();
    	String vDestinatario = cbxdestinatario.getValue();
    	//String vDataInicio = edtDataHoraInicio.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));  //alterado
    	//String vDataFinal = edtDataHoraFinal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));    //alterado
    	
    	
    	vReserva.setId_responsavel( Integer.parseInt( vResponsavel.substring(0, vResponsavel.indexOf(" ")).trim() ));
    	vReserva.setId_destinatario(Integer.parseInt(vDestinatario.substring(0, vDestinatario.indexOf(" ")).trim() ));
    	vReserva.setRepeticao(cbxTipoRepeticao.getValue());
    	vReserva.setStatus(cbxStatus.getValue());
    	vReserva.setData_hora_reserva(dataTimeAutor.getTextField().getText());
    	vReserva.setData_hora_final(dataTimeDestinatario.getTextField().getText());
    	
    	vCtrl.InserirReserva(vReserva);
    }
    
    public void preencherUser(List<usuario> user) throws Exception{
    	    	
    	String vListaUsuario = "";
    	    	
        for (usuario user3 : user){
        	if (vTipoComboBox == 1) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxResponsavel.getItems().addAll(vListaUsuario);
        	}
        	if (vTipoComboBox == 2) {
	        	vListaUsuario = Integer.toString(user3.getId())+" - "+user3.getNome();
	        	cbxdestinatario.getItems().addAll(vListaUsuario);
        	}
        }
        
    }
    
    public void alimentaComboBoxResponsavel(){
    	try {
    		vTipoComboBox = 1;
    		cbxResponsavel.setTooltip(new Tooltip());
    		preencherUser(vCtrl.ListaUsuario());    
			
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Responsavel!");
		} 
    	
    }
    
    public void alimentaComboBoxDestinatario(){
    	try {
    		vTipoComboBox = 2;
    		cbxdestinatario.setTooltip(new Tooltip());
    		preencherUser(vCtrl.ListaUsuario());
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Destinatario!");
		}
    }
    
    public void alimentaComboBoxTipoRepeticao(){
    	try {
    		
    		cbxTipoRepeticao.setTooltip(new Tooltip());
    		cbxTipoRepeticao.getItems().addAll("EVENTO UNICO");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SEGUNDA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA TER�A");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA QUARTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA QUINTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SEXTA");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA SABADO");
    		cbxTipoRepeticao.getItems().addAll("SEMANALMENTE A CADA DOMINGO");
    		cbxTipoRepeticao.getItems().addAll("TODOS OS DIAS");
    		
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Tipo de Repeticao!");
		}
    	
    }
    
    public void alimentaComboBoxStatus(){
    	try {
    		
    		cbxStatus.setTooltip(new Tooltip());
    		cbxStatus.getItems().addAll("ATIVO");
    		cbxStatus.getItems().addAll("PENDENTE");
    		cbxStatus.getItems().addAll("CONCLUIDO");
		} catch (Exception e) {
			vAlerta.mensagemAlerta("Erro ao Alimentar a ComboBox Status!");
		}
    }
    
    public void AlimetaComboBox(){/*Carrega as ComboBox*/
    	alimentaComboBoxResponsavel();
		alimentaComboBoxDestinatario();
		alimentaComboBoxTipoRepeticao();
		alimentaComboBoxStatus();
    }
    
    public void onShow(){
    	this.AlimetaComboBox();
    	this.inserirTabela();
    	this.ControlaBotao("novo");
    }
    
    public void moverPag1(){
    	tabPane.getSelectionModel().select(ctrlPag1);
    }
    
    public void moverPag2(){
    	tabPane.getSelectionModel().select(ctrlPag2);
    }
    
    public void ControlaBotao(String pBotao){
    	switch (pBotao) {
		case "novo":
			btnVoltar.setDisable(true);	
			btnNovo.setDisable(false);
			btnAlterar.setDisable(false);
			btnExcluir.setDisable(false);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(true);
			btnSair.setDisable(false);
			break;
		case "voltar":
			btnVoltar.setDisable(false);	
			btnNovo.setDisable(true);
			btnAlterar.setDisable(true);
			btnExcluir.setDisable(true);
			btnImprimir.setDisable(true);
			btnSalvar.setDisable(false);
			btnSair.setDisable(false);			
			break;
	
		default:
			break;
		}
    }
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.dataTimePicker();
		this.onShow();	
		
		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moverPag1();
				ControlaBotao("novo");
			}
		});
		
		btnNovo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moverPag2();
				ControlaBotao("voltar");
			}
		});
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				inserirReserva();
				
			}
		});
		
		
	}

}
