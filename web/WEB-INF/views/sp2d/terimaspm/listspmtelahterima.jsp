<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#"> TANDA TERIMA SPM</a></li>
</ul>

 <div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Tanda Terima SPM</div>
        
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="codeWilSp2dproses" >
                            <option value="" selected>Provinsi</option>
                            <option value="1">Jakarta Pusat</option>
                            <option value="2">Jakarta Utara</option>
                            <option value="3">Jakarta Barat</option>
                            <option value="4">Jakarta Selatan</option>
                            <option value="5">Jakarta Timur</option>
                            <option value="6">Kepulauan Seribu</option>
                            <option value="0">Balai Kota</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridtelahterimaspm()'>Cari</button>
                </div>
            </div>
        </form>
    </div>
</div>         
                    
<div class="portlet box">
    <div class="portlet-title">
    </div>
    
        <div class="portlet-body">
            <table id="telahterimaspmtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                       <th>No</th>
                        <th>Wilayah SP2D</th> 
                        <th>No SPM</th> 
                        <th>No Dokumen SPM</th>
                        <th>Jenis</th> 
                        <th>Beban</th> 
                        <th>Kode SKPD</th>
                        <th>Nama SKPD</th>
                        <th>Tanggal Cetak SPM</th>
                        <th>Keterangan SPM</th>
                        <th>Pilihan</th>
                        <th>Unduh PDF</th>
                    </tr>
                </thead>
                <tbody id="telahterimaspmtablebody" >
                </tbody>                
            </table>
        </div>
    
</div>

    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/terimaspm/telahterimaspmlist.js"></script>  
