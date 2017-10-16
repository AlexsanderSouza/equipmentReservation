/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import model.alerta;
import model.dao.dao_funcao;
import model.dao.dao_instituicao;
import model.dao.dao_reserva;
import model.dao.dao_unidade;
import model.dao.dao_usuario;
import model.entity.funcao;
import model.entity.instituicao;
import model.entity.reserva;
import model.entity.unidade;
import model.entity.usuario;

/**
 *
 * @author WigorPaulo
 */
public class Controller {
    
    dao_usuario vDaoUser = new dao_usuario();
    dao_unidade vDaoUnidade = new dao_unidade();
    dao_funcao vDaoFuncao = new dao_funcao();
    dao_instituicao vDaoInstituicao = new dao_instituicao();
    dao_reserva vDaoReserva = new dao_reserva();
    alerta vAlerta = new alerta();
   
    public void InserirReserva(reserva pReserva){
    	vDaoReserva.inserir(pReserva);    	
    }
    
    public List<reserva> ListaReserva(){
    	try {
			return vDaoReserva.listar();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
    }
    
    public List<usuario> ListaUsuario(){
    	try {
			return vDaoUser.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;			
		}		
    }
    
    public List<instituicao> listaInstituicao(){
    	try {
			return vDaoInstituicao.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
    }
    
    public void InserirInstituicao(instituicao pInstituicao){
    	vDaoInstituicao.inserir(pInstituicao);    	
    }
    
    public void InserirFuncao(funcao pFuncao){
    	vDaoFuncao.inserir(pFuncao);    	
    }
    
    public void InserirUnidade(unidade pUnidade){
    	vDaoUnidade.inserir(pUnidade);    	
    }
    
    public void InserirUsuario(usuario pUser){
        vDaoUser.inserir(pUser);
    }
        
}
