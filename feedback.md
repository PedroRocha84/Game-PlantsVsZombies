# Feedback

## Main Class - Branch name - feedbackMainClass - done!
- **Clean the `main` method signature**: The `throws` statements is not used, so removing them will keep the code tidy.
- **Handle specific exceptions**: Instead of catching general exceptions, focus on handling specific ones. This makes your code more reliable and avoids catching unintended runtime exceptions.

## Zombie - Branch name - feedbackZombieClass
- **Remove unused properties**: Keep the class focused and efficient. - DONE!
- **Relocate the `addNewZombie()` method**: Move it to the `ZombieFactory` class, as it better fits the responsibility of managing zombie creation.

## ZombieBuilder
- **Organize the class structure**: Use a consistent template, such as:
    - Static properties
    - Instance properties
    - Static methods
    - Instance methods
    - Getters
    - Setters
      This consistency will make your code easier to navigate and maintain.

## Game
- **Follow naming conventions**: Name static properties in uppercase, using underscores for multi-word names.
- **Add access modifiers**: Ensure all properties have appropriate access modifiers.
- **Remove unused properties**: Keep the class streamlined.
- I see you've started playing with **threads and thread pools** on the `createAndShootPeas()` method, but do you know what is it doing exactly?
- **Use block comments**: For entire methods, block comments are clearer than single-line comments.
- **Clean up method signatures**: Remove unused `throws` statements.
- **Refactor large class**: Break the class into smaller ones to encapsulate common behaviors and improve maintainability.

## GameStats
- **Skip the empty constructor**: Java provides a default constructor automatically, so there's no need to write one.

## Menu
- **Remove unused properties**: Keep your code clean and efficient.
- **Clean up method signatures**: Avoid unnecessary `throws` statements.

## MenuButton and NewMenu
- **Avoid declaring multiple variables on one line**: While possible, it reduces code readability and is considered a bad practice.

## SoundPlayer
- **Check for unused properties**: Remove them for better organization.
- **Add access modifiers**: Ensure properties have appropriate access modifiers.
- **Clean up method signatures**: Remove unnecessary `throws` statements.

## Plants
- **Remove unused properties**: Simplify the class for clarity.
- **Organize class structure**: Follow a consistent organization pattern, like the one suggested for `ZombieBuilder`.

## Overall Playability
- **Add level transitions**: The delay after killing zombies can disrupt gameplay; smooth transitions will enhance the experience.
- **Refine the `JFrame` usage**: Consider using it an intermediate screen, and not the current one, before the game starts to improve engagement.

---

## Recurring Themes
1. **Unused Properties**: Remove them to declutter your code.
2. **Method Signature Cleanup**: Avoid unnecessary `throws` statements.
3. **Class Organization**: Maintain a consistent structure for properties, methods, and access modifiers.
4. **Code Readability**: Avoid practices like declaring variables on the same line and use block comments where appropriate.
5. **Breaking Down Large Classes**: Refactor into smaller, focused classes for easier maintenance.

---

You’ve made incredible progress on your game! By addressing these areas, you’ll enhance your code quality and gameplay experience.
Keep up the great work and continue building on your momentum! 🎮🚀
