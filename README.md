# 🦶 Projeto Podologia (Java)

Um sistema simples em **Java** para gerenciamento de **clientes e atendimentos de podologia**, rodando no console e com **persistência em arquivos `.txt`**.

---

## 🚀 Funcionalidades
- 📋 Cadastro de clientes (nome, telefone).  
- 📅 Registro de atendimentos para cada cliente.  
- 📊 Interface gráfica **Swing** com:
  - Tabela de clientes
  - Tabela de atendimentos
  - Botões para cadastro e registro de atendimentos
  - Relatório financeiro automático
- 🔎 Listagem de clientes e atendimentos.  
- 💾 Armazenamento em arquivos (`clientes.txt` e `atendimentos.txt`).  


## 💻 Interface Gráfica
- Janela principal com:
- Botões no topo: Cadastrar Cliente, Registrar Atendimento, Relatório Financeiro
- Tabela de clientes (nome, telefone)
- Tabela de atendimentos (cliente, data, serviço, valor, comissão)
- Formulários em JOptionPane para cadastro e registro
- Relatório financeiro automático em caixa de diálogo scrollável

------------------------------------------
### ✨ Autor
# Jefferson França Teodoro
🔗 [LinkedIn](https://www.linkedin.com/in/jefferson-fran%C3%A7a-teodoro-6258ba215/)
🔗 [GitHub](https://github.com/JeffersonTeodoro)

---

## 🛠 Estrutura do Projeto

-----
ProjetoPodologia/
│
├── src/ # Código-fonte Java
│ ├── application/ # Classe principal que inicia o programa
│ │ └── Programa.java # Classe principal (menu)
│ │
│ ├── data/ # Arquivos de persistência
│ │ ├── clientes.txt # Armazena os clientes
│ │ └── atendimentos.txt # Armazena os atendimentos
│ │
│ ├── model/ # Classes de modelo de dados
│ │ ├── Cliente.java # Modelo do cliente
│ │ └── Atendimento.java # Modelo do atendimento
│ │
│ ├── service/ # Lógica de negócio / serviços
│ │ └── AtendimentoService.java # Regras de negócio e manipulação de clientes/atendimentos
│ │
│ ├── util/ # Utilitários gerais
│ │ └── FileManager.java # Manipulação de arquivos e relatórios
│ │
│ └── view/ # Interface gráfica (Swing)
│ ├── MainView.java # Janela principal com menu e tabelas
│ ├── JanelaFinanceiro.java # Tela com relatório financeiro
│ ├── TelaAtendimentos.java # Tela de registro e listagem de atendimentos
│ ├── TelaClientes.java # Tela de cadastro e listagem de clientes
│ └── TelaPrincipal.java # Tela inicial ou painel principal do app
│
└── README.md # Documentação do projeto


---

## ▶️ Como Rodar o Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/SEU_USUARIO/ProjetoPodologia.git
cd ProjetoPodologia

2. Compilar o código
javac -d bin src\application\Programa.java

3. Executar o sistema
java -cp bin application.Programa

💻 Exemplo de Uso
==== SISTEMA DE PODOLOGIA ====
1. Cadastrar Cliente
2. Listar Clientes
3. Registrar Atendimento
4. Listar Atendimentos
0. Sair

👉 Exemplo de fluxo:
Nome: Maria Silva
Telefone: (21) 98888-7777
Email: maria@email.com
✅ Cliente cadastrado com sucesso!

=== Lista de Clientes ===
0 - Cliente: Maria Silva | Telefone: (21) 98888-7777 | Email: maria@email.com

-------------------------
📌 Tecnologias
☕ Java 17
📂 Arquivos TXT para persistência

