<h2 align="center">MJC school : EPAM Systems<br/>Module #4. Authentication & Spring Security</h2>

<h3>Materials</h3>
<ol dir="auto">
<li><a href="https://spring.io/guides/topicals/spring-security-architecture" rel="nofollow">Spring Security Architecture</a></li>
<li><a href="https://spring.io/guides/gs/securing-web/" rel="nofollow">Securing a Web Application</a></li>
<li><a href="https://habr.com/ru/company/flant/blog/475942/" rel="nofollow">Иллюстрированное руководство по OAuth и OpenID Connect</a></li>
<li><a href="https://spring.io/guides/tutorials/spring-boot-oauth2/" rel="nofollow">Spring Boot and OAuth2</a></li>
<li><a href="https://jwt.io/introduction/" rel="nofollow">Introduction to JSON Web Tokens</a></li>
<li><a href="https://developer.okta.com/blog/2018/10/31/jwts-with-java" rel="nofollow">Tutorial: Create and Verify JWTs in Java</a></li>
<li><a href="https://developer.okta.com/blog/2018/10/16/token-auth-for-java" rel="nofollow">Simple Token Authentication for Java Apps</a></li>
</ol>

<h3>Task</h3>
<p dir="auto">This module is an extension of REST API Advanced module and covers following topics:</p>
<ol dir="auto">
<li>Spring Security framework</li>
<li>Oauth2 and OpenId Connect</li>
<li>JWT token</li>
</ol>
<p dir="auto">Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications. OAuth 2.0 is a security standard where you give one application permission to access your data in another application. The steps to grant permission, or consent, are often referred to as authorization or even delegated authorization. You authorize one application to access your data, or use features in another application on your behalf, without giving them your password. OpenID Connect (OIDC) is a thin layer that sits on top of OAuth 2.0 that adds login and profile information about the person who is logged in. JSON Web Tokens are JSON objects used to send information between parties in a compact and secure manner.</p>
<h4 dir="auto"><a id="user-content-application-requirements" class="anchor" aria-hidden="true" href="#application-requirements"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Application requirements</h4>
<ol dir="auto">
<li>Spring Security should be used as a security framework.</li>
<li>Application should support only stateless user authentication and verify integrity of JWT token.</li>
<li>Users should be stored in a database with some basic information and a password.</li>
</ol>
<p dir="auto">User Permissions:</p>
<div class="snippet-clipboard-content position-relative overflow-auto"><pre><code> - Guest:
    * Read operations for main entity.
    * Signup.
    * Login.
 - User:
    * Make an order on main entity.
    * All read operations.
 - Administrator (can be added only via database call):
    * All operations, including addition and modification of entities.
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value=" - Guest:
    * Read operations for main entity.
    * Signup.
    * Login.
 - User:
    * Make an order on main entity.
    * All read operations.
 - Administrator (can be added only via database call):
    * All operations, including addition and modification of entities." tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
  <ol start="4" dir="auto">
<li>Get acquainted with the concepts Oauth2 and OpenId Connect</li>
<li>(Optional task) Use Oauth2 as an authorization protocol.
a. OAuth2 scopes should be used to restrict data.
b. Implicit grant and Resource owner credentials grant should be implemented.</li>
<li>(Optional task) It's allowed to use Spring Data. Requirement for this task - all repository (and existing ones) should be migrated to Spring Data.</li>
</ol>
