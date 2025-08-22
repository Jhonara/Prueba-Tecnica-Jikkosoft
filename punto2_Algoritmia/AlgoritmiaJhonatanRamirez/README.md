# Proyecto: Suma en Lista

Este proyecto en **Java** permite al usuario ingresar una lista de números, mostrarla en pantalla y verificar si existen dos números dentro de la lista que sumen un número objetivo definido por el usuario.

---

## Requisitos

- Tener instalado **Java JDK 22**
- Tener un IDE como **IntelliJ IDEA**, **Eclipse** o usar la terminal.

---

## Estructura del proyecto

Proyecto/
│── src/
│ ├── Main.java
│ └── SumList/
│   └── SumList.java
│── README.md

---

## Ejecución

1. Clonar o descargar este repositorio.
2. Abrir el proyecto en tu IDE (ejemplo: IntelliJ IDEA).
3. Ejecutar la clase **Main.java**, que contiene el método `public static void main(String[] args)`.
   - El programa pedirá ingresar **5 números**.
   - Luego pedirá el **número objetivo**.
   - Mostrará la lista ingresada.
   - Validará si existen dos números cuya suma sea igual al número objetivo.

---

## Ejemplo de uso

Ingrese el número 1: 3
Ingrese el número 2: 7
Ingrese el número 3: 2
Ingrese el número 4: 5
Ingrese el número 5: 8

✅ Números ingresados: [3, 7, 2, 5, 8]

Ingrese el número objetivo: 10

🎯 Se encontró dos números que suman y dan el número objetivo
👉 Número en índice 0 = 3
👉 Número en índice 1 = 7
✅ 3 + 7 = 10



---

## Notas

- Si el usuario ingresa valores no numéricos, vacíos o alfanuméricos, el programa los rechazará y pedirá que se ingrese un número válido.
- El programa se ejecuta desde **Main.java**.

---

## Autor

Proyecto creado por **Jhonatan Stiven Ramírez Useche**