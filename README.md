# Spring Security With JWT Implementation


### JwtUtils

This **JwtUtils** class provides utility methods for working with **JSON Web Tokens (JWT)** in a Spring application. It allows for generating JWT tokens from user details, extracting tokens from HTTP request headers, retrieving usernames from tokens, and validating token authenticity and expiration. The class uses a secret key loaded from the application's configuration to sign and verify tokens, ensuring secure authentication and authorization.

### JwtFilter

This **JwtFilter** class is a Spring security filter that authenticates incoming HTTP requests by verifying the presence and validity of a JSON Web Token (JWT) in the request header. It uses the **JwtUtils** class to extract the JWT from the header, validate its authenticity, and retrieve the username from the token. If the token is valid, it loads the corresponding user details using the **UserDetailsServiceImpl** and creates a **UsernamePasswordAuthenticationToken** to authenticate the user. The authenticated user is then set in the **SecurityContextHolder**, allowing subsequent filters and controllers to access the authenticated user's details.

### AuthEntryPointJwt

This **AuthEntryPointJwt** class is a Spring security component that handles unauthorized requests by returning a JSON response with an HTTP status code of 401 Unauthorized. It implements the **AuthenticationEntryPoint** interface, which allows it to be invoked when a user tries to access a protected resource without proper authentication. The **commence** method is called when an unauthorized request is encountered. It logs the error, sets the response status and content type, and writes a JSON response body containing the status, error message, and path of the unauthorized request. This response is then sent back to the client.

### LoginController

This **LoginController** class is a Spring REST controller that handles user authentication requests. It exposes a **/signin** endpoint that accepts a **LoginRequest** object containing the username and password. The controller uses the **AuthenticationManager** to authenticate the user credentials, and if successful, generates a JSON Web Token (JWT) using the **JwtUtils** class. The JWT is then returned in a **LoginResponse** object along with the user's username and roles. If the authentication fails, the controller returns a response with a "Bad credentials" message and a 404 status code. The authenticated user's details are also stored in the **SecurityContextHolder** for subsequent requests.

## SecurityConfig

This **SecurityConfig** class is a Spring configuration class that sets up the security configuration for a Spring application. It defines the security rules for different URL paths and roles. It configures the **JwtFilter** to authenticate incoming requests and the **AuthEntryPointJwt** to handle unauthorized requests. It also sets up the **authenticationManager** bean to handle user authentication using the **DaoAuthenticationProvider** and **BCryptPasswordEncoder**. The **filterChain** method configures the security filter chain, disabling CSRF protection, using stateless session management, and setting the **authenticationEntryPoint** to the **unauthorizedHandler**. The configuration also allows access to the H2 console and the **/signin** endpoint for all users.

### UserDetailsServiceImpl

This **UserDetailsServiceImpl** class is a Spring service that implements the **UserDetailsService** interface, which is responsible for loading user details from a data store. It uses the **UserRepository** to retrieve a user by their username and then creates a **UserDetails** object, which is a Spring Security class that represents a user's details. The **loadUserByUsername** method is overridden to load the user's details, including their username, password, and authorities (roles), and returns a **UserDetails** object. The **getAuthorities** method is used to convert the user's role to a set of granted authorities, which are used by Spring Security to determine the user's permissions. If the user is not found, it throws a **UsernameNotFoundException**.
