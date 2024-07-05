<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Dokumen Referensi</a></li>
</ul>
      <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Dokumen Referensi</div>
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
                        <th>Pemprov/Daerah</th>
                        <th>Daerah</th>
                        <th>Kota</th>
                        <th>No. Perda</th>
                        <th>Tanggal Perda</th>
                        <th>Tahun</th>
                        <th>Nama PPKD</th>
                        <th>NIP PPKD</th> 
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table> 
        </div>    
    </div>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/dokrefflist.js"></script>  