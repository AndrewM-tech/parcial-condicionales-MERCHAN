# Parcial - Estructuras de Control Condicional
## TaxiYopal S.A.S. - Tarificador Inteligente de Viajes

---

## 📋 Datos del estudiante

| Campo | Información |
|-------|-------------|
| **Nombre completo** | Kevin Andrés Merchán González |
| **Código estudiantil** | [1029644350] |
| **Programa** | Ingeniería de Sistemas |
| **Semestre** | [primer semestre] |
| **Curso** | Algoritmos I |
| **Institución** | Uniremington Yopal |
| **Lenguaje utilizado** | Java |
| **Versión de JDK** | JDK 17 |

---

## 🚕 Descripción del proyecto

Este programa implementa un **tarificador automático de viajes** para la empresa TaxiYopal S.A.S. Calcula el valor a pagar por un viaje aplicando cuatro reglas de negocio:

1. **Tarifa base** por tipo de vehículo (Motocarro, Automóvil, Camioneta 4x4)
2. **Recargos acumulativos** (nocturno, domingo/festivo, lluvia fuerte, rural)
3. **Descuentos** por tipo de pasajero (frecuente, estudiante, adulto mayor, ocasional)
4. **Tarifa solidaria** de Yopal (para viajes urbanos)

El programa utiliza solo estructuras de control condicional: `if`, `if-else`, `else if`, condicionales anidadas, operadores lógicos (`&&`, `||`, `!`), y variables booleanas intermedias. **NO** se utilizan ciclos (`for`, `while`, `do-while`), arreglos, listas ni `switch`.

---

## 📥 Instrucciones para ejecutar 

### Requisitos previos
- Tener Java JDK 17 o superior instalado
- Tener Git instalado (opcional, solo para clonar el repositorio)

### Compilar el programa
```bash
javac src/TarificadorTaxiYopal.java
