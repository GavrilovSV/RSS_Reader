<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
    <title>Мои каналы</title>

    <link rel="icon" href = "/resources/icons/g48.ico"/>
    <link href = "/resources/css/main.css" rel = "stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <!-- Google Web Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,900&amp;subset=cyrillic" rel="stylesheet">

    <style>

        @import url("https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css");

        body {
            background-color: #eeeeee;
        }

        .icon {
            font-size: 20px;
            opacity: 0.3;
            transition: all 0.4s ease;
        }

        .icon:hover {
            opacity: 1;
            transform: scale(1.3);
        }

        .div-img {
            display:flex;
            align-items:center;
            justify-content:center;
        }

        h6 {
            font-family: 'Source Sans Pro', sans-serif;
            font-style: normal;
            font-weight: 900;
        }

        p {
            font-family: 'Source Sans Pro', sans-serif;
            font-style: normal;
            line-height: 1;
            font-weight: 400;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {
            border-bottom: 1px solid #ccc;
        }

        li:last-child {
            border: none;
        }

        li{
            text-decoration: none;
            color: #000;

            -webkit-transition: all 0.3s ease;
            -moz-transition: all 0.3s ease;
            -o-transition: all 0.3s ease;
            -ms-transition: all 0.3s ease;
            transition: all 0.3s ease;
            display: block;
        }

        li:hover {
            transform: scale(1.05);
        }

        .footer_btn {
            width: 100px;
            height: 50px;
            position: fixed;
            background: green;
            left: 50%;
            bottom: 5%;

            transform: translate(-50%, -5%);
        }


        .footer {
            margin-left: auto;
            margin-right: auto;
            width: 200px;
            background-color: #eeeeee;
            height: 150px;
            position: fixed;
/*            opacity: 50%;
            position: fixed;
            left: 50%;
            bottom: 1%;
            transform: translate(-50%, 0);
            filter: blur(5px);
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -o-filter: blur(5px);
            -ms-filter: blur(5px);*/
        }



    </style>


</head>
<body>

    <input type="checkbox" id="nav-toggle" hidden>

    <nav class="nav">
        <label for="nav-toggle" class="nav-toggle" onclick></label>
        <h2 class="logo">
            <a href=http://localhost:8080/">Мои каналы</a>
        </h2>
        <ul>
            <li><a href="/">  Моя лента</a>
            <li><a href="/mychannels">  Мои каналы</a>
            <li><a href="#3">  Мой аккаунт</a>
            <li><a href="#2">  О сайте</a>
        </ul>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class = "col-md-3"></div>
            <div class = "col-md-6">

                <form action="#" autocomplete="off" class="search_form">
                    <fieldset class="url">
                        <input id="url" type="text" name="url" required>
                        <label for="url">
                            <i class="fa fa-search"></i>
                            Поиск
                        </label>
                        <div class="after"></div>
                    </fieldset>

                </form>

                <p/><p/>

              <ul >
                <c:forEach items="${channels}" var="channel">
                  <li>
                    <div class = "row container-fluid">
                        <div class = "col-md-3 div-img">
                            <img src = "${channel.icon}"/>
                        </div>
                    <div class = "col-md-8">
                        <p/><p/>
                        <h6><c:out value="${channel.title}"/></h6><p/>
                        <p><h6>
                        <small>
                            <c:out value="${channel.description}"/>
                        </small>
                        </h6>
                        </p>
                    </div>
                    <div class = "col-md-1 div-img">
                        <a href="/delete/${channel.id}"><i class="fas fa-times icon"></i></a>
                    </div>
                    </div>
                  </li>
                </c:forEach>
              </ul>
              <div class = "footer row container-fluid">
                  Добавить канал
              </div>

            </div>
            <div class = "col-md-3"></div>
        </div>
    </div>


</body>
</html>
