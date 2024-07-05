<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8" />
        <title>SIPKD | Sistem Informasi Pengelolaan Keuangan Daerah</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <%/*  <meta name="_csrf" content="${_csrf.token}"/>
             <meta name="_csrf_header" content="${_csrf.headerName}"/> */ %>
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
        <script src="${pageContext.request.contextPath}/static/assets/scripts/maskedinput.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-serialize/jquery.serialize-object.compiled.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-arithmetics/jquery.basic_arithmetics.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-date/moment.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootsbox/bootbox.min.js"></script>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/ico/money.png" />
    </head>
    <body class="page-header-fixed page-full-width" >
        <!-- BEGIN HEADER -->
        <div id='header' class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="header-inner">

                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                    <img src="${pageContext.request.contextPath}/static/assets/img/logo_e-sipkd.png" alt="logo"   class="img-responsive" />
                </a>
                <c:set var="skpd" value="${pengguna.skpd[0]}" scope="session" />
                <!-- BEGIN HORIZANTAL MENU -->
                <div class="hor-menu hidden-sm hidden-xs">
                    <ul class="nav navbar-nav">
                        <c:if test="${pengguna.kodeGrup == '30'}">
                            <li  >
                                <a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" class="dropdown-toggle" href="javascript:;">
                                    <span class="selected"></span>
                                    ESETTLEMENT
                                    <i class="icon-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/eset/sp2d">SP2D</a></li>
                                    
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/listdatabank/sp2d">list data</a>
                            </li>
                                

                           
                            
                        </c:if>

                    </ul>
                </div>
                <!-- END HORIZANTAL MENU -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <img src="${pageContext.request.contextPath}/static/assets/img/menu-toggler.png" alt="" />
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <ul class="nav navbar-nav pull-right">
                    <!-- BEGIN NOTIFICATION DROPDOWN -->


                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <span class="username">Tahun Anggaran : ${tahunAnggaran}</span>|<span class="username">${pengguna.nip}</span> / <span class="username">${pengguna.namaPengguna} </span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a  href="${pageContext.request.contextPath}/ubahpass/index"><i style="display:inline" class="icon-key"></i> Ubah Password</a></li>
                            <li><a href="${pageContext.request.contextPath}/logout"><i class="icon-key"></i> Log Out</a></li>
                            <li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SPJ.pdf"><i class="icon-key"></i> User Manual </a></li>
                            <li><a href="${pageContext.request.contextPath}/static/pdf/PROSES_SIPKD_PENATAUSAHAAN.pdf"><i class="icon-key"></i> PROSES SIPKD PENATAUSAHAAN </a></li>

                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        <!-- END HEADER -->
        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container" >
            <!-- BEGIN PAGE -->
            <div class="page-content">

                <!-- end sidebar-->
                <sitemesh:write property='body'/>
                <!-- END PAGE CONTENT-->
            </div>
            <!-- END PAGE -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="footer">
            <%  /*    <div class="footer-inner">
                 2014 &copy; Diskominfomas Pemprov DKI Jakarta.
                 </div>
                 <div class="footer-tools">
                 <a class="go-top" href="#">
                 <i class="icon-angle-up"></i>
                 </a>
                 </div>
                 </div>*/ %>
            <!-- END FOOTER -->
            <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
            <!-- BEGIN CORE PLUGINS -->
            <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
            <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/static/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>

            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/scripts/maskedinput.js"></script>
            <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-timer/jquery.timer.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/akunting.js"></script>
            <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/autoNumeric-2.0-BETA.js"></script>
            <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/numericpack.min.js"></script>
            <script src="${pageContext.request.contextPath}/static/assets/scripts/app.js"></script>
            <script src="${pageContext.request.contextPath}/static/assets/scripts/sidebar.js"></script>
            <script type="text/javascript">

                $(document).ready(function() {

                    // App.init();
                    /* var token = $("meta[name='_csrf']").attr("content");
                     var header = $("meta[name='_csrf_header']").attr("content");
                     $(document).ajaxSend(function (e, xhr, options) {
                     xhr.setRequestHeader(header, token);
                     });*/
                    var nowTemp = new Date();
                    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
                    $(".entrybulan").mask("99/" + ${sessionScope.tahunAnggaran});
                    $(".dropdown-toggle").dropdown();
                    $("#dp1").datepicker({endDate: "+0d", language: "id", autoclose: "true", todayHighlight: "true"});
                    $("#dp2").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp3").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp4").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp5").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp6").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp7").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp8").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp9").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#dp10").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                    $("#loading").append("<div class='loadingAjax' id='ajaxBusy'><p><img src='${pageContext.request.contextPath}/static/ico/ajaxLoading.gif'></p></div>");
                    $(document).ajaxStart(function() {
                        $("#ajaxBusy").show()
                    }).ajaxStop(function() {
                        $("#ajaxBusy").hide()
                    });
                    $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
                    //  $(".inputinvoice").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                    $.validator.addMethod("accept", function(value, element, param) {
                        return value.match(new RegExp("^" + param + "$")) || value == ""
                    }, "Hanya boleh huruf dan angka!");
                    $.validator.addMethod("ruleWajib", function(value, element) {
                        console.log(value);
                        return parseFloat(accounting.unformat(value)) > 0
                    }, "Wajib diisi .");
                    $.validator.addMethod("ruleA03", function(value, element) {
                        return this.optional(element) || /^[0-9.,]+$/.test(value)
                    }, "Hanya boleh menggunakan angka dan simbol .");
                    $.validator.addMethod("ruleA04", function(value, element) {
                        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value)
                    }, "Hanya boleh menggunakan huruf dan angka");
                    $.validator.addMethod("ruleA05", function(value, element) {
                        return this.optional(element) || /^[a-zA-Z0-9/.,+: '"()-]*$/i.test(value)
                    }, "Hanya boleh menggunakan huruf, angka dan simbol .,''':+-/() spasi");
                    $.validator.addMethod("ruleAlamat", function(value, element) {
                        return this.optional(element) || /^[AC-IK-NP-TVWY\r\sa-zA-Z0-9/.,+: '"()-]*$/i.test(value)
                    }, "Hanya boleh menggunakan huruf, angka dan simbol .,''':+-/() spasi enter");
                    $.validator.addMethod("ruleCekKabupaten", function(value, element) {
                        return eval(value) > 0
                    }, "Wajib diisi .");
                    $.validator.addMethod("indoDate", function(value, element) {
                        return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/) || value == ""
                    }, " format hanya boleh dd/mm/yyyy.");
                    $.validator.addMethod("ruleCekBulan", function(value, element) {
                        var arrbulan = $.trim(value).length > 0 ? value.split("/") : "";
                        if (arrbulan.length == 2)
                            return eval(arrbulan[0]) > 0 && eval(arrbulan[0]) < 13
                        else
                            false;
                    }, "Wajib diisi dengan bulan antara 01 - 12.");
                    $.validator.addMethod("ruleCekBulanSekarang", function(value, element) {
                        var arrbulan = $.trim(value).length > 0 ? value.split("/") : "";
                        var tgl = new Date();
                        var bulan = tgl.getMonth() + 1
                        if (arrbulan.length == 2)
                            return eval(arrbulan[0]) > 0 && eval(arrbulan[0]) < 13 && eval(arrbulan[0]) <= bulan
                        else
                            false;
                    }, "Wajib diisi dengan bulan antara 01 - 12, dan tidak boleh lebih besar dari bulan sekarang.");
                    $.validator.addMethod("bulanLebihBesar",
                            function(value, element, params) {
                                var valBulanAwal = moment("01/" + value, "DD/MM/YYYY");
                                var valBulanAkhir = moment("01/" + $(params).val(), "DD/MM/YYYY");

                                return   (valBulanAwal.diff(valBulanAkhir, 'days') >= 0);
                            }, 'Harus lebih besar dari {0}  ');
                    $.validator.addMethod("angkaLebihBesar",
                            function(value, element, params) {
                                console.log(" angkaLebihBesar ");
                                var nilaiSpd = accounting.unformat($(params).val(), ",");
                                var nilaiangg = accounting.unformat(value, ",");
                                return   (nilaiSpd > nilaiangg);
                            }, 'Nilai SPD harus lebih kecil dari nilai anggaran!');

                    $.validator.addMethod("ruleCekNamaRekanan", function(value, element) {

                        var namarekan = value.toLowerCase();
                        var dkinamarekan; //= $("#dkinama").val().toLowerCase();
                        var kodeva = $("#kodeVA").val();

                        if ($("#kodeBank").val() !== "111" || kodeva == 1) {
                            dkinamarekan = namarekan;
                        } else {
                            dkinamarekan = $("#dkinama").val().toLowerCase();
                        }
                        console.log("ruleCekNamaRekanan dkinamarekan = " + dkinamarekan);
                        console.log("ruleCekNamaRekanan namarekan = " + namarekan);
                        return namarekan == dkinamarekan;
                    }, "Nama Harus Sesuai dengan Nama dari Data Bank DKI");

                    $.validator.addMethod("ruleCekNamaTujuan", function(value, element) {

                        var namatujuan = value.toLowerCase();
                        var cek; //= $("#dkinama").val().toLowerCase();

                        if (namatujuan == "" || namatujuan == null) {
                            cek = "kosong";
                        } else {
                            cek = namatujuan;
                        }
                        console.log("namatujuan = " + namatujuan);
                        console.log("cek = " + cek);

                        return namatujuan == cek;
                    }, "Nama Tujuan Tidak Boleh Kosong");

                    $.validator.addMethod("ceknourutltklexist", function(value, element) {
                        var result = false;
                        var noltklself = "${noltkl}";
                        if ($.trim(noltklself).length > 0) {
                            var arrltkl = noltklself.split("-");
                            result = arrltkl[2] == value ? true : false
                        }
                        if (!result) {
                            $.ajax({type: "POST", async: false, url: "${pageContext.request.contextPath}/historyltkl/json/ceknoltklisexist", data: {noltklsegmendua: value}, success: function(data) {
                                    result = data
                                }})
                        }
                        return result
                    }, "No urut   Sudah Digunakan");
                    formatnumberonkeyup();
                    accounting.settings = {currency: {symbol: "Rp", format: "%s%v", decimal: ",", thousand: ".", precision: 2}, number: {precision: 0, thousand: ".", decimal: ","}}
                    $(".date-picker").datepicker({endDate: "+0d", language: "id", autoclose: "true", todayHighlight: "true"});
                <%



                    final java.util.Date currentDate = new java.util.Date();
                    final java.text.SimpleDateFormat DATE_FORMAT = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    final String date = DATE_FORMAT.format(currentDate);
                %>
                    $(".date-picker").datepicker({
                        dateFormat: "dd/mm/yyyy",
                        "setDate": '<%=date%>',
                        "autoclose": true,
                        language: "id"

                    });
                    $(".date-picker").click(function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                    });
                    // $(".date-picker").val(myDate);
                    $(".date-picker2").datepicker({language: "id", autoclose: "true", todayHighlight: "true"});
                    $(".entrytanggal").mask('<%=date%>');
                    $(".entrytanggal2").mask('99/99/9999');
                });
                $(document).ajaxStart(function() {
                    $("#ajaxBusy").show()
                }).ajaxStop(function() {
                    $("#ajaxBusy").hide()
                });
                function IsNumeric(b) {
                    b = parseInt(b);
                    return(b - 0) == b && b.length > 0
                }
                function popupwindow(i, k, j, h) {
                    var l = (screen.width / 2) - (j / 2);
                    var g = (screen.height / 2) - (h / 2);
                    return window.open(i, k, "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=" + j + ", height=" + h + ", top=" + g + ", left=" + l)
                }
                function preventBack() {
                    window.history.forward()
                }
                function settextbootalert(d, f, e) {
                    $("#" + f).empty();
                    $("#" + d).show();
                    $("#" + f).html(e)
                }
                function closewindow() {
                    /* if (screenfull.enabled) {
                     screenfull.toggle(this);
                     }*/
                }
                function getbasepath() {
                    return '${pageContext.request.contextPath}';
                }
                ;
                function formatnumberonkeyup() {
                    $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
                    $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                }


            </script>
    </body>
</html>
