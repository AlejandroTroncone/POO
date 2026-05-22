# Checklist de Testes

| Teste | Entrada/Ação | Resultado Esperado | Status |
|-------|--------------|-------------------|--------|
| **1. Testes de Inicialização e Menu** |
| Inicialização | `./gradlew run` | Menu exibido sem erros com título correto | ✅ Passou |
| Opção inválida | Digitar '99' no menu | Mensagem de "Opção inválida" | ✅ Passou |
| Sair | Digitar '0' no menu | Encerra aplicação | ✅ Passou |
| **2. Testes de Aluno** |
| Cadastrar aluno válido | ID=1, Nome="João", Email="j@j.com", Ativo | Aluno cadastrado com sucesso | ✅ Passou |
| Impedir ID duplicado | Inserir mesmo ID 1 | Erro de duplicidade | ✅ Passou |
| Validar Nome < 3 | Nome="Ze" | Bloqueio no cadastro | ✅ Passou |
| Validar Email | "joao.com" (sem @) | Bloqueio no cadastro | ✅ Passou |
| Listar Vazio | Listar antes de cadastrar | Exibir "Nenhum aluno cadastrado" | ✅ Passou |
| **3. Testes de Curso** |
| Cadastrar válido | ID=1, Titulo="POO", CH=40, Básico, Kotlin | Curso cadastrado com sucesso | ✅ Passou |
| Impedir CH zero/negativa| CH=0 | Bloqueio de carga horária inválida | ✅ Passou |
| **4. Testes de Trilha** |
| Cadastrar válida | ID=1, Nome="Dev", Planejada | Trilha cadastrada com sucesso | ✅ Passou |
| Exibição status | Listar trilhas | Exibir quantidade e carga horária totais = 0 | ✅ Passou |
| **5. Testes de Associação (Curso -> Trilha)** |
| Associação bem sucedida| Trilha 1, Curso 1 | Curso adicionado com sucesso | ✅ Passou |
| Bloqueio Trilha inativa | Associar curso a Trilha Arquivada | Bloqueio por regra de status | ✅ Passou |
| **6. Testes de Matrícula** |
| Matrícula bem sucedida | Aluno 1 (ativo), Trilha 1 (ativa) | Sucesso | ✅ Passou |
| Bloqueio por Situação | Aluno Bloqueado | Bloqueio de matrícula | ✅ Passou |
| Matrícula Duplicada | Mesma dupla de ID | Bloqueio de duplicidade | ✅ Passou |
| **7. Testes de Progresso** |
| Registrar progresso | Aluno 1, Trilha 1, Cursos: 1 | Calculo da porcentagem correto | ✅ Passou |
| Limite de Cursos | Informar quantidade maior que trilha possui | Bloqueio | ✅ Passou |
| **8. Testes de Relatórios** |
| Exibir Ranking | Menu relatórios -> 5 | Ranking ordenado descrescentemente | ✅ Passou |
| Sem matricula | Menu relatórios -> 6 | Exibe corretamente os não matriculados | ✅ Passou |
| **9. Testes de Estabilidade** |
| Null check em ID | Digitar String onde pede ID numérico | Avisa do erro de conversão num. | ✅ Passou |
