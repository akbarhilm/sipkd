<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#">Pengajuan SP2D</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Bantuan</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Bantuan</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD Kordinator :</label>
                <div class="col-md-8">


                    <input type="hidden" id="idskpd"  name="idskpd" value="${idskpds}" onchange="gridsp2dbtlls()"  /> 
                    <input type="hidden" id="kodewilayah"name="kodewilayah" value="${sessionScope.pengguna.kodeProses}" /> 
                    <input type="text"  name="skpd" value="${kodeSkpd} / ${namaskpd}" readOnly="true" id="skpd"  class="m-wrap large" size="50"  value="${namaskpd}" />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpdbantuan?target='_top'" title="Pilih SKPD"><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>
        </form>
    </div>
</div>        
<div class="portlet box">
    <div class="portlet-title">

    </div>
    <div>
        <table id="btllssp2dtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>SPM No</th>
                    <th>Tgl SPM</th>
                    <th>Nilai SPM</th>
                    <th>Kegiatan</th>
                    <th>SP2D No</th>
                    <th>Tgl SP2D</th>
                    <th>Status</th>
                    <th>Pilihan</th>
                </tr>
            </thead>
            <tbody id="btllssp2dtablebody">
            </tbody>
        </table>
    </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/bantuanls/indexsp2dbantuanls.js"></script> 