# Simulation Project

A console-based step-by-step simulation of a 2D world inhabited by herbivores and predators. This project implements a grid-based ecosystem where creatures interact with each other and the environment, demonstrating core object-oriented programming principles and pathfinding algorithms.

## Features

*   **2D Grid World**: A customizable NxM matrix world where each cell can contain a single entity or be empty.
*   **Living Creatures**: Two types of creatures with distinct behaviors:
    *   **Herbivores**: Search for and consume grass to survive.
    *   **Predators**: Hunt herbivores, with combat based on attack power and health points.
*   **Static Objects**: The world includes non-interactive objects like rocks and trees that block movement, and grass which serves as a resource for herbivores.
*   **Turn-Based Simulation**: The simulation progresses step-by-step. Each turn, all creatures have a chance to perform an action (move, eat, or attack).
*   **Pathfinding**: Creatures use a search algorithm (A*) to find the shortest path to their targets (grass for herbivores, herbivores for predators).
*   **Configurable World**: Adjust world size, creature stats (speed, HP, attack), and initial spawn rates to create interesting ecosystem dynamics.

## Project Structure
```
simulation/
├── src/
│ └── main/
│ └── java/com/stubedavd/
│ ├── entities/ # All world entities
│ │ ├── Entity.java # Abstract base class for all objects
│ │ ├── Creature.java # Abstract class for living beings
│ │ ├── Herbivore.java # Herbivore creature logic
│ │ ├── Predator.java # Predator creature logic
│ │ ├── Grass.java # Resource for herbivores
│ │ ├── Rock.java # Static obstacle
│ │ └── Tree.java # Static obstacle
│ ├── model/
│ │ └── Map.java # Manages the grid and entity positions
│ ├── actions/ # Actions performed on the world
│ │ ├── Action.java # Abstract action class
│ │ └── ... # Specific actions (MoveCreaturesAction, SpawnGrassAction, etc.)
│ ├── simulation/
│ │ └── Simulation.java # Main simulation engine, turn management, renderer
│ ├── pathfinding/
│ │ └── AStar.java # Handles pathfinding algorithm
│ └── Main.java # Application entry point
├── pom.xml # Maven configuration
└── README.md # This file
```

## Class Overview

*   **`Entity`**: The root abstract class for everything that exists on the map (creatures, grass, rocks, trees).
*   **`Creature`**: An abstract subclass of `Entity`. Adds properties like `speed` and `hp`, and the abstract method `makeMove()`.
*   **`Herbivore`**: A `Creature` that targets `Grass`. Its `makeMove` logic involves finding grass, moving towards it, or consuming it.
*   **`Predator`**: A `Creature` that targets `Herbivore`. It has an additional `attack` property. Its `makeMove` handles moving towards or attacking a herbivore.
*   **`Map`**: The core data structure holding the relationship between `Coordinates` and `Entity` objects. It provides methods for adding, removing, and getting entities without exposing its internal collection directly.
*   **`Simulation`**: The central class. It holds the `Map`, a renderer, and lists of `Action` objects (`initActions` for setup, `turnActions` for each step). Its `nextTurn()` method orchestrates a single simulation step.
*   **`AStar`**: A separate utility class responsible for calculating the shortest path from one coordinate to another, given the map's obstacles.

## Pathfinding

The project implements a pathfinding algorithm (such as A*) from scratch to allow creatures to navigate the world. The logic is encapsulated in a dedicated `AStar` class, separate from the creature logic itself, to ensure clean code and reusability for both herbivores and predators.

## Project Requirements

This project was created as part of the Java Backend Learning Course. The original requirements and project description can be found here: [Симуляция - Курс Java Backend](https://zhukovsd.github.io/java-backend-learning-course/projects/simulation/)
