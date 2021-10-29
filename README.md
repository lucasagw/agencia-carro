# agencia-carro
Aplicação que executa comandos em um banco de dados.

## Diagrams 

### Data Base



<img src="https://i.ibb.co/WnNTVpD/diagrama-agencia.png" alt="diagrama-agencia" border="0">


### JPA e Hibernate

JPA significa Java Persistence API. É uma especificação de uma "API Java para gerenciamento de persistência e mapeamento objeto/relacional em ambientes Java EE e Java SE".

JPA é apenas uma especificação, não há implementação. Pense no JPA como um conjunto de diretrizes que devem ser seguidas na implementação.

Hibernate é uma das implementações que segue essas diretrizes. Um dos benefícios de se utilizar JPA é a possibilidade de trocar de implementação, pois todas usam a mesma interface. Existem outras implementações além do Hibernate, como EclipseLink e TopLink.

Basicamente, o JPA une um conjunto de regras que permite que qualquer um possa implementar esta API, permitindo que possa usar esta implementação em qualquer projeto que utilize as interfaces JPA, garantindo assim uma certa compatibilidade entre diferentes implementações.

É aqui que entra o Hibernate. O Hibernate é uma implementação da especificação JPA.

Em teoria, se não estiver usando nenhum recurso exclusivo de alguma implementação (o que é relativamente comum), é possível trocar uma implementação por outra no seu projeto de forma transparente. Por exemplo, um mapeamento comum de entidade envolve anotações como @Column, @Table, @Id, etc, e todos eles fazem parte do pacote javax.persistence, comum a qualquer implementação JPA. Assim, uma troca do Hibernate pelo OpenJPA (por exemplo) ocorreria de forma transparente para a aplicação.
