package org.example.model

data class Curso(
    val id: Int,
    val titulo: String,
    val cargaHoraria: Int,
    val nivel: NivelCurso,
    val categoria: CategoriaCurso
) {
    init {
        require(id > 0) { "ID deve ser positivo." }
        require(titulo.isNotBlank()) { "Título do curso é obrigatório." }
        require(cargaHoraria > 0) { "Carga horária deve ser maior que zero." }
    }

    fun resumo(): String {
        return "[$id] $titulo | ${cargaHoraria}h | ${nivel.descricao} | ${categoria.descricao}"
    }
}
