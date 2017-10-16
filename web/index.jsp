<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Web自制编译器</title>

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
<p align="center" style="color:white; height: 2em; font-size: 2em">词法分析器</p>

<div style="height: 600px; width: 100%;">
<div style="height: 600px; width: 40%; margin-left: 5%; float:left">
  <p style="color: white; left:5%; height: 1em; font-size: 1em;">输入测试用例：</p>
  <form name="TypeServlet" style="width: 100%; left: 5%; height: 100%; float: left" action="/TypeServlet" method="post">
    <textarea style="width: 100%; height: 40em" id="text" name="text"></textarea>

    <input type="file">
    <button type="button" id="upload" style="width: 15%">导入FA</button>
    <button type="submit" id="analys" style="width: 15%">词法分析</button>
  </form>
</div>

<div style="height: 600px; left:55%; width: 40%; margin-right: 5%; float: right">
    <p style="color: white; left:5%; height: 1em; font-size: 1em;">输出Token以及DFA转换表：</p>
    <div style="color: white; width: 100%; right: 5%; height: 90%; float: right; overflow-y: scroll">
        ${Compiler}
    </div>
</div>
</div>

<div class="footer">
  <p>Web自制编译器 @2017</p>
</div>

<script src="jquery-3.1.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>

</script>
</body>
</html>



