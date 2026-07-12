# Particlellife (Java + Swing)

**Particlellife** is a particle-based simulation inspired by the idea of emergent behavior from simple rules of interaction.
Built with 💛 **Java + Swing**, it simulates particles that attract and repel each other based on custom force rules.

Particles self-organize into mesmerizing clusters, swarms, and dynamic patterns — all driven by simple forces.

## 🔍 How It Works

* Particles start out in colored groups and have attraction/repulsion settings toward other groups
* The system updates positions using basic physics and force accumulation
* Beyond force, groups can also be bound with rules that blend particle colors, change particle shape based on speed, and destroy particles (predator/prey contact, or old age)
* UI powered by **Java Swing** for real-time rendering

## 🧠 Inspiration

This project is based on the original concept by:

* [**Jeffrey Ventrella**](https://ventrella.com/Clusters/) – *Clusters*
* [**Tom Mohr**](https://particle-life.com/) – *Particle Life*

Massive props to both for the mind-blowing concept of digital particle societies 💥

## 🛠️ Tech Stack

* **Language:** Java 17+
* **UI:** Swing

## 🚀 Run It

Clone the repo, then either run it directly with Maven:

```bash
mvn exec:java
```

or build a jar and run that:

```bash
mvn package
java -jar target/particle-life-1.0.jar
```

You can also just open the project in an IDE (IntelliJ, Eclipse, etc.) and run `Frame.main`.

## 📸 Preview

![Preview](./screenshot/s2.png)
# -- 
![Preview](./screenshot/s1.png)


