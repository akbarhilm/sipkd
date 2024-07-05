<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPM</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">  SPM BL LS</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  SPM BL LS</div>
         
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppup()"  />
                    <input type="text"  name="skpd"  readonly="true" id="skpd"  class="m-wrap large" size="80"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" />
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}" readonly="true"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
            <!--div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridpagusppup()'>Cari</button>
                </div>
            </div-->

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="blspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Nomor SPP</th>  
                        <th>Nilai SPP</th> 
                        <th>Nomor SPM</th>
                        <th>Nama Kegiatan</th>
                        <th>Entry SPM</th>
                        <th>Potongan Non Ayat</th>
                        <th>Potongan UM / Ayat</th>
                        <th>Pot. Swadana (BLUD)</th>
                        <th>Edit Rekan Kontrak</th>
                    </tr>
                </thead>
                <tbody id="blspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>

                    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmblls/spmblls.js"></script>  