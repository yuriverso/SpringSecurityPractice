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
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/SpringSecurityPracticeApplication.java">SpringSecurityPracticeApplication.java</a> - Contém o método principal (main), responsável por inicializar a aplicação</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/AppUser.java">AppUser.java</a> - Modelo de usuário utilizado para realizar operações e criar a tabela de usuários na base de dados</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/Role.java">Role.java</a> - Enumerador que contém os possíveis papéis para o usuário (ADMIN, USER)</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/appuser/UserRepo.java">UserRepo.java</a> - Repositório para manipular a base de dados</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtAuthFilter.java">JwtAuthFilter.java</a> - Filtro de autenticação da json web token recebida que será ativado uma vez por requisição</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/jwt/JwtService.java">JwtService.java</a> - Classe de serviço que contém os métodos para a checagem, manipulação e criação de jwts</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/config/ApplicationConfig.java">ApplicationConfig.java</a> - Classe de configuração que contém os Beans utilizados</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/config/SecurityConfig.java">SecurityConfig.java</a> - Classe de configuração que contém o Bean do SecurityFilterChain</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/RegisterRequest.java">RegisterRequest.java</a> - Classe modelo usada para obter dados de registro na requisição</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthRequest.java">AuthRequest.java</a> - Classe modelo usada para obter dados de autenticação na requisição</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthResponse.java">AuthResponse.java</a> - Classe modelo usada para retornar o token como resposta de uma requisição</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthService.java">AuthService.java</a> - Classe de serviço que contém os métodos de registro e autenticação de usuários</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/auth/AuthController.java">AuthController.java</a> - Classe de controle que contém os métodos que mapeiam as requisições relacionadas ao registro e autenticação de usuários</li>
<li><a href="https://github.com/yuriverso/SpringSecurityPractice/blob/main/src/main/java/springsecurity/SecurityPractice/demo/DemoController.java">DemoController.java</a> - Classe de controle que contém um método que mapeia requisições para um endpoint protegido, utilizada para testar a segurança da aplicação</li>
</ul>

### Anotações
<ul>
<li>@SpringBootApplication - utilizada na classe principal, reúne as anotações @EnableAutoConfiguration, @ComponentScan e @Configuration</li>
<li>@Entity - faz da classe uma tabela na base de dados</li>
<li>@Table - usada para especificar detalhes da tabela</li>
<li>@Id - indica o atributo que será a chave primária da tabela</li>
<li>@GeneratedValue - configura a forma como a chave primária irá gerar seus valores</li>
<li>@Enumerated - indicar para o Spring que se trata de um Enumerador</li>
<li>@RestController - indica que a classe será responsável por mapear requisições</li>
<li>@RequestMapping - indica o endereço que o controle irá tomar responsabilidade</li>
<li>@GetMapping - indica o endereço para uma requisição do tipo GET e o método que será ativado com ela</li>
<li>@PostMapping - indica o endereço para uma requisição do tipo POST e o método que será ativado com ela</li>
<li>@Service - utilizada para marcar classes que, com seus métodos, servirão os propósitos do negócio</li>
<li>@Configuration - indica que a classe possui configurações de Beans que serão utilizados</li>
<li>@Bean - utilizada para indicar ao spring que deve ser responsável por criar, manipular e destruir objetos</li>
<li>@EnableWebSecurity - habilita a segurança web do Spring</li>
<li>@Component - indica que a classe deve ser administrada pelo Spring</li>
<li>@Data - Lombok - cria getters, setters, toString, construtor e mais</li>
<li>@Builder - Lombok - auxilia a construção de objetos</li>
<li>@AllArgsConstructor - Lombok - cria um construtor com um argumento para cada campo da classe</li>
<li>@NoArgsConstructor - Lombok - cria um construtor sem argumentos</li>
<li>@RequiredArgsConstructor - Lombok - cria um construtor com todos os campos que tenham a palavra-chave final, não sendo necessário instanciá-las</li>
</ul>

## Agradecimentos
Agradeço à toda a comunidade Java por estar sempre ajudando e acompanhando o desenvolvimento de programadores menos experientes por meio de fórums, repostas, textos, vídeos e etc. Agradeço novamente ao canal [Amigoscode](https://www.youtube.com/@amigoscode) pelas instruções e conhecimentos.

Sigamos em frente!
