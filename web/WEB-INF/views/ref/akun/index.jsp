<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listrefakun.js"></script>
 
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Daftar Akun</a>
    </li>
</ul>
<div style="float: right;margin-bottom: 10px;">
    <button type="button" id="tambahakun" class="btn btn-defaul blue" onclick="pindahhaltambah()">Tambah</button>
    <div  style="display: none"><a id="popuptambahakun" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
    <button type="button" id="tambahakun" class="btn btn-defaul blue" onclick="pindahhal()">Ubah</button>
    <div  style="display: none"><a id="popupubahakun" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
    <button type="button" id="reports" class="btn btn-defaul blue" onclick="pindahhalcetakakun()">Cetak</button>
    <div  style="display: none"><a id="popupcetakakun" href="#" class="fancybox fancybox.iframe" ></a></div>
    <input type="hidden" name="keyalamat" id="keyalamat" />
</div>
<table  id="jstree_akun" class="table table-striped table-full-width table-responsive table-condensed  fancytree-radio"     >
    <thead> 
        <tr><th>#</th> <th>Nama Akun</th><th>Aktif</th></tr>  
    </thead>
    <tbody>
        <tr> <td></td> <td></td><td></td></tr> 
    </tbody>
</table> 