package view.INSTITUICAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import model.alertaInformacao;
import model.ENTITY.instituicao;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class controllerInstituicao implements Initializable{
	
	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone,txtIdPesquisa, txtNomePesquisa;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab ctrlPag1,ctrlPag2;
	
	@FXML
	private CheckBox chkAtivo;
	
	@FXML
	private TableView<instituicao> tbGrid;
	
	
	//Variavel local
		private instituicao vInstituicaoSelecionado;     //varialvel usada para pegar o id do objeto que foi selecionado na tabela e alterar ao salvar
		private String salvar = "salvarNovo";  //variavel de valida��o para quando clicar em novo o bota� salvar volta a inserir e n�o a alterar objetos
		//private String listTable = "table"; //variavel de valida��o para o bot�o excluir saber se deve excluir da tabela ou do listView
		private TableColumn<instituicao, Integer> tbColum1 = new TableColumn<instituicao, Integer>(); 		
		private TableColumn<instituicao, String> tbColum2 = new TableColumn<instituicao, String>();
		private TableColumn<instituicao, String> tbColum3 = new TableColumn<instituicao, String>();
		private TableColumn<instituicao, String> tbColum4 = new TableColumn<instituicao, String>();

		

	Controller vCtrl = new Controller();
    alertaInformacao vAlerta = new alertaInformacao();
    
    
    
    @SuppressWarnings("unchecked")
	public void inserirTabela(){
    	
    	try {
    		
    		tbColum1.setText("Id");/*SETA O TITULO DA GRID*/
        	tbColum2.setText("Nome");
        	tbColum3.setText("Email");
        	tbColum4.setText("Telefone");
    		
    	    tbColum1.setCellValueFactory(new PropertyValueFactory<instituicao, Integer>("id"));  /*SETA QUAL CAMPO DA LISTA*/
        	tbColum2.setCellValueFactory(new PropertyValueFactory<instituicao, String>("nome")); //PENSAR COMO MOSTRAR O NOME DO USUARIO
        	tbColum3.setCellValueFactory(new PropertyValueFactory<instituicao, String>("email"));
        	tbColum4.setCellValueFactory(new PropertyValueFactory<instituicao, String>("telefone"));        	
        	
        	tbGrid.getColumns().addAll(tbColum1,tbColum2,tbColum3,tbColum4);
        	
        	ObservableList<instituicao> vLista = FXCollections.observableArrayList(vCtrl.ListaInstituicao());
        	
        	tbGrid.setItems(vLista);
			
		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Fun��o Inserir na Tabela2: \n Erro: "+e.getMessage());
		}		
		    	
    }
    
    
    public void inserirInstituicao(){
        
        instituicao vInstituicao = new instituicao();
        
        vInstituicao.setNome(txtNome.getText());
        vInstituicao.setEmail(txtEmail.getText());        
        vInstituicao.setTelefone(txtTelefone.getText());
        vInstituicao.setAtivo(chkAtivo.isSelected());
        
        if(this.salvar.equals("salvarNovo")) {
			vCtrl.inserirInstituicao(vInstituicao);
    	}else if(this.salvar.equals("alterar")) {
    		vInstituicao.setId(vInstituicaoSelecionado.getId());
    		
        vCtrl.alterarInstituicao(vInstituicao);
        
    	
    	
    	}
        
        txtNome.clear();
        txtEmail.clear();
        txtTelefone.clear();
        chkAtivo.setSelected(true);

        
  }
    
    
    
    public void filtrar() {
		Integer aux;

		 try {
		 aux = Integer.parseInt(txtIdPesquisa.getText());
		 } catch (NumberFormatException e) {
		 // TODO Auto-generated catch block
		 aux = null;
		 }
		
		 ObservableList<instituicao> vLista = FXCollections.observableArrayList(vCtrl.filtrarinstituicao(aux,txtNomePesquisa.getText()));
		
		 tbGrid.setItems(vLista);

	}

	public void excluir() {
	
		instituicao vInstituicaoSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
		
		vCtrl.excluirInstituicao(vInstituicaoSelecionado);
		tbGrid.getItems().remove(attTabela);
	}

	public void moverPag1() {
		this.salvar = "salvarNovo"; //variavel de valida��o paraquando clicar em novo o bota� salvar volta a inserir e n�o a alterar objetos
    	//this.listTable = "table"; 
		ObservableList<instituicao> vLista = FXCollections.observableArrayList(vCtrl.ListaInstituicao());
		
		tbGrid.setItems(vLista);
		
		tabPane.getSelectionModel().select(ctrlPag1);
	}

	public void moverPag2() {
		//this.listTable  = "listView";
		
		tabPane.getSelectionModel().select(ctrlPag2);
	}

	public void ControlaBotao(String pBotao) {
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
			chkAtivo.setSelected(true);
			break;

		default:
			break;
		}
	}

	public void alterarDados() {

		 this.salvar = "alterar";
		
		 vInstituicaoSelecionado = (tbGrid.getSelectionModel().getSelectedItem());
		 
		 txtNome.setText(vInstituicaoSelecionado.getNome());
		 txtEmail.setText(vInstituicaoSelecionado.getEmail());
		 txtTelefone.setText(vInstituicaoSelecionado.getTelefone());
		 chkAtivo.setSelected(vInstituicaoSelecionado.getAtivo());
		 
		
	}

	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}
	
	public void onShow() {
		this.inserirTabela();
		this.ControlaBotao("novo");

	}

  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		onShow();
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					inserirInstituicao();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					excluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
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
	
	
	btnVoltar.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub				
			moverPag1();
			ControlaBotao("novo");				
		}
	});
	
	btnSair.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub				
			fecharJanela();
		}
	});
	
	btnAlterar.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub				
			alterarDados();
			moverPag2();
			ControlaBotao("voltar");
		}
	});
	
	btnFiltrar.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub				
			filtrar();
		}
	});
}
	
	
	
}
	
/*
	public instituicao getvInstituicaoSelecionado() {
		return vInstituicaoSelecionado;
	}

	public void setvInstituicaoSelecionado(instituicao vinstituicaoSelecionado) {
		this.vInstituicaoSelecionado = vinstituicaoSelecionado;
	}

	public String getSalvar() {
		return salvar;
	}

	public void setSalvar(String salvar) {
		this.salvar = salvar;
	}

	public String getListTable() {
		return listTable;
	}

	public void setListTable(String listTable) {
		this.listTable = listTable;
	}

	public TableColumn<instituicao, Integer> getTbColum1() {
		return tbColum1;
	}

	public void setTbColum1(TableColumn<instituicao, Integer> tbColum1) {
		this.tbColum1 = tbColum1;
	}

	public TableColumn<instituicao, String> getTbColum2() {
		return tbColum2;
	}

	public void setTbColum2(TableColumn<instituicao, String> tbColum2) {
		this.tbColum2 = tbColum2;
	}

	public TableColumn<instituicao, String> getTbColum4() {
		return tbColum4;
	}

	public void setTbColum4(TableColumn<instituicao, String> tbColum4) {
		this.tbColum4 = tbColum4;
	}


}
*/