<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Update Kode Bank SPP Bantuan<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Update Kode Bank SPP Bantuan</div>       
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bankbantuan/updatebank" class="form-horizontal">         

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idSpp" id='idSpp'     />   
                        <form:input path="tahun" id="tahun" cssClass="required" type="text" readonly="true" size="4" maxlength="6" value="${tahunAnggaran}"  /> 
                        <form:errors path="tahun" class="tahun" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No SPP:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="noSpp" value="" id="noSpp" readOnly="true" /> &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bankbantuan/listsppbantuan/?target='_top'" title="Pilih SPP Bantuan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD Koordinator:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="ketSkpd" value="" id="ketSkpd"  class="m-wrap large" size="80" readOnly="true" />
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">NIP/Nama Bendahara:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="ketBendahara" value="" id="ketBendahara" size="80" readOnly="true" />
                    </div>
                </div>
            </div> 
            
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" name="kodeBankTransfer" id="kodeBankTransfer" />
                        <form:hidden path="kodeBank" id='kodeBank' onchange="setKodeBank()" /> 
                        <form:input type="text"  path="namaBank" value="" id="namaBank" size="33" readOnly="true" /> &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listbankinduk/?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a> 
                        <form:errors path="namaBank" class="error" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekening:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="noRek" value="" id="noRek" onkeypress="return isNumber(event)" />
                        <form:errors path="noRek" class="error" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Penerima:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="namaPenerima" value="" id="namaPenerima"  readOnly="true" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Org/Yayasan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="namaOrg" value="" id="namaOrg" readOnly="true" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Bantuan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="alamatPenerima" value="" id="alamatPenerima"  class="m-wrap large" size="80" readOnly="true" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Uraian SPP:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="uraian" value="" id="uraian"  class="m-wrap large" size="80" readOnly="true" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SPP:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input type="text"  path="nilaiSpp" value="" id="nilaiSpp" readOnly="true" />
                    </div>
                </div>
            </div> 
                    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"    type="submit" class="btn blue" > Simpan </button>
                    
                </div>
            </div>
        </form:form>
    </div>
</div> 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankbantuan/bankbantuan.js"></script>  