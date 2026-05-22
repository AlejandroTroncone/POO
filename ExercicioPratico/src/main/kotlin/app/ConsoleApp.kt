package org.example.app

import org.example.model.*
import org.example.service.*

class ConsoleApp {
    private val alunoService = AlunoService()
    private val cursoService = CursoService()
    private val trilhaService = TrilhaService()
    private val matriculaService = MatriculaService()
    private val relatorioService = RelatorioService()

    fun executar() {
        println("========================================")
        println("     Sistema de Alunos e Trilhas")
        println("========================================")

        var executando = true
        while (executando) {
            exibirMenuPrincipal()
            when (lerOpcao()) {
                1 -> cadastrarAluno()
                2 -> listarAlunos()
                3 -> cadastrarCurso()
                4 -> listarCursos()
                5 -> cadastrarTrilha()
                6 -> listarTrilhas()
                7 -> adicionarCursoATrilha()
                8 -> matricularAluno()
                9 -> registrarProgresso()
                10 -> exibirRelatorios()
                0 -> {
                    println("Sistema encerrado.")
                    executando = false
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

    private fun exibirMenuPrincipal() {
        println()
        println("========== Menu Principal ==========")
        println(" 1 - Cadastrar aluno")
        println(" 2 - Listar alunos")
        println(" 3 - Cadastrar curso")
        println(" 4 - Listar cursos")
        println(" 5 - Cadastrar trilha")
        println(" 6 - Listar trilhas")
        println(" 7 - Adicionar curso a uma trilha")
        println(" 8 - Matricular aluno em trilha")
        println(" 9 - Registrar progresso do aluno")
        println("10 - Exibir relatórios")
        println(" 0 - Sair")
        println("====================================")
    }

    private fun lerOpcao(): Int? {
        print("Opção: ")
        return readlnOrNull()?.trim()?.toIntOrNull()
    }

    private fun lerTexto(mensagem: String): String {
        print(mensagem)
        return readlnOrNull()?.trim() ?: ""
    }

    private fun lerInteiro(mensagem: String): Int? {
        print(mensagem)
        return readlnOrNull()?.trim()?.toIntOrNull()
    }

    // ========== ALUNO ==========

    private fun cadastrarAluno() {
        println("\n--- Cadastrar Aluno ---")

        val id = lerInteiro("ID do aluno: ")
        if (id == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }
        if (alunoService.existeId(id)) {
            println("Erro: já existe um aluno com o ID $id.")
            return
        }

        val nome = lerTexto("Nome completo: ")
        if (nome.length < 3) {
            println("Erro: o nome deve possuir pelo menos 3 caracteres.")
            return
        }

        val email = lerTexto("E-mail: ")
        if (!email.contains("@") || !email.contains(".")) {
            println("Erro: e-mail inválido. Deve conter @ e .")
            return
        }

        println("Situação do aluno:")
        SituacaoAluno.entries.forEachIndexed { index, situacao ->
            println("  ${index + 1} - ${situacao.descricao}")
        }
        val opcaoSituacao = lerInteiro("Escolha a situação: ")
        val situacao = when (opcaoSituacao) {
            1 -> SituacaoAluno.ATIVO
            2 -> SituacaoAluno.INATIVO
            3 -> SituacaoAluno.BLOQUEADO
            else -> {
                println("Situação inválida.")
                return
            }
        }

        try {
            val aluno = Aluno(id, nome, email, situacao)
            if (alunoService.cadastrar(aluno)) {
                println("Aluno cadastrado com sucesso.")
            } else {
                println("Erro: já existe um aluno com o ID $id.")
            }
        } catch (e: IllegalArgumentException) {
            println("Erro: ${e.message}")
        }
    }

    private fun listarAlunos() {
        println("\n--- Lista de Alunos ---")
        val alunos = alunoService.listar()
        if (alunos.isEmpty()) {
            println("Nenhum aluno cadastrado.")
        } else {
            alunos.forEach { println(it.resumo()) }
        }
    }

    // ========== CURSO ==========

    private fun cadastrarCurso() {
        println("\n--- Cadastrar Curso ---")

        val id = lerInteiro("ID do curso: ")
        if (id == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }
        if (cursoService.existeId(id)) {
            println("Erro: já existe um curso com o ID $id.")
            return
        }

        val titulo = lerTexto("Título do curso: ")
        if (titulo.isBlank()) {
            println("Erro: o título do curso é obrigatório.")
            return
        }

        val cargaHoraria = lerInteiro("Carga horária (horas): ")
        if (cargaHoraria == null || cargaHoraria <= 0) {
            println("Erro: carga horária deve ser maior que zero.")
            return
        }

        println("Nível do curso:")
        NivelCurso.entries.forEachIndexed { index, nivel ->
            println("  ${index + 1} - ${nivel.descricao}")
        }
        val opcaoNivel = lerInteiro("Escolha o nível: ")
        val nivel = when (opcaoNivel) {
            1 -> NivelCurso.BASICO
            2 -> NivelCurso.INTERMEDIARIO
            3 -> NivelCurso.AVANCADO
            else -> {
                println("Nível inválido.")
                return
            }
        }

        println("Categoria do curso:")
        CategoriaCurso.entries.forEachIndexed { index, categoria ->
            println("  ${index + 1} - ${categoria.descricao}")
        }
        val opcaoCategoria = lerInteiro("Escolha a categoria: ")
        val categoria = when (opcaoCategoria) {
            1 -> CategoriaCurso.KOTLIN
            2 -> CategoriaCurso.ANDROID
            3 -> CategoriaCurso.ARQUITETURA
            4 -> CategoriaCurso.TESTES
            5 -> CategoriaCurso.DESIGN
            else -> {
                println("Categoria inválida.")
                return
            }
        }

        try {
            val curso = Curso(id, titulo, cargaHoraria, nivel, categoria)
            if (cursoService.cadastrar(curso)) {
                println("Curso cadastrado com sucesso.")
            } else {
                println("Erro: já existe um curso com o ID $id.")
            }
        } catch (e: IllegalArgumentException) {
            println("Erro: ${e.message}")
        }
    }

    private fun listarCursos() {
        println("\n--- Lista de Cursos ---")
        val cursos = cursoService.listar()
        if (cursos.isEmpty()) {
            println("Nenhum curso cadastrado.")
        } else {
            cursos.forEach { println(it.resumo()) }
        }
    }

    // ========== TRILHA ==========

    private fun cadastrarTrilha() {
        println("\n--- Cadastrar Trilha ---")

        val id = lerInteiro("ID da trilha: ")
        if (id == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }
        if (trilhaService.existeId(id)) {
            println("Erro: já existe uma trilha com o ID $id.")
            return
        }

        val nome = lerTexto("Nome da trilha: ")
        if (nome.isBlank()) {
            println("Erro: o nome da trilha é obrigatório.")
            return
        }

        val descricao = lerTexto("Descrição curta: ")

        println("Status da trilha:")
        StatusTrilha.entries.forEachIndexed { index, status ->
            println("  ${index + 1} - ${status.descricao}")
        }
        val opcaoStatus = lerInteiro("Escolha o status: ")
        val status = when (opcaoStatus) {
            1 -> StatusTrilha.PLANEJADA
            2 -> StatusTrilha.ATIVA
            3 -> StatusTrilha.CONCLUIDA
            4 -> StatusTrilha.ARQUIVADA
            else -> {
                println("Status inválido.")
                return
            }
        }

        try {
            val trilha = Trilha(id, nome, descricao, status)
            if (trilhaService.cadastrar(trilha)) {
                println("Trilha cadastrada com sucesso.")
            } else {
                println("Erro: já existe uma trilha com o ID $id.")
            }
        } catch (e: IllegalArgumentException) {
            println("Erro: ${e.message}")
        }
    }

    private fun listarTrilhas() {
        println("\n--- Lista de Trilhas ---")
        val trilhas = trilhaService.listar()
        if (trilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
        } else {
            trilhas.forEach { println(it.resumo()) }
        }
    }

    // ========== ADICIONAR CURSO À TRILHA ==========

    private fun adicionarCursoATrilha() {
        println("\n--- Adicionar Curso a uma Trilha ---")

        val trilhaId = lerInteiro("ID da trilha: ")
        if (trilhaId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val trilha = trilhaService.buscarPorId(trilhaId)
        if (trilha == null) {
            println("Trilha não encontrada.")
            return
        }

        if (trilha.status == StatusTrilha.CONCLUIDA || trilha.status == StatusTrilha.ARQUIVADA) {
            println("Erro: trilhas com status ${trilha.status.descricao} não podem receber novos cursos.")
            return
        }

        val cursoId = lerInteiro("ID do curso: ")
        if (cursoId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val curso = cursoService.buscarPorId(cursoId)
        if (curso == null) {
            println("Curso não encontrado.")
            return
        }

        if (trilha.contemCurso(cursoId)) {
            println("Curso já associado a esta trilha.")
            return
        }

        if (trilha.adicionarCurso(curso)) {
            println("Curso adicionado à trilha com sucesso.")
        } else {
            println("Erro ao adicionar curso à trilha.")
        }
    }

    // ========== MATRÍCULA ==========

    private fun matricularAluno() {
        println("\n--- Matricular Aluno em Trilha ---")

        val alunoId = lerInteiro("ID do aluno: ")
        if (alunoId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val aluno = alunoService.buscarPorId(alunoId)
        if (aluno == null) {
            println("Aluno não encontrado.")
            return
        }

        val trilhaId = lerInteiro("ID da trilha: ")
        if (trilhaId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val trilha = trilhaService.buscarPorId(trilhaId)
        if (trilha == null) {
            println("Trilha não encontrada.")
            return
        }

        val resultado = matriculaService.matricular(aluno, trilha)
        println(resultado)
    }

    // ========== PROGRESSO ==========

    private fun registrarProgresso() {
        println("\n--- Registrar Progresso ---")

        val alunoId = lerInteiro("ID do aluno: ")
        if (alunoId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val aluno = alunoService.buscarPorId(alunoId)
        if (aluno == null) {
            println("Aluno não encontrado.")
            return
        }

        val trilhaId = lerInteiro("ID da trilha: ")
        if (trilhaId == null) {
            println("ID inválido. Informe um número inteiro.")
            return
        }

        val trilha = trilhaService.buscarPorId(trilhaId)
        if (trilha == null) {
            println("Trilha não encontrada.")
            return
        }

        val matricula = matriculaService.buscarMatricula(alunoId, trilhaId)
        if (matricula == null) {
            println("Matrícula não encontrada para este aluno nesta trilha.")
            return
        }

        if (matricula.status != StatusMatricula.ATIVA) {
            println("Erro: a matrícula não está ativa. Status atual: ${matricula.status.descricao}.")
            return
        }

        val totalCursos = trilha.quantidadeCursos()
        if (totalCursos == 0) {
            println("A trilha não possui cursos cadastrados.")
            return
        }

        println("Total de cursos na trilha: $totalCursos")
        println("Cursos concluídos atualmente: ${matricula.cursosConcluidos}")
        val cursosConcluidos = lerInteiro("Quantidade de cursos concluídos: ")
        if (cursosConcluidos == null) {
            println("Valor inválido. Informe um número inteiro.")
            return
        }

        if (cursosConcluidos < 0) {
            println("Erro: a quantidade de cursos concluídos não pode ser negativa.")
            return
        }

        if (cursosConcluidos > totalCursos) {
            println("Erro: a quantidade de cursos concluídos não pode ser maior que o total de cursos da trilha ($totalCursos).")
            return
        }

        if (matricula.registrarProgresso(cursosConcluidos)) {
            println("Progresso registrado com sucesso.")
            println("Percentual de conclusão: ${matricula.percentualConclusao()}%")
            if (matricula.status == StatusMatricula.CONCLUIDA) {
                println("Parabéns! A trilha foi concluída e a matrícula foi marcada como concluída.")
            }
        } else {
            println("Erro ao registrar progresso.")
        }
    }

    // ========== RELATÓRIOS ==========

    private fun exibirRelatorios() {
        var voltar = false
        while (!voltar) {
            println()
            println("========== Relatórios ==========")
            println("1 - Alunos cadastrados")
            println("2 - Cursos cadastrados")
            println("3 - Trilhas com carga horária total")
            println("4 - Alunos matriculados por trilha")
            println("5 - Ranking de progresso")
            println("6 - Alunos sem matrícula")
            println("0 - Voltar ao menu principal")
            println("================================")

            when (lerOpcao()) {
                1 -> relatorioAlunos()
                2 -> relatorioCursos()
                3 -> relatorioTrilhas()
                4 -> relatorioAlunosPorTrilha()
                5 -> rankingProgresso()
                6 -> relatorioAlunosSemMatricula()
                0 -> voltar = true
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

    private fun relatorioAlunos() {
        println("\n--- Relatório: Alunos Cadastrados ---")
        val alunos = alunoService.listar()
        if (alunos.isEmpty()) {
            println("Nenhum aluno cadastrado.")
        } else {
            alunos.forEach { println(it.resumo()) }
            println("Total: ${alunos.size} aluno(s)")
        }
    }

    private fun relatorioCursos() {
        println("\n--- Relatório: Cursos Cadastrados ---")
        val cursos = cursoService.listar()
        if (cursos.isEmpty()) {
            println("Nenhum curso cadastrado.")
        } else {
            cursos.forEach { println(it.resumo()) }
            println("Total: ${cursos.size} curso(s)")
        }
    }

    private fun relatorioTrilhas() {
        println("\n--- Relatório: Trilhas com Carga Horária Total ---")
        val trilhas = trilhaService.listar()
        if (trilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
        } else {
            trilhas.forEach { println(it.resumo()) }
        }
    }

    private fun relatorioAlunosPorTrilha() {
        println("\n--- Relatório: Alunos Matriculados por Trilha ---")
        val trilhas = trilhaService.listar()
        if (trilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
            return
        }
        trilhas.forEach { trilha ->
            println("\nTrilha: [${trilha.id}] ${trilha.nome} (${trilha.status.descricao})")
            val matriculas = matriculaService.listarPorTrilha(trilha.id)
            if (matriculas.isEmpty()) {
                println("  Nenhum aluno matriculado nesta trilha.")
            } else {
                matriculas.forEach { matricula ->
                    println("  - ${matricula.aluno.nome} | ${matricula.status.descricao} | ${matricula.percentualConclusao()}% concluído")
                }
            }
        }
    }

    private fun rankingProgresso() {
        println("\n--- Relatório: Ranking de Progresso ---")
        val matriculas = matriculaService.listarTodas()
        if (matriculas.isEmpty()) {
            println("Nenhuma matrícula registrada.")
            return
        }
        val ranking = relatorioService.rankingProgresso(matriculas)
        ranking.forEachIndexed { index, matricula ->
            println("${index + 1}. ${matricula.aluno.nome} | Trilha: ${matricula.trilha.nome} | ${matricula.percentualConclusao()}% | ${matricula.status.descricao}")
        }
    }

    private fun relatorioAlunosSemMatricula() {
        println("\n--- Relatório: Alunos sem Matrícula ---")
        val todosAlunos = alunoService.listar()
        if (todosAlunos.isEmpty()) {
            println("Nenhum aluno cadastrado.")
            return
        }
        val idsComMatricula = matriculaService.alunosComMatricula()
        val semMatricula = todosAlunos.filter { it.id !in idsComMatricula }
        if (semMatricula.isEmpty()) {
            println("Todos os alunos possuem matrícula.")
        } else {
            semMatricula.forEach { println(it.resumo()) }
            println("Total: ${semMatricula.size} aluno(s) sem matrícula")
        }
    }
}
