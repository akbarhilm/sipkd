<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP BTL</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah  SPP BTL</a></li>
</ul>
        <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbtl"  id="refsppbtl"   action="${pageContext.request.contextPath}/btl/prosesupdate" class="form-horizontal">
  <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BTL</div>       
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
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan   ruleCekBulan"     size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd" value="${skpd.kodeSkpd} / ${skpd.namaSkpd}"  cssClass="required"   type="text" size="75" maxlength="80" readonly='true'  />
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
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="18" maxlength="18"   />  
                        <form:errors path="nipPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"  cssClass="required"   size="50" maxlength="50"   />  
                        <form:errors path="namaPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>
                    
                      <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kodeBank" id='kodeBank'    />
                        <form:hidden path="idBank" id='idBank'    />
                        <form:input   path="kodeBankTransfer" id="kodeBankTransfer"  cssClass="required"   size="10" maxlength="7" readonly="true" />  &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listbankinduk?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a> 
                        <form:errors path="kodeBank"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaBankTransfer" id="namaBankTransfer"  cssClass="required"   size="60" maxlength="75" readonly="true" />  
                        <form:errors path="namaBankTransfer"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nomor Rekening Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nomorRekBank" id="nomorRekBank"  cssClass="required "   size="31" maxlength="30"   />  
                        <form:errors path="nomorRekBank"  cssClass="error"  />
                    </div>
                </div>
            </div>  
                    
             
            <div class="form-group">
                <label class="col-md-3 control-label">Uraian:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea path="keterangan" id="uraian"  cssClass="required"    cols="30" rows="3"  />     
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  
                    
                    
                    <div id="hiddenDiv1" class="form-group" style="visibility:hidden;">
                <label class="col-md-3 control-label">Nama Pimpinan::</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="namaPenerima" id="namaPenerima"    size="50" maxlength="55"   />  
                        <form:errors path="namaPenerima"  cssClass="error"  /> 
                    </div>
                </div>
            </div> 
                    
         
                    
            <div id="hiddenDiv2" class="form-group" style="visibility:hidden;">
                <label class="col-md-3 control-label">Nama Penerima:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="namaYayasan" id="namaYayasan"    size="45" maxlength="50"   />  
                        <form:errors path="namaYayasan"  cssClass="error"  /> 
                    </div>
                </div>
            </div>   
                    
                    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >Simpan</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet">
        
        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
        <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                 <tr>
                     <th >No</th>
                    <th >SPD No</th>
                    <th >Kode Akun</th>
                    <th >Nama Akun</th>
                    <th >Nilai SPD</th> 
                    <th >SPP SEBELUM</th>
                    <th >SPP SISA</th>
                    <th >Nilai SPP</th>
                    <th >Pilih</th>
                                            
                </tr>
                
            </thead>
            <tfoot>
                <tr>
                    <th colspan="7" style="text-align:right">Total:</th>
                   <!-- <th>
                        <input type='text' id="totalspd"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th> -->
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
                        
                    <script type="text/javascript">
                    function idskpdCheck(){
                    var idskpd = document.getElementById("idskpd").value;
                   // alert(idskpd);
                    if(idskpd == "761"){
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbtl/editsppbtl.js"></script>  