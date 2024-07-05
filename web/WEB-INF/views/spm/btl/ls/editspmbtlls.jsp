<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:if test="${status == true}">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home"></i>
            <a href="${pageContext.request.contextPath}/beranda">SPM</a> 
            <span class="icon-angle-right"></span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/spmbtlls/indexspmbtlls" onclick="">Daftar SPM BTL LS</a> 
            <span class="icon-angle-right"></span>
        </li>
        <li><a href="#">Entry  SPM BTL LS</a></li>
    </ul>
    <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
    <form:form  method="post" commandName="refsppbtl"  id="refsppbtl"   action="${pageContext.request.contextPath}/spmbtlls/prosessimpan" class="form-horizontal">
        <div class="portlet box red">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>Form SPM BTL LS</div>       
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
                        <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan"  readonly="true"    size="8"    />mm/yyyy
                        <form:errors path="bulan" class="label label-important" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">SKPD:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                            <form:input path="skpd.namaSkpd" id="skpd" value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  cssClass="required"   type="text" size="55"  readonly='true'  />
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
                    <label class="col-md-3 control-label">No SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <c:if test="${setids == 0}">
                                <form:hidden  path="idspm" value="" />
                            </c:if>
                            <c:if test="${setids != 0}">
                                <form:hidden  path="idspm" />
                            </c:if>
                            <form:input path="noSpm" id="noSpm" type="text" size="25" maxlength="20" readonly='true'  /> 
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Tanggal SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input type="text" path="tglSpm" id="tglSpm" class="required valid" readonly="true" size="14" value=""/>
                            <!-- path="tglSpm" id="tglSpm" type="text" size="13" maxlength="14" tabindex="0"  cssStyle="background-color: #F1F1D9"  cssClass="required   entrytanggal "     /> -->
                            
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Kode Bank:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="kodeBank" id="kodeBank" type="text" readOnly="true" size="10" maxlength="6" tabindex="0"  cssStyle="background-color: #F1F1D9"  /> 

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Bank:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="namaBank" id="namaBank" type="text"  readOnly="true" size="50" maxlength="50" tabindex="0"  cssStyle="background-color: #F1F1D9"   /> 
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">No Rekening SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="noRekeningSPM" id="noRekeningSPM" readOnly="true" type="text" size="30" maxlength="30" tabindex="0"  cssStyle="background-color: #F1F1D9"   /> 
                        </div>
                    </div>
                </div> 
                <div class="form-group">
                    <label class="col-md-3 control-label">NIP PPTK:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="30" maxlength="21" readonly='true'  />  
                            <form:errors path="nipPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama PPTK:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="namaPptk" id="namaPptk"  cssClass="required"   size="45" maxlength="50"  readonly='true' />  
                            <form:errors path="namaPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">NIP Bendahara:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="nipBendahara" id="nipBendahara"  cssClass="required"   size="30" maxlength="21" readonly='true' />  
                            <form:errors path="nipBendahara"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Bendahara:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="namaBendahara" id="namaBendahara"  cssClass="required"   size="45" maxlength="50" readonly='true'  />  
                            <form:errors path="namaBendahara"  cssClass="error"  />
                        </div>
                    </div>
                </div>  

                <div class="form-group">
                    <label class="col-md-3 control-label">Potongan:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <c:if test="${cek != 0}">
                                <form:checkbox path="potongan" id="potongan" readOnly="true" />
                            </c:if>
                            <c:if test="${cek == 0}">
                                <form:checkbox  path="potongan" id="potongan" />
                            </c:if>

                        </div>
                    </div>
                </div>         
                <div class="form-group">
                    <label class="col-md-3 control-label">Uraian SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:textarea  cols="45" rows="3"    path="keteranganSpm" id="keteranganSpm"  cssClass="required"  maxlength="175"  cssStyle="background-color: #F1F1D9" tabindex="0"  />  
                            <form:errors path="keteranganSpm"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                        
                        <div id="hiddenDiv1" class="form-group" style="visibility:hidden;">
                <label class="col-md-3 control-label">Nama Pimpinan::</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="namaPenerima" id="namaPenerima" readonly='true'    size="30" maxlength="55"   />  
                        <form:errors path="namaPenerima"  cssClass="error"  /> 
                    </div>
                </div>
            </div> 
                    
         
                    
            <div id="hiddenDiv2" class="form-group" style="visibility:hidden;">
                <label class="col-md-3 control-label">Nama Penerima:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="namaYayasan" id="namaYayasan" readonly='true'   size="75" maxlength="50"   />  
                        <form:errors path="namaYayasan"  cssClass="error"  /> 
                    </div>
                </div>
            </div>          
                        
                        
                <form:hidden path="dokTtd.nama" />
                <form:hidden path="dokTtd.nip" />
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >Simpan</button>
                        <a class="btn blue"  href="${pageContext.request.contextPath}/spmbtlls/indexspmbtlls" onclick="" >Kembali</a>
                    </div>
                </div>

            </div>
        </div>
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>Rincian SPP BTL LS</div>       
            </div> 
            <input type="hidden" id="banyakrinci" name="banyakrinci"  />
            <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Nomor Akun</th>
                        <th>Nama Akun</th> 
                        <th>Nilai SPM</th>                                          
                    </tr>               
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="3" style="text-align:right">Total:</th>
                        <th>
                            <input type='text' id="totalspp"   name="totalspp" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                        </th>

                    </tr>
                </tfoot>
                <tbody id="spprincitablebody" >
                </tbody>             
            </table>  
        </div> 
                    
                    <script type="text/javascript">
                    function idskpdCheck(){
                    var idskpd = document.getElementById("idskpd").value;
                   // alert(idskpd);
                    if(idskpd == "761" || idskpd == "1234"){
                        document.getElementById("hiddenDiv1").style.visibility ="visible";
                        document.getElementById("hiddenDiv2").style.visibility ="visible";
                       
                        
                      
                    }else
                    {
                        //window.location = "http://google.com";
                        document.getElementById("hiddenDiv1").style.visibility ="hidden";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                       
                    }
                                       
                }
                </script>
                    
                    
    </form:form>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmbtlls/addspmbtlls.js"></script>  
</c:if>
    
<c:if test="${status == false}">
    <script type="text/javascript">
                        $(document).ready(function() {
                            bootbox.alert("PROSES SPM BLT SUDAH DITUTUP");
                        });

    </script>
</c:if>