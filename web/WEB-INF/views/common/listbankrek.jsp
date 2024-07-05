<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Rekening Bank</a></li>
</ul>
 
      <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Rekening Bank</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Bank:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="text"  name="namabankrek"  id="namabankrek"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30" 
                                   onkeyup="if (event.keyCode == 13)grid()" />
                        </div>
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portlet box">
         
        <div >
            <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>SKPD</th>
                        <th>Kode</th>
                        <th>Nama</th>
                        <th>BANK STS</th>
                        <th>BANK SPM</th>
                        <th>Tahun Berlaku</th>
                        <th>Tahun Berakhir</th>
                        <th>Status</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listbankrek.js"></script>  
