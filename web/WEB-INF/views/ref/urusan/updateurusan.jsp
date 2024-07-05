<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/rekanan"  >Daftar Urusan</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Urusan <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Urusan</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/urusan/prosesupdate" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Urusan</label>
                <div class="col-md-4">
                    <form:hidden path="idUrusan" id='idUrusan'     />                  
                    <form:input path="kodeUrusan" id="kodeUrusan" type="text" size="4" maxlength="4" required="true"   /> 
                    <form:errors path="kodeUrusan" class="error" />
                </div>
            </div>
              <div class="form-group">
                <label class="col-md-3 control-label">No Urusan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noUrusan" id="noUrusan"    size="2" maxlength="2" />  
                        <form:errors path="noUrusan" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Urusan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaUrusan" id="namaUrusan"    size="40" maxlength="150"  />  
                        <form:errors path="namaUrusan" class="error" />
                    </div>
                </div>  
            </div>
                <div class="form-group">
                <label class="col-md-3 control-label">Wajib:</label>
                <div class="col-md-4">
                    <div class="input-group">
                         <form:select  path="kodeWajib">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </form:select>
                        <form:errors path="isAktif" class="error" />
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
               <div class="form-group">
                <label class="col-md-3 control-label">Fungsi:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="fungsi.idFungsi" id='idFungsi'  />
                        <form:input path="fungsi.namaFungsi" id="namaFungsi"  cssClass="required"   type="text" size="55" maxlength="180" readonly='true'  />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/urusan/listfungsipopup?target='_top'" title="Pilih Fungsi"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    </div>
                </div>
            </div>
             
                </div>  
            </div>   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Update </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/urusan" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 