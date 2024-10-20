# Pós-graduação em Desenvolvimento Web Full-Stack

**Disciplina:** Microserviços e APIs</br>
**Professor:** Thiago Rodrigues - southiagorm@gmail.com</br>
**Aluno:** Williams Alves Dantas  </br>
**E-mail:** williamsad9@gmail.com  </br>
**RGM:** 38441381  </br>

## Projeto 1

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

## Projeto 2
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


