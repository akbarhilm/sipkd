<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D </a> 
        <span class="icon-angle-right"></span>
    </li>    
   
    <li><a href="#">Cetak SP2D</a></li>
</ul>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak SP2D </div>
        <% /* <div class="actions">
             <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
             </div> */%>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="getlistspdcetak(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
          
        </form>
    </div>
</div>
<div class="portlet box">
    <div class="portlet-title">
    </div>
 
    <div>
        <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>SP2D No</th>
                    <th>Jenis</th>
                    <th>Tgl SP2D</th>
                    <th>Nilai SP2D</th>
                    <th>Status SP2D</th>
                    <th>Tgl Cetak</th>
                    <th>Pegawai TTD</th>
                    <th>Pilih</th>
                    <th>Unduh PDF</th>

                </tr>
            </thead>
            <tbody id="btlspdtablebody" >
            </tbody>
        </table>
    </div>
</div>
 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2dup/sp2dup.js"></script>  
