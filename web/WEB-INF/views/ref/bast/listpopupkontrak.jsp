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

<div class="portlet box red">
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
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd} / ${skpd.kodeSkpd}"  />
                    
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
     
    <div >
        <table id="kontrakpopup" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                 <tr>
                    <th rowspan="2">No</th>
                    <th>Kode Kegiatan</th> 
                    <th>Nama Kegiatan</th> 
                    <th>No Kontrak</th>  
                    <th  rowspan="2">nama SKPD</th> 
                    <th  rowspan="2">Nilai BAST</th> 
                    <th  rowspan="2">Nilai Kontrak</th> 
                    <th rowspan="2" >Pilih</th>
                    
                </tr>
                 <tr>
                    
                     <th><input type="text" style="width: 99%;border: 0" id="kodekegiatanfilter"  onkeyup=""/></th> 
                    <th><input type="text" style="width: 99%;border: 0"  id="namakegiatanfilter"  /></th> 
                    <th><input type="text" style="width: 99%;border: 0" id="nokontrak" onkeyup="cekgrid()"/></th>  
                     
                    
                </tr>
               
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listpopupkontrak.js"></script>  