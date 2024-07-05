<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>Sistem Informasi Pengelolaan Keuangan Daerah</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="Diskominfotik Pemprov DKI Jakarta" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${pageContext.request.contextPath}/static/assets_login/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/css/style-metro.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets_login/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> 
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="${pageContext.request.contextPath}/static/assets_login/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
        <!-- END PAGE LEVEL STYLES -->
      <!--  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/favicon.ico" /> -->
    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <body class="login">
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
        <!-- BEGIN LOGO -->
        <div class="logo" >
            <!--    <img src="${pageContext.request.contextPath}/static/assets_login/img/logo_e-sipkd-big.png" alt="logo" class="img-responsive" />  -->
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="container">
            <div class="article">

                <div class="iklan">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">

                                <img src="${pageContext.request.contextPath}/static/assets_login/img/slide1.png" alt="First slide">

                                <div class="carousel-caption col-md-11" align="left">

                                    <div class="portlet-body flip-scroll">

                                        <div class="col-md-12">
                                            <label class="col-md-12" id="labelberitaawal"  align="left" style="color: #005580" ></label>

                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <TEXTAREA name="isiberita" id="isiberita" style="margin: 0px; width: 465px; height: 184px; opacity: 0.6" readonly="true"></TEXTAREA>

                                                </div>
                                            </div>
                                            <a href='${pageContext.request.contextPath}/static/pdf_berita/permendagri-32-2017.pdf' target="_new"><i class='icon-book' align="left"></i> PDF Berita </a>
                                            <label class="col-md-12" id="dari"  align="right" style="color: #005580" ></label>
                                            <label class="col-md-12" id="tgl"  align="right" style="color: #005580" ></label>
                                            
                           
                                        </div>
                                </div>
                                   
                                </div>
                                
                            </div>
                              <!--   
                            <div class="item ">
                                <img src="${pageContext.request.contextPath}/static/assets_login/img/slide2.png" alt="Second slide">
                                <div class="carousel-caption">
                                    Second Slide
                                </div>
                            </div>
                            <div class="item ">
                                <img src="${pageContext.request.contextPath}/static/assets_login/img/slide3.png" alt="Third slide">
                                <div class="carousel-caption">
                                    Third Slide
                                </div>
                            </div>
                                -->
                        </div>           
                    </div>
                </div><!-- /.box-body -->
                <div class="content">	
                    <!-- BEGIN LOGIN FORM -->

                    <form:form id="formlogin" action="${pageContext.request.contextPath}/login" method="POST" class="form-vertical login-form"  modelAttribute="loginForm"  >
                        
                        <h3 class="form-title">Login to your Account</h3>
                        <c:if test="${param.error != null}">
                            <div class="alert alert-error">
                                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                    Username/Password salah atau user id sedang digunakan
                                </c:if>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                You have been logged out.
                            </div>
                        </c:if>

                        <div class="form-group">
                            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                            <label class="control-label visible-ie8 visible-ie9">Username</label>
                            <div class="input-icon left">
                                <i class="icon-user"></i>
                                <input type="hidden" id="banyak" name="banyak" value="${banyak}" />
                                <form:hidden path="berita" id='berita'  value="${listBerita}"   />
                                <form:input   path="username"  cssClass="m-wrap placeholder-no-fix  required"  placeholder="Username"     autocomplete="off" cssStyle="width:100%;height:30px;"  id="username"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label visible-ie8 visible-ie9">Password</label>
                            <div class="input-icon left">
                                <i class="icon-lock"></i>
                                <form:password path="password" cssClass="m-wrap placeholder-no-fix required"  placeholder="Password" id="password"  autocomplete="off" cssStyle="width:100%;height:30px;"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label visible-ie8 visible-ie9">Tahun Anggaran</label>
                            <div class="input-icon ">

                                <jsp:useBean id="skrg" class="java.util.Date" />
                                <fmt:formatDate var="tahun" value="${skrg}" pattern="yyyy" />
                                <form:select id="tahun" path="tahun" cssClass="m-wrap placeholder-no-fix" placeholder="Tahun Anggaran" cssStyle="width:100%;height:30px;" >
                                    <option ${tahun - 2} >${tahun - 2}</option>
                                    <option ${tahun - 1} >${tahun - 1}</option>
                                    <option ${tahun} selected >${tahun}</option>

                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                            <label class="control-label visible-ie8 visible-ie9">Captcha</label>
                            <div class="input-icon">

                                <input type="text"  name="captchaCode" id="captchaCode" cssClass="m-wrap placeholder-no-fix  required"  placeholder="Kode Verifikasi"     autocomplete="off" cssStyle="width:100%;height:30px;"  />
                                <table cellpadding="4" id="captca">
                                    <tr><td ><span id="numb1" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive; font-weight: 600; font-stretch: expanded; "></span></td>
                                        <td><span id="numb2" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded; "></span></td>
                                        <td><span id="numb3" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded;"></span></td>
                                        <td><span id="numb4" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded; "></span></td>
                                    </tr>
                                </table>
                            </div>

                        </div> 


                        <div class="form-actions">
                            <a href="javascript:veri();" style="text-decoration: none;" class="blue btn  pull-right">
                                Login <i class="m-icon-swapright m-icon-white"></i>
                            </a>           
                        </div>			
                    </form:form>

                </div><!--Content-->
            </div>
        </div>
        <!-- BEGIN COPYRIGHT -->
        <div class="col-md-12">
            <div class="copyright">
                <!--2015 &copy; <a href="#">IT Centre - IAe</a> Login Form-->
                <p> ${tahun} &copy; Diskominfomas Pemprov DKI Jakarta. </p>
            </div>
        </div>
    </div>
    <!-- END COPYRIGHT -->
    <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
    <!-- BEGIN CORE PLUGINS -->   <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery-1.10.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/excanvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/respond.min.js"></script>  
    <![endif]-->   
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery.cookie.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
    <!-- END CORE PLUGINS -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets_login/plugins/select2/select2.min.js"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="${pageContext.request.contextPath}/static/assets_login/scripts/app.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/assets_login/scripts/login-soft.js" type="text/javascript"></script>      
    <!-- END PAGE LEVEL SCRIPTS --> 
    <script type="text/javascript">
        function getbasepath() {
            return '${pageContext.request.contextPath}';
        }
    </script>

    <script>
        $(document).ready(function() {
            $("#formlogin").validate();

            getrandomnum();
        });

        var a;
        var b;
        var c;
        var d;
        var e;
        var f;
        function getrandomnum() {
            a = Math.round(Math.random() * (9 - 0));
            b = Math.round(Math.random() * (9 - 0));
            c = Math.round(Math.random() * (9 - 0));
            d = Math.round(Math.random() * (9 - 0));
            e = Math.round(Math.random() * (6 - 0) + 1);
            f = a.toString() + b.toString() + c.toString() + d.toString();
            document.getElementById("numb1").innerHTML = a;
            document.getElementById("numb2").innerHTML = b;
            document.getElementById("numb3").innerHTML = c;
            document.getElementById("numb4").innerHTML = d;

            document.getElementById("captca").style = "width:35%;background-image:url('${pageContext.request.contextPath}/static/assets/img/capca" + e + ".jpg'); float:right;"
        }
        function veri() {

            var b = $("#captchaCode").val();
            if (f !== b) {
                alert("Kode Verifikasi Salah");
                console.log("salah");
                window.location = "${pageContext.request.contextPath}/login";

            } else {
                $("#formlogin").submit();
            }

        }

    </script>
    
    <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/login/login.js"></script>   
    
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/static/assets_login/scripts/bootstrap.min.js" type="text/javascript"></script>
</body>
<!-- END BODY -->
</html>