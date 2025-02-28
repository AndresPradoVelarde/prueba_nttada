package com.prueba.nttdata.cuenta_movimiento.exceptions;

public class SaldoNoDisponibleException extends RuntimeException{
    public SaldoNoDisponibleException(String message) {
        super(message);
    }
}
