/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.alerta;
import model.ENTITY.unidade;

/**
 *
 * @author WigorPaulo
 */
public class dao_unidade {
	alerta vAlerta = new alerta();
	
	
    public List<unidade> listar() throws Exception{
        
        List<unidade> vListaUnidade = new ArrayList<unidade>();
        java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
        st.executeQuery("SELECT uni.id, uni.nome, uni.email, uni.telefone, uni.endereco, uni.ativo, uni.id_instituicao, inst.nome as nome_instituicao FROM unidade uni left join instituicao inst on uni.id_instituicao = inst.id");
        ResultSet rs = st.getResultSet();
        while(rs.next()){
            unidade vUnidade = new unidade();
            vUnidade.setId(rs.getInt("id"));
            vUnidade.setNome(rs.getString("nome"));
            vUnidade.setEmail(rs.getString("email"));
            vUnidade.setTelefone(rs.getString("telefone"));            
            vUnidade.setEndereco(rs.getString("endereco"));
            vUnidade.setAtivo(rs.getBoolean("ativo"));
            vUnidade.setInstituicao(rs.getString("nome_instituicao")); 
            vListaUnidade.add(vUnidade);
            
        }
        rs.close();
        st.close();
        
        return vListaUnidade;       
    }

	
	public void inserir(unidade pUnidade){
        try {
            String vSQL = "INSERT INTO unidade(id, nome, email, telefone, endereco, ativo, id_instituicao) "
                                      +"VALUES(?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
            st.setString(1, Integer.toString(pUnidade.getId()));
            st.setString(2, pUnidade.getNome());
            st.setString(3, pUnidade.getEmail());
            st.setString(4, pUnidade.getTelefone());
            st.setString(5, pUnidade.getEndereco());
            st.setBoolean(6, pUnidade.getAtivo());
            st.setString(7, pUnidade.getInstituicao());
                        
            st.execute();
            st.close();
           System.out.println("inserido"); 
           vAlerta.mensagemAlerta("Inserido com Sucesso!"); 
            ConexaoDataBase.FecharConexao();
            
        } catch (Exception e) {
        	vAlerta.mensagemAlerta("Erro na Fun��o INSERIR! \n"+"Erro: "+e.getMessage());
        }    
    }
    
	public void alterar(unidade pUnidade) {

		try {

			int permissaoAtiva;

			if (pUnidade.getAtivo() == true) {
				permissaoAtiva = 1;
			} else {
				permissaoAtiva = 0;
			}

			String vSQL = "UPDATE unidade SET `nome`='" + pUnidade.getNome()
    		+ "',`email`='" + pUnidade.getEmail() + "', `telefone`='" + pUnidade.getTelefone() + "', `endereco`='" + pUnidade.getEndereco() + "', `id_instituicao`='" + Integer.parseInt(pUnidade.getInstituicao())
    		+ "',`ativo`='" + permissaoAtiva + "' WHERE `id` ='" + pUnidade.getId() + "'";
    		System.out.println(vSQL);
    		System.out.println(pUnidade.getAtivo());
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			st.execute();
			st.close();
			vAlerta.mensagemAlerta("Alterado com Sucesso!");
			ConexaoDataBase.FecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Fun��o alterar! \n" + "Erro: " + e.getMessage());
		}
	}
	
	public void excluir(unidade pUnidade) {
    	try {
    		
			String vSQL = "DELETE FROM unidade WHERE `id`='" + pUnidade.getId() +"'";
			PreparedStatement st = ConexaoDataBase.getConexaoMySQL().prepareStatement(vSQL);
			
			
			st.execute();
	        st.close();
	        vAlerta.mensagemAlerta("Excluido com Sucesso!");
	        ConexaoDataBase.FecharConexao();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Fun��o Excluir em dao_funcao! \n"+"Erro: "+e.getMessage());
		}
    	
    }
	
	public List<unidade> filtrar(Integer id,String nome) {
    	try {
    		
			String vSQL = "SELECT uni.id, uni.nome, uni.email, uni.telefone, uni.endereco, uni.ativo, uni.id_instituicao, inst.nome as nome_instituicao FROM unidade uni left join instituicao inst on uni.id_instituicao = inst.id where uni.id !=0";
			               
			if(id != null){
				vSQL = vSQL + " and uni.id = "+ id + "";
			}
			
			if(!nome.equals("")){
				vSQL = vSQL + " and uni.nome = '"+nome+"'";
			}
			
			
			List<unidade> vListaUnidade = new ArrayList<unidade>();
			java.sql.Statement st = ConexaoDataBase.getConexaoMySQL().createStatement();
			st.executeQuery(vSQL);
			ResultSet rs = st.getResultSet();
			while(rs.next()){
			    unidade vUnidade = new unidade();
			    vUnidade.setId(rs.getInt("id"));
			    vUnidade.setNome(rs.getString("nome"));
			    vUnidade.setEmail(rs.getString("email"));
			    vUnidade.setTelefone(rs.getString("telefone"));
			    vUnidade.setEndereco(rs.getString("endereco"));
			    vUnidade.setInstituicao(rs.getString("nome_instituicao"));
			    vUnidade.setAtivo(rs.getBoolean("ativo"));
			    			    
			    vListaUnidade.add(vUnidade);
			}
			rs.close();
			st.close();
			
			return vListaUnidade;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			vAlerta.mensagemAlerta("Erro na Fun��o FILTRO! \n"+"Erro: "+e.getMessage());
			return null;
		} 
    }
	
	
}