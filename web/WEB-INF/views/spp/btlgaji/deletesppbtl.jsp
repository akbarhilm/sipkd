<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

    $(document).ready(function() {
        $('#ketgaji').val($('#uraian').val());
        $('#textjumkotsimpeg').val($('#jumkotsimpeg').val());
    });

    function pindahhalamanadepan() {
        var idskpd = $.trim($("#idskpd").val());
        if (idskpd) {
            //window.location.replace(getbasepath() + "/btl/indexbtl/" + idskpd);
            window.location.replace(getbasepath() + "/btlgaji/indexbtl");
        } else {
            window.location.replace(getbasepath() + "/btlgaji/indexbtl");
        }
    }


</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP BTL PENGHASILAN</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus  SPP BTL PENGHASILAN</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbtl"  id="refsppbtl"   action="${pageContext.request.contextPath}/btlgaji/prosesdelete" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BTL PENGHASILAN</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan"     size="8"  readonly="true"  />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true' value="${skpd.kodeSkpd} / ${skpd.namaSkpd}"  />  
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>           
            <div class="form-group">
                <label class="col-md-3 control-label">No SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div>      

            <div id="" class="form-group">
                <label class="col-md-3 control-label">Jenis / Macam :</label>
                <div class="col-md-4">

                    <form:select path="kodeSimpeg" id='kodesimpeg'  disabled="true" >
                        <form:option value="0">-- Pilih --</form:option>
                        <form:option value="1">Gaji</form:option>
                        <form:option value="2">TKD</form:option>
                        <form:option value="3">Transport</form:option>
                        <form:option value="4">PPh</form:option>
                    </form:select>
                </div>
            </div> 

            <div id="" class="form-group">
                <label class="col-md-3 control-label">Pilih Data SIMPEG :</label>
                <div class="col-md-4">
                    <input type="text"   id="ketgaji"  class="m-wrap large" size="40" readOnly="true"/>  
                    <form:hidden path="idSimpeg" id="idlist" name="idlist"  />
                    <!--form:hidden path="kodeSimpeg" id='kodesimpeg' onchange="gridspprinci()"   /-->

                </div>
            </div>
            <div id="" class="form-group">
                <label class="col-md-3 control-label">Nilai (Total) :</label>
                <div class="col-md-4">
                    <input type="text"   id="textjumkotsimpeg"  class="m-wrap large" size="20" readOnly="true"/>
                    <form:hidden path="jumkotSimpeg" id='jumkotsimpeg' name="jumkotsimpeg" readonly="true" size="20" maxlength="20"/>
                    <form:errors path="jumkotSimpeg" class="error" />

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="30" maxlength="18" readonly="true"  />  
                        <form:errors path="nipPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"  cssClass="required"   size="50" maxlength="50"  readonly="true" />  
                        <form:errors path="namaPptk"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Uraian:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea cols="58" rows="3"   path="keterangan" id="uraian"  cssClass="required"    maxlength="400"  readonly="true" />  
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"  type="submit" class="btn blue" >Hapus</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" >Kembali</a>
                </div>
            </div>

        </div>
    </div>

</form:form>

