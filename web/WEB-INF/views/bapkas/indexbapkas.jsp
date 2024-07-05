<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>    
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  Berita Acara Pemeriksaan Kas </div>
        <div class="actions">
            <a href="#" onclick="pindahhalaman()" class="btn dark" id="tambahspj"><i class="icon-plus"></i> Tambah</a> 
         <!--   <a id="tambahspj" onclick="setbtntambah()" class="btn dark" ><i class="icon-plus"></i> Tambah</a>-->
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readOnly="true"/> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppup()"  />
                    <input type="hidden" id="tglBkuPros" name="tglBkuPros" value="${tglBkuPros}" />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="70"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" readOnly="true"/>
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
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
                <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Tgl BAP Kas</th>
                            <th>Bulan BAP Kas</th>
                            <th>Kode Wilayah SP2D</th>
                            <th>Edit  -  Hapus  -  Unduh</th>
                        </tr>
                    </thead>
                    <tbody id="btlspdtablebody" >
                    </tbody>                
                </table>
            </div>
        </form>
    </div>
 
    <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkas/indexbapkas.js"></script>  
