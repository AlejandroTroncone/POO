package org.example.model

class Trilha(
    val id: Int,
    val nome: String,
    val descricao: String,
    var status: StatusTrilha = StatusTrilha.PLANEJADA
) {
    private val _cursos = mutableListOf<Curso>()

    init {
        require(id > 0) { "ID deve ser positivo." }
        require(nome.isNotBlank()) { "Nome da trilha é obrigatório." }
    }

    fun cursos(): List<Curso> = _cursos.toList()

    fun quantidadeCursos(): Int = _cursos.size

    fun cargaHorariaTotal(): Int = _cursos.sumOf { it.cargaHoraria }

    fun contemCurso(cursoId: Int): Boolean = _cursos.any { it.id == cursoId }

    fun adicionarCurso(curso: Curso): Boolean {
        if (status == StatusTrilha.CONCLUIDA || status == StatusTrilha.ARQUIVADA) return false
        if (contemCurso(curso.id)) return false
        _cursos.add(curso)
        return true
    }

    fun resumo(): String {
        return "[$id] $nome | ${status.descricao} | ${quantidadeCursos()} curso(s) | ${cargaHorariaTotal()}h"
    }
}
