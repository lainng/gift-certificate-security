## MJC school

### Module #4 - Authentication & Spring Security

This is the last module, which includes the previous two modules.  
[Module #3](https://github.com/lainng/gift-certificate-boot) - REST API Advanced.  
[Module #2](https://github.com/lainng/gift-sertificate) - REST API Basics.

### *Task*

This module is an extension of REST API Advanced module and covers following topics:

- Spring Security framework
- Oauth2 and OpenId Connect
- JWT token

Application requirements
- Spring Security should be used as a security framework.
- Application should support only stateless user authentication and verify integrity of JWT token.
- Users should be stored in a database with some basic information and a password.
- Get acquainted with the concepts Oauth2 and OpenId Connect</li>
- (Optional task) Use Oauth2 as an authorization protocol.
    - OAuth2 scopes should be used to restrict data.
    - Implicit grant and Resource owner credentials grant should be implemented.
- (Optional task) It's allowed to use Spring Data. Requirement for this task - all repository (and existing ones) should be migrated to Spring Data.

User Permissions:
- Guest:
  - Read operations for main entity.
  - Signup.
  - Login.
- User:
  - Make an order on main entity.
  - All read operations.
- Administrator (can be added only via database call):
  - All operations, including addition and modification of entities.