# Mr. Bet - Sports Betting Management System
A Java-based system for managing sports teams, championships, and bets. Designed to handle team registrations, championship participation tracking, and betting operations with validation and reporting features.

## Features
- **Team Management**:
  - Register teams with unique IDs, names, and mascots
  - Validate team data (non-null fields, format checks)
  - Track team participation in championships

- **Championship Management**:
  - Create championships with participant limits
  - Add teams to championships (with capacity checks)
  - Case-insensitive championship name handling

- **Betting System**:
  - Place bets on team positions in championships
  - Validate bet parameters (positive values, valid placements)
  - Track bet history and generate reports

- **Reporting**:
  - List all registered bets with details
  - Generate historical reports:
    - Most frequent championship participants
    - Teams never participating in championships
    - Most popular teams in first-place bets

- **CLI Interface**:
  - Interactive menu system
  - Input validation and error handling
  - Case-insensitive commands

## Technologies
- **Core**: Java 21+
- **Build Tool**: Maven
- **Testing**: JUnit 5 (Parameterized tests)
- **Collections**:
  - `HashMap` for fast team/championship lookups
  - `HashSet` for unique participation tracking
  - `ArrayList` for bet history maintenance

## Project Structure
```
src/
├── main/java/mrbet/
│   ├── Aposta.java          # Bet entity
│   ├── Campeonato.java      # Championship management
│   ├── Time.java            # Team entity
│   ├── MrBetSistema.java    # Core system logic
│   └── MainMrBet.java       # CLI interface
│
├── test/java/mrbet/
│   ├── ApostaTest.java
│   ├── CampeonatoTest.java
│   ├── TimeTest.java
│   └── MrBetSistemaTest.java
```

## Installation & Usage

1. **Clone and Build**:
```bash
git clone [repository-url]
mvn clean install
```

2. **Run the Application**:
```bash
mvn exec:java -Dexec.mainClass="mrbet.MainMrBet"
```

3. **Sample Workflow**:
```
(M) Add Team:
Código: 250_PB
Nome: Nacional de Patos
Mascote: Canário

(.) Add Championship:
Campeonato: Brasileirão Série A 2023
Participantes: 20

(B) Add Team to Championship:
Código: 250_PB
Campeonato: Brasileirão Série A 2023

(T) Place Bet:
Código: 250_PB
Campeonato: Brasileirão Série A 2023
Colocação: 1
Valor: 100.00

(E) View Team Championships:
Código: 250_PB

(H) View Historical Reports
```

## Testing

Comprehensive test coverage with 150+ test cases:
```bash
mvn test
```

**Key Test Features**:
- Parameterized validation tests
- Edge case testing (empty strings, negative values)
- Collection manipulation tests
- Exception handling verification

## What I Learned
1. **Collection Management**:
   - Used `HashMap` for O(1) lookups of teams/championships
   - Implemented `HashSet` to prevent duplicate entries
   - Leveraged `ArrayList` for maintaining bet order

2. **Validation Patterns**:
   - Null/empty checks with custom exceptions
   - State validation (e.g., championship capacity checks)
   - Cross-entity validation (team/championship existence)

3. **OO Design Principles**:
   - Encapsulation in Team/Campeonato classes
   - Separation of concerns (CLI vs core logic)
   - Immutable ID handling for entities

4. **Testing Strategies**:
   - Parameterized JUnit tests for input variations
   - Test coverage for 100% of validation logic
   - Integration testing for system workflows

5. **Performance Considerations**:
   - Case-insensitive lookups via lowercase keys
   - Efficient participation tracking with Set operations
   - Lazy initialization of collections
