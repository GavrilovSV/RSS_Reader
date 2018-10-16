<%@ page import="java.util.List" %>
<%@ page import="com.rometools.rome.feed.synd.SyndEntry" %>
<%@ page import="edu.gavrilov.rss.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <title>RSS Reader</title>

    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <!-- Google Web Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,900&amp;subset=cyrillic" rel="stylesheet">

    <style>

        /*search form*/

        fieldset {
            position: relative;
        }

        .fa-search{
            position: relative;
            top: 0px;
            font-size: 20px !important;
        }

        .url{
            margin: 20px 50px;
            -webkit-box-flex: 5;
            -ms-flex-positive: 5;
            flex-grow: 5;
        }


        label {
            position: absolute;
            top: -5px;
            left: 0px;
            letter-spacing: 5px;
            font-size: 20px;
            color: RGBA(/*55, 73, 87,*/ 0, 0, 0, 0.8);
            -webkit-transform-origin: left;
            -ms-transform-origin: left;
            transform-origin: left;
            -webkit-transition: all 0.3s ease;
            -o-transition: all 0.3s ease;
            transition: all 0.3s ease;
        }

        input:focus ~ label {
            color: RGBA(0, 0, 0, 1.00);
        }

        input:focus ~ label,
        input:valid ~ label {
            top: 10px;
            letter-spacing: 5px;
            -webkit-transform: translate(0, -30px) scale(0.7, 0.7);
            -ms-transform: translate(0, -30px) scale(0.7, 0.7);
            transform: translate(0, -30px) scale(0.7, 0.7);

        }

        i {
            font-family: fontawesome !important;
        }

        input {
            font-family: 'Source Sans Pro', sans-serif;
            font-size: 20px !important;
            font-style: normal;
            font-weight: 400;
            width: 100%;
            border: none;
            margin-top: 0;
            background-color: transparent;
        }

        input:focus {
            outline: none;
        }

        .after {
            width: 100%;
            height: 1.5px;
            background-color: RGBA(0, 0, 0, 0.5);
            background-size: 200% 100%;
            background-position: 100% 0;
            -webkit-transition: all 0.3s ease;
            -o-transition: all 0.3s ease;
            transition: all 0.3s ease;
        }

        input:focus ~ .after {
            background-position: 0 0;
        }

        .search_form {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: horizontal;
            -webkit-box-direction: normal;
            -ms-flex-direction: row;
            flex-direction: row;
        }

        /*search form*/



        img {
            max-width:100%;
            height:auto;
            vertical-align: middle;
        }

        a:visited { color: #8a8582; }

        a:hover { color: #000;
            text-decoration: none;}


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

        body {
            background-color: #eeeeee;
        }

        .blog_post {
            clear: both; padding: 15px 0; overflow:auto;
            margin-bottom: 20px;
            background:#FFF; box-shadow: 0 1px 3px rgba(34, 25, 25, 0.4)
        }


        .blog_post:hover, .blog_post:focus, .blog_post:active {
            box-shadow: 0 10px 10px -10px rgba(0, 0, 0, 0.5);
            -webkit-transform: scale(1.03);
            transform: scale(1.03);
        }


        /**
         * Переключаемая боковая панель навигации
         * выдвигающаяся по клику слева
         */

        .nav {
            /*  ширна произвольная, не стесняйтесь экспериментировать */
            width: 320px;
            min-width: 320px;
            /* фиксируем и выставляем высоту панели на максимум */
            height: 100%;
            position: fixed;
            top: 0;
            bottom: 0;
            margin: 0;
            /* сдвигаем (прячем) панель относительно левого края страницы */
            left: -320px;
            /* внутренние отступы */
            padding: 15px 20px;
            /* плавный переход смещения панели */
            -webkit-transition: left 0.3s;
            -moz-transition: left 0.3s;
            transition: left 0.3s;
            /* определяем цвет фона панели */
            background: #16a085;
            /* поверх других элементов */
            z-index: 2000;
        }


        /**
         * Кнопка переключения панели
         * тег <label>
         */

        .nav-toggle {
            /* абсолютно позиционируем */
            position: absolute;
            /* относительно левого края панели */
            left: 320px;
            /* отступ от верхнего края панели */
            top: 1em;
            /* внутренние отступы */
            padding: 0.5em;
            /* определяем цвет фона переключателя
             * чаще вчего в соответствии с цветом фона панели
            */
            background: inherit;
            /* цвет текста */
            color: #dadada;
            /* вид курсора */
            cursor: pointer;
            /* размер шрифта */
            font-size: 1.2em;
            line-height: 1;
            /* всегда поверх других элементов страницы */
            z-index: 2001;
            /* анимируем цвет текста при наведении */
            -webkit-transition: color .25s ease-in-out;
            -moz-transition: color .25s ease-in-out;
            transition: color .25s ease-in-out;
        }


        /* определяем текст кнопки
         * символ Unicode (TRIGRAM FOR HEAVEN)
        */

        .nav-toggle:after {
            content: '\2630';
            text-decoration: none;
        }


        /* цвет текста при наведении */

        .nav-toggle:hover {
            color: #f4f4f4;
        }


        /**
         * Скрытый чекбокс (флажок)
         * невидим и недоступен :)
         * имя селектора атрибут флажка
         */

        [id='nav-toggle'] {
            position: absolute;
            display: none;
        }


        /**
         * изменение положения переключателя
         * при просмотре на мобильных устройствах
         * когда навигация раскрыта, распологаем внутри панели
        */

        [id='nav-toggle']:checked ~ .nav > .nav-toggle {
            left: auto;
            right: 2px;
            top: 1em;
        }


        /**
         * Когда флажок установлен, открывается панель
         * используем псевдокласс:checked
         */

        [id='nav-toggle']:checked ~ .nav {
            left: 0;
            box-shadow:4px 0px 20px 0px rgba(0,0,0, 0.5);
            -moz-box-shadow:4px 0px 20px 0px rgba(0,0,0, 0.5);
            -webkit-box-shadow:4px 0px 20px 0px rgba(0,0,0, 0.5);
            overflow-y: auto;
        }


        /*
         * смещение контента страницы
         * на размер ширины панели,
         * фишка необязательная, на любителя
        */

        [id='nav-toggle']:checked ~ main > article {
            -webkit-transform: translateX(160px);
            -moz-transform: translateX(160px);
            transform: translateX(160px);
        }


        /*
         * изменение символа переключателя,
         * привычный крестик (MULTIPLICATION X),
         * вы можете испльзовать любой другой значок
        */

        [id='nav-toggle']:checked ~ .nav > .nav-toggle:after {
            content: '\2715';
        }


        /**
         * профиксим баг в Android <= 4.1.2
         * см: http://timpietrusky.com/advanced-checkbox-hack
         */

        body {
            -webkit-animation: bugfix infinite 1s;
        }

        @-webkit-keyframes bugfix {
            to {
                padding: 0;
            }
        }


        /**
         * позаботьтимся о средних и маленьких экранах
         * мобильных устройств
         */

        @media screen and (min-width: 320px) {
            html,
            body {
                margin: 0;
                overflow-x: hidden;
            }
        }

        @media screen and (max-width: 320px) {
            html,
            body {
                margin: 0;
                overflow-x: hidden;
            }
            .nav {
                width: 100%;
                box-shadow: none
            }
        }


        /**
         * Формируем стиль заголовка (логотип) панели
        */

        .nav h2 {
            width: 90%;
            padding: 0;
            margin: 10px 0;
            text-align: center;
            text-shadow: rgba(255, 255, 255, .1) -1px -1px 1px, rgba(0, 0, 0, .5) 1px 1px 1px;
            font-size: 1.3em;
            line-height: 1.3em;
            opacity: 0;
            transform: scale(0.1, 0.1);
            -ms-transform: scale(0.1, 0.1);
            -moz-transform: scale(0.1, 0.1);
            -webkit-transform: scale(0.1, 0.1);
            transform-origin: 0% 0%;
            -ms-transform-origin: 0% 0%;
            -moz-transform-origin: 0% 0%;
            -webkit-transform-origin: 0% 0%;
            transition: opacity 0.8s, transform 0.8s;
            -ms-transition: opacity 0.8s, -ms-transform 0.8s;
            -moz-transition: opacity 0.8s, -moz-transform 0.8s;
            -webkit-transition: opacity 0.8s, -webkit-transform 0.8s;
        }

        .nav h2 a {
            color: #dadada;
            text-decoration: none;
            text-transform: uppercase;
        }


        /*плавное появление заголовка (логотипа) при раскрытии панели */

        [id='nav-toggle']:checked ~ .nav h2 {
            opacity: 1;
            transform: scale(1, 1);
            -ms-transform: scale(1, 1);
            -moz-transform: scale(1, 1);
            -webkit-transform: scale(1, 1);
        }


        /**
         * формируем непосредственно само меню
         * используем неупорядоченный список для пунктов меню
         * прикрутим трансфомации и плавные переходы
         */

        .nav > ul {
            display: block;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .nav > ul > li {
            line-height: 2.5;
            opacity: 0;
            -webkit-transform: translateX(-50%);
            -moz-transform: translateX(-50%);
            -ms-transform: translateX(-50%);
            transform: translateX(-50%);
            -webkit-transition: opacity .5s .1s, -webkit-transform .5s .1s;
            -moz-transition: opacity .5s .1s, -moz-transform .5s .1s;
            -ms-transition: opacity .5s .1s, -ms-transform .5s .1s;
            transition: opacity .5s .1s, transform .5s .1s;
        }

        [id='nav-toggle']:checked ~ .nav > ul > li {
            opacity: 1;
            -webkit-transform: translateX(0);
            -moz-transform: translateX(0);
            -ms-transform: translateX(0);
            transform: translateX(0);
        }


        /* определяем интервалы появления пунктов меню */

        .nav > ul > li:nth-child(2) {
            -webkit-transition: opacity .5s .2s, -webkit-transform .5s .2s;
            transition: opacity .5s .2s, transform .5s .2s;
        }

        .nav > ul > li:nth-child(3) {
            -webkit-transition: opacity .5s .3s, -webkit-transform .5s .3s;
            transition: opacity .5s .3s, transform .5s .3s;
        }

        .nav > ul > li:nth-child(4) {
            -webkit-transition: opacity .5s .4s, -webkit-transform .5s .4s;
            transition: opacity .5s .4s, transform .5s .4s;
        }

        .nav > ul > li:nth-child(5) {
            -webkit-transition: opacity .5s .5s, -webkit-transform .5s .5s;
            transition: opacity .5s .5s, transform .5s .5s;
        }

        .nav > ul > li:nth-child(6) {
            -webkit-transition: opacity .5s .6s, -webkit-transform .5s .6s;
            transition: opacity .5s .6s, transform .5s .6s;
        }

        .nav > ul > li:nth-child(7) {
            -webkit-transition: opacity .5s .7s, -webkit-transform .5s .7s;
            transition: opacity .5s .7s, transform .5s .7s;
        }


        /**
         * оформление ссылок пунктов меню
         */

        .nav > ul > li > a {
            display: inline-block;
            position: relative;
            padding: 0;
            font-family: 'Open Sans', sans-serif;
            font-weight: 300;
            font-size: 1.2em;
            color: #dadada;
            width: 100%;
            text-decoration: none;
            /* плавный переход */
            -webkit-transition: color .5s ease, padding .5s ease;
            -moz-transition: color .5s ease, padding .5s ease;
            transition: color .5s ease, padding .5s ease;
        }


        /**
         * состояние ссылок меню при наведении
         */

        .nav > ul > li > a:hover,
        .nav > ul > li > a:focus {
            color: white;
            padding-left: 15px;
        }


        .nav > ul > li > a:before {
            content: '';
            display: block;
            position: absolute;
            right: 0;
            bottom: 0;
            height: 1px;
            width: 100%;
            -webkit-transition: width 0s ease;
            transition: width 0s ease;
        }

        .nav > ul > li > a:after {
            content: '';
            display: block;
            position: absolute;
            left: 0;
            bottom: 0;
            height: 1px;
            width: 100%;
            background: #3bc1a0;
            -webkit-transition: width .5s ease;
            transition: width .5s ease;
        }


        /**
         * анимируем линию подчеркивания
         * ссылок при наведении
         */

        .nav > ul > li > a:hover:before {
            width: 0%;
            background: #3bc1a0;
            -webkit-transition: width .5s ease;
            transition: width .5s ease;
        }

        .nav > ul > li > a:hover:after {
            width: 0%;
            background: transparent;
            -webkit-transition: width 0s ease;
            transition: width 0s ease;
        }


        a {
            text-decoration: none;
            color: black;
        }

        .pic_margin {
            border-radius: 10px;
            margin-right: 20px;
            /*width: 150px;
            height: 95px;*/
        }

        .news_merging {
            margin-top: 70px;
            padding-left: 50px;
            padding-right: 100px;
            text-align: justify-all;
        }

        .sidenav {
            height: 100%; /* Full-height: remove this if you want "auto" height */
            width: 250px; /* Set the width of the sidebar */
            position: fixed; /* Fixed Sidebar (stay in place on scroll) */
            z-index: 1; /* Stay on top */
            top: 0; /* Stay at the top */
            left: 0;
            background-color: #4c514f;
            overflow-x: hidden; /* Disable horizontal scroll */
            padding-top: 20px;
        }

        /* The navigation menu links */
        .sidenav a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 25px;
            color: #818181;
            display: block;
        }

        /* When you mouse over the navigation links, change their color */
        .sidenav a:hover {
            color: #f1f1f1;
        }
    </style>

</head>
<body link="black">

    <input type="checkbox" id="nav-toggle" hidden>


    <nav class="nav">
        <!--
    Метка с именем `id` чекбокса в `for` атрибуте
    Символ Unicode 'TRIGRAM FOR HEAVEN' (U+2630)
    Пустой атрибут `onclick` используем для исправления бага в iOS < 6.0
    См: http://timpietrusky.com/advanced-checkbox-hack
    -->
        <label for="nav-toggle" class="nav-toggle" onclick></label>
        <!--
    Здесь размещаете любую разметку,
    если это меню, то скорее всего неупорядоченный список <ul>
    -->
        <h2 class="logo">
            <a href=http://localhost:8080/">RSS Reader</a>
        </h2>
        <ul>
            <li><a href="#1">Мои каналы</a>
            <li><a href="#3">Мой аккаунт</a>
            <li><a href="#2">О сайте</a>
        </ul>
    </nav>


    <main role="main">
        <article>
        <div class = "container-fluid">

            <div class="row">

                <div class="col-md-3"></div>

                <div class="col-md-6">
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

                            <a href="${item.url}" target="_blank"><img class = "pic_margin img-thumbnail" src="${item.picture}" /></a>

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
                <div class="col-md-3"></div>
            </div>
        </div>
        </article>
    </main>

</body>
</html>
