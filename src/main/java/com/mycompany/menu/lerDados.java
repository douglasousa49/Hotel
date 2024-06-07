package com.mycompany.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class lerDados {
    
    static LocalDate dataCerta;
    private static final Scanner scan = new Scanner(System.in);
    
    public static final DateTimeFormatter DATA = DateTimeFormatter
            .ofPattern("dd/MM/uuuu")
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final DateTimeFormatter DATA_HORA = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm:ss")
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);
    
    public static final DateFormat DATA_FORMATO = new SimpleDateFormat("dd/MM/yyyy");

    public static int lerInt() {        

        Scanner ler = new Scanner(System.in);        

        while(true){

            var linha = ler.nextLine();            

            try {

                return Integer.parseInt(linha);

            }

            catch (NumberFormatException erro) {

                System.out.println("Tente novamente.");

            }

        }

    }    
    public static LocalDate lerData2() {
        while (true) {
            var linha = scan.nextLine();
            try {
                dataCerta = LocalDate.parse(linha, DATA);
                LocalDate hoje = LocalDate.now();
                if(dataCerta.isBefore(hoje)){
                    System.out.println("A data informada nao pode ser anterior a data atual.");
                }
                else{
                    return dataCerta;
                }
            } catch (DateTimeParseException erro) {
            }
            System.out.println("Tente novamente:");
        }
    }
    
    public static LocalDate lerData3() {
        while (true) {
            var linha = scan.nextLine();
            try {
                LocalDate dataCerta2 = LocalDate.parse(linha, DATA);
                if(dataCerta2.isBefore(dataCerta)){
                    System.out.println("A data de CheckOut nao pode ser anterior a data de CheckIn.");
                }
                else{
                    return dataCerta2;
                }
            } catch (DateTimeParseException erro) {
            }
            System.out.println("Tente novamente:");
        }
    }

    public static LocalDate lerData() {
        while (true) {
            var linha = scan.nextLine();
            try {
                return LocalDate.parse(linha, DATA);
            } catch (DateTimeParseException erro) {
            }
            System.out.println("Tente novamente:");
        }
    }
    
    public static String lerTexto() {
        return scan.nextLine();   
    }
}
