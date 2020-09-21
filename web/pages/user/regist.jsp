<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>三变书城会员注册页面</title>
	<!--base标签固定相对路径跳转的页面路径-->
	<%@include file="/pages/common/header.jsp"%>

  <script>


    $(function () {

		$("#username").blur(function () {
			var username = this.value;
			$.getJSON("http://localhost:8080/book/userServlet","action=existsusername&username="+username,function (data){
						if(data.username){
							$("span.errorMsg").text("该用户名已存在！");
						}
					}
			);

		});


		/*
    验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
    验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
    验证确认密码：和密码相同
    邮箱验证：xxxxx@xxx.com
    验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
    */
      $("#sub_btn").click(function () {
        //验证用户名合法

          //获取输入的用户名
          var username = $("#username").val();
          //创建用户名正则表达式
          var userpatten = /^\w{5,12}$/;
          //若不匹配，显示提示信息
          if(!userpatten.test(username)){
            $("span.errorMsg").text("用户名不合法！");
              return false;
          }


          //验证密码是否合法
        var password = $("#password").val();
        //创建密码正则表达式
        var passpatten = /^\w{5,12}$/;
        //若不匹配，显示提示信息
        if(!passpatten.test(password)){
          $("span.errorMsg").text("密码不合法！");
             return false;
        }



          //验证密码和确认密码是否相同
        var passwordtext = $("#password").val();
        var repword = $("#repwd").val();
        if(password!=repword){
          $("span.errorMsg").text("输入密码不一致！");
          return false;
        }
        //邮箱验证：xxxxx@xxx.com
        var email = $("#email").val();
        var emailpatten = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if(!emailpatten.test(email)){
          $("span.errorMsg").text("邮箱格式不合法！");
          return false;
        }

        //验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
        var code = $("#code").val();
        var s = $.trim(code);//去掉字符串前后空格
        if(s==null||s==""){
          $("span.errorMsg").text("验证码不正确！");
          return false;
        }

        $("span.errorMsg").text("");

      });


    });


  </script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册书城会员</h1>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
                         autocomplete="off" tabindex="1" name="username" id="username" value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
                         autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
                         autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
                         autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
									<img src="static/img/code.bmp" alt="" style="float: right; margin-right: 40px;width: 100px;height:30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />

								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
