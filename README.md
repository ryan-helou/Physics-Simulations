# Physics Simulations

*A JavaFX desktop app of classical-mechanics simulations, built by a team for a Vanier College programming course.*

The centrepiece is a double pendulum. It integrates the standard coupled equations of motion for the two arm angles, then advances them with semi-implicit Euler — one step per animation frame — and traces the path of the lower bob, which is where the system's chaotic behaviour shows up: two runs from almost-identical starting angles diverge into completely different curves. The trail is drawn onto an off-screen buffer canvas so it persists as the arms keep swinging.

You can drag sliders for the two arm lengths (50–300 px), the two bob masses (1–25), and gravity (1–15), toggle the trail on and off, and start or reset the motion. This is the simulation the Gradle build actually launches (`DoublePendulumMain`), and it's the one part of the project with real physics behind it.

Two other simulations were sketched out but stop short of that:

- Newton's cradle draws five bobs and swings them with JavaFX `PathTransition` animations along arcs, so it looks the part but doesn't model momentum transfer or collisions; the length slider reshapes the arcs and the mass slider only tints the bobs.
- Particle attraction is an early prototype — a single `Mover` that chases the cursor with an acceleration of 0.4 and a capped speed of 5 — behind an FXML control panel whose handlers are still empty.

Tech stack: Java with JavaFX 20 and Gradle, scaffolded on FrostyBee's Vanier course template. The team was Ryan Helou (double pendulum) and Anish Mehra (Newton's cradle), with contributions from classmates on the particle-attraction screen and the image-based main menu.

MIT licensed.
