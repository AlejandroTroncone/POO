package org.example.service

import org.example.model.Curso

class CursoService {
    private val _cursos = mutableListOf<Curso>()

    fun cadastrar(curso: Curso): Boolean {
        if (_cursos.any { it.id == curso.id }) return false
        _cursos.add(curso)
        return true
    }

    fun listar(): List<Curso> = _cursos.toList()

    fun buscarPorId(id: Int): Curso? = _cursos.find { it.id == id }

    fun existeId(id: Int): Boolean = _cursos.any { it.id == id }
}
