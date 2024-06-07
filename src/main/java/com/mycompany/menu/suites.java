package com.mycompany.menu;

import com.mycompany.menu.Menu.Hospede;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class suites {

    static Hospede achei;
    static Map<Integer, Reserva> reservas = new HashMap<>();
    public record Reserva(String nome, String checkIn, String checkOut) {}

    public static void ReservaDeQuartos() {
        while (true) {
            System.out.println();
            System.out.println("---- Reserva de Quartos ----");
            System.out.println();
            System.out.println("1 - Exibir Quartos Disponiveis.");
            System.out.println("2 - Escolher quarto.");
            System.out.println("3 - Voltar");
            System.out.println();
            var escolha = lerDados.lerInt();

            switch (escolha) {
                case 1:
                    exibirReserva();
                    break;
                case 2:
                    fazerReserva();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Numero invalido. Tente novamente");
            }
        }
    }

    public static void exibirReserva() {
        System.out.println("----- Reservas de Quartos -----");
        for (int i = 1; i <= 40; i++) {
            if (reservas.containsKey(i)) {
                System.out.println("Quarto " + i + ": Ocupado por " + reservas.get(i).nome());
            } else {
                System.out.println("Quarto " + i + ": Disponivel");
            }
        }
    }

    public static void fazerReserva() {
        
        System.out.println("---- Escolha de Quartos ----");
        System.out.println("Digite seu CPF:");
        var cpf = lerDados.lerTexto();
        var lista = Menu.Hospede.carregarHospedesDoArquivo();
        achei = null;
        for (var h : lista) {
            if (h.cpfPassaporteRne().equals(cpf)) {
                achei = h;
                System.out.println("Hospede encontrado: " + achei.nome());
                break;
            }
        }
        if (achei == null) {
            while(true){
            System.out.println("Voce nao tem cadastro. Deseja se cadastrar?");
            System.out.println("1 - SIM.");
            System.out.println("2 - NAO.");
            var escolha = lerDados.lerInt();
            switch(escolha){
                case 1:
                    Menu.cadastrarHospede();
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Numero invalido, Digite 1 ou 2.");
                    break;
            }
            }
        }
        System.out.println();

        while (true) {
            System.out.println("Qual quarto deseja reservar?");
            int numeroQuarto = lerDados.lerInt();

            if (numeroQuarto < 1 || numeroQuarto > 40) {
                System.out.println("Numero de quarto invalido. Por favor, digite um numero de 1 a 40.");
            } else if (reservas.containsKey(numeroQuarto)) {
                System.out.println("Quarto ocupado por " + reservas.get(numeroQuarto).nome());
            } else {
                System.out.println();
                System.out.println("Data de Check-in: ");
                LocalDate checkIn = lerDados.lerData2();
                System.out.println("Data de Check-out: ");
                LocalDate checkOut = lerDados.lerData3();
                if(checkOut.isAfter(checkIn)){
                System.out.println("---- Quarto Reservado ----");
                System.out.println("Quarto " + numeroQuarto + " reservado para " + achei.nome());
                
                String dataCheckInStr = checkIn.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                String dataCheckOutStr = checkOut.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                
                Reserva reserva = new Reserva(achei.nome(), dataCheckInStr, dataCheckOutStr);
                reservas.put(numeroQuarto, reserva);
                break;
                }else{
                    System.out.println("A data de CheckOut deve ser posterior a data de checkIn.");
                }
        }
    }
}
    public static void lista1() {
        System.out.println("----- Reservas de Quartos -----");
        System.out.println();
        for (Map.Entry<Integer, Reserva> reserva : reservas.entrySet()) {
            Reserva reservaEX = reserva.getValue();
            System.out.println("Quarto numero " + reserva.getKey() + " ocupado por " + reservaEX.nome() +
                    ", Check-in: " + reservaEX.checkIn() + ", Check-out: " + reservaEX.checkOut());
        }
    }

    public static void cancelarReserva() {
        System.out.println("---- Cancelar Reserva ----");
        System.out.println();
        System.out.println("Qual o numero do quarto que deseja cancelar?");
        var numeroQuarto = lerDados.lerInt();
        System.out.println("Deseja cancelar a reserva do quarto: " + numeroQuarto + "?");
        System.out.println("1 - Sim.");
        System.out.println("2 - Não.");
        var escolha = lerDados.lerInt();
        if (escolha == 2){
            return;
        }
        if (reservas.containsKey(numeroQuarto)) {
            reservas.remove(numeroQuarto);
            salvarReservasEmArquivo(reservas);
            System.out.println("---- Reserva Cancelada ----");
            System.out.println("Deseja fazer outra reserva?");
            System.out.println("1 - SIM.");
            System.out.println("2 - NAO.");
            escolha = lerDados.lerInt();
            switch (escolha) {
                case 1:
                    ReservaDeQuartos();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Numero invalido, Digite 1 ou 2.");
                    break;
            }
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    public static List<String> transformarEmStrings(Map<Integer, Reserva> reservas) {
        List<String> resultado = new ArrayList<>();
        for (var entry : reservas.entrySet()) {
            int numeroQuarto = entry.getKey();
            Reserva reserva = entry.getValue();
            resultado.add(Integer.toString(numeroQuarto));
            resultado.add(reserva.nome());
            resultado.add(reserva.checkIn());
            resultado.add(reserva.checkOut());
        }
        return resultado;
    }

    public static Map<Integer, Reserva> lerDaLista(List<String> lista) {
        if (lista.size() % 4 != 0) throw new RuntimeException("Lista com erro");
        Map<Integer, Reserva> resultado = new HashMap<>();
        for (int i = 0; i < lista.size(); i += 4) {
            int numeroQuarto = Integer.parseInt(lista.get(i));
            String nome = lista.get(i + 1);
            String checkIn = lista.get(i + 2);
            String checkOut = lista.get(i + 3);
            Reserva reserva = new Reserva(nome, checkIn, checkOut);
            resultado.put(numeroQuarto, reserva);
        }
        return resultado;
    }

    public static void salvarReservasEmArquivo(Map<Integer, Reserva> reservas) {
        List<String> linhas = transformarEmStrings(reservas);
        Arquivo.escreverLinhas(linhas, "Reservas.txt");
    }

    public static Map<Integer, Reserva> carregarReservasDoArquivo() {
        List<String> linhas = Arquivo.lerLinhas("Reservas.txt");
        return lerDaLista(linhas);
    }
}




