/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.ProvaPSC.excecao;

/**
 *
 * @author Biblioteca
 */
public class MesReferenciaCamposNaoPreenchidosException extends MesReferenciaException {
    
    public MesReferenciaCamposNaoPreenchidosException() {
        super("Preencha todos os campos assinalados com \"*\". Estes campos são obrigatórios");
    }
    
}
