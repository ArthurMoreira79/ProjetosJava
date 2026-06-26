package com.transportadora.model;

public enum StatusFrete {
    PENDENTE, EM_TRANSPORTE, ENTREGUE, CANCELADO;

    public boolean podeMudarPara(StatusFrete novoStatus) {
        if (this == CANCELADO || this == ENTREGUE) {
            return false;
        }

        if (novoStatus == CANCELADO) {
            return true;
        }

        return novoStatus.ordinal() > this.ordinal();
    }

}