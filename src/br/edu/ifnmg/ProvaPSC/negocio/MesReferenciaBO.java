/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.ProvaPSC.negocio;

import br.edu.ifnmg.ProvaPSC.entidade.MesReferencia;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaInexistenteException;
import br.edu.ifnmg.ProvaPSC.excecao.MesReferenciaRepetidoException;
import br.edu.ifnmg.ProvaPSC.percistencia.MesReferenciaDAO;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Biblioteca
 */
public class MesReferenciaBO {
    private MesReferenciaDAO mesReferenciaDAO = new MesReferenciaDAO();

    public void insertMesReferecia(MesReferencia mesReferencia) throws SQLException, MesReferenciaException, Exception {
        mesReferenciaDAO.verificaMesRepetido( mesReferencia.getMes());
        mesReferenciaDAO.insertMesReferencia( mesReferencia);
    }

    public void verificaMesCadastrado(int mes) throws SQLException, MesReferenciaInexistenteException {
        mesReferenciaDAO.verificaMesCadastrado( mes);
    }

    public double selectTarifaMes(int mes) throws SQLException, MesReferenciaInexistenteException {
        return mesReferenciaDAO.selectTarifaMes( mes);
    }
    
    public int selectBandeiraMes(int mes) throws SQLException, MesReferenciaInexistenteException {
        return mesReferenciaDAO.selectBandeiraMes( mes);
    }   
}
