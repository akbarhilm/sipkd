<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Cetak SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  Cetak</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak  SPJ</div>
       
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" name="banyakspjbelumsah"  id="banyakspjbelumsah" maxlength="4" value="${banyakspjbelumsah}"  readOnly="true"/> 
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readOnly="true"/> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppup()"  />
                    <input type="hidden" id="kodeSkpd" name="kodeSkpd" value="${skpd.kodeSkpd}"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="70"  value="${skpd.namaSkpd}" readOnly="true"/>
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                    </div>
                </div>
            </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">
        <div class="top">
        </div>
        <div class="portlet-body">
            <table id="cetakspjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr  >
                    <th>No</th>
                    <th>SPJ No</th>
                    <th>Nilai SPJ UP</th>
                    <th>Nilai SPJ TU</th>
                    <th>Bulan SPJ</th>
                    <th>SPJ Nihil</th>
                    <th>Status</th>
                    <th>Tgl Cetak</th>
                    <th>Pilih</th>
                    <th>Unduh PDF</th>
                    <th>Kembali</th> 
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>



<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/spj/cetakspj.js"></script>   