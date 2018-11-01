<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored ="false" %>

<html>
<head>
    <title>Мои каналы</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="icon" href = "/resources/icons/g48.ico"/>
    <link href = "/resources/css/main.css" rel = "stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- Google Web Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,900&amp;subset=cyrillic" rel="stylesheet">


    <style>

        @import url("https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css");

        a:hover { color: #000;
            text-decoration: none;}

        a {
            text-decoration: none;
            color: black;
        }


        .footer_background {
            position: absolute;
            height: 100%;
            width: 100%;
            background: #eeeeee;
            opacity: 0.8;
            z-index: 0;
            border-top: 1px solid #ccc;
        }

        .footer {

            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            position: fixed;
            width: 25%;
            height: 80px;
            bottom:0%;
            left:50%;
            transform: translate(-50%, 0);

        }

        .add_button {
            background-color: #16a085;
            border: none;
            color: #dadada;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 18px;
            margin: 4px 2px;
            cursor: pointer;
            z-index: 1;
/*            -webkit-transition: color .25s ease-in-out;
            -moz-transition: color .25s ease-in-out;
            transition: color .25s ease-in-out;*/
        }

        .add_button:hover {
            color: #f4f4f4;
        }

        @media screen and (max-width: 576px) {
            .footer {
                float: none;
                width: 100%;
            }
        }


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
            margin-bottom: 85px;
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

    </style>



</head>
<body>


        <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="Новый канал" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Новый канал</h4>
                        <button type="button" id = "close_btn" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
<%--                    <form action = "/addchannel" method="post" role="form">--%>
                        <div class="modal-body mx-3">

                            <div class="md-form mb-4">
                                <span id = "result">${error}</span>
                                <input type="text" id = "new_url" name="newUrl"/>
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </div>

                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button id="sbmt" class="btn btn-success" type="submit">Сохранить канал</button>
                        </div>
<%--
                    </form>
--%>

                </div>
            </div>
        </div>

        <div class="modal fade" id="aboutSiteForm" tabindex="-1" role="dialog" aria-labelledby="О сайте" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">О сайте</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">

                            <div class="md-form mb-4">
            <%--                    Разработчик - Гаврилов С.В.<p/>
                                БГУИР, ФНиДО, кафедра ПОИТ<p/>
                                4 курс, гр. 591052<p/><p/>
                                <h6>RSS-Reader</h6><p/>
                                Сайт разработан в рамках курсового проектирования по дисциплине КСиС<p/>
                                2018 год --%>
                            </div>

                        </div>
                </div>
            </div>
        </div>


        <input type="checkbox" id="nav-toggle" hidden>

    <nav class="nav">
        <label for="nav-toggle" class="nav-toggle" id ="nvtgl" onclick></label>
        <h2 class="logo">
            <a href="/mychannels">Мои каналы</a>
        </h2>
        <ul>
            <li><a href="/">  Моя лента</a>
            <li><a href="/mychannels">  Мои каналы</a>
            <li><a href="" data-toggle="modal" data-target="#aboutSiteForm">  О сайте</a>
            <li><a href="/logout">  Выход</a>
        </ul>
    </nav>

    <main role="main">
        <article>
            <div class="container-fluid">
                <div class="row">
                    <div class = "col-md-3 col-sm-1"></div>
                    <div class = "col-md-6 col-sm-10">

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

                      <ul >
                        <c:forEach items="${channels}" var="channel">
                          <li>
                            <div class = "row container-fluid">
                                <div class = "col-md-3 col-sm-3 div-img">
                                    <img src = "${channel.icon}"/>
                                </div>
                            <div class = "col-md-8 col-sm-8">
                                <p/><p/>
                                <h6><c:out value="${channel.title}"/></h6><p/>
                                <p><h6>
                                <small>
                                    <c:out value="${channel.description}"/>
                                </small>
                                </h6>
                                </p>
                            </div>
                            <div class = "col-md-1 col-sm-1 div-img">
                                <a href="/delete/${channel.id}"><i class="fas fa-times icon"></i></a>
                            </div>
                            </div>
                          </li>
                        </c:forEach>
                      </ul>

                      <div class = "footer">
                          <div class = "footer_background"></div>
                         <%-- <a href="#" class="add_button">+ Добавить канал</a>--%>
                          <a href="" id = "addbtn" class="btn btn-default add_button" data-toggle="modal" data-target="#modalLoginForm">+ Добавить канал</a>
                      </div>
                    </div>
                    <div class = "col-md-3 col-sm-1"></div>
                </div>
            </div>
        </article>
    </main>

        <script src="/resources/js/jquery.cookie.js"></script>

        <script>

            $("#nav-toggle").change(function(){

               if (this.checked)
                $("#addbtn").hide('slow');
               else if (!this.checked)
                $("#addbtn").show('slow');

            });

            $("#sbmt").click(function () {

                var button = $(this);
                var html = button.html();

                button.html('<i class="fa fa-spinner fa-pulse fa-1x fa-fw"></i><span class="sr-only">Выполняется проверка</span>');

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");

                var channelWrapper = {};
                channelWrapper["url"] = $("#new_url").val();

                $.ajax({
                    url: "addchannel",
                    contentType : "application/json",
                    method: "POST",
                    dataType: "text",
                    data: JSON.stringify(channelWrapper),
                    beforeSend: function(request) {
                        request.setRequestHeader(header, token);
                    },
                    success: function (data) {
                        $("#result").html(data);
                        button.html(html);
                    }

                });

            });


            $("#close_btn").click(function () {
                location.reload();
            });



        </script>


</body>
</html>
