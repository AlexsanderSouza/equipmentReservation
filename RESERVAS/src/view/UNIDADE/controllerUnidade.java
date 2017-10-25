package view.UNIDADE;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import model.alerta;
import model.ENTITY.instituicao;
import model.ENTITY.unidade;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controllerUnidade implements Initializable{

	@FXML
    private Button btnVoltar,btnNovo,btnAlterar,btnExcluir,btnSalvar,btnImprimir,btnSair,btnFiltrar;
	
	@FXML
    private TextField txtNome,txtEmail,txtTelefone,txtEndereco, txtIdPesquisa, txtNomePesquisa;
	
	@FXML
	private Tab ctrlPag1, ctrlPag2;

	@FXML
	private TabPane tabPane;

	@FXML
	private TableView<unidade> tbGrid;

	@FXML
	private ComboBox<String> ccBoxInstituicao;
	
	@FXML
	private CheckBox chkAtivo;
	
	// Variavel local
		private unidade vUnidadeSelecionado; // varialvel usada para pegar o id do objeto que foi selecionado na tabela e
											// alterar ao salvar
		private String salvar = "salvarNovo"; // variavel de valida��o para quando clicar em novo o bota� salvar volta a

		private TableColumn<unidade, Integer> tbColum1 = new TableColumn<unidade, Integer>();
		private TableColumn<unidade, String> tbColum2 = new TableColumn<unidade, String>();
		private TableColumn<unidade, String> tbColum3 = new TableColumn<unidade, String>();
		private TableColumn<unidade, String> tbColum4 = new TableColumn<unidade, String>();
		private TableColumn<unidade, String> tbColum5 = new TableColumn<unidade, String>();
		private TableColumn<unidade, String> tbColum6 = new TableColumn<unidade, String>();
		
	
	Controller vCtrl = new Controller();
    alerta vAlerta = new alerta();
	
    @SuppressWarnings("unchecked")
	public void inserirTabela() {

		try {
			tbColum1.setText("Id");/* SETA O TITULO DA GRID */
			tbColum2.setText("Nome");
			tbColum3.setText("E-mail");
			tbColum4.setText("Telefone");
			tbColum5.setText("Endereco");
			tbColum6.setText("Instituicao");
		

			tbColum1.setCellValueFactory(new PropertyValueFactory<unidade, Integer>("id")); /* SETA QUAL CAMPO DA LISTA */
			tbColum2.setCellValueFactory(new PropertyValueFactory<unidade, String>("nome"));
			tbColum3.setCellValueFactory(new PropertyValueFactory<unidade, String>("email"));
			tbColum4.setCellValueFactory(new PropertyValueFactory<unidade, String>("telefone"));
			tbColum5.setCellValueFactory(new PropertyValueFactory<unidade, String>("endereco"));
			tbColum6.setCellValueFactory(new PropertyValueFactory<unidade, String>("instituicao"));
			

			tbGrid.getColumns().addAll(tbColum1, tbColum2, tbColum3, tbColum4, tbColum5, tbColum6);

			ObservableList<unidade> vLista = FXCollections.observableArrayList(vCtrl.ListaUnidade());

			tbGrid.setItems(vLista);

		} catch (Exception e) {
			// TODO: handle exception
			vAlerta.mensagemAlerta("Erro na Fun��o Inserir na Tabela: \n Erro: " + e.getMessage());
		}

	}
            
    public void inserirUnidade() {
		String[] instituicaoSelecionada;
		instituicaoSelecionada = ccBoxInstituicao.getSelectionModel().getSelectedItem().split(" ");

		 unidade vUnidade = new unidade();
		 
	        vUnidade.setNome(txtNome.getText());
	        vUnidade.setEmail(txtEmail.getText());        
	        vUnidade.setTelefone(txtTelefone.getText());
	        vUnidade.setEndereco(txtEndereco.getText()); 
		    vUnidade.setAtivo(chkAtivo.isSelected());
		    vUnidade.setInstituicao(instituicaoSelecionada[0]);

		if(this.salvar.equals("salvarNovo")) {
			vCtrl.InserirUnidade(vUnidade);
    	}else if(this.salvar.equals("alterar")) {
    		vUnidade.setId(vUnidadeSelecionado.getId());
    		vCtrl.alterarUnidade(vUnidade);
    	}
	
	}
	
	public void alimentaCcBoxInstituicao() {
		List<instituicao> instituicao = vCtrl.ListaInstituicao();
		for (instituicao aux : instituicao) {
			ccBoxInstituicao.getItems().add(aux.getId() + " - " + aux.getNome());
		}

	}
	
	public void filtrar() {
		Integer aux;

		 try {
		 aux = Integer.parseInt(txtIdPesquisa.getText());
		 } catch (NumberFormatException e) {
		 // TODO Auto-generated catch block
		 aux = null;
		 }
		
		 ObservableList<unidade> vLista = FXCollections.observableArrayList(vCtrl.filtrarunidade(aux,txtNomePesquisa.getText()));
		
		 tbGrid.setItems(vLista);

	}
	
	
	public void excluir() {
		int attTabela = tbGrid.getSelectionModel().getSelectedIndex();
		unidade vUnidadeSelecionada = tbGrid.getSelectionModel().getSelectedItem();
		
		vCtrl.excluirUnidade(vUnidadeSelecionada);
		tbGrid.getItems().remove(attTabela);
	}

	public void moverPag1() {
		 txtNome.clear();
		 txtEmail.clear();
		 txtTelefone.clear();
		 txtEndereco.clear();
		 chkAtivo.setSelected(true);
		 
		this.salvar = "salvarNovo"; // variavel de valida��o para quando clicar em novo o bota� salvar volta a
		
		ObservableList<unidade> vLista = FXCollections.observableArrayList(vCtrl.ListaUnidade());
		tbGrid.setItems(vLista);
		
		tabPane.getSelectionModel().select(ctrlPag1);
	}

	
	public void moverPag2() {
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
		
		 vUnidadeSelecionado = tbGrid.getSelectionModel().getSelectedItem();
		 
		 txtNome.setText(vUnidadeSelecionado.getNome());
		 txtEmail.setText(vUnidadeSelecionado.getEmail());
		 txtTelefone.setText(vUnidadeSelecionado.getTelefone());
		 txtEndereco.setText(vUnidadeSelecionado.getEndereco());
		 chkAtivo.setSelected(vUnidadeSelecionado.getAtivo());

	}
	
	public void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	public void onShow() {
		
		this.alimentaCcBoxInstituicao();
		this.inserirTabela();
		this.ControlaBotao("novo");

	}
	
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO

		this.onShow();

		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					inserirUnidade();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		ccBoxInstituicao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					//alimentaListViewPermissao();

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
	public TableColumn<unidade, String> getTbColum2() {
		return tbColum2;
	}

	public void setTbColum2(TableColumn<unidade, String> tbColum2) {
		this.tbColum2 = tbColum2;
	}

	public TableColumn<unidade, String> getTbColum3() {
		return tbColum3;
	}

	public void setTbColum3(TableColumn<unidade, String> tbColum3) {
		this.tbColum3 = tbColum3;
	}

	public TableColumn<unidade, String> getTbColum4() {
		return tbColum4;
	}

	public void setTbColum4(TableColumn<unidade, String> tbColum4) {
		this.tbColum4 = tbColum4;
	}

	public TableColumn<unidade, String> getTbColum5() {
		return tbColum5;
	}

	public void setTbColum5(TableColumn<unidade, String> tbColum5) {
		this.tbColum5 = tbColum5;
	}

	public String getSalvar() {
		return salvar;
	}

	public void setSalvar(String salvar) {
		this.salvar = salvar;
	}

	public unidade getvUnidadeSelecionado() {
		return vUnidadeSelecionado;
	}

	public void setvUnidadeSelecionado(unidade vUnidadeSelecionado) {
		this.vUnidadeSelecionado = vUnidadeSelecionado;
	}

}
*/