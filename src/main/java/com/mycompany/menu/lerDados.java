package com.mycompany.menu;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
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

    public static int lerIntMaiorQueZero(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                var resultado = Integer.parseInt(linha);
                if (resultado > 0) {
                    return resultado;
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static OptionalInt lerIntOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return OptionalInt.empty();
            try {
                return OptionalInt.of(Integer.parseInt(linha));
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static OptionalInt lerIntMaiorQueZeroOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return OptionalInt.empty();
            try {
                var resultado = Integer.parseInt(linha);
                if (resultado > 0) {
                    return OptionalInt.of(resultado);
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static double lerDouble(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static double lerDoubleMaiorQueZero(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                var resultado = Double.parseDouble(linha);
                if (resultado > 0) {
                    return resultado;
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static OptionalDouble lerDoubleOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return OptionalDouble.empty();
            try {
                return OptionalDouble.of(Double.parseDouble(linha));
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static OptionalDouble lerDoubleMaiorQueZeroOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return OptionalDouble.empty();
            try {
                var resultado = Double.parseDouble(linha);
                if (resultado > 0) {
                    return OptionalDouble.of(resultado);
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static BigDecimal lerBigDecimal(int casasDecimais, String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                var resultado = new BigDecimal(linha);
                if (resultado.scale() <= casasDecimais) {
                    return resultado.setScale(casasDecimais);
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static BigDecimal lerBigDecimalMaiorQueZero(int casasDecimais, String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                var resultado = new BigDecimal(linha);
                if (resultado.scale() <= casasDecimais && resultado.signum() >= 0) {
                    return resultado.setScale(casasDecimais);
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static Optional<BigDecimal> lerBigDecimalOpcional(int casasDecimais, String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return Optional.empty();
            try {
                var resultado = new BigDecimal(linha);
                if (resultado.scale() <= casasDecimais) {
                    return Optional.of(resultado.setScale(casasDecimais));
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static Optional<BigDecimal> lerBigDecimalMaiorQueZeroOpcional(int casasDecimais, String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return Optional.empty();
            try {
                var resultado = new BigDecimal(linha);
                if (resultado.scale() <= casasDecimais && resultado.signum() >= 0) {
                    return Optional.of(resultado.setScale(casasDecimais));
                }
            } catch (NumberFormatException erro) {
            }
            System.out.print(tenteNovamente);
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

    public static Optional<LocalDate> lerDataOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return Optional.empty();
            try {
                return Optional.of(LocalDate.parse(linha, DATA));
            } catch (DateTimeParseException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static LocalDateTime lerDataHora(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            try {
                return LocalDateTime.parse(linha, DATA_HORA);
            } catch (DateTimeParseException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static Optional<LocalDateTime> lerDataHoraOpcional(String tenteNovamente) {
        while (true) {
            var linha = scan.nextLine();
            if (linha.isEmpty()) return Optional.empty();
            try {
                return Optional.of(LocalDateTime.parse(linha, DATA_HORA));
            } catch (DateTimeParseException erro) {
            }
            System.out.print(tenteNovamente);
        }
    }

    public static boolean lerSimNao(String tenteNovamente) {
        while (true) {
            var linha = lerTexto().toUpperCase();
            if (linha.equals("S") || linha.equals("SIM")) return true;
            if (linha.equals("N") || linha.equals("NAO") || linha.equals("NÃO")) return false;
            System.out.print(tenteNovamente);
        }
    }

    public static String lerTexto() {
        return scan.nextLine();   
    }
}
