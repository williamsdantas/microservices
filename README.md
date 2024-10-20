# Pós-graduação em Desenvolvimento Web Full-Stack

**Disciplina:** Microserviços e APIs</br>
**Professor:** Thiago Rodrigues - southiagorm@gmail.com</br>


## Projeto 1

**Aluno:** Williams Alves Dantas  </br>
**E-mail:** williamsad9@gmail.com  </br>
**RGM:** 38441381  </br>

Criar um endpoint (rota) para usuários - /users
- Registrar (Cadastrar no banco)
- Buscar Usuário por Id
- Atualizar Usuário

Sugestão para fluxo de implementação
• Implementar Model
• Implementar Repository
• Implementar Dto
• Implementar Service
• Implementar Controller
o As três rotas descritas anteriormente

Propriedades para um model do usuário
• (id, nome, email, senha, telefone, endereço)

## Projeto Final
**Participantes:**
<table style="width:100%">
  <tr>
    <th>Matricula</th>
    <th>Nome</th>
    <th>E-mail</th>
  </tr>
  <tr>
    <td>38601648</td>
    <td>Joabe da Silva Senas</td>
    <td>joasen99@gmail.com</td>
  </tr>
  <tr>
    <td>38889854</td>
    <td>Lucas Farias Costa</td>
    <td>lucasfarias14@outlook.com</td>
  </tr>
  <tr>
    <td>38441381</td>
    <td>Williams Alves Dantas</td>
    <td>williamsad9@gmail.com</td>
  </tr>
  
</table>


**Objetivo:** 
Criar um sistema com as boas práticas de microsserviços, a partir dos requisitos
abaixo:

<p>Crie uma api para gerenciar (CRUD) os produtos e outra para gerenciar os
pedidos. Obs. Leve em consideração que cada pedido tem apenas um
produto.</p>
<p>• Criar um api gateway e um sistema de serviço de registro (service registry) e
serviço de descoberta (service discovery) para o ecossistema e
comunicação dos serviços.</p>
<p>• Os serviços de pedido e produtos devem ser divididos nos pacotes e
camadas, model, repository, dto, service e controller.</p>
<p>• Quando um novo pedido for ser criado, verificar se existe produto
disponível para criar o pedido, para isso a api de pedidos deverá se
comunicar com a api de produtos. Utilize o openfeign para realizar a
comunicação entre os microsserviços de pedido e produto.</p>

# Fluxo:
<p>▪ O cliente envia uma requisição para o Microservice Pedidos
para criar um novo pedido.
<p>▪ O Microservice de Pedidos valida os dados do pedido e consulta o Microservice Produtos para verificar a
disponibilidade dos itens. (Verifique a disponibilidade pela
propriedade quantidade do produto)</p>

<p> Se os itens estiverem disponíveis (quantidade do produto for maior
que zero), diminua a quantidade de produto no banco de dados de
produto e o Microservice de Pedidos persiste o pedido no banco de
dados com o status "pendente". (Dica o endpoint de produtos realiza
a lógica e retorna true ou false dependendo da quantidade de
produtos).</p>

<h2>Microserviço de Produtos(api.produtos)</h2>

<p>O microserviço de Produtos é responsável por gerenciar as operações relacionadas aos produtos disponíveis no sistema. As principais funcionalidades incluem:</p>

<ul>
    <li>
        <strong>Buscar Produto por ID:</strong> 
        Permite recuperar as informações de um produto específico através do seu ID.
    </li>
    <li>
        <strong>Listar Produtos Disponíveis:</strong> 
        Retorna uma lista paginada de todos os produtos cadastrados no sistema.
    </li>
    <li>
        <strong>Cadastrar Novo Produto:</strong> 
        Permite a criação de novos produtos, recebendo os dados no formato <code>ProdutoDto</code>.
    </li>
    <li>
        <strong>Reservar Produto:</strong> 
        Valida a disponibilidade de um produto e reserva uma quantidade solicitada, atualizando a quantidade disponível.
    </li>
    <li>
        <strong>Atualizar Produto:</strong> 
        Permite a atualização dos dados de um produto existente com base no seu ID.
    </li>
    <li>
        <strong>Deletar Produto:</strong> 
        Remove um produto do sistema, garantindo que o produto existe antes de realizar a exclusão.
    </li>
</ul>

<h3>Exceções</h3>
<p>O microserviço utiliza a exceção personalizada <code>EstoqueInsuficienteException</code> para lidar com casos de produtos não encontrados ou quando a quantidade solicitada para reserva é insuficiente.</p>

<h3>Dependências</h3>
<p>Este microserviço é construído com Spring Boot e utiliza JPA para interação com o banco de dados, garantindo operações transacionais e uma abordagem limpa para persistência de dados.</p>
