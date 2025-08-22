package SumList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumList {

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();

        System.out.println("👉 Ingresa 5 números enteros:");

        // Pedir 5 números con validaciones
        for (int i = 0; i < 5; i++) {
            Integer numero = null;

            while (numero == null) {
                System.out.print("Número " + (i + 1) + ": ");
                String entrada = scanner.nextLine().trim();

                if (entrada.isEmpty()) {
                    System.out.println("⚠️ Error: No puedes dejar el campo vacío.");
                    continue;
                }

                try {
                    numero = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error: Ingresa solo números enteros.");
                }
            }

            numeros.add(numero);
        }

        // Mostrar lista ingresada
        System.out.println("\n✅ Lista ingresada: " + numeros);

        // Pedir número objetivo
        Integer objetivo = null;
        while (objetivo == null) {
            System.out.print("\n Ingresa el número objetivo: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("⚠️ Error: No puedes dejar el campo vacío.");
                continue;
            }

            try {
                objetivo = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Ingresa solo números enteros.");
            }
        }

        // Buscar si dos números suman el objetivo
        boolean encontrado = false;
        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(i) + numeros.get(j) == objetivo) {
                    System.out.println("🎯 Se encontró dos números que suman y dan el número objetivo");
                    System.out.println("👉 Número en índice " + i + " = " + numeros.get(i));
                    System.out.println("👉 Número en índice " + j + " = " + numeros.get(j));
                    System.out.println("✅ " + numeros.get(i) + " + " + numeros.get(j) + " = " + objetivo);
                    encontrado = true;
                }
            }
        }


        if (!encontrado) {
            System.out.println("❌ No se encontró ninguna combinación que sume " + objetivo);
        }
    }
}
