<%-- 
    Document   : listrekanan
    Created on : Jul 16, 2014, 9:26:26 AM
    Author     : reyvan adryan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Rekanan</a></li>
</ul>
 
      <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Rekanan</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Rekanan:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="text"  name="namarekan"  id="namarekan"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30" 
                                   onkeyup="if (event.keyCode == 13)gridrekananlist()" />
                        </div>
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn dark" onclick='gridrekananlist()'>Cari</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portlet box">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Rekanan</div>

        </div>
        <div >
            <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>Kode</th>
                        <th>Identitas</th>
                        <th>Nama</th>
                        <th>Nama Direktur</th>
                        <th>NPWP</th>
                        <th>Alamat</th>
                        <th>Pilih</th>
                       
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/rekananlist.js"></script>  