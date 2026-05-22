package org.example.service

import org.example.model.Trilha

class TrilhaService {
    private val _trilhas = mutableListOf<Trilha>()

    fun cadastrar(trilha: Trilha): Boolean {
        if (_trilhas.any { it.id == trilha.id }) return false
        _trilhas.add(trilha)
        return true
    }

    fun listar(): List<Trilha> = _trilhas.toList()

    fun buscarPorId(id: Int): Trilha? = _trilhas.find { it.id == id }

    fun existeId(id: Int): Boolean = _trilhas.any { it.id == id }
}
