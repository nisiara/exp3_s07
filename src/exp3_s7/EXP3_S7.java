
package exp3_s7;

import java.util.ArrayList;
import java.util.Scanner;

public class EXP3_S7 {

  static int opcionMenu = 0;
  static int opcionUbicacion = 0;
  static int esEstudiante = 0;
  static int es3raEdad = 0;
  static int precioEntrada = 0;
  static double valorDescuento = 0;
  static double precioFinalEntrada = 0;
  static String tipoUbicacion = "";
  static String tipoDescuento = "";
  static int entradasVendidas = 0;
  static double totalPagarEntradas = 0;
  
  public static void main(String[] args) {
    Scanner entradaUsuario = new Scanner(System.in);

    ArrayList<String> arrayUbicacion = new ArrayList<>();
    ArrayList<Integer> arrayPrecioEntradas = new ArrayList<>();
    ArrayList<Double> arrayPrecioFinalEntrada = new ArrayList<>();
    ArrayList<String> arrayTipoDescuento = new ArrayList<>();

    //MENÚ PRINCIPAL
    do{
      System.out.println("------------------ GESTION VENTAS TEATRO MORO--------------------");
      System.out.println("[1]Ver Descuentos Disponibles");
      System.out.println("[2]Comprar Entradas");
      System.out.println("[3]Generar Boleta");
      System.out.println("[4]Salir");
      System.out.println("----------------------------------------------------------------- \n");

      opcionMenu = entradaUsuario.nextInt();
      if(opcionMenu < 1 || opcionMenu > 4){
        System.out.println("***** Debes ingresar el número correspondiente a la opción ***** \n");
      }

      //OPCIONES MENU PRINCIPAL
      switch(opcionMenu){

        //Mostrar promociones
        case 1:
          System.out.println("--------- Descuentos Disponibles ----------");
          System.out.println("10% de Descuento estudiante");
          System.out.println("15% de Descuento tercera edad \n" );
          break;

        //Elección de ubicación
        case 2:
          do{
            System.out.println("------------- COMPRA DE ENTRADAS ------------");
            System.out.println("----------------- Ubicaciones ---------------");
            System.out.println("[1]VIP [2]Platea Baja [3]Platea Alta [4]Palco");
            opcionUbicacion = entradaUsuario.nextByte();

            if(opcionUbicacion < 1 || opcionUbicacion > 4){
              System.out.println("***** Debe ingresar el número correspondiente a la ubicación para continuar. ***** \n");
            }
          } while(opcionUbicacion < 1 || opcionUbicacion > 4);

          // Definición de entrada por cada caso
          switch (opcionUbicacion){
            case 1:
              precioEntrada = 25000;
              tipoUbicacion = "VIP";
              break;
            case 2:
              precioEntrada = 19000;
              tipoUbicacion = "Platea Baja";
              break;
            case 3:
              precioEntrada = 11000;
              tipoUbicacion = "Platea Alta";
              break;
            case 4:
              precioEntrada = 7200;
              tipoUbicacion = "Palco";
              break;
          }

          // Aplicación de descuentos
          do{
            System.out.println("¿El cliente es tercera edad? [1]Sí [2]No ");
            es3raEdad = entradaUsuario.nextByte();
            if(es3raEdad != 1 && es3raEdad != 2){
              System.out.println("***** Debe ingresar el número correcto para continuar. ***** \n");
            }
          } while (es3raEdad != 1 && es3raEdad != 2);


          if(es3raEdad == 1){
            valorDescuento = precioEntrada * 0.15;
            tipoDescuento = "Descuento tercera edad del 15%";
          }
          else {
            tipoDescuento = "No aplica descuento";
            do {
              System.out.println("¿El cliente es estudiante? [1]Sí [2]No ");
              esEstudiante = entradaUsuario.nextInt();
              if(esEstudiante != 1 && esEstudiante != 2){
                System.out.println("***** Debe ingresar el número correcto para continuar. ***** \n");
              }
            } while (esEstudiante != 1 && esEstudiante != 2);

            if(esEstudiante == 1 ) {
              valorDescuento = precioEntrada * 0.10;
              tipoDescuento = "Descuento estudiante del 10%";
            } else {
              tipoDescuento = "No aplica descuento";
            }
          }

          precioFinalEntrada = precioEntrada - valorDescuento;

          arrayUbicacion.add(tipoUbicacion);
          arrayPrecioEntradas.add(precioEntrada);
          arrayPrecioFinalEntrada.add(precioFinalEntrada);
          arrayTipoDescuento.add(tipoDescuento);

          valorDescuento = 0;
          tipoDescuento = "";

          entradasVendidas += 1;

          System.out.println("--- Has comprado una entrada para la ubicación " + tipoUbicacion + " --- \n");

          break;

        //Resumen compra
        case 3:
          if (entradasVendidas != 0) {
            System.out.println("------------------ BOLETA ENTRADAS --------------------");
            for (int i = 0; i < entradasVendidas; i++) {
              System.out.println("Entrada #" + (i+1));
              System.out.println("Ubicacion: " + arrayUbicacion.get(i));
              System.out.println("Precio Entrada: $" + arrayPrecioEntradas.get(i));

              System.out.println(arrayTipoDescuento.get(i));
              System.out.println("Precio final entrada: $" + arrayPrecioFinalEntrada.get(i));

              System.out.println("----------------------------------------------------");
              totalPagarEntradas += arrayPrecioFinalEntrada.get(i);
            }

            System.out.println("TOTAL SIN IVA: $" + totalPagarEntradas);
            System.out.println("TOTAL MÁS IVA (19%): $" + Math.round( totalPagarEntradas + (totalPagarEntradas * 0.19) ) );

            System.out.println("----------------------------------------------------------------");
            System.out.println("   Gracias por su visita al Teatro Moro - Disfrute la función"                  );
            System.out.println("---------------------------------------------------------------- \n");
            
            opcionMenu = 4;
          }
          else {
            System.out.println("---------------------------------------------");
            System.out.println("     No has comprado ninguna entrada aún"  );
            System.out.println("---------------------------------------------\n");

          }
          break;
      }
    } while(opcionMenu != 4);
  }
  
}
