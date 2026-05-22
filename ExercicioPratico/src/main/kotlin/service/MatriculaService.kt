package org.example.service

import org.example.model.Matricula
import org.example.model.Aluno
import org.example.model.Trilha
import org.example.model.SituacaoAluno
import org.example.model.StatusTrilha
import org.example.model.StatusMatricula

class MatriculaService {
    private val _matriculas = mutableListOf<Matricula>()

    fun matricular(aluno: Aluno, trilha: Trilha): String {
        if (aluno.situacao != SituacaoAluno.ATIVO) {
            return "Aluno não está ativo. Apenas alunos ativos podem ser matriculados."
        }
        if (trilha.status != StatusTrilha.ATIVA) {
            return "Trilha não está ativa. Apenas trilhas ativas aceitam matrícula."
        }
        if (_matriculas.any { it.aluno.id == aluno.id && it.trilha.id == trilha.id }) {
            return "Aluno já matriculado nesta trilha."
        }
        _matriculas.add(Matricula(aluno, trilha))
        return "Matrícula realizada com sucesso."
    }

    fun buscarMatricula(alunoId: Int, trilhaId: Int): Matricula? {
        return _matriculas.find { it.aluno.id == alunoId && it.trilha.id == trilhaId }
    }

    fun listarPorTrilha(trilhaId: Int): List<Matricula> {
        return _matriculas.filter { it.trilha.id == trilhaId }
    }

    fun listarTodas(): List<Matricula> = _matriculas.toList()

    fun alunosComMatricula(): Set<Int> {
        return _matriculas.map { it.aluno.id }.toSet()
    }
}
