package com.amandaramos.entity;

import javax.persistence.*;


@Embeddable
public class DadosFinanceiros {

    private double saldoConta;

    public DadosFinanceiros() {
        }


    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

}

