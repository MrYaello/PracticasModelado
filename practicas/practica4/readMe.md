
# Practica 4 del curso de Modelado y Programación.
## Patrones de diseño 
### Yael Lozano Estrada 319007095
### Leslie Geronimo Soto 320032848

## ACTIVIDAD 1 
Menciona los principios de diseño esenciales de los patrones Adapter y Decorator. Menciona una desventaja de cada patron.



**Patrón Adapter:**

1. **Interfaz común**: El Adapter se utiliza para que una clase existente pueda trabajar con interfaces incompatibles. El principio es proporcionar una interfaz común que las clases existentes y las clases que se adaptan puedan utilizar.

2. **Reutilización de código**: El Adapter permite reutilizar código existente al permitir que las clases existentes trabajen con nuevas clases o interfaces sin necesidad de modificar su código.

3. **Transparencia**: El código que utiliza el Adapter generalmente no necesita saber que está trabajando con una adaptación. Debe ser transparente para el cliente.

**Patrón Decorator:**

1. **Extensibilidad**: El Decorator permite agregar funcionalidad adicional a objetos existentes de manera dinámica. Los principios de diseño de este patrón se centran en extender la funcionalidad sin modificar el código de las clases base.

2. **Composición en lugar de herencia**: En lugar de utilizar la herencia para agregar comportamiento, el Decorator utiliza la composición. Esto significa que se crean clases decoradoras que contienen una instancia de la clase base y agregan funcionalidad a través de métodos adicionales.

3. **Apertura/Cierre (Open/Closed Principle)**: El Decorator sigue el principio OCP de SOLID, que establece que las clases deben estar abiertas para la extensión pero cerradas para la modificación. Esto significa que puedes agregar nuevas funcionalidades a un objeto sin cambiar su código.

4. **Transparencia**: Al igual que en el Adapter, el uso del Decorator debe ser transparente para el cliente. El cliente debería poder interactuar con objetos decorados de la misma manera que interactuaría con objetos no decorados.

En resumen, el Adapter se enfoca en proporcionar una interfaz común entre clases incompatibles y reutilizar código existente, mientras que el Decorator se enfoca en extender la funcionalidad de objetos existentes de manera dinámica a través de la composición y sigue el principio OCP de SOLID. Ambos patrones son útiles para lograr una mayor flexibilidad y modularidad en el diseño de software.


Si bien los patrones Adapter y Decorator son útiles en muchos casos, también tienen sus desventajas y consideraciones que deben tenerse en cuenta al aplicarlos en el diseño de software:

**Desventajas del patrón Adapter:**

1. **Complejidad adicional**: Agregar un Adapter puede introducir una capa de complejidad adicional en el código, ya que implica crear una nueva clase que actúa como puente entre interfaces incompatibles. Esto puede dificultar la comprensión del código para los desarrolladores.

2. **Posible impacto en el rendimiento**: El uso excesivo de adaptadores puede afectar el rendimiento del sistema, ya que agrega una sobrecarga en tiempo de ejecución debido a la necesidad de adaptar llamadas y redirigirlas a la implementación real.

3. **Potencial para acoplamiento excesivo**: Si se utilizan demasiados adaptadores, puede aumentar el acoplamiento entre clases, lo que va en contra del principio de diseño de bajo acoplamiento. Esto podría dificultar la modificación y el mantenimiento del código.

**Desventajas del patrón Decorator:**

1. **Posible explosión de clases**: Si se aplican múltiples decoradores a un objeto, puede haber una proliferación de clases decoradoras. Esto puede resultar en un gran número de clases y una complejidad adicional en el diseño.

2. **Posible confusión en la jerarquía de clases**: Si no se gestiona cuidadosamente, la jerarquía de clases decoradoras puede volverse confusa, especialmente si se utilizan nombres de clases poco descriptivos.

3. **Potencial para afectar el rendimiento**: Al igual que con el Adapter, el uso excesivo de decoradores puede tener un impacto en el rendimiento debido a la necesidad de ejecutar múltiples capas de decoración.

4. **Dificultad para eliminar funcionalidad**: En algunos casos, puede ser complicado eliminar la funcionalidad agregada por decoradores específicos sin afectar a otros decoradores. Esto puede llevar a problemas de mantenimiento y refactorización.

En resumen, tanto el patrón Adapter como el Decorator son herramientas útiles en el diseño de software, pero deben usarse con cuidado y consideración de las desventajas potenciales. Es importante equilibrar los beneficios que aportan con las complejidades y problemas que pueden introducir en el código.