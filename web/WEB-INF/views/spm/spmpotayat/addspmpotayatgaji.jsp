<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/spmbtlgaji/indexspmbtlgaji">SPM BTL LS Gaji</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">SPM Potongan Ayat</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>SPM Potogan Ayat</div>
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
                            <input type="text" id="skpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" class="m-wrap large" size="75" readonly="true" />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NO.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sspm" name="sspm" value="${nospm}" size="7" readonly="true"   class="m-wrap large"  />
                            <input id="nospm" type="hidden" name="nospm" value="${idspm}"/>
                            <input id="idspp" type="hidden" name="idspp" value="${idspp}"/>
                            <input id="kodesimpeg" type="hidden" name="kodesimpeg" value="${kode}"/>
                            &nbsp; &nbsp;<!--a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/spmpotayat/spmlistpopup" title="Pilih SPM"  ><i class="icon-zoom-in"></i> Pilih</a-->
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">TGL.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sispm" name="sispm" value="" size="10" readonly="true" Class=""   />
                            <input type="hidden" id="ssspm" name="ssspm" value="${tglspm}" size="20" readonly="true" Class=""   />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Total Potongan:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sipotspm" name="sipotspm" value="" size="15" readonly="true" Class=""   />
                            <input type="hidden" id="sspotspm" name="sspotspm" value="${jumkotpot}" size="20" readonly="true" Class=""   />
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
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th width="85">No</th>
                        <th>Potongan</th>
                        <th>Banyak</th>
                        <th width="300">Nilai</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="3" style="text-align:right">Total:</th>
                        <!-- <th>
                             <input type='text' id="totalspd"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                         </th> -->
                        <th>
                            <input type='text' id="totalpotspm"   name="totalpotspm" readonly="true"  class="inputmoney"      />
                        </th>
                    </tr>
                </tfoot>
                <tbody id="btlspdtablebody" >

                </tbody>                
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid">
     <div class="col-md-offset-3 col-md-9" align="Right">
          <button type="button" class="btn blue" onclick='submitnilai( )'>Simpan</button>
          <a class="btn blue"  href="${pageContext.request.contextPath}/spmbtlgaji/indexspmbtlgaji" onclick="" >Kembali</a>
     </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmpotayat/spmpotayatgaji.js"></script>  
