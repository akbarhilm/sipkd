<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">

  
    
    </script>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Tanda Terima SPM</a></li>
</ul>

      <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Tanda Terima SPM</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input  id="tahun" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-3 control-label">Kode Wilayah SP2D :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <select id="wilayahSp2d" onchange="grid();" >
                                <option value ="8">Pilih Wilayah</option>
                                <option value ="0">PROVINSI</option>
                                <option value ="1">PUSAT</option>
                                <option value ="2">UTARA</option>
                                <option value ="3">BARAT</option>
                                <option value ="4">SELATAN</option>
                                <option value ="5">TIMUR</option>
                                <option value ="6">P. SERIBU</option>
                                <option value ="7">BALAIKOTA</option>
                        </select>
                        </div>
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn dark" id="car" onclick='cetak()' disabled>Cetak</button>
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
                        <th>No SPM</th>
                        <th>Jenis</th>
                        <th>Beban</th>
                        <th>Tanggal Terima</th>
                        <th>Jumlah Hari</th>
                        <th>No SP2D</th>
                        <th>Tanggal SP2D</th>
                        <th>SKPD</th>
                        <th>Keterangan SPM</th>
                       
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/statusspm/listspm.js"></script>  