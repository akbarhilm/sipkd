<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/common/listfungsi"  >Daftar Program</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Program <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Program</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/program/simpanprog" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Urusan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden  path="idUrusan" id="idUrusan"   />  
                        <span id='kodeUrusan'></span>
                        &nbsp;<a class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/program/common/listurusan?target='_top" title="Pilih Urusan"><i class="icon-zoom-in"></i> Pilih</a>
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Program:</label>
                <div class="col-md-4">
                    <form:hidden path="idProgram" id='idProgram'     />                  
                    <form:input path="kodeProgram" id="kodeProgram" type="text" size="6" maxlength="6"   /> 
                    <form:errors path="kodeProgram" class="error" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Program:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaProgram" id="namaProgram"    size="55" maxlength="50"   />  
                        <form:errors path="namaProgram" class="error" />
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
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bank" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 