<%@ page import="java.util.List" %>
<%@ page import="com.rometools.rome.feed.synd.SyndEntry" %>
<%@ page import="edu.gavrilov.rss.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored ="false" %>


<html>
<head>

    <title>RSS Reader</title>

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

        a:visited { color: #8a8582; }

        a:hover { color: #000;
            text-decoration: none;}

        a {
            text-decoration: none;
            color: black;
        }

    </style>

</head>

<body link="black">

    <input type="checkbox" id="nav-toggle" hidden>

    <nav class="nav">
        <label for="nav-toggle" class="nav-toggle" onclick></label>
        <h2 class="logo">
            <a href=http://localhost:8080/">Моя лента</a>
        </h2>
        <ul>
            <li><a href="/">  Моя лента</a>
            <li><a href="/mychannels">  Мои каналы</a>
            <li><a href="#3">  Мой аккаунт</a>
            <li><a href="#2">  О сайте</a>
        </ul>
    </nav>


    <main role="main">
        <article>
        <div class = "container-fluid">
            <div class="row">
                <div class="col-md-3 col-sm-1"></div>
                <div class="col-md-6 col-sm-10">
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
                    <c:forEach items = "${news}" var = "item">
                    <article class = "blog_post">
                    <div class="row container-fluid">
                        <div class = "col-md-4 div-img">
                            <a href="${item.url}" target="_blank"><img class = "pic_margin img-thumbnail" src="${item.picture}" onerror = "this.src = '/resources/icons/npa.jpg'" /></a>
                        </div>
                        <div class = "col-md-8">
                            <a href="${item.url}" target="_blank"><h6><b>${item.title}</b></h6></a>
                            <p><h6>
                            <small>
                                <c:out value="${item.description}"/>
                            </small>
                        </h6>
                            </p>
                        </div>
                    </div>
                    </article>
                    </c:forEach>
                </div>
                <div class="col-md-3 col-sm-1"></div>
            </div>
        </div>
        </article>
    </main>

</body>
</html>
