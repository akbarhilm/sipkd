<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
    .inputmoney2{
        text-align: right;
    }
</style>
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
    <li><a href="#">Tambah  SPP BL</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbl"  id="refsppbl"   action="${pageContext.request.contextPath}/bl/prosessimpan" class="form-horizontal">
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
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" readonly='true'  />
                        <c:if  test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
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
                        <form:hidden   path="kodeUangMuka" id="kodeUangMuka"      /> 
                        <form:checkbox   path="potonganUangMuka" id="potonganUangMuka"   />   
                        <form:errors path="potonganUangMuka"  cssClass="error"  />  
                    </div>
                </div>
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Nomor Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kontrak.noKontrak" id="noKontrak"   readonly="true" size="35"   /> 
                        <form:hidden   path="kontrak.idKontrak" id="idKontrak"      /> 
                        <form:errors path="kontrak.noKontrak"  cssClass="error"  />
                        &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bl/listbastpopup?target='_top'" id="popup1" title="Pilih Bast"  ><i class="icon-zoom-in"></i> Pilih</a>                        
                        &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bl/listbastpopup2?target='_top'" id="popup2" title="Pilih Bast"  ><i class="icon-zoom-in"></i> Pilih</a> 

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
                        <form:input   path="bast.noBast" id="noBast"   readonly="true" size="45"    />  
                        <form:hidden path="bast.idBast" id="idBast"   readonly="true" size="45"   /> 
                        <form:errors path="bast.noBast"  cssClass="error"  />
                        <input type="hidden" id="nomorBast"   name="nomorBast" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea path="kegiatan.namaKegiatan" id="namaKegiatan" cols="50" rows="3" readonly="true" /> 
                        <form:hidden   path="kegiatan.idKegiatan" id="idKegiatan" onChange="gridspprinci()"   readonly="true" size="45"  />
                        <form:hidden   path="akun.idAkun" id="idAkun"      />
                        <form:errors path="kegiatan.namaKegiatan"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <!------------------------ Tambahan Keterangan dari Kontrak ----------------------------->
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Perusahaan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaRekanan" id="namaRekanan"   readonly="true" size="45"  />  
                        <form:errors path="namaRekanan"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Perusahaan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="alamatRekanan" id="alamatRekanan"   readonly="true" size="55"  />  
                        <form:errors path="alamatRekanan"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Direktur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="dirRekanan" id="dirRekanan"   readonly="true" size="45"  />  
                        <form:errors path="dirRekanan"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No NPWP Perusahaan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="noNpwp" id="noNpwp"   readonly="true" size="45"  />  
                        <form:errors path="noNpwp"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kodeBank" id='kodeBank'    />
                        <form:hidden path="kodeBankTransfer" id='kodeBankTransfer'    />
                        <form:hidden path="idBank" id='idBank'    />
                        <form:input  path="namaBank" id="namaBank"   readonly="true" size="45"  />  
                        <form:errors path="namaBank"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Rekening Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="rekeningBank" id="rekeningBank"   readonly="true" size="45"  />  
                        <form:errors path="rekeningBank"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Uraian:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea cols="30" rows="3"     path="keterangan" id="uraian"  cssClass="required"  maxlength="255"  />  
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet">
        <div class="portlet-title"> 
            <div class="caption"  ><i class="icon-cogs"></i>Rincian SPP BL</div>       
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
                    <th rowspan="2">Nilai SPP</th>
                    <th rowspan="2"></th>                      
                </tr>
                <tr> 
                    <th>Nomor SPD</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th colspan="5" style="text-align:right">Total:</th>
                    
                    <th>
                        <input type='text' id="totalspp"   name="totalspp" readonly="true"     style='border:none;margin:0;width:99%;; text-align: right;'    />
                    </th>
                    <th> </th> 
                </tr>
            </tfoot>
            <tbody id="spprincitablebody" >
            </tbody>             
        </table> 
    </div> 

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbl/addsppbl.js"></script>  