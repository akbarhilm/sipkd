<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Berita Acara</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Kode SKPD:</label>
                <div class="col-md-4">
                    <input type="text"  name="kodeskpd"  id="kodeskpd"  class="m-wrap large" size="20"  value="${kodeskpd}" />

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
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
                    <th>No</th>
                  
                    <th>Tanggal</th> 
                    <th>Nomor BAST</th> 
                    <th>id. Kontrak</th> 
                    <th>Nama Kegiatan</th>  
                    <th>id. Akun</th> 
                    <th>nilai Bast</th> 
                    <th>nilai Prestasi</th> 
                    <th>nama PPTK</th> 
                    <th>NIP PPTK</th> 
                    <th>Keterangan</th> 
                    <th>Pilih</th>
                    
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spptu/listbastpopup.js"></script>  