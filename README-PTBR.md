# Mr. Bet – Sistema de Gerenciamento de Apostas Esportivas
Um sistema em Java 11+ para gerenciar equipes, campeonatos e apostas. Projetado para registrar times, controlar participações em campeonatos e processar operações de aposta com validação e geração de relatórios.

## Funcionalidades
- **Gestão de Times**  
  - Cadastro de times com ID único, nome e mascote  
  - Validação de dados (campos obrigatórios, formato)  
  - Acompanhamento de participação em campeonatos  

- **Gestão de Campeonatos**  
  - Criação de campeonatos com limite de participantes  
  - Inclusão de times, respeitando a capacidade  
  - Busca de campeonatos sem diferenciar maiúsculas/minúsculas  

- **Sistema de Apostas**  
  - Realização de apostas nas colocações de times  
  - Validação de valores (positivos) e posições válidas  
  - Registro de histórico de apostas para relatórios  

- **Relatórios**  
  - Listagem detalhada de todas as apostas  
  - Relatórios históricos:
    - Times com mais participações  
    - Times que nunca participaram  
    - Times mais apostados em 1ª colocação  

- **Interface CLI**  
  - Menu interativo com comandos case-insensitive  
  - Validação de entrada e tratamento de erros  

## Tecnologias
- **Linguagem**: Java 21+  
- **Build**: Maven  
- **Testes**: JUnit 5 (Parameterized Tests)  
- **Coleções**:  
  - `HashMap` para buscas rápidas  
  - `HashSet` para participação única  
  - `ArrayList` para histórico de apostas  

## Estrutura do Projeto
```
src/
├── main/java/mrbet/
│   ├── Aposta.java          # entidade de aposta
│   ├── Campeonato.java      # gestão de campeonato
│   ├── Time.java            # entidade de time
│   ├── MrBetSistema.java    # lógica do sistema
│   └── MainMrBet.java       # interface CLI
│
└── test/java/mrbet/
    ├── ApostaTest.java
    ├── CampeonatoTest.java
    ├── TimeTest.java
    └── MrBetSistemaTest.java
```

## Instalação e Uso
1. **Clonar e Compilar**  
   ```bash
   git clone [URL-do-repositório]
   mvn clean install
   ```

2. **Executar**  
   ```bash
   mvn exec:java -Dexec.mainClass="mrbet.MainMrBet"
   ```

3. **Exemplo de Fluxo**  
   ```
   (M) Adicionar Time
     Código: 250_PB
     Nome: Nacional de Patos
     Mascote: Canário

   (.) Adicionar Campeonato
     Nome: Brasileirão Série A 2023
     Participantes: 20

   (B) Incluir Time no Campeonato
     Código: 250_PB
     Campeonato: Brasileirão Série A 2023

   (T) Fazer Aposta
     Código: 250_PB
     Campeonato: Brasileirão Série A 2023
     Colocação: 1
     Valor: 100.00

   (E) Ver Campeonatos de um Time
     Código: 250_PB

   (H) Relatórios Históricos
   ```

## Testes
Cobertura completa com mais de 150 casos:  
```bash
mvn test
```

- Testes parametrizados (validações)  
- Casos de borda (strings vazias, valores negativos)  
- Verificação de exceções e manipulação de coleções  

## Aprendizados
1. **Coleções**  
   - `HashMap` para procura O(1)  
   - `HashSet` para evitar duplicatas
   - `ArrayList` para preservar ordem de inserção

2. **Validações**  
   - Checagens de null/empty com exceções customizadas  
   - Limite de participantes em campeonatos 
   - Dependências entre entidades (time vs. campeonato)  

3. **Design OO**  
   - Encapsulamento em `Time` e `Campeonato`  
   - Separação de responsabilidades (CLI × lógica)  
   - IDs imutáveis  

4. **Testes**  
   - JUnit parametrizado para variações de entrada  
   - Cobertura de 100% da lógica de validação  
   - Testes de fluxo integrado  

5. **Performance**  
   - Procura case-insensitive via chaves em lowercase  
   - Estrutura de dados eficiente com `Set`  
   - Inicialização sob demanda das coleções  
