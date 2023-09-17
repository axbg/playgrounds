The application can be started in: 
- client mode 
  - will use the Authentication Code Flow with PKCE
- resource-server mode 
  - will validate incoming JWTs and extract roles
  - can be used together with the [oauth-client](https://github.com/axbg/playgrounds/tree/main/javascript/angular/oauth-client) Angular application

It starts by default in resource-server mode.
To start it in client mode, you need to add the "client" profile. 

Keycloak was used as the Identity Provider.
