# Evidências de Execução

Ao executar `./gradlew run`, o sistema inicializa sem quebras. Abaixo estão os logs representativos da estabilidade e funcionalidade.

## Exibição do Menu
```
========================================
     Sistema de Alunos e Trilhas
========================================

========== Menu Principal ==========
 1 - Cadastrar aluno
 2 - Listar alunos
 3 - Cadastrar curso
...
10 - Exibir relatórios
 0 - Sair
====================================
```

## Cadastros com Validação Funcional
```
--- Cadastrar Curso ---
ID do curso: 1
Título do curso: Kotlin para Iniciantes
Carga horária (horas): 20
Nível do curso:
  1 - Básico
  2 - Intermediário
  3 - Avançado
Escolha o nível: 1
Categoria do curso:
  1 - Kotlin
  ...
Escolha a categoria: 1
Curso cadastrado com sucesso.
```

## Associação e Encapsulamento Garantidos
```
--- Adicionar Curso a uma Trilha ---
ID da trilha: 1
ID do curso: 1
Curso adicionado à trilha com sucesso.

--- Adicionar Curso a uma Trilha ---
ID da trilha: 1
ID do curso: 1
Curso já associado a esta trilha.
```

## Registro de Progresso e Percentual
```
--- Registrar Progresso ---
ID do aluno: 1
ID da trilha: 1
Total de cursos na trilha: 2
Cursos concluídos atualmente: 0
Quantidade de cursos concluídos: 1
Progresso registrado com sucesso.
Percentual de conclusão: 50%
```

## Relatórios e Ranking Consistentes
```
--- Relatório: Ranking de Progresso ---
1. Joao Silva | Trilha: Kotlin Starter | 50% | Ativa
```
