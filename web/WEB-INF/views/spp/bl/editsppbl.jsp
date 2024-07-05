<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP BL</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah  SPP BL</a></li>
</ul>
        <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbl"  id="refsppbl"   action="${pageContext.request.contextPath}/bl/prosesupdate" class="form-horizontal">
  <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BL</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <input type="hidden" id="id" path= "id" name="id" value="${id}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan ruleCekBulan"     size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>           
            <div class="form-group">
                <label class="col-md-3 control-label">No SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div>      
             <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="25" maxlength="18"   />  
                        <form:errors path="nipPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"  cssClass="required"   size="45" maxlength="50"   />  
                        <form:errors path="namaPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Uang Muka:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:checkbox   path="kodeUangMuka" id="kodeUangMuka" />  
                        
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bl/listbastpopup?target='_top'" id="popup1" title="Pilih Bast"  ><i class="icon-zoom-in"></i> Pilih</a>                        
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bl/listbastpopup2?target='_top'" id="popup2" title="Pilih Bast"  ><i class="icon-zoom-in"></i> Pilih</a> 
                        <form:errors path="kodeUangMuka"  cssClass="error"  />  
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nomor Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kontrak.noKontrak" id="noKontrak"   readonly="true" size="45"   /> 
                        <form:hidden   path="kontrak.idKontrak" id="idKontrak"   readonly="true" size="45"   /> 
                        <form:errors path="kontrak.noKontrak"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="kontrak.nilaiKontrak" id="nilaiKontrak"   readonly="true" size="45"  />  
                        <form:errors path="kontrak.nilaiKontrak"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bast:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="bast.noBast" id="noBast"   readonly="true" size="45"   />  
                        <form:hidden path="bast.idBast" id="idBast"   readonly="true" size="45"   /> 
                        <form:errors path="bast.noBast"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kegiatan.namaKegiatan" id="namaKegiatan"   readonly="true" size="45"  />
                        <form:hidden   path="kegiatan.idKegiatan" id="idKegiatan" onChange="gridspprinci()"   readonly="true" size="45"  />
                        <form:hidden   path="akun.idAkun" id="idAkun"   readonly="true" size="45"  />
                        <form:errors path="kegiatan.namaKegiatan"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Uraian:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="keterangan" id="uraian"  cssClass="required"   size="55" maxlength="70"   />  
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >Ubah</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian SPP BL</div>       
        </div> 
        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
        <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th rowspan="2">No</th>
                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="nospdfilter" name="nospdfilter" value="" title="Ketik no SPD yang dicari" /></th>
                    <th rowspan="2">Kode Akun</th>
                    <th rowspan="2">Nama Akun</th>
                    <th rowspan="2">Nilai SPD</th>                    
                    <th rowspan="2">Nilai Sebelum</th> 
                    <th rowspan="2">Nilai Sisa</th> 
                    <th rowspan="2">Nilai SPP</th>
                    <th rowspan="2"></th>                        
                </tr>
                <tr> 
                    <th>Nomor SPD</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th colspan="6" style="text-align:right">Total:</th>
                    <th>
                        <input type='text' id="totalspd"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>
                    <th>
                        <input type='text' id="totalspp"   name="totalspp" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>
                    <th></th>
                </tr>
            </tfoot>
            <tbody id="spprincitablebody" >
            </tbody>             
        </table> 
    </div> 
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbl/editsppbl.js"></script>  