<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />   
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="Sistem Informasi Pengelolaan Keuangan Daerah" name="description" />
        <meta content="PT Dirgantara Indonesia" name="author" />
        <meta name="MobileOptimized" content="320">
        <link rel="shortcut icon" href="favicon.ico" />
        <style>
            *
            {
                margin: 0;
                padding: 0;
            } @page {
                size:F4  ;
                page-break-before: always;
                page-break-after: auto;
            }
            html, body, #Container
            {
                height: 100%; 
                size: legal;
            }

            #Container:before
            {
                content: '';
                height: 100%;
                float: right;
            }
            #Copyright
            {

            }

            #Stretch
            {

            }
            #Stretch:after
            {
                content: '';
                display: block;
                clear: both;    
            }

            #Container, #Container > div
            {
                -moz-transform: rotateX(180deg);
                -ms-transform: rotateX(180deg);
                -o-transform: rotate(180deg);
                -webkit-transform: rotateX(180deg);
                transform: rotateX(180deg);
            }
        </style>
    </head>
    <body  >
        <div id="Container" style="text-align: right;">
            <div id="Copyright" >
                <img src="${pageContext.request.contextPath}/spjsah/print/cetakbarcodepengesahanbynospj/${nospd}"> <br/>
                 ${dataspj.D_SPJ_SAH} 
                 <fmt:formatNumber maxFractionDigits="3" value="${dataspj.V_SPJ}" /> 
            </div>
            <div id="Stretch">
               
            </div>
        </div>





        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/respond.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/excanvas.min.js"></script> 
        <![endif]-->   


        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>

