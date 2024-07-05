<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  SPP BIAYA</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  SPP BIAYA</div>
        <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppbiaya()"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="90"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd} " readonly="true" />
                     <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No Spp</th>
                        <th>Tanggal Spp</th>
                        <th>Nilai Spp</th> 
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>
 
 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbiaya/sppbiaya.js"></script>  
