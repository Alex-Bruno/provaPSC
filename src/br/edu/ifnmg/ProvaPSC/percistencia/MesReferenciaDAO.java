/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.ProvaPSC.percistencia;

import br.edu.ifnmg.ProvaPSC.entidade.MesReferencia;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaBandeiraNaoSelecionadaException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaInexistenteException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaRepetidoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Biblioteca
 */
public class MesReferenciaDAO {

    private static final String SELECT_MES_REFERENCIA_MES = "SELECT ANO, MES, TARIFA, BANDEIRA FROM MES_REFERENCIA"
                                                           + " WHERE MES = ?";
    
    private static final String INSERT_MES_REFERENCIA = "INSERT INTO MES_REFERENCIA( ANO, MES, TARIFA, BANDEIRA) VALUES( ?, ?, ?, ?)";
   
    private static final String SELECT_TARIFA_MES = "SELECT TARIFA FROM MES_REFERENCIA WHERE MES = ?";
    
    private static final String SELECT_BANDEIRA_MES = "SELECT BANDEIRA FROM MES_REFERENCIA WHERE MES = ?";
    
    public void verificaMesRepetido(int mes) throws SQLException, MesReferenciaRepetidoException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_MES_REFERENCIA_MES);
            
            comando.setInt(1, mes);
            
            resultado = comando.executeQuery();
            
            if( resultado.next())
                throw new MesReferenciaRepetidoException();
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
    }

    public void insertMesReferencia(MesReferencia mesReferencia) throws Exception {
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement( INSERT_MES_REFERENCIA);
            
            comando.setInt(1, mesReferencia.getAno());
            comando.setInt(2, mesReferencia.getMes());
            comando.setDouble(3, mesReferencia.getTarifa());
            comando.setInt(4, mesReferencia.getBandeira());
            
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

    public void verificaMesCadastrado(int mes) throws SQLException, MesReferenciaInexistenteException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_MES_REFERENCIA_MES);
            
            comando.setInt(1, mes);
            
            resultado = comando.executeQuery();
            
            if( !resultado.next())
                throw new MesReferenciaInexistenteException();
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
    }

    public double selectTarifaMes(int mes) throws SQLException, MesReferenciaInexistenteException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_TARIFA_MES);
            
            comando.setInt(1, mes);
            
            resultado = comando.executeQuery();
            
            if( resultado.next())
                return resultado.getDouble(1);
            else
                throw new MesReferenciaInexistenteException();
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
    }

    public int selectBandeiraMes(int mes) throws SQLException, MesReferenciaInexistenteException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement( SELECT_TARIFA_MES);
            
            comando.setInt(1, mes);
            
            resultado = comando.executeQuery();
            
            if( resultado.next())
                return resultado.getInt(1);
            else
                throw new MesReferenciaInexistenteException();
            
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
    }
    
}
