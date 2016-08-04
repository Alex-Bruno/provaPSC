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
public class MesReferenciaTarifanegativaException extends MesReferenciaException {
    
    public MesReferenciaTarifanegativaException() {
        super("A tarifa padrão do mês de referência não pode ser negativa");
    }
    
}
