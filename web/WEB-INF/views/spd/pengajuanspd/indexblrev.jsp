<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spd/pengajuanblrev/bl/indexblrev">Pengajuan SPD APBDP</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Belanja Langsung APBDP</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Belanja Langsung APBDP</div>
       <!-- <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div>-->
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="getlistspdbl(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu Belanja Langsung:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="pagubtl" id="pagubtl" readonly="true" class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="sisaspd" id="sisaspd"  readonly="true"  class="m-wrap medium inputnumber" /> 
                    </div>
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
                    <th>SPD No</th>
                    <th>Tgl SPD</th>
                   <!-- <th>Nilai SPD</th>-->
                    <th>Status SPD</th>
                    <th>Pilihan</th>
                </tr>
            </thead>
            <tbody id="btlspdtablebody" >
            </tbody>
        </table>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/spdblrev.js"></script>   