<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listrefskpd.js"></script>
<style>

</style>
<ul class="breadcrumb">
    
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Daftar Skpd</a>
    </li>
</ul>
<div style="float: right;margin-bottom: 10px;">
    <button type="button" id="insertskpd" class="btn btn-defaul blue" onclick="pindahhalinsert()">Tambah</button>
    <div  style="display: none"><a id="popuptambahskpd" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
    <button type="button" id="editskpd" class="btn btn-defaul blue" onclick="pindahhaledit()">Simpan</button>
    <div  style="display: none"><a id="popupeditskpd" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
    <button type="button" id="reports" class="btn btn-defaul blue" onclick="pindahhalcetakskpd()">Cetak</button>
    <div  style="display: none"><a id="popupcetakskpd" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
</div>
<table  id="jstree_skpd" class="table table-striped table-full-width table-responsive table-condensed"     >

    <thead>
        <tr> <th>#</th> <th>Nama</th><th>Kode SKPD</th><th>Kode Unit Kerja</th><th>Aktif</th></tr> 
    </thead>
    <tbody>
        <tr> <td></td> <td></td><td></td> <td></td>  </tr> 
    </tbody>
</table> 

