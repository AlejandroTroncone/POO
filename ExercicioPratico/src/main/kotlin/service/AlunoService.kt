package org.example.service

import org.example.model.Aluno

class AlunoService {
    private val _alunos = mutableListOf<Aluno>()

    fun cadastrar(aluno: Aluno): Boolean {
        if (_alunos.any { it.id == aluno.id }) return false
        _alunos.add(aluno)
        return true
    }

    fun listar(): List<Aluno> = _alunos.toList()

    fun buscarPorId(id: Int): Aluno? = _alunos.find { it.id == id }

    fun existeId(id: Int): Boolean = _alunos.any { it.id == id }
}
