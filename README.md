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
		
## O fluxo de funcionamento do Spring Security
##### As classes mencionadas aqui serão descritas posteriormente
Imaginemos que um usuário faz uma requisição para a aplicação. Esta cairá no FilterChain e será examinada para ver se é necessário um token, ou seja, se a página solicitada requer um token de autenticação válido. Caso seja, a requisição será encaminhada para a classe [JwtAuthFilter.java](https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtAuthFilter.java), a qual checará primeiramente o conteúdo da requisição e a existência do token. Se o token ou o cadastro requerido não for encontrado, a passagem do usuário será negada com um erro 403. Caso não seja necessário um token de autenticação (como por exemplo páginas de registro e autenticação), a requisição passará para o UserDetailsService, que registrará o novo cadastro na base de dados ou buscará o usuário existente. Se os dados para registro forem válidos ou o usuário for encontrado, [JwtService.java](https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtService.java) gerará um token que será entregue como resposta da requisição. Tendo o token válido, poderá ser feita uma requisição para um endereço protegido, como por exemplo para os métodos do [DemoController.java](https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/demo/DemoController.java). Esta requisição irá então para o [JwtAuthFilter.java](https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtAuthFilter.java) que realizará a checagem dos dados e token e atualizará o SecurityContextHolder, que permitirá a passagem do usuário para o endereço solicitado, ativando assim os métodos do controller e retornando a devida resposta.

## Classes criadas e anotações utilizadas
### Classes
<ul>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/SpringSecurityPracticeApplication.java">SpringSecurityPracticeApplication.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/AppUser.java">AppUser.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/Role.java">Role.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/UserRepo.java">UserRepo.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtAuthFilter.java">JwtAuthFilter.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtService.java">JwtService.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/config/ApplicationConfig.java">ApplicationConfig.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/config/SecurityConfig.java">SecurityConfig.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/RegisterRequest.java">RegisterRequest.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthRequest.java">AuthRequest.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthResponse.java">AuthResponse.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthService.java">AuthService.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthController.java">AuthController.java</a> - </li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/demo/DemoController.java">DemoController.java</a> - </li>
</ul>
