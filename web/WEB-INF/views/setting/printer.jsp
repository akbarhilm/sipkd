<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/spdbtl.js"></script>   
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}">Home </a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">Setting Aplikasi</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Setting Aplikasi</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form  method="post"   id="spdBTLMasterform"    class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Default Printer:</label>
                <div class="col-md-4">
                    <select id="daftarprinter" name="daftarprinter"></select>
                </div>
            </div>



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function() {
        getlistprinter("daftarprinter");
    });
    function getlistprinter(target) {
        var listprinter = jsPrintSetup.getPrintersList();
        if (listprinter) {
            var arrayprinter = listprinter.split(",");
            var opt = "<option value=''>-</option>"
            for (var i = 0; i < arrayprinter.length; i++) {
                opt += "<option value='" + arrayprinter[i] + "'>" + arrayprinter[i] + "</option>"
            }
            $('#' + target).html(opt);
        }
    }
</script>