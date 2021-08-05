## API Desafio Muxi

Esta API foi desenvolvida utilizando Java, pois tenho bastante conhecimento sobre o framework spring e JUnit. Eu poderia ter desenvolvido com Python utilizando flask, contudo não tenho experiência com testes unitários em python, logo eu iria levar mais tempo para desenvolver essa API. 

# Requirements

Para buildar e rodar você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.3](https://maven.apache.org)

# Rodando a aplicação localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `br.com.muxi.desafio.DesafioMuxiApiApplication` de seu IDE.

Alternativamente, você pode usar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

# Documentação

Para acessar a documentação localhost acesse o link: http://localhost:5000/swagger-ui.html#/

Se estiver apontando para o Heroku acesse: http://localhost:5000/swagger-ui.html#/


# Outras informações

Estou usando o banco em memória h2 - http://localhost:5000/h2

O projeto também está pronto para utilizar o Flyway para controlá versionamento de script sql;
	

# Direito autoral

Lançado sob o Apache License 2.0. Ver o [LICENSE](https://github.com/fifa1988/desafio-muxi-api/blob/main/LICENSE) arquivo.

	