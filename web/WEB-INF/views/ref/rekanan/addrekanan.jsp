<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/rekanan"  >Daftar Rekanan</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Rekanan <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Rekanan</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/rekanan/prosessimpanr" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Rekanan:</label>
                <div class="col-md-4">
                    <form:hidden path="idRekanan" id='idRekanan'     />                  
                    <form:input path="kodeRekanan" id="kodeRekanan" type="text" size="6" maxlength="6"   /> 
                    <form:errors path="kodeRekanan" class="error" />
                </div>
            </div>
              <div class="form-group">
                <label class="col-md-3 control-label">Id Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idRekananAkte" id="idRekananAkte"    size="40"  />  
                        <form:errors path="idRekananAkte" class="error" />
                    </div>
                </div>  
            </div>
                <div class="form-group">
                <label class="col-md-3 control-label">NPWP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="idRekananNpwp" id="idRekananNpwp"    size="40"    />  
                        <form:errors path="idRekananNpwp" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaRekanan" id="namaRekanan"    size="40" />  
                        <form:errors path="namaRekanan" class="error" />
                    </div>
                </div>  
            </div>   
                    <div class="form-group">
                <label class="col-md-3 control-label">Alamat Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="alamatRekanan" id="alamatRekanan"    size="55"   />  
                        <form:errors path="alamatRekanan" class="error" />
                    </div>
                </div>  
            </div> 
              <div class="form-group">
                <label class="col-md-3 control-label">Nama Direktur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaDirekturRekanan" id="namaDirekturRekanan"    size="40"   />  
                        <form:errors path="namaDirekturRekanan" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/rekanan" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 