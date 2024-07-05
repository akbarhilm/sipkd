<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<script src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listrefakun.js"></script>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Update Akun</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/refakun/prosesupdateakun' class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Id Akun Induk :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idAkun" id="idAkun" />
                        <form:input   path="idAkunInduk" id="idAkunInduk"   readonly="true"     size="15" maxlength="25"    />  
                        <form:errors path="idAkunInduk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Akun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="kodeAkun" id="kodeAkun"    size="20" maxlength="20" onfocus="maskkoderek( )"  />  
                        <form:errors path="kodeAkun" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Akun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaAkun" id="namaAkun"    size="41" maxlength="120" />  
                        <form:errors path="namaAkun" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <form:select path="isAktif">
                        <option value="1" ${progcmd.isAktif == '1'?'selected':''} >Aktif</option>
                        <option value="0" ${progcmd.isAktif == '0'?'selected':''} >Tidak Aktif</option>
                    </form:select>
                    <form:errors path="isAktif" class="error label label-important" />
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berlaku</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="tahunBerlaku" id="tahunBerlaku"    size="4" maxlength="4"   />  
                        <form:errors path="tahunBerlaku" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berakhir</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="tahunBerakhir" id="tahunBerakhir"    size="4" maxlength="4"   />  
                        <form:errors path="tahunBerakhir" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" onclick="closer()"> Update </button>
                </div>
            </div>
        </form:form>
    </div>
</div> 
<script>
  
    function maskkoderek() {
        var data = $("#kodeAkun").val();
        var arrrek = data.split(".");
        console.log(" arrrek " + arrrek.length)
        if (arrrek.length == 1) {
            $("#kodeAkun").mask(arrrek[0] + ".9");
        }
        else if (arrrek.length == 2) {
            $("#kodeAkun").mask(arrrek[0] + ".9");
        } else if (arrrek.length == 3) {
            $("#kodeAkun").mask(arrrek[0] + "." + arrrek[1] +  ".9");
        } else if (arrrek.length == 4) {
            $("#kodeAkun").mask(arrrek[0] + "." + arrrek[1] + "." + arrrek[2] + ".99");
        } else if (arrrek.length == 5) {
            $("#kodeAkun").mask(arrrek[0] + 
                    "." + arrrek[1] + "." + arrrek[2] + "." + arrrek[3] + ".99");
        } 

    }
    function closer(){
        parent.$.fancybox.close();
    }
</script>