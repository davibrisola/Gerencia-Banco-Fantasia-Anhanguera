/**
 *
 * @author Davi Brisola
 */
import java.util.Scanner;
import java.util.ArrayList;

public class BancoApp {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.adicionarClienteAleatorio("João", "Silva", "111.111.111-11");
        banco.adicionarClienteAleatorio("Maria", "Santos", "222.222.222-22");
        
        Scanner scanner = new Scanner(System.in);
        
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    banco.cadastrarUsuario();
                    break;
                case 2:
                    banco.consultarSaldo();
                    break;
                case 3:
                    banco.realizarDeposito();
                    break;
                case 4:
                    banco.realizarRetirada();
                    break;
                case 5:
                    System.out.println("Obrigado por utilizar o Gerenciador de Banco. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 5);
        
        scanner.close();
    }
    
    public static void exibirMenu() {
        System.out.println("===== Gerenciador de Banco =====");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Consultar Saldo");
        System.out.println("3. Realizar Depósito");
        System.out.println("4. Realizar Retirada");
        System.out.println("5. Sair");
        System.out.println("Escolha uma opção: ");
    }
}

class Banco {
    private ArrayList<Cliente> clientes;
    
    public Banco() {
        this.clientes = new ArrayList<>();
    }
    
    public void adicionarClienteAleatorio(String nome, String sobrenome, String cpf) {
        Cliente cliente = new Cliente(nome, sobrenome, cpf);
        clientes.add(cliente);
    }
    
    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome: ");
        String nome = scanner.next();
        System.out.println("Digite o sobrenome: ");
        String sobrenome = scanner.next();
        System.out.println("Digite o CPF: ");
        String cpf = scanner.next();
        Cliente cliente = new Cliente(nome, sobrenome, cpf);
        clientes.add(cliente);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    
    public void consultarSaldo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("Saldo atual do cliente " + cliente.getNome() + " " + cliente.getSobrenome() + ": R$" + cliente.getSaldo());
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
    
    public void realizarDeposito() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("Digite o valor do depósito: ");
                double valor = scanner.nextDouble();
                cliente.depositar(valor);
                System.out.println("Depósito realizado com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
    
    public void realizarRetirada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("Digite o valor da retirada: ");
                double valor = scanner.nextDouble();
                if (cliente.getSaldo() >= valor) {
                    cliente.retirar(valor);
                    System.out.println("Retirada realizada com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
}

class Cliente {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;
    
    public Cliente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void depositar(double valor) {
        saldo += valor;
    }
    
    public void retirar(double valor) {
        saldo -= valor;
    }
}
