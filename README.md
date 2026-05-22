# Sistema de Alunos e Trilhas

Projeto prático da Semana 02 do treinamento para competidores da WorldSkills, ocupação 08, Desenvolvimento de Aplicativos Móveis.

## Objetivo
Este projeto tem como objetivo desenvolver uma aplicação de console em Kotlin, focada em modelagem orientada a objetos (POO), organização de domínio e separação de responsabilidades. O sistema visa organizar alunos, cursos e trilhas de formação, além de registrar matrículas, progressos e emitir relatórios de acompanhamento.

A arquitetura prepara o raciocínio para futura migração para o desenvolvimento Android moderno, separando a interface (ConsoleApp) das regras de negócio (Serviços) e do modelo de dados (Domínio).

## Como Executar
O projeto pode ser executado diretamente pelo terminal ou importado no IntelliJ IDEA / Android Studio.

Para rodar via terminal:
```bash
# Navegue até o diretório do projeto
cd ExercicioPratico

# Execute a aplicação usando o Gradle Wrapper
./gradlew run
```

## Estrutura do Projeto
```
src/main/kotlin/
├── app/
│   └── ConsoleApp.kt         # Classe principal que cuida do menu, leitura de dados e exibição de relatórios
├── model/
│   ├── Aluno.kt              # Entidade Aluno
│   ├── Curso.kt              # Entidade Curso
│   ├── Trilha.kt             # Entidade Trilha com encapsulamento da lista de cursos
│   ├── Matricula.kt          # Entidade de Associação com regras de progresso
│   ├── CategoriaCurso.kt     # Enum com as categorias de curso permitidas
│   ├── NivelCurso.kt         # Enum com os níveis de curso permitidos
│   ├── SituacaoAluno.kt      # Enum com as situações possíveis para um aluno
│   ├── StatusMatricula.kt    # Enum com o status da matrícula (Ativa, Concluída, Cancelada)
│   └── StatusTrilha.kt       # Enum com o status da trilha (Planejada, Ativa, Concluída, Arquivada)
├── service/
│   ├── AlunoService.kt       # Regras para cadastro e busca de alunos
│   ├── CursoService.kt       # Regras para cadastro e busca de cursos
│   ├── MatriculaService.kt   # Regras para controle de matrículas e progresso
│   ├── RelatorioService.kt   # Regras de negócio relacionadas à exibição de relatórios (ex: Ranking)
│   └── TrilhaService.kt      # Regras para cadastro e busca de trilhas
└── Main.kt                   # Ponto de entrada do programa, muito enxuto
```

## Principais Classes
- **Aluno, Curso, Trilha:** O núcleo do modelo de dados. Utilizam construtores com validações baseadas na função `require()`. O uso do `data class` foi priorizado para Aluno e Curso, mas evitado em Trilha para manter o controle absoluto (`encapsulamento`) sobre a lista mutável e status.
- **Matricula:** Representa o vínculo entre o Aluno e a Trilha, e gerencia o progresso dos cursos.
- **ConsoleApp:** Toda a lógica de IO (interação com o usuário) está aqui, separando completamente a interface textual da lógica de negócio contida nos *Services*.
- **Services:** Classes que armazenam as listas das entidades (atuando como repositórios em memória) e validam regras antes de confirmar inserções.

## Regras Implementadas
- Validações básicas (ID único, nome com no mínimo 3 caracteres, e-mail com '@' e '.', etc).
- Não é permitida a exposição de `MutableList` fora da classe `Trilha`.
- Não é permitida a adição de cursos repetidos na mesma trilha.
- Trilhas com status `Concluída` ou `Arquivada` não recebem novos cursos.
- Apenas alunos com situação `Ativo` podem ser matriculados.
- Apenas trilhas com status `Ativa` aceitam matrícula.
- Cálculo de progresso percentual baseado no total de cursos da trilha.

## Decisões de Modelagem
- **Encapsulamento de coleções:** A lista de cursos da `Trilha` é `private` e mutável por dentro, mas exposta apenas como `List` imutável.
- **Uso de Enums:** Tipos fechados como status e níveis foram forçados a ser Enum Classes para evitar o uso problemático de strings soltas e "Magic Strings".
- **Sem var global:** Nenhum dado do sistema é uma variável global solta. O armazenamento fica inteiramente restrito aos `Services`, que são injetados em tempo de execução no `ConsoleApp`.
- **Nenhum Null-pointer fix (!!):** O uso rigoroso de safe calls `?.` em união com fallback values e `toIntOrNull()` afim de evitar exceções e eventuais quebras repentinas ("crashes") no console.

## Limitações Conhecidas
- O projeto não possui persistência de dados. Por ser executado inteiramente em memória e pelo console, todas as informações cadastradas são perdidas ao fechar o programa.
- Ausência de testes automatizados com JUnit (sendo coberto extensamente por Testes Manuais).

## Próximos Ajustes
- Implementar testes unitários em JUnit focados na camada de serviços (principalmente nos cenários de ranking de `RelatorioService` e limites em `MatriculaService`).
- Integração da base local em persistência SQLite com abstração Repository.
