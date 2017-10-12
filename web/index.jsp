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
  <title>图书管理</title>
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
    .btn-lg{
      border-radius: 8px;
      font-weight: 500;
      font-size: x-large;
      height: 200px;
    }
    .modal-content{
      top: 20%;
      /*width: 30%;*/
      /*height: 30%;*/
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
    #queryModal, #newBookModal, #newAuthorModal {
        left: 50%;
        top: 42%;
        transform: translate(-50%, -50%);
        min-width: 80%; /*这个比例可以自己按需调节*/
    }
    input{
      border-color: black;
    }
    #newAuthorMsg .col-sm-12 + .col-sm-12{
        margin: 0.5em;
    }
    #newBookMsg .col-sm-12 + .col-sm-12{
        margin: 0.5em;
    }
  </style>
</head>

<body>
<!--背景-->
<div class="logo">
  <img src="library.jpg" class="bg">
</div>

<div class="center-block container">
  <div class="logo">
    <img src="logo2.png">
  </div>
  <div class="row col-md-offset-3 col-md-6">
      <button class="btn-lg btn btn-primary col-lg-4" data-toggle="modal" data-target="#queryModal">查询书籍</button>
      <button class="btn-lg btn btn-primary col-lg-4" data-toggle="modal" data-target="#newBookModal">新增书籍</button>
      <button class="btn-lg btn btn-primary col-lg-4" data-toggle="modal" data-target="#newAuthorModal">新增作家</button>
    <!--<button class="btn-lg btn btn-primary" data-toggle="modal" data-target="#renewModal">更新</button>-->
  </div>
</div>

<!--查询模态框-->
<div class="modal fade" id="queryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="queryModalLabel">
          查询书籍
        </h4>
      </div>
      <div class="modal-body">
        <form id="queryByAuthor" action="queryByAuthor">
          <span>作者姓名:</span>
          <input type="text" name="authorName">
          <%--<input type="submit">--%>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        <button type="button" id="query-btn" class="btn btn-primary">
          查询
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

<div class="modal fade" id="newBookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="renewModalLabel">
          新增书籍
        </h4>
      </div>
      <div class="modal-body row newBookForm">
          <div class="container" id="newBookMsg">
              <div class="col-sm-12">
                  <h3>图书信息</h3>
              </div>
              <div class="col-sm-12">
                  <b>标题:</b>
                  <input class="input-sm" type="text" name="bookTitle" id="bookTitle">
                  <p></p>
              </div>
              <div class="col-sm-12">
                  <b>作者ID:</b>
                  <input class="input-sm" type="text" name="bookAuthor" id="bookAuthor">
                  <p></p>
              </div>
              <div class="col-sm-12">
                  <b>ISBN:</b>
                  <input class="input-sm" type="text" name="bookISBN" id="bookISBN">
                  <p></p>
              </div>
              <div class="col-sm-12">
                  <b>出版社:</b>
                  <input class="input-sm" type="text" name="bookPublisher" id="bookPublisher">
                  <p></p>
              </div>
              <div class="col-sm-12">
                  <b>出版日期:</b>
                  <input class="input-sm" type="text" placeholder="YYYY-MM-DD" name="bookPublishDate" id="bookPublishDate">
                  <p></p>
              </div>
              <div class="col-sm-12">
                  <b>价格:</b>
                  <input  class="input-sm" type="text" name="bookPrice" id="bookPrice">
                  <p></p>
              </div>
          </div>
            <%--<div class="col-md-6 container" id="newAuthorMsg">--%>
                <%--<div class="col-sm-12">--%>
                    <%--<h3>作者信息</h3>--%>
                <%--</div>--%>
                <%--<div class="col-sm-12">--%>
                    <%--<b>姓名:</b>--%>
                    <%--<input class="input-sm" type="text" name="authorName">--%>
                <%--</div>--%>
                <%--<div class="col-sm-12">--%>
                    <%--<b>年龄:</b>--%>
                    <%--<input class="input-sm" type="text" name="authorAge">--%>
                <%--</div>--%>
                <%--<div class="col-sm-12">--%>
                    <%--<b>国籍:</b>--%>
                    <%--<input class="input-sm" type="text" name="authorCountry">--%>
                <%--</div>--%>
            <%--</div>--%>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        <button type="button" class="btn btn-primary" id="newBookBtn">
          新增
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

