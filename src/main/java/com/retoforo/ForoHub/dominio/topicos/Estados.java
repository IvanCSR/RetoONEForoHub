package com.retoforo.ForoHub.dominio.topicos;

public enum Estados {
    ABIERTO(true),
    CERRADO(false);

    private final boolean valor;

    Estados(boolean valor) { this.valor = valor; }

    public boolean getValor() { return valor; }
}
