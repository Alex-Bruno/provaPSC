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
public class ContaClienteRepetidaException extends ContaClienteException {
    
    public ContaClienteRepetidaException() {
        super("Já foi cadastrada uma conta para este cliente neste mês");
    }
    
}
