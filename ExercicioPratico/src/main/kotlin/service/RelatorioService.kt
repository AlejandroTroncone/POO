package org.example.service

import org.example.model.Matricula

class RelatorioService {
    fun rankingProgresso(matriculas: List<Matricula>): List<Matricula> {
        return matriculas.sortedWith(
            compareByDescending<Matricula> { it.percentualConclusao() }
                .thenBy { it.aluno.nome }
        )
    }
}
