<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Dokttd</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="fungsicmda"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/dokttd/simpandokttd" class="form-horizontal">
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
                        <form:select  path="kodeDok">
                            <option value="ALL" >ALL</option>
                            <option value="SPP" selected>SPP</option>
                            <option value="SP2D">SP2D</option>
                        </form:select>
                        <form:errors path="kodeDok" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idNip" id="idNip" size="20" maxlength="18"   />  
                        <form:errors path="idNip" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idNrk" id="idNrk" size="10" maxlength="6"   />  
                        <form:errors path="idNrk" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NAMA :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPegawai" id="namaPegawai" size="50" maxlength="75"   />  
                        <form:errors path="namaPegawai" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">JABATAN :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatan" id="jabatan" size="50" maxlength="75"   />  
                        <form:errors path="jabatan" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NILAI :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiMin" id="nilaiMin" size="15" maxlength="12"   />  
                        <form:errors path="nilaiMin" class="error" />
                        s.d
                        <form:input   path="nilaiMax" id="nilaiMax" size="18" maxlength="15"   />  
                        <form:errors path="nilaiMax" class="error" />
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
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/dokttd" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 