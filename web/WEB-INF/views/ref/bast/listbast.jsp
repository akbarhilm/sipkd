<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Berita Acara Serah Terima</a></li>
</ul>

<<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Berita Acara Serah Terima</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
             <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppbantuan()"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />
                </div>
            </div>
                      <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10"  value="${tahunAnggaran}" class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
           
        </form>
    </div>
</div>        


<div class="portlet box">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Berita Acara</div>

    </div>
    <div >
        <table id="basttable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th rowspan="2">No</th>
                    <th rowspan="2">Tanggal</th>
                     <th><input type="text"  style="border:none;margin:0;width:98%;" id="nomorbastfilter" /></th>
                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodekegiatanfilter" /></th>
                     <th><input type="text"  style="border:none;margin:0;width:98%;" id="namakegiatanfilter" /></th>
                    <th rowspan="2">Nilai Bast</th> 
                    <th rowspan="2">Nilai Prestasi</th> 
                    <th rowspan="2">Nama PPTK</th> 
                    <th rowspan="2">NIP PPTK</th> 
                    <th rowspan="2">Keterangan</th> 
                    <th rowspan="2">Edit</th>                       
                </tr>
                <tr> 
                    <th>Nomor BAST</th>
                   <th>Kode Kegiatan</th>
                   <th>Nama Kegiatan</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listbast.js"></script>  