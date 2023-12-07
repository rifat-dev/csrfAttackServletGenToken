Простая защита от CSRF атаки при помощи генерации CSRF-токена, который отправляется вместе с запросом и ассоциируется с HTTP сессией пользователя.

## Структура работы в виде дерева:
```
./src
└── main
    ├── java
    │   └── com
    │       └── rifat
    │           └── app
    │               ├── CsrfTokenGenerator.java
    │               ├── LoginServlet.java
    │               ├── LogoutServlet.java
    │               ├── User.java
    │               ├── UserPageServlet.java
    │               └── UserRepository.java
    └── webapp
        ├── login.jsp
        └── userPage.jsp
```

Saint Petersburg December 2023