<%--
  Created by IntelliJ IDEA.
  User: xutao
  Date: 2017/9/16
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Web自制编译器</title>
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .bg{
      filter: brightness(45%) blur(1px);
      top: -4px;
      left: -4px;
      width: 101%;
      height: 101%;
      position: fixed;
      z-index: -2;
    }
    .logo{
      /*top: 20%;*/
      margin-bottom: 10%;
    }
    .footer{
      bottom: 0;
      left: 20%;
      color: white;
      width: 60%;
      margin: auto;
      text-align: center;
      position: absolute;
    }
    input{
      border-color: black;
    }
  </style>
</head>

<body>
<!--背景-->
<div class="logo">
  <img src="background.jpg" class="bg">
</div>

<div class="center-block container">
  <%--<div class="logo">
    <img src="logo.jpg">
  </div>--%>
  <div class="row col-md-offset-3 col-md-6">
    <!--<button class="btn-lg btn btn-primary" data-toggle="modal" data-target="#renewModal">更新</button>-->
  </div>
</div>

<div style="padding: 100px 100px 10px;">
    <form class="bs-example bs-example-form" role="form">
        <div class="row">
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" class="form-control" name="text" id="text">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            编译
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li><a href="/TypeServlet?parm=text">词法分析</a></li>
                            <li><a href="/TypeServlet?param=text">语法分析</a></li>
                            <li><a href='/TypeServlet?parm=text'>语义分析</a></li>
                            <li class="divider"></li>
                            <li><a href="/TypeServlet?param=text">分离的链接</a></li>
                        </ul>
                    </div><!-- /btn-group -->
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->
    </form>
</div>


<%--<form name="TypeServlet" action="/TypeServlet" method="post">
    <textarea id="text" name="text"></textarea>
    <input type="submit" value="词法分析">
</form>--%>


${Compiler}

<div class="footer">
  <p>Web自制编译器 @2017</p>
</div>

<script src="jquery-3.1.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>

</script>
</body>
</html>



