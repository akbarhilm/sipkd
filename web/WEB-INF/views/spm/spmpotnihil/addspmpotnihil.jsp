<%-- 
    Document   : addspmpotayat
    Created on : Nov 24, 2014, 9:51:28 AM
    Author     : Xalamaster
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPM</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">SPM Potongan Ayat</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>SPM Potogan NIHIL - BELANJA LANGSUNG LS</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
       <form class="form-horizontal"> 
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="tahunAnggaran" size="5"  class="m-wrap medium inputnumber" readonly="true" value="${tahunAnggaran}"  onchange="gridspmpotayat()" />
                            <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                        </div>
                    </div>
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="skpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" class="m-wrap large" size="50" readonly="true" />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NO.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sspm" name="sspm" value="${nospm}" size="7" readonly="true"   class="m-wrap large"  />
                            <input id="nospm" type="hidden" name="nospm" value="${idspm}"/>
                            &nbsp; &nbsp;<!--a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/spmpotayat/spmlistpopup" title="Pilih SPM"  ><i class="icon-zoom-in"></i> Pilih</a-->
                        </div>
                    </div>
            </div> 
                        <div class="form-group">
                <label class="col-md-3 control-label">TGL.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sispm" name="sispm" value="${tglspm}" size="10" readonly="true" Class=""   />
                            <input type="hidden" id="ssspm" name="ssspm" value="${tglspm}" size="20" readonly="true" Class=""   />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Total SPM</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text"  id="totspm" name="totspm"  value="" class="inputnumber"  size="25" readonly="true" />
                            <input type="hidden" id="totspmhide" name="totspmhide" value="">
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
            <table id="blspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th width="85">No</th>
                        <th>Akun</th>
                        
                        <th width="350">Nilai</th>
                    </tr>
                </thead>
                <tbody id="blspdtablebody" >    
                </tbody>                
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid">
     <div class="col-md-offset-3 col-md-9" align="Right">
          <button type="button" class="btn blue" onclick='cekmoney()'>Simpan</button>
       <!-- <button type="button" class="btn blue" onclick='cek( )'>Cek</button> -->
          <a  href="${pageContext.request.contextPath}/spmblls/indexspmblls" class="btn blue" >Kembali</a>
     </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmpotayat/spmpotnihil.js"></script>  
<script type="text/javascript">
 
</script>
