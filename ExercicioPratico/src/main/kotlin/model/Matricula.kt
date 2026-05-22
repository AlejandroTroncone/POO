package org.example.model

class Matricula(
    val aluno: Aluno,
    val trilha: Trilha
) {
    var status: StatusMatricula = StatusMatricula.ATIVA
        private set

    var cursosConcluidos: Int = 0
        private set

    fun percentualConclusao(): Int {
        val total = trilha.quantidadeCursos()
        if (total == 0) return 0
        return (cursosConcluidos * 100) / total
    }

    fun registrarProgresso(quantidade: Int): Boolean {
        if (status != StatusMatricula.ATIVA) return false
        val total = trilha.quantidadeCursos()
        if (total == 0) return false
        if (quantidade < 0) return false
        if (quantidade > total) return false
        cursosConcluidos = quantidade
        if (percentualConclusao() >= 100) {
            status = StatusMatricula.CONCLUIDA
        }
        return true
    }

    fun cancelar(): Boolean {
        if (status == StatusMatricula.CONCLUIDA) return false
        status = StatusMatricula.CANCELADA
        return true
    }
}
