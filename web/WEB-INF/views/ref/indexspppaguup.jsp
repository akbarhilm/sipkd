<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spd/pengajuan/btl/index">Referensi</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Pagu SPP GUP/UP</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Pagu SPP GUP/UP</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />

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
                    <button type="button" class="btn dark" onclick='gridpagusppup()'>Cari</button>
                </div>
            </div>

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div>
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tahun</th>
                        <th>SKPD</th>
                        <th>Nilai</th>
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>
            </table>
        </div>
        <div class="portlet-title">
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='submitnilaispp( )'>Simpan</button>
                </div>
            </div>
        </div>
    </form>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/pagusppguup.js"></script>  
