<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  SPP BTL BANTUAN</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  SPP BTL BANTUAN</div>
        <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppbantuan()"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="50"  value=" ${skpd.kodeSkpd}/${skpd.namaSkpd}"  />

                    
                </div>
            </div>
                   <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD Koordinator:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpdkoor" name="idskpdkoor" onchange="gridsppbantuan()"  />
                   <input type="text"  name="namaSkpdKoor"  id="namaSkpdKoor"  class="m-wrap large" size="50" readonly="true"  />&nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green"  href="${pageContext.request.contextPath}/sppbantuan/listskpdkoorpopup?target='_top" title="Pilih SKPD Koordinator"  ><i class="icon-zoom-in"></i> Pilih</a> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}" readonly="true" class="m-wrap medium inputnumber" />
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
                        <th>Nomor SPP</th>
                        <th>Tanggal SPP</th>
                        <th>No / Nama Kegiatan</th>
                        <th>Akun</th>
                        <th>Nilai SPP</th> 
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>
 
 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbantuan/sppbantuan.js"></script>  
