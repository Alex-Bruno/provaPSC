/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.ProvaPSC.negocio;

import br.edu.ifnmg.ProvaPSC.entidade.ContaCliente;
import br.edu.ifnmg.ProvaPSC.excecao.ContaClienteException;
import br.edu.ifnmg.ProvaPSC.excecao.ContaClienteRepetidaException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaInexistenteException;
import br.edu.ifnmg.ProvaPSC.percistencia.ContaClienteDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Biblioteca
 */
public class ContaClienteBO {
    private ContaClienteDAO contaClienteDAO = new ContaClienteDAO();

    public void insertContaCliente(ContaCliente contaCliente) throws SQLException, MesReferenciaException, ContaClienteException{
        MesReferenciaBO mesReferenciaBO = new MesReferenciaBO();
        mesReferenciaBO.verificaMesCadastrado( contaCliente.getMes());
        contaClienteDAO.verificaContaRepetida( contaCliente.getMes(), contaCliente.getCodigoCliente());
        contaCliente.setValor( this.calculaValorConta( contaCliente));
        contaClienteDAO.insertContaCliente( contaCliente);
    }

    private double calculaValorConta(ContaCliente contaCliente) throws SQLException, MesReferenciaInexistenteException {
        MesReferenciaBO mesReferenciaBO = new MesReferenciaBO();
        double tarifa = mesReferenciaBO.selectTarifaMes( contaCliente.getMes());
        int bandeira = mesReferenciaBO.selectBandeiraMes( contaCliente.getMes());
        double valor;
         
        if( bandeira == 1){
            valor = contaCliente.getConsumo()*(tarifa);
        }else if( bandeira == 2){
            valor = contaCliente.getConsumo()*(tarifa+(tarifa* 0.015));
        }else if( bandeira == 3){
            valor = contaCliente.getConsumo()*(tarifa+(tarifa* 0.030));
        }else{
            valor = contaCliente.getConsumo()*(tarifa+(tarifa* 0.045));
        }
        
        return valor;
        
    }

    public LinkedList<ContaCliente> selectTodasContaMes(int mes) throws SQLException, MesReferenciaException {
        MesReferenciaBO mesReferenciaBO = new MesReferenciaBO();
        mesReferenciaBO.verificaMesCadastrado( mes);
        
        return contaClienteDAO.selectTodasContaMes( mes);
    }
    
    
}