<div class="modal fade" id="newAuthorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="newAuthorModalLabel">
                    新增书籍
                </h4>
            </div>
            <div class="modal-body row newBookForm">
                <div class="col-md-6 container" id="newAuthorMsg">
                    <div class="col-sm-12"><h3>作者信息</h3>
                    </div>
                    <div class="col-sm-12">
                        <b>姓名:</b>
                        <input class="input-sm" type="text" name="authorName" id="authorName">
                    </div>
                    <div class="col-sm-12">
                        <b>ID:</b>
                        <input class="input-sm" type="text" name="authorName" id="authorID">
                    </div>
                    <div class="col-sm-12">
                        <b>年龄:</b>
                        <input class="input-sm" type="text" name="authorAge" id="authorAge">
                    </div>
                    <div class="col-sm-12">
                        <b>国籍:</b>
                        <input class="input-sm" type="text" name="authorCountry" id="authorCountry">
                    </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="newAuthorBtn">
                    新增
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="footer">
  <p>XXXXXXXXX @2017</p>
</div>

<script src="jquery-3.1.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>

    //提交查询作者
    $("#query-btn").on("click", function(){
//        if($("#queryByAuthor input").text() != ""){
//            $("#queryByAuthor").submit();
//        }
        var authorName = $("#queryByAuthor input").text();
        //$.get("booklist.jsp");
        $("#queryByAuthor").submit();
    })

    function authorExist(authorName){
        var authorID = "-1";
        $.ajax({
            type:"post",
            url:"isAuthorExist",
            data:{
                authorName:authorName
            },
            async: false,
            dataType:"json",
            success:function(data){
                var obj = JSON.parse(data);
                authorID = obj.authorID;
                if(obj.authorID == "-1"){
                    alert("输入的作者不存在，请先加入作者");
                }
                //alert("in authorExist    " + authorID);
            }
        });
        //alert("in authorExist2    " + authorID);
        return authorID;
    }

    function IDExist(id){
        var isExist = false;
        $.ajax({
            type:"post",
            url:"isIDExist",
            data:{
                id: id
            },
            async: false,
            dataType:"json",
            success:function(data){
                var obj = JSON.parse(data);
                if(obj.isExist == "1"){
                    //alert("该ID已经存在了");
                    isExist = true;
                }
            }
        });
        return isExist;
    }

    function titleExist(bootTitle){
        var isExist = false;
        $.ajax({
            type:"post",
            url:"isTitleExist",
            data:{
                title : bootTitle
            },
            async: false,
            dataType:"json",
            success:function(data){
                var obj = JSON.parse(data);
                if(obj.isExist == "1"){
                    //alert("该书本已经存在了");
                    isExist = true;
                }
                //alert("in titleExist    " + isExist);
            }
        });
        //alert("in titleExist2    " + isExist)
        return isExist;
    }

    function saveNewBook(bookTitle, bookAuthor, bookISBN, bookPrice, bookPublisher, bookPublishDate){
        $.ajax({
            type:"post",
            url:"saveNewBook",
            data:{
                bookTitle: bookTitle,
                bookAuthor: bookAuthor,
                bookISBN: bookISBN,
                bookPrice: bookPrice,
                bookPublisher: bookPublisher,
                bookPublishDate: bookPublishDate
            },
            dataType: "json",
            success:function(data){
                var obj = JSON.parse(data);
                if(obj.flag == "true"){
                    alert("书籍保存成功");
                }
                else{
                    alert("书籍保存失败");
                }
            }
        })
    }

    $("#newBookBtn").on("click", function(){
        var bookTitle = $("#bookTitle").val();
        var bookAuthor = $("#bookAuthor").val();
        var bookISBN = $("#bookISBN").val();
        var bookPrice = $("#bookPrice").val();
        var bookPublisher = $("#bookPublisher").val();
        var bookPublishDate = $("#bookPublishDate").val();
        var flag = true;
        var typeInt = "^-?\\d+$";
//        var typeFloat = "^\\d+(\\.\\d+)?$";
        var typeFloat = "^\\d+(\\.\\d+)?$";
        var rInt = new RegExp(typeInt);
        var rFloat = new RegExp(typeFloat);
        var s = "请输入有效数据";
//        if(!rInt.test(bookISBN)){
//            $("#bookISBN").parent().children("p").text(s);
//            flag &= false;
//        }
//        else{
//            $("#bookISBN").parent().children("p").text("");
//            flag &= true;
//        }

        if(rInt.test(bookPrice) || rFloat.test(bookPrice)){
            $("#bookPrice").parent().children("p").text("");
            flag &= true;
        }
        else{
            $("#bookPrice").parent().children("p").text(s);
            flag &= false;
        }

        if(bookPublishDate.length != 10){
            $("#bookPublishDate").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#bookPublishDate").parent().children("p").text("");
            flag &= true;
        }

        if(bookPublisher == ""){
            $("#bookPublisher").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#bookPublisher").parent().children("p").text("");
            flag &= true;
        }

        if(bookTitle == ""){
            $("#bookTitle").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#bookTitle").parent().children("p").text("");
            flag &= true;
        }

        if(bookAuthor == ""){
            $("#bookAuthor").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#bookAuthor").parent().children("p").text("");
            flag &= true;
        }

        if(flag){
            //确认输入无误后ajax请求后台数据，判明作者是否存在，如果不存在要求填写作者信息，存在则直接提交表单
//            $.ajax({
//                type:"post",
//                url:"isAuthorExist",
//                data:{
//                    authorName:bookAuthor
//                },
//                dataType:"json",
//                success:function(data){
//                    var obj = JSON.parse(data);
//                    if(obj.authorID == "-1"){
//                        alert("输入的作者不存在，请先加入作者");
//                    }
//                    else{
//                        alert("开始保存");
//                        var authorID = obj.authorID;
//                        saveNewBook(bookTitle, authorID, bookISBN, bookPrice, bookPublisher, bookPublishDate);
//                    }
//                }
//            })
            //var authorID = authorExist(bookAuthor);
            var istitleExist = titleExist(bookTitle);
            var isIDExist = IDExist(bookAuthor);

//            if(authorID == "-1" || istitleExist == true){
//                 alert(authorID + istitleExist);
//            }
//            else{
//                saveNewBook(bookTitle, authorID, bookISBN, bookPrice, bookPublisher, bookPublishDate);
//            }
            if(isIDExist == true && istitleExist == false){
                saveNewBook(bookTitle, bookAuthor, bookISBN, bookPrice, bookPublisher, bookPublishDate);
            }
            if(isIDExist == false){
                alert("ID不存在，请先添加作者");
            }
            if(istitleExist == true){
                alert("该标题已经存在了");
            }
//            if(isIDExist == false || istitleExist == true){
//                 //alert(authorID + istitleExist);
//                 alert("ID不存在，请先添加作者")
//            }
//            else{
//                saveNewBook(bookTitle, bookAuthor, bookISBN, bookPrice, bookPublisher, bookPublishDate);
//            }
        }
    });


    function saveNewAuthor(authorName, authorID, authorAge, authorCountry){
        $.ajax({
            type : "post",
            url : "saveNewAuthor",
            data : {
                authorName : authorName,
                authorID : authorID,
                authorAge : authorAge,
                authorCountry : authorCountry
            },
            dataType : "json",
            success:function (data) {
                var obj = JSON.parse(data);
                if(obj.flag == "true"){
                    alert("作者保存成功");
                }
                else{
                    alert("作者保存失败");
                }
            }
        });
    }
    $("#newAuthorBtn").on("click", function(){
        var authorName = $("#authorName").val();
        var authorAge = $("#authorAge").val();
        var authorCountry = $("#authorCountry").val();
        var authorID = $("#authorID").val();
        var flag = true;
        var typeInt = "^-?\\d+$";
        var rInt = new RegExp(typeInt);
        var s = "请输入有效数据";
        if(authorName == ""){
            $("#authorName").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#authorName").parent().children("p").text("");
            flag &= true;
        }
        if(!rInt.test(authorAge)){
            $("#authorAge").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#authorAge").parent().children("p").text("");
            flag &= true;
        }
        if(!rInt.test(authorAge)){
            $("#authorID").parent().children("p").text(s);
            flag &= false;
        }
        else{
            $("#authorID").parent().children("p").text("");
            flag &= true;
        }
        if(flag){
            $.ajax({
                type : "post",
                url : "isIDExist",
                data:{
                    id:authorID
                },
                dataType:"json",
                success:function(data){
                    var obj = JSON.parse(data);
                    if(obj.isExist == "0"){
                        //alert("开始保存");
                        saveNewAuthor(authorName, authorID, authorAge, authorCountry);
                    }
                    else{
                        alert("输入的ID已经存在了");
                    }
                }
            });
        }
    });
</script>
</body>
</html>



