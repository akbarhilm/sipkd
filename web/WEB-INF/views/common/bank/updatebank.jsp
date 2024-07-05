<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bank"  >Daftar Bank</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Bank<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Ubah Bank</div>       
    </div>
    <div class="portlet-body flip-scroll">
         <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/bank/updatebanks' class="form-horizontal">
          
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaBank" id="namaBank"    size="55" maxlength="50"   />  
                        <form:errors path="namaBank" class="error " />
                    </div>
                </div>  
            </div>  
                    <div class="form-group">
                <label class="col-md-3 control-label">Nomor Rekening:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="rekeningBank" id="rekeningBank"    size="55" maxlength="50"   />  
                        <form:errors path="rekeningBank" class="error " />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="isAktif">
                            <option value="1"  ${spdBTLMaster.isAktif == '1'?'selected':''} >Aktif</option>
                            <option value="0" ${spdBTLMaster.isAktif == '0'?'selected':''} >Tidak Aktif</option>
                        </form:select>
                         <form:errors path="isAktif" class="error label label-important" />
                    </div>
                </div>  
            </div>   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"    href="${pageContext.request.contextPath}/bank" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 