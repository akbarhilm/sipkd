<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bankrek"  >Daftar Rekening Bank</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Update Rekening Bank <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Update Rekening Bank</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bankrek/updatebankreks" class="form-horizontal">

             <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd"   type="text" size="55" maxlength="80" readonly='true'  />
                          &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> 
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>      

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank:</label>
                <div class="col-md-4">
                  
                    <form:hidden path="idBankrek" id='idBankrek'     />    
                    <form:input path="kodeBankrek" id="kodeBankrek" type="text" size="6" maxlength="6"   /> 
                    <form:errors path="kodeBankrek" class="error" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaBankrek" id="namaBankrek"    size="55" maxlength="50"   />  
                        <form:errors path="namaBankrek" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">BANK STS:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idBANKSTS" id="idBANKSTS"    size="55" maxlength="50"   />  
                        <form:errors path="idBANKSTS" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">BANK SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idBANKSPM" id="idBANKSPM"    size="55" maxlength="50"   />  
                        <form:errors path="idBANKSPM" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">TAHUN BERLAKU:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeThnBerlaku" id="kodeThnBerlaku"    size="55" maxlength="50"   />  
                        <form:errors path="kodeThnBerlaku" class="error" />
                    </div>
                </div>  
            </div>     

            <div class="form-group">
                <label class="col-md-3 control-label">TAHUN BERAKHIR:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeThnBerakhir" id="kodeThnBerakhir"    size="55" maxlength="50"   />  
                        <form:errors path="kodeThnBerakhir" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isAktif">
                            <option value="1">Aktif</option>
                            <option value="0">Tidak Aktif</option>
                        </form:select>
                        <form:errors path="isAktif" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Update </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bankrek" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 