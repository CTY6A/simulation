# Simulation Project

A console-based step-by-step simulation of a 2D world inhabited by herbivores and predators. This project implements a grid-based ecosystem where creatures interact with each other and the environment, demonstrating core object-oriented programming principles and pathfinding algorithms.

## Features

- **2D Grid World**: A customizable NxM world where each cell can contain a single entity or be empty.
- **Living Creatures**: Two types of creatures with distinct behaviors:
  - **Herbivores**: Search for and consume grass to survive.
  - **Predators**: Hunt herbivores, with combat based on attack power and health points.
- **Static Objects**: The world includes static objects like rocks and trees (obstacles), and grass (resource for herbivores).
- **Turn-Based Simulation**: The simulation progresses step-by-step. Each turn, all creatures have a chance to perform an action (move, eat, or attack).
- **Pathfinding**: Creatures use the A* search algorithm to find the shortest path to their targets (grass for herbivores, herbivores for predators).
- **Configurable World**: Adjust world size, creature stats (speed, HP, attack), and initial spawn rates to create interesting ecosystem dynamics.

## Quick Start

### Requirements

- Java 11 or higher
- Maven 3.x

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/simulation-1.0-SNAPSHOT.jar
```

Or via Maven:

```bash
mvn exec:java -Dexec.mainClass="com.stubedavd.Main"
```

### Controls

- **Enter** — pause/resume simulation

## Simulation Parameters

Parameters in `src/main/java/com/stubedavd/Simulation.java`:

| Parameter | Value | Description |
|-----------|-------|-------------|
| `WORLD_MAP_WIDTH` | 20    | Map width |
| `WORLD_MAP_HEIGHT` | 10    | Map height |
| `THREAD_SLEEP` | 300   | Delay between turns (ms) |
| `ROCK_SPAWN_RATE` | 10%   | Rock spawn probability |
| `TREE_SPAWN_RATE` | 10%   | Tree spawn probability |
| `GRASS_SPAWN_RATE` | 10%   | Grass spawn probability |
| `HERBIVORE_SPAWN_RATE` | 10%   | Herbivore spawn probability |
| `PREDATOR_SPAWN_RATE` | 5%    | Predator spawn probability |
| `EXTINCTION_LIMIT` | 50%   | Limit to prevent extinction |

## Project Structure

```
simulation/
├── src/main/java/com/stubedavd/
│   ├── Main.java              # Application entry point
│   ├── Simulation.java        # Main simulation engine
│   ├── WorldMap.java          # Map and entity management
│   ├── Renderer.java          # Console map rendering
│   ├── Position.java          # Coordinate representation
│   ├── actions/               # World actions
│   │   ├── Action.java        # Base action class
│   │   ├── CreaturesMove.java # Creature movement
│   │   ├── EntityFactory.java # Entity factory
│   │   └── HealthPointsChecker.java # Health check
│   ├── elements/              # World entities
│   │   ├── Entity.java        # Base entity class
│   │   ├── Grass.java         # Grass (resource)
│   │   ├── Rock.java          # Rock (obstacle)
│   │   ├── Tree.java          # Tree (obstacle)
│   │   ├── creatures/
│   │   │   ├── Creature.java  # Base creature class
│   │   │   ├── Herbivore.java # Herbivore
│   │   │   └── Predator.java  # Predator
│   │   └── types/             # Entity types
│   └── pathfinding/           # Pathfinding algorithms
│       ├── AStar.java         # A* algorithm
│       └── Node.java          # Node for A*
├── pom.xml                    # Maven configuration
└── README.md                  # This file
```

## Class Overview

- **`Entity`**: Abstract base class for everything on the map (creatures, grass, rocks, trees).
- **`Creature`**: Abstract subclass of `Entity`. Adds properties like `speed` and `hp`, and the abstract method `makeMove()`.
- **`Herbivore`**: A `Creature` that targets `Grass`. Its `makeMove` logic involves finding grass, moving towards it, or consuming it.
- **`Predator`**: A `Creature` that targets `Herbivore`. Has an additional `attack` property. Its `makeMove` handles moving towards or attacking a herbivore.
- **`WorldMap`**: Core data structure holding the relationship between coordinates and entities. Provides methods for adding, removing, and getting entities.
- **`Simulation`**: Central class. Holds `WorldMap`, renderer, and lists of `Action` objects (`initActions` for setup, `turnActions` for each step). Its `nextTurn()` method orchestrates a single simulation step.
- **`AStar`**: Utility class responsible for calculating the shortest path from one coordinate to another, given the map's obstacles.

## Pathfinding

The project implements the A* pathfinding algorithm from scratch to allow creatures to navigate the world. The logic is encapsulated in a dedicated `AStar` class, separate from the creature logic itself, to ensure clean code and reusability for both herbivores and predators.

## Sample Output

```
--- Turn 1 ---
⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️
⬛️🐄🟫🟫🦙🌱🟫🎍🟫⛰️🟫🌿🗻🟫🟫🎍🌿🟫🌳🦅🌱⬛️
⬛️🌳🟫🌵🐿️🟫🟫🟫🎄🥀🟫⛰️🌳🗻🐑🟫🍀🥀🌲🐼⛰️⬛️
⬛️🌳🟫☘️🟫🟫🟫🟫🎍🟫🌾🟫🎄🟫🗻🟫🟫🗻🟫🟫🟫⬛️
⬛️⛰️🦉🦙🟫🟫🟫🟫🌴🟫⛰️🟫🟫🟫🟫🟫🟫🟫🎍🦖🟫⬛️
⬛️☘️🟫🦙🟫🌴🟫🦒☘️🟫🟫🥀🦙🟫🟫🟫🟫🦙🟫🟫🟫⬛️
⬛️🗻🌳🗻🟫🥀🟫🟫☘️🟫🌿🦊🟫🟫🟫🌳🗻🟫🐑⛰️🦒⬛️
⬛️🟫🟫🦒🟫🐺🟫🟫🐿️⛰️⛰️🟫🗻🟫🟫⛰️🟫🌴🟫🐨⛰️⬛️
⬛️🟫🟫🟫🌵🦁🟫🐇🟫🐇🟫🐑🌿🟫🟫🌿🌳🟫🟫🗻🟫⬛️
⬛️🥀🟫🟫🟫🟫🟫🐄🗻🟫🟫🦁🐑🟫🟫🐹🟫🟫🟫🟫🐅⬛️
⬛️🟫🌿🟫🟫🟫🟫🟫🟫🦒🟫🟫🟫🟫🟫🟫🟫🟫🌲🟫🌿⬛️
⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️
  Grass: 20  Herbivores: 20  Predators: 10  Enter - pause/resume
```

## Project Requirements

This project was created as part of the Java Backend Learning Course. The original requirements and project description can be found here: [Simulation - Java Backend Course](https://zhukovsd.github.io/java-backend-learning-course/projects/simulation/)
