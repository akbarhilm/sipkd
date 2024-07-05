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
        <base href="">
        <meta charset="utf-8" />
        <title>Sistem Informasi Pengelolaan Keuangan Daerah</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="Diskominfomas Pemprov DKI Jakarta" />

        <% /*<meta name="_csrf" content="${_csrf.token}"/> 
            <meta name="_csrf_header" content="${_csrf.headerName}"/> */%>
        <!-- Le styles -->
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/pages/login.css" rel="stylesheet" type="text/css"/>          
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>     
        <!--[if lt IE 9]>
              <script src="${pageContext.request.contextPath}/static/assets/js/html5shiv.js"></script>
            <![endif]-->
    <body class='login'> 
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
        <div class="content">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/static/assets/img/logo_e-sipkd-big.png" alt="logo" class="img-responsive" />  
            </div>
            <form:form id="formlogin" action="${pageContext.request.contextPath}/login" method="POST"  modelAttribute="loginForm"> 
                <h3 class="form-title">Login to your account</h3> 
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
                    <div class="input-icon">
                        <i class="icon-user"></i>
                        <form:input   path="username"  cssClass="m-wrap placeholder-no-fix  required"  placeholder="Username"     autocomplete="off" cssStyle="width:100%;height:30px;"  id="username"  />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Password</label>
                    <div class="input-icon">
                        <i class="icon-lock"></i>
                        <form:password path="password" cssClass="m-wrap placeholder-no-fix required"  placeholder="Password" id="password"  autocomplete="off" cssStyle="width:100%;height:30px;"  />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Tahun Anggaran</label>
                    <div class="input-icon">
                        <i class="icon-lock"></i>
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
                        <i class="icon-user"></i>
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

                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->



                    <!--botDetect:captcha id="jQueryValidatedCaptcha" userInputID="captchaCode"
                                       imageWidth="270"
                    
                    /-->
                </div>  


                <div class="form-actions">
                    <!--                    <input type="hidden"
                                               name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>-->
                    <!--<button type="submit" class="blue btn  pull-right">
                                            Login <i class="m-icon-swapright m-icon-white"></i>
                                        </button>  -->
                    <a href="javascript:veri();" style="text-decoration: none;" class="blue btn  pull-right">
                        Login <i class="m-icon-swapright m-icon-white"></i>
                    </a>               
                </div>
            </form:form>


        </div> <!-- /container -->


        <script type="text/javascript">

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
                if (f != b) {
                    alert("Kode Verifikasi Salah");
                    console.log("salah");
                    window.location = "${pageContext.request.contextPath}";

                } else {
                    $("#formlogin").submit();
                }

            }
        </script>

        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/login/login.js"></script>   


    </body>
</html>