package com.mycompany.menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static ArrayList<Hospede> hospedes = new ArrayList<>();

    public static void main(String[] args) {
        if (Arquivo.arquivoExiste("Hospede.txt")) {
            hospedes = (ArrayList<Hospede>) Hospede.carregarHospedesDoArquivo();
        }
        if (Arquivo.arquivoExiste("Reservas.txt")) {
            suites.reservas = suites.carregarReservasDoArquivo();
        }
        menuPrincipal();
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("==== Menu Principal ====");
            System.out.println();
            System.out.println("1 - Cadastrar hospede");
            System.out.println("2 - Fazer Reserva");
            System.out.println("3 - Listar Hospedes");
            System.out.println("4 - Listar Reservas");
            System.out.println("5 - Cancelar/Alterar Reserva");
            System.out.println("6 - Sair");
            System.out.println();
            System.out.println("Escolha uma das opcoes:");
            var escolha = lerDados.lerInt();

            switch (escolha) {
                case 1:
                    cadastrarHospede();
                    break;
                case 2:
                    suites.ReservaDeQuartos();
                    break;
                case 3:
                    listarHospedes();
                    break;
                case 4:
                    suites.lista1();
                    break;
                case 5:
                    suites.cancelarReserva();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Tente novamente.");
                    break;
            }
        }
    }

    public static void cadastrarHospede() {
        System.out.println("---- Cadastro de Hospedes ----");
        System.out.println();
        System.out.println("Nome: ");
        String nome = lerDados.lerTexto();

        System.out.println("Data de nascimento: ");
        LocalDate dataDeNascimento = lerDados.lerData();

        System.out.println("CPF/Passaporte/RNE (Digite somente numeros): ");
        String cpfPassaporteRne = lerDados.lerTexto();

        System.out.println("Nacionalidade: ");
        String nacionalidade = lerDados.lerTexto();

        System.out.println("Endereço: ");
        String endereco = lerDados.lerTexto();

        System.out.println("Número: ");
        int numeroEndereco = lerDados.lerInt();

        System.out.println("Complemento: ");
        String complementoEndereco = lerDados.lerTexto();

        System.out.println("CEP (Digite somente números): ");
        String cep = lerDados.lerTexto();

        Hospede novoHospede = new Hospede(nome, dataDeNascimento, cpfPassaporteRne, nacionalidade, endereco, numeroEndereco, complementoEndereco, cep);
        hospedes.add(novoHospede);
        Hospede.salvarHospedesEmArquivo(hospedes);

        System.out.println("Hospede cadastrado com sucesso!");
    }

    public static void listarHospedes() {
        if (hospedes.isEmpty()) {
            System.out.println("Não há hóspedes cadastrados.");
        } else {
            System.out.println("Lista de Hóspedes:");
            for (Hospede hospede : hospedes) {
                System.out.println("Nome: " + hospede.nome());
                System.out.println("Data de Nascimento: " + hospede.dataDeNascimentoString());
                System.out.println("CPF/Passaporte/RNE: " + hospede.cpfPassaporteRne());
                System.out.println("Nacionalidade: " + hospede.nacionalidade());
                System.out.println("Endereço: " + hospede.endereco());
                System.out.println("Número: " + hospede.numero());
                System.out.println("Complemento: " + hospede.complemento());
                System.out.println("CEP: " + hospede.cep());
                System.out.println();
            }
        }
    }

    public record Hospede(
        String nome,
        LocalDate dataDeNascimento,
        String cpfPassaporteRne,
        String nacionalidade,
        String endereco,
        int numero,
        String complemento,
        String cep
    ) {
        public String dataDeNascimentoString() {
            return dataDeNascimento.format(lerDados.DATA);
        }

        public List<String> transformarEmLista() {
            return List.of(
                nome,
                dataDeNascimento.toString(),
                cpfPassaporteRne,
                nacionalidade,
                endereco,
                Integer.toString(numero),
                complemento,
                cep
            );
        }

        public static List<Hospede> lerDaLista(List<String> lista) {
            if (lista.size() % 8 != 0) throw new RuntimeException("Lista com erro");
            List<Hospede> resultado = new ArrayList<>();
            for (int i = 0; i < lista.size(); i += 8) {
                String nome = lista.get(i);
                LocalDate dataDeNascimento = LocalDate.parse(lista.get(i + 1));
                String cpfPassaporteRne = lista.get(i + 2);
                String nacionalidade = lista.get(i + 3);
                String endereco = lista.get(i + 4);
                int numero = Integer.parseInt(lista.get(i + 5));
                String complemento = lista.get(i + 6);
                String cep = lista.get(i + 7);

                Hospede novoHospede = new Hospede(nome, dataDeNascimento, cpfPassaporteRne, nacionalidade, endereco, numero, complemento, cep);
                resultado.add(novoHospede);
            }
            return resultado;
        }

        public static List<String> transformarEmStrings(List<Hospede> hospedes) {
            List<String> resultado = new ArrayList<>();
            for (var h : hospedes) {
                var listinha = h.transformarEmLista();
                resultado.addAll(listinha);
            }
            return resultado;
        }

        public static void salvarHospedesEmArquivo(List<Hospede> hospedes) {
            if (!Arquivo.arquivoExiste("Hospede.txt")) {
                try {
                    Files.createFile(Path.of("Hospede.txt"));
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao criar o arquivo Hospede.txt", e);
                }
            }

            List<String> linhas = transformarEmStrings(hospedes);
            Arquivo.escreverLinhas(linhas, "Hospede.txt");
        }

        public static List<Hospede> carregarHospedesDoArquivo() {
            List<String> linhas = Arquivo.lerLinhas("Hospede.txt");
            return lerDaLista(linhas);
        }
    }
}
