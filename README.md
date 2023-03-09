# SpringSecurityPractice
Este projeto tem como objetivo estudar e praticar os conceitos e aplicações do Spring Security, tal como entender seu fluxo de funcionamento e meios de implementá-lo em um sistema. Para seu desenvolvimento, várias fontes foram consultadas, mas a principal foi o canal [Amigoscode](https://www.youtube.com/@amigoscode). Deixo aqui meu primeiro agradecimento para Amigoscode, obrigado.

## Configurações
SpringSecurityPractice foi desenvolvido com o Java 17 e uma base de dados MySQL, a qual foi configurada no [application.properties](https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/resources/application.properties). Segue o detalhamento para cada uma das configurações:
 <ul>
  <li>spring.datasource.url=jdbc:mysql://localhost:3306/amigoscode - indica a base de dados que será utilizada</li>
  <li>spring.datasource.username=root - indica o usuário para acessar a base de dados</li>
  <li>spring.datasource.password=12345678 -  indica a senha para acessar a base de dados</li>
  <li>spring.jpa.hibernate.ddl-auto=create-drop - faz com que os dados sejam descartados ao finalizar a aplicação</li>
  <li>spring.jpa.show-sql=true - mostra no console os comandos que o Hibernate manda para o MySQL</li>
  <li>spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect - configura o dialeto usado</li>
  <li>spring.jpa.properties.hibernate.format_sql=true - faz com que os comandos SQL mostrados sejam formatados</li>
 </ul>
 
 As dependências utilizadas e configuradas no [pom.xml](https://github.com/yuriverso/SpringSecurityPractice/blob/main/pom.xml) foram as seguintes:
 <ul>
 <li>Spring Data JPA</li>
 <li>Spring Security</li>
 <li>Validation</li>
 <li>Spring Web</li>
 <li>MySQL Driver</li>
 <li>Lombok</li>
 </ul>
 Além dessas adicionadas ao inicializar o projeto no <a href="https://start.spring.io/">Spring Initializr</a>, foram também adicionadas as dependências para manipulação de json web tokens e a dependência relacionada ao Swagger.
 
            <!-- jwt dependencies -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
		</dependency>
		<!-- swagger dependency -->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.0.2</version>
		</dependency>
