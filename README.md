# Spring Boot + JWT + Roles

Proyecto de práctica con:
- Spring Security
- JWT
- Roles (ADMIN / USER)
- DTOs
- Validaciones personalizadas
- CRUD protegido

## Flujo
1. Login → genera token
2. Token se envía en Authorization Bearer
3. JwtFilter valida token
4. SecurityConfig autoriza por rol
