<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bank"  >Daftar Dokttd</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Dokttd <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Hapus Dokttd</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/dokttd/deletedokttd' class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">ID</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'     />                  
                    <form:errors path="id" class="error" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">C DOK</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="kodeDok" disabled="true">
                            <option value="ALL" ${spdBTLMaster.kodeDok == 'ALL'?'selected':''}>ALL</option>
                            <option value="SPP" ${spdBTLMaster.kodeDok == 'SPP'?'selected':''}>SPP</option>
                            <option value="SP2D" ${spdBTLMaster.kodeDok == 'SP2D'?'selected':''}>SP2D</option>
                        </form:select>
                        <form:errors path="kodeDok" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idNip" id="idNip" size="20" maxlength="18" readOnly="true"   />  
                        <form:errors path="idNip" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idNrk" id="idNrk" size="10" maxlength="6" readOnly="true"  />  
                        <form:errors path="idNrk" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NAMA :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPegawai" id="namaPegawai" size="50" maxlength="75" readOnly="true"  />  
                        <form:errors path="namaPegawai" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">JABATAN :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatan" id="jabatan" size="50" maxlength="75" readOnly="true"  />  
                        <form:errors path="jabatan" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NILAI :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiMin" id="nilaiMin" size="16" maxlength="14"  readOnly="true" />  
                        <form:errors path="nilaiMin" class="error" />
                        s.d
                        <form:input   path="nilaiMax" id="nilaiMax" size="20" maxlength="17" readOnly="true"  />  
                        <form:errors path="nilaiMax" class="error" />
                    </div>
                </div>  
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isAktif" disabled="true">
                            <option value="1" ${spdBTLMaster.isAktif == '1'?'selected':''}>Aktif</option>
                            <option value="0" ${spdBTLMaster.isAktif == '0'?'selected':''}>Tidak Aktif</option>
                        </form:select>
                        <form:errors path="isAktif" class="error" />
                    </div>
                </div>  
            </div> 
                    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Hapus </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/dokttd" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>