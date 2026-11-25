Ejercicio 2: Gestor de Conexiones a Base de Datos Simple

Contexto del problema: En un sistema de software tradicional, abrir y cerrar constantemente conexiones físicas a una base de datos local reduce drásticamente el rendimiento operativo. Se requiere un canal centralizado que controle el acceso al repositorio de datos.

Objetivo de aprendizaje: Aplicar el patrón Singleton para encapsular un recurso costoso y garantizar un punto único de control de acceso.

Requisitos funcionales:

Simular la apertura y el establecimiento de una única conexión física a la base de datos.

Proveer métodos para ejecutar operaciones genéricas de consulta y actualización utilizando siempre la misma conexión activa.

Restricciones técnicas:

El objeto gestor de conexiones no debe poder duplicarse ni instanciarse libremente desde diferentes módulos del código cliente.

El estado actual de la conexión debe mantenerse sincronizado a través de la única instancia existente en todo momento.

# Ejercicio 2: Control de Acceso a Base de Datos (Patrón Singleton)

## Temática

Banca Digital y Tradicional — Control centralizado de cupo de conexiones a la base de datos.

## Consigna

La base de datos donde se opera tiene un límite de 3 conexiones simultáneas para no saturarse. Si cada módulo del sistema controlara su propio acceso por separado, se podría superar ese tope y provocar fallos o bloqueos.

El objetivo es aplicar el patrón Singleton para centralizar el conteo de conexiones ocupadas, permitir el ingreso de sistemas solo cuando haya cupo disponible y liberar un lugar cuando un sistema salga.

## Explicación lógica

1. Atributos: En la clase ControlAccesoBaseDatos se declaran limiteConexiones (private final int) con valor 3 para fijar el tope máximo de conexiones simultáneas y conexionesOcupadas (private int) para llevar la cuenta de las conexiones activas en ese momento. A esto se suma instancia (private static ControlAccesoBaseDatos), que va a conservar la única referencia en memoria para que todos los sectores del sistema controlen exactamente el mismo cupo sin pasarse del límite.


2. Instancia única: El constructor se define como private para impedir que se creen controladores paralelos usando new, y además deja conexionesOcupadas en 0 cuando se crea por primera vez. El método getInstancia() controla el acceso: revisa si instancia es null (es decir, si todavía no se creó), la instancia una sola vez y en las siguientes llamadas devuelve siempre ese mismo objeto ya existente.


3. Asignación y Acceso: En Main, se obtiene la instancia desde dos puntos (control1 y control2) y se simulan ingresos a la base de datos con entrarABaseDatos(...), sumando 1 a conexionesOcupadas mientras haya lugar por debajo de limiteConexiones y mostrando un aviso si la base de datos ya está llena. Cuando un sistema libera su lugar con salirDeBaseDatos(...), se resta 1 si hay conexiones ocupadas, y la comparación control1 == control2 confirma que ambas variables apuntan exactamente al mismo objeto en memoria.



## Estructura del ejercicio

* ControlAccesoBaseDatos.java: Implementa el Singleton y los métodos entrarABaseDatos(...) y salirDeBaseDatos(...) con el límite de 3 conexiones
* Main.java: Simula entradas, bloqueo por cupo lleno, liberación de espacio y verifica que se comparte la misma instancia


