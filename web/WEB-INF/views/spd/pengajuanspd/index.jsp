<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spd/pengajuan/btl/index">Pengajuan SPD</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Belanja Tidak Langsung</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form BTL</div>
        <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="getlistspdbtl(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu BTL:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="pagubtl" id="pagubtl" readonly="true" class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class=" col-md-3 control-label">Sisa SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="sisaspd" id="sisaspd"  readonly="true" class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>        
             
        </form>
    </div>
</div>        
<div class="portlet box">
    <div class="portlet-title">
        
    </div>
    <div>
        <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>SPD No</th>
                    <th>Tgl SPD</th>
                    <th>Nilai SPD</th>
                    <th>Status SPD</th>
                    <th>Pilihan</th>
                </tr>
            </thead>
            <tbody id="btlspdtablebody" >
            </tbody>
        </table>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/skpd/spdbtllist.js"></script>  
<script type="text/javascript">
                        $(document).ready(function() {
                            $(".fancybox").fancybox({
                                fitToView: true,
                                autoSize: true,
                                closeClick: true,
                                openEffect: 'swing',
                                closeEffect: 'swing',
                                headers: {'X-fancyBox': true}
                            });
    <c:if test="${not empty idskpd}">
                            getlistspdbtl('<c:out value="${idskpd}" />');
    </c:if>
                        });
                        function getlistspdbtl(id) {
                             $.getJSON("${pageContext.request.contextPath}/common/json/getpagudansisa/" + id,
                                    function(result) {
                                        $('#pagubtl').val(accounting.formatNumber(result.pagu));
                                        $('#sisaspd').val(accounting.formatNumber(result.vspd));
                                    });

                            gridspdbtlmasterlist("${pageContext.request.contextPath}");

                        }
                        function pindahhalamanadd() {
                            if ($("#idskpd").val().trim()) {
                                window.location.replace("${pageContext.request.contextPath}/spd/pengajuan/btl/addskpd/" + $("#idskpd").val().trim());
                            } else {
                                window.location.replace("${pageContext.request.contextPath}/spd/pengajuan/btl/add");
                            }
                        }
</script>