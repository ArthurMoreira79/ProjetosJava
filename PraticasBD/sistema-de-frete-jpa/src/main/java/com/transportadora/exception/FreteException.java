package com.transportadora.exception;

public class FreteException extends RuntimeException{

    public FreteException(String mensagem){
        super(mensagem);
    }

    public FreteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
