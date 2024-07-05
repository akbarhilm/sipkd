<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Cetak SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  Cetak</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak  SP2D</div>
       
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="gridsppup(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="hidden" id="levelSkpd" name="levelSkpd"  value="${levelSkpd}" />
                    <input type="hidden" id="kproses" name="kproses" value="${pengguna.kodeProses}"  />
                    <input type="hidden" id="user" name="user" value="${pengguna.namaPengguna}"  />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                   <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpdwil?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

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
           

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="cetakspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr  >
                        <th>No</th>
                      <!-- <th>Nomor SPM</th>-->
                        <th>No SP2D</th> 
                      <!--  <th>Tgl SPM</th>-->
                        <th>Tgl SP2D</th>
                       <!-- <th>Kode Potongan</th> -->
                        <th>Beban</th> 
                        <th>Jenis</th> 
                        <th>Nilai SP2D</th>
                        <th>Status</th>
                        <th>Nama KBUD</th>
                        <th>Pilih Cetak</th>
                       <!-- <th>Pilih Cetak Pot</th>
                        <th>Unduh PDF</th>-->
                        <th>Unduh PDF</th>
                        <th>Kembali</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/sp2d/cetaksp2d.js"></script>  
