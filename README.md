# 🦶 Projeto Podologia (Java)

Um sistema simples em **Java** para gerenciamento de **clientes e atendimentos de podologia**, rodando no console e com **persistência em arquivos `.txt`**.

---

## 🚀 Funcionalidades
- 📋 Cadastro de clientes (nome, telefone, e-mail).  
- 📅 Registro de atendimentos para cada cliente.  
- 📂 Armazenamento em arquivos (`clientes.txt` e `atendimentos.txt`).  
- 🔎 Listagem de clientes e atendimentos já cadastrados.  

---

## 🛠 Estrutura do Projeto

ProjetoPodologia/
│── src/
│ └── application/
│ │ └── Programa.java # Classe principal (menu)
│ │
│ └── model/
│ │ ├── Cliente.java # Modelo do cliente
│ │ └── Atendimento.java # Modelo do atendimento
│ │
│ └── service/
│ │ └── AtendimentoService.java # Regras de negócio
│ │
│ └── util/
│ └── FileManager.java # Manipulação de arquivos
│
│── data/
│ ├── clientes.txt # Armazena os clientes
│ └── atendimentos.txt # Armazena os atendimentos
│
│── README.md


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

-------------------------
📌 Tecnologias
☕ Java 17
📂 Arquivos TXT para persistência

✨ Autor

✨ Autor

Jefferson França Teodoro
🔗 LinkedIn
🔗 GitHub
