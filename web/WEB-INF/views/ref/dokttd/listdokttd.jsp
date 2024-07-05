<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Dokttd</a></li>
</ul>
 
   
    <div class="portlet box">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Fungsi</div>

        </div>
        <div >
            <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>NO</th> 
                        <th>CDok</th>
                        <th>NIP</th>
                        <th>NRK</th>
                        <th>Nama</th>
                        <th>Jabatan</th>
                        <th>MIN</th>
                        <th>MAX</th>
                        <th>Aktif</th>
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
 
 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listdokttd.js"></script>  
