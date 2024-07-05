<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D </a> 
        <span class="icon-angle-right"></span>
    </li>    

    <li><a href="#">Laporan Penerimaan SKPD</a></li>
</ul>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Laporan Penerimaan SKPD</div>
        <% /* <div class="actions">
             <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
             </div> */%>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun : </label>
                <div class="col-md-4">
                    <input type="text" id="tahun"  readonly="true" name="tahun" value="${tahunAnggaran}"  /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD : </label>
                <div class="col-md-4 col-md-9">
                    <input type="hidden" id="idskpd" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  readonly="true" class="m-wrap large" size="75"  value="${namaskpd}" />
                    <a class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/lappnrm/listskpdpnrm?listskpdpnrm='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a><br>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!-- <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>-->
                        <input type="text"  name="tgl"  id="tgl"  class="m-wrap large date-picker entrytanggal" size="14"  value="${tgl}" /> 
                    </div>
                </div>  
            </div>          
        </form>

        <div class="form-actions fluid">
            <div class="col-md-offset-3 col-md-9" align="Left">
                <button type="button" id="btnTambah" class="btn blue" onclick='cetak()' >Cetak</button>
            </div>
        </div>   
    </div>
</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/laporanpenerimaan/penerimaanskpd.js"></script>   