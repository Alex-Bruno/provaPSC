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
public class MesReferenciaRepetidoException extends MesReferenciaException{
    
    public MesReferenciaRepetidoException() {
        super("Este mês de referência já está cadastrado no sistema");
    }
    
}
