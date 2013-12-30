
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp" %>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>terrace</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="${base}/resources/login/css/reset.css">
        <link rel="stylesheet" href="${base}/resources/login/css/supersized.css">
        <link rel="stylesheet" href="${base}/resources/login/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
	
    </head>

    <body>

        <div class="page-container">
            <h1>terrace</h1>
            <form action="${base}/j_spring_security_check" method="post">
                <input type="text" name="j_username" class="username" placeholder="Username" value="admin">
                <input type="password" name="j_password" class="password" placeholder="Password" value="123">
                <!-- <input type="text" name="checkcode" class="username" placeholder="Checkcode"> -->
                <button type="submit" >Sign me in</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>author by syd 2013-11-27:</p>
                <p>
                    <a class="facebook" href="http://weibo.com/u/1853131443"></a>
                    <a class="twitter" href="http://weibo.com/u/1853131443"></a>
                </p>
            </div>
        </div>

        <!-- Javascript -->
        <script src="${base}/resources/login/js/jquery-1.8.2.min.js"></script>
        <script src="${base}/resources/login/js/supersized.3.2.7.min.js"></script>
        <script src="${base}/resources/login/js/supersized-init.js"></script>
        <script src="${base}/resources/login/js/scripts.js"></script>
    </body>

</html>

