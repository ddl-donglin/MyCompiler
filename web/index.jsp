<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
    footer p{
      color: #fff;
        text-align: center;
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
<p align="center" style="color:white; height: 2em; font-size: 2em">自制编译器</p>

<div style="height: 600px; width: 100%;">

  <div style="height: 600px; width: 40%; margin-left: 5%; float:left">
    <p style="color: white; left:5%; height: 0.8em; font-size: 1em;">输入/导入测试用例：</p>
    <form name="TypeServlet" style="width: 100%; left: 5%; height: 100%; float: left">
      <textarea style="width: 100%; height: 40em" id="text" name="text"></textarea>

      <input type="file" id="file" name="file" >
      <button type="button" id="clear" style="width: 12%">清空输入</button>
      <button type="button" id="analys" style="width: 12%">词法分析</button>
      <button type="button" id="grammar" style="width: 12%">语法分析</button>
      <button type="button" id="understand" style="width: 12%">语义分析</button>
    </form>
  </div>

  <div style="height: 600px; left:55%; width: 40%; margin-right: 5%; float: right">
      <p style="color: white; left:5%; height: 1em; font-size: 1em;">输出结果：</p>
      <div id="result" style="color: white; width: 100%; right: 5%; height: 90%; float: right; overflow-y: scroll">${Compiler}</div>
  </div>
</div>

<footer>
  <p>Davidddl编译器 @2017</p>
</footer>

<script src="jquery-3.2.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("#file").change(function () {
            var fileSele = $("#file")[0].files;
            var file = fileSele[0];

            var reader = new FileReader();
            reader.onload = function () {
                $("#text").val(this.result);
            };
            reader.readAsText(file,"gbk");
        })
        $("#clear").click(function(){
            $("#text").val("");
            $("#file").val("");
        });
        $("#analys").click(function () {
            $.ajax({
                url: "/Lexer.TypeServlet",
                type: "POST",
                data: {
                    "text": $("#text").val()
                },
                success: function (data) {
                    $("#result").html(data);
                }

            })
        });
        $("#grammar").click(function () {
            $.ajax({
                url: "/Parser.GrammarServlet",
                type: "POST",
                data: {
                    "text": $("#text").val()
                },
                success: function (data) {
                    $("#result").html(data);
                }

            })
        });
        $("#understand").click(function () {
            $.ajax({
                url: "/Semantic.UnderStandServlet",
                type: "POST",
                data: {
                    "text": $("#text").val()
                },
                success: function (data) {
                    $("#result").html(data);
                }

            })
        });

    })
</script>
</body>
</html>



