<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8" />
        <title>Dashboard</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <%/*  <meta name="_csrf" content="${_csrf.token}"/>
             <meta name="_csrf_header" content="${_csrf.headerName}"/> */%>
        <!--<link href="${pageContext.request.contextPath}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
        -->

        <!--CSS-->
        <!-- Datatables -->
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        <link type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/animate.css/animate.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/nprogress/nprogress.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/css/custom.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/css/select2.min.css" /> <!--untuk search otomatis-->
        <link type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.css" />-->

        <!-- bootstrap-progressbar -->
        <link type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
        <!-- JQVMap -->
        <link type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
        <!-- bootstrap-daterangepicker -->
        <link type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <!--untuk live search
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->
<!--         <link type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-select-1.13.2/dist/css/bootstrap-select.css" rel="stylesheet">
         <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-select-1.13.2/dist/js/bootstrap-select.js" ></script>-->
                  <!--<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" ></script>-->
          <!--<link type="text/css" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">-->

        <!--<script data-require="jquery@2.2.4" data-semver="2.2.4" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>-->



    </head>
    <body class="nav-md footer_fixed">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col menu_fixed">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="#" class="site_title">
                                <i class="fa"><img src="${pageContext.request.contextPath}/static/img/dbicn6.png" width='40' height='40'>
                                </i>
                                <span>Dashboard</span>
                            </a>
                            <div class="clearfix"></div>
                        </div>

                        <div class="clearfix"></div>

                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <!--<img style="margin: 0 auto" src="${pageContext.request.contextPath}/static/img/logo_thumbnail.png" alt="..." class="img-container">-->
                            </div>
                            <!--                            <div class="profile_info">
                                                            <span>Welcome,</span>
                                                            <h2>Admin</h2>
                                                        </div>-->
                            <div class="clearfix"></div>
                        </div>
                        <!-- /menu profile quick info -->

                        <br />

                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <ul class="nav side-menu">
                                    <li><a><i class="fa fa-bar-chart-o"></i> Menu <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                           
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-pie-chart"></i>Menu <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                            <li><a href="#">Sub Menu</a></li>
                                            </ul>
                                    </li>

                                </ul>
                            </div>
<!--                            <div >
                                <center>
                                    <img src="${pageContext.request.contextPath}/static/img/logo-dki_tranparan_90.png">
                                    <br>
                                    <br>
                                    <img src="${pageContext.request.contextPath}/static/img/LOGO_DISDIK_DKI.png">
                                </center>
                            </div>-->

                        </div>
                        <!-- /sidebar menu -->

                        <!-- /menu footer buttons -->
                    </div>
                </div>

                <!-- top navigation -->
                <div class="top_nav">
                    <div class="nav_menu">
                        <nav>
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>

                            <ul class="nav navbar-nav navbar-right">
                                
                                <li class="">
                                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
<!--                                        <img src="" alt="">-->
                                        <span id='lu'>Last Update </span>
                                    </a>
                                </li>

                            </ul>
                        </nav>
                    </div>
                </div>
                <!-- /top navigation -->

                <!-- page content -->
                <div class="page-container" >
                    <div class="page-content">
                        <sitemesh:write property='body'/>
                    </div>
                </div>
                <!-- /page content -->

                <!-- footer content -->
                <footer style="opacity: 0.9">
                    <div class="pull-right">
                        Dashboard © 2019 - Pemerintah Provinsi DKI Jakarta
                    </div>
                    <div class="clearfix"></div>
                </footer>
                <!-- /footer content -->
            </div>
        </div>

        <!--JS-->
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jquery/dist/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
       <!--<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/fastclick/lib/fastclick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/nprogress/nprogress.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Chart.js/dist/Chart.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/custom.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/akunting.js"></script>
        <!-- iCheck -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/iCheck/icheck.min.js"></script>
        <!-- Skycons -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/skycons/skycons.js"></script>
        <!-- Flot -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Flot/jquery.flot.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Flot/jquery.flot.pie.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Flot/jquery.flot.time.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Flot/jquery.flot.stack.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/Flot/jquery.flot.resize.js"></script>
        <!-- Flot plugins -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/flot.curvedlines/curvedLines.js"></script>
        <!-- DateJS -->
        <script stype="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/DateJS/build/date.js"></script>
        <!-- JQVMap -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jqvmap/dist/jquery.vmap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
        <!-- bootstrap-daterangepicker -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/moment/min/moment.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
        <!-- Datatables -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jszip/dist/jszip.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/pdfmake/build/pdfmake.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/pdfmake/build/vfs_fonts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/select2.min.js"></script> <!--untuk pencarian otomatis-->

        <!--script untuk pencarian-->
<!--        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>-->
        <!--script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
        <!--<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>-->


<!--    ini    <link data-require="bootstrap@3.3.7" data-semver="3.3.7" rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-select/bootstrap.min.css" />
<script data-require="bootstrap@3.3.7" data-semver="3.3.7" src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-select/bootstrap.min.js"></script>-->

<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-select/css/bootstrap-select.min.css" />
 <script src="${pageContext.request.contextPath}/static/assets/vendors/bootstrap-select/js/bootstrap-select.min.js"></script>
 sini -->

        <!--        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
                <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script> -->
        <script type="text/javascript">
            function getbasepath() {
                return '${pageContext.request.contextPath}';
            }
            function getSysdate(callback) {
                $.getJSON(getbasepath() + "/beranda/json/getDate",
                        function (result) {
                            callback(result);
                        });
            }
            $(document).ready(function () {
                getLastUpdate();
            });
            function getLastUpdate(){
                $.getJSON(getbasepath() + "/dash/json/lastupdate",
                        function (result) {
                            $("#lu").append(result.aaData[0]['LASTUPDATE']);
                        });
            }
        </script>
    </body>
</html>
