# 🦶 Projeto Podologia (Java) - Versão Profissional com Imagens

Um sistema em **Java** para gerenciamento de **clientes e atendimentos de podologia**, com **interface gráfica Swing** e **persistência em arquivos `.txt`**.

---

## 🚀 Funcionalidades

* 📋 Cadastro de clientes (nome, telefone).
* 📅 Registro de atendimentos para cada cliente.
* 🖥 Interface gráfica Swing com:

  * Tabela de clientes
  * Tabela de atendimentos
  * Botões para cadastro e registro de atendimentos
  * Relatório financeiro automático
* 🔎 Listagem de clientes e atendimentos.
* 💾 Armazenamento em arquivos (`clientes.txt` e `atendimentos.txt`).

---

### ✨ Autor

# Jefferson França Teodoro

🔗 [LinkedIn](https://www.linkedin.com/in/jefferson-teodoro/)
🔗 [GitHub](https://github.com/JeffersonTeodoro)

---

## 🛠 Estrutura do Projeto

```
ProjetoPodologia/  🏠  # Diretório principal
│
├── src/  💻  # Código-fonte
│   ├── application/  🏗️  # Classe principal
│   │   └── Programa.java  ⚙️  # Menu principal
│   │
│   ├── data/  📂  # Persistência em arquivos
│   │   ├── clientes.txt  📝
│   │   └── atendimentos.txt  📝
│   │
│   ├── model/  🧩  # Modelos de dados
│   │   ├── Cliente.java  👤
│   │   └── Atendimento.java  💼
│   │
│   ├── service/  🔧  # Regras de negócio
│   │   └── AtendimentoService.java  📊
│   │
│   ├── util/  🛠️  # Utilitários
│   │   └── FileManager.java  💾
│   │
│   └── view/  🖥️  # Interface Swing
│       ├── MainView.java  🏠
│       ├── JanelaFinanceiro.java  💰
│       ├── TelaAtendimentos.java  📅
│       ├── TelaClientes.java  👥
│       └── TelaPrincipal.java  🏁
│
└── README.md  📖
```

---

## 📷 Screenshots das Telas

### Janela Principal

![MainView](docs/screenshots/mainview.png)

> Janela com botões de navegação e tabelas de clientes/atendimentos.

### Tela de Clientes

![TelaClientes](docs/screenshots/telaclientes.png)

> Formulário de cadastro de clientes com tabela de listagem.

### Tela de Atendimentos

![TelaAtendimentos](docs/screenshots/telaatendimentos.png)

> Registro de atendimentos com seleção de clientes e campos de valor e descrição.

### Relatório Financeiro

![JanelaFinanceiro](docs/screenshots/janelafinanceiro.png)

> Relatório financeiro automático, mostrando total recebido e comissão.

---

## ▶️ Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/JeffersonTeodoro/ProjetoPodologia.git
cd ProjetoPodologia
```

### 2. Compilar o código

```bash
javac -d bin src\application\Programa.java src\model\*.java src\service\*.java src\util\*.java src\view\*.java
```

### 3. Executar o sistema

```bash
java -cp bin application.Programa
```

---
## 💻 Interface Gráfica

- Janela principal com:
- Botões no topo: Cadastrar Cliente, Registrar Atendimento, Relatório Financeiro
- Tabela de clientes (nome, telefone)
- Tabela de atendimentos (cliente, data, serviço, valor, comissão)
- Formulários em JOptionPane para cadastro e registro
- Relatório financeiro automático em caixa de diálogo scrollável

## 📌 Tecnologias

* ☕ Java 17
* 🖥 Swing para interface gráfica
* 📂 Arquivos TXT para persistência
* 💡 Estrutura modular (MVC simplificado)
