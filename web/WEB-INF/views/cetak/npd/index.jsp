<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Cetak NPD</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  Cetak</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak  NPD</div>
        <div class="actions">            
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
                    <input type="hidden" id="kodeSkpd" name="kodeSkpd" value="${skpd.kodeSkpd}"  />
                    <input type="hidden" id="level" name="level" value="${skpd.levelSkpd}"  />
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
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
        <!--    <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridpagusppup()'>Cari</button>
                </div>
            </div>-->

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
                        <th>Nomor NPD</th> 
                        <th>Tgl NPD</th> 
                        <th>ID Kegiatan</th>
                        <th>Keterangan</th>
                        <th>Nilai NPD</th>
                        <th>Status</th>
                        <th>Pegawai PPTK</th> 
                        <th>Pilih</th>
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


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/npd/cetaknpd.js"></script>  
