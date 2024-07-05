<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]--> 
    <head>
        <meta charset="utf-8" />
        <title>SIPKD | Sistem Informasi Pengelolaan Keuangan Daerah</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        
        <link href="${pageContext.request.contextPath}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME STYLES --> 
        <link href="${pageContext.request.contextPath}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/custom.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.css" />          
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/fancybox/source/jquery.fancybox.css" />

        <!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/static/assets/plugins/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/respond.min.js"></script>  
<![endif]-->  
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>    
        <script src="${pageContext.request.contextPath}/static/assets/scripts/hogan-2.0.0.min.js" type="text/javascript"></script>   
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/data-tables/jquery.dataTables.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.js"></script> 		
        <script src="${pageContext.request.contextPath}/static/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootsbox/bootbox.min.js"></script> 


        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/ico/money.png" />
    </head> 
    <body class="page-header-fixed page-full-width"  >
        <div class="page-container" >
            <!-- BEGIN PAGE -->
            <div class="page-content">



                <!-- END PAGE HEADER-->
                <!-- BEGIN PAGE CONTENT-->
                <sitemesh:write property='body'/>
                <!-- END PAGE CONTENT--> 
            </div>
            <!-- END PAGE -->    
        </div>
        <div class="footer">
            <div class="footer-inner">
                2014 &copy; Diskominfomas Pemprov DKI Jakarta.
            </div>
            <div class="footer-tools">
                <a class="go-top" href="#">
                    <i class="icon-angle-up"></i>
                </a>
            </div>
        </div>  
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/scripts/maskedinput.js"></script>    
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script> 


        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/akunting.js"></script>
        <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/numericpack.min.js"></script> 
        <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/autoNumeric-2.0-BETA.js"></script> 
        
        <script src="${pageContext.request.contextPath}/static/assets/scripts/app.js"></script>      
        <script>
            function getbasepath() {
                return '${pageContext.request.contextPath}';
            }
            ;
        </script>      
        <script type="text/javascript">

            $(document).ready(function() {
                formatnumberonkeyup();
                accounting.settings = {currency: {symbol: "Rp", format: "%s%v", decimal: ",", thousand: ".", precision: 2}, number: {precision: 0, thousand: ".", decimal: ","}};
                $(".date-picker").datepicker({endDate: "+0d", language: "id", autoclose: "true", todayHighlight: "true"});
                $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
            });
            
            function formatnumberonkeyup() {
                $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            }
        </script>
    </body> 
</html>