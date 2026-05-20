import java.util.Scanner;

public class TarificadorTaxiYopal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // ============================================================
        // 1. SOLICITUD DE DATOS AL OPERADOR
        // ============================================================
        System.out.println("=== TARIFICADOR AUTOMÁTICO - TAXIYOPAL S.A.S. ===");
        
        System.out.print("1. Tipo de vehículo (1=Motocarro, 2=Automóvil, 3=Camioneta): ");
        int V = scanner.nextInt();
        
        System.out.print("2. Distancia del viaje en kilómetros: ");
        double km = scanner.nextDouble();
        
        System.out.print("3. Hora de inicio (0 a 23): ");
        int hora = scanner.nextInt();
        
        System.out.print("4. ¿Domingo o festivo? (S/N): ");
        String D = scanner.next().toUpperCase();
        
        System.out.print("5. ¿Hay lluvia fuerte? (S/N): ");
        String L = scanner.next().toUpperCase();
        
        System.out.print("6. ¿Viaje rural? (S/N): ");
        String R = scanner.next().toUpperCase();
        
        System.out.print("7. Tipo de pasajero (1=Frecuente, 2=Estudiante, 3=Adulto mayor, 4=Ocasional): ");
        int P = scanner.nextInt();
        
        System.out.print("8. Edad del pasajero: ");
        int edad = scanner.nextInt();
        
        // ============================================================
        // 2. VALIDACIONES DE ENTRADA
        // ============================================================
        
        boolean entradaValida = true;
        String mensajeError = "";
        
        // Validación tipo de vehículo
        if (V < 1 || V > 3) {
            entradaValida = false;
            mensajeError = "Tipo de vehículo no válido";
        }
        
        // Validación kilómetros
        if (entradaValida && (km <= 0)) {
            entradaValida = false;
            mensajeError = "Distancia inválida";
        }
        
        // Validación hora
        if (entradaValida && (hora < 0 || hora > 23)) {
            entradaValida = false;
            mensajeError = "Hora inválida";
        }
        
        // Validación respuestas S/N
        if (entradaValida && (!D.equals("S") && !D.equals("N"))) {
            entradaValida = false;
            mensajeError = "Respuesta S/N inválida (D)";
        }
        
        if (entradaValida && (!L.equals("S") && !L.equals("N"))) {
            entradaValida = false;
            mensajeError = "Respuesta S/N inválida (L)";
        }
        
        if (entradaValida && (!R.equals("S") && !R.equals("N"))) {
            entradaValida = false;
            mensajeError = "Respuesta S/N inválida (R)";
        }
        
        // Validación tipo de pasajero
        if (entradaValida && (P < 1 || P > 4)) {
            entradaValida = false;
            mensajeError = "Tipo de pasajero no válido";
        }
        
        // Validación edad
        if (entradaValida && (edad < 0 || edad > 120)) {
            entradaValida = false;
            mensajeError = "Edad fuera de rango";
        }
        
        // Si hay error, mostramos y terminamos
        if (!entradaValida) {
            System.out.println("ERROR: " + mensajeError);
            System.out.println("El programa ha terminado.");
            scanner.close();
            return;
        }
        
        // ============================================================
        // 3. REGLA 1 - TARIFA BASE POR TIPO DE VEHÍCULO
        // ============================================================
        
        double tarifaPorKm = 0;
        double tarifaMinima = 0;
        String nombreVehiculo = "";
        
        // Cadena if-else if-else (mínimo 3 ramas)
        if (V == 1) {
            tarifaPorKm = 1200;
            tarifaMinima = 5000;
            nombreVehiculo = "Motocarro";
        } else if (V == 2) {
            tarifaPorKm = 2000;
            tarifaMinima = 8000;
            nombreVehiculo = "Automóvil";
        } else if (V == 3) {
            tarifaPorKm = 2800;
            tarifaMinima = 12000;
            nombreVehiculo = "Camioneta 4x4";
        }
        
        double subtotal = km * tarifaPorKm;
        boolean seAplicoTarifaMinima = false;
        
        // If simple
        if (subtotal < tarifaMinima) {
            subtotal = tarifaMinima;
            seAplicoTarifaMinima = true;
        }
        
        // ============================================================
        // 4. REGLA 2 - RECARGOS SOBRE EL SUBTOTAL
        // ============================================================
        
        double porcentajeRecargo = 0;
        
        if (hora >= 22 || hora < 5) {
            porcentajeRecargo = porcentajeRecargo + 20;
        }
        
        if (D.equals("S")) {
            porcentajeRecargo = porcentajeRecargo + 15;
        }
        
        if (L.equals("S")) {
            porcentajeRecargo = porcentajeRecargo + 10;
        }
        
        // Variable booleana intermedia
        boolean esRural = R.equals("S");
        if (esRural) {
            porcentajeRecargo = porcentajeRecargo + 25;
        }
        
        double valorConRecargos = subtotal * (1 + porcentajeRecargo / 100);
        double valorRecargoPesos = valorConRecargos - subtotal;
        
        // ============================================================
        // 5. REGLA 3 - DESCUENTO POR TIPO DE PASAJERO
        // ============================================================
        
        // Validación de inconsistencia: adulto mayor con edad < 60
        if (P == 3 && edad < 60) {
            System.out.println("ADVERTENCIA: Inconsistencia: edad no corresponde a adulto mayor");
            System.out.println("Se reasigna a pasajero ocasional (sin descuento)");
            P = 4;
        }
        
        double porcentajeDescuento = 0;
        String tipoPasajero = "";
        
        // Otra cadena if-else if-else
        if (P == 1) {
            porcentajeDescuento = 10;
            tipoPasajero = "Pasajero frecuente (≥20 viajes)";
        } else if (P == 2) {
            porcentajeDescuento = 8;
            tipoPasajero = "Estudiante con carnet vigente";
        } else if (P == 3) {
            porcentajeDescuento = 12;
            tipoPasajero = "Adulto mayor (≥60 años)";
        } else if (P == 4) {
            porcentajeDescuento = 0;
            tipoPasajero = "Pasajero ocasional";
        }
        
        double valorDescuentoPesos = valorConRecargos * (porcentajeDescuento / 100);
        double totalFinal = valorConRecargos - valorDescuentoPesos;
        
        // ============================================================
        // 6. REGLA 4 - TARIFA SOLIDARIA DE YOPAL
        // ============================================================
        
        boolean seAplicoTarifaSolidaria = false;
        
        // Condicional anidada (if dentro de if)
        if (!esRural) {
            if (totalFinal < tarifaMinima) {
                totalFinal = tarifaMinima;
                seAplicoTarifaSolidaria = true;
            }
        }
        
        // ============================================================
        // 7. SALIDA - RECIBO DEL VIAJE
        // ============================================================
        
        System.out.println("\n==================================================");
        System.out.println("           RECIBO DE VIAJE - TAXIYOPAL S.A.S.");
        System.out.println("==================================================\n");
        
        System.out.println("--- DATOS DEL VIAJE ---");
        System.out.println("Tipo de vehículo: " + nombreVehiculo);
        System.out.printf("Kilómetros recorridos: %.2f km\n", km);
        
        System.out.println("\n--- REGLA 1: TARIFA BASE ---");
        System.out.printf("Subtotal por tarifa base: $%,.0f\n", subtotal);
        if (seAplicoTarifaMinima) {
            System.out.println("*** SE APLICÓ TARIFA MÍNIMA DEL VEHÍCULO ***");
        }
        
        System.out.println("\n--- REGLA 2: RECARGOS ---");
        System.out.printf("Porcentaje total de recargo: %.0f%%\n", porcentajeRecargo);
        System.out.printf("Valor del recargo: $%,.0f\n", valorRecargoPesos);
        System.out.printf("Valor con recargos: $%,.0f\n", valorConRecargos);
        
        System.out.println("\n--- REGLA 3: DESCUENTOS ---");
        System.out.println("Tipo de pasajero: " + tipoPasajero);
        System.out.printf("Porcentaje de descuento: %.0f%%\n", porcentajeDescuento);
        System.out.printf("Valor del descuento: $%,.0f\n", valorDescuentoPesos);
        
        System.out.println("\n--- REGLA 4: TOTAL FINAL ---");
        System.out.printf("TOTAL A PAGAR: $%,.0f\n", totalFinal);
        if (seAplicoTarifaSolidaria) {
            System.out.println("*** SE APLICÓ TARIFA SOLIDARIA MÍNIMA ***");
        }
        
        System.out.println("\n==================================================");
        System.out.println("¡Gracias por viajar con TaxiYopal S.A.S.!");
        
        scanner.close();
    }
}