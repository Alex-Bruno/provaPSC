/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.ProvaPSC.percistencia;

import br.edu.ifnmg.ProvaPSC.entidade.ContaCliente;
import br.edu.ifnmg.ProvaPSC.excecao.ContaClienteRepetidaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Biblioteca
 */
public class ContaClienteDAO {

    private static final String SELECT_CONTA_MES = "SELECT ID_CONTA, CODIGO_CLIENTE, MES, CONSUMO, VALOR FROM CONTA"
                                                           + " WHERE MES = ?";
    
    private static final String SELECT_CONTA_CLIENTE_MES = "SELECT ID_CONTA, CODIGO_CLIENTE, MES, CONSUMO, VALOR FROM CONTA"
                                                           + " WHERE MES = ? AND CODIGO_CLIENTE = ?";
    
    private static final String INSERT_CONTA_CLIENTE = "INSERT INTO CONTA( CODIGO_CLIENTE, MES, CONSUMO, VALOR)"
                                                     + " VALUES( ?, ?, ?, ?)";
    
    public void verificaContaRepetida(int mes, String codigo) throws SQLException, ContaClienteRepetidaException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_CONTA_CLIENTE_MES);
            
            comando.setInt(1, mes);
            comando.setString(2, codigo);
            
            resultado = comando.executeQuery();
            
            if( resultado.next())
                throw new ContaClienteRepetidaException();
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
    }

    public void insertContaCliente(ContaCliente contaCliente) throws SQLException {
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement( INSERT_CONTA_CLIENTE);
            
            comando.setString(1, contaCliente.getCodigoCliente());
            comando.setInt(2, contaCliente.getMes());
            comando.setInt(3, contaCliente.getConsumo());
            comando.setDouble(4, contaCliente.getValor());
            
            comando.execute();
            conexao.commit();         
        
        } catch( Exception ex){
           
            if( comando != null)
                conexao.rollback();
            ex.printStackTrace();
            throw ex;
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }

    public LinkedList<ContaCliente> selectTodasContaMes(int mes) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        LinkedList< ContaCliente> listaContaClientes = new LinkedList<>();
        ContaCliente contaCliente;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_CONTA_MES);
            
            comando.setInt(1, mes);
            
            resultado = comando.executeQuery();
            
            while( resultado.next()){
                contaCliente = this.extrairLinha( resultado);
                listaContaClientes.add(contaCliente);
            }
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaContaClientes;
    }

    private ContaCliente extrairLinha(ResultSet resultado) throws SQLException {
        ContaCliente contaCliente = new ContaCliente();
        
        contaCliente.setId( resultado.getInt(1));
        contaCliente.setCodigoCliente(resultado.getString(2));
        contaCliente.setMes(resultado.getInt(3));
        contaCliente.setConsumo(resultado.getInt(4));
        contaCliente.setValor(resultado.getDouble(5));
        
        return contaCliente;
    }
    
}
