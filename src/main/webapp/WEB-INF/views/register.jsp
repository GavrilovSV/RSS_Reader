<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>


<html>
<head>
    <title>Регистрация в системе</title>
    <link rel="icon" href = "/resources/icons/g48.ico"/>

    <style>

        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        .login-page {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
        }
        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            padding-top: 20px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #16a085;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3s ease;
            transition: all 0.3s ease;
            cursor: pointer;
        }
        .form button:hover,.form button:active,.form button:focus {
            background: #43A047;
        }
        .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
        }
        .form .message a {
            color: #4CAF50;
            text-decoration: none;
        }
        .form .register-form {
            display: none;
        }
        .container {
            position: relative;
            z-index: 1;
            max-width: 300px;
            margin: 0 auto;
        }
        .container:before, .container:after {
            content: "";
            display: block;
            clear: both;
        }
        .container .info {
            margin: 50px auto;
            text-align: center;
        }
        .container .info h1 {
            margin: 0 0 15px;
            padding: 0;
            font-size: 36px;
            font-weight: 300;
            color: #1a1a1a;
        }
        .container .info span {
            color: #4d4d4d;
            font-size: 12px;
        }
        .container .info span a {
            color: #000000;
            text-decoration: none;
        }
        .container .info span .fa {
            color: #EF3B3A;
        }
        body {
            background: #eeeeee; /* fallback for old browsers */
            /*            background: -webkit-linear-gradient(right, #76b852, #8DC26F);
                        background: -moz-linear-gradient(right, #76b852, #8DC26F);
                        background: -o-linear-gradient(right, #76b852, #8DC26F);
                        background: linear-gradient(to left, #76b852, #8DC26F);*/
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .errorMessage {
            color: red;
            font-size: 13px;
            text-align: left;
            margin-bottom: 5px;
        }

    </style>

    <script
            src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous">
    </script>

</head>
<body>

<div class="login-page">


    <div class="form">
        <form:form modelAttribute="registerForm" action="/register" method="post">
            <h2>Создайте аккаунт</h2>
            <form:errors path="username" cssClass="errorMessage"/>
            <form:input path = "username" placeholder = "Email"/>
            <form:errors path="password" cssClass="errorMessage"/>
            <form:password path="password" placeholder="Пароль"/>
            <form:errors path="confirmPassword" cssClass="errorMessage"/>
            <form:password path="confirmPassword" name = "confirmPassword" placeholder="Подтвердите пароль"/>
            <button type="submit" class="btn">Зарегистрироваться</button>
            <p class="message">Уже есть аккаунт? <a href="/login">Авторизируйтесь</a></p>
        </form:form>
    </div>
</div>


</body>
</html>
