<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>terrace Login</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${base}/resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/select2/select2_metro.css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${base }/resources/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="${base }/resources/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${base }/resources/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${base}/resources/css/pages/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/app/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<h1 style="color: #FFFFFF">
			<s:property value="%{systemTitle}" />
		</h1>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="clearfix" style="padding: 15px">
		<div class="content" style="width: 100%; max-width: 450px">
			<!-- BEGIN LOGIN FORM -->
			<form id="login-form" class="login-form" action="${base}/j_spring_security_check" method="post">
				<h3 class="form-title">系统登录</h3>
				<div class="form-group">
					<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
					<label class="control-label visible-ie8 visible-ie9">登录账号</label>
					<div class="input-icon">
						<i class="fa fa-user"></i> <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
							placeholder="登录账号" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">登录密码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i> <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
							placeholder="登录密码" name="j_password" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">验证码</label>
					<div class="input-group">
						<div class="input-icon">
							<i class="fa fa-qrcode"></i> <input class="form-control captcha-text" type="text" autocomplete="off"
								placeholder="验证码...看不清可点击图片可刷新" name="j_captcha" />
						</div>
						<span class="input-group-btn" style="cursor: pointer;"> <img alt="验证码" name="j_captcha" height="34px"
							class="captcha-img" src="${base}/assets/img/captcha_placeholder.jpg" title="看不清？点击刷新" />
						</span>
					</div>
				</div>
				<div class="form-actions">
					<label> <input type="checkbox" name="_spring_security_remember_me" checked="true" value="true" />
						记住我(两周内自动登录)
					</label>
					<button type="submit" class="btn blue pull-right">
						登录 <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>
				<div class="forget-password">
					<div class="row">
						<div class="col-md-3">
							<s:if test="casSupport">
								<p>
									<a href='<s:property value="casRedirectUrl"/>'>单点登录</a>
								</p>
							</s:if>
						</div>
						<div class="col-md-9">
							<p class="pull-right">
								忘记密码? <a href="#forget-password" data-toggle="modal">找回密码</a>
								<s:if test="signupEnabled">
								&nbsp; &nbsp;&nbsp; &nbsp; 没有账号? <a href="#create-account" data-toggle="modal">自助注册</a>
								</s:if>
							</p>
						</div>
					</div>
				</div>
				<s:if test="%{devMode}">
					<script type="text/javascript">
                        function setupDevUser(user, password) {
                            var $form = $("#login-form");
                            $("input[name='j_username']", $form).val(user);
                            $("input[name='j_password']", $form).val(password);
                            $("input[name='j_captcha']", $form).val('admin');
                            $form.submit();
                        }
                    </script>
					<div>
						<p>
							开发测试登录快速入口: <a href="javascript:void(0)" class="" onclick="setupDevUser('admin','123')">admin</a>
						</p>
					</div>
				</s:if>
			</form>
			<!-- END LOGIN FORM -->

			<!-- BEGIN FORGOT PASSWORD FORM -->
			<div class="modal fade" id="forget-password" tabindex="-1" role="basic" aria-hidden="true">
				<div class="modal-dialog">
					<form id="forget-form" action="${base}/pub/signin!forget" method="post">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">找回密码</h4>
							</div>
							<div class="modal-body">
								<p>输入您注册时填写的登录账号或邮箱地址.</p>
								<p>如果未设置注册邮箱或遗忘相关注册信息请联系管理员协助处理.</p>
								<div class="form-group">
									<div class="input-icon">
										<i class="fa fa-user"></i> <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
											placeholder="填写登录账号或注册邮箱" name="uid" />
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-icon">
											<i class="fa fa-qrcode"></i> <input class="form-control captcha-text" type="text" autocomplete="off"
												placeholder="验证码...看不清可点击图片可刷新" name="j_captcha" />
										</div>
										<span class="input-group-btn" style="cursor: pointer;"> <img alt="验证码" name="j_captcha" height="34px"
											class="captcha-img" src="${base}/assets/img/captcha_placeholder.jpg" title="看不清？点击刷新" />
										</span>
									</div>
								</div>
								<s:if test="!mailServiceEnabled">
									<div class="note note-warning" style="margin-bottom: 0px">
										<p>系统当前未开启邮件服务，暂时无法提供找回密码服务！</p>
										<p>若有疑问请联系告知管理员！</p>
									</div>
								</s:if>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn default" data-dismiss="modal">取消</button>
								<s:if test="mailServiceEnabled">
									<button type="submit" class="btn blue">提交</button>
								</s:if>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END FORGOT PASSWORD FORM -->

			<s:if test="signupEnabled">
				<!-- BEGIN REGISTRATION FORM -->
				<div class="modal fade" id="create-account" tabindex="-1" role="basic" aria-hidden="true">
					<div class="modal-dialog">
						<form id="register-form" class="form-horizontal form-bordered form-label-stripped"
							action="${base}/pub/signup!submit" method="post">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title">账号注册</h4>
								</div>
								<div class="modal-body">
									<p>请填写如下必须的注册信息：</p>
									<div class="form-group">
										<label class="control-label">登录账号</label>
										<div class="controls">
											<div class="input-icon">
												<i class="fa fa-user"></i> <input class="form-control placeholder-no-fix" type="text" name="signinid" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label">输入登录密码</label>
										<div class="controls">
											<div class="input-icon">
												<i class="fa fa-lock"></i> <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
													name="password" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label">再次输入密码</label>
										<div class="controls">
											<div class="input-icon">
												<i class="fa fa-check"></i> <input class="form-control placeholder-no-fix" type="password"
													autocomplete="off" name="rpassword" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label">注册邮箱</label>
										<div class="controls">
											<div class="input-icon">
												<i class="fa fa-envelope"></i> <input class="form-control placeholder-no-fix" type="text"
													placeholder="请填写真实有效邮箱地址，可用于邮件通知、找回密码等功能" name="email" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label">验证码</label>
										<div class="controls">
											<div class="input-group">
												<div class="input-icon">
													<i class="fa fa-qrcode"></i> <input class="form-control captcha-text" type="text" autocomplete="off"
														placeholder="验证码...看不清可点击图片可刷新" name="j_captcha" />
												</div>
												<span class="input-group-btn" style="cursor: pointer;"> <img alt="验证码" name="j_captcha" height="34px"
													class="captcha-img" src="${base}/assets/img/captcha_placeholder.jpg" title="看不清？点击刷新" />
												</span>
											</div>
										</div>
									</div>
									<p style="margin-top: 10px">以下为选填的注册信息：</p>
									<div class="form-group">
										<label class="control-label">联系信息</label>
										<div class="controls">
											<textarea rows="1" class="form-control placeholder-no-fix" name="contactInfo"
												placeholder="可自由填写申请人的姓名、电话、邮件、聊天账号等信息，用于系统管理员在需要时联系到您进行资料确认"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label">备注说明</label>
										<div class="controls">
											<textarea rows="2" class="form-control placeholder-no-fix" name="remarkInfo"
												placeholder="提供相关备注说明信息，如账号类型，需要访问的 功能列表等，有助于管理员快速有效的进行账号设定"></textarea>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12" style="padding-top: 10px">
											<label> <input type="checkbox" name="tnc" checked="checked" /> 同意遵守本系统相关访问和使用协议!
											</label>
											<div id="register_tnc_error"></div>
										</div>
									</div>
									<div class="note note-info" style="margin-bottom: 0px">
										<p>提交注册请求后，需要等待系统管理员人工审核授权，在此期间无法访问系统！</p>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn default" data-dismiss="modal">取消</button>
									<button type="submit" class="btn blue">提交</button>
								</div>
							</div>
						</form>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- END REGISTRATION FORM -->
			</s:if>

		</div>
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright" style="color: #FFFFFF">
		2013 &copy;
		<%=request.getServerName()%></div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src="${base }/resources/plugins/ie/respond.min.js"></script>
	<script src="${base }/resources/plugins/ie/excanvas.min.js"></script> 
	<![endif]-->
	<script src="${base}/resources/plugins/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/jquery-migrate/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/others/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/others/jquery.cookie.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${base}/resources/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script src="${base}/resources/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${base}/resources/plugins/others/jquery.form.js"></script>
	<script src="${base}/resources/js/app.js" type="text/javascript"></script>
	<script src="${base}/resources/app/util.js" type="text/javascript"></script>
	<script src="${base}/resources/app/form-validation.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script type="text/javascript">
        var WEB_ROOT = "${base}";
    </script>

	<s:if test="%{#parameters.email!=null}">
		<!-- BEGIN RESET PASSWORD FORM -->
		<div class="modal fade" id="reset-password" tabindex="-1" role="basic" aria-hidden="true">
			<div class="modal-dialog">
				<form id="reset-form" class="form-horizontal form-bordered form-label-stripped" action="${base}/pub/signin!resetpwd"
					method="post">
					<s3:hidden name="email" value="%{#parameters.email}" />
					<s3:hidden name="code" value="%{#parameters.code}" />
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">设置新密码</h4>
						</div>
						<div class="modal-body">
							<p>
								您正在重新设置注册邮箱：
								<s:property value="#parameters.email" />
								对应账号密码
							</p>
							<div class="form-group">
								<label class="control-label">输入新的密码</label>
								<div class="controls">
									<div class="input-icon">
										<i class="fa fa-lock"></i> <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
											name="password" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label">再次输入密码</label>
								<div class="controls">
									<div class="input-icon">
										<i class="fa fa-check"></i> <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
											name="rpassword" />
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
							<button type="submit" class="btn blue">提交</button>
						</div>
					</div>
				</form>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- END RESET PASSWORD FORM -->
		<script type="text/javascript">
            jQuery(document).ready(function() {
                $("#reset-password").modal();
            });
        </script>
	</s:if>

	<script type="text/javascript">
	jQuery(document).ready(function() {

	    App.init();
	    Util.init();
	    FormValidation.init();

	    $('#login-form').find("input:first").focus();

	    jQuery('body').on('click', '.captcha-img', function(e) {
	        $(".captcha-img").each(function() {
	            $(this).attr('src', WEB_ROOT + '/assets/img/captcha_placeholder.jpg');
	        })
	        $(this).attr('src', WEB_ROOT + '/pub/jcaptcha.servlet?_=' + new Date().getTime());
	        var $captchaText = $(this).closest(".form-group").find(".captcha-text");
	        $captchaText.val("");
	        $captchaText.focus();
	        return false;
	    });

	    jQuery('body').on('focus', '.captcha-text', function(e) {
	        var $captchaImg = $(this).closest(".form-group").find(".captcha-img");
	        if ($captchaImg.attr("src") == WEB_ROOT + '/assets/img/captcha_placeholder.jpg') {
	            $captchaImg.click();
	        }
	    });

	    $('#login-form').validate({
	        errorElement : 'span', //default input error message container
	        errorClass : 'help-block', // default input error message class
	        focusInvalid : false, // do not focus the last invalid input
	        rules : {
	            j_username : {
	                required : true
	            },
	            j_password : {
	                required : true
	            },
	            j_captcha : {
	                required : true
	            }
	        },

	        messages : {
	            j_username : {
	                required : "请填写登录账号"
	            },
	            j_password : {
	                required : "请填写登录密码"
	            },
	            j_captcha : {
	                required : "请填写登录验证码"
	            }
	        },

	        highlight : function(element) { // hightlight error inputs
	            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	        },

	        success : function(label) {
	            label.closest('.form-group').removeClass('has-error');
	            label.remove();
	        },

	        errorPlacement : function(error, element) {
	            error.appendTo(element.closest('.form-group'));
	        },

	        submitHandler : function(form) {
	            form.submit();
	        }
	    });

	    var imageHV = 'h';
	    if ($(window).height() > $(window).width()) {
	        imageHV = 'v';
	        //$(".content").removeClass('pull-left');
	    }

	    $.backstretch([ WEB_ROOT + "/assets/img/bg/1.jpg", WEB_ROOT + "/assets/img/bg/2.jpg", WEB_ROOT + "/assets/img/bg/3.jpg", WEB_ROOT + "/assets/img/bg/4.jpg" ], {
	        fade : 1000,
	        duration : 8000
	    });

	    $('#forget-form').validate({
	        errorElement : 'span', //default input error message container
	        errorClass : 'help-block', // default input error message class
	        focusInvalid : false, // do not focus the last invalid input
	        ignore : "",
	        rules : {
	            uid : {
	                required : true
	            },
	            j_captcha : {
	                required : true
	            }
	        },

	        messages : {
	            uid : {
	                required : "请填写登录账号或注册邮箱"
	            },
	            j_captcha : {
	                required : "请填写验证码"
	            },
	        },

	        highlight : function(element) { // hightlight error inputs
	            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	        },

	        success : function(label) {
	            label.closest('.form-group').removeClass('has-error');
	            label.remove();
	        },

	        errorPlacement : function(error, element) {
	            error.appendTo(element.closest('.form-group'));
	        },

	        submitHandler : function(form) {
	            $(form).ajaxPostForm(function(data) {
	                $(".captcha-img").each(function() {
	                    $(this).attr('src', WEB_ROOT + '/assets/img/captcha_placeholder.jpg');
	                })
	                $(form).find('button[data-dismiss="modal"]').click();
	                bootbox.dialog({
	                    message : "系统已发送重置密码邮件至：<span class='text-primary'>" + data.userdata + "</span><br/>请稍后访问此邮箱按照邮件内容提示操作重新设置密码！",
	                    title : "恭喜，找回密码请求成功",
	                    buttons : {
	                        main : {
	                            label : "关闭",
	                            className : "blue"
	                        }
	                    }
	                });
	            }, false, function() {
	                var $captchaImg = $(form).find(".captcha-img");
	                $captchaImg.click();
	            });
	            return false;
	        }
	    });

	    $('#register-form').validate({
	        errorElement : 'span', //default input error message container
	        errorClass : 'help-block', // default input error message class
	        focusInvalid : false, // do not focus the last invalid input
	        ignore : "",
	        rules : {
	            signinid : {
	                required : true
	            },
	            password : {
	                required : true
	            },
	            rpassword : {
	                required : true,
	                equalToByName : "password"
	            },
	            email : {
	                required : true,
	                email : true
	            },
	            j_captcha : {
	                required : true
	            },
	            tnc : {
	                required : true
	            }
	        },

	        messages : { // custom messages for radio buttons and checkboxes
	            signinid : {
	                required : "请填写登录账号"
	            },
	            password : {
	                required : "请输入登录密码"
	            },
	            rpassword : {
	                required : "请再次输入登录密码",
	                equalTo : "确认密码必须与登录密码一致"
	            },
	            email : {
	                required : "请填写注册电子邮件",
	                email : "请填写有效格式的电子邮件地址"
	            },
	            j_captcha : {
	                required : "请填写验证码"
	            },
	            tnc : {
	                required : "注册账号必须勾选同意协议."
	            }
	        },

	        highlight : function(element) { // hightlight error inputs
	            if ($(element).attr("name") == "tnc") { // insert checkbox errors after the container                  
	                $(element).closest('.row').addClass('has-error');
	            } else {
	                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	            }
	        },

	        success : function(label) {
	            label.closest('.form-group').removeClass('has-error');
	            label.remove();
	        },

	        errorPlacement : function(error, element) {
	            if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
	                error.insertAfter($('#register_tnc_error'));
	            } else if (element.closest('.input-icon').size() === 1) {
	                error.insertAfter(element.closest('.input-icon'));
	            } else {
	                error.insertAfter(element);
	            }
	        },

	        submitHandler : function(form) {
	            $(form).ajaxPostForm(function() {
	                $(".captcha-img").each(function() {
	                    $(this).attr('src', WEB_ROOT + '/assets/img/captcha_placeholder.jpg');
	                })
	                $(form).find('button[data-dismiss="modal"]').click();
	                bootbox.dialog({
	                    message : "注册账号已提交成功，请耐心等待管理员审核授权！<br/>审核通过后会自动发送通知邮件到您的注册邮箱！",
	                    title : "恭喜，注册提交完成",
	                    buttons : {
	                        main : {
	                            label : "关闭",
	                            className : "blue"
	                        }
	                    }
	                });
	            }, false, function() {
	                var $captchaImg = $(form).find(".captcha-img");
	                $captchaImg.click();
	            });
	            return false;
	        }
	    });

	    $('#reset-form').validate({
	        errorElement : 'span', //default input error message container
	        errorClass : 'help-block', // default input error message class
	        focusInvalid : false, // do not focus the last invalid input
	        ignore : "",
	        rules : {
	            password : {
	                required : true
	            },
	            rpassword : {
	                required : true,
	                equalToByName : "password"
	            }
	        },

	        messages : {
	            password : {
	                required : "请输入登录密码"
	            },
	            rpassword : {
	                required : "请再次输入登录密码",
	                equalTo : "确认密码必须与登录密码一致"
	            }
	        },

	        highlight : function(element) { // hightlight error inputs
	            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	        },

	        success : function(label) {
	            label.closest('.form-group').removeClass('has-error');
	            label.remove();
	        },

	        errorPlacement : function(error, element) {
	            error.insertAfter(element);
	        },

	        submitHandler : function(form) {
	            $(form).ajaxPostForm(function() {
	                $(form).find('button[data-dismiss="modal"]').click();
	                bootbox.dialog({
	                    message : "您可以马上使用新设定密码登录系统啦",
	                    title : "恭喜，密码设置成功",
	                    buttons : {
	                        main : {
	                            label : "关闭",
	                            className : "blue"
	                        }
	                    }
	                });
	            });
	            return false;
	        }
	    });
	});
	</script>
</body>
<!-- END BODY -->
</html>