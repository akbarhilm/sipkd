<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  SP2D </a></li>
</ul>
<div class="portlet box red">
  <!--  <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Register  SP2D</div>
        <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>-->
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                  <!--  <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppup()"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>-->
                    
                    <input type="hidden" id="idskpd" onchange="gridsppup(this.value)" name="idskpd" value="${idskpd}"  /> 
                   <!-- <input type="hidden" id="skpd" name="skpd" class="m-wrap large" size="40"  value="${namaskpd}" />-->
                    <input type="hidden" id="kproses" name="kproses" value="${pengguna.kodeProses}"  />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                   <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpdall?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                    
                    
                    
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
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridpagusppup()'>Cari</button>
                </div>
            </div>

        </form>
    </div>
</div>        
<div class="portlet box">
    <div class="portlet-title">
    </div>
    <div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                       <th>No</th>
                        <th>Nomor SPM</th> 
                        <th>Nomor SP2D</th> 
                        <th>Tgl SPM</th>
                        <th>Tgl SP2D</th>
                        <th>Kode Potongan</th> 
                        <th>Kode Beban</th> 
                        <th>Kode Jenis</th> 
                        <th>Nilai SP2D</th>
                        <th>Status Cetak</th>
                        <th>Nama KBUD</th>
                        <th>Validasi</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
   
</div>

    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/sp2dup.js"></script>  
