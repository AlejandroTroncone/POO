package org.example.model

data class Aluno(
    val id: Int,
    val nome: String,
    val email: String,
    val situacao: SituacaoAluno = SituacaoAluno.ATIVO
) {
    init {
        require(id > 0) { "ID deve ser positivo." }
        require(nome.trim().length >= 3) { "Nome deve possuir pelo menos 3 caracteres." }
        require(email.contains("@") && email.contains(".")) { "E-mail inválido." }
    }

    fun resumo(): String {
        return "[$id] $nome | $email | ${situacao.descricao}"
    }
}
