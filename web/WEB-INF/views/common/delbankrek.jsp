<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bankrek"  >Daftar Bank</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Bank <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Hapus Bank</div>       
    </div>

     <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/bankrek/deletebankrek' class="form-horizontal">


    <div class="form-group">
        <label class="col-md-3 control-label">SKPD:</label>
        <div class="col-md-5">
            <div class="input-group">
               
                <form:errors path="skpd.idSkpd" class="label label-important" />
            </div>
        </div>
    </div>   



   
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank:</label>
                <div class="col-md-4">
                    <form:hidden path="idBankrek" id='idBankrek'     />                  
                    <form:input path="kodeBankrek" id="kodeBankrek" type="text" size="6" maxlength="6" readOnly="true"  /> 
                    <form:errors path="kodeBankrek" class="error" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaBankrek" id="namaBankrek"    size="55" maxlength="50"  readOnly="true" />  
                        <form:errors path="namaBankrek" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">BANK STS:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idBANKSTS" id="idBANKSTS"    size="55" maxlength="50" readOnly="true"   />  
                        <form:errors path="idBANKSTS" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">BANK SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idBANKSPM" id="idBANKSPM"    size="55" maxlength="50" readOnly="true"   />  
                        <form:errors path="idBANKSPM" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berlaku:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeThnBerlaku" id="kodeThnBerlaku"    size="55" maxlength="50" readOnly="true"   />  
                        <form:errors path="kodeThnBerlaku" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berakhir:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeThnBerakhir" id="kodeThnBerakhir"    size="55" maxlength="50" readOnly="true"   />  
                        <form:errors path="kodeThnBerakhir" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isAktif" disabled="true">
                            <option value="1">Aktif</option>
                            <option value="0">Tidak Aktif</option>
                        </form:select>
                        <form:errors path="isAktif" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Hapus </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bankrek" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 