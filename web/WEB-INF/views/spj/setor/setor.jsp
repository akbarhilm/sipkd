<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Setor</a></li>
</ul>
        
 <div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Setor Biaya Langsung</div>
        <div class="actions">
            <a href="${pageContext.request.contextPath}/setor/addsetor"  class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>
       <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridspptu()"  />
                    <input type="text"  name="skpd" readonly="true"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}"  />
                     <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" readonly="true" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="portlet box">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Fungsi</div>

        </div>
        <div >
            <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>No Setor</th>
                        <th>Kegiatan</th>
                        <th>Tanggal Setor</th>
                        <th>Tahun Anggaran</th>
                        <th>Jenis</th>
                        <th>Temuan</th>
                        <th>Nilai Setor</th>
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
       <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spjsetor/setor.js"></script>  